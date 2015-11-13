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
