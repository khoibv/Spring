package vn.com.khoibv.spring.jtademo.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import vn.com.khoibv.spring.jtademo.entity.UserEntity;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Resource(name = "masterJdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;



    @Override
    public List<UserEntity> getAll() {

        List<UserEntity> users = jdbcTemplate.query("SELECT id, username, email, address FROM user", (rs, rowNum) -> new UserEntity(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("address")
        ));

        return users;
    }

    @Override
    public int insert(UserEntity user) {
        KeyHolder key = new GeneratedKeyHolder();
        String query = "INSERT INTO user(" +
                "   username, " +
                "   email, " +
                "   address" +
                ") VALUES (" +
                "   :name," +
                "   :email," +
                "   :address" +
                ")";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", user.getName());
        parameters.addValue("email", user.getEmail());
        parameters.addValue("address", user.getAddress());

        // Insert new user and return new generated ID in `key` parameter
        jdbcTemplate.update(query, parameters, key);

        return key.getKey().intValue();
    }
}
