package com.barry.cloud.platform.shiro.config.shiro;

import com.barry.cloud.platform.shiro.entity.SysPermission;
import com.barry.cloud.platform.shiro.entity.SysRole;
import com.barry.cloud.platform.shiro.entity.UserInfo;
import com.barry.cloud.platform.shiro.service.SysRoleService;
import com.barry.cloud.platform.shiro.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/16 17:42
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private SysRoleService sysRoleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
        List<SysRole> roleList = sysRoleService.findRolesByUserName(null);
        if (roleList!=null && !roleList.isEmpty()){
            for(SysRole role : roleList){
                authorizationInfo.addRole(role.getRole());
                for(SysPermission p : role.getPermissions()){
                    authorizationInfo.addStringPermission(p.getPermission());
                }
            }
        }
        return authorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        log.info("MyShiroRealm.doGetAuthenticationInfo()");
        /**
         * 获取用户的输入的账号.
         * */
        String username = (String)token.getPrincipal();
        log.info("用户身份认证,username={},Credentials={}", username, token.getCredentials());
        /**
         * 通过username从数据库中查找 User对象，如果找到，没找到.
         * 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
         */
        UserInfo userInfo = userInfoService.findByUsername(username);
        log.info("从数据库获取到的用户信息为userInfo:"+userInfo);
        if(userInfo == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

}