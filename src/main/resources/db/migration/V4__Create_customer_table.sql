--
-- Name: shop; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

create table customer (
    customer_id bigint NOT NULL,
    email varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    mobile_number varchar(12) NOT NULL,
    shop_id bigint,
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
    address varchar(255) not null,
    city varchar(255) not null,
    country varchar(255) not null,
    state varchar(255) not null,
    zipcode varchar(255) not null,
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


