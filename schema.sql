CREATE DATABASE gym_db;

CREATE TYPE user_role AS ENUM ('Admin', 'Trainer', 'Member');

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    role user_role NOT NULL
);

CREATE TABLE memberships (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    membership_type VARCHAR(50) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE workout_classes (
    id SERIAL PRIMARY KEY,
    class_name VARCHAR(100) NOT NULL,
    schedule VARCHAR(100) NOT NULL,
    trainer_id INT REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE merchandise (
    id SERIAL PRIMARY KEY,
    item_name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    stock_quantity INT NOT NULL DEFAULT 0
);
