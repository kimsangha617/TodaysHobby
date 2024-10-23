create table Product
(
    product_id   bigint auto_increment,
    seller_id    bigint not null,
    korean_name  varchar(255),
    english_name varchar(255),
    description  varchar(255),
    brand_id     bigint,
    category_id  bigint,
    thumbnail_image_path varchar(255),
    primary key (product_id)
) engine=InnoDB;

create index idx_seller_id on product (product_id);

INSERT into product (seller_id, korean_name, english_name, description, brand_id, category_id, thumbnail_image_path) values (1, '냉면', 'korean cool noodle', '냉면이다', 1, 1, null);
INSERT into product (seller_id, korean_name, english_name, description, brand_id, category_id, thumbnail_image_path) values (1, '부츠', 'boots', '부츠', 2, 2, null);
INSERT into product (seller_id, korean_name, english_name, description, brand_id, category_id, thumbnail_image_path) values (1, '청바지', 'NewJeans', '새로운 청바지', 3, 2, null);
