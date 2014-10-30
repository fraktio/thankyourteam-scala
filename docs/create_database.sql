CREATE SCHEMA thankyourteam;
CREATE USER thankyourteam PASSWORD 'th4nky0urt34m';
GRANT ALL ON SCHEMA thankyourteam TO thankyourteam;
GRANT ALL ON ALL TABLES IN SCHEMA thankyourteam TO thankyourteam;

CREATE SEQUENCE serial START 1;

CREATE TABLE entry (
  id            integer PRIMARY KEY DEFAULT nextval('serial'),
  shorturl      varchar(50) NOT NULL,
  author        varchar(100) NOT NULL,
  message       varchar(255) NOT NULL,
  created       timestamp DEFAULT current_timestamp
);

ALTER TABLE serial OWNER TO thankyourteam;
ALTER TABLE entry OWNER TO thankyourteam;