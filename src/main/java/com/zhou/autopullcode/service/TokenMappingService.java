package com.zhou.autopullcode.service;

import com.zhou.autopullcode.dao.TokenDao;
import com.zhou.autopullcode.entity.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenDao tokenDao;

    @Autowired
    public TokenService(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }


    public String findTokenByAppname(String appName) {
        Mapping mapping = tokenDao.findTokenByAppname(appName).get(0);
        if (mapping == null) {
            return "";
        }
        return mapping.getToken();
    }
}
