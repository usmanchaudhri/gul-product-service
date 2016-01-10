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
    image_path character varying(255),
    created_on timestamp without time zone,
    updated_on timestamp without time zone
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
    shop_id bigint,
    created_on timestamp without time zone,
    updated_on timestamp without time zone
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
    zipcode character varying(255) NOT NULL,
    created_on timestamp without time zone,
    updated_on timestamp without time zone
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
    shop_id bigint NOT NULL,
    created_on timestamp without time zone,
    updated_on timestamp without time zone
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
    featured_product_id bigint NOT NULL,
    created_on timestamp without time zone,
    updated_on timestamp without time zone
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
    image_path character varying(255),
    created_on timestamp without time zone,
    updated_on timestamp without time zone
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
    customer_id bigint NOT NULL,
    created_on timestamp without time zone,
    updated_on timestamp without time zone
);


ALTER TABLE order_product OWNER TO njcyxzlnmtiyvb;

--
-- Name: pricing_product; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

CREATE TABLE pricing_product (
    pricing_product_id bigint NOT NULL,
    stored_value numeric(4,2) NOT NULL,
    created_on timestamp without time zone,
    updated_on timestamp without time zone
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
    long_desc character varying(255),
    name character varying(255) NOT NULL,
    quantity bigint NOT NULL,
    short_desc character varying(255) NOT NULL,
    sku character varying(255) NOT NULL,
    category_id bigint NOT NULL,
    featured_product_id bigint,
    pricing_product_id bigint,
    shop_id bigint NOT NULL,
    image_info_id bigint,
    created_on timestamp without time zone,
    updated_on timestamp without time zone
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
    product_id bigint NOT NULL,
    created_on timestamp without time zone,
    updated_on timestamp without time zone
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
    shippingfrom_id bigint,
    created_on timestamp without time zone,
    updated_on timestamp without time zone
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
    shop_name character varying(255) NOT NULL,
    created_on timestamp without time zone,
    updated_on timestamp without time zone
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

COPY category (category_id, code, name, parentcategory_category_id, image_path, created_on, updated_on) FROM stdin;
35	1001	Bags	34	\N	2016-01-07 11:38:46.215	\N
36	1002	Tunic Top	34	\N	2016-01-07 11:38:46.215	\N
37	1003	Cashmere Scarves	34	\N	2016-01-07 11:38:46.215	\N
39	2001	Wallets	38	\N	2016-01-07 12:33:22.171	\N
40	2002	T-Shirts	38	\N	2016-01-07 12:33:22.171	\N
41	2003	Cashmere Scarves	38	\N	2016-01-07 12:33:22.171	\N
42	2004	Wallets	\N	\N	2016-01-08 15:50:28.122	\N
43	2005	Hats	38	\N	\N	\N
38	2000	Men	\N	\N	\N	2016-01-08 15:56:14.303
46	1004	Sweaters	34	\N	\N	\N
47	1005	Dresses	34	\N	\N	\N
34	1000	Women	\N	\N	\N	2016-01-08 17:38:29.004
\.


--
-- Name: category_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('category_category_id_seq', 47, true);


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY customer (customer_id, email, first_name, last_name, mobile_number, shop_id, created_on, updated_on) FROM stdin;
\.


--
-- Name: customer_customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('customer_customer_id_seq', 1, false);


--
-- Data for Name: customer_shipping; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY customer_shipping (customer_shipping_id, address, city, country, state, zipcode, created_on, updated_on) FROM stdin;
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

COPY designer (designer_id, image_path, designer_name, shop_id, created_on, updated_on) FROM stdin;
10006	/shop/Nayyar	Nayyar Chaudhri	38	\N	\N
10007	/shop/Gulgs	Usman Chaudhri	39	\N	\N
10008	/shop/LeatherHeaven	Jawaria	40	\N	\N
10009	/shop/ScarfPalace	Aliya	41	\N	\N
10010	/shop/TunicPlace	Zartosh	42	\N	\N
10011	/shop/FivePillars	Farrukh	43	\N	\N
10012	/shop/KingNoorLA	Afgha	44	\N	\N
10013	/shop/knitfashionable	Mehmet	45	\N	\N
\.


--
-- Name: designer_designer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('designer_designer_id_seq', 10013, true);


--
-- Data for Name: featured_product; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY featured_product (featured_product_id, created_on, updated_on) FROM stdin;
\.


--
-- Name: featuredproducts_featuredproducts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('featuredproducts_featuredproducts_id_seq', 1, false);


--
-- Data for Name: image_info; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY image_info (image_info_id, image_path, created_on, updated_on) FROM stdin;
26	/listing	\N	\N
27	/listing	\N	\N
28	/listing	\N	\N
29	/listing	\N	\N
30	/listing	\N	\N
31	/listing	\N	\N
32	/listing	\N	\N
33	/listing	\N	\N
34	/listing	\N	\N
35	/listing	\N	\N
36	/listing	\N	\N
37	/listing	\N	\N
38	/listing	\N	\N
39	/listing	\N	\N
40	/listing	\N	\N
\.


--
-- Name: imageinfo_imageinfo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('imageinfo_imageinfo_id_seq', 40, true);


--
-- Name: order_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('order_order_id_seq', 1, false);


--
-- Data for Name: order_product; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY order_product (order_id, product_category_id, product_id, product_image_path, product_name, product_price, product_quantity, product_shop_id, product_sku, customer_id, created_on, updated_on) FROM stdin;
\.


--
-- Data for Name: pricing_product; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY pricing_product (pricing_product_id, stored_value, created_on, updated_on) FROM stdin;
62	29.95	\N	\N
63	29.95	\N	\N
64	29.95	\N	\N
65	54.95	\N	\N
66	54.95	\N	\N
67	44.95	\N	\N
68	44.95	\N	\N
69	44.95	\N	\N
70	44.95	\N	\N
71	39.95	\N	\N
72	27.95	\N	\N
73	27.95	\N	\N
74	39.61	\N	\N
75	39.61	\N	\N
76	37.73	\N	\N
\.


--
-- Name: pricing_product_pricing_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('pricing_product_pricing_product_id_seq', 76, true);


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY product (product_id, long_desc, name, quantity, short_desc, sku, category_id, featured_product_id, pricing_product_id, shop_id, image_info_id, created_on, updated_on) FROM stdin;
64	Birds hands tee	Birds Hands Tee	5	Birds hands tee	SKU_T_100	40	\N	62	43	26	2016-01-07 12:59:29.525	\N
65	Sisterly Love	Sisterly Love	5	Sisterly Love	SKU_T_200	40	\N	63	43	27	2016-01-07 13:02:10.761	\N
66	Long tee collaboration with author Alex Elle	Be you long Tee	5	Be you, love you. All ways, always	SKU_T_300	40	\N	64	43	28	2016-01-08 14:49:09.802	\N
67	Love Hoodie by 5ive Pillars	Love Hoodie	10	Love Hoodie by 5ive Pillars	SKU_T_400	40	\N	65	43	29	2016-01-08 15:30:51.461	\N
68	God is Greater Hoodie by 5ive Pillars	God is Greater Hoodie	5	God is Greater Hoodie by 5ive Pillars	SKU_T_500	40	\N	66	43	30	2016-01-08 15:32:19.852	\N
69	Sweater collaboration with artist Amira Rahim by 5ive Pillars	Bedouin Sweater	5	Sweater collaboration with artist Amira Rahim	SKU_T_600	40	\N	67	43	31	2016-01-08 15:34:11.94	\N
70	God is Greater Sweater artist Amira Rahim by 5ive Pillars	God is Greater Sweater	5	God is Greater Sweater artist Amira Rahim	SKU_T_700	40	\N	68	43	32	2016-01-08 15:35:30.626	\N
71	Bismillah Sweater artist Amira Rahim by 5ive Pillars	Bismillah Sweater	5	Bismillah Sweater artist Amira Rahim	SKU_T_800	40	\N	69	43	33	2016-01-08 15:36:34.689	\N
72	Love Sweats artist Amira Rahim by 5ive Pillars	Love Sweats	5	Love Sweats artist Amira Rahim	SKU_T_900	40	\N	70	43	34	2016-01-08 15:37:26.467	\N
73	5ive Pillars Sweats Amira Rahim by 5ive Pillars	5ive Pillars Sweats	5	5ive Pillars Sweats artist Amira Rahim	SKU_T_1000	40	\N	71	43	35	2016-01-08 15:38:26.483	\N
74	Be you, Love you Hat	Be you, Love you Hat	5	Be you, Love you Hat	SKU_T_110	43	\N	72	43	36	2016-01-08 15:59:51.433	\N
75	Crescent and Heart Cap	Crescent and Heart Cap	5	Crescent and Heart Cap	SKU_T_1200	43	\N	73	43	37	2016-01-08 16:00:48.554	\N
76	Looking for oversized sweater dress gift ideas for women Casual dresses casual womens dresses tan dress plus size sweater handmade 100% acrylic	Crescent and Heart Cap	5	Wife gift girlfriend gift oversized sweater dress gift ideas for women Casual dresses casual womens dresses navy dress plus size sweater	SKU_T_1300	46	\N	74	45	38	2016-01-08 17:25:27.735	\N
77	Looking for oversized sweater dress gift ideas for women Casual dresses casual womens dresses tan dress plus size sweater handmade 100% acrylic	Sweater	5	Sweater dress summer dress gift ideas for women womens sweater casual dresses casual womens dresses mothers day gift womens clothing 1	SKU_T_1400	46	\N	75	45	39	2016-01-08 17:31:58.953	\N
78	red dress mothers day gifts for mom women's fashion women sweater dress dress women's knitwear 70% acrylic, 30% polyester	Dress	5	red dress mothers day gifts for mom women's fashion women sweater dress dress women's knitwear	SKU_T_1500	47	\N	76	45	40	2016-01-08 17:40:55.089	\N
\.


--
-- Name: product_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('product_product_id_seq', 78, true);


--
-- Data for Name: product_variation; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY product_variation (productvariation_id, color, material, mics, price, quantity, size, product_id, created_on, updated_on) FROM stdin;
28	\N	\N	\N	\N	\N	\N	64	\N	\N
29	\N	\N	\N	\N	\N	\N	65	\N	\N
30	\N	\N	\N	\N	\N	\N	66	\N	\N
31	\N	\N	\N	\N	\N	\N	67	\N	\N
32	\N	\N	\N	\N	\N	\N	68	\N	\N
33	\N	\N	\N	\N	\N	\N	69	\N	\N
34	\N	\N	\N	\N	\N	\N	70	\N	\N
35	\N	\N	\N	\N	\N	\N	71	\N	\N
36	\N	\N	\N	\N	\N	\N	72	\N	\N
37	\N	\N	\N	\N	\N	\N	73	\N	\N
38	\N	\N	\N	\N	\N	\N	74	\N	\N
39	\N	\N	\N	\N	\N	\N	75	\N	\N
40	\N	\N	\N	\N	\N	\N	76	\N	\N
41	\N	\N	\N	\N	\N	\N	77	\N	\N
42	\N	\N	\N	\N	\N	\N	78	\N	\N
\.


--
-- Name: productvariation_productvariation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('productvariation_productvariation_id_seq', 42, true);


--
-- Data for Name: shipping; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY shipping (id, country_name, shipping_processing_time, shipping_cost, shippingfrom_id, created_on, updated_on) FROM stdin;
\.


--
-- Name: shipping_shipping_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('shipping_shipping_id_seq', 1, false);


--
-- Data for Name: shop; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY shop (shop_id, shop_name, created_on, updated_on) FROM stdin;
38	Nayyar	2016-01-07 12:39:11.703	\N
39	Gulgs	2016-01-07 12:42:01.598	\N
40	Leather Heaven	2016-01-07 12:42:10.148	\N
41	Scarf Palace	2016-01-07 12:42:20.769	\N
42	Tunic place	2016-01-07 12:42:28.791	\N
43	5ive Pillars	2016-01-07 12:49:16.23	\N
44	King Noor LA	2016-01-07 12:52:29.06	\N
45	knitfashionable	2016-01-08 17:12:05.616	\N
\.


--
-- Name: shop_shop_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('shop_shop_id_seq', 45, true);


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
-- Name: public; Type: ACL; Schema: -; Owner: usman
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM usman;
GRANT ALL ON SCHEMA public TO usman;
GRANT ALL ON SCHEMA public TO njcyxzlnmtiyvb;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

