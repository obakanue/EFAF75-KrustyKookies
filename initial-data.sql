PRAGMA foreign_keys = ON;
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
'Flour', 'gram', 100000, '2018-04-10', 100000),
('Butter', 'gram', 100000, '2018-04-10', 100000),
('Icing sugar', 'gram', 100000, '2018-04-10', 100000),
('Roasted, chopped nuts', 'gram', 100000, '2018-04-10', 100000),
('Fine-ground nuts', 'gram', 100000, '2018-04-10', 100000),
('Ground, roasted nuts', 'gram', 100000, '2018-04-10', 100000),
('Bread crumbs', 'gram', 100000, '2018-04-10', 100000),
('Sugar', 'gram', 100000, '2018-04-10', 100000),
('Egg whites', 'deciliter', 100000, '2018-04-10', 100000),
('Chocolate', 'gram', 100000, '2018-04-10', 100000),
('Marzipan', 'gram', 100000, '2018-04-10', 100000),
('Eggs', 'pieces', 100000, '2018-04-10', 100000),
('Potato starch', 'gram', 100000, '2018-04-10', 100000),
('Wheat flour', 'gram', 100000, '2018-04-10', 100000),
('Sodium bicarbonate', 'gram', 100000, '2018-04-10', 100000),
('Vanilla', 'gram', 100000, '2018-04-10', 100000),
('Chopped almonds', 'gram', 100000, '2018-04-10', 100000),
('Cinnamon', 'gram', 100000, '2018-04-10', 100000),
('Vanilla sugar', 'gram', 100000, '2018-04-10', 100000);

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
('Almond delight', 'Butter', 400),
('Almond delight', 'Sugar', 270),
('Almond delight', 'Chopped almonds', 279),
('Almond delight', 'Flour', 400),
('Almond delight', 'Cinnamon', 10),
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
735446, '2018-04-16', 'IKEA Göteborg'),
(735555, '2018-04-15', 'IKEA Malmö'),
(734234, '2018-04-20', 'COOP Lund'),
(731204, '2018-04-15', 'ICA Kvantum Angered'),
(735023, '2018-04-15', 'ICA MAXI Hisingen'),
(735032, '2018-04-18', 'ICA Kvantum Tuna Lund'),
(738989, '2018-04-19', 'ICA MAXI Hisingen'),
(733333, '2018-04-22', 'IKEA Malmö'),
(1,'2018-04-13', 'IKEA Malmö');

INSERT INTO pallets(
pallet_id, status, prod_date, rec_name, order_id)
VALUES
(
99221342,0, '2018-04-14', 'Berliner', 735446),
(94509902,0, '2018-04-14', 'Tango',735555),
(24443040,0, '2018-04-14', 'Tango', 734234),
(13509509,1, '2018-04-14', 'Almond delight', 731204),
(36462323,2, '2018-04-14', 'Nut ring', 735023),
(53988563,3, '2018-04-14', 'Nut cookie', 735032),
(50940277,3, '2018-04-14', 'Nut cookie', 735032),
(23053149,1, '2018-04-14', 'Amneris', 738989),
(53070002,0, '2018-04-14', 'Amneris', 733333),
(91309437,0, '2018-04-14', 'Nut cookie', 733333);

INSERT INTO deliveries(
delivery_id, delivery_date)
VALUES
(
9453025, '2018-04-20'),
(2464200, '2018-04-14'),
(7439204, '2018-04-17'),
(0283742, '2018-04-15'),
(8593024, '2018-04-18'),
(0753095, '2018-04-19'),
(3509142, '2018-04-22'),
(8746930, '2018-04-15');

INSERT INTO delivery_info(
delivery_id, order_id, amount_delivered)
VALUES
(
7439204, 735446, 1),
(2464200, 735555, 1),
(9453025, 734234, 1),
(0283742, 731204, 1),
(8746930, 735023, 1),
(8593024, 735032, 2),
(0753095, 738989, 1),
(3509142, 733333, 2);

INSERT INTO blocked(
rec_name, start_date, end_date)
VALUES
(
'Almond delight', '2018-04-01', '2018-04-10');

INSERT INTO order_info(
rec_name, order_id, no_pallets)
VALUES
(
'Berliner', 735446, 1),
('Tango', 735555, 1),
('Tango', 734234, 1),
('Almond delight', 731204, 1),
('Nut ring', 735023, 1),
('Nut cookie', 735032, 2),
('Nut cookie', 733333, 1),
('Amneris', 738989, 1),
('Amneris', 733333, 1),
('Berliner', 1, 10);