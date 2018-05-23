package mapper;

import model.DwMetricMapping;

public interface DwMetricMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DwMetricMapping record);

    int insertSelective(DwMetricMapping record);

    DwMetricMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DwMetricMapping record);

    int updateByPrimaryKey(DwMetricMapping record);
}