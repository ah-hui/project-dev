package mapper;

import model.DwDimensionMapping;

public interface DwDimensionMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DwDimensionMapping record);

    int insertSelective(DwDimensionMapping record);

    DwDimensionMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DwDimensionMapping record);

    int updateByPrimaryKey(DwDimensionMapping record);
}