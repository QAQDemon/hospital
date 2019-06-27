package com.neusoft.ssm.service.impl;

import com.neusoft.ssm.bean.User;
import com.neusoft.ssm.bean.UserExample;
import com.neusoft.ssm.dao.UserMapper;
import com.neusoft.ssm.service.LoginService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserMapper userMapper;

    /*
     * @Description 检验用户是否存在，不存在则返回id为-1的user//TODO 密码解密？
     * @Param [signUser]
     * @return com.neusoft.ssm.bean.User
     **/
    public User checkLogin(User signUser){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(signUser.getLoginName());
        criteria.andPasswordEqualTo(signUser.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()==1)
            return users.get(0);
        else {
            User user = new User();
            user.setId(-1);
            return user;
        }
    }

}
