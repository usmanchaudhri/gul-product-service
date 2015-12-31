--
-- Name: shop; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

create table NOTES (
    notes_id bigint not null,
    message varchar(255),
    buyer_id bigint not null,
    seller_id bigint not null,
    primary key (notes_id)
);

create table CUSTOMER_NOTES (
    CUSTOMER_customer_id bigint not null,
    notes_featured_product_id bigint not null
);

CREATE SEQUENCE notes_notes_id_seq 
	START WITH 1 
	INCREMENT BY 1
	NO MINVALUE
    NO MAXVALUE
    CACHE 1;

