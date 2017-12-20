package ind.lgh.system.domain.compositepk;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * SimpleRoleMenu的复合主键
 *
 * 复合主键类必须满足：
 * 1.实现Serializable接口
 * 2.有默认的public无参数的构造方法
 * 3.重写equals和hashCode方法
 *
 * @author lgh
 */
@Getter
@Setter
public class RoleMenuCompositePK implements Serializable {

    private Integer roleId;
    private Integer menuId;

    public RoleMenuCompositePK() {
    }

    public RoleMenuCompositePK(Integer roleId, Integer menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
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
        result = 31 * result + ((roleId == null) ? 0 : roleId.hashCode());
        result = 31 * result + ((menuId == null) ? 0 : menuId.hashCode());
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
        final RoleMenuCompositePK other = (RoleMenuCompositePK) obj;
        if (roleId == null) {
            if (other.roleId != null) {
                return false;
            }
        } else if (!roleId.equals(other.roleId)) {
            return false;
        }
        if (menuId == null) {
            if (other.menuId != null) {
                return false;
            }
        } else if (!menuId.equals(other.menuId)) {
            return false;
        }
        return true;
    }

}