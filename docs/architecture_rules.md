# Architecture Rules

## Purpose
These rules define the Android architecture that future sessions must build toward. They are intentionally strict so the project scales without needing a second rewrite.

## Architectural Principles
- Keep UI, domain logic, and data concerns separate.
- Make module dependencies directional and acyclic.
- Keep state deterministic and observable.
- Prefer feature-local ownership over global shared state.
- Add infrastructure only when it serves a sourced requirement.

## Planned Module Taxonomy
The project should move toward the following module types incrementally. Do not create all of them up front unless a task requires them.

| Module Type | Responsibility |
| --- | --- |
| `:app` | Application shell, top-level DI wiring, navigation graph assembly, startup configuration |
| `:core:common` | Shared primitives, dispatchers, result wrappers, time providers, non-UI helpers |
| `:core:designsystem` | Approved color, typography, spacing, shapes, icons, reusable parity UI primitives |
| `:core:ui` | Shared Android UI utilities that are not design tokens, such as window insets helpers or common containers |
| `:core:navigation` | Shared route contracts or navigation helpers used by multiple features |
| `:core:network` | Retrofit, OkHttp, serialization, interceptors, and transport-level concerns |
| `:core:database` | Room database, migrations, DAOs, and local persistence infrastructure |
| `:core:testing` | Test helpers, fakes for tests only, common rules, and builders |
| `:feature:<name>` | Presentation layer and feature-local domain contracts for a sourced feature |
| `:data:<name>` | Data implementations for a sourced feature when the feature has dedicated remote or local data sources |

## Module Creation Rules
- Add a new module only when the next sourced feature or shared concern justifies it.
- Do not create empty placeholder feature modules.
- Do not create generic `common` dumping grounds inside feature modules.
- If a piece of code is used by only one feature, keep it in that feature.
- Shared code moves into a `core` module only after reuse is real or clearly imminent.

## Dependency Rules
- `:app` may depend on all feature and core modules needed for assembly.
- `:feature:<name>` may depend on core modules and feature-local contracts only.
- `:feature:<name>` must not depend on another feature's implementation details.
- `:data:<name>` may depend on `:core:network`, `:core:database`, `:core:common`, and the contracts it implements.
- Core modules must not depend on feature modules.
- No circular dependencies, including through DI bindings or navigation helpers.

## Layering Inside A Feature Module
Each feature module should be internally organized so ownership remains obvious:

| Package Area | Responsibility |
| --- | --- |
| `presentation` | Compose UI, ViewModel, `UiState`, `Intent`, `Effect`, reducers |
| `domain` | Use cases, domain models, repository interfaces, feature rules |
| `data` | Optional only if the feature is not split into a `:data:<name>` module; includes repositories, DTO mappers, local/remote data sources |
| `mapper` | Explicit transformations between DTO, entity, and domain layers |

## Dependency Injection Rules
- Hilt is the DI mechanism for production code.
- Bind interfaces at the closest reasonable boundary.
- ViewModels belong to feature modules.
- `:app` owns application-level DI startup.
- Avoid service locators, static singletons, and direct object construction in Composables.

## Navigation Rules
- Navigation Compose is the navigation framework.
- `:app` owns the assembled nav graph.
- Features expose typed entry points or route definitions, not direct knowledge of the whole graph.
- Navigation arguments must map directly to sourced requirements.
- Do not add routes for unsourced screens.

## Data Rules
- DTOs model transport only.
- Room entities model storage only.
- Domain models model business meaning only.
- Mapping between layers must be explicit and testable.
- Repositories expose domain-friendly APIs and hide storage or transport specifics.
- Room migrations must be deterministic and versioned.

## Concurrency Rules
- Use Kotlin coroutines.
- Inject dispatchers instead of hardcoding them.
- No `GlobalScope`.
- Long-running work must respect lifecycle or explicit background execution requirements backed by source material.

## UI Boundary Rules
- Composables render state and emit intents.
- Business rules stay in use cases, reducers, or ViewModels.
- UI state should not be assembled from multiple uncontrolled sources.
- Avoid hidden mutable state in singletons or remember blocks.

## Change Control
Any architecture change must answer all of the following before implementation:
1. Which sourced requirement does this change support?
2. Why is the chosen module boundary better than a simpler alternative?
3. Does the change introduce shared code that is actually reused?
4. Can the new structure be adopted without breaking buildability?
