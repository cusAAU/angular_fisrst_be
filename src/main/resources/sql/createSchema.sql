CREATE TABLE IF NOT EXISTS owner
(
  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  created_at DATETIME     NOT NULL,
  updated_at DATETIME     NOT NULL
);