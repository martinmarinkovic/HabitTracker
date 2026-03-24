# UI Parity Checklist

## Purpose
This checklist is used during implementation review and before sign-off for each sourced screen.

## Current Status
No screen-level parity review can be completed yet because the iOS screen inventory has not been imported.

## Review Areas
| Area | Required Verification |
| --- | --- |
| Layout | Overall structure, alignment, spacing, safe-area handling, scrolling behavior |
| Typography | Font family, size, weight, line height, truncation behavior |
| Color | Backgrounds, text, borders, accents, disabled states, dark-mode behavior |
| Components | Buttons, fields, cards, lists, chips, dialogs, sheets, and custom components |
| States | Loading, empty, content, error, blocked, offline, and permission states if sourced |
| Navigation | Entry and exit behavior, back handling, modal presentation, deep links if sourced |
| Motion | Screen transitions, loading animations, micro-interactions, gesture behavior |
| Input | Keyboard behavior, focus order, validation timing, text selection, IME actions |
| Accessibility | Labels, roles, contrast, touch targets, screen reader order |
| Localization | Text expansion, pluralization, date or number formatting, RTL if applicable |

## Screen Review Table
| Screen ID | Screen Name | Visual Parity | Interaction Parity | Accessibility | Localization | Status | Notes |
| --- | --- | --- | --- | --- | --- | --- | --- |
| UIP-000 | Unverified screen set | Pending | Pending | Pending | Pending | Blocked | Populate after iOS audit. |

## Sign-Off Rule
No screen may be marked parity-complete until both visual behavior and interaction behavior are confirmed against iOS source references.
