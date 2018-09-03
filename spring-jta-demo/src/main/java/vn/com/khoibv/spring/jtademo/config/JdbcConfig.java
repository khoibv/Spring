package vn.com.khoibv.spring.jtademo.config;

import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import java.util.Properties;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class JdbcConfig {

    /**
     * Master DB
     */
//    @Value("${jdbc.master.driverClassName}")
//    private String driverClassNameMaster;

    @Value("${jdbc.master.url}")
    private String urlMaster;

    @Value("${jdbc.master.user}")
    private String usernameMaster;

    @Value("${jdbc.master.password}")
    private String passwordMaster;

    @Value("${jdbc.master.max-pool-size}")
    private int transactionTimeoutMaster;

    /**
     * Slave DB
     */
//    @Value("${jdbc.slave.driverClassName}")
//    private String driverClassNameSlave;

//    @Value("${jdbc.slave.url}")
//    private String urlSlave;

    @Value("${jdbc.slave.serverName}")
    private String serverNameSlave;

    @Value("${jdbc.slave.port}")
    private String portSlave;

    @Value("${jdbc.slave.databaseName}")
    private String databaseNameSlave;

    @Value("${jdbc.slave.user}")
    private String usernameSlave;

    @Value("${jdbc.slave.password}")
    private String passwordSlave;

    @Value("${jdbc.slave.max-pool-size}")
    private int transactionTimeoutSlave;

    /**
     * Transaction timeout in seconds
     */
    @Value("${jdbc.transaction.timeout}")
    private int transactionTimeout;

    /**
     * Create transaction manager
     *
     * @return
     * @throws SystemException
     */
//    @Bean(initMethod = "init", destroyMethod = "close")
    public TransactionManager getTransactionManager() throws SystemException {
        UserTransactionManager tm = new UserTransactionManager();
        tm.setTransactionTimeout(transactionTimeout);

        return tm;
    }


    @Bean("masterDataSource")
    public AtomikosDataSourceBean getMasterDataSource() {
        AtomikosDataSourceBean masterDataSource = new AtomikosDataSourceBean();
        masterDataSource.setXaDataSourceClassName(MysqlXADataSource.class.getCanonicalName());
        masterDataSource.setUniqueResourceName("master");
        masterDataSource.setMaxPoolSize(transactionTimeoutMaster);

        Properties masterProperties = new Properties();
//        masterProperties.setProperty("driverClassName", driverClassNameMaster);
        masterProperties.setProperty("url", urlMaster);
        masterProperties.setProperty("user", usernameMaster);
        masterProperties.setProperty("password", passwordMaster);
        masterDataSource.setXaProperties(masterProperties);

        return masterDataSource;
    }

    @Bean("slaveDataSource")
    public AtomikosDataSourceBean getSlaveDataSource() {
        AtomikosDataSourceBean masterDataSource = new AtomikosDataSourceBean();
        masterDataSource.setXaDataSourceClassName(SQLServerXADataSource.class.getCanonicalName());
        masterDataSource.setUniqueResourceName("slave");
        masterDataSource.setMaxPoolSize(transactionTimeoutSlave);

        Properties masterProperties = new Properties();
//        masterProperties.setProperty("driverClassName", driverClassNameSlave);
        masterProperties.setProperty("serverName", serverNameSlave);
        masterProperties.setProperty("portNumber", portSlave);
        masterProperties.setProperty("databaseName", databaseNameSlave);
        masterProperties.setProperty("user", usernameSlave);
        masterProperties.setProperty("password", passwordSlave);
        masterDataSource.setXaProperties(masterProperties);

        return masterDataSource;
    }

    @Bean("masterJdbcTemplate")
    public NamedParameterJdbcTemplate getMasterJdbcTemplate(){
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getMasterDataSource());

        return jdbcTemplate;
    }

    @Bean("slaveJdbcTemplate")
    public NamedParameterJdbcTemplate getSlaveJdbcTemplate(){
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getSlaveDataSource());

        return jdbcTemplate;
    }
}
