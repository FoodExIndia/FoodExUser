CREATE TABLE FE_LOGIN(
CLIENT_KEY VARCHAR(15) primary key,
USER_NAME_EMAIL VARCHAR(50) unique key,
USER_NAME_PHONE INTEGER(10) unique key,
CLIENT_PASSWORD VARCHAR(15) not null,
RECENT_OTP INTEGER(6),
INSERT_DATE DATE,
UPDATE_DATE DATE
)

CREATE TABLE FE_CLIENT(
CLIENT_KEY VARCHAR(15) primary key,
CLIENT_EMAIL VARCHAR(50) UNIQUE KEY,
CLIENT_MOBILE_NUM INTEGER(10) UNIQUE KEY,
CLIENT_FIRST_NAME VARCHAR(30) NOT NULL,
CLIENT_LAST_NAME VARCHAR(30),
CLIENT_ADDRESS_LINE_1 VARCHAR(100) NOT NULL,
CLIENT_ADDRESS_LINE_2 VARCHAR(100) NOT NULL,
CLIENT_LANDMARK VARCHAR(30),
CLIENT_CITY VARCHAR(30) NOT NULL,
CLIENT_AREA VARCHAR(30) NOT NULL,
CLIENT_STATE VARCHAR(20) NOT NULL,
CLIENT_ZIP INTEGER(6) NOT NULL,
DOB DATE,
CLIENT_GPS_LOCATION VARCHAR(30),
INSERT_DATE DATE,
UPDATE_DATE DATE
)

CREATE TABLE FE_DELIVERY_ADDRESS(
CLIENT_KEY varchar(15),
ADDRESS_KEY INTEGER(1),
DEFAULT_ADDRESS_FLAG INTEGER(1) NOT NULL,
CLIENT_ADDRESS_LINE_1 VARCHAR(100) NOT NULL,
CLIENT_ADDRESS_LINE_2 VARCHAR(100) NOT NULL,
CLIENT_LANDMARK VARCHAR(30) ,
CLIENT_CITY VARCHAR(30) NOT NULL,
CLIENT_AREA VARCHAR(30) NOT NULL,
CLIENT_STATE VARCHAR(20) NOT NULL,
CLIENT_ZIP INTEGER(6) NOT NULL,PRIMARY KEY(CLIENT_KEY,ADDRESS_KEY)
)

CREATE TABLE FE_AREA(
AREA_CODE VARCHAR(6) PRIMARY KEY,
AREA_NAME VARCHAR(30),
CITY_CODE VARCHAR(3),
CITY_NAME VARCHAR(30),
CUSTOMER_COUNT INTEGER(6),
COOK_COUNT INTEGER(5),
DELIVERY_BOYS_COUNT INTEGER(3),
AREA_MANAGER_NAME VARCHAR(50),
AREA_MANAGER_NO INTEGER(10),
INSERT_DATE DATE,
UPDATE_DATE DATE
)

CREATE TABLE FE_CONTACT_US(
CITY_CODE VARCHAR(3) PRIMARY KEY,
CITY_NAME VARCHAR(30),
EMAIL VARCHAR(50),
MOBILE_1 INTEGER(10),
MOBILE_2 INTEGER(10),
TOLL_FREE VARCHAR(16),
INSERT_DATE DATE,
UPDATE_DATE DATE
)

--CREATE TABLE REFERRAL(
--REFERRAL_CODE VARCHAR(28),
--REFFERER_ID INTEGER(10),
--USER_TYPE VARCHAR(15),
--REFERRAL_COUNT INTEGER(2),
--REFEREE_EMAIL VARCHAR(50),
--REFEREE_ID INTEGER(10),
--REFEREE_MOBILE_NO INTEGER(10),
--INSERT_DATE DATE,
--UPDATE_DATE DATE
--)

