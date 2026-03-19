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

DROP TABLE IF EXISTS flight;

CREATE TABLE flight (
    f_code VARCHAR(20) PRIMARY KEY,
    source VARCHAR(40),
    destination VARCHAR(40),
    departure VARCHAR(10),
    arrival VARCHAR(10),
    duration VARCHAR(20),
    price VARCHAR(20)
);

INSERT INTO flight VALUES
('VN101', 'Hà Nội', 'TP. Hồ Chí Minh', '08:00', '10:30', '2h 30p', '1500000'),
('VN102', 'TP. Hồ Chí Minh', 'Đà Nẵng', '11:00', '13:00', '2h 00p', '1200000'),
('VN103', 'Đà Nẵng', 'Nha Trang', '14:00', '15:30', '1h 30p', '900000'),
('VN104', 'Nha Trang', 'Hà Nội', '16:00', '19:00', '3h 00p', '1800000'),
('VN105', 'Hà Nội', 'Phú Quốc', '20:00', '22:30', '2h 30p', '1600000');