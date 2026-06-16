CREATE TABLE workorder (
                           id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

                           assign_task_id INT NOT NULL,
                           employee_id INT NULL,
                           employee_name VARCHAR(255) NULL,

                           equipment_id INT NOT NULL,
                           equipment_name VARCHAR(255) NULL,
                           equipment_status VARCHAR(50) NULL,

                           due_date DATE NOT NULL,
                           done_date DATE NULL,

                           observ_result VARCHAR(1000) NULL,

                           work_order_status VARCHAR(50) NOT NULL,
                           priority VARCHAR(50) NULL
);