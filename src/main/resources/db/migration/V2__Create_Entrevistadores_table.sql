CREATE TABLE entrevistadores (
<<<<<<< HEAD
                                 id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
=======
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
>>>>>>> origin/main
                                 nome VARCHAR(255) NOT NULL,
                                 email VARCHAR(255) NOT NULL,
                                 cargo VARCHAR(50),
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
