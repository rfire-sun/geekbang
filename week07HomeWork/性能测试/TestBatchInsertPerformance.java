package com.sun.geekbang.TrainingCamp.week07;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 * 测试插入100万条数据
 */
public class TestBatchInsertPerformance {


    @Test
    public void testPrepareStatement() {
        try {
            HikariDataSource dataSource = getDataSource();
            Connection connection = dataSource.getConnection();

            connection.setAutoCommit(false);

            doDelete(connection);

            doBatchInsertUserPreparedStatement(connection);

            connection.commit();

            if (!connection.isClosed())
                connection.close();
            if (!dataSource.isClosed())
                dataSource.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStatement() {
        try {
            HikariDataSource dataSource = getDataSource();
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            doDelete(connection);

            doBatchInsertUserStatement(connection);

            connection.commit();

            if (!connection.isClosed())
                connection.close();
            if (!dataSource.isClosed())
                dataSource.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPrepareStatementBatch() {
        try {
            HikariDataSource dataSource = getDataSource();
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            doDelete(connection);

            doBatchInsertUserPreparedStatementUseBatch(connection);

            connection.commit();
            if (!connection.isClosed())
                connection.close();
            if (!dataSource.isClosed())
                dataSource.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void doBatchInsertUserStatement(Connection connection) throws SQLException {

        long start = System.currentTimeMillis();
        Statement statement = connection.createStatement();

        for (int i = 0; i < 100 * 10000; i++) {

            String sql = String.format("insert into t_user values(%s,'%s','%s','%s',%s)",
                    i,
                    RandomStringUtils.randomAlphanumeric(8),
                    getRandomGender(),
                    RandomStringUtils.randomAlphanumeric(10),
                    1);

            statement.execute(sql);
        }
        long end = System.currentTimeMillis();


        System.out.println("doBatchInsertUserStatement time: " + (end - start));

    }


    private void doBatchInsertUserPreparedStatement(Connection connection) throws SQLException {

        long start = System.currentTimeMillis();
        PreparedStatement pStat = connection.prepareStatement("insert into t_user values(?,?,?,?,?)");

        for (int i = 0; i < 100 * 10000; i++) {

            pStat.setInt(1, i);
            pStat.setString(2, RandomStringUtils.randomAlphanumeric(8));
            pStat.setString(3, getRandomGender());
            pStat.setString(4, RandomStringUtils.randomAlphanumeric(10));
            pStat.setInt(5, 1);

            pStat.execute();
        }
        long end = System.currentTimeMillis();

        System.out.println("doBatchInsertUserPreparedStatement time: " + (end - start));
    }


    private void doBatchInsertUserPreparedStatementUseBatch(Connection connection) throws SQLException {

        long start = System.currentTimeMillis();
        PreparedStatement pStat = connection.prepareStatement("insert into t_user values(?,?,?,?,?)");

        for (int i = 0; i < 100 * 10000; i++) {

            pStat.setInt(1, i);
            pStat.setString(2, RandomStringUtils.randomAlphanumeric(8));
            pStat.setString(3, getRandomGender());
            pStat.setString(4, RandomStringUtils.randomAlphanumeric(10));
            pStat.setInt(5, 1);
            pStat.addBatch();
        }

        pStat.executeBatch();

        long end = System.currentTimeMillis();

        System.out.println("doBatchInsertUserPreparedStatementUseBatch time: " + (end - start));
    }


    private void doDelete(Connection connection) throws SQLException {
        PreparedStatement pStat = connection.prepareStatement("delete from t_user");
        pStat.execute();
    }

    private String getRandomGender() {
        Random random = new Random();
        // 空间换时间  写代码时间
        return random.nextInt(100) % 2 == 0 ? "男" : "女";
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
