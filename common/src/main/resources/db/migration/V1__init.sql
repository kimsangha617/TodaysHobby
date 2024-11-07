CREATE TABLE IF NOT EXISTS revinfo (
    REV INTEGER NOT NULL AUTO_INCREMENT,
    REVTSTMP BIGINT,
    PRIMARY KEY (REV)
);

create table IF not exists product
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

create table IF not exists product_aud (
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

create table IF not exists product_item
(
    product_item_id   bigint auto_increment,
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
create index idx_product_item_id on product_item (product_item_id);

create table IF not exists product_item_aud (
    product_item_id   bigint auto_increment,
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


create table IF not exists brand
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
create index idx_brand_id on brand (brand_id);

create table IF not exists brand_aud (
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


create table IF not exists category
(
    category_id   bigint auto_increment,
    korean_name varchar(255),
    english_name varchar(255),
    parent_id bigint,
    depth_level integer,
    created_at DATETIME(6),
    modified_at DATETIME(6),
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    primary key (category_id)
    ) engine=InnoDB;
create index idx_category_id on category (category_id);

create table IF not exists category_aud (
    category_id BIGINT auto_increment,
    korean_name varchar(255),
    english_name varchar(255),
    parent_id bigint,
    depth_level integer,
    created_at DATETIME(6),
    modified_at DATETIME(6),
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    REV INTEGER NOT NULL,
    REVTYPE TINYINT,
    PRIMARY KEY (category_id, REV)
    );

create table if not exists sku
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
create index idx_sku_id on sku (sku_id);

create table if not exists sku_aud (
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

create table if not exists stock
(
    stock_id   bigint auto_increment,
    stock_quantity integer,
    version bigint,
    sku_id bigint,
    stock_status varchar(255),
    created_at DATETIME(6),
    modified_at DATETIME(6),
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    primary key (stock_id)
) engine=InnoDB;
create index idx_stock_id on sku (stock_id);

create table if not exists stock_aud (
    stock_id   bigint auto_increment,
    stock_quantity integer,
    version bigint,
    sku_id bigint,
    stock_status varchar(255),
    created_at DATETIME(6),
    modified_at DATETIME(6),
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    REV INTEGER NOT NULL,
    REVTYPE TINYINT,
    PRIMARY KEY (stock_id, REV)
);