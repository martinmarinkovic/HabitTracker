# State Management Rules

## Standard
Every feature must use MVI with these mandatory parts:
- `UiState`
- `Intent`
- `Effect`
- `ViewModel`
- Reducer-style state updates

## Core Rules
- `UiState` is the single source of truth for everything the screen can render.
- `Intent` represents user actions or external inputs handled by the feature.
- `Effect` represents one-time events such as navigation, toast/snackbar triggers, and external launches.
- The reducer is pure and contains no side effects.
- The ViewModel orchestrates intent handling, use cases, and effect emission.

## UiState Rules
- Use immutable data classes.
- Include loading, content, and error-related render state explicitly instead of inferring it from nullable fields.
- Keep field names domain-specific and parity-aligned with the source product wording.
- Compose local state is allowed only for purely ephemeral rendering concerns that are not business state and do not need restoration or testing.
- Persisted or restorable state should flow through `UiState` and, when required, `SavedStateHandle`.

## Intent Rules
- Model what happened, not what the UI should directly mutate.
- Separate initialization intents from user interaction intents when needed.
- Do not pass Android framework types through intents unless unavoidable.
- Keep intent payloads minimal and serializable when possible.

## Effect Rules
- Effects are one-time only.
- Effects must be emitted through a channel or equivalent one-shot stream, not stored permanently in `UiState`.
- Effects must never be replayed because the Composable recomposed.
- If a user-visible event must survive process death, it is not an effect and belongs in persisted state or a sourced recovery flow.

## Reducer Rules
- The reducer receives current state plus an internal result or action and returns new state.
- Reducers must be deterministic.
- No repository calls, database writes, logging side effects, or navigation inside a reducer.
- Prefer small explicit result types over ad hoc state mutation.

## ViewModel Rules
- ViewModels accept intents, invoke use cases, reduce new state, and emit effects.
- ViewModels may coordinate multiple use cases, but domain rules remain outside the UI layer.
- Error mapping into user-facing state or effect must happen consistently.
- ViewModels should expose a stable state flow and a separate effect stream.

## Recommended Feature Contract Shape
Use feature-specific names following this pattern:

| Contract | Name Pattern |
| --- | --- |
| State | `<FeatureName>UiState` |
| Intent | `<FeatureName>Intent` |
| Effect | `<FeatureName>Effect` |
| ViewModel | `<FeatureName>ViewModel` |
| Reducer | `<FeatureName>Reducer` or reducer function/object inside the feature |

## Compose Integration Rules
- Composables collect state and render it.
- Composables send intents upward.
- Composables collect effects in a lifecycle-aware way and translate them into Android actions.
- Do not call repositories, DAOs, or Retrofit services from Composables.

## Testing Expectations
- Reducers require focused unit tests.
- ViewModels require intent-to-state and intent-to-effect tests.
- Business use cases require domain tests.
- State restoration behavior should be tested for screens that depend on it.

## Anti-Patterns
- Treating `UiState` as a bag of mutable variables.
- Using `SharedFlow` or `StateFlow` incorrectly for replayable one-time events.
- Placing validation, filtering, or persistence logic directly in Composables.
- Letting multiple asynchronous sources mutate screen state independently without a reducer path.
