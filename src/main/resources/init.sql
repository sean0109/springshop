CREATE TABLE SPRINGSHOP.DELIVERY (
                                     DELIVERY_ID NUMBER NOT NULL,
                                     STATUS VARCHAR2(10),
                                     CITY VARCHAR2(100),
                                     STREET VARCHAR2(200),
                                     ZIPCODE VARCHAR2(20),
                                     CONSTRAINT DELIVERY_PK PRIMARY KEY (DELIVERY_ID)
);

CREATE TABLE SPRINGSHOP.ITEM (
                                 ITEM_ID NUMBER NOT NULL,
                                 NAME VARCHAR2(200) NOT NULL,
                                 PRICE NUMBER(10,0),
                                 STOCK_QUANTITY NUMBER(10,0),
                                 DTYPE VARCHAR2(20),
                                 AUTHOR VARCHAR2(200),
                                 ISBN VARCHAR2(50),
                                 DIRECTOR VARCHAR2(200),
                                 ACTOR VARCHAR2(200),
                                 ARTIST VARCHAR2(200),
                                 ETC VARCHAR2(500),
                                 CONSTRAINT CK_ITEM_DTYPE CHECK (DTYPE IN ('BOOK', 'MOVIE', 'ALBUM')),
                                 CONSTRAINT CK_ITEM_PRICE CHECK (PRICE >= 0),
                                 CONSTRAINT CK_ITEM_STOCK CHECK (STOCK_QUANTITY >= 0),
                                 CONSTRAINT PK_ITEM PRIMARY KEY (ITEM_ID)
);


CREATE TABLE SPRINGSHOP."MEMBER" (
                                     MEMBER_ID NUMBER NOT NULL,
                                     NAME VARCHAR2(100),
                                     CITY VARCHAR2(100),
                                     STREET VARCHAR2(200),
                                     ZIPCODE VARCHAR2(20),
                                     CONSTRAINT MEMBER_PK PRIMARY KEY (MEMBER_ID)
);



CREATE TABLE SPRINGSHOP.ORDERS (
                                   ORDER_ID NUMBER NOT NULL,
                                   MEMBER_ID NUMBER,
                                   DELIVERY_ID NUMBER,
                                   ORDERDATE DATE,
                                   STATUS VARCHAR2(10),
                                   CONSTRAINT ORDERS_PK PRIMARY KEY (ORDER_ID),
                                   CONSTRAINT FK_ORDERS_DELIVERY FOREIGN KEY (DELIVERY_ID) REFERENCES SPRINGSHOP.DELIVERY(DELIVERY_ID),
                                   CONSTRAINT FK_ORDERS_MEMBER FOREIGN KEY (MEMBER_ID) REFERENCES SPRINGSHOP."MEMBER"(MEMBER_ID)
);


CREATE TABLE SPRINGSHOP.ORDER_ITEM (
                                       ORDER_ITEM_ID NUMBER NOT NULL,
                                       ORDER_ID NUMBER NOT NULL,
                                       ITEM_ID NUMBER NOT NULL,
                                       ORDERPRICE NUMBER,
                                       COUNT NUMBER,
                                       CONSTRAINT PK_ORDERITEM PRIMARY KEY (ORDER_ITEM_ID),
                                       CONSTRAINT FK_ORDERITEM_ITEM FOREIGN KEY (ITEM_ID) REFERENCES SPRINGSHOP.ITEM(ITEM_ID),
                                       CONSTRAINT FK_ORDERITEM_ORDERS FOREIGN KEY (ORDER_ID) REFERENCES SPRINGSHOP.ORDERS(ORDER_ID)
);

CREATE SEQUENCE SPRINGSHOP.ITEM_SEQ INCREMENT BY 1 MINVALUE 1 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SPRINGSHOP.MEMBER_SEQ INCREMENT BY 1 MINVALUE 1  NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SPRINGSHOP.DELIVERY_SEQ
    INCREMENT BY 1 MINVALUE 1 NOCYCLE NOCACHE;
CREATE SEQUENCE SPRINGSHOP.ORDERS_SEQ INCREMENT
    BY 1 MINVALUE 1 NOCYCLE NOCACHE;
CREATE SEQUENCE SPRINGSHOP.ORDER_ITEM_SEQ
    INCREMENT BY 1 MINVALUE 1 NOCYCLE NOCACHE;
