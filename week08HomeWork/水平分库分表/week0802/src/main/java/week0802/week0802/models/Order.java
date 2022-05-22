package week0802.week0802.models;

import lombok.Data;

/**
 * 订单
 *
 * @author suncheng
 */
@Data
public class Order {

    private Long order_id;
    private Long user_id;

    public Order(long userId, Long order_id) {
        this.order_id = order_id;
        this.user_id = userId;
    }
}
