package com.springframwork.madulebnk;

import com.springframwork.madulebnk.domain.Transaction;
import com.springframwork.madulebnk.enums.TransactionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest extends AbstractTest {

    @Test
    public void createTransaction() throws Exception {
        //String uri = "localhost:9091/modular-bank/account/create";
        String uri = "/modular-bank/transaction/create";
        Transaction transaction = new Transaction();
        transaction.setAccountId(1);
        transaction.setAmount(20L);
        transaction.setBalanceAfterTransaction(102L);
        transaction.setCurrency("EUR");
        transaction.setDescription("description Test");
        transaction.setDirectionOfTransaction(TransactionType.IN);

        String inputJson = super.mapToJson(transaction);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Transaction is created successfully");
    }

    @Test
    public void getTransaction() throws Exception {
        String uri = "/modular-bank/transaction/get/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Transaction transaction = super.mapFromJson(content, Transaction.class);
        assertTrue(!transaction.equals(null));
    }

    @Test
    public void getTransactionList() throws Exception {
        String uri = "/modular-bank/transaction/get-by-accountId/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Transaction[] transactions = super.mapFromJson(content, Transaction[].class);
        assertTrue(transactions.length > 0);
    }
}
