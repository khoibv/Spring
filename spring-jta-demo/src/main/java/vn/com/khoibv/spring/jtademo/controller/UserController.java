package vn.com.khoibv.spring.jtademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.khoibv.spring.jtademo.entity.UserEntity;
import vn.com.khoibv.spring.jtademo.form.UserForm;
import vn.com.khoibv.spring.jtademo.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public String index(Model model) {

        model.addAttribute("users", userService.getAll());

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

//        UserEntity user = new UserEntity();
//        user.setName(userForm.getUser().getName());
//        user.setEmail(userForm.getUser().getEmail());
//        user.setAddress(userForm.getUser().getAddress());
        userService.insert(userForm);

        return "redirect:/user";
    }

}
