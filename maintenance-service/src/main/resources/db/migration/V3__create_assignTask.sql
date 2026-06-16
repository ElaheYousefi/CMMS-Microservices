CREATE TABLE assign_task (
     id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     equip_id INT NOT NULL,
     defined_task_id INT NOT NULL,
     next_execution_date DATE NOT NULL,
     active INT NULL
);