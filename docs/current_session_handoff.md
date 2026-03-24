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
- Added `ActivityUiState`, `ActivityIntent`, `ActivityEffect`, analytics summary/chart/filter models, a pure reducer, and `ActivityViewModel` in `feature/activity`.
- Added a feature-local `ObserveActivityDataUseCase` that derives daily / weekly / monthly period windows, previous/next period navigation, all-habits versus selected-habit filtering, chart-ready daily points, average completion, done days, and total progress from the existing habits repository surface.
- Kept `ActivityDestination` as the existing placeholder route so this phase stays logic-only and does not start unsourced Activity UI assembly.
- Added Activity unit tests for analytics aggregation and failure handling, reducer state transitions, and ViewModel period/filter/navigation behavior.
- Reviewed the Activity logic phase and documented that Activity currently inherits the same empty `selectedDays` => every-day schedule fallback already used elsewhere in the app, so the assumption ledger now matches the implementation more precisely.
- Added direct Activity use-case coverage for the previously under-tested analytics rules: empty `selectedDays` => every-day fallback plus created-at and stopped-at historical gating.
- Replaced the Activity placeholder route with a conservative Compose `ActivityRoute` and `ActivityScreen` backed by the existing `ActivityViewModel`.
- Added Activity UI rendering for the title, daily/weekly/monthly segmented tabs, current-period header card, previous/next controls, habit filter entry, analytics summary card, chart card, habit-selection sheet, and loading/empty/error states.
- Added an `activityViewModelFactory(...)`, minimal app-level factory wiring in `MainActivity`, and navigation-graph wiring in `HabitTrackerApp` so the Activity route now consumes the existing Activity MVI logic without widening feature behavior.
- Added Activity previews, UI test tags, and Compose UI tests for basic rendering plus critical period-switching, period-navigation, habit-filter-sheet, and retry interactions.
- Reviewed the Activity UI phase and added the missing durable Activity UI assumption row to `docs/known_assumptions_and_gaps.md` so the blocker ledger now matches the current screen implementation.
- Moved Activity off the manual `activityViewModelFactory(...)` path and onto a Hilt-owned `@HiltViewModel` integration.
- Made `ObserveActivityDataUseCase` injectable, added a singleton `Clock` provider to the app Hilt module, and switched `ActivityRoute` to resolve `ActivityViewModel` through `hiltViewModel()`.
- Removed the Activity-specific manual factory wiring from `MainActivity` and `HabitTrackerApp` while keeping current Activity behavior unchanged.
- Added `LearnUiState`, `LearnIntent`, `LearnEffect`, a pure reducer, a feature-local `ObserveLearnContentUseCase`, and an `@HiltViewModel`-owned `LearnViewModel` in `feature/learn`.
- Replaced the Learn placeholder route with a conservative Compose Learn flow that now renders a main grid, detail screen, optional Coil-backed image treatment, paragraph sections when explicit text exists, optional video CTA wiring, and loading/empty/error states.
- Added a minimal Hilt binding module in `data:learn` so `LearnRepositoryImpl` is resolved through DI, then wired `LearnRoute` to the project-standard Hilt ViewModel path.
- Added Learn previews, UI test tags, unit tests for the use case/reducer/ViewModel layers, and Compose UI tests for main-grid rendering plus detail-opening basics.
- Logged the new Learn runtime and UI assumptions in `docs/known_assumptions_and_gaps.md`, including the fact that repository-backed production Learn content still maps title-only data and currently resolves to the `Unavailable` error path.
- Continued the interrupted Learn phase from the existing working-tree implementation instead of recreating it, and confirmed the current Learn production code already covered the requested grid/detail/MVI surface.
- Added Learn follow-up coverage for selection retention/clearing, empty-content ViewModel behavior, unknown article selection handling, loading/empty/error UI rendering, retry dispatch, and video-CTA intent dispatch.
- Added Learn loading/empty/error previews and re-ran the Learn validation command so the current assumption-driven state surface is easier to inspect without widening the blocked Learn contract.
- Replaced the blocked Learn API placeholder doc with the approved provisional runtime spec for this project: `index.json` categories, `{categoryId}.json` detail path, and derived `jpg` / `mp4` media URLs under `https://iomc.rs/rutinna/db/`.
- Updated the Learn blocker ledger so the repo now records that a provisional runtime contract exists, while the field-level detail payload schema and iOS Learn parity references are still unresolved.
- Verified the live Learn detail payload schema, expanded the shared Learn model only to the proven runtime fields (`imageUrl`, `videoUrl`, and detail `sections(id, title, body)`), replaced the Learn repository `Unavailable` stub with a real categories-plus-detail remote aggregation path, and updated Learn mapping/tests accordingly.
- Removed the direct `feature:learn` -> `data:learn` dependency and moved Learn feature/data assembly ownership to `:app` by adding `data:learn` at the app boundary while leaving the existing `data:learn` Hilt binding module in place.
- Audited the Profile implementation request against `docs/ios_screen_inventory.md` and the current Profile model/repository surface, then stopped implementation because the repository still has no verified Profile iOS screen inventory and no source-backed Profile contract beyond the provisional count-based `ProfileSummary`.
- Added a durable Profile blocker row to `docs/known_assumptions_and_gaps.md` so the long-term gap ledger now explicitly records that the current Profile surface is insufficient for sourced Profile MVI or UI implementation.

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
- Activity now follows the same strict MVI baseline as Home and Create Habit: immutable `UiState`, reducer-style state updates, and a separate one-time `Effect` stream even though no Activity effect is currently emitted by the logic layer.
- Activity analytics currently stay feature-local and build only on `domain:habits`; no new repository or database contract was introduced because the existing habit list and per-habit period-entry observers are sufficient for the current conservative analytics scope.
- Activity currently defaults to `WEEKLY`, uses ISO Monday-through-Sunday weekly windows and calendar-month monthly windows, keeps chart points day-granular across all period modes, and allows next-period navigation without a future bound because no sourced Activity period semantics exist in the repository.
- Activity analytics currently average capped per-habit completion ratios across scheduled habit-days, treat `doneDays` for the all-habits filter as dates where every scheduled filtered habit reached its current target, expose all stored habits as filter options, keep zero-entry periods as content with zero metrics when habits exist, inherit the current empty `selectedDays` => every-day schedule fallback, and use current habit schedule/target/lifecycle metadata across historical dates because there is no versioned habit-history model.
- The Activity use-case test surface now directly covers the currently implemented schedule fallback and historical lifecycle gating assumptions instead of leaving them implied by the analytics implementation.
- Activity UI now stays intentionally conservative where source is missing: it uses a plain title-based top section instead of sourced iconography, a tinted period header card with explicit date-range text, a simple single-choice habit filter sheet that dismisses after selection, a day-granular vertical bar chart derived directly from the existing `chartData`, and provisional English copy for analytics labels plus loading/empty/error states.
- Activity still does not add bottom navigation, extra chart interactions, or new analytics behaviors because those flows are not sourced in the repository today.
- Activity dependency ownership now matches the project-standard Hilt path: the Activity route resolves `ActivityViewModel` through `hiltViewModel()`, and the app shell no longer assembles an Activity-specific manual factory.
- Learn now has a real MVI/UI implementation backed by a real provisional runtime data path: the repository fetches `index.json`, fetches each `{categoryId}.json` array of `{ id, title, body }` detail items, derives `jpg` / `mp4` URLs from the category id, and the feature maps those sections into the current detail paragraph cards.
- The Learn continuation follow-up intentionally changed coverage and previews only; it did not widen the Learn data contract or rework the existing production UI/state flow that was already present in the interrupted session.
- Learn now has an approved provisional runtime spec for the category list endpoint, detail payload schema, and derived media URLs, but the repo still lacks verified iOS Learn screen inventory and parity references.
- Learn dependency ownership now matches the architecture rules: `feature:learn` depends only on core/domain modules, `data:learn` still owns repository binding, and `:app` now owns the Learn feature/data assembly dependency.
- The app shell now wires `onVideoRequested` end to end through the Learn feature entry and opens derived Learn `videoUrl` values through an app-owned external URI handler, so the `Play Video` CTA no longer resolves to a no-op.
- Profile remains intentionally blocked: `ProfileFeatureEntry` is still a placeholder route, and the current `ProfileSummary` contract is only a provisional count aggregate with no sourced identity/avatar/settings/mood-analytics surface.

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
- No iOS Activity screen inventory, screenshots, or analytics-state audit exists in the repository.
- No sourced Activity default period, completion-percentage semantics, done-day rule, chart granularity, or future-period navigation rule exists in the repository.
- The new Activity UI is buildable, but it remains assumption-driven and cannot be treated as parity-final until the iOS Activity audit exists.
- The Activity Compose UI tests compile into the Android test artifact, but they were not executed on a device or emulator in this session.
- No verified Learn iOS screen inventory or parity references exist in the repository yet.
- The Learn Compose UI tests compile into the Android test artifact, but they were not executed on a device or emulator in this session.
- The Learn repository failure branches for detail-fetch failure, empty-body failure, and serialization failure still lack direct unit-test coverage.
- No verified Profile iOS screen inventory, identity/avatar contract, settings-shortcut behavior, period-switching rule, or mood-analytics spec exists in the repository.

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
- `./gradlew --no-daemon :feature:activity:testDebugUnitTest :feature:activity:assembleDebug` completed successfully after adding the Activity MVI logic layer and analytics tests.
- `./gradlew --no-daemon :app:assembleDebug` completed successfully after the Activity feature-module changes.
- `./gradlew --no-daemon :feature:activity:testDebugUnitTest` completed successfully after adding direct coverage for the Activity schedule fallback and created-at / stopped-at gating rules.
- `./gradlew --no-daemon :feature:activity:assembleDebug :feature:activity:testDebugUnitTest :feature:activity:assembleAndroidTest :app:assembleDebug` completed successfully after the Activity UI implementation and minimal app wiring.
- `./gradlew --no-daemon :feature:activity:assembleDebug :feature:activity:testDebugUnitTest :feature:activity:assembleAndroidTest :app:assembleDebug` completed successfully after moving Activity onto the Hilt-owned ViewModel path.
- `./gradlew --no-daemon :feature:learn:assembleDebug :feature:learn:testDebugUnitTest :feature:learn:assembleAndroidTest :app:assembleDebug` completed successfully after the Learn MVI, Hilt wiring, and UI implementation.
- `./gradlew --no-daemon :feature:learn:assembleDebug :feature:learn:testDebugUnitTest :feature:learn:assembleAndroidTest :app:assembleDebug` completed successfully after the Learn continuation follow-up coverage and preview additions.
- No build validation was required in the Learn runtime spec approval follow-up because this session updated documentation only.
- `./gradlew --no-daemon :data:learn:testDebugUnitTest :feature:learn:testDebugUnitTest :feature:learn:assembleDebug :app:assembleDebug` completed successfully after implementing the Learn runtime data path and mapping updates.
- `./gradlew --no-daemon :feature:learn:assembleDebug :app:assembleDebug` completed successfully after removing the direct `feature:learn` -> `data:learn` dependency and moving Learn data integration ownership to `:app`.
- `./gradlew --no-daemon :feature:learn:testDebugUnitTest :feature:learn:assembleDebug :app:assembleDebug` completed successfully after wiring Learn video launching through the app shell.

## Exact Next Recommended Step
Add direct Learn repository tests for detail-fetch failure, empty-body failure, and serialization failure so the runtime data-path failure branches are covered.
