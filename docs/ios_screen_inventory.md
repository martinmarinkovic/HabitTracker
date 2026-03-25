# iOS Screen Inventory

## Purpose
This file is the ledger of every screen that exists in the iOS source product. No Android feature implementation should start until the relevant screen has a verified inventory record here.

## Current Status
- Verified iOS screen count: `6`
- Inventory completeness: `Provisional`
- Reason: Inventory reconstructed from provided screenshot references. Some behaviors remain assumption-driven and must be refined when full iOS source or spec is available.

## Inventory Columns
Each screen record must capture:
- Screen ID
- iOS screen name
- Source reference location
- Primary purpose
- Entry points
- Navigation destinations
- Required UI states
- Data dependencies
- Device capability dependencies
- Media dependencies
- Android route/module owner
- Parity status
- Notes

## Inventory Table

| Screen ID | iOS Screen Name | Source Reference | Purpose | Entry Points | Destinations | States | Data Dependencies | Media | Android Ownership | Parity Status | Notes |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| INV-001 | Home | Provided screenshots | Display daily habits, progress, and quick actions | App start, bottom navigation | Create Habit, Habit actions | Loading, Empty, Content, Error | Habits, Habit entries, Mood | Icons | feature/home | Provisional | Multi-action screen with quick updates and mood input |
| INV-002 | Create Habit | Provided screenshots | Create and configure a new habit | FAB / center button | Icon picker, Color picker | Form state, Validation error | Habit draft | Icons, Colors | feature/create_habit | Provisional | Multiple subflows (goal, schedule, reminders) |
| INV-003 | Activity | Provided screenshots | Show analytics and progress over time | Bottom navigation | Habit filter sheet | Loading, Empty, Content | Habit entries, Analytics | Charts | feature/activity | Provisional | Period switching (daily/weekly/monthly) |
| INV-004 | Learn | Provided screenshots + API spec | Display educational content and articles | Bottom navigation | Detail screen, Video | Loading, Empty, Content, Error | Remote Learn API | Images, Video | feature/learn | Provisional | Content-driven feature with external media |
| INV-005 | Profile | Provided screenshots | Show user summary and mood analytics | Bottom navigation | Settings | Loading, Content | Mood entries, Habit stats | Charts, Avatar | feature/profile | Provisional | Includes summary metrics and mood analytics |
| INV-006 | Settings | Provided screenshots | Manage app preferences and toggles | Profile screen | None or sub-settings | Content | Settings data | None | feature/settings | Provisional | Toggle-based configuration screen |

---

## Profile Screen Detail (Source-backed from screenshots)

### Layout

#### Header
- Title: Profile
- Avatar (circular)
- Optional change photo action (not confirmed → keep simple)

#### Summary Section
- Completed habits
- Current streak
- Best streak

#### Period Switch
- Tabs:
  - Daily
  - Weekly
  - Monthly

#### Mood Analytics Card
- Visual representation of mood data
- Shows distribution/trend over selected period
- Chart style: based on screenshot (keep simple if unclear)

#### Settings Entry
- Navigation row/button to Settings screen

---

## Population Rules
- One row per real product screen, modal, or distinct full-screen flow step.
- Do not collapse multiple sourced screens into a generic Android interpretation.
- Record every empty, loading, error, permission, and blocked state if the iOS source exposes them.
- Link every row to the exact screenshot set or product reference used.
- If behavior is unclear, document it as an assumption instead of inventing logic.

## Exit Criteria For This File
This inventory is usable when:
- all main screens are listed
- each screen has a clear purpose and structure
- parity status is defined
- Android implementation can proceed without guessing core layout