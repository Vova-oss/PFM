--
-- PostgreSQL database dump
--

-- Dumped from database version 13.4
-- Dumped by pg_dump version 13.4

-- Started on 2021-09-16 13:16:15

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3021 (class 0 OID 127554)
-- Dependencies: 203
-- Data for Name: pfm_our_mcc; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (6538, 'Transactions', 'Переводы и снятия', 'MasterCard MoneySend Funding Transaction');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5310, 'Shops', 'Покупки', 'Discount stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5046, 'Furnurure', 'Дом и ремонт', 'Commercial Equipment N-Else');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (6533, 'Subscriptions', 'Подписки и счета на оплату', 'Payment Service Provider -');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5965, 'Shops', 'Покупки', 'Direct Marketing-Combinatio');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (6010, 'Transactions', 'Переводы и снятия', 'POS Cash');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5311, 'Shops', 'Покупки', 'Department stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5992, 'Hobbies', 'Хобби', 'Florists');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7379, 'HardwareAndSoftware', 'Устройства и ПО ', 'Computer Main/Repair Servic');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5940, 'Hobbies', 'Хобби', 'Bicycle Shops-sales & Servi');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5714, 'Furnurure', 'Дом и ремонт', 'Drapery & Upholstery Stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (3504, 'Hotels', 'Отели и гостиницы', 'Hilton Hotels');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7277, 'TaxesAndFines', 'Налоги и штрафы', 'Counseling Service - Debt');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (6051, 'MoneySaveAndInvesting', 'Деньги и инвестиции', 'Foreign Currency,Money,TC''s');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5231, 'Furnurure', 'Дом и ремонт', 'Glass,paint,wallpaper Store');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5712, 'Furnurure', 'Дом и ремонт', 'Furniture,home Furnishings');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8299, 'Education', 'Образование', 'Schools & Educational Servi');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7311, 'Services', 'Сервисы', 'Advertising Services');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8220, 'Education', 'Образование', 'Colleges,Universities');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5950, 'Hobbies', 'Хобби', 'Glassware And Crystal Store');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7829, 'Hobbies', 'Хобби', 'Motion Picture/Video Prd/Di');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5621, 'Clothes', 'Одежда и бижутерия', 'Women''s Ready-to-wear Store');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4814, 'Subscriptions', 'Подписки и счета на оплату', 'Telecommunication Service');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5661, 'Clothes', 'Одежда и бижутерия', 'Shoe stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5944, 'Clothes', 'Одежда и бижутерия', 'Jewelry Stores - Watches');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8062, 'Health', 'Аптеки и медицина', 'Hospitals');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5533, 'Auto', 'Автомобильные сервисы', 'Automotive Parts,acces. Sto');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7933, 'Entertainment', 'Отдых и развлечения', 'Bowling Alleys');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5963, 'Shops', 'Покупки', 'Door-to-Door Sales');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5946, 'Hobbies', 'Хобби', 'Camera And Photographic Sup');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5200, 'Furnurure', 'Дом и ремонт', 'Home Supply,Warehouse Store');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8641, 'TaxesAndFines', 'Налоги и штрафы', 'Civic,social & Fraternal As');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7941, 'Hobbies', 'Хобби', 'Commercial Sports');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5811, 'Shops', 'Покупки', 'Caterers');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (2741, 'Hobbies', 'Хобби', 'Misc-Publishing/Printing');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8211, 'Education', 'Образование', 'Elementary & Secondary Scho');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7273, 'Entertainment', 'Отдых и развлечения', 'Dating & Escort Services');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5942, 'Hobbies', 'Хобби', 'Book Stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5511, 'Auto', 'Автомобильные сервисы', 'Automobile & Truck Dealers');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (6536, 'Transactions', 'Переводы и снятия', 'MasterCard MoneySend Intracountry');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4111, 'Transportation', 'Транспорт и путешествия', 'Local/Suburban Commuter Pas');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5699, 'Clothes', 'Одежда и бижутерия', 'Miscellaneous Apparel & Acc');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5994, 'Services', 'Сервисы', 'News Dealers And Newsstands');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5065, 'HardwareAndSoftware', 'Устройства и ПО ', 'Electric Parts/Equipment');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7832, 'Entertainment', 'Отдых и развлечения', 'Motion Picture Theaters');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7998, 'Furnurure', 'Дом и ремонт', 'Aquariums,seaquariums');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8011, 'Health', 'Аптеки и медицина', 'Doctors (Not Elsewhere Clas');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5462, 'Food', 'Кафе, рестораны', 'Bakeries');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5977, 'Hobbies', 'Хобби', 'Cosmetic Stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5111, 'Shops', 'Покупки', 'Stationery/Off-Supp/Printin');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (780, 'Hobbies', 'Хобби', 'Landscape And Horticultural');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4215, 'Shops', 'Покупки', 'Courier Services-air Or Gro');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8111, 'Services', 'Сервисы', 'Legal Services,attorneys');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4784, 'TaxesAndFines', 'Налоги и штрафы', 'Tolls,road And Bridge Fees');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7216, 'Services', 'Сервисы', 'Dry Cleaners');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5691, 'Clothes', 'Одежда и бижутерия', 'Men''s And Ladies''s Clothing');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (6011, 'Auto', 'Автомобильные сервисы', 'ATM');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (6537, 'Transactions', 'Переводы и снятия', 'MasterCard MoneySend Intercountry');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5812, 'Food', 'Кафе, рестораны', 'Eating Places,Restaurants');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5813, 'Food', 'Кафе, рестораны', 'Drinking Places - Bars,Tave');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5818, 'Services', 'Сервисы', 'Digital Goods - Multy-Category');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7999, 'Entertainment', 'Отдых и развлечения', 'Recreation Services (Not El');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (6012, 'Subscriptions', 'Подписки и счета на оплату', 'Member Financial Institutio');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5441, 'Food', 'Кафе, рестораны', 'Candy,nut,confectionary Sto');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7534, 'Auto', 'Автомобильные сервисы', 'Tire Retreading & Repair Sh');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5941, 'Hobbies', 'Хобби', 'Sporting Goods Stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5964, 'Shops', 'Покупки', 'Direct Marketing-Catalog Me');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5521, 'Auto', 'Автомобильные сервисы', 'Automobile & Truck Dealers');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8661, 'Hobbies', 'Хобби', 'Religious Organizations');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4131, 'Transportation', 'Транспорт и путешествия', 'Bus Lines');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4214, 'Auto', 'Автомобильные сервисы', 'Motor Freight Carriers');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7993, 'HardwareAndSoftware', 'Устройства и ПО ', 'Video Amusement Game Suppli');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7399, 'Services', 'Сервисы', 'Business Services');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5945, 'Hobbies', 'Хобби', 'Hobby,toy,and Game Shops');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8099, 'Health', 'Аптеки и медицина', 'Medical Services');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4121, 'Transportation', 'Транспорт и путешествия', 'Taxicabs/limousines');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5933, 'MoneySaveAndInvesting', 'Деньги и инвестиции', 'Pawn Shops');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8351, 'Services', 'Сервисы', 'Child Day Care Services');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5094, 'Clothes', 'Одежда и бижутерия', 'Precious Stones/Metals/Jewe');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8042, 'Health', 'Аптеки и медицина', 'Optometrists,opthamalogists');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5451, 'Shops', 'Покупки', 'Dairy Products Stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4816, 'HardwareAndSoftware', 'Устройства и ПО ', 'Computer Network/Informatio');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5734, 'Hobbies', 'Хобби', 'Record Shops');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5697, 'Clothes', 'Одежда и бижутерия', 'Tailors,seamstress,mending');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5631, 'Clothes', 'Одежда и бижутерия', 'Women''s Accessory And Speci');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5949, 'Hobbies', 'Хобби', 'Sewing,needlework,fabric');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5970, 'Hobbies', 'Хобби', 'Artists Supply Stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5411, 'Shops', 'Покупки', 'Grocery Stores,supermarkets');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5722, 'Furnurure', 'Дом и ремонт', 'Household Appliance Stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4112, 'Transportation', 'Транспорт и путешествия', 'Passenger RailwaysX');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5816, 'HardwareAndSoftware', 'Устройства и ПО ', 'Digital Goods - Games');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5943, 'Education', 'Образование', 'Stationery,office,and Schoo');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7221, 'Services', 'Сервисы', 'Photographic Studios, Portr');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5967, 'Services', 'Сервисы', 'Direct Marketing-Inbound Te');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (2842, 'Services', 'Сервисы', 'Special Clean/Polish/Sanit');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7349, 'Services', 'Сервисы', 'Cleaning & Maintenance');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7372, 'HardwareAndSoftware', 'Устройства и ПО ', 'Computer And Data Processin');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7333, 'Services', 'Сервисы', 'Commercial Photography,art');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5044, 'HardwareAndSoftware', 'Устройства и ПО ', 'Office/Photogr/Cpy/Microfil');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7622, 'HardwareAndSoftware', 'Устройства и ПО ', 'Electronic Repair Shops');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5532, 'Auto', 'Автомобильные сервисы', 'Automotive Tire Stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4411, 'Entertainment', 'Отдых и развлечения', 'Cruise Lines');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7929, 'Hobbies', 'Хобби', 'Bands, Orchestras And Misce');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5251, 'HardwareAndSoftware', 'Устройства и ПО ', 'Hardware Stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5422, 'Furnurure', 'Дом и ремонт', 'Freezer And Locker Meat Pro');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (742, 'Health', 'Аптеки и медицина', 'Veterinary Services');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (1711, 'Furnurure', 'Дом и ремонт', 'Heating,Plumbing,Air Condit');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5947, 'Hobbies', 'Хобби', 'Gift,card,novelty And Souve');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7538, 'Auto', 'Автомобильные сервисы', 'Automotive Service Shops');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4829, 'Transactions', 'Переводы и снятия', 'Money Transfer');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5192, 'Hobbies', 'Хобби', 'Books/Periodicals/Newspaper');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7629, 'Subscriptions', 'Подписки и счета на оплату', 'Electrical & Small Appl.rep');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5309, 'Shops', 'Покупки', 'Duty Free Stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8071, 'Health', 'Аптеки и медицина', 'Medical & Dental Laboratori');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (6540, 'Transactions', 'Переводы и снятия', 'POI Transactions [Excluding MoneySend]');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5921, 'Food', 'Кафе, рестораны', 'Package Stores - Beer, Liqu');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4722, 'Entertainment', 'Отдых и развлечения', 'Travel Agencies And Tour Op');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5817, 'HardwareAndSoftware', 'Устройства и ПО ', 'Digital Goods - Software Applications (Excluding Games)');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (0, 'Services', 'Сервисы', NULL);
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5047, 'Health', 'Аптеки и медицина', 'Lab/Medic/Dental/Opth/Equip');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5561, 'Entertainment', 'Отдых и развлечения', 'Recreational And Utility');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5973, 'Hobbies', 'Хобби', 'Religious Goods Stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7994, 'HardwareAndSoftware', 'Устройства и ПО ', 'Video Games Arcades/establi');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5999, 'Hobbies', 'Хобби', 'Miscellaneous & specialty r');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5599, 'Auto', 'Автомобильные сервисы', 'Miscellaneous Automotive De');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5719, 'Furnurure', 'Дом и ремонт', 'Miscellaneous House Furnish');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5039, 'Furnurure', 'Дом и ремонт', 'Constuction Materials N-Els');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8249, 'Education', 'Образование', 'Vocational & Trade Schools');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4900, 'Subscriptions', 'Подписки и счета на оплату', 'Utilities-electric,gas,water');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5995, 'Shops', 'Покупки', 'Pet Shops-pet Foods & Suppl');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (6211, 'MoneySaveAndInvesting', 'Деньги и инвестиции', 'Security Brokers/dealers In');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8021, 'Health', 'Аптеки и медицина', 'Dentists,orthodontists');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5045, 'HardwareAndSoftware', 'Устройства и ПО ', 'Computers/Peripheral/Softwr');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5499, 'Shops', 'Покупки', 'Miscellaneous Food Stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4789, 'Transportation', 'Транспорт и путешествия', 'Transportation Services');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5732, 'HardwareAndSoftware', 'Устройства и ПО ', 'Electronic Sales');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (6300, 'TaxesAndFines', 'Налоги и штрафы', 'Insurrance Sales And Underw');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5969, 'Shops', 'Покупки', 'Direct Marketing-Other Dire');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7996, 'Entertainment', 'Отдых и развлечения', 'Amusement Parks,circuses');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (9222, 'TaxesAndFines', 'Налоги и штрафы', 'Fines');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7995, 'Entertainment', 'Отдых и развлечения', 'Casino');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7512, 'Auto', 'Автомобильные сервисы', 'Automobile Rental & Leasing');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5193, 'Hobbies', 'Хобби', 'Florist/Nursery Stock/Flowe');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7997, 'Subscriptions', 'Подписки и счета на оплату', 'Membership Clubs');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7278, 'Shops', 'Покупки', 'Buying/shopping Services');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5976, 'Health', 'Аптеки и медицина', 'Orthopedic Goods - Artifici');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4812, 'Subscriptions', 'Подписки и счета на оплату', 'Telephone Service/Equip....');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7911, 'Entertainment', 'Отдых и развлечения', 'Dance Halls, Studios');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7338, 'Services', 'Сервисы', 'Quick-copy & Reproduction');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8244, 'Education', 'Образование', 'Business & Secretarial Scho');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7230, 'Clothes', 'Одежда и бижутерия', 'Beauty Shops & Barber Shops');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5735, 'Hobbies', 'Хобби', 'Record Shops');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5681, 'Clothes', 'Одежда и бижутерия', 'Furriers And Fur Shops');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7542, 'Auto', 'Автомобильные сервисы', 'Car Washes');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7531, 'Auto', 'Автомобильные сервисы', 'Automotive Top & Body Shops');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5641, 'Clothes', 'Одежда и бижутерия', 'Children''s And Infant''s Wea');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5993, 'Shops', 'Покупки', 'Cigar Stores And Stands');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5541, 'Auto', 'Автомобильные сервисы', 'Service Stations');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7392, 'Services', 'Сервисы', 'Management, Consulting');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (6513, 'Services', 'Сервисы', 'REAL ESTATE AGENTS AND MANAGERS-RENTALS');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8699, 'Subscriptions', 'Подписки и счета на оплату', 'Membership Organizations');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8050, 'Services', 'Сервисы', 'Nursing And Personal Care F');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7922, 'Hobbies', 'Хобби', 'Theatrical Producers');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4225, 'Services', 'Сервисы', 'Public Warehousing');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5651, 'Clothes', 'Одежда и бижутерия', 'Family Clothing Stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5713, 'Furnurure', 'Дом и ремонт', 'Floor Covering,rugs,and Car');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7298, 'Health', 'Аптеки и медицина', 'Health & Beauty Spas');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8043, 'Health', 'Аптеки и медицина', 'Opticians, Optical Goods, E');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (9402, 'Services', 'Сервисы', 'Postal Services-Government');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5996, 'Entertainment', 'Отдых и развлечения', 'Swimming Pools');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5331, 'Shops', 'Покупки', 'Variety Stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (9406, 'Hobbies', 'Хобби', 'Government-Owned Lotteries (Non-U.S.region)');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7297, 'Education', 'Образование', 'Massage Parlors');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7841, 'Hobbies', 'Хобби', 'DVD/VIDEO TAPE RENTAL STORE');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5814, 'Food', 'Кафе, рестораны', 'Fast Food Res. (Quick P S P');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5571, 'Auto', 'Автомобильные сервисы', 'Motorcycle Shops And Dealer');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5013, 'Auto', 'Автомобильные сервисы', 'Motor Vehicle Parts/Supplie');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7395, 'Entertainment', 'Отдых и развлечения', 'Photofinishing Laboratories');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7699, 'Services', 'Сервисы', 'Miscellaneous Repair Shops');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5948, 'Furnurure', 'Дом и ремонт', 'Luggage And Leather Goods S');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (1731, 'Services', 'Сервисы', 'Electrical Contractors');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7261, 'Services', 'Сервисы', 'Funeral Service & Crematori');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (9399, 'Services', 'Сервисы', 'Government Services');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5983, 'Auto', 'Автомобильные сервисы', 'Fuel Dealers - Fuel Oil');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (9311, 'TaxesAndFines', 'Налоги и штрафы', 'TAX PAYMENTS');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (3011, 'Transportation', 'Транспорт и путешествия', 'Aeroflot');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5912, 'Health', 'Аптеки и медицина', 'Drug Stores,pharmacies');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (1799, 'Shops', 'Покупки', 'Special Trade Contractors');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7210, 'Clothes', 'Одежда и бижутерия', 'Laundry,cleaning & Garment');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5542, 'Auto', 'Автомобильные сервисы', 'Automated Fuel Dispenser');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5399, 'Clothes', 'Одежда и бижутерия', 'Miscellaneous General Merch');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5968, 'Shops', 'Покупки', 'Direct Marketing-Continuity');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5074, 'Furnurure', 'Дом и ремонт', 'Plumbing/Heating Equipment');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5960, 'Subscriptions', 'Подписки и счета на оплату', 'Direct Marketing-Insurance');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5261, 'Education', 'Образование', 'Nurseries,lawn & Garden');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7394, 'Services', 'Сервисы', 'Equipment Rental & Leasing');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5655, 'Entertainment', 'Отдых и развлечения', 'Sports Apparel,riding App.s');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5137, 'Clothes', 'Одежда и бижутерия', 'Men/Women/Child Uniforms/Co');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4899, 'Services', 'Сервисы', 'Cable Services');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5211, 'Furnurure', 'Дом и ремонт', 'Lumber And Building Materia');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7011, 'Hotels', 'Отели и гостиницы', 'Hotels,Motels,Resorts-Lodgi');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5931, 'Hobbies', 'Хобби', 'Used Merchandise Stores');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (3553, 'Entertainment', 'Отдых и развлечения', 'Park Inns International');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8999, 'Services', 'Сервисы', 'Services,not Elsewhere Clas');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5611, 'Clothes', 'Одежда и бижутерия', 'Men''s And Boys'' Clothing');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5131, 'Hobbies', 'Хобби', 'Piece Goods/Nations Dry Goo');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7631, 'Clothes', 'Одежда и бижутерия', 'Watch, Clock & Jewelry Repa');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7991, 'Entertainment', 'Отдых и развлечения', 'Tourist Attr. And Exhibitio');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5122, 'Health', 'Аптеки и медицина', 'Drugs/Drug Propriat/Sundrie');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7523, 'Auto', 'Автомобильные сервисы', 'Automobile Parking Lots');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (4511, 'Transportation', 'Транспорт и путешествия', 'Airlines, Air Carriers');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8241, 'Education', 'Образование', 'Correspondence Schools');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (8398, 'Subscriptions', 'Подписки и счета на оплату', 'Charitable Contributions');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7375, 'Services', 'Сервисы', 'Info Retrieval Services');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (7299, 'Services', 'Сервисы', 'Other Services (Not Elsewhe');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5733, 'Hobbies', 'Хобби', 'Music Stores-musical Instru');
INSERT INTO public.pfm_our_mcc (code, group_code, group_code_rus, info) VALUES (5815, 'HardwareAndSoftware', 'Устройства и ПО ', 'Digital Goods - Audiovisual Media Including Books, Movies, and Music');


--
-- TOC entry 3027 (class 0 OID 0)
-- Dependencies: 202
-- Name: pfm_our_mcc_code_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pfm_our_mcc_code_seq', 1, false);


-- Completed on 2021-09-16 13:16:15

--
-- PostgreSQL database dump complete
--

