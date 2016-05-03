--
-- Name: shop; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

create table ATTRIBUTE_DEFINITION (
    attribute_definition_id bigint not null,
    attribute_name varchar(255) not null,
    created_on timestamp,
    active_flag boolean not null,
    updated_on timestamp,
    product_id bigint not null,
    primary key (attribute_definition_id)
);

create table ATTRIBUTE_VALUE (
    attribute_value_id bigint not null,
    attr_value varchar(255) not null,
    created_on timestamp,
    image_path varchar(255) not null,
    active_flag boolean not null,
    updated_on timestamp,
    attribute_definition_id bigint not null,
    primary key (attribute_value_id)
);
    
create sequence ATTRIBUTEDEFINITION_ATTRIBUTEDEFINITION_ID_SEQ 
	START WITH 1 
	INCREMENT BY 1
	NO MINVALUE
    NO MAXVALUE
    CACHE 1;

create sequence ATTRIBUTEVALUE_ATTRIBUTEVALUE_ID_SEQ 
	START WITH 1 
	INCREMENT BY 1
	NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--alter table ATTRIBUTE_DEFINITION 
--	add constraint UK_jenbcltkas5rcg8vklricg69a  
--	unique (attribute_name);

--alter table ATTRIBUTE_VALUE 
--	add constraint UK_oc3rerwfrj1et7os25il1u03k  
--	unique (attr_value);
--	
--alter table ATTRIBUTE_DEFINITION 
--	add constraint FK_2wud3bqumh2btnv2qg7tv0h1y 
--	foreign key (product_id) 
--	references PRODUCT;
--	
--alter table ATTRIBUTE_VALUE 
--    add constraint FK_2udennwwk0gusv1256p6djvoh 
--    foreign key (attribute_definition_id) 
--    references ATTRIBUTE_DEFINITION;	
	
	
	