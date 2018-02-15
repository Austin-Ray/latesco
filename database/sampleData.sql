truncate stock;

INSERT INTO stock (
	id, 
	name, 
	symbol, 
	rank, 
	price_usd, 
	price_btc, 
	h24_volume_usd, 
	market_cap_usd, 
	available_supply,
	total_supply,
	percent_change_1h,
	percent_change_24h,
	percent_change_7d,
	last_updated) 
VALUES (
	 'bitcoin',
	 'Bitcoin',
	 'BTC',
	 '1',
     '573.137',
     '1.0',
     '72855700.0',
     '9080883500.0',
     '15844176.0',
     '15844176.0',
     '0.04',
     '-0.3',
     '-0.57',
     '1472762067'
);						  
