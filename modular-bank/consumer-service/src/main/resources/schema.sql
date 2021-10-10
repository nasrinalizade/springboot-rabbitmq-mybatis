CREATE TABLE account
(
    accountId SERIAL PRIMARY KEY,
    customerId bigint NOT NULL,
    country varchar(50)
);
CREATE TABLE balance
(
    balanceId SERIAL PRIMARY KEY,
    currency varchar(100) NOT NULL,
    availableBalance bigint NOT NULL,
    accountId int NOT  NULL
);
CREATE TABLE transaction
(
    transactionId SERIAL PRIMARY KEY,
    accountId int NOT NULL,
    amount bigint NOT NULL,
    currency varchar(100) NOT NULL,
    directionOfTransaction  varchar(5) NOT NULL,
    description varchar(100) NOT NULL,
    balanceAfterTransaction bigint NOT NULL
);


