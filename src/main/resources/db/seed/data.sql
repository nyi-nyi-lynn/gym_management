-- =========================================================
-- Gym Management - Local Seed Data
-- Profile: seed
-- Purpose: deterministic data for frontend/manual QA
-- =========================================================

SET FOREIGN_KEY_CHECKS = 0;

-- Backward compatibility for older local DBs where trainers.status
-- was created as numeric with a CHECK constraint.
SET @drop_trainers_chk = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.TABLE_CONSTRAINTS
            WHERE CONSTRAINT_SCHEMA = DATABASE()
              AND TABLE_NAME = 'trainers'
              AND CONSTRAINT_NAME = 'trainers_chk_1'
        ),
        'ALTER TABLE trainers DROP CHECK trainers_chk_1',
        'SELECT 1'
    )
);
PREPARE stmt_drop_trainers_chk FROM @drop_trainers_chk;
EXECUTE stmt_drop_trainers_chk;
DEALLOCATE PREPARE stmt_drop_trainers_chk;

SET @fix_trainers_status_type = (
    SELECT IF(
        EXISTS(
            SELECT 1
            FROM information_schema.COLUMNS
            WHERE TABLE_SCHEMA = DATABASE()
              AND TABLE_NAME = 'trainers'
              AND COLUMN_NAME = 'status'
              AND DATA_TYPE IN ('tinyint', 'smallint', 'int', 'bigint')
        ),
        'ALTER TABLE trainers MODIFY COLUMN status VARCHAR(255)',
        'SELECT 1'
    )
);
PREPARE stmt_fix_trainers_status_type FROM @fix_trainers_status_type;
EXECUTE stmt_fix_trainers_status_type;
DEALLOCATE PREPARE stmt_fix_trainers_status_type;

-- Reset only seed ID range to keep IDs stable but preserve non-seed data.
DELETE FROM payments WHERE id BETWEEN 1 AND 200;
DELETE FROM orders WHERE id BETWEEN 1 AND 200;
DELETE FROM workout_plans WHERE id BETWEEN 1 AND 200;
DELETE FROM courses WHERE id BETWEEN 1 AND 200;
DELETE FROM classes WHERE id BETWEEN 1 AND 200;
DELETE FROM membership_plans WHERE id BETWEEN 1 AND 200;
DELETE FROM categories WHERE id BETWEEN 1 AND 200;
DELETE FROM members WHERE id BETWEEN 1 AND 200;
DELETE FROM trainers WHERE id BETWEEN 1 AND 200;
DELETE FROM users WHERE id BETWEEN 1 AND 200;

-- Also reset by seed emails in case legacy test data used different IDs.
DELETE FROM members
WHERE user_id IN (
    SELECT id FROM users
    WHERE email IN (
        'admin@gym.local',
        'trainer1@gym.local',
        'trainer2@gym.local',
        'member1@gym.local',
        'member2@gym.local',
        'member3@gym.local'
    )
);

DELETE FROM trainers
WHERE user_id IN (
    SELECT id FROM users
    WHERE email IN (
        'admin@gym.local',
        'trainer1@gym.local',
        'trainer2@gym.local',
        'member1@gym.local',
        'member2@gym.local',
        'member3@gym.local'
    )
);

DELETE FROM users
WHERE email IN (
    'admin@gym.local',
    'trainer1@gym.local',
    'trainer2@gym.local',
    'member1@gym.local',
    'member2@gym.local',
    'member3@gym.local'
);

-- Seed users (password for all users: password)
-- Stored as {noop}password for deterministic local login.
INSERT INTO users (
    id, name, email, password, phone_number, address, gender, date_of_birth, role, status, created_at, updated_at
) VALUES
    (1,  'System Admin', 'admin@gym.local',    '{noop}password', '0910000001', 'Yangon',    'MALE',   '1990-01-01', 'ADMIN',   'ACTIVE', NOW(), NOW()),
    (2,  'Trainer One',  'trainer1@gym.local', '{noop}password', '0910000002', 'Yangon',    'FEMALE', '1992-03-15', 'TRAINER', 'ACTIVE', NOW(), NOW()),
    (3,  'Trainer Two',  'trainer2@gym.local', '{noop}password', '0910000003', 'Mandalay',  'MALE',   '1991-07-21', 'TRAINER', 'ACTIVE', NOW(), NOW()),
    (10, 'Member One',   'member1@gym.local',  '{noop}password', '0910000010', 'Yangon',    'MALE',   '2000-05-10', 'MEMBER',  'ACTIVE', NOW(), NOW()),
    (11, 'Member Two',   'member2@gym.local',  '{noop}password', '0910000011', 'Bago',      'FEMALE', '1999-09-09', 'MEMBER',  'ACTIVE', NOW(), NOW()),
    (12, 'Member Three', 'member3@gym.local',  '{noop}password', '0910000012', 'Naypyitaw', 'OTHER',  '2001-12-20', 'MEMBER',  'ACTIVE', NOW(), NOW());

