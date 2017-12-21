package ind.lgh.system.domain;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSimpleRoleMenu is a Querydsl query type for SimpleRoleMenu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSimpleRoleMenu extends EntityPathBase<SimpleRoleMenu> {

    private static final long serialVersionUID = -1492524286L;

    public static final QSimpleRoleMenu simpleRoleMenu = new QSimpleRoleMenu("simpleRoleMenu");

    public final NumberPath<Integer> menuId = createNumber("menuId", Integer.class);

    public final NumberPath<Integer> roleId = createNumber("roleId", Integer.class);

    public QSimpleRoleMenu(String variable) {
        super(SimpleRoleMenu.class, forVariable(variable));
    }

    public QSimpleRoleMenu(Path<? extends SimpleRoleMenu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSimpleRoleMenu(PathMetadata metadata) {
        super(SimpleRoleMenu.class, metadata);
    }

}

