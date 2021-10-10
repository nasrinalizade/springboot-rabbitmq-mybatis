package com.springframwork.madulebnk.mapper;

import com.springframwork.madulebnk.domain.Balance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BalanceMapper {

    @Insert("insert into balance(availableBalance,currency,accountId) values(#{availableBalance},#{currency},#{accountId})")
    void insert(Balance balance);

    @Select("select * from balance Where accountId=#{accountId}")
    List<Balance> getByAccountId(Integer AccountId);

    @Update("update balance set availableBalance=#{availableBalance} where accountId=#{accountId} and currency=#{currency}")
    void update(Balance balance);
}
