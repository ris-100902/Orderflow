SELECT setval(
    pg_get_serial_sequence('orders', 'id'),
    COALESCE((SELECT MAX(id) FROM orders), 1)
);

SELECT setval(
    pg_get_serial_sequence('order_item', 'id'),
    COALESCE((SELECT MAX(id) FROM order_item), 1)
);

SELECT setval(
    pg_get_serial_sequence('order_line', 'id'),
    COALESCE((SELECT MAX(id) FROM order_line), 1)
);