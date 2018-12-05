package com.zhou.autopullcode.dao;

import com.zhou.autopullcode.entity.TokenMapping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenMapDao {
    List<TokenMapping> findTokenByAppname(@Param("appname") String appname);
}
