# iOS Screen Inventory

## Purpose
This file is the ledger of every screen that exists in the iOS source product. No Android feature implementation should start until the relevant screen has a verified inventory record here.

## Current Status
- Verified iOS screen count: `0`
- Inventory completeness: `Blocked`
- Reason: no iOS reference package or exported screen audit is present in this repository

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
| INV-000 | Unverified inventory bootstrap | Missing | No screen inventory has been imported yet | Unknown | Unknown | Unknown | Unknown | Unknown | Not assigned | Blocked | Populate only from verified iOS source artifacts. |

## Population Rules
- One row per real product screen, modal, or distinct full-screen flow step.
- Do not collapse multiple sourced screens into a generic Android interpretation.
- Record every empty, loading, error, permission, and blocked state if the iOS source exposes them.
- Link every row to the exact iOS file, screenshot set, or product spec that proves the screen exists.
- If a screen's existence is uncertain, keep it out of the inventory until confirmed.

## Exit Criteria For This File
This inventory is usable when:
- every iOS screen is listed
- every row has a source reference
- parity status is assigned
- Android ownership can be planned without guessing
