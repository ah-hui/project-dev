package ind.lgh.system.config.shiro;

import ind.lgh.system.domain.SimplePermission;
import ind.lgh.system.domain.SimpleRole;
import ind.lgh.system.domain.SysUser;
import ind.lgh.system.service.SysUserService;
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

/**
 * Realm提供认证和授权
 *
 * @author lgh
 * @since 2017-12-21
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private SysUserService userService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置--ShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        for (SimpleRole role : user.getRoles()) {
            authorizationInfo.addRole(role.getCode());
            for (SimplePermission permission : role.getPermissions()) {
                authorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("认证登录--ShiroRealm.doGetAuthenticationInfo()");
        // 获取用户的输入的账号.
        String loginName = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        // 通过loginName从数据库中查找 User对象，如果找到，没找到.
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUser user = userService.findByLoginName(loginName);
        System.out.println("SysUser--" + user);
        if (user == null) {
            return null;
        }
        // 密码校验交由Shiro的SimpleAuthenticationInfo自动验证处理
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                /* principal */
                user,
                /* hashedCredentials */
                user.getHashedPassword(),
                /* 加盐 */
//                ByteSource.Util.bytes(user.getLoginName() + user.getPhone()),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                /* realmName */
                getName());
        return authenticationInfo;
    }
}
