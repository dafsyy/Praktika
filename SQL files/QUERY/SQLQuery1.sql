CREATE TABLE Product (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(200) NOT NULL,
    Description NVARCHAR(500) NOT NULL,
    Price DECIMAL(10,2) NOT NULL
);

CREATE TABLE [Order] (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    CustomerName NVARCHAR(200),
    Phone NVARCHAR(50),
    Address NVARCHAR(300),
    CreatedAt DATETIME,
    Status NVARCHAR(50)
);

CREATE TABLE OrderItem (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    OrderId INT NOT NULL,
    ProductId INT NOT NULL,
    Quantity INT NOT NULL,
    PriceAtOrder DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (OrderId) REFERENCES [Order](Id),
    FOREIGN KEY (ProductId) REFERENCES Product(Id)
);

INSERT INTO Product (Name, Description, Price) VALUES
(N'Американо', N'Крепкий черный кофе', 3.0),
(N'Капучино', N'Кофе с молочной пеной', 3.5),
(N'Латте', N'Кофе с молоком', 4.0),
(N'Экспрессо', N'Кофе с молоком', 2.5),
(N'Макиато', N'Кофе с молоком', 3.2),
(N'Флэт Уайт', N'Кофе с молоком', 3.8);