# Current Session Handoff

## Session Date
2026-03-24

## What Changed
- Added `HomeUiState`, `HomeIntent`, `HomeEffect`, reducer results, and supporting state models in `feature/home`.
- Added feature-local Home use cases and a `HomeViewModel` that orchestrates selected-date loading, habit actions, quick add state, mood selection, and stop/edit actions.
- Added reducer and ViewModel tests for core state transitions and one-time effects.
- Added the minimal habits repository support Home needed to observe all habit entries for a selected date.
- Reviewed the Home phase and fixed a reducer state-consistency issue so loading and load-failure transitions clear transient quick-add and mood state.
- Added direct unit tests for all Home use cases and a shared Home test-support layer so business logic assumptions are now covered independently from the ViewModel.
- Reviewed the Home use-case test phase and corrected the mood-ordering assumption docs so they match the current implementation and tests.
- Replaced the Home placeholder route with a conservative Compose Home screen implementation backed by the existing Home MVI contracts.
- Added Home UI rendering for greeting header, mood summary button, horizontal date selector, habits section, habit cards, loading/empty/error states, quick-add sheet, habit action sheet, and floating bottom navigation with a center create CTA.
- Added a public `homeViewModelFactory(...)` and minimal app-level repository assembly so `HomeViewModel` can be wired into the Home destination without introducing broader DI changes.
- Added Home previews, UI test tags, and Compose UI tests for core Home rendering and interaction paths.
- Reviewed the Home UI phase and hid the `Edit Habit` action whenever no real edit callback is wired so Home no longer exposes a no-op unresolved flow.
- Kept the Home context sheet as ephemeral Compose state by using `remember` instead of `rememberSaveable`, aligning it with the documented MVI boundary for non-restorable UI chrome.
- Logged the Home UI fallback copy/visual assumptions in `docs/known_assumptions_and_gaps.md`.
- Added an app-level Hilt module that now owns `HabitTrackerDatabase`, `HabitsRepository`, and `ActivityRepository` creation.
- Injected the Home repositories into `MainActivity`, moved `homeViewModelFactory(...)` assembly there, and removed direct Room/repository construction from `HabitTrackerApp`.
- Added `CreateHabitUiState`, `CreateHabitIntent`, `CreateHabitEffect`, reminder/form state models, a pure reducer, and a `CreateHabitViewModel` in `feature/create_habit`.
- Added feature-local Create Habit draft, validation, and save use cases so Create Habit form rules and persistence orchestration live outside the UI layer.
- Added Create Habit tests for validation behavior, reminder/save mapping and rollback behavior, reducer transitions, and ViewModel intent-to-state / intent-to-effect handling.
- Reviewed the Create Habit logic phase and added the missing Create Habit assumption rows to `docs/known_assumptions_and_gaps.md` so the durable blocker ledger now matches the current Create Habit implementation and tests.
- Replaced the Create Habit placeholder route with a conservative Compose screen backed by the existing Create Habit MVI layer.
- Added Create Habit UI rendering for the main form, goal summary card, schedule controls, reminders section, `Add Habit` CTA, modal Goal sheet, and full-screen Select Icon / Select Color picker views.
- Added Create Habit previews, UI test tags, and Compose UI tests covering core rendering plus critical goal-sheet, picker, reminder, and save interactions.
- Logged the new Create Habit UI fallback decisions in `docs/known_assumptions_and_gaps.md`, including provisional picker grouping, read-only reminder times, and manual picker dismissal behavior.
- Reviewed the Create Habit UI phase and fixed the route-level effect collector so one-time effects are now collected with lifecycle awareness.

