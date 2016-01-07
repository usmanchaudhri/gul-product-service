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
-- Name: category; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

CREATE TABLE category (
    category_id bigint NOT NULL,
    code character varying(255),
    name character varying(255) NOT NULL,
    parentcategory_category_id bigint,
    created_on date,
    updated_on date,
    image_path character varying(255)
);


ALTER TABLE category OWNER TO njcyxzlnmtiyvb;

--
-- Name: category_category_id_seq; Type: SEQUENCE; Schema: public; Owner: njcyxzlnmtiyvb
--

CREATE SEQUENCE category_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE category_category_id_seq OWNER TO njcyxzlnmtiyvb;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

CREATE TABLE customer (
    customer_id bigint NOT NULL,
    email character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    mobile_number character varying(12) NOT NULL,
    shop_id bigint
);


ALTER TABLE customer OWNER TO njcyxzlnmtiyvb;

--
-- Name: customer_customer_id_seq; Type: SEQUENCE; Schema: public; Owner: njcyxzlnmtiyvb
--

CREATE SEQUENCE customer_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE customer_customer_id_seq OWNER TO njcyxzlnmtiyvb;

--
-- Name: customer_shipping; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

CREATE TABLE customer_shipping (
    customer_shipping_id bigint NOT NULL,
    address character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    country character varying(255) NOT NULL,
    state character varying(255) NOT NULL,
    zipcode character varying(255) NOT NULL
);


ALTER TABLE customer_shipping OWNER TO njcyxzlnmtiyvb;

--
-- Name: customershipping_customershipping_id_seq; Type: SEQUENCE; Schema: public; Owner: njcyxzlnmtiyvb
--

CREATE SEQUENCE customershipping_customershipping_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE customershipping_customershipping_id_seq OWNER TO njcyxzlnmtiyvb;

--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
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


ALTER TABLE databasechangelog OWNER TO njcyxzlnmtiyvb;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

CREATE TABLE databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE databasechangeloglock OWNER TO njcyxzlnmtiyvb;

--
-- Name: designer; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

CREATE TABLE designer (
    designer_id bigint NOT NULL,
    image_path character varying(255),
    designer_name character varying(255) NOT NULL,
    shop_id bigint NOT NULL
);


ALTER TABLE designer OWNER TO njcyxzlnmtiyvb;

--
-- Name: designer_designer_id_seq; Type: SEQUENCE; Schema: public; Owner: njcyxzlnmtiyvb
--

CREATE SEQUENCE designer_designer_id_seq
    START WITH 9999
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE designer_designer_id_seq OWNER TO njcyxzlnmtiyvb;

--
-- Name: featured_product; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

CREATE TABLE featured_product (
    featured_product_id bigint NOT NULL
);


ALTER TABLE featured_product OWNER TO njcyxzlnmtiyvb;

--
-- Name: featuredproducts_featuredproducts_id_seq; Type: SEQUENCE; Schema: public; Owner: njcyxzlnmtiyvb
--

CREATE SEQUENCE featuredproducts_featuredproducts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE featuredproducts_featuredproducts_id_seq OWNER TO njcyxzlnmtiyvb;

--
-- Name: image_info; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

CREATE TABLE image_info (
    image_info_id bigint NOT NULL,
    image_path character varying(255)
);


ALTER TABLE image_info OWNER TO njcyxzlnmtiyvb;

--
-- Name: imageinfo_imageinfo_id_seq; Type: SEQUENCE; Schema: public; Owner: njcyxzlnmtiyvb
--

CREATE SEQUENCE imageinfo_imageinfo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE imageinfo_imageinfo_id_seq OWNER TO njcyxzlnmtiyvb;

--
-- Name: order_order_id_seq; Type: SEQUENCE; Schema: public; Owner: njcyxzlnmtiyvb
--

CREATE SEQUENCE order_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE order_order_id_seq OWNER TO njcyxzlnmtiyvb;

--
-- Name: order_product; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
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


ALTER TABLE order_product OWNER TO njcyxzlnmtiyvb;

--
-- Name: pricing_product; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

CREATE TABLE pricing_product (
    pricing_product_id bigint NOT NULL,
    stored_value numeric(4,2) NOT NULL
);


ALTER TABLE pricing_product OWNER TO njcyxzlnmtiyvb;

--
-- Name: pricing_product_pricing_product_id_seq; Type: SEQUENCE; Schema: public; Owner: njcyxzlnmtiyvb
--

CREATE SEQUENCE pricing_product_pricing_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pricing_product_pricing_product_id_seq OWNER TO njcyxzlnmtiyvb;

