CREATE TABLE tareas (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id   BIGINT       NOT NULL,
    titulo      VARCHAR(255) NOT NULL,
    descripcion TEXT,
    prioridad   VARCHAR(10)  NOT NULL DEFAULT 'MEDIUM',
    start_date  DATE,
    due_date    DATE         NOT NULL,
    due_time    VARCHAR(5),
    end_date    DATE,
    estado      VARCHAR(20)  NOT NULL DEFAULT 'PENDING',
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_tareas_course FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE,
    INDEX idx_tareas_course_id (course_id),
    INDEX idx_tareas_due_date  (due_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

