use `airline-management-system`;

CREATE TABLE login (
    username VARCHAR(20),
    password VARCHAR(20)
);

INSERT INTO login VALUES ('admin', 'admin');

CREATE TABLE passenger (
    Name VARCHAR(20),
    Nationality VARCHAR(20),
    Phone VARCHAR(15),
    Address VARCHAR(50),
    Aadhar VARCHAR(20),
    Gender VARCHAR(20)
);

CREATE TABLE flight (
    f_code VARCHAR(20),
    f_name VARCHAR(20),
    source VARCHAR(40),
    destination VARCHAR(40)
);

INSERT INTO flight VALUES
('1001', 'Air India 1212', 'Delhi', 'Mumbai'),
('1002', 'IndiGo 2201', 'Delhi', 'Goa'),
('1003', 'SpiceJet 1112', 'Mumbai', 'Chennai'),
('1004', 'Vistara 3222', 'Delhi', 'Amritsar'),
('1005', 'Air India 4500', 'Delhi', 'Ayodhya');