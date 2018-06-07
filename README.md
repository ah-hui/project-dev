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
- 搞懂mybatis-generator的全部配置，无敌，牛逼
    1.配置中的plugin可以指定使用默认generator插件还是tk-mybatis提供的插件
    2.generatorConfig文件的配置将会影响产生的xml文件的内容（例如是构造器还是result描述的resultMap）
  