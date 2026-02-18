# API Contract

## Base

- Base URL: `http://localhost:8080`
- Auth header for protected routes: `Authorization: Bearer <JWT>`
- Public routes: `/api/auth/**`

## Roles

- `ADMIN`
- `TRAINER`
- `MEMBER`

Auth rules are enforced with `@PreAuthorize` on controller methods.

## Error Shape

Current global handler: `src/main/java/com/gymmanagement/gym_management/exception/GlobalExceptionHandler.java`
Security 401/403 handler: `src/main/java/com/gymmanagement/gym_management/security/RestAuthenticationEntryPoint.java`, `src/main/java/com/gymmanagement/gym_management/security/RestAccessDeniedHandler.java`

Standard response:

```json
{
  "code": "ERROR_CODE",
  "message": "Human readable message",
  "details": {},
  "timestamp": "2026-02-15T10:00:00Z"
}
```

### Validation Error (`400`)

```json
{
  "code": "VALIDATION_ERROR",
  "message": "Validation failed",
  "details": {
    "fieldErrors": {
      "fieldName": "validation message"
    }
  },
  "timestamp": "2026-02-15T10:00:00Z"
}
```

### Status Code Mapping

- `400` -> `BAD_REQUEST`, `VALIDATION_ERROR`
- `401` -> `UNAUTHORIZED`
- `403` -> `FORBIDDEN`
- `404` -> `NOT_FOUND`
- `409` -> `CONFLICT`
- `500` -> `INTERNAL_SERVER_ERROR`

## Endpoint Contract

`Auth`

| Method | Route | Auth | Request Body | Success Response |
|---|---|---|---|---|
| POST | `/api/auth/login` | Public | `LoginRequestDTO` | `String` (JWT token) |
| POST | `/api/auth/register/member` | Public | `RegisterRequest` | `String` |
| POST | `/api/auth/register/trainer` | Public | `RegisterRequest` | `String` |

`Users (Admin)`

| Method | Route | Auth | Request Body | Success Response |
|---|---|---|---|---|
| POST | `/api/users` | ADMIN | `UserCreateRequest` | `UserResponse` |
| PUT | `/api/users/{id}` | ADMIN | `UserUpdateRequest` | `UserResponse` |
| GET | `/api/users/{id}` | ADMIN | - | `UserResponse` |
| GET | `/api/users` | ADMIN | - | `List<UserResponse>` |
| DELETE | `/api/users/{id}` | ADMIN | - | `String` |
| PUT | `/api/users/{id}/activate` | ADMIN | - | `String` |
| PUT | `/api/users/{id}/deactivate` | ADMIN | - | `String` |
| PUT | `/api/users/{id}/suspended` | ADMIN | - | `String` |
| PATCH | `/api/users/{id}/status?status=...` | ADMIN | - | `String` |

`Member / Trainer Profile`

| Method | Route | Auth | Request Body | Success Response |
|---|---|---|---|---|
| GET | `/api/members/me` | MEMBER | - | `MemberResponse` |
| PUT | `/api/members/me/profile` | MEMBER | `MemberProfileRequest` | `MemberResponse` |
| GET | `/api/trainers/me` | TRAINER | - | `TrainerResponse` |
| PUT | `/api/trainers/me/profile` | TRAINER | `TrainerProfileRequest` | `TrainerResponse` |

`Categories`

| Method | Route | Auth | Request Body | Success Response |
|---|---|---|---|---|
| GET | `/api/categories` | ADMIN/TRAINER/MEMBER | - | `List<CategoryResponse>` |
| GET | `/api/categories/manage` | ADMIN | Query: `keyword,status,page,size` | `Page<CategoryResponse>` |
| POST | `/api/categories` | ADMIN | `CategoryCreateRequest` | `CategoryResponse` |
| PUT | `/api/categories/{id}` | ADMIN | `CategoryUpdateRequest` | `CategoryResponse` |

`Courses + Course Contents`

