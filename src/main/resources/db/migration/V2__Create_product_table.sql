--
-- Name: category; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

create table PRODUCT (
    product_id bigint not null,
    created_on timestamp,
    long_desc varchar(255),
    name varchar(255) not null,
    quantity bigint not null,
    short_desc varchar(255) not null,
    sku varchar(255) not null,
    updated_on timestamp,
    category_id bigint not null,
    featured_product_id bigint,
    pricing_product_id bigint,
    shipsto_id bigint,
    shop_id bigint not null,
    image_info_id bigint not null,
    customize boolean,
    primary key (product_id)
);

CREATE SEQUENCE product_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
CREATE TABLE pricing_product (
    pricing_product_id bigint NOT NULL,
    stored_value numeric(4,2) NOT NULL,
    created_on timestamp,
    updated_on timestamp,
    
);

CREATE SEQUENCE pricing_product_pricing_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
      
create table image_info (
    image_info_id bigint NOT NULL,
    image_path varchar(255),
    image_count varchar(255),
    created_on timestamp,
    updated_on timestamp,    
    primary key (image_info_id)
);    
    
CREATE SEQUENCE imageinfo_imageinfo_id_seq 
	START WITH 1 
	INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
