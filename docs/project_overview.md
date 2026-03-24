# Project Overview

## Objective
Recreate the iOS Habit Tracker product on Android with strict feature, behavior, and UI parity while using a maintainable Android-native implementation stack.

## Confirmed Repository Baseline
As of 2026-03-23, the repository contains the Android project foundation but not sourced feature implementations:

| Area | Confirmed State |
| --- | --- |
| Modules | `:app`, `:core:*`, `:data:*`, `:domain:*`, `:feature:*`, and `build-logic` |
| Entry point | `MainActivity` hosting a compile-safe placeholder `NavHost` |
| UI stack present | Jetpack Compose with shared `core:designsystem` and `core:ui` foundations |
| Dependency management | Version catalog plus included `build-logic` convention plugins |
| Android config | `compileSdk 36.1`, `targetSdk 36`, `minSdk 28`, Java/Kotlin bytecode target 11, AGP `8.13.2`, Gradle `8.13` |
| Architecture | Modular foundation present; production MVI feature implementation not started |
| Networking | `core:network` foundation module integrated, no source-backed APIs implemented |
| Persistence | `core:database` foundation module integrated, no source-backed schema implemented |
| DI | Hilt application bootstrap integrated |
| Navigation | Navigation Compose baseline integrated with placeholder feature destinations |
| Parity references | Not present in repo |

## Target End State
The target Android stack is fixed unless product docs later require additions:
- Kotlin
- Jetpack Compose
- Hilt
- Room
- Retrofit + OkHttp + Kotlinx Serialization
- Navigation Compose
- Coil
- Media3 only if Learn video playback is confirmed by source material
- Clean Architecture
- Modularization
- MVI

## Migration Principles
- Product parity over platform reinterpretation.
- Source-backed implementation only.
- Deterministic state handling.
- Strict architecture boundaries.
- Incremental delivery that preserves buildability.
- No speculative infrastructure.

## Delivery Phases
### Phase 0: Operating Framework
- Create long-lived project documentation.
- Freeze feature work until source inventory exists.
- Capture confirmed baseline and gaps.

### Phase 1: Source Intake
- Import or reference the iOS app screens, flows, assets, copy, and API contracts.
- Populate the screen inventory, parity checklists, and design catalogs.
- Resolve unknowns before feature implementation.

### Phase 2: Android Foundation
- Introduce modularization incrementally.
- Add Hilt, Navigation Compose, Room, Retrofit, OkHttp, Kotlinx Serialization, and Coil.
- Establish production MVI scaffolding that will survive feature delivery.

### Phase 3: Feature Migration
- Migrate one sourced feature slice at a time.
- Verify UI parity, business parity, and regression coverage before moving on.

### Phase 4: Hardening
- Performance validation.
- Error-state validation.
- Accessibility and localization review.
- Release readiness and regression stabilization.

## Source Of Truth Documents
- [architecture_rules.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/architecture_rules.md)
- [state_management_rules.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/state_management_rules.md)
- [design_system_spec.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/design_system_spec.md)
- [ios_screen_inventory.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/ios_screen_inventory.md)
- [feature_parity_checklist.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/feature_parity_checklist.md)
- [ui_parity_checklist.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/ui_parity_checklist.md)
- [known_assumptions_and_gaps.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/known_assumptions_and_gaps.md)
- [current_session_handoff.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/current_session_handoff.md)

## Current Status
| Track | Status | Notes |
| --- | --- | --- |
| Project framework | In progress | Documentation scaffold created in this session |
| iOS screen inventory | Blocked | No iOS reference set in repo |
| Feature parity mapping | Blocked | Depends on iOS source intake |
| Design token parity | Blocked | No approved color, typography, spacing, or icon export |
| API parity | Blocked | No backend contract in repo |
| Android production architecture | In progress | Foundation modules and build logic are in place; sourced feature logic is still blocked |

## Immediate Constraint
No feature implementation should begin until the missing parity source material is inventoried and reflected in the project docs.