| Method | Route | Auth | Request Body | Success Response |
|---|---|---|---|---|
| GET | `/api/courses` | ADMIN/TRAINER/MEMBER | Query: `page,size` | `Page<CourseResponse>` |
| GET | `/api/courses/categories/{categoryId}` | ADMIN/TRAINER/MEMBER | Query: `page,size` | `Page<CourseResponse>` |
| POST | `/api/courses` | ADMIN | `multipart/form-data` (`data`, `thumbnail`) | `CourseResponse` |
| PUT | `/api/courses/{id}` | ADMIN | `multipart/form-data` (`data`, `thumbnail`) | `CourseResponse` |
| PATCH | `/api/courses/{id}/deactivate` | ADMIN | - | `204 No Content` |
| GET | `/api/{courseId}/contents` | ADMIN/TRAINER/MEMBER | - | `List<CourseContentResponse>` |
| POST | `/api/course-contents` | ADMIN | `multipart/form-data` (`data`, `file`) | `CourseContentResponse` |
| DELETE | `/api/course-contents/{id}` | ADMIN | - | `204 No Content` |

`Classes + Bookings`

| Method | Route | Auth | Request Body | Success Response |
|---|---|---|---|---|
| GET | `/api/classes` | ADMIN/TRAINER/MEMBER | - | `List<ClassResponse>` |
| GET | `/api/classes/me` | TRAINER | - | `List<ClassResponse>` |
| GET | `/api/classes/manage` | ADMIN | - | `List<ClassResponse>` |
| POST | `/api/classes` | ADMIN/TRAINER | `ClassRequest` | `ClassResponse` |
| PUT | `/api/classes/{id}` | ADMIN/TRAINER | `ClassRequest` | `ClassResponse` |
| DELETE | `/api/classes/{id}` | ADMIN/TRAINER | - | `String` |
| GET | `/api/class-bookings/me` | MEMBER | - | `List<ClassBookingResponse>` |
| PATCH | `/api/class-bookings/{bookingId}/status/cancel` | MEMBER | - | `String` |
| GET | `/api/class-bookings/classes/{classId}` | ADMIN/TRAINER | - | `List<ClassBookingResponse>` |
| PATCH | `/api/class-bookings/{bookingId}/attendance` | ADMIN/TRAINER | - | `String` |
| GET | `/api/class-bookings/manage/classes/{classId}` | ADMIN | - | `List<ClassBookingResponse>` |

`Orders + Payments`

| Method | Route | Auth | Request Body | Success Response |
|---|---|---|---|---|
| POST | `/api/orders` | MEMBER | `OrderCreateRequest` | `Orders` |
| GET | `/api/orders` | MEMBER | - | `List<Orders>` |
| GET | `/api/orders/{orderId}/payments` | MEMBER | - | `List<Payment>` |
| GET | `/api/orders/manage` | ADMIN | - | `List<Orders>` |
| GET | `/api/orders/manage/{id}` | ADMIN | - | `Orders` |
| GET | `/api/orders/manage/payments` | ADMIN | - | `List<Payment>` |
| GET | `/api/orders/manage/{orderId}/payments` | ADMIN | - | `List<Payment>` |
| POST | `/api/orders/{orderId}/payments` | MEMBER | `PaymentRequest` | `String` |

`Membership`

| Method | Route | Auth | Request Body | Success Response |
|---|---|---|---|---|
| GET | `/api/membership-plans` | ADMIN/TRAINER/MEMBER | - | `List<MembershipPlanResponse>` |
| POST | `/api/membership-plans` | ADMIN | `MembershipPlanRequest` | `MembershipPlanResponse` |
| PUT | `/api/membership-plans/{id}` | ADMIN | `MembershipPlanRequest` | `MembershipPlanResponse` |
| DELETE | `/api/membership-plans/{id}` | ADMIN | - | `void` |
| GET | `/api/membership-subscriptions/members/{memberId}` | ADMIN/MEMBER | - | `List<MembershipSubscriptionResponse>` |
| PATCH | `/api/membership-subscriptions/{subscriptionId}/status?status=...` | ADMIN | - | `void` |

`Workout`