## Key Decisions
- Home now has strict MVI contracts with reducer-driven state updates and channel-backed one-time effects.
- Home business rules are feature-local use cases built on top of `domain:habits` and `domain:activity`; no business logic was added to Composables.
- The only cross-module contract change was adding date-scoped habit-entry observation to `HabitsRepository`, because Home needs a selected-date aggregate without N-per-habit repository observers.
- Home behavior currently relies on provisional assumptions for greeting time buckets, empty schedules, one mood choice per date, and repeat same-day updates when multiple updates are disabled.
- Home business logic is now directly tested for the currently documented assumptions instead of relying only on ViewModel coverage.
- Home mood behavior currently uses the first observed same-day mood entry for both snapshot selection and id reuse, so repository ordering is still part of the provisional behavior surface.
- Home UI stays intentionally conservative where product source is missing: the mood control is read-only visual state, the date strip uses a 7-day selected-date-centered window, habit icons fall back to token/name monograms, color tokens map to semantic accents, and bottom navigation uses text monograms instead of unsourced product icons.
- App-shell DI ownership is now Hilt-managed: `MainActivity` receives repository interfaces from Hilt and passes the existing Home `ViewModelProvider.Factory` into `HabitTrackerApp`, so the Composable shell no longer constructs Room or repository objects.
- Edit-habit navigation remains effect-backed but unresolved at the app layer because no sourced edit flow or destination exists in the repository, so the action sheet now hides `Edit Habit` until a real callback is wired.
- Create Habit now follows the same strict MVI baseline as Home: immutable `UiState`, reducer-style state updates, and one-time effects emitted outside `UiState`.
- Create Habit validation is conservative and currently assumes these unsourced defaults: boolean is the initial habit type, quantity validation only applies in quantity mode, schedule presets are limited to `EVERY_DAY`, `WEEKDAYS`, `WEEKENDS`, and `CUSTOM`, custom weekday toggles preserve the currently visible day selection, and blank quantity unit labels normalize to `null`.
- Create Habit currently treats icon/color selection as required token picks with no product-approved catalog, gives newly added reminders a provisional default time of `09:00`, and maps reminder schedules to the habit-level selected days because no sourced per-reminder schedule UI/rules exist in the repo.
- Create Habit save currently persists the habit through `HabitsRepository.upsertHabit(...)`, then persists reminders individually through `upsertReminder(...)`, and performs a best-effort rollback by deleting the new habit if a reminder save fails because no transactional create-habit repository contract exists yet.
- Create Habit UI now stays intentionally conservative where source is missing: the screen uses provisional English section copy, a single full-screen form, a modal Goal sheet, full-screen icon/color pickers with one provisional `Available` group, monogram or token-based visual placeholders instead of sourced assets, and reminder rows that show existing time values without exposing unsourced time-editing UI.
- Picker selection updates feature state immediately but does not auto-dismiss the picker; users return to the form with manual back navigation until iOS dismissal behavior is sourced.
- Create Habit now matches the project’s Compose integration rule for one-time effects: route-level effect collection is lifecycle-aware.

## Blockers
- No iOS Home screen inventory, flow audit, or state inventory exists in the repository.
- No sourced Home greeting copy or final Home section semantics exist in the repository.
- No sourced Home rule set exists for empty schedules, mood frequency, or same-day repeat-tap behavior.
- No sourced Home mood taxonomy, icon catalog, color mapping, or bottom-navigation icon set exists in the repository.
- No sourced Home edit flow or action-sheet behavior exists beyond the already implemented Home logic effects.
- `HomeRoute` still uses non-lifecycle-aware one-shot effect collection.
- Home still uses a manually assembled `homeViewModelFactory(...)` rather than feature-level Hilt ViewModel integration, although the app-shell DI violation itself is now fixed.
- No iOS Create Habit screen inventory, screenshots, or flow/state audit exists in the repository.
- No sourced Create Habit defaults, validation copy, schedule preset list, reminder interaction rules, or icon/color picker behavior exists in the repository.
- No approved Create Habit icon catalog, color catalog grouping, or reminder-time editing treatment exists in the repository.
- The new Create Habit UI is buildable, but it is still assumption-driven and cannot be treated as parity-final until the iOS Create Habit audit exists.

## Validation
- `./gradlew :feature:home:testDebugUnitTest :feature:home:assembleDebug :data:habits:assembleDebug` completed successfully.
- `./gradlew :app:assembleDebug` completed successfully after the Home logic changes.
- `./gradlew :feature:home:testDebugUnitTest` completed successfully after adding direct use-case coverage.
- `./gradlew --no-daemon :feature:home:assembleDebug :feature:home:testDebugUnitTest :feature:home:assembleAndroidTest` completed successfully after the Home UI implementation.
- `./gradlew --no-daemon :app:assembleDebug` completed successfully after wiring the Home UI into the app route.
- `./gradlew --no-daemon :feature:home:assembleDebug :feature:home:assembleAndroidTest :app:assembleDebug` completed successfully after the Home UI review follow-up fix.
- `./gradlew --no-daemon :feature:home:assembleDebug` completed successfully after moving app-shell database/repository ownership into Hilt providers.
- `./gradlew --no-daemon :app:assembleDebug` completed successfully after removing direct Room/repository construction from `HabitTrackerApp`.
- `./gradlew --no-daemon :feature:create_habit:assembleDebug` completed successfully after adding the Create Habit MVI logic layer.
- `./gradlew --no-daemon :feature:create_habit:testDebugUnitTest` completed successfully after adding Create Habit validation, reducer, save-path, and ViewModel tests.
- `./gradlew --no-daemon :app:assembleDebug` completed successfully after the Create Habit feature-module changes.
- `./gradlew --no-daemon :feature:create_habit:assembleDebug :feature:create_habit:testDebugUnitTest :feature:create_habit:assembleAndroidTest :app:assembleDebug` completed successfully after the Create Habit UI implementation.
- `./gradlew --no-daemon :feature:create_habit:assembleDebug` completed successfully after the Create Habit UI review follow-up fix.

## Exact Next Recommended Step
Import verified iOS Create Habit screenshots and flow/state references, then update [docs/ios_screen_inventory.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/ios_screen_inventory.md), [docs/feature_parity_checklist.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/feature_parity_checklist.md), [docs/ui_parity_checklist.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/ui_parity_checklist.md), and [docs/icon_color_catalog.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/icon_color_catalog.md) against the implemented Create Habit screen before changing picker grouping, reminder-time editing treatment, copy, or dismissal behavior.
