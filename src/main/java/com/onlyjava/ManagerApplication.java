package com.onlyjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class ManagerApplication {
   //cnm狗哥
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://26.72.189.70:3306/manager", "root", "2005")) {
            System.err.println("√ 数据库连接成功");
        } catch (SQLException e) {
            System.err.println("x 数据库连接失败");
            return;
        }
        SpringApplication.run(ManagerApplication.class, args);
    }

}
