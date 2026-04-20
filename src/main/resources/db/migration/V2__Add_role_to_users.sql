-- Migración: agrega el campo role a la tabla users
ALTER TABLE users
    ADD COLUMN role VARCHAR(20) NOT NULL DEFAULT 'STUDENT';

CREATE INDEX idx_users_role ON users (role);
