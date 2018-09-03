package vn.com.khoibv.spring.jtademo.service;

import vn.com.khoibv.spring.jtademo.dto.UserCouponDto;
import vn.com.khoibv.spring.jtademo.form.UserForm;

import java.util.List;

public interface UserService {


    List<UserCouponDto> getAll();

    void insert(UserForm userForm);
}
