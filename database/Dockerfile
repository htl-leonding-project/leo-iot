FROM timescale/timescaledb:latest-pg12

ENV PGDATA=/var/lib/postgresql/data/timescaledb

EXPOSE 5432

ADD docker-entrypoint-initdb.d/start.sh /docker-entrypoint-initdb.d/init-user-db.sh
