# Route Authorization Guide

This project uses method-level authorization with `@PreAuthorize`.

## Strategy

- Keep `/manage/*` endpoints as privileged management routes.
- Use role checks in controller methods for enforcement.
- Keep URL routing clear and stable for frontend integration.

## Role Groups

- `public`
  - `/api/auth/**`

- `member`
  - `/api/members/me/**`
  - `/api/enrollments/me`
  - member order flow (`/api/orders`, `/api/orders/{orderId}/payments`, `/api/orders/{orderId}/payments` POST)
  - member booking/attendance actions

- `trainer`
  - `/api/trainers/me/**`
  - `/api/classes/me`
  - trainer operational endpoints (class attendance, workout plan/schedule create-update)

- `admin/manage`
  - Any management endpoint under `/manage/*`
  - user management, category/course/class management, reporting/attendance-all, broadcast notifications

## `/manage/*` Convention

The following are privileged and should be treated as admin-only UI pages:

- `/api/categories/manage`
- `/api/classes/manage`
- `/api/class-bookings/manage/**`
- `/api/orders/manage/**`
- `/api/workout-plans/manage`
- `/api/income/manage`

## Postman Grouping

The Postman collection now includes role folders:

- `public`
- `member`
- `trainer`
- `admin/manage`

Use the correct token per folder when testing.
