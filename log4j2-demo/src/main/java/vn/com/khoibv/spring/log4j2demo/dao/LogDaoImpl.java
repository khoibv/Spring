package vn.com.khoibv.spring.log4j2demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import vn.com.khoibv.spring.log4j2demo.entity.OperationLog;

@Repository
public class LogDaoImpl implements LogDao {

    private static final String SQL_INSERT =
            "INSERT INTO operation_log (" +
            "   log_datetime," +
            "   user_id," +
            "   type," +
            "   system_scope," +
            "   ip_address," +
            "   token," +
            "   thread_id," +
            "   function_name," +
            "   event_name," +
            "   start_stop," +
            "   end_result" +
            ") values (" +
            "   :log_datetime," +
            "   :user_id," +
            "   :type, " +
            "   :system_scope," +
            "   :ip_address," +
            "   :token," +
            "   :thread_id," +
            "   :function_name," +
            "   :event_name," +
            "   :start_stop," +
            "   :end_result" +
            ")";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int insert(OperationLog newOperationLog) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("log_datetime", newOperationLog.getLogDatetime())
                .addValue("user_id", newOperationLog.getUserId())
                .addValue("type", newOperationLog.getType())
                .addValue("system_scope", newOperationLog.getSystemScope())
                .addValue("ip_address", newOperationLog.getIpAddress())
                .addValue("token", newOperationLog.getToken())
                .addValue("thread_id", newOperationLog.getThreadId())
                .addValue("function_name", newOperationLog.getFunctionName())
                .addValue("event_name", newOperationLog.getEventName())
                .addValue("start_stop", newOperationLog.getStartStop())
                .addValue("end_result", newOperationLog.getEndResult())
                ;

        return jdbcTemplate.update(SQL_INSERT, parameters);
    }
}