--
-- Name: product; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

CREATE TABLE product (
    product_id bigint NOT NULL,
    created_on timestamp without time zone,
    long_desc character varying(255),
    name character varying(255) NOT NULL,
    quantity bigint NOT NULL,
    short_desc character varying(255) NOT NULL,
    sku character varying(255) NOT NULL,
    updated_on timestamp without time zone,
    category_id bigint NOT NULL,
    featured_product_id bigint,
    pricing_product_id bigint,
    shop_id bigint NOT NULL,
    image_info_id bigint
);


ALTER TABLE product OWNER TO njcyxzlnmtiyvb;

--
-- Name: product_product_id_seq; Type: SEQUENCE; Schema: public; Owner: njcyxzlnmtiyvb
--

CREATE SEQUENCE product_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_product_id_seq OWNER TO njcyxzlnmtiyvb;

--
-- Name: product_variation; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
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


ALTER TABLE product_variation OWNER TO njcyxzlnmtiyvb;

--
-- Name: productvariation_productvariation_id_seq; Type: SEQUENCE; Schema: public; Owner: njcyxzlnmtiyvb
--

CREATE SEQUENCE productvariation_productvariation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE productvariation_productvariation_id_seq OWNER TO njcyxzlnmtiyvb;

--
-- Name: shipping; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

CREATE TABLE shipping (
    id bigint NOT NULL,
    country_name character varying(255) NOT NULL,
    shipping_processing_time bigint,
    shipping_cost numeric(4,2),
    shippingfrom_id bigint
);


ALTER TABLE shipping OWNER TO njcyxzlnmtiyvb;

--
-- Name: shipping_shipping_id_seq; Type: SEQUENCE; Schema: public; Owner: njcyxzlnmtiyvb
--

CREATE SEQUENCE shipping_shipping_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE shipping_shipping_id_seq OWNER TO njcyxzlnmtiyvb;

--
-- Name: shop; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

CREATE TABLE shop (
    shop_id bigint NOT NULL,
    shop_name character varying(255) NOT NULL
);


ALTER TABLE shop OWNER TO njcyxzlnmtiyvb;

--
-- Name: shop_shop_id_seq; Type: SEQUENCE; Schema: public; Owner: njcyxzlnmtiyvb
--

CREATE SEQUENCE shop_shop_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE shop_shop_id_seq OWNER TO njcyxzlnmtiyvb;

--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY category (category_id, code, name, parentcategory_category_id, created_on, updated_on, image_path) FROM stdin;
15	1000	Women	\N	\N	\N	\N
16	1100	Totes and Bag	15	\N	\N	\N
17	1200	Shirts and Dresses	15	\N	\N	\N
18	1300	Handbags	15	\N	\N	\N
19	1000	Women	\N	\N	\N	\N
20	1100	Totes and Bag	19	\N	\N	\N
21	1200	Shirts and Dresses	19	\N	\N	\N
22	1300	Handbags	19	\N	\N	\N
23	1000	Men	\N	\N	\N	\N
24	1100	Bags	23	\N	\N	\N
25	1200	Shirts	23	\N	\N	\N
\.


--
-- Name: category_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('category_category_id_seq', 25, true);


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY customer (customer_id, email, first_name, last_name, mobile_number, shop_id) FROM stdin;
\.


--
-- Name: customer_customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('customer_customer_id_seq', 1, false);


--
-- Data for Name: customer_shipping; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY customer_shipping (customer_shipping_id, address, city, country, state, zipcode) FROM stdin;
\.


--
-- Name: customershipping_customershipping_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('customershipping_customershipping_id_seq', 1, false);


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase) FROM stdin;
7	uc	migrations.xml	2015-09-13 23:37:37.316733	1	EXECUTED	7:1cc29ac4f52a266e81138438f1a87b60	createSequence, createTable	make product table and column names start lowercase	\N	3.3.2
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- Data for Name: designer; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY designer (designer_id, image_path, designer_name, shop_id) FROM stdin;
10005	/2015/winter	Nayyar Chaudhri	37
\.


--
-- Name: designer_designer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('designer_designer_id_seq', 10005, true);


--
-- Data for Name: featured_product; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY featured_product (featured_product_id) FROM stdin;
\.


--
-- Name: featuredproducts_featuredproducts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('featuredproducts_featuredproducts_id_seq', 1, false);


--
-- Data for Name: image_info; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY image_info (image_info_id, image_path) FROM stdin;
12	clothes/women/tops/tunic/img-11.jpg
13	/listing/wallets
14	/listing/wallets
15	/listing/wallets
16	/listing/wallets
17	/listing/wallets
18	/listing/wallets
\.


