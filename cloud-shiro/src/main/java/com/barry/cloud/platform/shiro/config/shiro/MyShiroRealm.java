package com.barry.cloud.platform.shiro.config.shiro;

import com.barry.cloud.platform.common.parse.json.JSONMapper;
import com.barry.cloud.platform.shiro.entity.SysPermission;
import com.barry.cloud.platform.shiro.entity.SysRole;
import com.barry.cloud.platform.shiro.entity.UserInfo;
import com.barry.cloud.platform.shiro.service.SysPermissionService;
import com.barry.cloud.platform.shiro.service.SysRoleService;
import com.barry.cloud.platform.shiro.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.*;

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
    private SysPermissionService sysPermissionService;

    @Autowired
    private RedisSessionDAO redisSessionDAO;

    /**
     * 根据用户的权限信息做授权判断，这一步是以doGetAuthenticationInfo为基础的，
     * 只有在有用户信息后才能根据用户的角色和授权信息做判断是否给用户授权，因此这里的Roles和Permissions是用户的两个重点判断依据
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
        log.info("=============>加载权限设置:"+JSONMapper.writeObjectAsString(userInfo));
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userInfo.getUid());
        List<SysPermission> permissionList = sysPermissionService.findPermissionByRoleInfo(map);
        if (permissionList!=null && !permissionList.isEmpty()){
            for(SysPermission p : permissionList){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确
     * 获取用户的权限信息，这是为下一步的授权做判断，获取当前用户的角色和这些角色所拥有的权限信息
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

        // 当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession", userInfo);
        session.setAttribute("userSessionId", userInfo.getUid());


        return authenticationInfo;
    }


    /**
     * 根据userId 清除当前session存在的用户的权限缓存
     * @param userIds 已经修改了权限的userId
     */
    public void clearUserAuthByUserId(List<Integer> userIds){
        if(null == userIds || userIds.size() == 0)	return ;
        //获取所有session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session:sessions){
            //获取session登录信息。
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(null != obj && obj instanceof SimplePrincipalCollection){
                //强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
                //判断用户，匹配用户ID。
                obj = spc.getPrimaryPrincipal();
                if(null != obj && obj instanceof UserInfo){
                    UserInfo user = (UserInfo) obj;
                    System.out.println("user:"+user);
                    //比较用户ID，符合即加入集合
                    if(null != user && userIds.contains(user.getUid())){
                        list.add(spc);
                    }
                }
            }
        }
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm)securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection simplePrincipalCollection : list) {
            realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
        }
    }

}