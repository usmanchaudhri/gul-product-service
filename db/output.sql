--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

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


ALTER TABLE category OWNER TO test;

--
-- Name: category_category_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE category_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE category_category_id_seq OWNER TO test;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

CREATE TABLE customer (
    customer_id bigint NOT NULL,
    email character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    mobile_number character varying(12) NOT NULL,
    shop_id bigint
);


ALTER TABLE customer OWNER TO test;

--
-- Name: customer_customer_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE customer_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE customer_customer_id_seq OWNER TO test;

--
-- Name: customer_shipping; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

CREATE TABLE customer_shipping (
    customer_shipping_id bigint NOT NULL,
    address character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    country character varying(255) NOT NULL,
    state character varying(255) NOT NULL,
    zipcode character varying(255) NOT NULL
);


ALTER TABLE customer_shipping OWNER TO test;

--
-- Name: customershipping_customershipping_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE customershipping_customershipping_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE customershipping_customershipping_id_seq OWNER TO test;

--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

CREATE TABLE databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20)
);


ALTER TABLE databasechangelog OWNER TO test;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

CREATE TABLE databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE databasechangeloglock OWNER TO test;

--
-- Name: featured_product; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

CREATE TABLE featured_product (
    featured_product_id bigint NOT NULL
);


ALTER TABLE featured_product OWNER TO test;

--
-- Name: featuredproducts_featuredproducts_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE featuredproducts_featuredproducts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE featuredproducts_featuredproducts_id_seq OWNER TO test;

--
-- Name: order_order_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE order_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE order_order_id_seq OWNER TO test;

--
-- Name: order_product; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

CREATE TABLE order_product (
    order_id bigint NOT NULL,
    product_category_id character varying(255) NOT NULL,
    product_id character varying(255) NOT NULL,
    product_image_path character varying(255) NOT NULL,
    product_name character varying(255) NOT NULL,
    product_price character varying(255) NOT NULL,
    product_quantity character varying(255) NOT NULL,
    product_shop_id character varying(255) NOT NULL,
    product_sku character varying(255) NOT NULL,
    customer_id bigint NOT NULL
);


ALTER TABLE order_product OWNER TO test;

--
-- Name: pricing_product; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

CREATE TABLE pricing_product (
    pricing_product_id bigint NOT NULL,
    stored_value numeric(4,2) NOT NULL
);


ALTER TABLE pricing_product OWNER TO test;

--
-- Name: pricing_product_pricing_product_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE pricing_product_pricing_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pricing_product_pricing_product_id_seq OWNER TO test;

--
-- Name: product; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

CREATE TABLE product (
    product_id bigint NOT NULL,
    created_on timestamp without time zone,
    image_path character varying(255) NOT NULL,
    long_desc character varying(255),
    name character varying(255) NOT NULL,
    quantity bigint NOT NULL,
    short_desc character varying(255) NOT NULL,
    sku character varying(255) NOT NULL,
    updated_on timestamp without time zone,
    category_id bigint NOT NULL,
    featured_product_id bigint,
    pricing_product_id bigint,
    shop_id bigint NOT NULL
);


ALTER TABLE product OWNER TO test;

--
-- Name: product_product_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE product_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_product_id_seq OWNER TO test;

--
-- Name: product_variation; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

CREATE TABLE product_variation (
    productvariation_id bigint NOT NULL,
    color character varying(255),
    material character varying(255),
    mics character varying(255),
    price character varying(255),
    quantity character varying(255),
    size character varying(255),
    product_id bigint NOT NULL
);


ALTER TABLE product_variation OWNER TO test;

--
-- Name: productvariation_productvariation_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE productvariation_productvariation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE productvariation_productvariation_id_seq OWNER TO test;

--
-- Name: shipping; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

CREATE TABLE shipping (
    id bigint NOT NULL,
    country_name character varying(255) NOT NULL,
    shipping_processing_time bigint,
    shipping_cost numeric(4,2),
    shippingfrom_id bigint
);


ALTER TABLE shipping OWNER TO test;

--
-- Name: shipping_shipping_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE shipping_shipping_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE shipping_shipping_id_seq OWNER TO test;

--
-- Name: shop; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

CREATE TABLE shop (
    shop_id bigint NOT NULL,
    shop_name character varying(255) NOT NULL
);


ALTER TABLE shop OWNER TO test;

--
-- Name: shop_shop_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE shop_shop_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE shop_shop_id_seq OWNER TO test;

--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: test
--

