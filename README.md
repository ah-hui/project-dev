# Spring Boot Example

**[github地址](https://github.com/JesseyGone/project-dev)**

Spring Boot的使用案例

# T00: 
仅保存项目spring-boot初始化状态

# T04: 
REST接口
- 配置fastjson解析数据
- ORM换用mybatis，添加tk-mybatis和pagehelper
- 添加mybatis-generator
- 添加lombok/druid
- 切换日志记录为log4j，并在多环境配置下做不同配置
- 添加AOP实现的web请求记录
- [集成resteasy](https://github.com/JesseyGone/project-dev/blob/T04/src/main/java/ind/lgh/system/service/impl/SysUserServiceImpl.java)

# T05: 
整合图形数据库Neo4j
- 整合Neo4j实现节点创建和查询
- 模拟微博用户互粉、用户发布消息模型（实现两种实体两种关系）