CREATE TABLE FE_NOTIFICATION(
ORDER_ID INTEGER(10) PRIMARY KEY,
ORDER_STATUS VARCHAR(1),
DESPATCH_STATUS VARCHAR(1),
DESPATCH_START_TIME TIMESTAMP,
DELIVERED_TIME TIMESTAMP,
INSERT_DATE DATE,
UPDATE_DATE DATE
)

CREATE TABLE FE_PLANS(
PLAN_NAME VARCHAR(20),
PLAN_KEY INTEGER(10) PRIMARY KEY,
PLAN_DURATION VARCHAR(20),
PLAN_DESC VARCHAR(50),
INSERT_DATE DATE,
UPDATE_DATE DATE
)

CREATE TABLE FE_MENU_LIST(
FOOD_KEY INTEGER(10) PRIMARY KEY,
FOOD_CUISINE_FLAG VARCHAR(30),
FOOD_ITEM VARCHAR(30),
FOOD_ITEM_COMMENT VARCHAR(100),
FOOD_TYPE_FLAG INTEGER(1),
FOOD_ITEM_MRP DECIMAL(4,2),
FOOD_SPECIAL_ITEM_FLAG VARCHAR(1),
INSERT_DATE DATE,
UPDATE_DATE DATE
)

CREATE TABLE FE_FOOD_COST(
FOOD_KEY INTEGER(10) PRIMARY KEY,
FOOD_ITEM VARCHAR(30),
FOOD_ITEM_MRP DECIMAL(4,2),
FOOD_COST DECIMAL(4,2),
FOODEX_SHARE DECIMAL(4,2),
INSERT_DATE DATE,
UPDATE_DATE DATE
)

CREATE TABLE FE_COOK_SPECIALITY(
COOK_KEY INTEGER(10),
FOOD_KEY INTEGER(10),
INSERT_DATE DATE,
UPDATE_DATE DATE,PRIMARY KEY(COOK_KEY,FOOD_KEY)
)

CREATE TABLE FE_ORDERS(
ORDER_ID INTEGER(10) PRIMARY KEY,
CLIENT_ID varchar(15) NOT NULL,
PLAN_KEY INTEGER(10) NOT NULL,
FOOD_ORDER_FROM_DATE DATE NOT NULL,
FOOD_ORDER_TO_DATE DATE NOT NULL,
TOTAL_BILL_AMT DECIMAL(8,2) NOT NULL,
BF_TIME TIMESTAMP,
LUNCH_TIME TIMESTAMP,
DINNER_TIME TIMESTAMP,
ORDER_STATUS INTEGER(1) NOT NULL,
ORPHANAGE_FLAG INTEGER(1) NOT NULL,
ORDER_CANCELLATION_FLAG INTEGER(1) NOT NULL,
INSERT_DATE DATE,
UPDATE_DATE DATE
)

CREATE TABLE FE_SUB_ORDERS(
ORDER_ID INTEGER(10) NOT NULL,
SUB_ORDER_ID INTEGER(10) PRIMARY KEY,
SUB_ORDER_DATE DATE NOT NULL,
COOK_KEY INTEGER(10) ,
DELIVERY_BOY_KEY INTEGER(10),
FOOD_KEY INTEGER(10) NOT NULL ,
FOOD_QUANTITY INTEGER(2) NOT NULL,
FOOD_ITEM_MRP DECIMAL(5,2) NOT NULL,
ADDRESS_KEY  VARCHAR(15) NOT NULL,
DELIVERY_TIME TIMESTAMP NOT NULL,
ORDER_STATUS INTEGER(1) NOT NULL,
ORPHANAGE_FLAG INTEGER(1) NOT NULL,
SUB_ORDER_CANCELLATION_FLAG INTEGER(1) NOT NULL,
INSERT_DATE DATE,
UPDATE_DATE DATE
)

