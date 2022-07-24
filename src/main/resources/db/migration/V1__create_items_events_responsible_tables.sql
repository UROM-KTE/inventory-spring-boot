CREATE TABLE departments
(
    responsible_id BIGINT NOT NULL,
    leader_id      BIGINT NOT NULL,
    CONSTRAINT pk_departments PRIMARY KEY (responsible_id)
);

CREATE TABLE employees
(
    responsible_id BIGINT NOT NULL,
    email          VARCHAR(254) CHECK (email ~ '^[a-zA-Z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$'
) NOT NULL,
    CONSTRAINT pk_employees PRIMARY KEY (responsible_id),
    CONSTRAINT uc_employee_email UNIQUE (email)
    );

CREATE TABLE events
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY                                NOT NULL,
    date            DATE CHECK (date < CURRENT_DATE AND date > '1990-01-01') NOT NULL,
    note_number     VARCHAR(50),
    event_type      VARCHAR(11)                                                            NOT NULL,
    description     TEXT,
    old_responsible BIGINT,
    new_responsible BIGINT,
    CONSTRAINT pk_events PRIMARY KEY (id)
);

CREATE TABLE inventory_items
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY                                NOT NULL,
    inventory_id      VARCHAR(50)                                                            NOT NULL,
    item_type         VARCHAR(16)                                                            NOT NULL,
    name              VARCHAR(100)                                                           NOT NULL,
    date_of_use       DATE CHECK (date_of_use < CURRENT_DATE AND date_of_use > '1990-01-01') NOT NULL,
    description       TEXT,
    serial_number     VARCHAR(100),
    category          VARCHAR(100)                                                           NOT NULL,
    kit_id            BIGINT,
    additional_fields TEXT,
    location          VARCHAR(100),
    to_disposal       BOOLEAN                                                                NOT NULL,
    deficit           BOOLEAN                                                                NOT NULL,
    initial_price     DOUBLE PRECISION CHECK (initial_price >= 0)                            NOT NULL,
    amount            INT CHECK (amount >= 0)                                                NOT NULL,
    responsible_id    BIGINT                                                                 NOT NULL,
    CONSTRAINT pk_inventory_items PRIMARY KEY (id),
    CONSTRAINT uc_inventory_items_inventory UNIQUE (inventory_id)
);

CREATE TABLE inventory_items_events
(
    event_id          BIGINT NOT NULL,
    inventory_item_id BIGINT NOT NULL
);

CREATE TABLE kits
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(100)                            NOT NULL,
    CONSTRAINT pk_kits PRIMARY KEY (id)
);

CREATE TABLE responsible
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(150)                            NOT NULL,
    CONSTRAINT pk_responsible PRIMARY KEY (id)
);

ALTER TABLE departments
    ADD CONSTRAINT fk_departments_on_leader FOREIGN KEY (leader_id) REFERENCES employees (responsible_id);

ALTER TABLE departments
    ADD CONSTRAINT fk_departments_on_responsible FOREIGN KEY (responsible_id) REFERENCES responsible (id);

ALTER TABLE employees
    ADD CONSTRAINT fk_employees_on_responsible FOREIGN KEY (responsible_id) REFERENCES responsible (id);

ALTER TABLE events
    ADD CONSTRAINT fk_events_on_new_responsible FOREIGN KEY (new_responsible) REFERENCES responsible (id);

ALTER TABLE events
    ADD CONSTRAINT fk_events_on_old_responsible FOREIGN KEY (old_responsible) REFERENCES responsible (id);

ALTER TABLE inventory_items
    ADD CONSTRAINT fk_inventory_items_on_kit FOREIGN KEY (kit_id) REFERENCES kits (id);

ALTER TABLE inventory_items
    ADD CONSTRAINT fk_inventory_items_on_responsible FOREIGN KEY (responsible_id) REFERENCES responsible (id);

ALTER TABLE inventory_items_events
    ADD CONSTRAINT fk_inviteeve_on_event FOREIGN KEY (event_id) REFERENCES events (id);

ALTER TABLE inventory_items_events
    ADD CONSTRAINT fk_inviteeve_on_inventory_item FOREIGN KEY (inventory_item_id) REFERENCES inventory_items (id);