package ind.lgh.system.domain.compositepk;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * SimpleUserRole的复合主键
 *
 * 复合主键类必须满足：
 * 1.实现Serializable接口
 * 2.有默认的public无参数的构造方法
 * 3.重写equals和hashCode方法
 *
 * @author lgh
 * @since 2017-12-21
 */
@Getter
@Setter
public class UserRoleCompositePK implements Serializable {

    private Integer userId;
    private Integer roleId;

    public UserRoleCompositePK() {
    }

    public UserRoleCompositePK(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int result = 17;
        /** why 31 ?
         * 1.为了让hashcode唯一，一定使用素数做系数
         * 2.31*i == (i<<5)-1，从效率?占用内存?比较好?，形成惯例，很多虚拟机都有做相关优化
         * 3.所以常用31做系数运算hashcode
         */
        // Integer的hashCode()方法返回本身
        result = 31 * result + ((userId == null) ? 0 : userId.hashCode());
        result = 31 * result + ((roleId == null) ? 0 : roleId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserRoleCompositePK other = (UserRoleCompositePK) obj;
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }
        if (roleId == null) {
            if (other.roleId != null) {
                return false;
            }
        } else if (!roleId.equals(other.roleId)) {
            return false;
        }
        return true;
    }

}