--
-- Name: imageinfo_imageinfo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('imageinfo_imageinfo_id_seq', 18, true);


--
-- Name: order_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('order_order_id_seq', 1, false);


--
-- Data for Name: order_product; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY order_product (order_id, product_category_id, product_id, product_image_path, product_name, product_price, product_quantity, product_shop_id, product_sku, customer_id) FROM stdin;
\.


--
-- Data for Name: pricing_product; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
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
48	89.99
49	89.99
50	89.99
51	89.99
52	89.99
53	89.99
54	89.99
\.


--
-- Name: pricing_product_pricing_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('pricing_product_pricing_product_id_seq', 54, true);


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY product (product_id, created_on, long_desc, name, quantity, short_desc, sku, updated_on, category_id, featured_product_id, pricing_product_id, shop_id, image_info_id) FROM stdin;
37	2015-11-24 23:39:04.205	Hand embroided tunic	tunic	50	Embroided Tunic	SKU_TUNIC_1	\N	17	\N	35	29	\N
38	2015-11-24 23:47:58.075	Jacket - Multi Colored Patch Work	top	50	Jacket - Multi Colored Patch Work	SKU_Tops_1	\N	17	\N	36	30	\N
50	2016-01-03 03:52:49.727	Handmade women leather tote	Women tote	50	Women leather tote 	SKU_BAG_10	\N	16	\N	48	10	12
51	2016-01-03 06:10:59.564	handmde leather wallet	leather wallet	50	handmade leather wallet	SKU_BAG_100	\N	24	\N	49	37	13
52	2016-01-03 08:40:55.884	handmde leather wallet	leather wallet	50	handmade leather wallet	SKU_BAG_101	\N	24	\N	50	37	14
53	2016-01-03 08:50:45.082	handmde leather wallet	leather wallet	50	handmade leather wallet	SKU_BAG_201	\N	24	\N	51	37	15
54	2016-01-03 08:53:43.612	handmde leather wallet	leather wallet	50	handmade leather wallet	SKU_BAG_301	\N	24	\N	52	37	16
55	2016-01-03 08:57:03.05	handmde leather wallet	leather wallet	50	handmade leather wallet	SKU_BAG_401	\N	24	\N	53	37	17
56	2016-01-03 08:58:27.716	handmde leather wallet	leather wallet	50	handmade leather wallet	SKU_BAG_501	\N	24	\N	54	37	18
\.


--
-- Name: product_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('product_product_id_seq', 56, true);


--
-- Data for Name: product_variation; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY product_variation (productvariation_id, color, material, mics, price, quantity, size, product_id) FROM stdin;
1	\N	\N	\N	\N	\N	\N	37
2	\N	\N	\N	\N	\N	\N	38
14	\N	\N	\N	\N	\N	\N	50
15	\N	\N	\N	\N	\N	\N	51
16	\N	\N	\N	\N	\N	\N	52
17	\N	\N	\N	\N	\N	\N	53
18	\N	\N	\N	\N	\N	\N	54
19	\N	\N	\N	\N	\N	\N	55
20	\N	\N	\N	\N	\N	\N	56
\.


--
-- Name: productvariation_productvariation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('productvariation_productvariation_id_seq', 20, true);


--
-- Data for Name: shipping; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY shipping (id, country_name, shipping_processing_time, shipping_cost, shippingfrom_id) FROM stdin;
\.


--
-- Name: shipping_shipping_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('shipping_shipping_id_seq', 1, false);


--
-- Data for Name: shop; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
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
37	Leather Heaven
\.


--
-- Name: shop_shop_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('shop_shop_id_seq', 37, true);


--
-- Name: category_pkey; Type: CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (category_id);


--
-- Name: customer_pkey; Type: CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

ALTER TABLE ONLY customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id);


--
-- Name: customer_shipping_pkey; Type: CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

ALTER TABLE ONLY customer_shipping
    ADD CONSTRAINT customer_shipping_pkey PRIMARY KEY (customer_shipping_id);


--
-- Name: designer_pkey; Type: CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

ALTER TABLE ONLY designer
    ADD CONSTRAINT designer_pkey PRIMARY KEY (designer_id);


--
-- Name: featured_product_pkey; Type: CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

ALTER TABLE ONLY featured_product
    ADD CONSTRAINT featured_product_pkey PRIMARY KEY (featured_product_id);


--
-- Name: image_info_pkey; Type: CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

