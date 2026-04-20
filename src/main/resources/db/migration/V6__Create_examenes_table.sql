CREATE TABLE examenes (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id    BIGINT       NOT NULL,
    titulo       VARCHAR(255) NOT NULL,
    tema         VARCHAR(255) NOT NULL DEFAULT '',
    puntaje      DOUBLE,
    fecha        DATE         NOT NULL,
    hora_inicio  VARCHAR(5),
    hora_fin     VARCHAR(5),
    duracion     INT,
    estado       VARCHAR(20)  NOT NULL DEFAULT 'PENDING',
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_examenes_course FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE,
    INDEX idx_examenes_course_id (course_id),
    INDEX idx_examenes_fecha     (fecha)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

