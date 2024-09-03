-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123

INSERT INTO USERS (username, password, first_name, last_name, email, phone_number, enabled, last_password_reset_date) VALUES ('owner', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Vladimir', 'Vladimirovic', 'owner@example.com', '06000000', true, '2017-10-01 21:58:58.508-07');
INSERT INTO USERS (username, password, first_name, last_name, email, phone_number, enabled, last_password_reset_date) VALUES ('user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Kosta', 'Kostic', 'kalargic@gmail.com', '06000000', true, '2017-10-01 18:57:58.508-07');
INSERT INTO USERS (username, password, first_name, last_name, email, phone_number, enabled, last_password_reset_date) VALUES ('receptionist', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Radmilo', 'Radmilovic', 'receptionist@example.com', '06000000', true, '2017-10-01 18:57:58.508-07');

INSERT INTO ROLE (name) VALUES ('ROLE_OWNER');
INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_RECEPTIONIST');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); -- owner-u dodeljujemo rolu ROLE_OWNER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2); -- user-u dodeljujemo rolu ROLE_USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 3); -- receptionist -u dodeljujemo rolu ROLE_RECEPTIONIST


INSERT INTO MENU_ITEM (id, name, picture, price, rate, prep, cook, description) values (1, 'French Toast', 'mafin.jpeg', 20, 4.5, 10, 16, 'Join us for the perfect breakfast experience and indulge in our delightful French Toast Meal.');
INSERT INTO MENU_ITEM (id, name, picture, price, rate, prep, cook, description) values (2, 'Bowl', 'bowl.png', 21, 4.5, 10, 16, 'Join us for the perfect breakfast experience and indulge in our delightful French Toast Meal.');
INSERT INTO MENU_ITEM (id, name, picture, price, rate, prep, cook, description) values (3, 'Pizza Prosciutto', 'pizza.jpeg', 22, 4.5, 10, 16, 'Join us for the perfect breakfast experience and indulge in our delightful French Toast Meal.');
INSERT INTO MENU_ITEM (id, name, picture, price, rate, prep, cook, description) values (4, 'Egg Muffins', 'egg_muffins.jpeg', 23, 4.5, 10, 16, 'Join us for the perfect breakfast experience and indulge in our delightful French Toast Meal.');
INSERT INTO MENU_ITEM (id, name, picture, price, rate, prep, cook, description) values (5, 'Eggs', 'eggs.jpeg', 24, 4.5, 10, 16, 'Join us for the perfect breakfast experience and indulge in our delightful French Toast Meal.');
INSERT INTO MENU_ITEM (id, name, picture, price, rate, prep, cook, description) values (6, 'Omelette', 'omlette.jpeg', 25, 4.5, 10, 16, 'Join us for the perfect breakfast experience and indulge in our delightful French Toast Meal.');
INSERT INTO MENU_ITEM (id, name, picture, price, rate, prep, cook, description) values (7, 'Pizza Breakfast', 'pizza_breakfast.jpeg', 26, 4.5, 10, 16, 'Join us for the perfect breakfast experience and indulge in our delightful French Toast Meal.');
INSERT INTO MENU_ITEM (id, name, picture, price, rate, prep, cook, description) values (8, 'Pizza Margarita', 'pizza_margarita.png', 27, 4.5, 10, 16, 'Join us for the perfect breakfast experience and indulge in our delightful French Toast Meal.');
INSERT INTO MENU_ITEM (id, name, picture, price, rate, prep, cook, description) values (9, 'Tacos', 'tacos.jpeg', 28, 4.5, 10, 16, 'Join us for the perfect breakfast experience and indulge in our delightful French Toast Meal.');


INSERT INTO Ingredient (id, name, menu_item_id) values (1, 'eggs', 1);
INSERT INTO Ingredient (id, name, menu_item_id) values (2, '1 cup half-and-half', 1);
INSERT INTO Ingredient (id, name, menu_item_id) values (3, '1 teaspoon vanilla', 1);
INSERT INTO Ingredient (id, name, menu_item_id) values (4, '1/2 teaspoon salt', 1);
INSERT INTO Ingredient (id, name, menu_item_id) values (5, '2 tablespoons butter', 1);

INSERT INTO Ingredient (id, name, menu_item_id) values (6, '1 cup oat flakes', 2);
INSERT INTO Ingredient (id, name, menu_item_id) values (7, '1 cup milk', 2);
INSERT INTO Ingredient (id, name, menu_item_id) values (8, '1 teaspoon honey', 2);
INSERT INTO Ingredient (id, name, menu_item_id) values (9, '1/2 cup raspberries', 2);

INSERT INTO Ingredient (id, name, menu_item_id) values (10, '1 1/2 cups shredded mozzarella cheese', 3);
INSERT INTO Ingredient (id, name, menu_item_id) values (11, '6-8 slices of prosciutto', 3);
INSERT INTO Ingredient (id, name, menu_item_id) values (12, 'pizza dough', 3);

INSERT INTO Ingredient (id, name, menu_item_id) values (13, '6 eggs', 4);
INSERT INTO Ingredient (id, name, menu_item_id) values (14, '1/4 cup milk', 4);
INSERT INTO Ingredient (id, name, menu_item_id) values (15, '1/2 cup shredded cheese', 4);
INSERT INTO Ingredient (id, name, menu_item_id) values (16, '1 cup diced vegetables', 4);

INSERT INTO Ingredient (id, name, menu_item_id) values (17, '4 eggs', 5);
INSERT INTO Ingredient (id, name, menu_item_id) values (18, '1 tablespoon butter or oil', 5);

INSERT INTO Ingredient (id, name, menu_item_id) values (19, '4 eggs', 6);
INSERT INTO Ingredient (id, name, menu_item_id) values (20, '1 tablespoon butter or oil', 6);

INSERT INTO Ingredient (id, name, menu_item_id) values (21, 'pizza dough', 7);
INSERT INTO Ingredient (id, name, menu_item_id) values (22, '1 cup diced vegetables', 7);

INSERT INTO Ingredient (id, name, menu_item_id) values (23, 'pizza dough', 8);
INSERT INTO Ingredient (id, name, menu_item_id) values (24, '1 1/2 cups shredded mozzarella cheese', 8);

INSERT INTO Ingredient (id, name, menu_item_id) values (25, '450g ground beef, chicken, or turkey', 9);
INSERT INTO Ingredient (id, name, menu_item_id) values (26, 'taco shells or tortillas', 9);
INSERT INTO Ingredient (id, name, menu_item_id) values (27, '1 cup diced vegetables', 9);

