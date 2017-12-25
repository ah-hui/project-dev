package ind.lgh.system.repository;

import java.util.List;

/**
 * 基础数据访问层 - 接口
 *
 * @author lgh
 * @since 2017-12-25
 */
public interface BaseRepository<T> {

    void batchInsert(List<T> list);

    void batchUpdate(List<T> list);

}
