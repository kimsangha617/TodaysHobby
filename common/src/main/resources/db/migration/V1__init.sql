CREATE TABLE IF NOT EXISTS REVINFO (
    REV INTEGER NOT NULL AUTO_INCREMENT,
    REVTSTMP BIGINT,
    PRIMARY KEY (REV)
);

create table IF not exists PRODUCT
(
    product_id   bigint auto_increment,
    seller_id    bigint not null,
    korean_name  varchar(255),
    english_name varchar(255),
    description  varchar(255),
    brand_id     bigint,
    category_id  bigint,
    thumbnail_image_path varchar(255),
    created_at DATETIME(6),
    modified_at DATETIME(6),
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    primary key (product_id)
) engine=InnoDB;
create index idx_product_id on product (product_id);

create table IF not exists PRODUCT_AUD (
   product_id   bigint auto_increment,
   seller_id    bigint not null,
   korean_name  varchar(255),
   english_name varchar(255),
   description  varchar(255),
   brand_id     bigint,
   category_id  bigint,
   thumbnail_image_path varchar(255),
   created_at DATETIME(6),
   modified_at DATETIME(6),
   created_by VARCHAR(255),
   modified_by VARCHAR(255),
   REV INTEGER NOT NULL,
   REVTYPE TINYINT,
   PRIMARY KEY (product_id, REV)
);


INSERT into product (seller_id, korean_name, english_name, description, brand_id, category_id, thumbnail_image_path) values (1, '냉면', 'korean cool noodle', '냉면이다', 1, 1, null);
INSERT into product (seller_id, korean_name, english_name, description, brand_id, category_id, thumbnail_image_path) values (1, '부츠', 'boots', '부츠', 2, 2, null);
INSERT into product (seller_id, korean_name, english_name, description, brand_id, category_id, thumbnail_image_path) values (1, '청바지', 'NewJeans', '새로운 청바지', 3, 2, null);

create table IF not exists PRODUCT_ITEM
(
    product_item_id   bigint auto_increment,
    seller_id    bigint not null,
    name  varchar(255),
    price DECIMAL(19),
    stock_quantity integer,
    size  varchar(4),
    color  varchar(10),
    status  varchar(255),
    category_id  bigint,
    product_id  bigint,
    sku_id  bigint,
    created_at DATETIME(6),
    modified_at DATETIME(6),
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    primary key (product_item_id)
    ) engine=InnoDB;
create index idx_product_item_id on Product_item (product_item_id);

create table IF not exists PRODUCT_ITEM_AUD (
    product_item_id   bigint auto_increment,
    seller_id    bigint not null,
    name  varchar(255),
    price DECIMAL(19),
    stock_quantity integer,
    size  varchar(4),
    color  varchar(10),
    status  varchar(255),
    category_id  bigint,
    product_id  bigint,
    sku_id  bigint,
    created_at DATETIME(6),
    modified_at DATETIME(6),
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    REV INTEGER NOT NULL,
    REVTYPE TINYINT,
    PRIMARY KEY (product_item_id, REV)
    );


create table IF not exists BRAND
(
    brand_id   bigint auto_increment,
    korean_name  varchar(255),
    english_name varchar(255),
    category_id  bigint,
    thumbnail_image_path varchar(255),
    created_at DATETIME(6),
    modified_at DATETIME(6),
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    primary key (brand_id)
) engine=InnoDB;
create index idx_brand_id on Brand (brand_id);

create table IF not exists BRAND_AUD (
   brand_id BIGINT auto_increment,
   korean_name  varchar(255),
   english_name varchar(255),
   category_id  bigint,
   thumbnail_image_path varchar(255),
   created_at DATETIME(6),
   modified_at DATETIME(6),
   created_by VARCHAR(255),
   modified_by VARCHAR(255),
   REV INTEGER NOT NULL,
   REVTYPE TINYINT,
   PRIMARY KEY (brand_id, REV)
);
ALTER TABLE brand_aud ADD CONSTRAINT FK_brand_aud_revinfo FOREIGN KEY (REV) REFERENCES revinfo(REV);

INSERT into Brand (korean_name, english_name, category_id, thumbnail_image_path) values ('나이키', 'NIKE', 1, null);
INSERT into Brand (korean_name, english_name, category_id, thumbnail_image_path) values ('나이키', 'NIKE', 1, null);
INSERT into Brand (korean_name, english_name, category_id, thumbnail_image_path) values ('캘빈클라인', 'Calvin Klein', 2, null);


create table IF not exists CATEGORY
(
    category_id   bigint auto_increment,
    name varchar(255),
    created_at DATETIME(6),
    modified_at DATETIME(6),
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    primary key (category_id)
    ) engine=InnoDB;
create index idx_category_id on Category (category_id);

create table IF not exists CATEGORY_AUD (
    category_id BIGINT auto_increment,
    name varchar(255),
    created_at DATETIME(6),
    modified_at DATETIME(6),
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    REV INTEGER NOT NULL,
    REVTYPE TINYINT,
    PRIMARY KEY (category_id, REV)
    );

create table if not exists SKU
(
    sku_id   bigint auto_increment,
    sku_code varchar(255),
    product_id bigint,
    name varchar(255),
    color varchar(10),
    size varchar(4),
    stock_quantity integer,
    product_item_id bigint,
    created_at DATETIME(6),
    modified_at DATETIME(6),
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    primary key (sku_id)
    ) engine=InnoDB;
create index idx_sku_id on SKU (sku_id);

create table if not exists SKU_AUD (
    sku_id   bigint auto_increment,
    sku_code varchar(255),
    product_id bigint,
    name varchar(255),
    color varchar(10),
    size varchar(4),
    stock_quantity integer,
    product_item_id bigint,
    created_at DATETIME(6),
    modified_at DATETIME(6),
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    REV INTEGER NOT NULL,
    REVTYPE TINYINT,
    PRIMARY KEY (sku_id, REV)
    );
