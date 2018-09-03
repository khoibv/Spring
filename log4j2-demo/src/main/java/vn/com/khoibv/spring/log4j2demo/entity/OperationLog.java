package vn.com.khoibv.spring.log4j2demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationLog {
    private String logDatetime;
    private String userId;
    private String type;
    private String systemScope;
    private String ipAddress;
    private String token;
    private String threadId;
    private String functionName;
    private String eventName;
    private String startStop;
    private String endResult;

}
