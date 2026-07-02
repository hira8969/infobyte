CREATE DATABASE IF NOT EXISTS digital_library;
USE digital_library;

CREATE TABLE roles (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  full_name VARCHAR(120) NOT NULL,
  email VARCHAR(160) NOT NULL UNIQUE,
  username VARCHAR(80) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  phone_number VARCHAR(20),
  active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_roles (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE books (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  author VARCHAR(255) NOT NULL,
  isbn VARCHAR(40) NOT NULL UNIQUE,
  category VARCHAR(80) NOT NULL,
  quantity INT NOT NULL,
  available_quantity INT NOT NULL,
  publisher VARCHAR(255),
  publication_year INT,
  description VARCHAR(2000),
  CHECK (quantity >= 0),
  CHECK (available_quantity >= 0),
  CHECK (available_quantity <= quantity)
);

CREATE TABLE issue_records (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  book_id BIGINT NOT NULL,
  issue_date DATE NOT NULL,
  due_date DATE NOT NULL,
  return_date DATE,
  status VARCHAR(30) NOT NULL,
  CONSTRAINT fk_issue_user FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_issue_book FOREIGN KEY (book_id) REFERENCES books(id)
);

CREATE TABLE fines (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  issue_record_id BIGINT NOT NULL UNIQUE,
  amount DECIMAL(10,2) NOT NULL,
  overdue_days BIGINT NOT NULL,
  status VARCHAR(30) NOT NULL,
  paid_date DATE,
  CONSTRAINT fk_fine_issue FOREIGN KEY (issue_record_id) REFERENCES issue_records(id)
);

CREATE TABLE reservations (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  book_id BIGINT NOT NULL,
  reservation_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status VARCHAR(30) NOT NULL,
  CONSTRAINT fk_reservation_user FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_reservation_book FOREIGN KEY (book_id) REFERENCES books(id)
);

CREATE TABLE contact_queries (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(120) NOT NULL,
  email VARCHAR(160) NOT NULL,
  subject VARCHAR(160) NOT NULL,
  message VARCHAR(3000) NOT NULL,
  response VARCHAR(3000),
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE audit_logs (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  actor_username VARCHAR(80),
  action VARCHAR(120) NOT NULL,
  entity_name VARCHAR(120),
  entity_id BIGINT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT IGNORE INTO roles(name) VALUES ('ADMIN'), ('USER');
