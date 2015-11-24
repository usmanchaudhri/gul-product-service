--
-- Name: category; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

CREATE TABLE category (
    category_id bigint NOT NULL,
    code character varying(255),
    name character varying(255) NOT NULL,
    parentcategory_category_id bigint,
    created_on date,
    updated_on date

);

CREATE SEQUENCE category_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;