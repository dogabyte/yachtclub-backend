-- Partners (BCrypt password: socio123)
INSERT INTO partners (last_name, name, address, phone, dni, username, password, join_date, role)
VALUES ('Pérez', 'Juan', 'Calle 123', '1123456789', '12345678', 'juan_socio', '$2y$10$kcLskOnpresBYaaX5Vt1J.yHgGIyMvUnLRzAOGPRGHQ8MXJPhnbem', CURRENT_DATE, 'PARTNER')
ON CONFLICT (dni) DO NOTHING;

INSERT INTO partners (last_name, name, address, phone, dni, username, password, join_date, role)
VALUES ('Gómez', 'Ana', 'Av. Rivadavia 456', '1134567890', '87654321', 'ana_socio', '$2y$10$kcLskOnpresBYaaX5Vt1J.yHgGIyMvUnLRzAOGPRGHQ8MXJPhnbem', CURRENT_DATE, 'PARTNER')
ON CONFLICT (dni) DO NOTHING;

INSERT INTO partners (last_name, name, address, phone, dni, username, password, join_date, role)
VALUES ('López', 'Carlos', 'Mitre 789', '1145678901', '11223344', 'carlos_socio', '$2y$10$kcLskOnpresBYaaX5Vt1J.yHgGIyMvUnLRzAOGPRGHQ8MXJPhnbem', CURRENT_DATE, 'PARTNER')
ON CONFLICT (dni) DO NOTHING;

-- Employees (BCrypt password: empleado123)
INSERT INTO employees (code, name, address, phone, specialization, username, password, role)
VALUES (2001, 'Lucía González', 'Av. del Libertador 1000', '1122334455', 'Mecánica naval', 'lucia_empleado', '$2y$10$Xc1jJ8YBBDWq5llDNJaeneirLic9GzosriTd94Xz2.WlvBBOiN26u', 'EMPLOYEE')
ON CONFLICT (username) DO NOTHING;

INSERT INTO employees (code, name, address, phone, specialization, username, password, role)
VALUES (2002, 'Martín Díaz', 'Calle Corrientes 200', '1133445566', 'Electricidad náutica', 'martin_empleado', '$2y$10$Xc1jJ8YBBDWq5llDNJaeneirLic9GzosriTd94Xz2.WlvBBOiN26u', 'EMPLOYEE')
ON CONFLICT (username) DO NOTHING;

INSERT INTO employees (code, name, address, phone, specialization, username, password, role)
VALUES (2003, 'Sofía Ramírez', 'Av. Belgrano 300', '1144556677', 'Velería', 'sofia_empleado', '$2y$10$Xc1jJ8YBBDWq5llDNJaeneirLic9GzosriTd94Xz2.WlvBBOiN26u', 'EMPLOYEE')
ON CONFLICT (username) DO NOTHING;

-- Administrators (BCrypt password: admin123)
INSERT INTO administradores (name, address, phone, username, password, role)
VALUES ('Roberto Martínez', 'Calle San Martín 789', '1145678901', 'roberto_admin', '$2y$10$pXfyeqbWYsEDIsDxYowO3eknBrL5SyanusRiYJHSP40i9n.qiK3CW', 'ADMIN')
ON CONFLICT (username) DO NOTHING;

INSERT INTO administradores (name, address, phone, username, password, role)
VALUES ('Laura Franco', 'Av. Córdoba 500', '1122113344', 'laura_admin', '$2y$10$pXfyeqbWYsEDIsDxYowO3eknBrL5SyanusRiYJHSP40i9n.qiK3CW', 'ADMIN')
ON CONFLICT (username) DO NOTHING;

INSERT INTO administradores (name, address, phone, username, password, role)
VALUES ('Diego Torres', 'Av. Santa Fe 600', '1166778899', 'diego_admin', '$2y$10$pXfyeqbWYsEDIsDxYowO3eknBrL5SyanusRiYJHSP40i9n.qiK3CW', 'ADMIN')
ON CONFLICT (username) DO NOTHING;


-- Zones
INSERT INTO zones (name, boat_type, depth, width, boat_capacity)
VALUES ('Zona A - Veleros Grandes', 'Velero', 4.5, 12.0, 20);

INSERT INTO zones (name, boat_type, depth, width, boat_capacity)
VALUES ('Zona B - Veleros Medianos', 'Velero', 3.0, 8.0, 30);

INSERT INTO zones (name, boat_type, depth, width, boat_capacity)
VALUES ('Zona C - Lanchas Deportivas', 'Lancha', 2.5, 6.0, 25);

INSERT INTO zones (name, boat_type, depth, width, boat_capacity)
VALUES ('Zona D - Embarcaciones Pequeñas', 'Mixto', 2.0, 5.0, 40);




