# Testing Strategy

## Purpose
Testing must prove feature parity, architectural correctness, and regression safety without relying on unstable manual verification alone.

## Confirmed Baseline
- The repository currently contains only the Android Studio starter tests.
- No production test strategy has been implemented in code yet.

## Test Layers
| Layer | Purpose | Typical Targets |
| --- | --- | --- |
| Unit | Fast deterministic verification of pure logic | Reducers, use cases, mappers, validators |
| Integration | Boundary verification across modules or infrastructure | Repositories, Room DAOs, Retrofit adapters, navigation assembly |
| UI | User-facing behavior verification | Compose screen rendering, interaction flows, effect handling |
| Manual parity review | Final comparison against iOS source | Visual parity, motion, copy, navigation flow, edge states |

## Minimum Test Expectations By Change Type
| Change Type | Required Tests |
| --- | --- |
| Reducer change | Reducer unit tests |
| ViewModel change | Intent-to-state and intent-to-effect tests |
| Use case change | Unit tests |
| Mapper or serialization change | Unit tests and contract-focused integration tests |
| Room schema change | Migration and DAO tests |
| Navigation change | Navigation integration or UI tests |
| UI state rendering change | Compose UI tests and manual parity verification |

## Determinism Rules
- Tests must not depend on wall clock time, locale, timezone, network availability, or random ordering unless those inputs are explicitly controlled.
- Inject time, dispatcher, and data dependencies.
- Use fixed test fixtures for parity-sensitive behavior.

## Production Data Rules
- Never add fake production behavior to make tests easy.
- Test doubles belong in tests or `:core:testing`, not production modules.
- Keep test data semantically meaningful and traceable to the behavior under test.

## Parity Verification Rules
- Each migrated screen must be checked against iOS for loading, content, empty, and error states.
- Manual parity findings must feed back into [ui_parity_checklist.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/ui_parity_checklist.md) and [regression_rules.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/regression_rules.md).
- If screenshots or recordings are used, store the reference path or review artifact in task notes when available.

## Build Validation
- Run the smallest reliable test/build command that covers the changed surface.
- Do not merge unvalidated infrastructure changes unless the validation blocker is documented explicitly.
