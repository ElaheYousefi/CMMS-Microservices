CREATE TABLE defined_task (
                              id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                              task_name VARCHAR(255) NOT NULL,
                              start_date DATE NOT NULL,
                              end_date DATE NULL,
                              period_day INT NOT NULL
);