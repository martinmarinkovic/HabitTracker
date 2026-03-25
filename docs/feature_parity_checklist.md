# Feature Parity Checklist

## Purpose
Use this checklist to approve a sourced feature for Android implementation and to verify parity after implementation.

## Current Status
No feature list has been verified from the iOS product yet. All feature-level parity work is blocked on source intake.

## Feature Checklist
Every feature must be checked across these dimensions:
- Source references collected
- Screen inventory entries created
- Business rules extracted
- Domain models defined
- Persistence behavior defined
- Network behavior defined if applicable
- Navigation behavior defined
- Error handling defined
- Accessibility behavior defined
- Localization behavior defined
- Tests defined
- Regression risk noted

## Profile
- [x] Header (avatar, title)
- [x] Summary metrics (completed, streaks)
- [x] Period switching (daily/weekly/monthly)
- [x] Mood analytics visualization
- [x] Settings navigation

## Tracking Table
| Feature ID | Feature Name | Source References | Business Rules | UI States | Data Contracts | Navigation | Errors | Tests | Status | Notes |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| FP-000 | Unverified feature inventory | Missing | Missing | Missing | Missing | Missing | Missing | Missing | Blocked | Populate after iOS audit. |

## Sign-Off Rules
- A feature cannot be marked ready for implementation until all required source references exist.
- A feature cannot be marked parity-complete until UI, behavior, persistence, and error handling are all verified.
- Missing information must remain a visible blocker, not an implicit guess.

## Required Cross-Checks
For every feature, confirm alignment with:
- [ios_screen_inventory.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/ios_screen_inventory.md)
- [ui_parity_checklist.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/ui_parity_checklist.md)
- [testing_strategy.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/testing_strategy.md)
- [definition_of_done.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/definition_of_done.md)