| Method | Route | Auth | Request Body | Success Response |
|---|---|---|---|---|
| GET | `/api/workout-plans/manage` | ADMIN | - | `List<WorkoutPlanResponse>` |
| POST | `/api/workout-plans` | ADMIN/TRAINER | `WorkoutPlanRequest` | `WorkoutPlanResponse` |
| PUT | `/api/workout-plans/{planId}` | ADMIN/TRAINER | `WorkoutPlanRequest` | `WorkoutPlanResponse` |
| DELETE | `/api/workout-plans/{planId}` | ADMIN/TRAINER | - | `String` |
| GET | `/api/workout-plans/trainers/{trainerUserId}` | ADMIN/TRAINER | - | `List<WorkoutPlanResponse>` |
| GET | `/api/workout-plans/members/{memberId}` | ADMIN/TRAINER/MEMBER | - | `List<WorkoutPlanResponse>` |
| POST | `/api/workout-plans/{planId}/schedules` | ADMIN/TRAINER | `WorkoutScheduleRequest` | `WorkoutScheduleResponse` |
| PUT | `/api/workout-schedules/{id}` | ADMIN/TRAINER | `WorkoutScheduleRequest` | `WorkoutScheduleResponse` |
| DELETE | `/api/workout-schedules/{id}` | ADMIN/TRAINER | - | `void` |
| GET | `/api/workout-plans/{planId}/schedules` | ADMIN/TRAINER/MEMBER | - | `List<WorkoutScheduleResponse>` |
| POST | `/api/workout-assignments` | ADMIN/TRAINER | `MemberWorkoutAssignRequest` | `MemberWorkoutAssignResponse` |
| PUT | `/api/workout-assignments/{id}` | ADMIN/TRAINER | `MemberWorkoutAssignRequest` | `MemberWorkoutAssignResponse` |
| DELETE | `/api/workout-assignments/{id}` | ADMIN/TRAINER | - | `void` |
| GET | `/api/workout-assignments/members/{memberId}` | ADMIN/TRAINER/MEMBER | - | `List<MemberWorkoutAssignResponse>` |

`Notifications`

| Method | Route | Auth | Request Body | Success Response |
|---|---|---|---|---|
| POST | `/api/notifications` | ADMIN | `NotificationRequest` | `NotificationResponse` |
| POST | `/api/notifications/broadcast` | ADMIN | `NotificationRequest` | `void` |
| GET | `/api/notifications/users/{userId}` | ADMIN/TRAINER/MEMBER | - | `List<NotificationResponse>` |
| PATCH | `/api/notifications/{notificationId}/read` | ADMIN/TRAINER/MEMBER | - | `void` |

`Attendance + Enrollment + Files`

| Method | Route | Auth | Request Body | Success Response |
|---|---|---|---|---|
| POST | `/api/attendance/check-ins` | MEMBER | `AttendanceRequest` | `AttendanceResponse` |
| PATCH | `/api/attendance/{id}/check-out` | MEMBER | - | `AttendanceResponse` |
| GET | `/api/attendance/members/{memberId}` | ADMIN/TRAINER/MEMBER | - | `List<AttendanceResponse>` |
| GET | `/api/attendance` | ADMIN | - | `List<AttendanceResponse>` |
| GET | `/api/enrollments/me` | MEMBER | - | Enrollment list payload |
| POST | `/api/files` | ADMIN/TRAINER/MEMBER | `multipart/form-data` (`file`, `type`) | `FileUploadResponse` |

`Income (Admin)`

| Method | Route | Auth | Request Body | Success Response |
|---|---|---|---|---|
| GET | `/api/income/manage/summary` | ADMIN | Query: `from,to` (`yyyy-MM-dd`) | `IncomeSummaryResponse` |
| GET | `/api/income/manage/by-type` | ADMIN | Query: `from,to` | `List<IncomeByTypeResponse>` |
| GET | `/api/income/manage/daily` | ADMIN | Query: `from,to` | `List<IncomeByDateResponse>` |
| GET | `/api/income/manage/monthly` | ADMIN | Query: `from,to` | `List<IncomeByDateResponse>` |

## Postman Contract Versioning

- Source collection: `postman/gym_management.postman_collection.json`
- Source environment: `postman/gym_management.postman_environment.json`
- Rule:
  - Update `info.description` and `info.version` in collection after API changes.
  - Commit API code + `API_CONTRACT.md` + Postman collection in the same PR/commit.

## Optional OpenAPI (Swagger)

To auto-sync docs in future:

1. Add `springdoc-openapi-starter-webmvc-ui` dependency.
2. Expose `/swagger-ui.html` and `/v3/api-docs`.
3. Annotate endpoints/DTOs incrementally for richer docs.
