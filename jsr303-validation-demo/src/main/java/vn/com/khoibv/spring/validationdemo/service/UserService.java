package vn.com.khoibv.spring.validationdemo.service;

import java.util.List;
import vn.com.khoibv.spring.validationdemo.model.UserModel;

public interface UserService {

  /**
   * Get all users
   *
   * @return
   */
  List<UserModel> getAll();

  /**
   * Find user by user ID
   * @param userId
   * @return
   */
  UserModel findById(int userId);

  /**
   * Insert new user
   *
   * @param user
   * @return
   */
  UserModel insert(UserModel user);

  /**
   * Update an existing user
   *
   * @param user
   * @return
   */
  UserModel update(UserModel user);

  /**
   * Delete user by user ID
   *
   * @param userId
   * @return
   */
  int delete(int userId);

}
