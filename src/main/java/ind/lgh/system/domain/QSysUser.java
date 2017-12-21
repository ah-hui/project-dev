package ind.lgh.system.domain;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSysUser is a Querydsl query type for SysUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysUser extends EntityPathBase<SysUser> {

    private static final long serialVersionUID = 523873373L;

    public static final QSysUser sysUser = new QSysUser("sysUser");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath accountExpired = createBoolean("accountExpired");

    //inherited
    public final NumberPath<Integer> createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateCreated = _super.dateCreated;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final StringPath email = createString("email");

    public final StringPath hashedPassword = createString("hashedPassword");

    //inherited
    public final NumberPath<Integer> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final StringPath loginName = createString("loginName");

    public final StringPath nickName = createString("nickName");

    public final StringPath phone = createString("phone");

    //inherited
    public final StringPath remarks = _super.remarks;

    public final ListPath<SimpleRole, QSimpleRole> roles = this.<SimpleRole, QSimpleRole>createList("roles", SimpleRole.class, QSimpleRole.class, PathInits.DIRECT2);

    public final StringPath token = createString("token");

    //inherited
    public final NumberPath<Integer> updatedBy = _super.updatedBy;

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QSysUser(String variable) {
        super(SysUser.class, forVariable(variable));
    }

    public QSysUser(Path<? extends SysUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysUser(PathMetadata metadata) {
        super(SysUser.class, metadata);
    }

}

