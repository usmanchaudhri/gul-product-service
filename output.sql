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
-- Name: cchat; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

CREATE TABLE cchat (
    cchat_id bigint NOT NULL,
    unique_name character varying(255) NOT NULL,
    customer_id bigint,
    customer_username character varying(255),
    shopowner_username character varying(255)
);


ALTER TABLE cchat OWNER TO njcyxzlnmtiyvb;

--
-- Name: cchat_cchat_id_seq; Type: SEQUENCE; Schema: public; Owner: njcyxzlnmtiyvb
--

CREATE SEQUENCE cchat_cchat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cchat_cchat_id_seq OWNER TO njcyxzlnmtiyvb;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

CREATE TABLE customer (
    customer_id bigint NOT NULL,
    shop_id bigint,
    created_on timestamp without time zone,
    updated_on timestamp without time zone,
    username character varying(255),
    password character varying(255)
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
    address character varying(255),
    city character varying(255),
    country character varying(255),
    state character varying(255),
    zipcode character varying(255),
    created_on timestamp without time zone,
    updated_on timestamp without time zone,
    customer_id bigint,
    first_name character varying(255),
    last_name character varying(255),
    is_active character varying(2)
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
    updated_on timestamp without time zone,
    details text
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
    updated_on timestamp without time zone,
    image_count character varying(255)
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
    updated_on timestamp without time zone,
    status character varying(255)
);


ALTER TABLE order_product OWNER TO njcyxzlnmtiyvb;

--
-- Name: pricing_product; Type: TABLE; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

