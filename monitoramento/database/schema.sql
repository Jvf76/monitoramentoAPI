CREATE DATABASE IF NOT EXISTS monitoramento_industrial
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE monitoramento_industrial;

CREATE TABLE IF NOT EXISTS leituras (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        data_hora DATETIME(6) NOT NULL,
    temperatura DOUBLE NOT NULL,
    eficiencia DOUBLE NOT NULL,
    anormal BOOLEAN NOT NULL
    );

CREATE INDEX idx_leituras_data_hora
    ON leituras (data_hora);