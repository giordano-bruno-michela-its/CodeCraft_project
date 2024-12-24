-- src/main/resources/data.sql

-- Insert fixed records for fascia_eta
INSERT INTO fascia_eta (id, denominazione, descrizione, eta_min, eta_max) VALUES (1, '6-10 anni', 'Da 6 a 10 anni', 6, 10)
ON DUPLICATE KEY UPDATE denominazione = VALUES(denominazione), descrizione = VALUES(descrizione);
INSERT INTO fascia_eta (id, denominazione, descrizione, eta_min, eta_max) VALUES (2, '11-15 anni', 'Da 11 a 15 anni', 11, 15)
ON DUPLICATE KEY UPDATE denominazione = VALUES(denominazione), descrizione = VALUES(descrizione);
INSERT INTO fascia_eta (id, denominazione, descrizione, eta_min, eta_max) VALUES (3, '16-20 anni', 'Da 16 a 20 anni', 16, 20)
ON DUPLICATE KEY UPDATE denominazione = VALUES(denominazione), descrizione = VALUES(descrizione);

-- Insert fixed records for tipo_attivita
INSERT INTO tipo_attivita (id, denominazione) VALUES (1, 'Salto nel buio')
ON DUPLICATE KEY UPDATE denominazione = VALUES(denominazione);
INSERT INTO tipo_attivita (id, denominazione) VALUES (2, 'Tiro della cinghia')
ON DUPLICATE KEY UPDATE denominazione = VALUES(denominazione);
INSERT INTO tipo_attivita (id, denominazione) VALUES (3, 'Arrampicata sui vetri')
ON DUPLICATE KEY UPDATE denominazione = VALUES(denominazione);

-- Insert sample data for form_richiesta
INSERT INTO form_richiesta (id, email, nome, cognome, ente, telefono, data_contatto, descrizione, fascia_eta_id, tipo_richiesta)
VALUES (1, 'john.doe@example.com', 'John', 'Doe', 'Ente 1', '1234567890', '2023-01-01', 'Descrizione di esempio 1', 1, 'RICHIESTA_INFORMAZIONI')
ON DUPLICATE KEY UPDATE email = VALUES(email), nome = VALUES(nome), cognome = VALUES(cognome), ente = VALUES(ente), telefono = VALUES(telefono), data_contatto = VALUES(data_contatto), descrizione = VALUES(descrizione), fascia_eta_id = VALUES(fascia_eta_id), tipo_richiesta = VALUES(tipo_richiesta);

INSERT INTO form_richiesta (id, email, nome, cognome, ente, telefono, data_contatto, descrizione, fascia_eta_id, tipo_richiesta)
VALUES (2, 'jane.smith@example.com', 'Jane', 'Smith', 'Ente 2', '0987654321', '2023-02-01', 'Descrizione di esempio 2', 2, 'RICHIESTA_INFORMAZIONI')
ON DUPLICATE KEY UPDATE email = VALUES(email), nome = VALUES(nome), cognome = VALUES(cognome), ente = VALUES(ente), telefono = VALUES(telefono), data_contatto = VALUES(data_contatto), descrizione = VALUES(descrizione), fascia_eta_id = VALUES(fascia_eta_id), tipo_richiesta = VALUES(tipo_richiesta);

-- Insert sample data for form_prenotazione
INSERT INTO form_richiesta (id, email, nome, cognome, ente, telefono, data_contatto, descrizione, fascia_eta_id, tipo_richiesta)
VALUES (3, 'alice.johnson@example.com', 'Alice', 'Johnson', 'Ente 3', '1122334455', '2023-03-01', 'Descrizione di esempio 3', 3, 'RICHIESTA_PRENOTAZIONE')
ON DUPLICATE KEY UPDATE email = VALUES(email), nome = VALUES(nome), cognome = VALUES(cognome), ente = VALUES(ente), telefono = VALUES(telefono), data_contatto = VALUES(data_contatto), descrizione = VALUES(descrizione), fascia_eta_id = VALUES(fascia_eta_id), tipo_richiesta = VALUES(tipo_richiesta);

INSERT INTO form_prenotazione (id, data_inizio, data_fine, num_partecipanti, num_insegnanti)
VALUES (3, '2023-03-10', '2023-03-15', 15, 3)
ON DUPLICATE KEY UPDATE data_inizio = VALUES(data_inizio), data_fine = VALUES(data_fine), num_partecipanti = VALUES(num_partecipanti), num_insegnanti = VALUES(num_insegnanti);

-- Insert sample data for form_prenotazione_tipo_attivita (join table)
INSERT INTO form_prenotazione_tipo_attivita (form_prenotazione_id, tipo_attivita_id)
VALUES (3, 2)
ON DUPLICATE KEY UPDATE form_prenotazione_id = VALUES(form_prenotazione_id), tipo_attivita_id = VALUES(tipo_attivita_id);