INSERT INTO trainers (
    id, user_id, specialization, experience_years, bio, status, start_work_date, profile_completed
) VALUES
    (1, 2, 'Strength', 6, 'Strength and conditioning coach', 'ACTIVE', '2022-01-01', 1),
    (2, 3, 'Yoga',     5, 'Yoga and mobility trainer',       'ACTIVE', '2022-06-01', 1);

INSERT INTO members (
    id, user_id, join_date, height, weight, status, profile_completed
) VALUES
    (1, 10, '2025-01-15', 172.0, 68.0, 'ACTIVE',   1),
    (2, 11, '2025-02-10', 160.0, 56.0, 'ACTIVE',   1),
    (3, 12, '2025-03-01', 168.0, 62.0, 'INACTIVE', 0);

INSERT INTO categories (
    id, name, description, status, created_at, updated_at
) VALUES
    (1, 'Strength', 'Strength training programs', 'ACTIVE', NOW(), NOW()),
    (2, 'Yoga',     'Yoga and mobility classes',  'ACTIVE', NOW(), NOW()),
    (3, 'Cardio',   'Cardio and endurance',       'ACTIVE', NOW(), NOW());

INSERT INTO membership_plans (
    id, name, price, duration_months, description, status
) VALUES
    (1, 'Basic Monthly',      50000.00, 1, 'Gym access monthly',      'ACTIVE'),
    (2, 'Premium Quarterly', 130000.00, 3, 'Gym + classes quarterly', 'ACTIVE');

INSERT INTO classes (
    id, name, category_id, trainer_id, price, capacity, duration, status, created_at
) VALUES
    (1, 'Morning Strength', 1, 1, 8000.00, 20, 60, 'ACTIVE', NOW()),
    (2, 'Evening Yoga',     2, 2, 6000.00, 25, 60, 'ACTIVE', NOW());

INSERT INTO courses (
    id, category_id, trainer_id, title, description, price, duration_days, level, thumbnail_url, status, created_at
) VALUES
    (1, 1, 1, 'Strength Fundamentals', 'Basic strength course', 45000.00, 30, 'BEGINNER', NULL, 'ACTIVE', NOW()),
    (2, 2, 2, 'Yoga for Beginners',    'Basic yoga course',     40000.00, 21, 'BEGINNER', NULL, 'ACTIVE', NOW());

INSERT INTO workout_plans (
    id, trainer_id, title, description, status, created_at
) VALUES
    (1, 1, 'Fat Loss 4 Weeks', 'Beginner-friendly fat loss plan', 'ACTIVE', NOW()),
    (2, 2, 'Mobility Boost',   'Flexibility and mobility plan',   'ACTIVE', NOW());

INSERT INTO orders (
    id, user_id, order_type, reference_id, total_amount, payment_status, created_at
) VALUES
    (1, 10, 'MEMBERSHIP', 1, 50000.00, 'PAID', NOW()),
    (2, 11, 'COURSE',     1, 45000.00, 'PAID', NOW()),
    (3, 10, 'CLASS',      1,  8000.00, 'PAID', NOW());

INSERT INTO payments (
    id, order_id, amount, payment_method, transaction_id, payment_status, payment_date, receipt_url
) VALUES
    (1, 1, 50000.00, 'KBZPAY',  'TXN-SEED-0001', 'PAID', NOW(), NULL),
    (2, 2, 45000.00, 'WAVEPAY', 'TXN-SEED-0002', 'PAID', NOW(), NULL),
    (3, 3,  8000.00, 'CASH',    'TXN-SEED-0003', 'PAID', NOW(), NULL);

SET FOREIGN_KEY_CHECKS = 1;




