package vn.com.khoibv.spring.log4j2demo.dao;

import vn.com.khoibv.spring.log4j2demo.entity.OperationLog;

public interface LogDao {

    /**
     * Insert new operation log record
     *
     * @param newOperationLog
     *
     * @return Number of affected records
     */
    int insert(OperationLog newOperationLog);

}
