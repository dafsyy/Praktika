-- USERS
CREATE TABLE Users (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NOT NULL UNIQUE,
    PasswordHash NVARCHAR(200) NOT NULL,
    CreatedAt DATETIME2 NOT NULL DEFAULT GETDATE()
);

-- CATEGORY
CREATE TABLE Categories (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL
);

-- PRODUCT (обновлён с CategoryId)
CREATE TABLE Products (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(150) NOT NULL,
    Description NVARCHAR(MAX) NULL,
    Price DECIMAL(10,2) NOT NULL,
    ImageUrl NVARCHAR(300) NULL,
    CategoryId INT NOT NULL,

    CONSTRAINT FK_Products_Categories
        FOREIGN KEY (CategoryId)
        REFERENCES Categories(Id)
);

-- CART
CREATE TABLE Carts (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT NOT NULL,
    CreatedAt DATETIME2 NOT NULL DEFAULT GETDATE(),

    CONSTRAINT FK_Carts_Users
        FOREIGN KEY (UserId)
        REFERENCES Users(Id)
);

-- CART ITEM
CREATE TABLE CartItems (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    CartId INT NOT NULL,
    ProductId INT NOT NULL,
    Quantity INT NOT NULL DEFAULT 1,

    CONSTRAINT FK_CartItems_Carts
        FOREIGN KEY (CartId)
        REFERENCES Carts(Id),

    CONSTRAINT FK_CartItems_Products
        FOREIGN KEY (ProductId)
        REFERENCES Products(Id)
);

-- ORDER
CREATE TABLE Orders (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT NOT NULL,
    CustomerName NVARCHAR(150) NOT NULL,
    Phone NVARCHAR(50) NOT NULL,
    Address NVARCHAR(300) NOT NULL,
    CreatedAt DATETIME2 NOT NULL DEFAULT GETDATE(),
    Status NVARCHAR(50) NOT NULL,

    CONSTRAINT FK_Orders_Users
        FOREIGN KEY (UserId)
        REFERENCES Users(Id)
);

-- ORDER ITEM
CREATE TABLE OrderItems (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    OrderId INT NOT NULL,
    ProductId INT NOT NULL,
    Quantity INT NOT NULL,
    PriceAtOrder DECIMAL(10,2) NOT NULL,

    CONSTRAINT FK_OrderItems_Orders
        FOREIGN KEY (OrderId)
        REFERENCES Orders(Id),

    CONSTRAINT FK_OrderItems_Products
        FOREIGN KEY (ProductId)
        REFERENCES Products(Id)
);