package vn.com.khoibv.spring.jtademo.dao;

import vn.com.khoibv.spring.jtademo.entity.UserEntity;

import java.util.List;

public interface UserDao {

    List<UserEntity> getAll();

    /**
     * Insert new user
     *
     * @param user
     * @return Generated ID
     */
    int insert(UserEntity user);
}
