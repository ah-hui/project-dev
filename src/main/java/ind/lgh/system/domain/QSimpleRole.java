package ind.lgh.system.domain;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSimpleRole is a Querydsl query type for SimpleRole
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSimpleRole extends EntityPathBase<SimpleRole> {

    private static final long serialVersionUID = -1925410045L;

    public static final QSimpleRole simpleRole = new QSimpleRole("simpleRole");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath code = createString("code");

    //inherited
    public final NumberPath<Integer> createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateCreated = _super.dateCreated;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Integer> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final StringPath name = createString("name");

    //inherited
    public final StringPath remarks = _super.remarks;

    //inherited
    public final NumberPath<Integer> updatedBy = _super.updatedBy;

    public final BooleanPath valid = createBoolean("valid");

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QSimpleRole(String variable) {
        super(SimpleRole.class, forVariable(variable));
    }

    public QSimpleRole(Path<? extends SimpleRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSimpleRole(PathMetadata metadata) {
        super(SimpleRole.class, metadata);
    }

}

