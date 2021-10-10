package com.springframwork.madulebnk.service;

import com.springframwork.madulebnk.domain.*;
import com.springframwork.madulebnk.enums.TransactionType;
import com.springframwork.madulebnk.mapper.AccountMapper;
import com.springframwork.madulebnk.mapper.BalanceMapper;
import com.springframwork.madulebnk.mapper.TransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class RabbitMqReceiver implements RabbitListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);
    private AccountMapper accountMapper;
    private BalanceMapper balanceMapper;
    private TransactionMapper transactionMapper;

    public RabbitMqReceiver(AccountMapper accountMapper, BalanceMapper balanceMapper, TransactionMapper transactionMapper) {
        this.accountMapper = accountMapper;
        this.balanceMapper = balanceMapper;
        this.transactionMapper = transactionMapper;
    }

    @Value("${app.message2}")
    private String invalidCurrency;
    @Value("${app.message3}")
    private String negativeAvailableBalance;
    @Value("${app.message5}")
    private String accountIdIsMissing;
    @Value("${app.message6}")
    private String invalidDirection;

    private final List<String> commPreferences = Arrays.asList("EUR", "SEK", "GBP", "USD");


    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    @Transactional
    public void receivedCreateAccountMessage(Customer customer) throws Exception {

        logger.info("Customer Details Received is.. " + customer);
        try {
            if (!commPreferences.containsAll(customer.getCurrencyList())) {
                ErrorMsg errorMsg = new ErrorMsg(2, invalidCurrency);
                customer.setErrorMsg(errorMsg);
                logger.error("Error:" + errorMsg + "Customer:" + customer);
                throw new Exception("Error:" + errorMsg + "Customer:" + customer);
            }
            accountMapper.insert(customer);
            List<Account> accountList = accountMapper.findAll();
            for (String currency : customer.getCurrencyList()) {
                Balance balance = new Balance();
                balance.setCurrency(currency);
                // by default AvailableBalance Set 10000000
                balance.setAvailableBalance(10000000);
                balance.setAccountId(accountList.get(accountList.size() - 1).getAccountId());
                balanceMapper.insert(balance);
            }
        } catch (Exception ex) {
            logger.error("CreateAccount Exception .. " + ex);
        }

    }

  @RabbitListener(queues = "${spring.rabbitmq.queue}")
  public void receivedGetByAccountIdMessage(Integer accountId) {
      logger.info("receivedGetByAccountIdMessage AccountId Received is.. " + accountId);
      try {
          Account account = accountMapper.getById(accountId);
          List<Balance> balanceList = balanceMapper.getByAccountId(accountId);
          account.setBalanceList(balanceList);
      }
      catch (Exception ex){
          logger.error("GetAccountByAccountId Exception .. " + ex);
      }
  }
   /* @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedGetByTransactionIdMessage(Integer transactionId) {

        logger.info("receivedGetByTransactionIdMessage transactionId Received is.. " + transactionId);
        try {
            Transaction transaction = transactionMapper.getById(transactionId);
        }
        catch (Exception ex){
            logger.error("GetTransactionByTransactionId Exception .. " + ex);
        }
    }*/

    /*@RabbitListener(queues = "${spring.rabbitmq.queue}")
    @Transactional
    public void receivedCreateTransactionMessage(Transaction transaction) throws Exception {

        logger.info("receivedCreateTransactionMessage Transaction Details Received is: " + transaction);
        try {
            if (!commPreferences.contains(transaction.getCurrency())) {
                ErrorMsg errorMsg=new ErrorMsg(2, invalidCurrency);
                logger.error("Error:" + errorMsg);
                throw new Exception("Error:" + errorMsg);
            }
            if (!(transaction.getDirectionOfTransaction().equals(TransactionType.IN) || transaction.getDirectionOfTransaction().equals(TransactionType.OUT))) {
                ErrorMsg errorMsg=new ErrorMsg(6, invalidDirection);
                transaction.setErrorMsg(errorMsg);
                logger.error("Error:" + errorMsg);
                throw new Exception("Error:" + errorMsg);
            }
            Account account = accountMapper.getById(transaction.getAccountId());
            List<Balance> balanceList = balanceMapper.getByAccountId(transaction.getAccountId());
            Balance balance = balanceList.stream().filter(blnc -> blnc.getCurrency().equals(transaction.getCurrency())).findFirst().orElse(null);
            if (transaction.getDirectionOfTransaction().equals(TransactionType.OUT) && balance.getAvailableBalance() - transaction.getAmount() < 0) {
                ErrorMsg errorMsg=new ErrorMsg(3, negativeAvailableBalance);
                transaction.setErrorMsg(errorMsg);
                logger.error("Error:" + transaction);
                throw new Exception("Error:" + errorMsg);
            }
            if (account.equals(null)) {
                ErrorMsg errorMsg=new ErrorMsg(5, accountIdIsMissing);
                transaction.setErrorMsg(errorMsg);
                logger.error("Error:" + errorMsg);
                throw new Exception("Error:" + errorMsg);
            }
            if (transaction.getDirectionOfTransaction().equals(TransactionType.OUT)) {
                transaction.setBalanceAfterTransaction(balance.getAvailableBalance() - transaction.getAmount());
                balance.setAvailableBalance(balance.getAvailableBalance() - transaction.getAmount());
            }
            else {
                transaction.setBalanceAfterTransaction(balance.getAvailableBalance() + transaction.getAmount());
                balance.setAvailableBalance(balance.getAvailableBalance() + transaction.getAmount());
            }
            transactionMapper.insert(transaction);
            balanceMapper.update(balance);
        }
        catch (Exception ex){
            logger.error("CreateTransaction Exception .. " + ex);
        }
    }*/

   /* @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedGeTransactionListtByAccountIdMessage(Integer accountId) throws Exception {

        logger.info("receivedGeTransactionListtByAccountIdMessage accountId Received is.. " + accountId);
        try {
            Account account = accountMapper.getById(accountId);
            if (account.equals(null)) {
                account.setErrorMsg(new ErrorMsg(5, accountIdIsMissing));
                logger.error("Error:" + account);
                throw new Exception("Error:" + account);
            }
            List<Transaction> transactions = transactionMapper.getByAccountId(accountId);
            logger.info("get Transaction List By Account Id Output.. " + transactions);
        }
        catch (Exception ex){
            logger.error("GetTransactionListByAccountId Exception .. " + ex);
        }
    }*/

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
