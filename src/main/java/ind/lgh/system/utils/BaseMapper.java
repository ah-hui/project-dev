package ind.lgh.system.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * BaseMapper
 * 全部Mapper继承这个类
 * FIXME 注意：现在该mapper不能被扫描到，否则报错ClassCastException
 *
 * @author lgh
 * @since 2018-01-11
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
