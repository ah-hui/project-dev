package ind.lgh.system.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ind.lgh.system.domain.QSimpleMenu;
import ind.lgh.system.domain.QSimpleRole;
import ind.lgh.system.domain.QSimpleRoleMenu;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * spring boot自动扫描自动实现SimpleRoleRepository（同目录且后缀是Impl）
 *
 * @author lgh
 * @since 2017-12-21
 */
public class SimpleRoleRepositoryImpl extends BaseRepository {

    public List<Tuple> findRoleAndMenu(Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<Tuple> jpaQuery = queryFactory.select(QSimpleRole.simpleRole, QSimpleMenu.simpleMenu)
                /* !!!注意此处特别蛋疼!!! from的表要包含全部下方要join的表否则报错：path expected for join! */
                .from(QSimpleRole.simpleRole, QSimpleRoleMenu.simpleRoleMenu, QSimpleMenu.simpleMenu)
                .leftJoin(QSimpleRoleMenu.simpleRoleMenu)
                .on(QSimpleRole.simpleRole.id.intValue().eq(QSimpleRoleMenu.simpleRoleMenu.roleId.intValue()))
                .where(QSimpleRoleMenu.simpleRoleMenu.menuId.intValue().eq(QSimpleMenu.simpleMenu.id.intValue()));
        jpaQuery.where(predicate);
        return jpaQuery.fetch();
    }

    public QueryResults<Tuple> findRoleAndMenuByPage(Predicate predicate, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<Tuple> jpaQuery = queryFactory.select(QSimpleRole.simpleRole, QSimpleMenu.simpleMenu)
                .from(QSimpleRole.simpleRole, QSimpleRoleMenu.simpleRoleMenu, QSimpleMenu.simpleMenu)
                .leftJoin(QSimpleRoleMenu.simpleRoleMenu)
                .on(QSimpleRole.simpleRole.id.intValue().eq(QSimpleRoleMenu.simpleRoleMenu.roleId.intValue()))
                .where(QSimpleRoleMenu.simpleRoleMenu.menuId.intValue().eq(QSimpleMenu.simpleMenu.id.intValue()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        jpaQuery.where(predicate);
        return jpaQuery.fetchResults();
    }
}
