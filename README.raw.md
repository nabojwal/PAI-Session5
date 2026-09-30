# Order API

`OrderApi` provides methods for listing, finding, creating, and cancelling orders. `OrderService` stores orders in memory and assigns each created order an ID. A new order has status `NEW`.

The order amount is denominated in INR.

## Operations

- `listOrders()` returns all orders as `OrderDto` values.
- `getOrder(String id)` returns an `Optional<OrderDto>` that is empty when no order has that ID.
- `createOrder(String customerId, BigDecimal amount)` creates an order and returns its DTO.
- `cancelOrder(String id)` returns `true` when it cancels an order and `false` when the order is missing or already cancelled.
