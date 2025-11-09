-- Insert authors
INSERT INTO author (id, birth_date, death_date, name, nationality, surname)
VALUES
(1, '1775-12-16', '1817-07-18', 'Jane', 'British', 'Austen'),
(2, '1947-09-21', NULL, 'Stephen', 'American', 'King'),
(3, '1903-06-25', '1950-01-21', 'George', 'British', 'Orwell'),
(4, '1965-07-31', NULL, 'J.K.', 'British', 'Rowling'),
(5, '1899-07-21', '1961-07-02', 'Ernest', 'American', 'Hemingway');

-- Insert books
INSERT INTO book (id, genre, release_date, title, author_id)
VALUES
(1, 'Romance', '1813-01-28', 'Pride and Prejudice', 1),
(2, 'Romance', '1815-12-23', 'Emma', 1),
(3, 'Horror', '1977-01-28', 'The Shining', 2),
(4, 'Horror', '1986-09-15', 'It', 2),
(5, 'Science Fiction', '1949-06-08', '1984', 3),
(6, 'Political Fiction', '1945-08-17', 'Animal Farm', 3),
(7, 'Fantasy', '1997-06-26', 'Harry Potter and the Philosopher''s Stone', 4),
(8, 'Fantasy', '1998-07-02', 'Harry Potter and the Chamber of Secrets', 4),
(9, 'Fiction', '1926-10-22', 'The Sun Also Rises', 5),
(10, 'Fiction', '1952-09-01', 'The Old Man and the Sea', 5);