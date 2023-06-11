INSERT INTO departments (id, name)
VALUES (1, 'Administration'),
       (2, 'Marketing'),
       (3, 'Sales'),
       (4, 'After sales'),
       (5, 'Human resources'),
       (6, 'Accountants'),
       (7, 'Warehouse');

INSERT INTO positions (id, name)
VALUES (1, 'Administrator'),
       (2, 'Marketing specialist'),
       (3, 'Salesman'),
       (4, 'Senior salesman'),
       (5, 'Guarantee manager'),
       (6, 'Mechanic'),
       (7, 'HR specialist'),
       (8, 'Accountant'),
       (9, 'Chief accountant'),
       (10, 'Warehouseman');

INSERT INTO employees (id, name, surname, telephone, department_id)
VALUES (1, 'TESTIvan', 'TESTIvanov', '+3752912345678', 1),
       (2, 'TESTFedor', 'TESTFedorov', '+3752922345678', 2),
       (3, 'TESTIgor', 'TESTPetrov', '+3752915345678', 3),
       (4, 'TESTJohn', 'TESTCena', '+3752917345678', 4),
       (5, 'TESTJack', 'TESTVorobei', '+3752919345678', 5),
       (6, 'TESTJohn', 'TESTConnor', '+3752911245678', 6),
       (7, 'TESTLebron', 'TESTJames', '+3752912645678', 7),
       (8, 'TESTHanna', 'TESTSchmitz', '+3752912945678', 1),
       (9, 'TESTAngela', 'TESTCullen', '+3752912045678', 2),
       (10, 'TESTSteve', 'TESTRogers', '+3752912345278', 3),
       (11, 'TESTNatasha', 'TESTRomanov', '+3752912345378', 4),
       (12, 'TESTBruce', 'TESTBanner', '+3752912345778', 5),
       (13, 'TESTAngelina', 'TESTJolie', '+3752912345078', 6),
       (14, 'TESTSasha', 'TESTGrey', '+3752912345671', 7);

INSERT INTO employees_positions (employee_id, position_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (3, 4),
       (4, 5),
       (5, 6),
       (5, 7),
       (6, 8),
       (7, 9),
       (7, 10),
       (8, 1),
       (9, 2),
       (10, 3),
       (10, 4),
       (11, 5),
       (12, 6),
       (13, 7),
       (14, 8),
       (14, 9);