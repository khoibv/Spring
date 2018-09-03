package vn.com.khoibv.spring.jtademo.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import vn.com.khoibv.spring.jtademo.entity.CouponEntity;
import vn.com.khoibv.spring.jtademo.entity.UserEntity;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CouponDaoImpl implements CouponDao {

    @Resource(name = "slaveJdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;


    public List<CouponEntity> getByUserIds(List<Integer> userIds) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", userIds);
        List<CouponEntity> coupons = jdbcTemplate.query("SELECT id, userId, type, value FROM coupon WHERE userId IN (:ids)", parameters, (rs, row) ->
                new CouponEntity(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getString("type"),
                        rs.getInt("value")
                )

        );

        return coupons;
    }

    @Override
    public int insert(CouponEntity coupon) {
        KeyHolder key = new GeneratedKeyHolder();
        String query = "INSERT INTO coupon(" +
                "   userid, " +
                "   [type], " +
                "   [value]" +
                ") VALUES (" +
                "   :userid," +
                "   :type," +
                "   :value" +
                ")";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("userid", coupon.getUserId());
        parameters.addValue("type", coupon.getType());
        parameters.addValue("value", coupon.getValue());

        // Insert new user and return new generated ID in `key` parameter
        jdbcTemplate.update(query, parameters, key);

        return key.getKey().intValue();
    }
}
