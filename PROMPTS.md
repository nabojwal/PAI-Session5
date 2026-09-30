# Session 5A: AI-generated tests
# Part A - Baseline Coverage

- Line coverage: 39.8% (33 / 83 lines covered)
- Branch coverage: 31% (15 / 48 branches covered)

Methods with less than 50% branch coverage:

- `isEligibleForReturn(BigDecimal, int)`: 0% (0 / 10 branches covered)
- `computeVAT(BigDecimal, int)`: 42% (6 / 14 branches covered)
- `roundToPaise(BigDecimal)`: 0% (0 / 2 branches covered)

# Part B
## Generated Tests

Added contract-focused JUnit 5 tests for both methods tied at 0% branch coverage in `TaxCalculatorTest.java`. The tests cover null input, age and income thresholds, and rounding behavior; empty input does not apply to these `BigDecimal`/primitive parameters.

All 14 tests pass. JaCoCo reports 100% branch coverage for both `isEligibleForReturn` and `roundToPaise`.

## Updated TaxCalculator Coverage

JaCoCo class report after adding the tests:

- Instruction coverage: 69% (159 / 229 instructions covered)
- Line coverage: 75.6% (31 / 41 lines covered)
- Branch coverage: 61% (27 / 44 branches covered)
- Method coverage: 100% (6 / 6 methods covered)

`isEligibleForReturn` and `roundToPaise` each have 100% branch coverage. The constructor has no branches.

# Part C

- Assertions use fixed expected outcomes rather than recomputing formulas from the implementation. `roundToPaise_decimalAmount_returnsTwoDecimalPlaces` uses scale-sensitive `BigDecimal` equality to verify that `12.345` rounds to exactly `12.35`; `compareTo` would ignore scale.

# Part D - Mutation Testing

- Chosen method: `isEligibleForReturn(BigDecimal, int)`
- Mutation score: 10 / 11 mutants killed (91%)
- Overall mutation score: 27 / 42 mutants killed (64%)

The mutant at line 70 (`ageYears < 0` -> `ageYears <= 0`) is now killed by `isEligibleForReturn_zeroAgeAboveIncomeThreshold_returnsTrue`, which checks eligibility at age 0.

Surviving mutants:

- `ConditionalsBoundaryMutator` changes `ageYears >= 60` to `ageYears > 60`; the tests do not cover age 60 with income greater than 250,000 and less than or equal to 300,000.

# Part E - Reflection

- Line coverage increased from the baseline 39.8% (33 / 83) to 69% (87 / 126).
- Branch coverage increased from the baseline 31% (15 / 48) to 56% (27 / 48).
- Overall mutation score increased from 26 / 42 (62%) before the targeted test to 27 / 42 (64%) after it.
- For `isEligibleForReturn`, mutation score increased from 9 / 11 (82%) to 10 / 11 (91%).

Mutation-testing feedback felt more useful for choosing the next test because it exposed a specific surviving boundary change, while coverage showed which code paths had been exercised.


# Session 5B: Auto-documentation

## OrderApi

- Created `src/OrderApi.java` as a plain Java adapter for listing, finding, creating, and cancelling orders; returned orders are mapped to `OrderDto`.
- Added JavaDoc to `createOrder`; currency, nullability, and range constraints remain TODO because the code does not specify them.
- Removed the unsupported INR currency assumption; no currency is defined by the order code.
- No fake Spring annotation stubs were found in the workspace, so the class currently has no endpoint annotations.
- `make test` passes with 14 tests.

## Part D - Reflection

- The first JavaDoc self-check caught 0 inconsistencies; a later review caught 1 unsupported assumption: that `amount` was in INR.
- `README.raw.md` preserves 1 invented feature from the documentation draft: the unsupported INR currency assumption. The reviewed `README.md` and JavaDoc leave currency unspecified.
- OpenAPI needed the most hand editing because `OrderApi` has no Spring route annotations and does not define HTTP status mappings or error contracts, so routes and response behavior had to be marked with TODOs.
