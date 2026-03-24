# Learn API Spec

## Status
Blocked. No Learn feature contract, backend specification, or media requirement is present in this repository as of 2026-03-23.

## Confirmed Constraints
- The target Android networking stack is Retrofit + OkHttp + Kotlinx Serialization.
- Media3 may be added only if the Learn experience is confirmed to require video playback.
- No Android endpoint, DTO, authentication rule, or caching rule may be invented without source material.

## Unknowns
| Area | Status |
| --- | --- |
| Whether a Learn feature exists in the iOS app | Unknown |
| Whether Learn uses remote data | Unknown |
| Endpoint list | Unknown |
| Authentication model | Unknown |
| Request and response fields | Unknown |
| Pagination behavior | Unknown |
| Offline caching rules | Unknown |
| Video playback requirements | Unknown |

## Contract Table
Populate this table only from verified source material.

| Endpoint ID | Path | Method | Auth | Request Model | Response Model | Error Cases | Cache Rules | Notes |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| API-000 | Unknown | Unknown | Unknown | Unknown | Unknown | Unknown | Unknown | Placeholder row until a sourced contract exists. |

## Implementation Rules
- Do not add Retrofit services until the endpoint contract is known.
- Do not add Media3 unless a sourced Learn media requirement exists.
- If Learn content is local-only, keep the feature free of unnecessary network and media infrastructure.
- Map transport models into domain models explicitly once the contract exists.
- Document any server-driven presentation behavior before implementing it.

## Required Inputs Before Android Work Starts
1. Proof that Learn exists in the iOS product.
2. Endpoint documentation or traffic capture with field-level details.
3. Authentication and authorization behavior.
4. Loading, empty, and failure states.
5. Whether content includes downloadable or streamed media.
