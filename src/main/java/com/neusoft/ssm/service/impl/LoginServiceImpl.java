package com.neusoft.ssm.service.impl;

import com.neusoft.ssm.bean.User;
import com.neusoft.ssm.bean.UserExample;
import com.neusoft.ssm.dao.UserMapper;
import com.neusoft.ssm.service.LoginService;
import com.neusoft.ssm.util.HashSaltUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserMapper userMapper;

    /*
     * @Description 检验用户是否存在,hash+salt验证密码，不存在则返回id为-1的user
     * @Param [signUser]
     * @return com.neusoft.ssm.bean.User
     **/
    public User checkLogin(User signUser) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(signUser.getLoginName());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 1){
            User user = users.get(0);
            try {
                if(HashSaltUtil.validatePassword(signUser.getPassword(), user.getPassword()))
                    return user;
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        User user = new User();
        user.setId(-1);
        return user;
    }
}
