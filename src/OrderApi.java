import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class OrderApi {
    private final OrderService orderService;

    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<OrderDto> listOrders() {
        return orderService.listAll().stream()
                .map(OrderDto::from)
                .toList();
    }

    public Optional<OrderDto> getOrder(String id) {
        return orderService.findById(id).map(OrderDto::from);
    }

    /**
     * Create an order for a customer.
     *
    * @param customerId the customer identifier ({@code String}); TODO: specify null and format constraints
    * @param amount the order amount ({@code BigDecimal}); TODO: specify currency, null and range constraints
     * @return the created order as a DTO, with its assigned ID and {@code NEW} status
     * @throws NullPointerException if this API was constructed with a null order service
     */
    public OrderDto createOrder(String customerId, BigDecimal amount) {
        return OrderDto.from(orderService.create(customerId, amount));
    }

    public boolean cancelOrder(String id) {
        return orderService.cancel(id);
    }
}
