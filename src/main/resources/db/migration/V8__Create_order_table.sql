--
-- Name: shop; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

create table ORDER_PRODUCT (
    order_id bigint not null,
    product_category_id varchar(255),
    product_id varchar(255),
    product_image_path varchar(255),
    product_name varchar(255),
    product_price varchar(255),
    product_quantity varchar(255),
    product_shop_id varchar(255),
    product_sku varchar(255),
    customer_id bigint,
    status varchar(255),
    created_on timestamp,
    updated_on timestamp,
    primary key (order_id)
);


CREATE SEQUENCE order_order_id_seq 
	START WITH 1 
	INCREMENT BY 1
	NO MINVALUE
    NO MAXVALUE
    CACHE 1;

