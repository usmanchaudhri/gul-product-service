--
-- Name: shop; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--


create table PRODUCT_VARIATION (
    productvariation_id bigint not null,
    color varchar(255),
    material varchar(255),
    mics varchar(255),
    price varchar(255),
    quantity varchar(255),
    size varchar(255),
    product_id bigint not null,
    created_on timestamp,
    updated_on timestamp,    
    primary key (productvariation_id)
);
    
CREATE SEQUENCE productvariation_productvariation_id_seq 
	START WITH 1 
	INCREMENT BY 1
	NO MINVALUE
    NO MAXVALUE
    CACHE 1;

