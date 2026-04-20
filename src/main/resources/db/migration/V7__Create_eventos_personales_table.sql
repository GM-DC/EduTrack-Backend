CREATE TABLE eventos_personales (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT       NOT NULL,
    titulo      VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fecha       DATE         NOT NULL,
    hora_inicio VARCHAR(5),
    hora_fin    VARCHAR(5),
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_eventos_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    INDEX idx_eventos_user_id (user_id),
    INDEX idx_eventos_fecha   (fecha)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

