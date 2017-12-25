package ind.lgh.system.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 基础数据访问层
 * 标注@NoRepositoryBean，Spring Data Jpa在启动时就不会去实例化BaseRepository接口
 * 数据访问层的实现类可以选择继承BaseRepository的实现类，同时数据访问层接口可以选择继承以下接口：
 * Repository： 仅仅是一个标识，没有任何方法，方便Spring自动扫描识别
 * CrudRepository： 继承Repository，实现了一组CRUD相关的方法
 * PagingAndSortingRepository： 继承CrudRepository，实现了一组分页排序相关的方法
 * JpaRepository： 继承PagingAndSortingRepository，实现一组JPA规范相关的方法
 * QueryDsl相关
 * <p>
 * 作用：
 * 1.暴露JPA EntityManager；
 * 2.实现一组简单的批量插入和更新
 *
 * @author lgh
 * @since 2017-12-25
 */
@NoRepositoryBean
public class BaseRepositoryImpl<T> {

    @PersistenceContext
    protected EntityManager em;

    /**
     * 批量插入.
     * 0.使用JPA EntityManager实现批量操作比循环调用service快的多，下面介绍em的全部api：
     * 1.persist()可以将实例转换为 managed(托管) 状态。在调用flush()或提交事物后，实例将会被插入到数据库中
     * 2.merge()的主要作用是将用户对一个detached状态实体的修改进行归档，归档后将产生一个新的managed状态对象。
     * 2.1.如果Entity是新创建的，则这个方法类似于persist();
     * 2.2.如果Entity已经存在的，则只作为更新操作.
     * 3.refresh()可以保证当前的实例与数据库中的实例的内容一致。
     * 4.remove()可以将实体转换为removed状态，并且在调用flush()或提交事物后删除数据库中的数据。
     * 5.flush()将PersistenceContext的信息同步到数据库中。
     * 5.1.当触发Flush这个动作的时候，所有的实体都将会被insert/update/remove到数据库中。数据库不会触发Commit的操作。
     * 6.clear()分离所有当前正在被管理的实体
     * 6.1.在处理大量实体的时候，如果你不把已经处理过的实体从EntityManager中分离出来，将会消耗你大量的内存。
     * 6.2.调用EntityManager的clear()方法后，所有正在被管理的实体将会从持久化内容中分离出来。
     * 6.3.注意，在事务没有提交前（事务默认在调用堆栈的最后提交，如：方法的返回），如果调用clear()方法，之前对实体所作的任何改变将会丢失，所以在调用clear()方法之前先调用flush()方法保存更改
     *
     * @param list
     */
    @Transactional(rollbackFor = Exception.class)
    public void batchInsert(List<T> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            em.persist(list.get(i));
            // 每10条数据执行一次，或者最后不足10条时执行
            if (i % 10 == 0 || i == (size - 1)) {
                em.flush();
                em.clear();
            }
        }
    }

    /**
     * 批量更新.
     *
     * @param list
     */
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdate(List<T> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            em.merge(list.get(i));
            // 每10条数据执行一次，或者最后不足10条时执行
            if (i % 10 == 0) {
                em.flush();
                em.clear();
            }
        }
    }
}
