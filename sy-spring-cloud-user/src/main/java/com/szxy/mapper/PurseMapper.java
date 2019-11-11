package com.szxy.mapper;

import com.szxy.pojo.Purse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Auther:zwer
 * @Date:2019/11/8 21:06
 * @Description:com.szxy.mapper
 * @Version:1.0
 **/
public interface PurseMapper {

    @Select("select id,user_id userId,balance,recharge,withdrawals,state from purse where user_id = #{userId}")
    Purse findPurseByUserId(@Param("userId") Integer userId);

    @Insert("insert into purse(id,user_id,balance, recharge, withdrawals, state) values(default, #{userId}, 0.0, 0, 0, 0)")
    void addPurse(@Param("userId") Integer userId);

    @Update("update purse" +
            " set balance = #{balance}, recharge = #{recharge}, withdrawals = #{withdrawals}," +
            " state = #{state} where user_id = #{userId} ")
    void updatePurseByUserId(Purse purse);
}