ALTER TABLE ONLY image_info
    ADD CONSTRAINT image_info_pkey PRIMARY KEY (image_info_id);


--
-- Name: order_product_pkey; Type: CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

ALTER TABLE ONLY order_product
    ADD CONSTRAINT order_product_pkey PRIMARY KEY (order_id);


--
-- Name: pk_databasechangeloglock; Type: CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

ALTER TABLE ONLY databasechangeloglock
    ADD CONSTRAINT pk_databasechangeloglock PRIMARY KEY (id);


--
-- Name: pricing_product_pkey; Type: CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

ALTER TABLE ONLY pricing_product
    ADD CONSTRAINT pricing_product_pkey PRIMARY KEY (pricing_product_id);


--
-- Name: product_pkey; Type: CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);


--
-- Name: product_variation_pkey; Type: CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

ALTER TABLE ONLY product_variation
    ADD CONSTRAINT product_variation_pkey PRIMARY KEY (productvariation_id);


--
-- Name: shipping_pkey; Type: CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

ALTER TABLE ONLY shipping
    ADD CONSTRAINT shipping_pkey PRIMARY KEY (id);


--
-- Name: shop_pkey; Type: CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

ALTER TABLE ONLY shop
    ADD CONSTRAINT shop_pkey PRIMARY KEY (shop_id);


--
-- Name: fk_3rqk4fspf25iyfbl04uppp4ul; Type: FK CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb
--

ALTER TABLE ONLY category
    ADD CONSTRAINT fk_3rqk4fspf25iyfbl04uppp4ul FOREIGN KEY (parentcategory_category_id) REFERENCES category(category_id);


--
-- Name: fk_3ykfr3nic6i9y6q63ld1tr0m0; Type: FK CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb
--

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_3ykfr3nic6i9y6q63ld1tr0m0 FOREIGN KEY (featured_product_id) REFERENCES featured_product(featured_product_id);


--
-- Name: fk_6oweamgjibmex1v06bgk59asd; Type: FK CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb
--

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_6oweamgjibmex1v06bgk59asd FOREIGN KEY (category_id) REFERENCES category(category_id);


--
-- Name: fk_9q2l318eh55wqb10i6ah4s0ng; Type: FK CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb
--

ALTER TABLE ONLY shipping
    ADD CONSTRAINT fk_9q2l318eh55wqb10i6ah4s0ng FOREIGN KEY (shippingfrom_id) REFERENCES shipping(id);


--
-- Name: fk_io1ytbi6v66ayv6ksymadh6fn; Type: FK CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb
--

ALTER TABLE ONLY designer
    ADD CONSTRAINT fk_io1ytbi6v66ayv6ksymadh6fn FOREIGN KEY (shop_id) REFERENCES shop(shop_id);


--
-- Name: fk_irahbcigpcey2v8ucfo9e8i4j; Type: FK CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb
--

ALTER TABLE ONLY product_variation
    ADD CONSTRAINT fk_irahbcigpcey2v8ucfo9e8i4j FOREIGN KEY (product_id) REFERENCES product(product_id);


--
-- Name: fk_m7x5c3eg0yydsjf7pktglly1n; Type: FK CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb
--

ALTER TABLE ONLY customer
    ADD CONSTRAINT fk_m7x5c3eg0yydsjf7pktglly1n FOREIGN KEY (shop_id) REFERENCES shop(shop_id);


--
-- Name: fk_nodxvyr0xxxij5c427ulsl5ag; Type: FK CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb
--

ALTER TABLE ONLY order_product
    ADD CONSTRAINT fk_nodxvyr0xxxij5c427ulsl5ag FOREIGN KEY (customer_id) REFERENCES customer(customer_id);


--
-- Name: fk_oyjqqep8hbmk4adib1t3g2om2; Type: FK CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb
--

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_oyjqqep8hbmk4adib1t3g2om2 FOREIGN KEY (pricing_product_id) REFERENCES pricing_product(pricing_product_id);


--
-- Name: fk_r0v5c2ajsmfyqwo8il06y4lqu; Type: FK CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb
--

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_r0v5c2ajsmfyqwo8il06y4lqu FOREIGN KEY (shop_id) REFERENCES shop(shop_id);


--
-- Name: fk_sql6plkrp72t9j6c65hf78g54; Type: FK CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb
--

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_sql6plkrp72t9j6c65hf78g54 FOREIGN KEY (image_info_id) REFERENCES image_info(image_info_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: njcyxzlnmtiyvb
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM njcyxzlnmtiyvb;
GRANT ALL ON SCHEMA public TO njcyxzlnmtiyvb;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

