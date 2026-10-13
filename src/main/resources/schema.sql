CREATE TABLE currencies (
    id serial primary key,
    code varchar(3) unique not null,
    fullname varchar not null,
    sign varchar not null
    );

CREATE TABLE exchange_rates (
    id serial primary key,
    base_сurrency_id int
        references currencies(id)
        on delete cascade
        not null ,
    target_currency_id int
        references currencies(id)
        on delete cascade
        not null,
    rate numeric(14, 6) not null,

    constraint unique_comb UNIQUE (base_сurrency_id, target_currency_id),
    constraint check_diff CHECK (base_сurrency_id <> target_currency_id)
)