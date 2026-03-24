# Definition Of Done

## Purpose
A task is done only when the changed surface is source-backed, validated, documented, and safe for the next contributor to build on.

## Done Criteria For Feature Work
- The feature is backed by verified iOS or product source references.
- Relevant rows in [ios_screen_inventory.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/ios_screen_inventory.md) are complete.
- Relevant rows in [feature_parity_checklist.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/feature_parity_checklist.md) are updated.
- The feature follows [architecture_rules.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/architecture_rules.md) and [state_management_rules.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/state_management_rules.md).
- Required tests pass for the changed scope.
- Error, loading, and edge states are handled.
- No unsourced business behavior has been added.
- Any remaining gap is documented in [known_assumptions_and_gaps.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/known_assumptions_and_gaps.md).
- The repo remains buildable.

## Done Criteria For Documentation Or Bootstrap Work
- The document reflects confirmed repository or product facts.
- Unknowns are clearly marked as blocked or assumptions.
- The document is reusable by future sessions without re-discovery.
- Related docs remain internally consistent.
- [progress_log.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/progress_log.md) and [current_session_handoff.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/current_session_handoff.md) are updated.

## Done Criteria For Infrastructure Work
- The infrastructure is required by confirmed architecture or source-backed feature work.
- The implementation is not a throwaway scaffold.
- Module and dependency boundaries remain clean.
- Validation covers wiring and failure modes.
- New conventions are documented before the session ends.

## Automatic Not-Done Conditions
- Missing source references for implemented behavior.
- TODOs that hide unresolved product questions.
- Unvalidated schema or navigation changes.
- Build or test failures caused by the change.
- New assumptions that were not documented.
