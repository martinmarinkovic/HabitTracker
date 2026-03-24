# Feature Freeze

## Current Freeze State
Active.

## Reason
The repository does not yet contain the iOS source inventory, approved design assets, or backend contracts required to implement features without invention.

## What Is Allowed During The Freeze
- Documentation updates
- Project workflow setup
- Architecture planning
- Minimal bootstrap work that is directly required to enable source-backed implementation later
- Validation and cleanup of starter scaffolding when necessary to support the approved architecture

## What Is Not Allowed During The Freeze
- Building production screens without sourced parity references
- Defining business rules from guesswork
- Adding speculative APIs, DTOs, entities, or repositories
- Introducing placeholder architecture meant to be discarded later
- Redesigning product flows for Android convenience

## Unfreeze Criteria
The freeze can be relaxed for a feature only when:
- the feature exists in the iOS inventory
- its screen and parity requirements are documented
- its design inputs are known
- its data contract is known if it depends on local or remote data

## Enforcement Rule
If a task request conflicts with this freeze, document the blocker and stop short of unsourced implementation.
