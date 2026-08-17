CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY,
    customer_id VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS order_item (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS  order_line (
    id BIGSERIAL PRIMARY KEY,
    quantity INT NOT NULL,
    order_id BIGINT,
    order_item_id BIGINT NOT NULL,

    CONSTRAINT fk_order_line_order FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT fk_order_line_order_item FOREIGN KEY (order_item_id) REFERENCES order_item(id)
);