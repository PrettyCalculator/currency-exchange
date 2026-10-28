--
-- PostgreSQL database dump
--

\restrict PUYCY9pPpikUCSCNxg7N9VL4gez5QSwERVedRprBVDPWu5wgeJ5BkHHzKmkcLnc

-- Dumped from database version 18.3
-- Dumped by pg_dump version 18.3

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
-- Data for Name: currencies; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.currencies VALUES (2, 'AFN', 'Afghani', '؋');
INSERT INTO public.currencies VALUES (3, 'ALL', 'Lek', 'L');
INSERT INTO public.currencies VALUES (4, 'DZD', 'Algerian Dinar', 'د.ج');
INSERT INTO public.currencies VALUES (5, 'USD', 'US Dollar', '$');
INSERT INTO public.currencies VALUES (6, 'AOA', 'Kwanza', 'Kz');
INSERT INTO public.currencies VALUES (7, 'XCD', 'East Caribbean Dollar', '$');
INSERT INTO public.currencies VALUES (8, 'ARS', 'Argentine Peso', '$');
INSERT INTO public.currencies VALUES (9, 'AMD', 'Armenian Dram', '֏');
INSERT INTO public.currencies VALUES (10, 'AWG', 'Aruban Florin', 'ƒ');
INSERT INTO public.currencies VALUES (11, 'AUD', 'Australian Dollar', '$');
INSERT INTO public.currencies VALUES (12, 'AZN', 'Azerbaijan Manat', '₼');
INSERT INTO public.currencies VALUES (13, 'BSD', 'Bahamian Dollar', '$');
INSERT INTO public.currencies VALUES (14, 'BHD', 'Bahraini Dinar', '.د.ب');
INSERT INTO public.currencies VALUES (15, 'BDT', 'Taka', '৳');
INSERT INTO public.currencies VALUES (16, 'BBD', 'Barbados Dollar', '$');
INSERT INTO public.currencies VALUES (17, 'BYN', 'Belarusian Ruble', 'Br');
INSERT INTO public.currencies VALUES (18, 'XOF', 'CFA Franc BCEAO', 'Fr');
INSERT INTO public.currencies VALUES (19, 'BZD', 'Belize Dollar', '$');
INSERT INTO public.currencies VALUES (20, 'BMD', 'Bermudian Dollar', '$');
INSERT INTO public.currencies VALUES (21, 'BTN', 'Ngultrum', 'Nu.');
INSERT INTO public.currencies VALUES (22, 'INR', 'Indian Rupee', '₹');
INSERT INTO public.currencies VALUES (23, 'BOB', 'Boliviano', 'Bs.');
INSERT INTO public.currencies VALUES (24, 'BOV', 'Mvdol', 'BOV');
INSERT INTO public.currencies VALUES (25, 'BAM', 'Convertible Mark', 'KM');
INSERT INTO public.currencies VALUES (26, 'BWP', 'Pula', 'P');
INSERT INTO public.currencies VALUES (27, 'NOK', 'Norwegian Krone', 'kr');
INSERT INTO public.currencies VALUES (28, 'BRL', 'Brazilian Real', 'R$');
INSERT INTO public.currencies VALUES (29, 'BND', 'Brunei Dollar', '$');
INSERT INTO public.currencies VALUES (30, 'BIF', 'Burundi Franc', 'Fr');
INSERT INTO public.currencies VALUES (31, 'CVE', 'Cabo Verde Escudo', '$');
INSERT INTO public.currencies VALUES (32, 'KHR', 'Riel', '៛');
INSERT INTO public.currencies VALUES (33, 'XAF', 'CFA Franc BEAC', 'Fr');
INSERT INTO public.currencies VALUES (34, 'CAD', 'Canadian Dollar', '$');
INSERT INTO public.currencies VALUES (35, 'KYD', 'Cayman Islands Dollar', '$');
INSERT INTO public.currencies VALUES (36, 'CLF', 'Unidad de Fomento', 'CLF');
INSERT INTO public.currencies VALUES (37, 'CLP', 'Chilean Peso', '$');
INSERT INTO public.currencies VALUES (38, 'CNY', 'Yuan Renminbi', '¥');
INSERT INTO public.currencies VALUES (39, 'COP', 'Colombian Peso', '$');
INSERT INTO public.currencies VALUES (40, 'COU', 'Unidad de Valor Real', 'COU');
INSERT INTO public.currencies VALUES (41, 'KMF', 'Comorian Franc', 'Fr');
INSERT INTO public.currencies VALUES (42, 'CDF', 'Congolese Franc', 'Fr');
INSERT INTO public.currencies VALUES (43, 'NZD', 'New Zealand Dollar', '$');
INSERT INTO public.currencies VALUES (44, 'CRC', 'Costa Rican Colon', '₡');
INSERT INTO public.currencies VALUES (45, 'CUP', 'Cuban Peso', '$');
INSERT INTO public.currencies VALUES (46, 'XCG', 'Caribbean Guilder', 'Cg');
INSERT INTO public.currencies VALUES (47, 'CZK', 'Czech Koruna', 'Kč');
INSERT INTO public.currencies VALUES (48, 'DKK', 'Danish Krone', 'kr');
INSERT INTO public.currencies VALUES (49, 'DJF', 'Djibouti Franc', 'Fr');
INSERT INTO public.currencies VALUES (50, 'DOP', 'Dominican Peso', '$');
INSERT INTO public.currencies VALUES (51, 'EGP', 'Egyptian Pound', '£');
INSERT INTO public.currencies VALUES (52, 'SVC', 'El Salvador Colon', '₡');
INSERT INTO public.currencies VALUES (53, 'ERN', 'Nakfa', 'Nfk');
INSERT INTO public.currencies VALUES (54, 'ETB', 'Ethiopian Birr', 'Br');
INSERT INTO public.currencies VALUES (55, 'FKP', 'Falkland Islands Pound', '£');
INSERT INTO public.currencies VALUES (56, 'FJD', 'Fiji Dollar', '$');
INSERT INTO public.currencies VALUES (57, 'XPF', 'CFP Franc', 'Fr');
INSERT INTO public.currencies VALUES (58, 'GMD', 'Dalasi', 'D');
INSERT INTO public.currencies VALUES (59, 'GEL', 'Lari', '₾');
INSERT INTO public.currencies VALUES (60, 'GHS', 'Ghana Cedi', '₵');
INSERT INTO public.currencies VALUES (61, 'GIP', 'Gibraltar Pound', '£');
INSERT INTO public.currencies VALUES (62, 'GBP', 'Pound Sterling', '£');
INSERT INTO public.currencies VALUES (63, 'GTQ', 'Quetzal', 'Q');
INSERT INTO public.currencies VALUES (64, 'GNF', 'Guinean Franc', 'Fr');
INSERT INTO public.currencies VALUES (65, 'GYD', 'Guyana Dollar', '$');
INSERT INTO public.currencies VALUES (66, 'HTG', 'Gourde', 'G');
INSERT INTO public.currencies VALUES (67, 'HNL', 'Lempira', 'L');
INSERT INTO public.currencies VALUES (68, 'HKD', 'Hong Kong Dollar', '$');
INSERT INTO public.currencies VALUES (69, 'HUF', 'Forint', 'Ft');
INSERT INTO public.currencies VALUES (70, 'ISK', 'Iceland Krona', 'kr');
INSERT INTO public.currencies VALUES (71, 'IDR', 'Rupiah', 'Rp');
INSERT INTO public.currencies VALUES (72, 'XDR', 'SDR (Special Drawing Right)', 'XDR');
INSERT INTO public.currencies VALUES (73, 'IRR', 'Iranian Rial', '﷼');
INSERT INTO public.currencies VALUES (74, 'IQD', 'Iraqi Dinar', 'ع.د');
INSERT INTO public.currencies VALUES (75, 'ILS', 'New Israeli Sheqel', '₪');
INSERT INTO public.currencies VALUES (76, 'JMD', 'Jamaican Dollar', '$');
INSERT INTO public.currencies VALUES (77, 'JPY', 'Yen', '¥');
INSERT INTO public.currencies VALUES (78, 'JOD', 'Jordanian Dinar', 'د.ا');
INSERT INTO public.currencies VALUES (79, 'KZT', 'Tenge', '₸');
INSERT INTO public.currencies VALUES (80, 'KES', 'Kenyan Shilling', 'Sh');
INSERT INTO public.currencies VALUES (81, 'KPW', 'North Korean Won', '₩');
INSERT INTO public.currencies VALUES (82, 'KRW', 'Won', '₩');
INSERT INTO public.currencies VALUES (83, 'KWD', 'Kuwaiti Dinar', 'د.ك');
INSERT INTO public.currencies VALUES (84, 'KGS', 'Som', 'с');
INSERT INTO public.currencies VALUES (85, 'LAK', 'Lao Kip', '₭');
INSERT INTO public.currencies VALUES (86, 'LBP', 'Lebanese Pound', 'ل.ل');
INSERT INTO public.currencies VALUES (87, 'LSL', 'Loti', 'L');
INSERT INTO public.currencies VALUES (88, 'ZAR', 'Rand', 'R');
INSERT INTO public.currencies VALUES (89, 'LRD', 'Liberian Dollar', '$');
INSERT INTO public.currencies VALUES (90, 'LYD', 'Libyan Dinar', 'ل.د');
INSERT INTO public.currencies VALUES (91, 'CHF', 'Swiss Franc', 'Fr');
INSERT INTO public.currencies VALUES (92, 'MOP', 'Pataca', 'P');
INSERT INTO public.currencies VALUES (93, 'MKD', 'Denar', 'ден');
INSERT INTO public.currencies VALUES (94, 'MGA', 'Malagasy Ariary', 'Ar');
INSERT INTO public.currencies VALUES (95, 'MWK', 'Malawi Kwacha', 'MK');
INSERT INTO public.currencies VALUES (96, 'MYR', 'Malaysian Ringgit', 'RM');
INSERT INTO public.currencies VALUES (97, 'MVR', 'Rufiyaa', 'Rf');
INSERT INTO public.currencies VALUES (98, 'MRU', 'Ouguiya', 'UM');
INSERT INTO public.currencies VALUES (99, 'MUR', 'Mauritius Rupee', '₨');
INSERT INTO public.currencies VALUES (100, 'XUA', 'ADB Unit of Account', 'XUA');
INSERT INTO public.currencies VALUES (101, 'MXN', 'Mexican Peso', '$');
INSERT INTO public.currencies VALUES (102, 'MXV', 'Mexican Unidad de Inversion (UDI)', 'MXV');
INSERT INTO public.currencies VALUES (103, 'MDL', 'Moldovan Leu', 'L');
INSERT INTO public.currencies VALUES (104, 'MNT', 'Tugrik', '₮');
INSERT INTO public.currencies VALUES (105, 'MAD', 'Moroccan Dirham', 'د.م.');
INSERT INTO public.currencies VALUES (106, 'MZN', 'Mozambique Metical', 'MT');
INSERT INTO public.currencies VALUES (107, 'MMK', 'Kyat', 'Ks');
INSERT INTO public.currencies VALUES (108, 'NAD', 'Namibia Dollar', '$');
INSERT INTO public.currencies VALUES (109, 'NPR', 'Nepalese Rupee', '₨');
INSERT INTO public.currencies VALUES (110, 'NIO', 'Cordoba Oro', 'C$');
INSERT INTO public.currencies VALUES (111, 'NGN', 'Naira', '₦');
INSERT INTO public.currencies VALUES (112, 'OMR', 'Rial Omani', 'ر.ع.');
INSERT INTO public.currencies VALUES (113, 'PKR', 'Pakistan Rupee', '₨');
INSERT INTO public.currencies VALUES (114, 'PAB', 'Balboa', 'B/.');
INSERT INTO public.currencies VALUES (115, 'PGK', 'Kina', 'K');
INSERT INTO public.currencies VALUES (116, 'PYG', 'Guarani', '₲');
INSERT INTO public.currencies VALUES (117, 'PEN', 'Sol', 'S/');
INSERT INTO public.currencies VALUES (118, 'PHP', 'Philippine Peso', '₱');
INSERT INTO public.currencies VALUES (119, 'PLN', 'Zloty', 'zł');
INSERT INTO public.currencies VALUES (120, 'QAR', 'Qatari Rial', 'ر.ق');
INSERT INTO public.currencies VALUES (121, 'RON', 'Romanian Leu', 'lei');
INSERT INTO public.currencies VALUES (122, 'RUB', 'Russian Ruble', '₽');
INSERT INTO public.currencies VALUES (123, 'RWF', 'Rwanda Franc', 'Fr');
INSERT INTO public.currencies VALUES (124, 'SHP', 'Saint Helena Pound', '£');
INSERT INTO public.currencies VALUES (125, 'WST', 'Tala', 'T');
INSERT INTO public.currencies VALUES (126, 'STN', 'Dobra', 'Db');
INSERT INTO public.currencies VALUES (127, 'SAR', 'Saudi Riyal', 'ر.س');
INSERT INTO public.currencies VALUES (128, 'RSD', 'Serbian Dinar', 'дин.');
INSERT INTO public.currencies VALUES (129, 'SCR', 'Seychelles Rupee', '₨');
INSERT INTO public.currencies VALUES (130, 'SLE', 'Leone', 'Le');
INSERT INTO public.currencies VALUES (131, 'SGD', 'Singapore Dollar', '$');
INSERT INTO public.currencies VALUES (132, 'XSU', 'Sucre', 'XSU');
INSERT INTO public.currencies VALUES (133, 'SBD', 'Solomon Islands Dollar', '$');
INSERT INTO public.currencies VALUES (134, 'SOS', 'Somali Shilling', 'Sh');
INSERT INTO public.currencies VALUES (135, 'SSP', 'South Sudanese Pound', '£');
INSERT INTO public.currencies VALUES (136, 'LKR', 'Sri Lanka Rupee', 'Rs');
INSERT INTO public.currencies VALUES (137, 'SDG', 'Sudanese Pound', 'ج.س.');
INSERT INTO public.currencies VALUES (138, 'SRD', 'Surinam Dollar', '$');
INSERT INTO public.currencies VALUES (139, 'SZL', 'Lilangeni', 'L');
INSERT INTO public.currencies VALUES (140, 'SEK', 'Swedish Krona', 'kr');
INSERT INTO public.currencies VALUES (141, 'CHE', 'WIR Euro', 'CHE');
INSERT INTO public.currencies VALUES (142, 'CHW', 'WIR Franc', 'CHW');
INSERT INTO public.currencies VALUES (143, 'SYP', 'Syrian Pound', '£');
INSERT INTO public.currencies VALUES (144, 'TWD', 'New Taiwan Dollar', 'NT$');
INSERT INTO public.currencies VALUES (145, 'TJS', 'Somoni', 'SM');
INSERT INTO public.currencies VALUES (146, 'TZS', 'Tanzanian Shilling', 'Sh');
INSERT INTO public.currencies VALUES (147, 'THB', 'Baht', '฿');
INSERT INTO public.currencies VALUES (148, 'TOP', 'Pa''anga', 'T$');
INSERT INTO public.currencies VALUES (149, 'TTD', 'Trinidad and Tobago Dollar', '$');
INSERT INTO public.currencies VALUES (150, 'TND', 'Tunisian Dinar', 'د.ت');
INSERT INTO public.currencies VALUES (151, 'TRY', 'Turkish Lira', '₺');
INSERT INTO public.currencies VALUES (152, 'TMT', 'Turkmenistan New Manat', 'm');
INSERT INTO public.currencies VALUES (153, 'UGX', 'Uganda Shilling', 'Sh');
INSERT INTO public.currencies VALUES (154, 'UAH', 'Hryvnia', '₴');
INSERT INTO public.currencies VALUES (155, 'AED', 'UAE Dirham', 'د.إ');
INSERT INTO public.currencies VALUES (156, 'USN', 'US Dollar (Next day)', 'USN');
INSERT INTO public.currencies VALUES (157, 'UYI', 'Uruguay Peso en Unidades Indexadas (UI)', 'UYI');
INSERT INTO public.currencies VALUES (158, 'UYW', 'Unidad Previsional', 'UYW');
INSERT INTO public.currencies VALUES (159, 'UYU', 'Peso Uruguayo', '$');
INSERT INTO public.currencies VALUES (160, 'UZS', 'Uzbekistan Sum', 'soʻm');
INSERT INTO public.currencies VALUES (161, 'VUV', 'Vatu', 'Vt');
INSERT INTO public.currencies VALUES (162, 'VES', 'Bolívar Soberano', 'Bs.S');
INSERT INTO public.currencies VALUES (163, 'VED', 'Bolívar Soberano', 'Bs.D');
INSERT INTO public.currencies VALUES (164, 'VND', 'Dong', '₫');
INSERT INTO public.currencies VALUES (165, 'YER', 'Yemeni Rial', '﷼');
INSERT INTO public.currencies VALUES (166, 'ZMW', 'Zambian Kwacha', 'ZK');
INSERT INTO public.currencies VALUES (167, 'ZWG', 'Zimbabwe Gold', 'ZiG');
INSERT INTO public.currencies VALUES (170, 'abc', 'lit', 'sign');
INSERT INTO public.currencies VALUES (1, 'EUR', 'Euro', '€');


--
-- Data for Name: exchange_rates; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.exchange_rates VALUES (2, 1, 4, 12.000000);
INSERT INTO public.exchange_rates VALUES (3, 5, 7, 0.493480);
INSERT INTO public.exchange_rates VALUES (10, 1, 100, 1.200000);
INSERT INTO public.exchange_rates VALUES (11, 1, 122, 0.010000);
INSERT INTO public.exchange_rates VALUES (1, 1, 2, 0.390000);
INSERT INTO public.exchange_rates VALUES (13, 5, 122, 1.200000);
INSERT INTO public.exchange_rates VALUES (5, 5, 1, 1.300000);
INSERT INTO public.exchange_rates VALUES (17, 2, 122, 0.500000);


--
-- Name: currencies_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.currencies_id_seq', 174, true);


--
-- Name: exchange_rates_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.exchange_rates_id_seq', 17, true);


--
-- PostgreSQL database dump complete
--

\unrestrict PUYCY9pPpikUCSCNxg7N9VL4gez5QSwERVedRprBVDPWu5wgeJ5BkHHzKmkcLnc

