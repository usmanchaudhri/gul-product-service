--
-- Name: shop; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

create table EMAILSUBSCRIPTION (
    emailsubscription_id bigint not null,
    created_on timestamp,
    email varchar(255),
    updated_on timestamp,
    primary key (emailsubscription_id)
);
  
create sequence EMAILSUBSCRIPTION_EMAILSUBSCRIPTION_ID_SEQ 
	START WITH 0 
	INCREMENT BY 1
	NO MINVALUE
    NO MAXVALUE
    CACHE 1;