COPY category (category_id, code, name, parentcategory_category_id, created_on, updated_on) FROM stdin;
15	1000	Women	\N	\N	\N
16	1100	Totes and Bag	15	\N	\N
17	1200	Shirts and Dresses	15	\N	\N
18	1300	Handbags	15	\N	\N
19	1000	Women	\N	\N	\N
20	1100	Totes and Bag	19	\N	\N
21	1200	Shirts and Dresses	19	\N	\N
22	1300	Handbags	19	\N	\N
23	1000	Men	\N	\N	\N
24	1100	Bags	23	\N	\N
25	1200	Shirts	23	\N	\N
\.


--
-- Name: category_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('category_category_id_seq', 25, true);


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: test
--

COPY customer (customer_id, email, first_name, last_name, mobile_number, shop_id) FROM stdin;
\.


--
-- Name: customer_customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('customer_customer_id_seq', 1, false);


--
-- Data for Name: customer_shipping; Type: TABLE DATA; Schema: public; Owner: test
--

COPY customer_shipping (customer_shipping_id, address, city, country, state, zipcode) FROM stdin;
\.


--
-- Name: customershipping_customershipping_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('customershipping_customershipping_id_seq', 1, false);


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: test
--

COPY databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase) FROM stdin;
7	uc	migrations.xml	2015-09-13 23:37:37.316733	1	EXECUTED	7:1cc29ac4f52a266e81138438f1a87b60	createSequence, createTable	make product table and column names start lowercase	\N	3.3.2
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: test
--

COPY databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- Data for Name: featured_product; Type: TABLE DATA; Schema: public; Owner: test
--

COPY featured_product (featured_product_id) FROM stdin;
\.


--
-- Name: featuredproducts_featuredproducts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('featuredproducts_featuredproducts_id_seq', 1, false);


--
-- Name: order_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('order_order_id_seq', 1, false);


--
-- Data for Name: order_product; Type: TABLE DATA; Schema: public; Owner: test
--

COPY order_product (order_id, product_category_id, product_id, product_image_path, product_name, product_price, product_quantity, product_shop_id, product_sku, customer_id) FROM stdin;
\.


--
-- Data for Name: pricing_product; Type: TABLE DATA; Schema: public; Owner: test
--

COPY pricing_product (pricing_product_id, stored_value) FROM stdin;
7	89.99
8	99.99
9	89.99
10	89.99
11	89.99
12	89.99
13	89.99
14	89.99
15	89.99
16	89.99
17	89.99
18	89.99
19	89.99
20	89.99
21	89.99
22	89.99
23	89.99
24	89.99
25	89.99
26	89.99
27	89.99
35	89.99
36	89.99
\.


--
-- Name: pricing_product_pricing_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('pricing_product_pricing_product_id_seq', 36, true);


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: test
--

COPY product (product_id, created_on, image_path, long_desc, name, quantity, short_desc, sku, updated_on, category_id, featured_product_id, pricing_product_id, shop_id) FROM stdin;
37	2015-11-24 23:39:04.205	clothes/women/tops/tunic/img-11.jpg	Hand embroided tunic	tunic	50	Embroided Tunic	SKU_TUNIC_1	\N	17	\N	35	29
38	2015-11-24 23:47:58.075	clothes/women/tops/tunic/img-02.jpg	Jacket - Multi Colored Patch Work	top	50	Jacket - Multi Colored Patch Work	SKU_Tops_1	\N	17	\N	36	30
\.


--
-- Name: product_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('product_product_id_seq', 38, true);


--
-- Data for Name: product_variation; Type: TABLE DATA; Schema: public; Owner: test
--

COPY product_variation (productvariation_id, color, material, mics, price, quantity, size, product_id) FROM stdin;
1	\N	\N	\N	\N	\N	\N	37
2	\N	\N	\N	\N	\N	\N	38
\.


--
-- Name: productvariation_productvariation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('productvariation_productvariation_id_seq', 2, true);


--
-- Data for Name: shipping; Type: TABLE DATA; Schema: public; Owner: test
--

COPY shipping (id, country_name, shipping_processing_time, shipping_cost, shippingfrom_id) FROM stdin;
\.


--
-- Name: shipping_shipping_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('shipping_shipping_id_seq', 1, false);


--
-- Data for Name: shop; Type: TABLE DATA; Schema: public; Owner: test
--

COPY shop (shop_id, shop_name) FROM stdin;
1	gulgs
2	gulgs
3	gulgs
4	gulgs
5	gulgs
6	gulgs
7	gulgs
8	gulgs
9	gulgs
10	gulgs
11	gulgs
12	gulgs
13	gulgs
14	gulgs
15	gulgs
16	gulgs
17	gulgs
18	gulgs
19	gulgs
20	gulgs
21	gulgs
29	gulgs
30	gulgs
\.


--
-- Name: shop_shop_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('shop_shop_id_seq', 30, true);


--
-- Name: category_pkey; Type: CONSTRAINT; Schema: public; Owner: test; Tablespace: 
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (category_id);


