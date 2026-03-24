# Performance Rules

## Purpose
Performance work must protect perceived quality without altering parity behavior.

## Core Rules
- Keep rendering driven by immutable state to reduce unnecessary recomposition.
- Avoid work on the main thread outside UI rendering.
- Prefer explicit data flow over hidden caching layers.
- Optimize only after understanding the bottleneck, not by guessing.

## Compose Rules
- Keep `UiState` stable and minimal.
- Avoid building large transient objects during recomposition.
- Hoist expensive work into ViewModels or use cases.
- Use lazy lists for large collections once list size is sourced and justifies it.
- Remember that one-time effects are not state and should not trigger repeated work.

## Data Rules
- Database and network access must stay off the main thread.
- Cache only when the product behavior or performance need is confirmed.
- Map transport and storage models efficiently, but do not skip explicit layers for convenience.

## Image And Media Rules
- Use Coil for image loading once image requirements exist.
- Add Media3 only if Learn video playback is confirmed.
- Avoid eager preloading of large media unless the source product demonstrates that behavior.

## Measurement Rules
- Validate performance claims with reproducible observations where possible.
- Track startup, list scrolling, and expensive rendering regressions as they appear.
- Do not hide correctness issues behind performance shortcuts.

## Guardrail
Performance changes that alter sourced behavior, timing, loading semantics, or user-visible ordering are not acceptable without explicit product approval.
