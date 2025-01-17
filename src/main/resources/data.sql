/* -- Insert fixed records for age_group
INSERT INTO age_group (id, name, description, min_age, max_age) VALUES (1, '6-10 anni', 'Da 6 a 10 anni', 6, 10)
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);
INSERT INTO age_group (id, name, description, min_age, max_age) VALUES (2, '11-15 anni', 'Da 11 a 15 anni', 11, 15)
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);
INSERT INTO age_group (id, name, description, min_age, max_age) VALUES (3, '16-20 anni', 'Da 16 a 20 anni', 16, 20)
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description); */

INSERT INTO age_group (id, name, description, min_age, max_age) VALUES (1, 'Fascia unica', 'Da 6 a 99 anni', 6, 99)
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);
INSERT INTO age_group (id, name, description, min_age, max_age) VALUES (2, '6-14 anni', 'Da 6 a 14 anni - IL GIOCO NON È UN AZZARDO, ecc.', 6, 14)
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);

-- Insert fixed records for activity_type
/* INSERT INTO activity_type (id, name) VALUES (1, 'Salto nel buio')
ON DUPLICATE KEY UPDATE name = VALUES(name);
INSERT INTO activity_type (id, name) VALUES (2, 'Tiro della cinghia')
ON DUPLICATE KEY UPDATE name = VALUES(name);
INSERT INTO activity_type (id, name) VALUES (3, 'Arrampicata sui vetri')
ON DUPLICATE KEY UPDATE name = VALUES(name); */

INSERT INTO activity_type (id, name, description) VALUES (1, 'STORIA DI CASCINA CARLA E BRUNO CACCIA', 'Un racconto avvincente della storia della Cascina e del suo impegno contro le mafie.')
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);
INSERT INTO activity_type (id, name, description) VALUES (2, 'BRUNO CACCIA', 'Laboratorio sulla figura di Bruno Caccia sotto il profilo istituzionale e umano.')
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);
INSERT INTO activity_type (id, name, description) VALUES (3, 'INTRODUZIONE AL TEMA MAFIA', 'Attività introduttiva volta a definire, conoscere e far conoscere il fenomeno mafioso.')
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);
INSERT INTO activity_type (id, name, description) VALUES (4, 'I BENI CONFISCATI', 'Attività di approfondimento sui beni confiscati, delle leggi sulla confisca dei beni e delle novità legislative.')
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);
INSERT INTO activity_type (id, name, description) VALUES (5, 'LE MAFIE IN PIEMONTE', 'Attività di approfondimento sulla presenza delle mafie sul territorio piemontese.')
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);
INSERT INTO activity_type (id, name, description) VALUES (6, 'SOSTENIBILITÀ AMBIENTALE', 'Approfondimenti sui temi della sostenibilità ambientale e della disuguaglianza con riflessioni sui problemi e la ricerca delle possibili soluzioni.')
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);
INSERT INTO activity_type (id, name, description) VALUES (7, 'LA MAFIA ATTRAVERSO IL CINEMA', 'Riflessioni sui modelli mafiosi presentati dal cinema per comprendere il loro impatto sulla società e migliorare la comprensione critica (e su quanto siano rappresentativi della realtà oggettiva e del sentire comune).')
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);
INSERT INTO activity_type (id, name, description) VALUES (8, 'IL GIOCO NON È UN AZZARDO', 'L''attività mira a esplorare le varie tipologie di gioco e le loro caratteristiche e a riflettere sulla correttezza dei giochi a cui si prende parte.')
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);
INSERT INTO activity_type (id, name, description) VALUES (9, 'CHI NON GIOCA VINCE', 'Considerazioni per sensibilizzare ai rischi e alla dipendenza del gioco d''azzardo e ai collegamenti tra mafia e gioco d''azzardo illegale.')
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);

-- Insert fixed records for booking_duration
INSERT INTO booking_duration (id, name, description) VALUES (1, '1/2 giornata', 'Mezza giornata')
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);
INSERT INTO booking_duration (id, name, description) VALUES (2, '1 giorno', 'Giornata intera')
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);
INSERT INTO booking_duration (id, name, description) VALUES (3, '2 giorni', 'Due giornate')
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);

