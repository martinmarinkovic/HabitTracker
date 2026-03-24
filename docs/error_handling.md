# Error Handling

## Purpose
Errors must be handled consistently across the app so user experience remains predictable and implementation remains testable.

## Error Categories
| Category | Examples | Handling Goal |
| --- | --- | --- |
| Validation | User input does not meet sourced rules | Explain what the user can fix |
| Connectivity | No network, timeout, unreachable host | Preserve state and offer safe retry when appropriate |
| Server | 4xx or 5xx responses with known contract | Map into sourced user-facing behavior |
| Serialization | Contract mismatch or malformed data | Fail safely, log diagnostically, surface minimal user message |
| Storage | Database read, write, or migration failure | Preserve app stability and avoid silent corruption |
| Unknown | Anything unexpected | Fail closed, log with context, avoid crashes when recoverable |

## MVI Integration Rules
- Recoverable errors should be represented through state and, when needed, a one-time effect.
- Irrecoverable initialization failures should still produce deterministic UI state.
- Do not throw raw exceptions across presentation boundaries.
- Map infrastructure exceptions into feature-level error models close to the boundary where context exists.

## User-Facing Rules
- Match iOS copy and presentation style when sourced.
- If copy is not sourced yet, use neutral minimal wording only when implementation work requires it, then record the gap.
- Preserve the last valid content state where possible instead of blanking the screen.
- Offer retry only when retry semantics are meaningful.

## Logging Rules
- Log technical details for diagnosis.
- Do not leak raw stack traces or backend internals into user-visible UI.
- Avoid logging sensitive user-generated content or personal data.

## Retry Rules
- Retry behavior must be explicit and testable.
- Do not implement silent endless retries.
- Network retries at the transport layer must be conservative and source-appropriate.

## Failure Documentation Rule
If a new failure mode is discovered during migration, update this document or the feature-specific docs before ending the session.
