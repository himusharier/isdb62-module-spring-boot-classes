-- Initial data for student table
INSERT INTO students (first_name, last_name, email, date_of_birth, enrollment_date)
VALUES 
('John', 'Doe', 'john.doe@example.com', '2000-01-15', CURRENT_TIMESTAMP),
('Jane', 'Smith', 'jane.smith@example.com', '2001-05-20', CURRENT_TIMESTAMP),
('Michael', 'Johnson', 'michael.johnson@example.com', '1999-11-10', CURRENT_TIMESTAMP),
('Emily', 'Williams', 'emily.williams@example.com', '2002-03-25', CURRENT_TIMESTAMP),
('David', 'Brown', 'david.brown@example.com', '2000-07-08', CURRENT_TIMESTAMP);
