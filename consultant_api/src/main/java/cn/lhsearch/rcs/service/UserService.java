package cn.lhsearch.rcs.service;

import org.springframework.stereotype.Service;

import cn.lhsearch.rcs.entity.User;

@Service
public class UserService {
    public User login(String code)
    {
        User user = new User();
        user.setWeChatOpenId(code);
        return user;
    }
}
