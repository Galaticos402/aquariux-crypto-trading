INSERT INTO trader (trader_name, created_at) VALUES ('trader', CURRENT_TIMESTAMP);

INSERT INTO wallet (asset, balance, trader_id, created_at) VALUES ('USDT', '50000', 1, CURRENT_TIMESTAMP);

INSERT INTO wallet (asset, balance, trader_id, created_at) VALUES ('ETH', '0', 1, CURRENT_TIMESTAMP);

INSERT INTO wallet (asset, balance, trader_id, created_at) VALUES ('BTC', '0', 1, CURRENT_TIMESTAMP);