# Logging Rules

## Purpose
Logging exists to support debugging, supportability, and migration verification. It must not become an unstructured dump of sensitive data.

## Core Rules
- Use structured, concise log messages.
- Log at the boundary where context is strongest.
- Prefer stable tags and event names over free-form prose.
- Keep release logging conservative.

## Recommended Log Shape
Each log entry should make clear:
- feature or module
- action or failure
- stable identifiers when safe
- outcome

## Levels
| Level | Usage |
| --- | --- |
| Debug | Local development diagnosis |
| Info | Important lifecycle or feature milestones with low volume |
| Warn | Recoverable unexpected states |
| Error | Failures that require investigation |

## Privacy Rules
- Do not log personal data, habit content, authentication material, tokens, or raw server payloads unless explicitly redacted and absolutely necessary.
- Do not log full database rows or response bodies in release builds.
- If a field might be sensitive, treat it as sensitive.

## Product Rules
- Logging is not analytics.
- Do not invent product analytics events while adding logs.
- If analytics are later required, document them separately from operational logs.

## Naming Rule
Use a stable tag format based on module or feature ownership, such as `FeatureNameViewModel` or `NetworkClient`, once the production architecture exists.
