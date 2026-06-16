insert into equipment (id, name, equipment_status)
    OVERRIDING SYSTEM VALUE
VALUES
 (1, 'Boiler1', 'healthy'),
 (2, 'Boiler2', 'alarm'),
 (3, 'A boiler feed pump1', 'critical'),
 (4, 'B boiler feed pump1', 'underRepair'),
 (5, 'Transformer1', 'healthy'),
 (6, 'Gas Turbine1', 'critical');