package ind.lgh.system.domain;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSimpleMenu is a Querydsl query type for SimpleMenu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSimpleMenu extends EntityPathBase<SimpleMenu> {

    private static final long serialVersionUID = -1925568532L;

    public static final QSimpleMenu simpleMenu = new QSimpleMenu("simpleMenu");

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

    public final StringPath resource = createString("resource");

    //inherited
    public final NumberPath<Integer> updatedBy = _super.updatedBy;

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QSimpleMenu(String variable) {
        super(SimpleMenu.class, forVariable(variable));
    }

    public QSimpleMenu(Path<? extends SimpleMenu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSimpleMenu(PathMetadata metadata) {
        super(SimpleMenu.class, metadata);
    }

}

