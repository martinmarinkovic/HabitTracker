# Design System Spec

## Status
No parity-approved design system export from the iOS product exists in this repository yet. The Android project now contains a full semantic design-system foundation in `core/designsystem`, but the actual token values remain provisional until iOS source material is imported.

## Confirmed Android Baseline
The current Android app now uses the shared `core/designsystem` theme rather than the original starter theme:

| Area | Current State | Production Status |
| --- | --- | --- |
| Semantic colors | Implemented in `core/designsystem/Colors.kt` | Provisional, not parity-approved |
| Typography hierarchy | Implemented in `core/designsystem/Typography.kt` | Provisional, not parity-approved |
| Spacing scale | Implemented in `core/designsystem/Spacing.kt` | Provisional, not parity-approved |
| Shapes | Implemented in `core/designsystem/Shapes.kt` | Provisional, not parity-approved |
| Elevation and gradients | Implemented in `core/designsystem/Elevation.kt` and `Gradients.kt` | Provisional, not parity-approved |
| Surface primitives | Implemented in `core/designsystem/Surfaces.kt` | Provisional, not parity-approved |
| Dynamic color | Disabled in the shared runtime theme | Correct until sourced requirements say otherwise |
| XML app theme | `android:Theme.Material.Light.NoActionBar` for shell startup only | Transitional |
| Launcher assets | Default Android Studio launcher resources | Not parity-approved |

## Design System Rules
- Only implement tokens and components that are backed by iOS source or approved product documentation.
- Do not treat Material 3 defaults as product decisions.
- Dynamic color must be disabled unless parity requirements explicitly allow Android-only personalization.
- Shared visual primitives belong in a dedicated design system module once introduced.
- Screen code must consume semantic tokens and approved components, not raw colors or ad hoc spacing.

## Token Categories That Must Be Sourced
| Category | Required Source | Status |
| --- | --- | --- |
| Colors | iOS asset catalog or design export | Missing |
| Typography | Font families, sizes, weights, line heights | Missing |
| Spacing | Layout spacing scale and padding rules | Missing |
| Shapes | Corner radius and border patterns | Missing |
| Elevation | Shadow and depth rules if any | Missing |
| Icons | Product icon catalog and usage rules | Missing |
| Motion | Timing, easing, and transition behavior | Missing |

## Componentization Rules
- Promote a UI pattern into the design system only after it appears in multiple sourced screens or is clearly a shared primitive.
- Keep product-specific composite components close to the owning feature until reuse is proven.
- Each design system component must define supported states, tokens consumed, and accessibility expectations.

## Styling Rules
- Use semantic token names such as `surface_primary` or `text_muted` once the token set exists.
- Avoid hardcoded color literals in feature code.
- Avoid mixed ownership where a component partly uses design-system tokens and partly uses feature-local values.
- Any deviation from iOS visual behavior must be documented and approved as a gap, not hidden.

## Source Intake Checklist
Before implementing the first production screen, collect:
1. Color palette and semantic usage mapping.
2. Typography scale, font families, and text styles.
3. Spacing and sizing rules.
4. Icons and illustrations.
5. Light and dark mode behavior.
6. Motion and transition behavior.
7. Any accessibility-specific color or contrast rules.

## Delivery Rule
The provisional semantic system may be reused structurally, but its concrete values must be reconciled against iOS exports before any feature UI is treated as parity-complete. Replace provisional token values only after parity-approved tokens are documented in [icon_color_catalog.md](/Users/martinmarinkovic/AndroidStudioProjects/HabitTracker/docs/icon_color_catalog.md) and finalized here.
