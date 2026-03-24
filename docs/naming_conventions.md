# Naming Conventions

## Purpose
Naming must preserve product language from the iOS source while remaining idiomatic Kotlin. Consistent naming is required for searchability, parity reviews, and long-term maintenance.

## General Rules
- Use the product's existing feature terminology from source material whenever it exists.
- Prefer explicit names over abbreviations.
- Avoid generic names like `Manager`, `Helper`, `Utils`, or `Data`.
- Keep one concept mapped to one primary term across modules, docs, and tests.

## Module Names
- Feature modules use `:feature:<feature-name>`.
- Data modules use `:data:<feature-name>`.
- Shared modules use `:core:<area>`.
- Use lowercase kebab-case for Gradle module path segments.

## Package Names
- Base package stays under `com.threemdroid.habittracker`.
- Package names are lowercase and dot-separated.
- Mirror ownership boundaries in packages, such as `presentation`, `domain`, `data`, `mapper`, `navigation`, and `di`.

## Kotlin Type Names
- Classes, interfaces, objects, and enums use PascalCase.
- Functions and properties use camelCase.
- Constants use `UPPER_SNAKE_CASE` only when truly constant.
- Sealed hierarchy members should read clearly in logs and tests.

## MVI Names
- State: `<FeatureName>UiState`
- Intent: `<FeatureName>Intent`
- Effect: `<FeatureName>Effect`
- ViewModel: `<FeatureName>ViewModel`
- Reducer: `<FeatureName>Reducer`
- Internal result type: `<FeatureName>Result` or a scoped `Result` inside the feature contract

## Data Layer Names
- Repository interface: `<FeatureName>Repository`
- Repository implementation: `<FeatureName>RepositoryImpl`
- Remote data source: `<FeatureName>RemoteDataSource`
- Local data source: `<FeatureName>LocalDataSource`
- DTO: `<Thing>Dto`
- Room entity: `<Thing>Entity`
- Mapper: `<Thing>Mapper` only when the object has clear reusable mapping responsibility

## Use Case Names
- Name use cases as actions, such as `Load<Thing>UseCase`, `Update<Thing>UseCase`, or `Observe<Thing>UseCase`.
- Avoid umbrella use cases that hide multiple unrelated operations.

## Navigation Names
- Route constants or typed route classes must use sourced screen names.
- Do not invent route aliases that diverge from the product language.
- Deep link names and argument keys must be explicit and stable.

## Resource Names
- Drawables, colors, strings, and dimens use lowercase snake_case.
- Use semantic names rather than visual descriptions once parity tokens exist.
- Do not add hardcoded design tokens directly inside feature code.

## Test Names
- Test classes mirror the production type name plus `Test`.
- Test methods should state behavior, not implementation details.
- Builders or fixtures should be named after the domain concept they create.

## Naming Review Rule
If a name cannot be traced back to a confirmed product term, domain term, or technical responsibility, rename it before it spreads.
