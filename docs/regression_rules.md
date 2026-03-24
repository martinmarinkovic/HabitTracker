# Regression Rules

## Purpose
Every change must protect already-migrated behavior from accidental divergence. Regression prevention is part of implementation, not a cleanup task.

## Core Rules
- Before changing anything, identify the existing surfaces that could break.
- Prefer narrow changes over broad rewrites.
- Re-run the smallest reliable validation that covers the impacted surface.
- Update parity and testing docs when a regression risk is discovered.
- If behavior changes intentionally, document the reason and source approval explicitly.

## High-Risk Areas
| Area | Why It Is Risky |
| --- | --- |
| State transitions | Easy to break loading, retry, and one-time effect delivery |
| Navigation | Back stack, restoration, and modal behavior regress easily |
| Persistence | Schema mismatches or mapping mistakes can corrupt or hide data |
| Time-based logic | Date calculations, timezone handling, and day boundaries are error-prone |
| Network mapping | DTO drift can silently break UI or domain logic |
| UI parity | Visual polish often regresses when tokens or components are inconsistent |
| Accessibility | Minor UI changes can break labels, roles, or focus order |

## Required Change Review Questions
1. What parity surface is being changed?
2. What existing screens, data flows, or states could be affected?
3. Which tests need to be added or rerun?
4. Which docs need to be updated to reflect the new truth?

## Safe Rollout Rules
- Do not mix unrelated refactors into parity work.
- Keep migrations incremental so issues can be isolated quickly.
- Prefer explicit mappings and typed contracts over dynamic or reflection-heavy shortcuts.
- Treat undocumented product behavior as a blocker, not a chance to improvise.
