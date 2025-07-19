CREATE DATABASE male_fashion;
USE male_fashion;

-- Tạo bảng Category
CREATE TABLE Category (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng Branda
CREATE TABLE Brand (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       description TEXT,
                       createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng Product
CREATE TABLE Product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         price FLOAT NOT NULL,
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

-- Dữ liệu mẫu
INSERT INTO Color (colorName, colorHex) VALUES
                                            ('Đen Xám', '#0b090c'),
                                            ('Xanh Đậm', '#20315f'),
                                            ('Vàng Cam', '#f1af4d'),
                                            ('Xám Sẫm', '#636068'),
                                            ('Xanh Rêu Xám', '#57594d'),
                                            ('Hồng Nhạt', '#e8bac4'),
                                            ('Tím Nhạt', '#d6c1d7'),
                                            ('Đỏ Tươi', '#ed1c24'),
                                            ('Trắng Tinh', '#ffffff');

INSERT INTO Category (name, description) VALUES
                                             ('Men', 'Thời trang nam'),
                                             ('Women', 'Thời trang nữ'),
                                             ('Bags', 'Các loại túi xách'),
                                             ('Clothing', 'Quần áo các loại'),
                                             ('Shoes', 'Giày dép các loại'),
                                             ('Accessories', 'Phụ kiện thời trang'),
                                             ('Kids', 'Thời trang trẻ em');

INSERT INTO Brand (name, description) VALUES
                                          ('Louis Vuitton', 'Thương hiệu thời trang xa xỉ của Pháp, nổi tiếng với các sản phẩm da, túi xách, quần áo và phụ kiện.'),
                                          ('Chanel', 'Nhà mốt cao cấp của Pháp, nổi tiếng với các thiết kế cổ điển, nước hoa và phụ kiện.'),
                                          ('Hermes', 'Thương hiệu xa xỉ của Pháp chuyên về đồ da, phụ kiện phong cách sống, đồ nội thất và trang sức.'),
                                          ('Gucci', 'Nhà mốt Ý nổi tiếng với các sản phẩm da, quần áo may sẵn và phụ kiện.');

INSERT INTO Tag (tagName, description) VALUES
                                           ('Product', 'Thẻ chung cho các sản phẩm.'),
                                           ('Bags', 'Thẻ cho các loại túi xách.'),
                                           ('Shoes', 'Thẻ cho giày dép.'),
                                           ('Fashion', 'Thẻ cho thời trang nói chung.'),
                                           ('Clothing', 'Thẻ cho quần áo.'),
                                           ('Hats', 'Thẻ cho mũ các loại.'),
                                           ('Accessories', 'Thẻ cho phụ kiện thời trang.');

INSERT INTO Size (sizeName, description) VALUES
                                             ('xs', 'Extra small size'),
                                             ('s', 'Small size'),
                                             ('m', 'Medium size'),
                                             ('l', 'Large size'),
                                             ('xl', 'Extra large size'),
                                             ('2xl', 'Double extra large size'),
                                             ('3xl', 'Triple extra large size');

-- Dữ liệu mẫu Product, ProductSize, ProductColor, ProductTag
INSERT INTO Product (name, description, price, stockQuantity, imageUrl, rating, categoryId, brandId)
VALUES
    ('Piqué Biker Jacket', 'Áo khoác biker chất liệu piqué cao cấp.', 672.400, 75, 'img/product/product-2.jpg', 3, 4, 4),
    ('Multi-pocket Chest Bag', 'Túi đeo ngực đa năng.', 434.800, 120, 'img/product/product-3.jpg', 4, 3, 3);

INSERT INTO ProductSize (product_id, size_id)
VALUES
    (1, 2),
    (1, 3),
    (2, 3),
    (2, 4);

INSERT INTO ProductColor (product_id, color_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 1),
    (2, 3);

INSERT INTO ProductTag (product_id, tag_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 1),
    (2, 4);
