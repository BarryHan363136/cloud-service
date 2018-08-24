package com.barry.cloud.platform.security.jwt.mapper;

import com.barry.cloud.platform.common.mapper.RepositoryMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoMapper extends RepositoryMapper<User> {

//    @Select("select account_id as accountId, account_name as accountName from account where account_id = #{accountId}")
//    User findById(@Param("accountId") Long accountId);
//
//    @Select("SELECT count(*) as totalRow FROM account ")
//    Integer gettotalRow();
//
//    @Select("SELECT * FROM ( SELECT a.*, ROWNUM RN FROM (SELECT * FROM account) a  WHERE ROWNUM <= #{endNum} )WHERE RN >= #{startNum} ")
//    List<User> findByPage(@Param("startNum") Integer startNum, @Param("endNum") Integer endNum);
//
//    User selectAccount(@Param("accountId") Long accountId);
//
//    Map<String, Object> selectAccount1(@Param("accountId") Long accountId);
//
//    /**
//     * 批量插入，Oralce需要设置useGeneratedKeys=false，不然报错
//     *  Oracle批量插入：  insert all into table(...) values(...) into table(...) values(...) select * from dual
//     *  Mysql批量插入：   insert into table(...) values(...),(...)
//     * @param accounts
//     * @return
//     */
//    @Insert("<script>" +
//            "insert all " +
//            "<foreach collection=\"list\" item=\"account\">" +
//            "into account(account_id, account_name, account_code) " +
//            "values(#{account.accountId}, #{account.accountName}, #{account.accountCode})" +
//            "</foreach> SELECT * FROM DUAL" +
//            "</script>")
//    @Options(useGeneratedKeys = false)
//    int insertAccounts(List<User> accounts);
//
//    /**
//     * 根据主键查询一个
//     * @param accountId
//     * @return
//     */
//    @Results(id = "accountResultTest", value = {
//            @Result(property = "accountId", column = "account_id", id = true),
//            @Result(property = "accountName", column = "account_name", id = true),
//            @Result(property = "accountCode", column = "account_code", id = true)
//    })
//    @Select("select account_id, account_name, account_code from account where account_id = #{accountId}")
//    User selectById(Long accountId);
//
//    /**
//     * 查询全部，引用上面的Results
//     * @return
//     */
//    @ResultMap("accountResultTest")
//    @Select("select * from spark_user")
//    List<User> selectAll();
    /**
     * PageHelper.startPage(apps.getPageNum(), apps.getPageSize());
     *     ArrayList<Apps> appsList = appsService.getApps(apps);
     *     PageInfo<Apps> appsPageInfo = new PageInfo<>(appsList);
     *     return JSON.toJSONString(appsPageInfo);
     * */

}