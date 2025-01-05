-- Insert fixed records for age_group
INSERT INTO age_group (id, name, description, min_age, max_age) VALUES (1, '6-10 anni', 'Da 6 a 10 anni', 6, 10)
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);
INSERT INTO age_group (id, name, description, min_age, max_age) VALUES (2, '11-15 anni', 'Da 11 a 15 anni', 11, 15)
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);
INSERT INTO age_group (id, name, description, min_age, max_age) VALUES (3, '16-20 anni', 'Da 16 a 20 anni', 16, 20)
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);

-- Insert fixed records for activity_type
INSERT INTO activity_type (id, name) VALUES (1, 'Salto nel buio')
ON DUPLICATE KEY UPDATE name = VALUES(name);
INSERT INTO activity_type (id, name) VALUES (2, 'Tiro della cinghia')
ON DUPLICATE KEY UPDATE name = VALUES(name);
INSERT INTO activity_type (id, name) VALUES (3, 'Arrampicata sui vetri')
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- Insert sample data for form_info
INSERT INTO form_info (id, email, name, surname, association, phone_number, contact_date, additional_info, age_group_id, form_type)
VALUES (1, 'john.doe@example.com', 'John', 'Doe', 'Ente 1', '1234567890', '2023-01-01', 'Descrizione di esempio 1', 1, 'FORM_INFO')
ON DUPLICATE KEY UPDATE email = VALUES(email), name = VALUES(name), surname = VALUES(surname), association = VALUES(association), phone_number = VALUES(phone_number), contact_date = VALUES(contact_date), additional_info = VALUES(additional_info), age_group_id = VALUES(age_group_id), form_type = VALUES(form_type);
INSERT INTO form_info (id, email, name, surname, association, phone_number, contact_date, additional_info, age_group_id, form_type)
VALUES (2, 'jane.smith@example.com', 'Jane', 'Smith', 'Ente 2', '0987654321', '2023-02-01', 'Descrizione di esempio 2', 2, 'FORM_INFO')
ON DUPLICATE KEY UPDATE email = VALUES(email), name = VALUES(name), surname = VALUES(surname), association = VALUES(association), phone_number = VALUES(phone_number), contact_date = VALUES(contact_date), additional_info = VALUES(additional_info), age_group_id = VALUES(age_group_id), form_type = VALUES(form_type);
INSERT INTO form_info (id, email, name, surname, association, phone_number, contact_date, additional_info, age_group_id, form_type)
VALUES (3, 'alice.johnson@example.com', 'Alice', 'Johnson', 'Ente 3', '1122334455', '2023-03-01', 'Descrizione di esempio 3', 3, 'FORM_BOOKING')
ON DUPLICATE KEY UPDATE email = VALUES(email), name = VALUES(name), surname = VALUES(surname), association = VALUES(association), phone_number = VALUES(phone_number), contact_date = VALUES(contact_date), additional_info = VALUES(additional_info), age_group_id = VALUES(age_group_id), form_type = VALUES(form_type);

-- Insert sample data for form_booking
INSERT INTO form_booking (id, begin_time, end_time, participants_quantity, guides_quantity)
VALUES (3, '2023-03-10', '2023-03-15', 15, 3)
ON DUPLICATE KEY UPDATE begin_time = VALUES(begin_time), end_time = VALUES(end_time), participants_quantity = VALUES(participants_quantity), guides_quantity = VALUES(guides_quantity);

-- Insert sample data for form_booking_activity_type (join table)
INSERT INTO form_info_activity_type (form_info_id, activity_type_id)
VALUES (3, 2)
ON DUPLICATE KEY UPDATE form_info_id = VALUES(form_info_id), activity_type_id = VALUES(activity_type_id);