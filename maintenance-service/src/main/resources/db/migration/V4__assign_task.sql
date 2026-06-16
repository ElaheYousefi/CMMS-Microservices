insert into assign_task(id, active, defined_task_id, next_execution_date, equip_id)
    OVERRIDING SYSTEM VALUE
VALUES
(1, 1, 2, '2026/02/01', 1),
(2, 1, 1,'2026/02/01', 3),
(3, 1, 3, '2026/02/01', 5);