-- Insert sample data for form_info
INSERT INTO form_info (id, email, name, surname, association, phone_number, contact_date, additional_info, age_group_id, form_type, newsletter_check)
VALUES (1, 'john.doe@example.com', 'John', 'Doe', 'Ente 1', '1234567890', '2023-01-01', 'Descrizione di esempio 1', 1, 'FORM_INFO', 'YES')
ON DUPLICATE KEY UPDATE email = VALUES(email), name = VALUES(name), surname = VALUES(surname), association = VALUES(association), phone_number = VALUES(phone_number), contact_date = VALUES(contact_date), additional_info = VALUES(additional_info), age_group_id = VALUES(age_group_id), form_type = VALUES(form_type), newsletter_check = VALUES(newsletter_check);
INSERT INTO form_info (id, email, name, surname, association, phone_number, contact_date, additional_info, age_group_id, form_type, newsletter_check)
VALUES (2, 'jane.smith@example.com', 'Jane', 'Smith', 'Ente 2', '0987654321', '2023-02-01', 'Descrizione di esempio 2', 1, 'FORM_INFO', 'NO')
ON DUPLICATE KEY UPDATE email = VALUES(email), name = VALUES(name), surname = VALUES(surname), association = VALUES(association), phone_number = VALUES(phone_number), contact_date = VALUES(contact_date), additional_info = VALUES(additional_info), age_group_id = VALUES(age_group_id), form_type = VALUES(form_type), newsletter_check = VALUES(newsletter_check);
INSERT INTO form_info (id, email, name, surname, association, phone_number, contact_date, additional_info, age_group_id, form_type, newsletter_check)
VALUES (3, 'alice.johnson@example.com', 'Alice', 'Johnson', 'Ente 3', '1122334455', '2023-03-01', 'Descrizione di esempio 3', 1, 'FORM_BOOKING', 'YES')
ON DUPLICATE KEY UPDATE email = VALUES(email), name = VALUES(name), surname = VALUES(surname), association = VALUES(association), phone_number = VALUES(phone_number), contact_date = VALUES(contact_date), additional_info = VALUES(additional_info), age_group_id = VALUES(age_group_id), form_type = VALUES(form_type), newsletter_check = VALUES(newsletter_check);

-- Insert sample data for form_info_activity_type (join table)
INSERT INTO form_info_activity_type (form_info_id, activity_type_id)
VALUES (1, 1)
ON DUPLICATE KEY UPDATE form_info_id = VALUES(form_info_id), activity_type_id = VALUES(activity_type_id);
INSERT INTO form_info_activity_type (form_info_id, activity_type_id)
VALUES (2, 1), (2, 2)
ON DUPLICATE KEY UPDATE form_info_id = VALUES(form_info_id), activity_type_id = VALUES(activity_type_id);
INSERT INTO form_info_activity_type (form_info_id, activity_type_id)
VALUES (3, 1), (3, 2), (3, 3)
ON DUPLICATE KEY UPDATE form_info_id = VALUES(form_info_id), activity_type_id = VALUES(activity_type_id);

-- Insert sample data for form_booking
INSERT INTO form_booking (id, begin_time, end_time, participants_quantity, guides_quantity, booking_duration_id)
VALUES (3, '2023-03-10', '2023-03-15', 15, 3, 1)
ON DUPLICATE KEY UPDATE begin_time = VALUES(begin_time), end_time = VALUES(end_time), participants_quantity = VALUES(participants_quantity), guides_quantity = VALUES(guides_quantity), booking_duration_id = VALUES(booking_duration_id);

-- Insert sample data for admin_emails
INSERT INTO  admin_emails (id, no_reply_email, no_reply_password, admin_email)
VALUES (1, 'testcascinacaccia@gmail.com', 'vwiwpzvtuypoinuo', 'testcascinacaccia@gmail.com')
ON DUPLICATE KEY UPDATE no_reply_email = VALUES(no_reply_email), no_reply_password = VALUES(no_reply_password), admin_email = VALUES(admin_email);