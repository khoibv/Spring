package vn.com.khoibv.spring.jtademo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.khoibv.spring.jtademo.entity.CouponEntity;
import vn.com.khoibv.spring.jtademo.entity.UserEntity;

@Data
@AllArgsConstructor
public class UserForm {

    private UserEntity user;
    private CouponEntity coupon;

    public UserForm() {
        this.user = new UserEntity();
        this.coupon = new CouponEntity();
    }

}
