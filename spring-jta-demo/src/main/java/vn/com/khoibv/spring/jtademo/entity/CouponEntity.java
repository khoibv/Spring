package vn.com.khoibv.spring.jtademo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponEntity {

    private int id;
    private int userId;
    private String type;
    private int value;

}
