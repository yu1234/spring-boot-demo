package com.yu.springbootdemo.config.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yu.springbootdemo.domain.bean.User;
import com.yu.springbootdemo.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  身份校验核心类;
 * @version v.0.1
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String userName = (String)token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        EntityWrapper<User> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq(User.ATTR_NAME,userName);
        User user = userService.selectOne(entityWrapper);
        if(user == null){
            return null;
        }
        return null;
    }
}
