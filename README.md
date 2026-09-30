# Order API

`OrderApi` is a plain Java adapter over `OrderService`. It is not currently a Spring controller, so the methods below do not register HTTP routes.

## Operations

- `listOrders()` returns all stored orders as `OrderDto` values. Ordering is unspecified.
- `getOrder(String id)` returns an `Optional<OrderDto>`; it is empty when no order has the ID.
- `createOrder(String customerId, BigDecimal amount)` creates an order and returns its DTO. The service assigns an ID and initializes its status to `NEW`.
- `cancelOrder(String id)` returns `true` when an order is cancelled, and `false` when it is missing or already cancelled.

The `amount` field is represented by `BigDecimal`; its currency and validation constraints are unspecified.

## Data Transfer Object

`OrderDto` contains `id`, `customerId`, `amount`, and `status` fields. The DTO does not include the order's creation timestamp.

## OpenAPI

See [openapi.yaml](openapi.yaml) for a draft contract. Its paths and HTTP status mappings are conventional assumptions marked with TODOs because the Java class has no Spring mapping annotations.
# M5 -- AI-assisted testing and documentation

Starter for the tax-calculator + order-API exercises.

Run:

    make deps && make test
    make coverage       # JaCoCo HTML at coverage/index.html
    make mutation       # PIT HTML at build/reports/pitest/index.html

Session 5A: use an LLM to raise `TaxCalculator` line/branch coverage
above the ~45%/~30% baseline the three seed tests give you.
Session 5B: add JavaDoc, a README, and an OpenAPI spec for
`OrderApi` (fake Spring annotations already in place so it compiles
without Spring on the classpath).

The `mutation` target uses PIT 1.17.4 with the JUnit 5 plugin; both
are auto-downloaded into `libs/` on first use.  Baseline mutation
score with the seed test is around 36% (15 of 42 mutants killed) --
Part D of the handout asks students to kill at least one surviving
mutant.
