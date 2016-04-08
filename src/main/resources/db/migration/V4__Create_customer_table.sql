--
-- Name: shop; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

create table customer (
    customer_id bigint NOT NULL,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    shop_id bigint,
    cchat_id bigint,
    created_on timestamp,
    updated_on timestamp,    
    primary key (customer_id)
);

CREATE SEQUENCE customer_customer_id_seq 
	START WITH 1 
	INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
create table CUSTOMER_SHIPPING (
    customer_shipping_id bigint not null,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    address varchar(255) not null,
    city varchar(255) not null,
    country varchar(255) not null,
    state varchar(255) not null,
    zipcode varchar(255) not null,
    customer_id bigint,
    created_on timestamp,
    updated_on timestamp,    
    primary key (customer_shipping_id)
);
       
create table CUSTOMER_CUSTOMER_SHIPPING (
    CUSTOMER_customer_id bigint not null,
    customerShipping_customer_shipping_id bigint not null
);    

CREATE SEQUENCE customershipping_customershipping_id_seq 
	START WITH 1 
	INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

create table CCHAT (
	cchat_id bigint not null,
	unique_name varchar(255) not null,
	customer_id bigint,
	customer_username varchar(255),
	shopowner_username varchar(255),
	primary key (cchat_id)
);

create sequence 
	cchat_cchat_id_seq 
	start with 1 
	increment by 1;

    
    