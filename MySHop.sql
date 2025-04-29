-- Create the database
CREATE DATABASE MyShop;
GO

-- Use the newly created database
USE MyShop;
GO

-- Create the Mobiles table
CREATE TABLE Mobiles (
    mobileId VARCHAR(10) PRIMARY KEY,
    description NVARCHAR(255),
    price FLOAT,
    mobileName NVARCHAR(100),
    yearOfProduction INT,
    quantity INT,
    notSale BIT
);
GO

-- Create the Users table
CREATE TABLE Users (
    userId VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50),
    fullName NVARCHAR(100),
    role INT -- 1 for admin, 0 for normal user
);
GO
-- Insert data into Mobiles table
INSERT INTO Mobiles (mobileId, description, price, mobileName, yearOfProduction, quantity, notSale)
VALUES 
('M001', 'xiaomi phone', 200, 'Galaxy A', 2020, 60, 1),
('M002', 'High-end model', 999.9899, 'iPhone 14', 2023, 40, 0),
('M003', 'Budget phone', 149.5, 'Redmi 10', 2021, 100, 1),
('M004', 'Affordable phone', 120, 'Realme C', 2022, 75, 0);

-- Insert data into Users table
INSERT INTO Users (userId, password, fullName, role)
VALUES
('tri', '123', 'tri minh', 1),
('user01', '123', 'Minh tri', 0),
('user02', '111', 'tri dep trai', 0);
