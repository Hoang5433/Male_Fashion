CREATE DATABASE male_fashion;
USE male_fashion;
-- Tạo bảng Category
CREATE TABLE Category (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          parentCategoryId BIGINT DEFAULT NULL,
                          createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (parentCategoryId) REFERENCES Category(id)
);

-- Tạo bảng Brand
CREATE TABLE Brand (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       description TEXT,
                       logoUrl VARCHAR(255),
                       createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng Product
CREATE TABLE Product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         price BIGINT NOT NULL,
                         stockQuantity INT NOT NULL,
                         imageUrl VARCHAR(255),
                         rating INT CHECK (rating BETWEEN 0 AND 5),
                         categoryId BIGINT NOT NULL,
                         brandId BIGINT NOT NULL,
                         isActive BOOLEAN DEFAULT TRUE,
                         createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         FOREIGN KEY (categoryId) REFERENCES Category(id),
                         FOREIGN KEY (brandId) REFERENCES Brand(id)
);

-- Tạo bảng Color
CREATE TABLE Color (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       colorName VARCHAR(255) NOT NULL,
                       colorHex VARCHAR(7) NOT NULL,
                       imageUrl VARCHAR(255),
                       createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng Tag
CREATE TABLE Tag (
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     tagName VARCHAR(255) NOT NULL,
                     description TEXT,
                     createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                     updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng Size
CREATE TABLE Size (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      sizeName VARCHAR(50) NOT NULL,
                      description TEXT,
                      createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng ProductColor
CREATE TABLE ProductColor (
                              product_id BIGINT NOT NULL,
                              color_id BIGINT NOT NULL,
                              createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              PRIMARY KEY (product_id, color_id),
                              FOREIGN KEY (product_id) REFERENCES Product(id),
                              FOREIGN KEY (color_id) REFERENCES Color(id)
);

-- Tạo bảng ProductTag
CREATE TABLE ProductTag (
                            product_id BIGINT NOT NULL,
                            tag_id BIGINT NOT NULL,
                            createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (product_id, tag_id),
                            FOREIGN KEY (product_id) REFERENCES Product(id),
                            FOREIGN KEY (tag_id) REFERENCES Tag(id)
);

-- Tạo bảng ProductSize
CREATE TABLE ProductSize (
                             product_id BIGINT NOT NULL,
                             size_id BIGINT NOT NULL,
                             createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             PRIMARY KEY (product_id, size_id),
                             FOREIGN KEY (product_id) REFERENCES Product(id),
                             FOREIGN KEY (size_id) REFERENCES Size(id)
);