-- Embarcación de Juan Pérez (id_owner = 1)
INSERT INTO boats (vessel_number, name, type, length, beam, draft, id_owner)
VALUES ('ARG-2458-VL', 'Viento del Sur', 'Velero crucero', 11.5, 3.8, 1.9, 1)
ON CONFLICT (vessel_number) DO NOTHING;

-- Embarcación de Ana Gómez (id_owner = 2)
INSERT INTO boats (vessel_number, name, type, length, beam, draft, id_owner)
VALUES ('ARG-3721-LC', 'Mar Azul', 'Lancha deportiva', 8.2, 2.6, 0.8, 2)
ON CONFLICT (vessel_number) DO NOTHING;

-- Segunda embarcación de Ana Gómez
INSERT INTO boats (vessel_number, name, type, length, beam, draft, id_owner)
VALUES ('ARG-4892-VL', 'Sirena', 'Velero regata', 9.5, 3.2, 1.6, 2)
ON CONFLICT (vessel_number) DO NOTHING;

-- Embarcación de Carlos López (id_owner = 3)
INSERT INTO boats (vessel_number, name, type, length, beam, draft, id_owner)
VALUES ('ARG-5634-LC', 'Rayo Plateado', 'Lancha cabinada', 10.0, 3.0, 1.2, 3)
ON CONFLICT (vessel_number) DO NOTHING;

-- Moorings (Amarres)
-- Amarre para "Viento del Sur" de Juan Pérez en Zona A
INSERT INTO moorings (id_zone, boat_registration, water_consumption, electricity_consumption, maintenance_service, assignment_date, purchase_date)
SELECT z.id, 'ARG-2458-VL', 15.5, 45.2, true, CURRENT_DATE - INTERVAL '6 months', CURRENT_DATE - INTERVAL '2 years'
FROM zones z WHERE z.name = 'Zona A - Veleros Grandes'
ON CONFLICT (boat_registration) DO NOTHING;

-- Amarre para "Mar Azul" de Ana Gómez en Zona C
INSERT INTO moorings (id_zone, boat_registration, water_consumption, electricity_consumption, maintenance_service, assignment_date)
SELECT z.id, 'ARG-3721-LC', 8.3, 28.7, false, CURRENT_DATE - INTERVAL '3 months'
FROM zones z WHERE z.name = 'Zona C - Lanchas Deportivas'
ON CONFLICT (boat_registration) DO NOTHING;

-- Amarre para "Sirena" de Ana Gómez en Zona B
INSERT INTO moorings (id_zone, boat_registration, water_consumption, electricity_consumption, maintenance_service, assignment_date, purchase_date)
SELECT z.id, 'ARG-4892-VL', 12.1, 35.8, true, CURRENT_DATE - INTERVAL '1 year', CURRENT_DATE - INTERVAL '1 year'
FROM zones z WHERE z.name = 'Zona B - Veleros Medianos'
ON CONFLICT (boat_registration) DO NOTHING;

-- Amarre para "Rayo Plateado" de Carlos López en Zona C
INSERT INTO moorings (id_zone, boat_registration, water_consumption, electricity_consumption, maintenance_service, assignment_date)
SELECT z.id, 'ARG-5634-LC', 10.7, 32.4, false, CURRENT_DATE - INTERVAL '4 months'
FROM zones z WHERE z.name = 'Zona C - Lanchas Deportivas'
ON CONFLICT (boat_registration) DO NOTHING;


-- Employee Zone Assignments (Asignación de empleados a zonas)
-- Lucía González (id 1) asignada a Zona A y Zona B
INSERT INTO employee_zone (id_employee, id_zone, quantity_of_boats)
SELECT 1, id, 8 FROM zones WHERE name = 'Zona A - Veleros Grandes'
ON CONFLICT (id_employee, id_zone) DO NOTHING;

INSERT INTO employee_zone (id_employee, id_zone, quantity_of_boats)
SELECT 1, id, 12 FROM zones WHERE name = 'Zona B - Veleros Medianos'
ON CONFLICT (id_employee, id_zone) DO NOTHING;

-- Martín Díaz (id 2) asignado a Zona C
INSERT INTO employee_zone (id_employee, id_zone, quantity_of_boats)
SELECT 2, id, 10 FROM zones WHERE name = 'Zona C - Lanchas Deportivas'
ON CONFLICT (id_employee, id_zone) DO NOTHING;

-- Sofía Ramírez (id 3) asignada a Zona B y Zona D
INSERT INTO employee_zone (id_employee, id_zone, quantity_of_boats)
SELECT 3, id, 15 FROM zones WHERE name = 'Zona B - Veleros Medianos'
ON CONFLICT (id_employee, id_zone) DO NOTHING;

INSERT INTO employee_zone (id_employee, id_zone, quantity_of_boats)
SELECT 3, id, 18 FROM zones WHERE name = 'Zona D - Embarcaciones Pequeñas'
ON CONFLICT (id_employee, id_zone) DO NOTHING;
