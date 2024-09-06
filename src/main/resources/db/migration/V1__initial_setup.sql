CREATE TABLE IF NOT EXISTS tb_carts (
                                        id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
                                        name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_items (
                                        id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                        serial_number VARCHAR(20) NOT NULL,
                                        cart_id BIGINT,
                                        CONSTRAINT fk_cart FOREIGN KEY(cart_id) REFERENCES tb_carts(id) ON DELETE CASCADE
);
