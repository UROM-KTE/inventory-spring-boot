#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE USER inventory
  WITH PASSWORD 'inventory';

CREATE DATABASE inventory
    WITH OWNER = inventory
    TEMPLATE = 'template0'
    ENCODING = 'UTF-8'
    LC_COLLATE = 'hu_HU.UTF-8'
    LC_CTYPE = 'hu_HU.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE USER keycloak
WITH PASSWORD 'keycloak';

CREATE DATABASE keycloak
    WITH OWNER = keycloak
    TEMPLATE = 'template0'
    ENCODING = 'UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
EOSQL