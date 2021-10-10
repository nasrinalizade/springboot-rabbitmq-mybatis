package com.springframwork.madulebnk.controller;

import com.springframwork.madulebnk.domain.Customer;
import com.springframwork.madulebnk.service.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/modular-bank/account/")
public class AccountController {


    private RabbitMqSender rabbitMqSender;

    @Autowired
    public AccountController(RabbitMqSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }

    @Value("${app.message}")
    private String message;

    public AccountController() {

    }

    @PostMapping(value = "create")
    public String createAccount(@Valid @RequestBody Customer customer) {
        rabbitMqSender.sendAccount(customer);
        return message;
    }

    @GetMapping(value = "/get/{accountId}")
    @ResponseBody
    public String getAccount(@PathVariable Integer accountId) {
        rabbitMqSender.sendAccountId(accountId);
        return message;
    }
}
