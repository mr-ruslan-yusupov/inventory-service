CREATE SEQUENCE hibernate_sequence START 1;

-- Table: store_inventory.tbl_stores
CREATE TABLE IF NOT EXISTS store_inventory.tbl_stores
(
    store_id bigint NOT NULL,
    store_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    store_address character varying(150) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tbl_stores_pkey PRIMARY KEY (store_id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS store_inventory.tbl_stores
    OWNER to postgres;

-- Table: store_inventory.tbl_categories
CREATE TABLE IF NOT EXISTS store_inventory.tbl_categories
(
    category_id bigint NOT NULL,
    category_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tbl_categories_pkey PRIMARY KEY (category_id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS store_inventory.tbl_categories
    OWNER to postgres;

-- Table: store_inventory.tbl_brands
CREATE TABLE IF NOT EXISTS store_inventory.tbl_brands
(
    brand_id bigint NOT NULL,
    brand_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tbl_brands_pkey PRIMARY KEY (brand_id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS store_inventory.tbl_brands
    OWNER to postgres;

-- Table: store_inventorytbl_products
CREATE TABLE IF NOT EXISTS store_inventory.tbl_products
(
    product_id bigint NOT NULL,
    product_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    product_description character varying(300) COLLATE pg_catalog."default" NOT NULL,
    product_price decimal NOT NULL,
    catalog_number character varying(25),
    category_id bigint NOT NULL DEFAULT (0),
    brand_id bigint NOT NULL DEFAULT (0),
    CONSTRAINT tbl_products_pkey PRIMARY KEY (product_id),
    CONSTRAINT tbl_products_category_id_fkey FOREIGN KEY (category_id)
        REFERENCES store_inventory.tbl_categories (category_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE SET DEFAULT,
    CONSTRAINT tbl_products_brand_id_fkey FOREIGN KEY (brand_id)
        REFERENCES store_inventory.tbl_brands (brand_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE SET DEFAULT
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS store_inventory.tbl_products
    OWNER to postgres;

-- Table: store_inventory.tbl_inventory
CREATE TABLE IF NOT EXISTS store_inventory.tbl_items
(
    product_id bigint,
    store_id bigint,
    quantity bigint,
    CONSTRAINT tbl_items_product_id_fkey FOREIGN KEY (product_id)
        REFERENCES store_inventory.tbl_products (product_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT tbl_items_store_id_fkey FOREIGN KEY (store_id)
        REFERENCES store_inventory.tbl_stores (store_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS store_inventory.tbl_items
    OWNER to postgres;
