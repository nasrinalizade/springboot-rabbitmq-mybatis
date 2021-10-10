package com.springframwork.madulebnk.mapper;

import com.springframwork.madulebnk.domain.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransactionMapper {
    @Insert("insert into transaction(accountId,amount,currency,directionOfTransaction,description,balanceAfterTransaction) values(#{accountId},#{amount},#{currency},#{directionOfTransaction},#{description},#{balanceAfterTransaction}) ")
    void insert(Transaction transaction);

    @Select("select * from transaction where transactionId=#{transactionId}")
    Transaction getById(int transactionId);

    @Select("select * from transaction where accountId=#{accountId}")
    List<Transaction> getByAccountId(Integer accountId);
}
