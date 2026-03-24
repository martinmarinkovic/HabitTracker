# Android Migration Agent Guide

## Mission
This repository exists to recreate the iOS Habit Tracker app on Android with 1:1 product parity. The Android app is not a redesign project. If a behavior, screen, API contract, asset, copy string, or business rule is not confirmed by source material, do not invent it.

## Current Baseline
- The repository now contains the initial modular Android foundation with `:app`, `:core:*`, `:data:*`, `:domain:*`, and `:feature:*` modules plus convention plugins in `build-logic`.
- Confirmed Android stack integrated at foundation level: Kotlin, Jetpack Compose, Hilt bootstrap, Room foundation, Retrofit + OkHttp + Kotlinx Serialization foundation, Navigation Compose, Coil, modularization.
- Confirmed Android stack not yet integrated at production feature level: sourced business logic, real data implementations, production MVI feature contracts, parity-approved design tokens, and feature-complete navigation.
- No iOS reference package, approved design token export, or backend contract is stored in this repository yet.

## Non-Negotiable Rules
- Maintain strict 1:1 parity with the iOS product.
- Do not invent screens, flows, APIs, fields, states, edge cases, or business rules.
- If something is unclear, implement the safest minimal version only when required, and record the assumption in [docs/known_assumptions_and_gaps.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/known_assumptions_and_gaps.md).
- Keep the app buildable at all times.
- Prefer incremental, source-backed changes over broad refactors.
- Do not replace temporary gaps with fake architecture that will be thrown away later.

## Mandatory Read Order For Every Session
1. [docs/project_overview.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/project_overview.md)
2. [docs/known_assumptions_and_gaps.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/known_assumptions_and_gaps.md)
3. [docs/current_session_handoff.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/current_session_handoff.md)
4. Relevant task-specific docs such as architecture, state management, testing, parity, and error handling.

## Session Workflow
1. Read the current handoff and blockers before changing code or docs.
2. Confirm which requested change is backed by source material already in the repo.
3. If the source is missing, document the gap instead of guessing.
4. Keep changes inside the explicitly allowed scope of the task.
5. Validate impacted build/test commands when possible.
6. Before ending the session, update:
- [docs/progress_log.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/progress_log.md)
- [docs/known_assumptions_and_gaps.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/known_assumptions_and_gaps.md) if anything new is discovered
- [docs/current_session_handoff.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/current_session_handoff.md) with the exact next recommended step

## Allowed Changes Without Additional Product Input
- Project documentation under `/docs`
- Minimal bootstrap code required to support sourced architecture work
- Dependency and module setup needed to support confirmed architecture decisions
- Tests and tooling tied directly to already-approved behavior

## Blocked Until Source Material Exists
- Production screen UI
- Navigation flows beyond bootstrap shell wiring
- Backend contracts and endpoint fields
- Design tokens, icons, illustrations, motion specs, and copy not exported from iOS or product docs
- Feature-specific business logic

## Architecture Baseline
- Kotlin
- Jetpack Compose
- Hilt
- Room
- Retrofit + OkHttp + Kotlinx Serialization
- Navigation Compose
- Coil
- Media3 only if a sourced Learn video requirement exists
- Clean Architecture
- Modularization
- MVI with `UiState`, `Intent`, `Effect`, `ViewModel`, and reducer-style state updates

## Implementation Standards
- State is the single source of truth.
- Effects are one-time only and must not be replayed by recomposition.
- Business logic does not belong in Composables.
- DTOs, database entities, and domain models stay separate.
- Shared code belongs in explicit `core` modules, not in random utility packages.
- Every feature implementation must cite the iOS or product source that defines it.

## End-Of-Session Minimum
- The repo still builds or the validation gap is called out explicitly.
- Any new uncertainty is logged.
- The handoff names one exact next step that another session can take without re-discovery.
