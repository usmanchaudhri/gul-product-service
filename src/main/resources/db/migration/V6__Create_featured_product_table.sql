--
-- Name: shop; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

create table FEATURED_PRODUCT (
    featured_product_id bigint not null,
    created_on timestamp,
    updated_on timestamp,    
    primary key (featured_product_id)
);
    
CREATE SEQUENCE featuredproducts_featuredproducts_id_seq 
	START WITH 1 
	INCREMENT BY 1
	NO MINVALUE
    NO MAXVALUE
    CACHE 1;

