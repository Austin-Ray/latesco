Version: psql (10.2)


**Current tables and data are a direct reflection of the sample API call from coin market.
** Will add more table functionality and stuff later. Just use this as a basis for now.


To start the postgres DB system:
    pg_ctl -D /usr/local/var/postgres start

To start postgres:
    psql postgres -U <user>;


Create DB:
	***DB's are created under the current user
	createdb <dbname>;

create user:
	create user <name> with password <password>;

Check users:
    \du

Check all tables:
	\d

Check databases:
    \l

To connect to database:
    \connect <db name>;

Upload script file into db
	psql -d <DB name> -a -f <filename/path>

** Be advise when inserting values, it only takes ' and not ".
