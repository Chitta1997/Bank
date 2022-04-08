DROP TABLE IF EXISTS CUSTOMERS;
DROP TABLE IF EXISTS ACCOUNTS;
DROP TABLE IF EXISTS TRANSACTIONS;
DROP TABLE IF EXISTS TRANSACTIONS_TYPES;


CREATE TABLE TRANSACTIONS_TYPES (
Transaction_Type_ID int NOT NULL,
Transaction_Type  VARCHAR(100),
PRIMARY KEY(Transaction_Type_ID)
);

CREATE TABLE CUSTOMERS (
    Customer_ID int NOT NULL,
    Customer_Name varchar(255) NOT NULL,
    Customer_Phone varchar(255),
    Customer_Email nvarchar(255),
	Date_Opened date,
	Date_Closed date,
    PRIMARY KEY (Customer_ID)
);
CREATE TABLE ACCOUNTS (
    Account_ID int NOT NULL,
    Customer_ID int NOT NULL,
    Date_Opened date,
	Date_Closed date,
	Minimum_Balance float,
	Current_Balance float,
	PRIMARY KEY (Account_ID),
    CONSTRAINT FK_ID_OF_CUSTOMER FOREIGN KEY (Customer_ID)
    REFERENCES CUSTOMERS(Customer_ID)
);
CREATE TABLE TRANSACTIONS (
    Transaction_ID int NOT NULL,
    Account_ID int NOT NULL,
    Transaction_Date date,
    Transaction_Type_ID int,
    Transaction_Amount float,
    Transaction_Note VARCHAR(200),
    Balance float,
    PRIMARY KEY (Transaction_ID),
    CONSTRAINT FK_ID_OF_ACCOUNTS FOREIGN KEY (Account_ID)
    REFERENCES ACCOUNTS(Account_ID),
    CONSTRAINT FK_ID_OF_TRANSACTIONS_TYPES FOREIGN KEY (Transaction_Type_ID)
        REFERENCES TRANSACTIONS_TYPES(Transaction_Type_ID)

    );




