
-- Inserir dados para a entidade Aquarium
INSERT INTO tb_aquarium (name, volume, last_reading_date, water_type) VALUES ('Aquário 1', 100, CURRENT_DATE, 0);
INSERT INTO tb_aquarium (name, volume, last_reading_date, water_type) VALUES ('Aquário 2', 150, CURRENT_DATE, 1);

-- Inserir dados para a entidade Plant
INSERT INTO tb_plant (name, description, aquarium_id) VALUES ('Planta A', 'Descrição da Planta A', 1);
INSERT INTO tb_plant (name, description, aquarium_id) VALUES ('Planta B', 'Descrição da Planta B', 2);
INSERT INTO tb_plant (name, description, aquarium_id) VALUES ('Planta C', 'Descrição da Planta C', 1);

-- Inserir dados para a entidade Fish
INSERT INTO tb_fish (type, age, name, aquarium_id) VALUES ('Tipo A', 2, 'Peixe A', 2);
INSERT INTO tb_fish (type, age, name, aquarium_id) VALUES ('Tipo B', 1, 'Peixe B', 1);
INSERT INTO tb_fish (type, age, name, aquarium_id) VALUES ('Tipo C', 3, 'Peixe C', 2);

-- Inserir dados para a entidade Parameters
INSERT INTO tb_parameters (qtd_ph, qtd_nitrito, qtd_nitrato, valor_kh, temperature, aquarium_id) VALUES (7, 1, 2, 5, 25.5, 1);
INSERT INTO tb_parameters (qtd_ph, qtd_nitrito, qtd_nitrato, valor_kh, temperature, aquarium_id) VALUES (6, 2, 3, 6, 26.0, 2);
INSERT INTO tb_parameters (qtd_ph, qtd_nitrito, qtd_nitrato, valor_kh, temperature, aquarium_id) VALUES (7, 1, 2, 5, 24.5, 1);

-- Inserir dados para a entidade Event
INSERT INTO tb_event (name, description, aquarium_id) VALUES ('Evento A', 'Descrição do Evento A', 1);
INSERT INTO tb_event (name, description, aquarium_id) VALUES ('Evento B', 'Descrição do Evento B', 2);
INSERT INTO tb_event (name, description, aquarium_id) VALUES ('Evento C', 'Descrição do Evento C', 1);
