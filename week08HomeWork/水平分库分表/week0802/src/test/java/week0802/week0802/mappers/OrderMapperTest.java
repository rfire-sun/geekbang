package week0802.week0802.mappers;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import week0802.week0802.models.Order;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@MapperScan("week0802.week0802.mappers")
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 通过不同的查询条件的传入，可以体会到分库分表是需要设计的
     * 一个设计不好，查询难搞
     */
    @Test
    @Transactional
    public void test() throws SQLException {

        orderMapper.insertOne(new Order(11L, 11L));
        orderMapper.insertOne(new Order(22L, 22L));

    }
}
