CREATE TABLE entrevistadores (
                                 id BIGINT PRIMARY KEY,

                                 nome VARCHAR(255) NOT NULL,
                                 email VARCHAR(255) NOT NULL,
                                 cargo VARCHAR(50),
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
