package com.barry.cloud.platform.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface RepositoryMapper<T> extends Mapper<T>, MySqlMapper<T> {

}

