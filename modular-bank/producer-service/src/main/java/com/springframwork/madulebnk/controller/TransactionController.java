package com.springframwork.madulebnk.controller;

import com.springframwork.madulebnk.domain.Transaction;
import com.springframwork.madulebnk.service.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/modular-bank/transaction/")
public class TransactionController {


    private RabbitMqSender rabbitMqSender;

    @Autowired
    public TransactionController(RabbitMqSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }

    @Value("${app.message}")
    private String message;

    @PostMapping(value = "create")
    public String createTransaction(@Valid @RequestBody Transaction transaction) {
        rabbitMqSender.sendTransaction(transaction);
        return message;
    }

    @RequestMapping(value = "/get/{transactionId}", method = RequestMethod.GET)
    @ResponseBody
    public String getTransaction(@PathVariable Integer transactionId) {
        rabbitMqSender.sendTransactionId(transactionId);
        return message;
    }

    @RequestMapping(value = "/get-by-accountId/{accountId}", method = RequestMethod.GET)
    @ResponseBody
    public String getTransactionListByAccountId(@PathVariable Integer accountId) {
        rabbitMqSender.sendAccountIdGetTransactionList(accountId);
        return message;
    }
}
