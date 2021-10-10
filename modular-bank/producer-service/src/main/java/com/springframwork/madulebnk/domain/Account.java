package com.springframwork.madulebnk.domain;

import java.util.List;

public class Account extends BaseObject {
    private Integer accountId;
    private Long customerId;
    private List<Balance> balanceList;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<Balance> getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(List<Balance> balanceList) {
        this.balanceList = balanceList;
    }
}