--
-- Name: customer_pkey; Type: CONSTRAINT; Schema: public; Owner: test; Tablespace: 
--

ALTER TABLE ONLY customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id);


--
-- Name: customer_shipping_pkey; Type: CONSTRAINT; Schema: public; Owner: test; Tablespace: 
--

ALTER TABLE ONLY customer_shipping
    ADD CONSTRAINT customer_shipping_pkey PRIMARY KEY (customer_shipping_id);


--
-- Name: featured_product_pkey; Type: CONSTRAINT; Schema: public; Owner: test; Tablespace: 
--

ALTER TABLE ONLY featured_product
    ADD CONSTRAINT featured_product_pkey PRIMARY KEY (featured_product_id);


--
-- Name: order_product_pkey; Type: CONSTRAINT; Schema: public; Owner: test; Tablespace: 
--

ALTER TABLE ONLY order_product
    ADD CONSTRAINT order_product_pkey PRIMARY KEY (order_id);


--
-- Name: pk_databasechangeloglock; Type: CONSTRAINT; Schema: public; Owner: test; Tablespace: 
--

ALTER TABLE ONLY databasechangeloglock
    ADD CONSTRAINT pk_databasechangeloglock PRIMARY KEY (id);


--
-- Name: pricing_product_pkey; Type: CONSTRAINT; Schema: public; Owner: test; Tablespace: 
--

ALTER TABLE ONLY pricing_product
    ADD CONSTRAINT pricing_product_pkey PRIMARY KEY (pricing_product_id);


--
-- Name: product_pkey; Type: CONSTRAINT; Schema: public; Owner: test; Tablespace: 
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);


--
-- Name: product_variation_pkey; Type: CONSTRAINT; Schema: public; Owner: test; Tablespace: 
--

ALTER TABLE ONLY product_variation
    ADD CONSTRAINT product_variation_pkey PRIMARY KEY (productvariation_id);


--
-- Name: shipping_pkey; Type: CONSTRAINT; Schema: public; Owner: test; Tablespace: 
--

ALTER TABLE ONLY shipping
    ADD CONSTRAINT shipping_pkey PRIMARY KEY (id);


--
-- Name: shop_pkey; Type: CONSTRAINT; Schema: public; Owner: test; Tablespace: 
--

ALTER TABLE ONLY shop
    ADD CONSTRAINT shop_pkey PRIMARY KEY (shop_id);


--
-- Name: fk_3rqk4fspf25iyfbl04uppp4ul; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY category
    ADD CONSTRAINT fk_3rqk4fspf25iyfbl04uppp4ul FOREIGN KEY (parentcategory_category_id) REFERENCES category(category_id);


--
-- Name: fk_3ykfr3nic6i9y6q63ld1tr0m0; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_3ykfr3nic6i9y6q63ld1tr0m0 FOREIGN KEY (featured_product_id) REFERENCES featured_product(featured_product_id);


--
-- Name: fk_6oweamgjibmex1v06bgk59asd; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_6oweamgjibmex1v06bgk59asd FOREIGN KEY (category_id) REFERENCES category(category_id);


--
-- Name: fk_9q2l318eh55wqb10i6ah4s0ng; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY shipping
    ADD CONSTRAINT fk_9q2l318eh55wqb10i6ah4s0ng FOREIGN KEY (shippingfrom_id) REFERENCES shipping(id);


--
-- Name: fk_irahbcigpcey2v8ucfo9e8i4j; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY product_variation
    ADD CONSTRAINT fk_irahbcigpcey2v8ucfo9e8i4j FOREIGN KEY (product_id) REFERENCES product(product_id);


--
-- Name: fk_m7x5c3eg0yydsjf7pktglly1n; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY customer
    ADD CONSTRAINT fk_m7x5c3eg0yydsjf7pktglly1n FOREIGN KEY (shop_id) REFERENCES shop(shop_id);


--
-- Name: fk_nodxvyr0xxxij5c427ulsl5ag; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY order_product
    ADD CONSTRAINT fk_nodxvyr0xxxij5c427ulsl5ag FOREIGN KEY (customer_id) REFERENCES customer(customer_id);


--
-- Name: fk_oyjqqep8hbmk4adib1t3g2om2; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_oyjqqep8hbmk4adib1t3g2om2 FOREIGN KEY (pricing_product_id) REFERENCES pricing_product(pricing_product_id);


--
-- Name: fk_r0v5c2ajsmfyqwo8il06y4lqu; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_r0v5c2ajsmfyqwo8il06y4lqu FOREIGN KEY (shop_id) REFERENCES shop(shop_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: test
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM test;
GRANT ALL ON SCHEMA public TO test;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

