package com.springframwork.madulebnk.service;

import com.springframwork.madulebnk.domain.Customer;
import com.springframwork.madulebnk.domain.Transaction;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender {
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${spring.rabbitmq.exchange}")
    private String exchanget;

    public void sendAccount(Customer customer) {
        rabbitTemplate.convertAndSend(exchange, routingkey, customer);

    }

    public void sendAccountId(Integer accountId) {
        rabbitTemplate.convertAndSend(exchange, routingkey, accountId);
    }

    public void sendTransaction(Transaction transaction) {
        rabbitTemplate.convertAndSend(exchanget, routingkey, transaction);
    }

    public void sendTransactionId(Integer transactionId) {
        rabbitTemplate.convertAndSend(exchange, routingkey, transactionId);
    }

    public void sendAccountIdGetTransactionList(Integer accountId) {
        rabbitTemplate.convertAndSend(exchange, routingkey, accountId);
    }

}
