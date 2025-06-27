-- 1. Create the database
CREATE DATABASE IF NOT EXISTS interview_master
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_0900_ai_ci;

USE interview_master;

-- 2. Create a table for questions
CREATE TABLE IF NOT EXISTS questions (
  id INT AUTO_INCREMENT PRIMARY KEY,
  question_text TEXT NOT NULL,
  category VARCHAR(50) NOT NULL,
  difficulty VARCHAR(20) NOT NULL
);

-- 3. (Optional) Create a results table
CREATE TABLE IF NOT EXISTS results (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100),
  score INT,
  interview_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. Insert sample questions
INSERT INTO questions (question_text, category, difficulty) VALUES
('What is polymorphism in Java?', 'Technical', 'Medium'),
('Tell me about a time you solved a conflict.', 'Behavioral', 'Easy'),
('Explain JDBC architecture.', 'Technical', 'Hard');
