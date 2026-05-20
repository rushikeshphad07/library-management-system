INSERT INTO user (name, email, password, role)
VALUES
('Rushikesh', 'rushikesh@gmail.com', 'admin123', 'ADMIN'),
('CShubham', 'cshubham@gmailc.com', 'shubham123', 'USER'),
('Abhijit', 'abhijit@gmail.com', 'abhijit@123', 'USER');


INSERT INTO books (title, author, isbn, quantity, available_quantity)
VALUES
('Clean Code', 'Robert C. Martin', '9780132350884', 5, 5),
('Effective Java', 'Joshua Bloch', '9780134685991', 4, 3),
('Head First Java', 'Kathy Sierra', '9780596009205', 6, 4),
('Java: The Complete Reference', 'Herbert Schildt', '9781260440232', 3, 2);


INSERT INTO issue_records 
(user_id, book_id, issue_date, due_date, return_date, status)
VALUES
(2, 2, '2026-05-15', '2026-05-22', NULL, 'ISSUED'),
(3, 3, '2026-05-10', '2026-05-17', '2026-05-16', 'RETURNED'),
(2, 4, '2026-05-01', '2026-05-08', NULL, 'LATE');