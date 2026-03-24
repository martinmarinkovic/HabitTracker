# Recovery Protocol

## Purpose
Use this protocol when a session is interrupted, context is lost, or there is uncertainty about the current migration state.

## Recovery Steps
1. Read [AGENTS.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/AGENTS.md).
2. Read [project_overview.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/project_overview.md).
3. Read [known_assumptions_and_gaps.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/known_assumptions_and_gaps.md).
4. Read [current_session_handoff.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/current_session_handoff.md).
5. Read task-specific docs for the area being changed.
6. Confirm the requested work is backed by source material already in the repository.
7. If source material is missing, document the blocker before changing code.

## When To Trigger This Protocol
- A previous session stopped mid-task.
- New files appear without explanation.
- Behavior is unclear or contradictory across docs.
- A future session is tempted to guess missing product requirements.

## Recovery Rule
When in doubt, restore context from docs first and choose the smallest safe next step. Do not improvise feature behavior to regain momentum.
