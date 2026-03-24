# Learn API Spec

## Status
Approved provisional runtime Learn spec for Android implementation as of 2026-03-24.
The current field-level detail payload schema has been verified against runtime responses.

## Categories Endpoint
- `GET https://iomc.rs/rutinna/db/index.json`
- Response: JSON array of objects with:
  - `title: string`
  - `id: string`
- `id` is the canonical Learn category/article key and is used for detail and media URL derivation.

## Example Categories
```json
[
  { "title": "Routine / Habit Building", "id": "routine" },
  { "title": "Learn Consistency and Discipline", "id": "consistency" },
  { "title": "Nutrition / Eating habits", "id": "nutrition" },
  { "title": "Why should we drink water often?", "id": "hydration" },
  { "title": "Benefits of regular walking", "id": "walking" },
  { "title": "Build your body and long term health", "id": "workout" },
  { "title": "Why meditation reduce stress?", "id": "meditation" },
  { "title": "Good night sleep - boost health", "id": "sleep" }
]
```

## Detail Endpoint
- `GET https://iomc.rs/rutinna/db/{categoryId}.json`
- `categoryId` must come from the categories response `id`.
- Response: JSON array of objects with:
  - `id: string`
  - `title: string`
  - `body: string`
- Each object represents one detail section for the category/article identified by `categoryId`.
- Runtime shape verified against current `routine`, `consistency`, `nutrition`, `walking`, and `meditation` payloads on 2026-03-24.

## Derived Media URLs
- `image = https://iomc.rs/rutinna/db/{categoryId}.jpg`
- `video = https://iomc.rs/rutinna/db/{categoryId}.mp4`
- Media URLs are derived from `categoryId`; they are not defined as separate endpoints in this spec.

## Implementation Rules
- Use `index.json` as the Learn list source.
- Use the category `id` as the only approved key for detail fetches and derived media URLs.
- Do not invent alternate paths, query parameters, authentication rules, pagination, or caching behavior.
- Expand Android DTO/domain models only to the currently proven fields: category `id`, category `title`, derived media URLs, and detail item `id` / `title` / `body`.
- Keep loading, empty, and error handling in the Learn feature.
- Keep video support conservative. If the runtime detail/content path does not cleanly support video playback, do not expose a dead CTA.
