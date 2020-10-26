CREATE TABLE Product (
    id varchar(255) PRIMARY KEY NOT NULL,
    name text NOT NULL,
    category text NOT NULL,
    description text NOT NULL,
    availability text,
    price numeric(18,6) NOT NULL,
    quantity integer DEFAULT 0,
    supplierId varchar(255) DEFAULT NULL
);
--
--ALTER TABLE Product ALTER COLUMN id SET NOT NULL;
--ALTER TABLE Product ALTER COLUMN name SET NOT NULL;
--ALTER TABLE Product ALTER COLUMN category SET NOT NULL;
--ALTER TABLE Product ALTER COLUMN description SET NOT NULL;
--ALTER TABLE Product ALTER COLUMN availability SET DEFAULT NULL;
--ALTER TABLE Product ALTER COLUMN price SET NOT NULL;
--ALTER TABLE Product ALTER COLUMN quantity SET DEFAULT 0;
--ALTER TABLE Product ALTER COLUMN supplierId SET DEFAULT NULL;
