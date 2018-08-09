package com.barry.cloud.platform.datasource.base;

import java.util.List;
import java.util.Map;

/**
 * MyBatis Mapper基础数据操作接口，定义和规范基础的操作方法。
 * 
 * @author <a href="Tongshan.Han@partner.bmw.com">hants</a>
 */
public interface BaseMapper<T,PK> {
	
	/**
     * 删除指定的实体
     * 
     * @param pk 实体键
     */
	int deleteByPrimaryKey(PK pk);

    /**
     * 插入指定的实体
     *  
     * @param t 实体对象
     */
    int insert(T t);

    /**
	 * 通过编号查询实体
	 * 
	 * @param pk 实体键 
	 * @return
	 */
    T selectByPrimaryKey(PK pk);

    /**
	 * 更新指定的实体
	 * 
	 * @param t 实体对象
	 */
    int updateByPrimaryKey(T t);
    
    /**
	 * 查询结果集
	 * 
	 * @param Map<String, Object> map
	 */
    public List<T> findResults(Map<String, Object> map);
	
    /**
	 * 查询结果集总条数
	 * 
	 * @param Map<String, Object> map
	 */
	public Integer queryCount(Map<String, Object> map);

}
