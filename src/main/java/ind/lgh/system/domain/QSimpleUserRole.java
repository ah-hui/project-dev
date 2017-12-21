package ind.lgh.system.domain;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSimpleUserRole is a Querydsl query type for SimpleUserRole
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSimpleUserRole extends EntityPathBase<SimpleUserRole> {

    private static final long serialVersionUID = -1492252946L;

    public static final QSimpleUserRole simpleUserRole = new QSimpleUserRole("simpleUserRole");

    public final NumberPath<Integer> roleId = createNumber("roleId", Integer.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QSimpleUserRole(String variable) {
        super(SimpleUserRole.class, forVariable(variable));
    }

    public QSimpleUserRole(Path<? extends SimpleUserRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSimpleUserRole(PathMetadata metadata) {
        super(SimpleUserRole.class, metadata);
    }

}

