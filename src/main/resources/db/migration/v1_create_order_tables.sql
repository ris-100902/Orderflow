CREATE TABLE order (
    id BIGSERIAL PRIMARY KEY,
    customer_id VARCHAR(255) NOT NULL
);

CREATE TABLE order_item (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE order_line (
    id BIGSERIAL PRIMARY KEY,
    quantity INT NOT NULL,
    order_id BIGINT NOT NULL,
    order_item_id BIGINT NOT NULL,

    CONSTRAINT fk_order_line_order FOREIGN KEY (order_id) REFERENCES order(id),
    CONSTRAINT fk_order_line_order_item FOREIGN KEY (order_item_id) REFERENCES order_item(id)
);