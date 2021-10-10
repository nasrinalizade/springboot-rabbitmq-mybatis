# springboot-rabbitmq-mybatis
Using SpringBoot , MyBatis , Rabbitmq and Junit 

#	Technologies
Java 11
SpringBoot
MyBatis
Maven
RabbitMQ
Junit

#	Build and run applications
1.	getting dependency by Maven


2.	In Application.ym filel define configuration for :

a.	Port of Service
b.	Rabbitmq Setting
c.	Messages

3.	In schema.sq file define table and column that generate in postgresql after running the project. 
4.	In application.properties file define database Connection Setting
5.	run Producer and Consumer Services

#Example
RestUrl:localhost:9091/modular-bank/account/create
Method:Post

```json
{ 
    "customerId":31,
    "country": 222,
    "currencyList":["EUR"] 
}
```

RestUrl:localhost:9091/modular-bank/transaction/create
Method:Post

```json
{ 
    "accountId": "1",
    "amount": 20,
    "currency":"EUR",
    "directionOfTransaction":"0",
    "description":"WITHDRAW", 
    "balanceAfterTransaction":"" 
}
```