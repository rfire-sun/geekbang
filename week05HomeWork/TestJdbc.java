package com.sun.geekbang.TrainingCamp.week05;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import java.sql.*;
import java.text.MessageFormat;

/**
 * 直接使用数据库连接池了
 * 由于比较久远，其实用过挺多次了
 * 真的些还挺繁琐，暂时先这么用着
 */
public class TestJdbc {


    @Test
    public void test() throws SQLException {
        add_Demo();
//        delete_Demo();
//        update_demo();
//        simpleQuery("select * from table1");

//        addTable1("231","niasidfj",9);
    }

    // todo 使用springBoot，配合这个Test框架（有实例代码，好像再shardingsphere中）  接口化
    /* 简单的新增 */
    public static int add_Demo() throws SQLException {
        int result = -1;

        HikariDataSource dataSource = null;
        Connection connection = null;

        try {

            dataSource = getDataSource();
            connection = dataSource.getConnection();

            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();

            statement.execute("insert into table1 values('9','wudi23',8)", Statement.RETURN_GENERATED_KEYS);

            connection.commit();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet != null) {
                if (resultSet.next()) {
                    result = resultSet.getInt(1);
                }
            }

        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
        } finally {
            if (connection != null && !connection.isClosed())
                connection.close();
            if (dataSource != null && !dataSource.isClosed())
                dataSource.close();
        }
        return result;
    }

    /**
     * 使用prepareStatement 往table1插入一条数据
     */
    public static int addTable1(String id, String name, int age) {
        int result = -1;
        try {
            HikariDataSource dataSource = getDataSource();
            Connection connection = dataSource.getConnection();

            PreparedStatement pStat = connection.prepareStatement("insert into table1 values(?,?,?)");
            pStat.setString(1, id);
            pStat.setString(2, name);
            pStat.setInt(3, age);

            pStat.execute();

            if (!connection.isClosed())
                connection.close();
            if (!dataSource.isClosed())
                dataSource.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 简单的删除 */
    public static int delete_Demo() {
        int result = -1;
        try {
            HikariDataSource dataSource = getDataSource();
            Connection connection = dataSource.getConnection();

            Statement statement = connection.createStatement();

            statement.execute("delete from table1 where id = '9'", Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet != null) {
                if (resultSet.next()) {
                    result = resultSet.getInt(1);
                }
            }

            if (!connection.isClosed())
                connection.close();
            if (!dataSource.isClosed())
                dataSource.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 更新 */
    public static int update_demo() {
        int result = -1;
        try {

            HikariDataSource dataSource = getDataSource();
            Connection connection = dataSource.getConnection();

            Statement statement = connection.createStatement();
            result = statement.executeUpdate("update table1 set name = 'shasha' where id = 4");

            if (!connection.isClosed())
                connection.close();
            if (!dataSource.isClosed())
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

            if (!connection.isClosed())
                connection.close();
            if (!dataSource.isClosed())
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
