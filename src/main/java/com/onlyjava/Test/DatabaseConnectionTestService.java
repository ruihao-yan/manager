package com.onlyjava.Test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class DatabaseConnectionTestService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 仅测试MyBatis数据库连接，不执行任何SQL语句
     * @return 连接测试结果
     */
    public Map<String, Object> testConnectionOnly() {
        Map<String, Object> result = new HashMap<>();
        SqlSession sqlSession = null;

        try {
            // 通过SqlSessionFactory创建SqlSession
            sqlSession = sqlSessionFactory.openSession();

            // 获取底层数据库连接
            Connection connection = sqlSession.getConnection();

            // 检查连接是否有效
            if (connection != null) {
                result.put("connected", true);
                result.put("message", "MyBatis数据库连接成功");
                result.put("closed", connection.isClosed());
                result.put("autoCommit", connection.getAutoCommit());
                result.put("databaseProductName", connection.getMetaData().getDatabaseProductName());
                result.put("databaseProductVersion", connection.getMetaData().getDatabaseProductVersion());
                result.put("driverName", connection.getMetaData().getDriverName());
                result.put("driverVersion", connection.getMetaData().getDriverVersion());
            } else {
                result.put("connected", false);
                result.put("message", "无法获取数据库连接");
            }
        } catch (SQLException e) {
            result.put("connected", false);
            result.put("message", "数据库连接异常: " + e.getMessage());
            result.put("error", e.getClass().getSimpleName());
        } catch (Exception e) {
            result.put("connected", false);
            result.put("message", "连接测试失败: " + e.getMessage());
            result.put("error", e.getClass().getSimpleName());
        } finally {
            // 关闭SqlSession
            if (sqlSession != null) {
                try {
                    sqlSession.close();
                } catch (Exception e) {
                    // 忽略关闭异常
                }
            }
        }

        return result;
    }
}
