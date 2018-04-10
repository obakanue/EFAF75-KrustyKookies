INSERT INTO recipes(
rec_name)
VALUES 
(
'Nut ring'),
('Nut cookie'),
('Amneris'),
('Tango'), 
('Almond delight'),
('Berliner');

INSERT INTO ingredients(
ing_name, unit, amount, date_of_deliv, size_of_deliv)
VALUES 
(
'Flour', 'gram', 1000, '2018-04-10', 1000),
('Butter', 'gram', 1000, '2018-04-10', 1000),
('Icing sugar', 'gram', 1000, '2018-04-10', 1000),
('Roasted, chopped nuts', 'gram', 1000, '2018-04-10', 1000),
('Fine-ground nuts', 'gram', 1000, '2018-04-10', 1000),
('Ground, roasted nuts', 'gram', 1000, '2018-04-10', 1000),
('Bread crumbs', 'gram', 1000, '2018-04-10', 1000),
('Sugar', 'gram', 1000, '2018-04-10', 1000),
('Egg whites', 'deciliter', 1000, '2018-04-10', 1000),
('Chocolate', 'gram', 1000, '2018-04-10', 1000),
('Marzipan', 'gram', 1000, '2018-04-10', 1000),
('Eggs', 'pieces', 1000, '2018-04-10', 1000),
('Potato starch', 'gram', 1000, '2018-04-10', 1000),
('Wheat flour', 'gram', 1000, '2018-04-10', 1000),
('Sodium bicarbonate', 'gram', 1000, '2018-04-10', 1000),
('Vanilla', 'gram', 1000, '2018-04-10', 1000),
('Chopped almonds', 'gram', 1000, '2018-04-10', 1000),
('Cinnamon', 'gram', 1000, '2018-04-10', 1000),
('Vanilla sugar', 'gram', 1000, '2018-04-10', 1000);

INSERT INTO rec_ing(
rec_name, ing_name, amount)
VALUES
(
'Nut ring', 'Flour', 450),
('Nut ring', 'Butter', 450),
('Nut ring', 'Icing sugar', 190),
('Nut ring', 'Roasted, chopped nuts', 225),
('Nut cookie', 'Fine-ground nuts', 750),
('Nut cookie', 'Ground, roasted nuts', 625),
('Nut cookie', 'Bread crumbs', 125),
('Nut cookie', 'Sugar', 375),
('Nut cookie', 'Egg whites', 3.5),
('Nut cookie', 'Chocolate', 50),
('Amneris', 'Marzipan', 750),
('Amneris', 'Butter', 250),
('Amneris', 'Eggs', 10),
('Amneris', 'Potato starch', 25),
('Amneris', 'Wheat flour', 25),
('Tango', 'Butter', 200),
('Tango', 'Sugar', 250),
('Tango', 'Flour', 300),
('Tango', 'Sodium bicarbonate', 4),
('Tango', 'Vanilla', 2),
('Almont delight', 'Butter', 400),
('Almont delight', 'Sugar', 270),
('Almont delight', 'Chopped almonds', 279),
('Almont delight', 'Flour', 400),
('Almont delight', 'Cinnamon', 10),
('Berliner', 'Flour', 350),
('Berliner', 'Butter', 250),
('Berliner', 'Icing sugar', 100),
('Berliner', 'Eggs', 2),
('Berliner', 'Vanilla sugar', 5),
('Berliner', 'Chocolate', 50);

INSERT INTO customers(
c_name, address, phn_nbr)
VALUES
(
'IKEA Malmö', 'Malmövägen 5', '1122334455'),
('ICA Kvantum Tuna Lund', 'Tunavägen 3', '2233445566'),
('COOP Lund', 'Lundvägen 2', '3344556677'),
('IKEA Göteborg', 'Göteborgsvägen 1', '4455667788'),
('ICA Kvantum Angered', 'Angeredsvägen 9', '5566778899'),
('ICA MAXI Hisingen', 'Hisingsvägen 44', '1234567890');

INSERT INTO orders(
order_id, delivered_by, c_name)
VALUES
(
NULL, '2018-04-14', 'IKEA Göteborg'),
(NULL, '2018-04-15', 'IKEA Malmö'),
(NULL, '2018-04-20', 'COOP Lund'),
(NULL, '2018-04-15', 'ICA Kvantum Angered'),
(NULL, '2018-04-15', 'ICA MAXI Hisingen'),
(NULL, '2018-04-18', 'ICA Kvantum Tuna Lund'),
(NULL, '2018-04-19', 'ICA MAXI Hisingen'),
(NULL, '2018-04-22', 'IKEA Malmö');

INSERT INTO pallets(
pallet_id, status, prod_date, rec_name, order_id)
VALUES
(
NULL,0, '2018-04-14', 'Berliner', NULL),
(NULL,0, '2018-04-14', 'Tango', NULL),
(NULL,0, '2018-04-14', 'Tango', NULL),
(NULL,1, '2018-04-14', 'Almond delight', NULL),
(NULL,2, '2018-04-14', 'Nut ring', NULL),
(NULL,3, '2018-04-14', 'Nut cookie', NULL),
(NULL,4, '2018-04-14', 'Amneris', NULL);

INSERT INTO deliveries(
delivery_id, delivery_date)
VALUES
(
NULL, '2018-04-20'),
(NULL, '2018-04-14'),
(NULL, '2018-04-17'),
(NULL, '2018-04-15'),
(NULL, '2018-04-18'),
(NULL, '2018-04-19'),
(NULL, '2018-04-22');

INSERT INTO delivery_info(
delivery_id, order_id, amount_delivered)
VALUES
(
NULL, NULL, 1),
(NULL, NULL, 1),
(NULL, NULL, 1),
(NULL, NULL, 1),
(NULL, NULL, 1),
(NULL, NULL, 1),
(NULL, NULL, 1);

INSERT INTO blocked(
rec_name, start_date, end_date)
VALUES
(
'Almond delight', '2018-04-01', '2018-04-10');

INSERT INTO order_info(
rec_name, order_id, no_pallets)
VALUES
(
'Berliner', NULL, 1),
('Tango', NULL, 1),
('Tango', NULL, 1),
('Almond delight', NULL, 1),
('Nut ring', NULL, 1),
('Nut cookie', NULL, 1),
('Amneris', NULL, 1);