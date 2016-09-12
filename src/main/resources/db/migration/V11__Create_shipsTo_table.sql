--
-- Name: Shipping; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

create table SHIPPING (
    shipping_id bigint not null,
    country_name varchar(255) not null,
    created_on timestamp,
    shipping_processing_time bigint,
    shipping_cost double,
    updated_on timestamp,
    shippingFrom_shipsTo_id bigint,
    primary key (shipsTo_id)
);

create sequence SHIPPING_SHIPPING_ID_SEQ 
	START WITH 1 
	INCREMENT BY 1
	NO MINVALUE
    NO MAXVALUE
    CACHE 1;

    