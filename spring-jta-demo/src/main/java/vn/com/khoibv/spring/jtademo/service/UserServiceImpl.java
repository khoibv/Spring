package vn.com.khoibv.spring.jtademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import vn.com.khoibv.spring.jtademo.dao.CouponDao;
import vn.com.khoibv.spring.jtademo.dao.UserDao;
import vn.com.khoibv.spring.jtademo.dto.UserCouponDto;
import vn.com.khoibv.spring.jtademo.entity.CouponEntity;
import vn.com.khoibv.spring.jtademo.entity.UserEntity;
import vn.com.khoibv.spring.jtademo.form.UserForm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CouponDao couponDao;


    @Override
    public List<UserCouponDto> getAll() {
        List<UserEntity> users = userDao.getAll();
        List<CouponEntity> coupons = null;

        List<Integer> userIds = users.stream().map(UserEntity::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(userIds)) {
            coupons = couponDao.getByUserIds(userIds);
        }
        // Map user and coupon
        UserCouponDto uc = null;
        List<UserCouponDto> userCouponDtos = new ArrayList<>();
        for (UserEntity u : users) {
            // Add user information
            uc = new UserCouponDto();
            uc.setUserId(u.getId());
            uc.setUserName(u.getName());
            uc.setUserEmail(u.getEmail());
            uc.setUserAddress(u.getAddress());

            if (!CollectionUtils.isEmpty(coupons)) {
                List<CouponEntity> belongedCoupons = coupons.stream().filter(c -> c.getUserId() == u.getId()).collect(Collectors.toList());
                for (int i = 0; i < belongedCoupons.size(); i++) {
                    if (i > 0) {
                        // In the case a user have more coupons, create new row
                        uc = new UserCouponDto();
                        uc.setUserId(u.getId());
                        uc.setUserName(u.getName());
                        uc.setUserEmail(u.getEmail());
                        uc.setUserAddress(u.getAddress());
                    }

                    // Add coupon information
                    uc.setCouponId(belongedCoupons.get(i).getId());
                    uc.setCouponType(belongedCoupons.get(i).getType());
                    uc.setCouponValue(belongedCoupons.get(i).getValue());
                }
            }

            userCouponDtos.add(uc);
        }

        return userCouponDtos;
    }

    @Override
    @Transactional
    public void insert(UserForm userForm) {

        UserEntity user = new UserEntity();
        user.setName(userForm.getUser().getName());
        user.setEmail(userForm.getUser().getEmail());
        user.setAddress(userForm.getUser().getAddress());

        int newUserId = userDao.insert(user);

        CouponEntity coupon = new CouponEntity();
        coupon.setUserId(newUserId);
        coupon.setType(userForm.getCoupon().getType());
        coupon.setValue(userForm.getCoupon().getValue());

        int newCouponId = couponDao.insert(coupon);

        System.out.println(String.format("Insert completed. UserID = %d, couponID=%d", newUserId, newCouponId));
    }
}
