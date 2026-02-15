# Seed Data Guide

This project includes deterministic local seed data for frontend QA.

## Files

- Profile: `src/main/resources/application-seed.properties`
- Seed SQL: `src/main/resources/db/seed/data.sql`
- Quick run script: `scripts/run-seed.ps1`

## Seeded Tables

- `users`
- `trainers`
- `members`
- `categories`
- `membership_plans`
- `classes`
- `courses`
- `workout_plans`
- `orders`
- `payments`

## Stable IDs

- Admin user: `id=1`
- Trainer users: `id=2,3`
- Member users: `id=10,11,12`
- Trainers: `id=1,2`
- Members: `id=1,2,3`
- Membership plans: `id=1,2`
- Categories: `id=1,2,3`
- Classes: `id=1,2`
- Courses: `id=1,2`
- Workout plans: `id=1,2`
- Orders: `id=1,2,3`
- Payments: `id=1,2,3`

## Seed Credentials

All seed users use:

- Password: `password`

Emails:

- `admin@gym.local`
- `trainer1@gym.local`
- `trainer2@gym.local`
- `member1@gym.local`
- `member2@gym.local`
- `member3@gym.local`

## Run Seed

PowerShell:

```powershell
./scripts/run-seed.ps1
```

Manual:

```powershell
$env:SPRING_PROFILES_ACTIVE="seed"
./mvnw spring-boot:run
```

## Notes

- Seed deletes only `id` range `1..200` in seeded tables before re-inserting.
- Legacy local schemas with numeric `trainers.status` are auto-adjusted in the seed SQL.
- If startup fails with `Port 8080 was already in use`, stop existing app instance and rerun.
