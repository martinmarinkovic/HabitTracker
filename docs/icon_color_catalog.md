# Icon And Color Catalog

## Status
No parity-approved icon or color catalog has been imported from the iOS app. This file records the current Android starter assets so they are not mistaken for approved product assets.

## Approved Production Assets
None yet.

## Current Provisional Design-System Tokens
These values are active in `core/designsystem`, but they are not yet parity-approved:

| Asset | Source | Current Value | Status |
| --- | --- | --- | --- |
| `accentPrimary` | `core/designsystem/Colors.kt` | `#3D63FF` light / `#88A1FF` dark | Provisional |
| `accentSecondary` | `core/designsystem/Colors.kt` | `#7F8FFF` light / `#B2BEFF` dark | Provisional |
| `surfaceCanvas` | `core/designsystem/Colors.kt` | `#F4F5F7` light / `#0E1116` dark | Provisional |
| `surfacePrimary` | `core/designsystem/Colors.kt` | `#FFFFFF` light / `#151A22` dark | Provisional |
| `surfaceSecondary` | `core/designsystem/Colors.kt` | `#F1F4F8` light / `#1A2130` dark | Provisional |
| `surfaceTinted` | `core/designsystem/Colors.kt` | `#EEF3FF` light / `#1B2333` dark | Provisional |
| `textPrimary` | `core/designsystem/Colors.kt` | `#111827` light / `#F5F7FB` dark | Provisional |
| `textSecondary` | `core/designsystem/Colors.kt` | `#5B6472` light / `#C3CAD6` dark | Provisional |
| `strokeSubtle` | `core/designsystem/Colors.kt` | `#E5EAF1` light / `#253041` dark | Provisional |

## Legacy Starter Assets That Must Not Be Treated As Product Truth
| Asset | Source | Current Value | Status |
| --- | --- | --- | --- |
| `Purple80` | `app/src/main/java/com/threemdroid/habittracker/ui/theme/Color.kt` | `#D0BCFF` | Starter only |
| `PurpleGrey80` | `app/src/main/java/com/threemdroid/habittracker/ui/theme/Color.kt` | `#CCC2DC` | Starter only |
| `Pink80` | `app/src/main/java/com/threemdroid/habittracker/ui/theme/Color.kt` | `#EFB8C8` | Starter only |
| `Purple40` | `app/src/main/java/com/threemdroid/habittracker/ui/theme/Color.kt` | `#6650A4` | Starter only |
| `PurpleGrey40` | `app/src/main/java/com/threemdroid/habittracker/ui/theme/Color.kt` | `#625B71` | Starter only |
| `Pink40` | `app/src/main/java/com/threemdroid/habittracker/ui/theme/Color.kt` | `#7D5260` | Starter only |
| `ic_launcher` and round variants | `app/src/main/res/mipmap-*` | Default Android Studio launcher assets | Starter only |

## Catalog Structure For Approved Assets
When source material is available, track each approved asset with:
- Asset ID
- Semantic name
- iOS source reference
- Android target name
- Hex or vector source
- Usage context
- Light and dark behavior
- Status

## Approved Colors Table
| Color ID | Semantic Name | Source Reference | Value | Usage | Status | Notes |
| --- | --- | --- | --- | --- | --- | --- |
| COLOR-000 | Unverified parity color set | Missing | Unknown | Unknown | Blocked | Replace provisional values with sourced tokens only. |

## Approved Icons Table
| Icon ID | Semantic Name | Source Reference | Asset Type | Usage | Status | Notes |
| --- | --- | --- | --- | --- | --- | --- |
| ICON-000 | Unverified icon set | Missing | Unknown | Unknown | Blocked | Replace with sourced assets only. |

## Rules
- Use semantic names, not appearance-based names, for production assets.
- Do not hardcode hex values in feature code.
- Do not introduce Material symbols or icons unless they match a sourced iOS asset or approved substitute.
- Keep asset naming aligned with [naming_conventions.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/naming_conventions.md).
