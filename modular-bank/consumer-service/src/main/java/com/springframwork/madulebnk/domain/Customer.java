package com.springframwork.madulebnk.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Customer.class)
public class Customer extends BaseObject {
    @NotNull(message = "customerId requires valid value")
    @Min(value = 1, message = "customerId must be greater than zero")
    private long customerId;
    private String country;
    private List<String> currencyList;

    public Customer(long customerId, String country, List<String> currencyList) {
        this.customerId = customerId;
        this.country = country;
        this.currencyList = currencyList;
    }

    public Customer() {

    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<String> currencyList) {
        this.currencyList = currencyList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", country=" + country +
                ", currencyList=" + currencyList +
                '}';
    }
}
