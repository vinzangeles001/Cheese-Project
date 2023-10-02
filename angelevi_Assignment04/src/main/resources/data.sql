INSERT INTO units (description) VALUES 
('Lb. Wheel'), ('Lb. Half Wheel'), ('Lb. Quarter Wheel'), 
('Lb. Eighth Wheel'), ('Lb. Square'), ('Lb. Tomme');

INSERT INTO cheeses (name, quantity, weight, unitsId, price, specSheet)
    VALUES ('Carles Roquefort AOC', 10, 3, 2, 39.95, 'http://makertomonger.com/carles-roquefort'),
    ('Carles Roquefort AOC', 17, 6, 1, 75.99, 'http://makertomonger.com/carles-roquefort'), 
    ('Alp Blossom', 18, 6, 2, 152.00, 'http://makertomonger.com/alp-blossom-2'),
    ('Alp Blossom', 4, 6, 2, 152.00, 'http://makertomonger.com/alp-blossom-2'),
    ('Alp Blossom', 1, 3, 3, 72.5, 'http://makertomonger.com/alp-blossom-2'),
    ('Alp Blossom', 4, 1.5, 4, 26.5, 'http://makertomonger.com/alp-blossom-2'),
    ('Stinky Feet Cheese', 20, 4, 5, 4.20, ''),
    ('Chällerhocker', 15, 4, 1, 249.99, 'http://makertomonger.com/wp-content/uploads/2015/11/Challerhocker-Long-Form.jpg'),
    ('Chällerhocker', 17, 2, 2, 129.99, 'http://makertomonger.com/wp-content/uploads/2015/11/Challerhocker-Long-Form.jpg');
    
INSERT INTO users (userName, email, password, enabled)
VALUES ('foobar', 'foo@java.com', '$2a$10$/zHkTBc3Q8xVTkKAd1ZYIu0RLP6dPxim9vNlUVgOO6jjcm8qDWQrC', 1);
INSERT INTO users (userName, email, password, enabled)
VALUES ('wendi', 'bar@java.com', '$2a$10$rwm/l4oiRNPFVqUb4B/w4Ot3QMdSaNSgZTCnP6ipfvVb4OoyTIzLK', 1);

INSERT INTO roles (rolename)
VALUES ('ROLE_MANAGER');
INSERT INTO roles (rolename)
VALUES ('ROLE_CUSTOMER');

INSERT INTO user_role (userId, roleId)
VALUES (1, 1); 
INSERT INTO user_role (userId, roleId)
VALUES (1, 2);
INSERT INTO user_role (userId, roleId)
VALUES (2, 2);   