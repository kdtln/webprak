DROP TABLE IF EXISTS deal, clients, services, workers;

CREATE TABLE clients (
    id_client SERIAL PRIMARY KEY,
    company_name text,
    face text NOT NULL,
    email text NOT NULL,
    login text NOT NULL,
    password text NOT NULL
);

CREATE TABLE services (
    id_service SERIAL PRIMARY KEY,
    name text NOT NULL,
    price numeric NOT NULL,
    describe text
);

CREATE TABLE workers (
    id_worker SERIAL PRIMARY KEY,
    full_name text NOT NULL ,
    address text,
    phone text NOT NULL,
    email text NOT NULL,
    education text,
    post text NOT NULL ,
    login text NOT NULL ,
    password text NOT NULL,
    is_admin int NOT NULL
);

CREATE TABLE deal (
    id_deal SERIAL PRIMARY KEY,
    client int references clients(id_client),
    worker int references workers(id_worker),
    service int references services(id_service),
    start_date date,
    end_date date,
    description text
);