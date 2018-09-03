package vn.com.khoibv.spring.jtademo.dao;

import vn.com.khoibv.spring.jtademo.entity.CouponEntity;

import java.util.List;

public interface CouponDao {

    List<CouponEntity> getByUserIds(List<Integer> userIds);


    int insert(CouponEntity coupon);
}
