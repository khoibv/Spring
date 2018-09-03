package vn.com.khoibv.springsessiondemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.khoibv.springsessiondemo.form.UserForm;
import vn.com.khoibv.springsessiondemo.model.UserModel;
import vn.com.khoibv.springsessiondemo.service.UserService;
import vn.com.khoibv.springsessiondemo.validator.UserUpdatedGroup;

import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = {"", "/index"})
    public String index(Model model, HttpSession session) throws UnknownHostException {

        model.addAttribute("users", userService.getAll());
        model.addAttribute("sessionId", session.getId());

        InetAddress currentMachine = InetAddress.getLocalHost();
        model.addAttribute("ipaddress", currentMachine.getHostAddress());
        model.addAttribute("hostname", currentMachine.getHostName());

        return "user/index";
    }

    @GetMapping("insert")
    public String insert(Model model) {

        model.addAttribute("userForm", new UserForm());
        return "user/insertOrUpdate";
    }

    @PostMapping("insert")
    public String insert(@Validated UserForm userForm, Errors errors) {
        if (errors.hasErrors()) {
            return "user/insertOrUpdate";
        }

        UserModel user = new UserModel();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setAddress(userForm.getAddress());
        userService.insert(user);

        return "redirect:/user";
    }

    @GetMapping("update")
    public String update(int id, Model model) {
        UserModel userModel = userService.findById(id);
        if (userModel != null) {
            UserForm user = new UserForm();
            user.setId(userModel.getId());
            user.setName(userModel.getName());
            user.setEmail(userModel.getEmail());
            user.setAddress(userModel.getAddress());
            model.addAttribute("userForm", user);
        } else {
            throw new RuntimeException("Can not found user");
        }

        return "user/insertOrUpdate";
    }

    @PostMapping("update")
    public String update(@Validated({UserUpdatedGroup.class, Default.class}) UserForm userForm, Errors errors) {
        if (errors.hasErrors()) {
            return "user/insertOrUpdate";
        }

        UserModel user = new UserModel();
        user.setId(userForm.getId());
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setAddress(userForm.getAddress());
        userService.update(user);

        return "redirect:/user";
    }
}
