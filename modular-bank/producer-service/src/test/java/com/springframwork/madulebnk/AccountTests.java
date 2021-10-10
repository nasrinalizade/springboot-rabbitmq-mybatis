package com.springframwork.madulebnk;

import com.springframwork.madulebnk.domain.Account;
import com.springframwork.madulebnk.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTests extends AbstractTest {

    @Test
    public void createAccount() throws Exception {
        //String uri = "localhost:9091/modular-bank/account/create";
        String uri = "/modular-bank/account/create";
        Customer customer = new Customer();
        customer.setCustomerId(27);
        customer.setCountry("TALIN");
        ArrayList<String> ar = new ArrayList<>();
        ar.add("EUR");
        customer.setCurrencyList(ar);

        String inputJson = super.mapToJson(customer);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Account is created successfully");
    }

    @Test
    public void getAccount() throws Exception {
        String uri = "/modular-bank/account/get/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Account account = super.mapFromJson(content, Account.class);
        assertTrue(!account.equals(null));
    }
}