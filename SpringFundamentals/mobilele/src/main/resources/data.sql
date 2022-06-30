USE
mobilele;
INSERT INTO users (id, email, first_name, last_name, image_url, is_Active, password)
VALUES (1, 'test@mail.com', 'test', 'testov', null, 1,
        '98aa5f965c4b0f3a678fc4d6ef174e99d7e0a766656cf1f09c5b19ff6a708002a581a36db77019b3');

INSERT INTO brands (id, name)
VALUES (1, 'Ford'),
       (2, 'Toyota');

INSERT INTO models (id, name, category, start_year, end_year, brand_id, image_url)
VALUES (1, 'Fiesta', 'CAR', '1976', null, 1, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/280px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg'),
       (2, 'Escort', 'CAR', '1966', 2000, 1, 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Ford_Escort_RS2000_MkI.jpg/280px-Ford_Escort_RS2000_MkI.jpg'),
       (3, 'Yaris', 'CAR', '1999', 2000, 2, 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/2020_Toyota_Yaris_Design_HEV_CVT_1.5_Front.jpg/275px-2020_Toyota_Yaris_Design_HEV_CVT_1.5_Front.jpg')