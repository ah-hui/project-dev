package ind.lgh.system.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 基础数据访问层
 * 标注@NoRepositoryBean，Spring Data Jpa在启动时就不会去实例化BaseRepository接口
 * 数据访问层可以选择继承BaseRepository，也可以选择继承以下：
 * Repository： 仅仅是一个标识，没有任何方法，方便Spring自动扫描识别
 * CrudRepository： 继承Repository，实现了一组CRUD相关的方法
 * PagingAndSortingRepository： 继承CrudRepository，实现了一组分页排序相关的方法
 * JpaRepository： 继承PagingAndSortingRepository，实现一组JPA规范相关的方法
 *
 * @author lgh
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
