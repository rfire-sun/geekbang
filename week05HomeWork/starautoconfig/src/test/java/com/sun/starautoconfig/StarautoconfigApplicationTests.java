package com.sun.starautoconfig;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.text.MessageFormat;

@SpringBootTest
class StarautoconfigApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){

        simpleQuery("select * from table1");
    }

    /* 简单的新增 */
    public static int selectDemo() {
        int result = -1;
        try {
            HikariDataSource dataSource = getDataSource();
            Connection connection = dataSource.getConnection();

            Statement statement = connection.createStatement();

            statement.execute("select * from table1", Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet != null) {
                if (resultSet.next()) {
                    result = resultSet.getInt(1);
                }
            }

            if (connection != null && !connection.isClosed())
                connection.close();
            if (dataSource != null && !dataSource.isClosed())
                dataSource.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 更新 */
    public static int update_dmeo() {
        int result = -1;
        try {

            HikariDataSource dataSource = getDataSource();
            Connection connection = dataSource.getConnection();

            Statement statement = connection.createStatement();
            result = statement.executeUpdate("update user set username = 'changed' where userid = 4");

            if (connection != null && !connection.isClosed())
                connection.close();
            if (dataSource != null && !dataSource.isClosed())
                dataSource.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ResultSet simpleQuery(String sql) {
        try {
            HikariDataSource dataSource = getDataSource();
            Connection connection = dataSource.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            printResultSet(resultSet);

            if (connection != null && !connection.isClosed())
                connection.close();
            if (dataSource != null && !dataSource.isClosed())
                dataSource.close();

            return resultSet;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void printResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            ResultSetMetaData md = resultSet.getMetaData();// 获取键名
            int columnCount = md.getColumnCount();// 获取行的数量

            while (resultSet.next()) {
                StringBuilder builder = new StringBuilder();

                for (int i = 1; i <= columnCount; i++) {
                    String colName = md.getColumnName(i);
                    Object val = resultSet.getObject(i);

                    builder.append(MessageFormat.format("{0}:{1}", colName, val));
                }

                print(builder.toString());
            }
        }
    }

    public static void print(Object obj) {
        System.out.println(obj);
    }

    private static HikariDataSource getDataSource() throws SQLException {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/suncheng?useUnicode=true&characterEncoding=utf8&useSSL=false");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }

}