CREATE TABLE FE_TRNXN(
TRNXN_ID INTEGER(10) PRIMARY KEY,
ORDER_ID INTEGER(10) NOT NULL,
CLIENT_KEY varchar(15) NOT NULL,
TRNXN_DATE_TIME DATE,
TRNXN_PAYMENT_TYPE INTEGER(1) NOT NULL,
TRNXN_STATUS INTEGER(1) NOT NULL,
TRNXN_AMOUNT DECIMAL(8,2) NOT NULL,
REFUND_STATUS INTEGER(1),
ERROR_TEXT VARCHAR(250),
INSERT_DATE DATE,
UPDATE_DATE DATE
)

CREATE TABLE FE_COOK(
COOK_MOBILE_NUM INTEGER(10) UNIQUE KEY NOT NULL,
COOK_KEY INTEGER(10) PRIMARY KEY,
COOK_PASSWORD VARCHAR(15) NOT NULL,
COOK_EMAIL VARCHAR(50),
COOK_FIRST_NAME VARCHAR(30) NOT NULL,
COOK_LAST_NAME VARCHAR(30),
COOK_ADDRESS_LINE_1 VARCHAR(100) NOT NULL,
COOK_ADDRESS_LINE_2 VARCHAR(100) NOT NULL,
COOK_LANDMARK VARCHAR(30),
COOK_CITY VARCHAR(30) NOT NULL,
COOK_STATE VARCHAR(20) NOT NULL,
COOK_ZIP INTEGER(6) NOT NULL,
COOK_AREA VARCHAR(30) NOT NULL,
DOB DATE,
GPS_LOCATION VARCHAR(30),
INSERT_DATE DATE,
UPDATE_DATE DATE
)

CREATE TABLE FE_DESPATCH_DETAILS(
SUB_ORDER_ID INTEGER(10) PRIMARY KEY,
ORDER_ID INTEGER(10) NOT NULL,
ORDER_STATUS INTEGER(1) NOT NULL,
CLIENT_KEY varchar(15) NOT NULL,
COOK_KEY INTEGER(10) NOT NULL,
FOOD_KEY INTEGER(10) NOT NULL,
DESPATCH_STATUS INTEGER(1) NOT NULL,
DESPATCH_DATE DATE NOT NULL,
DESPATCH_START_TIME TIMESTAMP,
DELIVERED_TIME TIMESTAMP,
INSERT_DATE DATE,
UPDATE_DATE DATE
)

CREATE TABLE FE_FEEDBACK(
CLIENT_KEY varchar(15),
ORDER_ID INTEGER(10),
FEEDBACK_FOR INTEGER(1),
FEEDBACK_COMMENTS VARCHAR(250),
INSERT_DATE DATE,
UPDATE_DATE DATE
)

CREATE TABLE FE_COMPLAINTS(
CLIENT_KEY varchar(15) NOT NULL,
CLIENT_COMPLAINTS VARCHAR(250) NOT NULL,
FE_RESOLUTION VARCHAR(250),
INSERT_DATE DATE,
UPDATE_DATE DATE
)

--CREATE TABLE FE_WALLET(
--CLIENT_KEY INTEGER(10),
--CURRENT_AMOUT DECIMAL(8,2),
--TRANSACTION_ID INTEGER(10),
--TRANSACTION_AMOUNT DECIMAL(8,2)
--TRANSACTION_DATE DATE,
--INSERT_DATE DATE,
--UPDATE_DATE DATE
--)


--CREATE TABLE FE_DELIVERY_BOYS(
--DELIVERY_BOY_KEY INTEGER(10)
--)

CREATE TABLE FE_PROMO(
PROMO_CODE VARCHAR(6) PRIMARY KEY,
PROMO_DESC VARCHAR(100) NOT NULL,
PROMO_VALID_FROM DATE NOT NULL,
PROMO_VALID_TO DATE NOT NULL,
PROMO_STATUS INTEGER(1) NOT NULL,
PROMO_DISCOUNT_PERCENT INTEGER(2),
PROMO_MAX_DISCOUNT_INR INTEGER(2),
INSERT_DATE DATE,
UPDATE_DATE DATE
)