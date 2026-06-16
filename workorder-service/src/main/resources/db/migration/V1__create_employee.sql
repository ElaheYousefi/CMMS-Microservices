CREATE TABLE employee (
                          id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          name VARCHAR(255),
                          mobile VARCHAR(15),
                          email VARCHAR(100)
);