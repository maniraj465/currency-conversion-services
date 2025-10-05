-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile


-- Insert data in currency_exchange table
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10001, 'USD', 'INR', 86);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10002, 'EUR', 'INR', 90);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10003, 'AUD', 'INR', 81);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10004, 'CAD', 'INR', 55);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10005, 'GBP', 'INR', 70);

-- Major World Currencies to INR
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10006, 'JPY', 'INR', 0.58);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10007, 'CHF', 'INR', 98);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10008, 'CNY', 'INR', 11.85);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10009, 'SGD', 'INR', 63.70);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10010, 'NZD', 'INR', 52);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10011, 'HKD', 'INR', 11);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10012, 'SEK', 'INR', 8.20);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10013, 'NOK', 'INR', 8.10);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10014, 'DKK', 'INR', 12.35);

-- Asian Currencies to INR
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10015, 'KRW', 'INR', 0.065);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10016, 'THB', 'INR', 2.37);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10017, 'MYR', 'INR', 18.35);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10018, 'IDR', 'INR', 0.0055);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10019, 'PHP', 'INR', 1.51);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10020, 'VND', 'INR', 0.0035);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10021, 'BDT', 'INR', 0.78);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10022, 'PKR', 'INR', 0.31);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10023, 'LKR', 'INR', 0.28);

-- Middle Eastern Currencies to INR
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10024, 'AED', 'INR', 23.40);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10025, 'SAR', 'INR', 22.90);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10026, 'QAR', 'INR', 23.60);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10027, 'KWD', 'INR', 280);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10028, 'OMR', 'INR', 223);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10029, 'BHD', 'INR', 228);

-- European Currencies to INR
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10030, 'RUB', 'INR', 0.92);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10031, 'PLN', 'INR', 21.50);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10032, 'HUF', 'INR', 0.23);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10033, 'CZK', 'INR', 3.65);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10034, 'RON', 'INR', 18.20);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10035, 'BGN', 'INR', 46);

-- American Currencies to INR
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10036, 'BRL', 'INR', 17.20);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10037, 'MXN', 'INR', 5.10);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10038, 'ARS', 'INR', 0.10);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10039, 'CLP', 'INR', 0.095);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10040, 'COP', 'INR', 0.020);

-- African Currencies to INR
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10041, 'ZAR', 'INR', 4.60);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10042, 'EGP', 'INR', 2.78);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10043, 'NGN', 'INR', 0.11);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10044, 'KES', 'INR', 0.65);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10045, 'GHS', 'INR', 7.20);

-- Reverse Conversions (INR to other currencies)
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10046, 'INR', 'USD', 0.0116);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10047, 'INR', 'EUR', 0.0111);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10048, 'INR', 'GBP', 0.0143);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10049, 'INR', 'AUD', 0.0123);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10050, 'INR', 'CAD', 0.0182);

-- Cross Currency Conversions
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10051, 'EUR', 'USD', 1.09);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10052, 'GBP', 'USD', 1.28);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10053, 'EUR', 'GBP', 0.85);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10054, 'JPY', 'USD', 0.0067);
insert into currency_exchange(id, currency_from, currency_to, conversion_multiple) values(10055, 'AUD', 'USD', 0.65);






