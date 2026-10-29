# Currency Exchange

Веб-приложение на Java для просмотра валют и курсов и расчёта суммы конвертации. Данные хранятся в PostgreSQL.

## Примеры запросов

- `GET /currencies` — список валют.
- `GET /exchangeRates` — список курсов обмена.
- `GET /exchange?from=USD&to=EUR&amount=100` — конвертация 100 долларов США в евро.
