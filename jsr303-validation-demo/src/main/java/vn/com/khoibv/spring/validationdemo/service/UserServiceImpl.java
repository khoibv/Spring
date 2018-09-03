package vn.com.khoibv.spring.validationdemo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import vn.com.khoibv.spring.validationdemo.model.UserModel;

@Service
public class UserServiceImpl implements UserService {

  private static List<UserModel> users = new ArrayList<>(Arrays.asList(
      new UserModel(1, "User 01", "user01@gmail.com", "Address 01"),
      new UserModel(2, "User 02", "user02@gmail.com", "Address 02"),
      new UserModel(4, "User 04", "user04@gmail.com", "Address 04"),
      new UserModel(3, "User 03", "user03@gmail.com", "Address 03"),
      new UserModel(5, "User 05", "user05@gmail.com", "Address 05")
  ));

  @Override
  public List<UserModel> getAll() {
    return users;
  }

  @Override
  public UserModel findById(int userId) {
    return users.stream()
        .filter(u -> u.getId() == userId)
        .findFirst()
        .orElse(null);
  }

  @Override
  public UserModel insert(UserModel user) {
    // Fake auto-increment ID
    int maxId = users.stream()
        .max(Comparator.comparingInt(UserModel::getId))
        .orElse(new UserModel())
        .getId();
    user.setId(maxId + 1);
    users.add(user);

    return user;
  }

  @Override
  public UserModel update(UserModel user) {
    UserModel updatedUser = findById(user.getId());
    if (updatedUser != null) {
      updatedUser.setName(user.getName());
      updatedUser.setEmail(user.getEmail());
      updatedUser.setAddress(user.getAddress());
    }

    return updatedUser;
  }

  @Override
  public int delete(int userId) {
    UserModel deletedUser = findById(userId);
    if (deletedUser != null) {
      users.remove(deletedUser);
      return 1;
    }

    return 0;
  }
}
