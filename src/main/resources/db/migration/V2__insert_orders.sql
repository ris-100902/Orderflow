INSERT INTO order_item (id, name) VALUES (1, 'Pen'), (2, 'Pencil'), (3, 'Notebook');
INSERT INTO orders (id, customer_id) VALUES (1, 'cust-1000');

INSERT INTO order_line (id, quantity, order_id, order_item_id)
VALUES (1, 5, 1, 1), (2, 10, 1, 3);