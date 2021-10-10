package com.springframwork.madulebnk.mapper;

import com.springframwork.madulebnk.domain.Account;
import com.springframwork.madulebnk.domain.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountMapper {
    @Select("select * from account")
    List<Account> findAll();

    @Insert("insert into account(customerId,country) values(#{customerId},#{country}) ")
    void insert(Customer customer);

    @Select("select * from account Where accountId=#{accountId}")
    Account getById(Integer AccountId);
}
