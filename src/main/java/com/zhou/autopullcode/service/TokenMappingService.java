package com.zhou.autopullcode.service;

import com.zhou.autopullcode.dao.TokenMappingDao;
import com.zhou.autopullcode.entity.TokenMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenMappingService {

    private final TokenMappingDao tokenMappingDao;

    @Autowired
    public TokenMappingService(TokenMappingDao tokenMappingDao) {
        this.tokenMappingDao = tokenMappingDao;
    }


    public String findTokenByAppname(String appName) {
        TokenMapping tokenMapping = tokenMappingDao.findTokenByAppname(appName).get(0);
        if (tokenMapping == null) {
            return "";
        }
        return tokenMapping.getToken();
    }
}
