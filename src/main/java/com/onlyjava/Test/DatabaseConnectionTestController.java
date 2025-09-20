package com.onlyjava.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DatabaseConnectionTestController {

    @Autowired
    private DatabaseConnectionTestService databaseConnectionTestService;

    /**
     * 仅测试数据库连接的端点
     * @return 连接测试结果
     */
    @GetMapping("/test-db-connection")
    public Map<String, Object> testDatabaseConnection() {
        return databaseConnectionTestService.testConnectionOnly();
    }
}