CREATE TABLE pricing_product (
    pricing_product_id bigint NOT NULL,
    stored_value numeric(6,2) NOT NULL,
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
    long_desc character varying(400),
    name character varying(255) NOT NULL,
    quantity bigint NOT NULL,
    short_desc character varying(400) NOT NULL,
    category_id bigint NOT NULL,
    featured_product_id bigint,
    pricing_product_id bigint,
    shop_id bigint NOT NULL,
    image_info_id bigint,
    created_on timestamp without time zone,
    updated_on timestamp without time zone,
    sku character varying(255)
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
    updated_on timestamp without time zone,
    details text,
    image_path character varying(255)
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
3	1002	Tunic Top	1	\N	2016-01-11 04:48:51.643	\N
4	1003	Cashmere Scarves	1	\N	2016-01-11 04:48:51.643	\N
5	1004	Dresses	1	\N	2016-01-11 04:48:51.643	\N
14	1006	Bridal Dresses	1	\N	\N	\N
1	1000	Women	\N	\N	\N	2016-04-11 13:39:43.408
\.


--
-- Name: category_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('category_category_id_seq', 14, true);


--
-- Data for Name: cchat; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY cchat (cchat_id, unique_name, customer_id, customer_username, shopowner_username) FROM stdin;
91	CH666ede0042eb40ad8f76b4769ae959bc	90	Gold blue Top	embroidered@gmail.com
92	CH666ede0042eb40ad8f76b4769ae959bc	90	Gold blue Top	embroidered@gmail.com
93	CH3c34af35b00348288eda09d540825696	61	Gold blue Top	embroidered@gmail.com
94	CH3c34af35b00348288eda09d540825696	90	Gold blue Top	embroidered@gmail.com
96	CHbc889da461cd42af958abe11834604f8	61	Pretty Blue top	gulgs@gmail.com
95	CHbc889da461cd42af958abe11834604f8	63	Pretty Blue top	gulgs@gmail.com
\.


--
-- Name: cchat_cchat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('cchat_cchat_id_seq', 96, true);


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY customer (customer_id, shop_id, created_on, updated_on, username, password) FROM stdin;
91	26	2016-04-11 13:05:55.183	\N	maria@gmail.com	�.�z|���皥G��͛��^��uT���
62	1	2016-04-05 10:43:06.963	2016-04-06 06:13:41.978	nayyar@gmail.com	�"�v��J$z�>���|��p���Ά_�`�Q
63	2	2016-04-05 10:44:44.088	2016-04-05 14:32:01.089	gulgs@gmail.com	l��Ǘ����m��3(5Pq�/r�^T��&����
92	\N	2016-04-12 08:39:28.358	\N	amjad@gmail.com	Y�G�*���Y��t�����Y�����s���
93	\N	2016-04-14 12:35:46.433	\N	majid@gmail.com	����n��:b����\f?]Z����\f�:�l�
94	\N	2016-04-19 12:29:07.718	\N	hussnain@gmail.com	���\\詰$Ax�(�l,�8W#�jk��X���\n
95	\N	2016-04-20 10:56:19.772	\N	ali@gmail.com	���\\詰$Ax�(�l,�8W#�jk��X���\n
96	\N	2016-04-20 11:07:48.535	\N	abc@gmail.com	���\\詰$Ax�(�l,�8W#�jk��X���\n
97	\N	2016-04-20 11:11:39.407	\N	xyz@g.co	���\\詰$Ax�(�l,�8W#�jk��X���\n
64	3	2016-04-05 10:46:31.387	2016-04-08 13:34:32.569	leather@gmail.com	A�R\b�x�\b��D��{\nA8#�E(-ݿ�
98	\N	2016-04-20 11:20:51.45	\N	a@gmail.com	���\\詰$Ax�(�l,�8W#�jk��X���\n
68	\N	2016-04-05 12:25:12.441	\N	azhar.rao@gmail.com	^�H��(qQ��o��)'s`=\rj���*�rB�
99	\N	2016-04-20 11:22:49.764	\N	husnain@yahoo.com	���\\詰$Ax�(�l,�8W#�jk��X���\n
100	\N	2016-04-20 11:24:47.171	\N	ali@gmail.com.pk	���\\詰$Ax�(�l,�8W#�jk��X���\n
101	\N	2016-04-20 11:52:20.055	\N	hus@gmail.com	���\\詰$Ax�(�l,�8W#�jk��X���\n
102	\N	2016-04-20 11:55:12.469	\N	kk@gmail.com	���\\詰$Ax�(�l,�8W#�jk��X���\n
103	\N	2016-04-21 14:02:13.198	\N	test@gmail.com	���\\詰$Ax�(�l,�8W#�jk��X���\n
104	\N	2016-04-21 14:02:47.196	\N	test@gmail.com	���\\詰$Ax�(�l,�8W#�jk��X���\n
105	\N	2016-04-28 09:32:23.323	\N	usman@gmail.com	�e�Y B/�A~Hg��O��J?��~������z�
106	\N	2016-04-29 14:14:02.02	\N	ali@gmail.com	���\\詰$Ax�(�l,�8W#�jk��X���\n
107	\N	2016-04-29 14:32:04.242	\N	test@gmail.com	���\\詰$Ax�(�l,�8W#�jk��X���\n
108	\N	2016-04-29 14:34:16.938	\N	guest@gmail.com	'@�/$e)�Pa1*û���z2_[q>�}F��
109	\N	2016-04-29 14:48:05.228	\N	jj@gmail.com	���\\詰$Ax�(�l,�8W#�jk��X���\n
110	\N	2016-04-29 15:40:44.787	\N	kamran@gmail.com	���\\詰$Ax�(�l,�8W#�jk��X���\n
111	\N	2016-04-29 15:42:05.13	\N	bot1@gmail.com	���\\詰$Ax�(�l,�8W#�jk��X���\n
70	\N	2016-04-06 08:50:45.335	\N	amjad123	O���qtޟF^S/�*��o�"^�lc�u��-
71	\N	2016-04-06 10:12:43.056	\N	uzair	���b�6nHϭ�}:N�u��[G��2���$T�;
72	\N	2016-04-06 10:14:41.475	\N	majid	e��u\nL��;Й�&�'J�K[LDӖ↙�K
73	\N	2016-04-06 10:16:02.411	\N	jawad	���w d��@?#���mI�)9�c�<Ncr1�M�
74	\N	2016-04-06 10:22:19.212	\N	hamza	���w d��@?#���mI�)9�c�<Ncr1�M�
75	\N	2016-04-06 10:40:16.71	\N	jawaad	���b�6nHϭ�}:N�u��[G��2���$T�;
76	\N	2016-04-06 10:42:39.5	\N	Amjada	��B�\t�Z�B�p~I1�<����.�e��x�
77	\N	2016-04-06 10:46:34.977	\N	amjad1	�e�Y B/�A~Hg��O��J?��~������z�
78	\N	2016-04-06 10:48:35.713	\N	uzair2	�e�Y B/�A~Hg��O��J?��~������z�
79	\N	2016-04-06 10:52:24.902	\N	uzair3	������㸠}�(���\r���vc�DU'yE�
80	\N	2016-04-06 10:52:45.191	\N	uzair3	������㸠}�(���\r���vc�DU'yE�
81	\N	2016-04-06 11:40:39.245	\N	uzair@gmail.com	Y�G�*���Y��t�����Y�����s���
82	\N	2016-04-06 12:04:33.715	\N	usman	^�:$�����b���|)�\t��v��ET�C��
83	\N	2016-04-06 12:11:07.974	\N	majjiaad	G�����.�9���^T��YşV������
84	\N	2016-04-06 12:16:52.835	\N	usmankhan	Y�G�*���Y��t�����Y�����s���
85	\N	2016-04-06 14:10:59.522	\N	Hotmail	�����Z�&�"�p2�L��'��\b�*����U
86	\N	2016-04-06 14:11:52.023	\N	hotmaill	�����Z�&�"�p2�L��'��\b�*����U
61	24	2016-04-05 10:14:55.197	2016-04-08 14:50:47.864	amjad@gmail.com	z1�YQ��!�e��g��Н;Ow\f?��k\fW\r0
87	\N	2016-04-08 14:56:58.124	\N	amjadislam@gmail.com	����n��:b����\f?]Z����\f�:�l�
88	\N	2016-04-08 14:58:32.377	\N	usman.chaudhri@gmail.com	Y�G�*���Y��t�����Y�����s���
65	13	2016-04-05 10:47:54.096	2016-04-09 08:56:22.947	traditional@gmail.com	u�5y�0��忑kl�ˍ��\f�C:��*
89	\N	2016-04-09 12:24:33.771	\N	abc@hm.com	Hk4%\v�@\f\n����c:�.�\r\b(�)���%��
90	15	2016-04-11 07:55:28.704	\N	embroidered@gmail.com	�Y�_tl��/���k�5\fA�8�-�_}�9|
\.


--
-- Name: customer_customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('customer_customer_id_seq', 111, true);


--
-- Data for Name: customer_shipping; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY customer_shipping (customer_shipping_id, address, city, country, state, zipcode, created_on, updated_on, customer_id, first_name, last_name, is_active) FROM stdin;
1	1000 Lasse	Lahore	Pakistan	Punjab	54000	\N	\N	5	\N	\N	\N
2	2460 Fulton	San Francisco	USA	CA	94117	2016-03-31 11:02:45.721	\N	53	usman	chaudhri	\N
3	2460 Fulton	San Francisco	USA	CA	94117	2016-03-31 13:01:28.872	\N	53	uzair	rahim	\N
4	2460 Fulton	San Francisco	USA	CA	94117	2016-03-31 13:02:02.712	\N	53	Majid	Khan	\N
5	2460 Fulton	San Francisco	USA	CA	94117	2016-03-31 14:42:54.883	\N	53	Majid	Khan	\N
6	kjds	lahore	CA	lahore	32423	2016-03-31 14:45:57.707	\N	53	amajd	islamss	\N
7	2460 Fulton	San Francisco Bay Area	TA	CA	94117	2016-04-08 21:05:14.022	\N	88	usman	Chaudhri	\N
8	2460 Fulton	San Francisco Bay Area	CA	CA	94117	2016-04-08 21:06:29.249	\N	88	usman	Chaudhri	\N
88	T	T	T	TA	123456	2016-04-29 13:24:32.293	\N	94	T	T	n
89	T	T	T	TA	123456	2016-04-29 13:36:17.249	\N	94	T	T	n
90	T	T	T	TA	123456	2016-04-29 13:37:14.622	\N	94	T	T	n
91	lahore	lahore	Pakistan	TA	123456	2016-04-29 13:40:54.379	\N	94	Amjad	Islam	n
92	lahore	lahore	Pakistan	TA	123456	2016-04-29 13:42:12.718	\N	94	Khan	Khan	n
93	I	I	I	TA	123456	2016-04-29 13:44:56.852	\N	94	I	I	n
94	I	I	I	TA	123456	2016-04-29 13:45:50.059	\N	94	I	I	n
111	RR	RR	RR	TA	123456	2016-04-29 14:55:48.761	\N	109	RR	RR	n
116	lahore	lahore	Pakistan	TA	123456	2016-04-29 15:41:06.654	\N	110	Kamran	Khan	n
12	Islamabad	Islamabad	CA	TA	54203	2016-04-12 12:41:14.74	2016-04-29 13:50:58.558	61	Amjad	Islam KJ	n
109	HH	HH	HH	TA	123456	2016-04-29 14:54:03.595	2016-04-29 14:59:09.049	109	AA	HH	n
85	abc	abc	abc	TA	123456	2016-04-29 13:17:34.565	2016-04-29 13:53:19.998	94	abc asd	abc	n
95	address	city	country	TA	123456	2016-04-29 13:47:39.782	2016-04-29 13:54:05.15	94	name2	name	n
98	lahore	lahore	Pakistan	TA	123456	2016-04-29 14:04:03.423	\N	94	Awais	khan	n
99	lahore	lahore	Pakistan	TA	123456	2016-04-29 14:04:29.296	\N	94	Awais	khan	n
14	DHA	Islamabad	FA	Punjab	54888	2016-04-12 12:43:22.491	2016-04-29 14:05:19.944	61	Amjad	Khan	n
18	Walton	Lahore	Pakistan	Punjab	54660	2016-04-12 12:49:51.765	2016-04-29 14:07:16.038	61	Uzair	Khan	n
110	HH	HH	HH	TA	123456	2016-04-29 14:55:17.923	2016-04-29 15:00:28.767	109	HHAAHH	HH	n
100	lahore	lahore	lahore	TA	123456	2016-04-29 14:08:02.532	\N	61	Name	Name	n
101	Lahore	Lahore	pakistan	TA	123456	2016-04-29 14:09:17.83	\N	94	Husnain	Ali	n
102	lahore	lahore	Pakistan	TA	123456	2016-04-29 14:11:01.032	\N	94	Test	User	n
96	I	I	I	TA	123456	2016-04-29 13:50:14.025	2016-04-29 15:20:31.5	61	I	I	n
103	AAAA	AAAA	AAAA	TA	123456	2016-04-29 14:24:50.703	\N	94	AAAA	AAAA	n
104	BBB	BBB	BBB	TA	123456	2016-04-29 14:25:48.004	\N	94	BBB	BBB	n
86	abc	abc	abc	TA	123456	2016-04-29 13:18:18.469	2016-04-29 14:29:16.946	94	abc Abc	abc ABc	n
17	asd	lad	TA	ads	55566	2016-04-12 12:48:51.46	2016-04-27 10:48:25.188	61	xyz	abc	n
97	I	I	I	TA	123456	2016-04-29 13:50:48.608	2016-04-29 14:30:40.466	61	I	I	n
16	Sabzazar	Lahore	CA	KPK	55663	2016-04-12 12:47:36.205	2016-04-28 09:53:13.354	61	Majid	Ashraf	n
105	Islamabad	Islamabad	Canada	CA	54203	2016-04-29 14:34:48.086	\N	108	Guest	Guest	n
106	Islamabad	Islamabad	Canada	CA	54203	2016-04-29 14:35:08.099	\N	108	Guest	Guest	n
11	lahoe	Lahore	Pakistan	Punjab	54785	2016-04-12 12:40:30.813	2016-04-29 15:20:44.219	61	Amjad	Islam	n
15	Walton	Peshawar	TA	Punjab	54660	2016-04-12 12:45:52.734	2016-04-29 15:20:44.472	61	MajidAshraf	Ashraf	n
79	Lahore	lahore	Pakistan	TA	54000	2016-04-29 06:56:13.962	\N	94	Hussnain	Ali	\N
118	lahore	lahore	Pakistan	TA	123456	2016-04-29 15:47:18.659	2016-04-29 15:47:40.052	111	bot1	abc	n
80	Lahore	lahore	Pakistan	TA	54000	2016-04-29 06:57:44.294	2016-04-29 06:58:22.974	94	Hussnain	Ali	\N
117	lahore	lahore	Pakistan	TA	123456	2016-04-29 15:42:29.834	2016-04-29 15:47:40.063	111	bot1	bot1	y
13	DHA	ISlamabad	MA	Punjab	54546	2016-04-12 12:42:34.389	2016-04-29 15:27:18.113	61	Amjad	Khan	n
19	2460 Fulton	San Francisco	USA	CA	94117	2016-04-18 09:14:13.873	2016-04-29 07:05:15.074	61	Usman	Ghani	n
81	lahore	lahore	PK	TA	54000	2016-04-29 12:02:56.114	\N	94	Test	User	n
82	lahore	lahore	PK	TA	54000	2016-04-29 12:05:02.836	\N	94	Test	User	n
87	abc	abc	abc	TA	123456	2016-04-29 13:18:23.905	2016-04-29 14:47:15	94	abcasdabc	abc	n
83	Test	Test	Test	TA	123456	2016-04-29 12:06:24.009	\N	94	Test	Test	n
107	lahore	lahore	Pakistan	TA	123456	2016-04-29 14:48:33.737	\N	109	JJ	JJ	n
84	Ali	Ali	Ali	TA	1234789	2016-04-29 12:07:43.092	\N	94	Ali	Ali	n
108	lahore	lahore	Pakistan	TA	123456	2016-04-29 14:49:58.431	\N	109	KK	KK	n
10	abc	abc	TA	abc	54000	2016-04-12 12:32:43.248	2016-04-29 15:27:24.185	61	Amjad	khan	y
112	lahore	lahore	Pakistan	TA	123456	2016-04-29 15:28:56.491	\N	61	NewUser	Test	n
113	lahore	lahore	Pakistan	TA	123456	2016-04-29 15:36:31.443	\N	61	ASA	ASA	n
114	lahore	lahore	Pakistan	TA	123456	2016-04-29 15:37:30.556	\N	61	AK	Ak	n
115	lahore	lahore	Pakistan	TA	123456	2016-04-29 15:40:10.322	\N	61	TestA	TestA	n
\.


--
-- Name: customershipping_customershipping_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('customershipping_customershipping_id_seq', 118, true);


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase) FROM stdin;
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
\.


--
-- Data for Name: designer; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY designer (designer_id, image_path, designer_name, shop_id, created_on, updated_on, details) FROM stdin;
9999	/shop/Nayyar	Nayyar Chaudhri	1	\N	\N	\N
10000	/shop/Gulgs	Usman Chaudhri	2	\N	\N	\N
10001	/shop/LeatherHeaven	Jawaria	3	\N	\N	\N
10010	\N	HUMA MANZOOR	13	\N	\N	\N
10011	\N	AATIQA GILL	14	\N	\N	\N
10012	\N	NAVEERA KHALID	15	\N	\N	\N
10016	\N	Maria Ali	26	\N	\N	 
\.


--
-- Name: designer_designer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('designer_designer_id_seq', 10049, true);


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

COPY image_info (image_info_id, image_path, created_on, updated_on, image_count) FROM stdin;
57	/listing/img-68.jpg	\N	2016-01-22 11:14:53.315	\N
60	/listing/img-71.jpg	\N	2016-01-22 11:15:24.193	\N
1	/listing/img-05.jpg	\N	2016-01-14 02:49:50.276	\N
2	/listing/img-06.jpg	\N	2016-01-14 02:50:24.178	\N
3	/listing/img-07.jpg	\N	2016-01-14 02:51:08.789	\N
4	/listing/img-08.jpg	\N	2016-01-14 02:52:14.058	\N
5	/listing/img-09.jpg	\N	2016-01-14 02:52:30.138	\N
6	/listing/img-10.jpg	\N	2016-01-14 02:52:43.388	\N
61	/listing/img-72.jpg	\N	2016-01-22 11:15:32.7	\N
62	/listing/img-73.jpg	\N	2016-01-22 11:15:40.796	\N
63	/listing/img-74.jpg	\N	2016-01-22 11:15:50.097	\N
10	/listing/img.jpg	\N	2016-01-14 02:54:03.497	\N
64	/listing/img-75.jpg	\N	2016-01-22 11:15:58.517	\N
65	/listing/img-76.jpg	\N	2016-01-22 11:16:20.561	\N
15	/listing/img-15.jpg	\N	2016-01-14 03:00:12.102	\N
16	/listing/img-16.jpg	\N	2016-01-14 03:00:48.534	\N
17	/listing/img-17.jpg	\N	2016-01-14 03:01:04.084	\N
18	/listing/img-18.jpg	\N	2016-01-14 03:01:16.001	\N
11	/listing/img-05.jpg	\N	2016-01-14 03:13:11.485	\N
9	/listing/img-04.jpg	\N	2016-01-14 03:13:19.983	\N
8	/listing/img-03.jpg	\N	2016-01-14 03:13:26.905	\N
66	/listing/img-77.jpg	\N	2016-01-22 11:16:29.827	\N
7	/listing/img-02.jpg	\N	2016-01-16 13:47:59.854	\N
67	/listing/img-78.jpg	\N	2016-01-22 11:16:39.481	\N
68	/listing/img-79.jpg	\N	2016-01-22 11:16:49.409	\N
70	/listing/img-81.jpg	\N	2016-01-22 11:17:40.687	\N
71	/listing/img-82.jpg	\N	2016-01-22 11:17:51.176	\N
72	/listing/img-83.jpg	\N	2016-01-22 11:18:03.477	\N
73	/listing/img-84.jpg	\N	2016-01-22 11:18:13.878	\N
74	/listing/img-85.jpg	\N	2016-01-22 11:18:27.335	\N
75	/listing/img-86.jpg	\N	2016-01-22 11:20:38.712	\N
76	/listing/img-87.jpg	\N	2016-01-22 11:20:55.916	\N
77	/listing/img-88pg	\N	2016-01-22 11:21:10.208	\N
81	/listing/img-90.jpg	\N	\N	\N
82	/listing/img-93.jpg	\N	\N	\N
83	/listing/img-94.jpg	\N	\N	\N
84	/listing/img-95.jpg	\N	\N	\N
85	/listing/img-96.jpg	\N	\N	\N
86	/listing/img-97.jpg	\N	\N	\N
87	/listing/img-98.jpg	\N	\N	\N
88	/listing/img-99.jpg	\N	\N	\N
69	listing/img-80.jpg	\N	\N	\N
89	/listing/img-100.jpg	\N	\N	\N
90	/listing/img-101.jpg	\N	\N	\N
91	/listing/img-101.jpg	\N	\N	\N
92	/listing/img-102.jpg	\N	\N	\N
93	/listing/img-103.jpg	\N	\N	\N
94	/listing/img-104.jpg	\N	\N	\N
95	/listing/img-105.jpg	\N	\N	\N
96	/listing/img-106.jpg	\N	\N	\N
97	/listing/img-108.jpg	\N	\N	\N
98	/listing/img-108.jpg	\N	\N	\N
99	/listing/img-109.jpg	\N	\N	\N
100	/listing/img-110.jpg	\N	\N	\N
101	/listing/img-111.jpg	\N	\N	\N
102	/listing/img-112.jpg	\N	\N	\N
103	/listing/img-112.jpg	\N	\N	\N
104	/listing/img-113.jpg	\N	\N	\N
105	/listing/img-114.jpg	\N	\N	\N
106	/listing/img-115.jpg	\N	\N	\N
107	/listing/img-116.jpg	\N	\N	\N
108	/listing/img-117.jpg	\N	\N	\N
109	/listing/img-118.jpg	\N	\N	\N
110	/listing/img-119.jpg	\N	\N	\N
111	/listing/img-120.jpg	\N	\N	\N
131	/listing/img-141.jpg	\N	\N	\N
132	/listing/img-142.jpg	\N	\N	\N
133	/listing/img-143.jpg	\N	\N	\N
134	/listing/img-144.jpg	\N	\N	\N
142	/listing/	\N	\N	\N
143	/listing/	\N	\N	\N
144	/listing/	\N	\N	\N
145	/listing/	\N	\N	\N
146	/listing/	\N	\N	\N
148	/listing/	\N	\N	\N
149	/listing/	\N	\N	\N
19	/listing/img-30.jpg	\N	2016-01-22 11:03:46.457	\N
20	/listing/img-31.jpg	\N	2016-01-22 11:03:53.273	\N
150	/listing/	\N	\N	\N
21	/listing/img-32.jpg	\N	2016-01-22 11:04:04.764	\N
22	/listing/img-33.jpg	\N	2016-01-22 11:04:09.968	\N
23	/listing/img-34.jpg	\N	2016-01-22 11:04:14.909	\N
24	/listing/img-35.jpg	\N	2016-01-22 11:04:20.546	\N
25	/listing/img-36.jpg	\N	2016-01-22 11:05:49.589	\N
26	/listing/img-37.jpg	\N	2016-01-22 11:05:54.885	\N
27	/listing/img-38.jpg	\N	2016-01-22 11:05:59.848	\N
28	/listing/img-39.jpg	\N	2016-01-22 11:06:05.353	\N
29	/listing/img-40.jpg	\N	2016-01-22 11:06:10.895	\N
30	/listing/img-41.jpg	\N	2016-01-22 11:06:16.321	\N
31	/listing/img-42.jpg	\N	2016-01-22 11:06:21.665	\N
32	/listing/img-43.jpg	\N	2016-01-22 11:07:48.012	\N
33	/listing/img-44.jpg	\N	2016-01-22 11:07:52.996	\N
34	/listing/img-45.jpg	\N	2016-01-22 11:07:58.929	\N
35	/listing/img-46.jpg	\N	2016-01-22 11:08:04.512	\N
36	/listing/img-47.jpg	\N	2016-01-22 11:08:10.731	\N
37	/listing/img-48.jpg	\N	2016-01-22 11:08:16.034	\N
38	/listing/img-49.jpg	\N	2016-01-22 11:08:21.02	\N
39	/listing/img-50.jpg	\N	2016-01-22 11:08:26.052	\N
40	/listing/img-51.jpg	\N	2016-01-22 11:08:31.021	\N
152	/listing/	\N	\N	\N
162	/listing/	\N	\N	\N
166	/listing/	\N	\N	\N
171	/listing/	\N	\N	\N
172	/listing/	\N	\N	\N
173	/listing/	\N	\N	\N
177	/listing/	\N	\N	\N
182	/listing/	\N	\N	\N
183	/listing/	\N	\N	\N
198	/listing/	\N	\N	\N
200	/listing/	\N	\N	\N
201	/listing/	\N	\N	\N
218	/listing/	\N	\N	\N
219	/listing/	\N	\N	\N
220	/listing/	\N	\N	\N
225	/listing/	\N	\N	\N
226	/listing/	\N	\N	\N
228	/listing/	\N	\N	\N
41	/listing/img-52.jpg	\N	2016-01-22 11:08:36.052	\N
42	/listing/img-53.jpg	\N	2016-01-22 11:08:40.797	\N
43	/listing/img-54.jpg	\N	2016-01-22 11:08:46.033	\N
44	/listing/img-55.jpg	\N	2016-01-22 11:09:13.844	\N
45	/listing/img-56.jpg	\N	2016-01-22 11:09:19.434	\N
46	/listing/img-57.jpg	\N	2016-01-22 11:09:25.149	\N
47	/listing/img-58.jpg	\N	2016-01-22 11:09:29.999	\N
48	/listing/img-59.jpg	\N	2016-01-22 11:09:35.907	\N
49	/listing/img-60.jpg	\N	2016-01-22 11:09:40.599	\N
50	/listing/img-61.jpg	\N	2016-01-22 11:09:45.313	\N
51	/listing/img-62.jpg	\N	2016-01-22 11:09:50.39	\N
52	/listing/img-63.jpg	\N	2016-01-22 11:09:55.351	\N
53	/listing/img-64.jpg	\N	2016-01-22 11:10:00.95	\N
54	/listing/img-65.jpg	\N	2016-01-22 11:10:06.838	\N
55	/listing/img-66.jpg	\N	2016-01-22 11:10:12.226	\N
56	/listing/img-67.jpg	\N	2016-01-22 11:10:18.869	\N
58	/listing/img-69.jpg	\N	2016-01-22 11:10:38.118	\N
59	/listing/img-70.jpg	\N	2016-01-22 11:10:43.125	\N
78	/listing/img-89.jpg	\N	2016-01-22 11:22:01.044	\N
79	/listing/img-88.jpg	\N	\N	\N
80	/listing/img-89.jpg	\N	\N	\N
112	/listing/img-121.jpg	\N	\N	\N
113	/listing/img-122.jpg	\N	\N	\N
114	/listing/img-123.jpg	\N	\N	\N
115	/listing/img-125.jpg	\N	\N	\N
116	/listing/img-126.jpg	\N	\N	\N
117	/listing/img-127.jpg	\N	\N	\N
118	/listing/img-128.jpg	\N	\N	\N
119	/listing/img-129.jpg	\N	\N	\N
120	/listing/img-130.jpg	\N	\N	\N
121	/listing/img-131.jpg	\N	\N	\N
122	/listing/img-132.jpg	\N	\N	\N
123	/listing/img-133.jpg	\N	\N	\N
124	/listing/img-134.jpg	\N	\N	\N
125	/listing/img-135.jpg	\N	\N	\N
126	/listing/img-136.jpg	\N	\N	\N
127	/listing/img-137.jpg	\N	\N	\N
128	/listing/img-138.jpg	\N	\N	\N
129	/listing/img-139.jpg	\N	\N	\N
130	/listing/img-140.jpg	\N	\N	\N
135	/listing/img-201.jpg	\N	\N	\N
136	/listing/img-201.jpg	\N	\N	\N
137	/listing/img-201.jpg	\N	\N	\N
138	/listing/img-201.jpg	\N	\N	\N
139	/listing/	\N	\N	\N
140	/listing/	\N	\N	\N
141	/listing/	\N	\N	\N
147	/listing/	\N	\N	\N
151	/listing/	\N	\N	\N
153	/listing/	\N	\N	\N
154	/listing/	\N	\N	\N
155	/listing/	\N	\N	\N
156	/listing/	\N	\N	\N
157	/listing/	\N	\N	\N
158	/listing/	\N	\N	\N
160	/listing/	\N	\N	\N
161	/listing/	\N	\N	\N
163	/listing/	\N	\N	\N
164	/listing/	\N	\N	\N
165	/listing/	\N	\N	\N
167	/listing/	\N	\N	\N
168	/listing/	\N	\N	\N
169	/listing/	\N	\N	\N
170	/listing/	\N	\N	\N
181	/listing/	\N	\N	\N
184	/listing/	\N	\N	\N
185	/listing/	\N	\N	\N
186	/listing/	\N	\N	\N
187	/listing/	\N	\N	\N
188	/listing/	\N	\N	\N
189	/listing/	\N	\N	\N
190	/listing/	\N	\N	\N
191	/listing/	\N	\N	\N
192	/listing/	\N	\N	\N
193	/listing/	\N	\N	\N
194	/listing/	\N	\N	\N
195	/listing/	\N	\N	\N
196	/listing/	\N	\N	\N
197	/listing/	\N	\N	\N
199	/listing/	\N	\N	\N
202	/listing/	\N	\N	\N
203	/listing/	\N	\N	\N
204	/listing/	\N	\N	\N
214	/listing/	\N	\N	\N
217	/listing/	\N	\N	\N
221	/listing/	\N	\N	\N
229	/listing/	\N	\N	\N
230	/listing/	\N	\N	\N
231	/listing/	\N	\N	\N
233	/listing/	\N	\N	\N
234	/listing/	\N	\N	\N
235	/listing/	\N	\N	\N
238	/listing/	\N	\N	\N
242	/listing/	\N	\N	\N
246	/listing/	\N	\N	3
241	/listing/	\N	\N	1
243	/listing/	\N	\N	2
244	/listing/	\N	\N	2
245	/listing/	\N	\N	2
247	/listing/	\N	\N	\N
248	/listing/	\N	\N	\N
249	/listing/	\N	\N	\N
250	/listing/	\N	\N	\N
251	/listing/	\N	\N	\N
252	/listing/	\N	\N	\N
253	/listing/	\N	\N	\N
254	/listing/	\N	\N	\N
255	/listing/	\N	\N	\N
256	/listing/	\N	\N	\N
257	/listing/	\N	\N	\N
258	/listing/	\N	\N	\N
259	/listing/	\N	\N	\N
260	/listing/	\N	\N	\N
261	/listing/	\N	\N	\N
262	/listing/	\N	\N	\N
263	/listing/	\N	\N	\N
264	/listing/	\N	\N	\N
265	/listing/	\N	\N	\N
266	/listing/	\N	\N	\N
267	/listing/	\N	\N	\N
268	/listing/	\N	\N	\N
269	/listing/	\N	\N	\N
270	/listing/	\N	\N	\N
271	/listing/	\N	\N	\N
272	/listing/	\N	\N	\N
273	/listing/	\N	\N	\N
274	/listing/	\N	\N	\N
275	/listing/	\N	\N	\N
276	/listing/	\N	\N	\N
277	/listing/	\N	\N	\N
278	/listing/	\N	\N	\N
279	/listing/	\N	\N	\N
280	/listing/	\N	\N	\N
281	/listing/	\N	\N	\N
282	/listing/	\N	\N	\N
283	/listing/	\N	\N	\N
284	/listing/	\N	\N	\N
285	/listing/	\N	\N	\N
286	/listing/	\N	\N	\N
287	/listing/	\N	\N	\N
\.


--
-- Name: imageinfo_imageinfo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('imageinfo_imageinfo_id_seq', 287, true);


--
-- Name: order_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('order_order_id_seq', 48, true);


--
-- Data for Name: order_product; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY order_product (order_id, product_category_id, product_id, product_image_path, product_name, product_price, product_quantity, product_shop_id, product_sku, customer_id, created_on, updated_on, status) FROM stdin;
29	3	204	/listing	Pretty Dark Blue top	60	1	24	Birds Han	61	2016-04-27 11:09:08.341	\N	\N
30	3	204	/listing	Pretty Dark Blue top	60	1	24	Birds Han	61	2016-04-27 11:09:42.475	\N	\N
31	3	204	/listing	Pretty Dark Blue top	60	1	24	Birds Han	61	2016-04-27 11:10:08.4	\N	\N
32	3	204	/listing	Pretty Dark Blue top	60	1	24	Birds Han	61	2016-04-27 11:10:14.601	\N	\N
33	3	202	/listing	Gold blue Top	59.99	1	15	Birds Han	61	2016-04-28 07:52:02.198	\N	\N
34	3	202	/listing	Gold blue Top	59.99	1	15	Birds Han	61	2016-04-28 08:46:44.406	\N	\N
35	3	193	/listing	Black and red dragon embroiddered top	49.99	3	2	Birds Han	61	2016-04-28 08:46:44.607	\N	\N
36	3	193	/listing	Black and red dragon embroiddered top	49.99	1	2	Birds Han	61	2016-04-28 08:46:44.613	\N	\N
37	3	204	/listing	Pretty Dark Blue top	60	1	24	Birds Han	61	2016-04-28 09:17:53.022	\N	\N
38	3	204	/listing	Pretty Dark Blue top	60	1	24	Birds Han	61	2016-04-28 09:17:53.039	\N	\N
39	3	204	/listing	Pretty Dark Blue top	60	1	24	Birds Han	61	2016-04-28 09:17:53.057	\N	\N
40	3	204	/listing	Pretty Dark Blue top	60	1	24	Birds Han	61	2016-04-28 09:17:53.075	\N	\N
41	3	204	/listing	Pretty Dark Blue top	60	1	24	Birds Han	61	2016-04-28 09:17:54.044	\N	\N
42	3	200	/listing	Couture Tops	49.99	4	14	Birds Han	61	2016-04-28 09:24:34.114	\N	\N
43	3	199	/listing	Mughal Art Inspired Dress	49.99	1	13	Birds Han	61	2016-04-28 13:46:02.742	\N	\N
44	3	204	/listing	Pretty Dark Blue top	60	1	24	Birds Han	61	2016-04-28 13:46:02.878	\N	\N
45	3	204	/listing	Pretty Dark Blue top	60	1	24	Birds Han	61	2016-04-28 13:46:02.916	\N	\N
46	3	204	/listing	Pretty Dark Blue top	60	2	24	Birds Han	61	2016-04-28 13:46:02.972	\N	\N
47	3	204	/listing	Pretty Dark Blue top	60	1	24	Birds Han	61	2016-04-28 13:48:41.192	\N	\N
48	3	199	/listing	Mughal Art Inspired Dress	49.99	1	13	Birds Han	61	2016-04-28 13:56:10.427	\N	\N
\.


--
-- Data for Name: pricing_product; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY pricing_product (pricing_product_id, stored_value, created_on, updated_on) FROM stdin;
1	29.95	\N	\N
2	29.95	\N	\N
3	37.73	\N	\N
4	29.95	\N	\N
5	39.61	\N	\N
6	39.61	\N	\N
7	37.73	\N	\N
8	59.99	\N	\N
9	69.99	\N	\N
10	79.99	\N	\N
11	89.99	\N	\N
15	59.99	\N	\N
16	99.99	\N	\N
17	89.99	\N	\N
18	85.99	\N	\N
19	89.99	\N	\N
20	89.99	\N	\N
21	89.99	\N	\N
22	89.99	\N	\N
23	89.99	\N	\N
24	89.99	\N	\N
25	89.99	\N	\N
26	89.99	\N	\N
27	89.99	\N	\N
28	89.99	\N	\N
29	89.99	\N	\N
30	89.99	\N	\N
31	89.99	\N	\N
32	89.99	\N	\N
33	89.99	\N	\N
34	89.99	\N	\N
35	89.99	\N	\N
36	89.99	\N	\N
37	89.99	\N	\N
38	89.99	\N	\N
39	89.99	\N	\N
40	89.99	\N	\N
41	89.99	\N	\N
42	89.99	\N	\N
43	89.99	\N	\N
44	89.99	\N	\N
45	89.99	\N	\N
46	89.99	\N	\N
47	89.99	\N	\N
48	89.99	\N	\N
49	89.99	\N	\N
50	89.99	\N	\N
51	89.99	\N	\N
52	89.99	\N	\N
53	89.99	\N	\N
54	89.99	\N	\N
55	89.99	\N	\N
56	89.99	\N	\N
57	89.99	\N	\N
58	89.99	\N	\N
59	89.99	\N	\N
60	89.99	\N	\N
61	89.99	\N	\N
62	89.99	\N	\N
63	89.99	\N	\N
64	89.99	\N	\N
65	89.99	\N	\N
66	89.99	\N	\N
67	89.99	\N	\N
68	89.99	\N	\N
69	89.99	\N	\N
70	89.99	\N	\N
71	89.99	\N	\N
72	89.99	\N	\N
73	89.99	\N	\N
74	89.99	\N	\N
75	89.99	\N	\N
76	89.99	\N	\N
77	89.99	\N	\N
78	89.99	\N	\N
79	89.99	\N	\N
80	89.99	\N	\N
81	89.99	\N	\N
82	89.99	\N	\N
83	89.99	\N	\N
84	89.99	\N	\N
85	89.99	\N	\N
86	89.99	\N	\N
87	89.99	\N	\N
88	89.99	\N	\N
89	89.99	\N	\N
90	89.99	\N	\N
91	89.99	\N	\N
92	89.99	\N	\N
93	89.99	\N	\N
94	89.99	\N	\N
95	89.99	\N	\N
96	89.99	\N	\N
97	89.99	\N	\N
98	89.99	\N	\N
99	89.99	\N	\N
100	89.99	\N	\N
101	89.99	\N	\N
102	89.99	\N	\N
103	89.99	\N	\N
104	89.99	\N	\N
105	89.99	\N	\N
106	89.99	\N	\N
107	89.99	\N	\N
108	89.99	\N	\N
109	89.99	\N	\N
110	89.99	\N	\N
111	89.99	\N	\N
112	89.99	\N	\N
113	89.99	\N	\N
114	89.99	\N	\N
115	89.99	\N	\N
116	89.99	\N	\N
117	89.99	\N	\N
118	89.99	\N	\N
119	89.99	\N	\N
120	89.99	\N	\N
121	89.99	\N	\N
122	89.99	\N	\N
123	89.99	\N	\N
124	89.99	\N	\N
125	89.99	\N	\N
126	89.99	\N	\N
127	89.99	\N	\N
128	89.99	\N	\N
129	89.99	\N	\N
130	89.99	\N	\N
131	89.99	\N	\N
132	89.99	\N	\N
133	89.99	\N	\N
134	89.99	\N	\N
135	50.00	\N	\N
136	50.00	\N	\N
137	50.00	\N	\N
138	50.00	\N	\N
139	20.00	\N	\N
140	20.00	\N	\N
141	20.00	\N	\N
142	20.00	\N	\N
143	25.00	\N	\N
144	25.00	\N	\N
145	25.00	\N	\N
146	20.00	\N	\N
147	60.00	\N	\N
148	55.00	\N	\N
149	26.00	\N	\N
150	25.00	\N	\N
151	20.00	\N	\N
152	20.00	\N	\N
153	89.99	\N	\N
154	5.00	\N	\N
155	5.00	\N	\N
156	5.00	\N	\N
157	3.00	\N	\N
158	8.00	\N	\N
160	12.00	\N	\N
161	20.00	\N	\N
162	20.00	\N	\N
163	20.00	\N	\N
164	55.00	\N	\N
165	20.00	\N	\N
166	50.00	\N	\N
167	20.00	\N	\N
168	5.00	\N	\N
169	50.00	\N	\N
170	29.99	\N	\N
171	45.00	\N	\N
172	20.00	\N	\N
173	30.00	\N	\N
177	80.00	\N	\N
181	5.00	\N	\N
182	5.00	\N	\N
183	4.00	\N	\N
184	49.99	\N	\N
185	49.99	\N	\N
186	49.99	\N	\N
187	49.99	\N	\N
188	49.99	\N	\N
189	49.99	\N	\N
190	49.99	\N	\N
191	49.99	\N	\N
192	49.99	\N	\N
193	49.99	\N	\N
194	49.99	\N	\N
195	49.99	\N	\N
196	49.99	\N	\N
197	49.99	\N	\N
198	49.99	\N	\N
199	49.99	\N	\N
200	49.99	\N	\N
201	49.99	\N	\N
202	59.99	\N	\N
203	85.00	\N	\N
204	60.00	\N	\N
214	2388.00	\N	\N
217	2599.00	\N	\N
218	2599.00	\N	\N
219	2599.00	\N	\N
220	2599.00	\N	\N
221	2599.00	\N	\N
225	2899.00	\N	\N
226	2899.00	\N	\N
228	2899.00	\N	\N
229	2899.00	\N	\N
230	2899.00	\N	\N
231	7.00	\N	\N
233	2388.00	\N	\N
234	2388.00	\N	\N
235	2388.00	\N	\N
238	2388.00	\N	\N
241	2388.00	\N	\N
242	1576.00	\N	\N
243	1576.00	\N	\N
244	460.00	\N	\N
245	480.00	\N	\N
246	1625.00	\N	\N
247	20.00	\N	\N
248	1000.00	\N	\N
249	200.00	\N	\N
250	400.00	\N	\N
251	100.00	\N	\N
252	100.00	\N	\N
253	10.00	\N	\N
254	100.00	\N	\N
255	100.00	\N	\N
256	10.00	\N	\N
257	10.00	\N	\N
258	101.00	\N	\N
259	101.00	\N	\N
260	101.00	\N	\N
261	101.00	\N	\N
262	101.00	\N	\N
263	101.00	\N	\N
264	10.00	\N	\N
265	10.00	\N	\N
266	1000.00	\N	\N
267	10.00	\N	\N
268	101.00	\N	\N
269	101.00	\N	\N
270	100.00	\N	\N
271	100.00	\N	\N
272	101.00	\N	\N
273	100.00	\N	\N
274	111.00	\N	\N
275	101.00	\N	\N
276	222.00	\N	\N
277	100.00	\N	\N
278	101.00	\N	\N
279	100.00	\N	\N
280	101.00	\N	\N
281	123.00	\N	\N
282	100.00	\N	\N
283	123.00	\N	\N
284	5.00	\N	\N
285	5.00	\N	\N
286	101.00	\N	\N
287	5.00	\N	\N
\.


--
-- Name: pricing_product_pricing_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('pricing_product_pricing_product_id_seq', 287, true);


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY product (product_id, long_desc, name, quantity, short_desc, category_id, featured_product_id, pricing_product_id, shop_id, image_info_id, created_on, updated_on, sku) FROM stdin;
184	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Embroided polka dot elegant top	2	love your loved one's with elegant embroided elegant top.	3	\N	184	3	184	2016-03-02 12:29:47.897	2016-03-08 12:24:25.871	sku-u1
195	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Pink flower design shirt	5	Pink flower design shirt with green color combination	3	\N	195	2	195	2016-03-05 08:18:40.284	2016-03-08 12:25:29.121	as44
202	\N	Gold blue Top	12	hand embroidered gold blue top.	3	\N	202	15	202	2016-03-18 08:39:25.387	\N	Gold blue Top
204	\N	Pretty Dark Blue top	10	Embroidered Dark blue top	3	\N	204	24	204	2016-03-18 10:43:08.769	\N	prety
241	- all clothes can be customized, colors and cuts can be changed and we respect every clients budget, hence can be customized according to the budget as well. \n- Time to create the product is at-least one month.	Red bridal	4	The bridal is made on silk kaadi net with gold embellishment. Duppatta has a 4 sided border and the front border is heavier then the other 3 sides. The azzar is banarsi with embellishment at the bottom of the lowers.	14	\N	241	26	241	2016-04-12 07:37:17.257	\N	Red bridal
185	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Silver embroidery on green top	5	hand stitched green top with embroidered neck line.	3	\N	185	1	185	2016-03-02 12:36:57.645	2016-03-08 12:25:43.878	skuu2
199	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Mughal Art Inspired Dress	5	and embroidered traditional sindhi inspired dress	3	\N	199	13	199	2016-03-07 20:49:10.511	2016-03-08 12:25:54.373	asdf1
169	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Red Woman Shirt	5	Red Woman Shirt	1	\N	169	1	169	2016-02-23 06:56:00.379	2016-03-08 12:26:05.48	sku-red
186	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Red and black lace top	5	Red with black perfect for party wear.	3	\N	186	3	186	2016-03-03 06:56:12.5	2016-03-08 12:26:17.181	as12
187	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Elegant hand embroidered black sindhi top	5	all embroidery is handmade to inspire you with culture and traditions.	3	\N	187	3	187	2016-03-03 06:58:07.834	2016-03-08 12:26:26.376	as2
188	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Black and white dress with lace around neck	5	hand stitched black and white lace dress.	3	\N	188	3	188	2016-03-03 06:59:25.147	2016-03-08 12:26:37.573	as3
189	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Gold blue Top	5	hand embroidered gold blue top	3	\N	189	3	189	2016-03-03 07:01:24.949	2016-03-08 12:26:48.369	as4
190	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Gold brown embroidered top	4		3	\N	190	3	190	2016-03-03 07:02:48.803	2016-03-08 12:27:00.207	as5
191	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Pretty Blue top	5	Embroidered blue top	3	\N	191	2	191	2016-03-03 07:09:09.697	2016-03-08 12:27:11.477	as6
192	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Pink flower top	4	wear it with white jeans	3	\N	192	2	192	2016-03-03 07:10:07.601	2016-03-08 12:27:23.369	as7
193	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Black and red dragon embroiddered top	5	wear it with leather tights	3	\N	193	2	193	2016-03-03 07:11:15.901	2016-03-08 12:27:32.632	as9
194	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Simplicity with elegance Red Sash	5		3	\N	194	2	194	2016-03-03 07:15:52.828	2016-03-08 12:27:41.208	as78
200	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Couture Tops	5	design tops that inspire	3	\N	200	14	200	2016-03-07 20:53:04.228	2016-03-08 12:27:51.317	asd33
201	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Embroided Neckline Top	4	customizable embroidered neckline	3	\N	201	15	201	2016-03-07 20:55:20.345	2016-03-08 12:28:00.384	asd5565
243	- all clothes can be customized, colors and cuts can be changed and we respect every clients budget, hence can be customized according to the budget as well. \n- Time to create the product is at-least one month.	Baby pink	5	Made on organza, thread and silver embellishment.	14	\N	243	26	243	2016-04-12 07:49:02.522	\N	Baby pink
244	- all clothes can be customized, colors and cuts can be changed and we respect every clients budget, hence can be customized according to the budget as well. \n- Time to create the product is at-least one month.	black chooli with gold embellishment	5	black chooli with gold embellishment, with plain lehnga and gold dupatta	14	\N	244	26	244	2016-04-12 07:51:06.23	\N	black chooli with gold embellishment
245	- all clothes can be customized, colors and cuts can be changed and we respect every clients budget, hence can be customized according to the budget as well. \n- Time to create the product is at-least one month.	Mint green shirt with silver embellishment	5	Mint green shirt with silver embellishment with lowers of your choice, dupptta will be emerald green in colour.	14	\N	245	26	245	2016-04-12 07:52:57.973	\N	Mint green shirt with silver embellishment
246	- all clothes can be customized, colors and cuts can be changed and we respect every clients budget, hence can be customized according to the budget as well. \n- Time to create the product is at-least one month.	Beige shirt with gold and silver	5	beige shirt with gold and silver work with a plain lehnga work at the border and a fushia pink duptta	14	\N	246	26	246	2016-04-12 07:54:40.396	\N	Beige shirt with gold and silver
170	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	White Tunic Shirt	5	White Tunic Shirt	1	\N	170	1	170	2016-02-28 10:09:02.586	2016-03-08 12:28:10.921	sku-white-t
171	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	White stylish Tunic Shirt	3	White stylish Tunic Shirt with Black Neck	1	\N	171	1	171	2016-02-28 10:14:44.857	2016-03-08 12:28:19.157	White Shirt
172	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Blue Tunic Shirt	3	Blue Tunic Shirt with stylish Golden Neck	1	\N	172	1	172	2016-02-28 10:19:01.41	2016-03-08 12:28:26.999	Blue sku
173	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	Brown Tunic Shirt	5	Brown Tunic Shirt with design	1	\N	173	1	173	2016-02-28 10:21:34.845	2016-03-08 12:28:36.403	Brown sku
177	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.	white Tunic black dot Shirt	4	white Tunic shirt with black dots and style	1	\N	177	1	177	2016-02-28 10:29:58.94	2016-03-08 12:28:44.685	white Tunic Shirt
\.


--
-- Name: product_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('product_product_id_seq', 287, true);


--
-- Data for Name: product_variation; Type: TABLE DATA; Schema: public; Owner: njcyxzlnmtiyvb
--

COPY product_variation (productvariation_id, color, material, mics, price, quantity, size, product_id, created_on, updated_on) FROM stdin;
379	Red	\N	\N	\N	\N	L	241	\N	\N
380	Red	\N	\N	\N	\N	M	241	\N	\N
381	Red	\N	\N	\N	\N	S	241	\N	\N
214	Red	\N	\N	\N	\N	L	184	\N	\N
215	Red	\N	\N	\N	\N	M	184	\N	\N
216	Red	\N	\N	\N	\N	S	184	\N	\N
217	Red	\N	\N	\N	\N	L	185	\N	\N
218	Red	\N	\N	\N	\N	M	185	\N	\N
219	Red	\N	\N	\N	\N	S	185	\N	\N
247	Red	\N	\N	\N	\N	L	195	\N	\N
248	Red	\N	\N	\N	\N	M	195	\N	\N
249	Red	\N	\N	\N	\N	S	195	\N	\N
259	Red	\N	\N	\N	\N	L	199	\N	\N
260	Red	\N	\N	\N	\N	M	199	\N	\N
261	Red	\N	\N	\N	\N	S	199	\N	\N
266	Red	\N	\N	\N	\N	L	202	\N	\N
267	Red	\N	\N	\N	\N	M	202	\N	\N
271	Red	\N	\N	\N	\N	L	204	\N	\N
272	Red	\N	\N	\N	\N	M	204	\N	\N
273	Red	\N	\N	\N	\N	S	204	\N	\N
385	Red	\N	\N	\N	\N	L	243	\N	\N
386	Red	\N	\N	\N	\N	M	243	\N	\N
387	Red	\N	\N	\N	\N	S	243	\N	\N
388	Red	\N	\N	\N	\N	L	244	\N	\N
389	Red	\N	\N	\N	\N	M	244	\N	\N
390	Red	\N	\N	\N	\N	S	244	\N	\N
220	Red	\N	\N	\N	\N	L	186	\N	\N
169	Red	\N	\N	\N	\N	L	169	\N	\N
170	Red	\N	\N	\N	\N	M	169	\N	\N
171	Red	\N	\N	\N	\N	S	169	\N	\N
172	Red	\N	\N	\N	\N	L	170	\N	\N
173	Red	\N	\N	\N	\N	M	170	\N	\N
174	Red	\N	\N	\N	\N	S	170	\N	\N
175	Red	\N	\N	\N	\N	L	171	\N	\N
176	Red	\N	\N	\N	\N	M	171	\N	\N
177	Red	\N	\N	\N	\N	S	171	\N	\N
178	Red	\N	\N	\N	\N	L	172	\N	\N
179	Red	\N	\N	\N	\N	M	172	\N	\N
180	Red	\N	\N	\N	\N	S	172	\N	\N
181	Red	\N	\N	\N	\N	L	173	\N	\N
182	Red	\N	\N	\N	\N	M	173	\N	\N
183	Red	\N	\N	\N	\N	S	173	\N	\N
193	Red	\N	\N	\N	\N	L	177	\N	\N
194	Red	\N	\N	\N	\N	M	177	\N	\N
195	Red	\N	\N	\N	\N	S	177	\N	\N
221	Red	\N	\N	\N	\N	M	186	\N	\N
222	Red	\N	\N	\N	\N	S	186	\N	\N
223	Red	\N	\N	\N	\N	L	187	\N	\N
224	Red	\N	\N	\N	\N	M	187	\N	\N
225	Red	\N	\N	\N	\N	S	187	\N	\N
226	Red	\N	\N	\N	\N	L	188	\N	\N
227	Red	\N	\N	\N	\N	M	188	\N	\N
228	Red	\N	\N	\N	\N	S	188	\N	\N
229	Red	\N	\N	\N	\N	L	189	\N	\N
230	Red	\N	\N	\N	\N	M	189	\N	\N
231	Red	\N	\N	\N	\N	S	189	\N	\N
232	Red	\N	\N	\N	\N	L	190	\N	\N
233	Red	\N	\N	\N	\N	M	190	\N	\N
234	Red	\N	\N	\N	\N	S	190	\N	\N
235	Red	\N	\N	\N	\N	L	191	\N	\N
236	Red	\N	\N	\N	\N	M	191	\N	\N
237	Red	\N	\N	\N	\N	S	191	\N	\N
238	Red	\N	\N	\N	\N	L	192	\N	\N
239	Red	\N	\N	\N	\N	M	192	\N	\N
240	Red	\N	\N	\N	\N	S	192	\N	\N
241	Red	\N	\N	\N	\N	L	193	\N	\N
242	Red	\N	\N	\N	\N	M	193	\N	\N
243	Red	\N	\N	\N	\N	S	193	\N	\N
244	Red	\N	\N	\N	\N	L	194	\N	\N
245	Red	\N	\N	\N	\N	M	194	\N	\N
246	Red	\N	\N	\N	\N	S	194	\N	\N
391	Red	\N	\N	\N	\N	L	245	\N	\N
392	Red	\N	\N	\N	\N	M	245	\N	\N
393	Red	\N	\N	\N	\N	S	245	\N	\N
262	Red	\N	\N	\N	\N	L	200	\N	\N
263	Red	\N	\N	\N	\N	M	200	\N	\N
264	Red	\N	\N	\N	\N	L	201	\N	\N
265	Red	\N	\N	\N	\N	M	201	\N	\N
394	Red	\N	\N	\N	\N	L	246	\N	\N
395	Red	\N	\N	\N	\N	M	246	\N	\N
396	Red	\N	\N	\N	\N	S	246	\N	\N
\.


--
-- Name: productvariation_productvariation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('productvariation_productvariation_id_seq', 519, true);


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

COPY shop (shop_id, shop_name, created_on, updated_on, details, image_path) FROM stdin;
1	Nayyar	2016-01-11 04:55:50.743	\N	\N	\N
2	Gulgs	2016-01-11 04:56:00.792	\N	\N	\N
3	Leather Heaven	2016-01-11 04:56:09.302	\N	\N	\N
13	Traditional Art Inspired	2016-03-07 20:40:09.334	\N	\N	\N
14	Couture Clothing	2016-03-07 20:41:33.537	\N	\N	\N
15	Embroidered Tops	2016-03-07 20:42:43.704	\N	\N	\N
24	Amjad	\N	\N	\N	\N
26	Maria Ali	\N	\N	Maria Ali is a boutique label synonymous with creating alluring feminine eastern pret, formal wedding wear, and bridal couture. The designer graduated from the prestigious Indus Valley School of Art and Architecture and immediately founded her own label having had a natural inclination and talent for designing clothes. Over a decade of experience, combined with her innate creative abilities has led to her to establish a respectable stature amongst her loyal clientŠle./nThe brand caters to her private clientŠle at her studio based in Lahore while also stocking her seasonal pret collections at LABELS Islamabad and PKDL Designers Lounge in the USA. Specializing in elegant contemporary designs defined by feminine embroideries and romantic embellishments, Maria Ali works within an array of style silhouettes from modern fusion wear comprising coats and jackets to polished kurtas and bridal lehngas./nMaria Ali pret is understated with composed thread work and subtle colors that vary from soft pastels to vibrant bright hues. Her wedding formals concentrate on intricate hand work and meticulous attention to detail.	\N
\.


--
-- Name: shop_shop_id_seq; Type: SEQUENCE SET; Schema: public; Owner: njcyxzlnmtiyvb
--

SELECT pg_catalog.setval('shop_shop_id_seq', 59, true);


--
-- Name: category_pkey; Type: CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (category_id);


--
-- Name: cchat_pkey; Type: CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb; Tablespace: 
--

ALTER TABLE ONLY cchat
    ADD CONSTRAINT cchat_pkey PRIMARY KEY (cchat_id);


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
-- Name: fk_d0y4r3ucois7mmwg3fwm8pgkp; Type: FK CONSTRAINT; Schema: public; Owner: njcyxzlnmtiyvb
--

ALTER TABLE ONLY cchat
    ADD CONSTRAINT fk_d0y4r3ucois7mmwg3fwm8pgkp FOREIGN KEY (customer_id) REFERENCES customer(customer_id);


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
-- PostgreSQL database dump complete
--

