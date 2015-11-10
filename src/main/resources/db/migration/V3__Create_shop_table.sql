--
-- Name: shop; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

CREATE TABLE shop (
    shop_id bigint NOT NULL,
    shop_name character varying(255) NOT NULL
);

CREATE SEQUENCE shop_shop_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

