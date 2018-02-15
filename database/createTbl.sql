drop table if exists stock;

create table currency(
	id					text,
	name				text,
	symbol  			char(3),
	rank				int,
	price_usd			real,
	price_btc			real,
	h24_volume_usd		real,
    market_cap_usd		real,
    available_supply	real,
    total_supply		real,
    percent_change_1h	real,
    percent_change_24h	real,
    percent_change_7d	real,
    last_updated 		int,
	primary key (id)
) 


