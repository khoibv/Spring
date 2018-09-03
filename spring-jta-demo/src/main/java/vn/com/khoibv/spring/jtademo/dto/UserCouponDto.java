package vn.com.khoibv.spring.jtademo.dto;

import lombok.Data;

@Data
public class UserCouponDto {

    private int userId;
    private String userName;
    private String userEmail;
    private String userAddress;
    private int couponId;
    private String couponType;
    private int couponValue;

}
