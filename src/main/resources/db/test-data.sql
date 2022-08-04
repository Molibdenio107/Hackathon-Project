DELETE FROM customer;
INSERT INTO customer(ID, CREATIONTIME, UPDATETIME, VERSION, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, PHONE) VALUES
  (1, TIMESTAMP '2022-08-04 08:45:56.468', TIMESTAMP '2022-08-04 08:45:56.468', 0, 'rui', '123', 'Rui', 'Ferrão', 'mail@gmail.com', '777888'),
  (2, TIMESTAMP '2022-08-04 08:45:56.481', TIMESTAMP '2022-08-04 08:45:56.481', 0, 'sergio', '1234', 'Sergio', 'Gouveia', 'mail@gmail.com', '777888'),
  (3, TIMESTAMP '2022-08-04 08:45:56.482', TIMESTAMP '2022-08-04 08:45:56.482', 0, 'bruno', '12345' 'Bruno', 'Ferreira', 'mail@gmail.com', '777888'),
  (4, TIMESTAMP '2022-08-04 08:45:56.482', TIMESTAMP '2022-08-04 08:45:56.482', 0, 'No Accounts', 'No name', 'mail@gmail.com', '777888');