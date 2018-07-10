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

# T06:
自定义线程池
- 以T04分支为基础，初始化本分支
- 创建自定义线程池

# T07:
十大数据挖掘算法，搞起，[参考](https://www.cnblogs.com/en-heng/p/5719101.html)
- Apriori算法
- K-means算法，已整合k-means++，基于k-means实现二分k-means算法
- 整合Spark，Java连接Spark提交任务，计划使用Spark MLlib

- 跑偏了，加入了mqtt相关
- 又跑偏了，测试了下可重入读写锁ReentrantReadWriteLock
- 继续跑偏，编写OpenOffice转换工具类，使用三种方式实现并发下的转换实现
- Maven多环境配置的两种实现：
    ```第一种：Properties属性```
    ```使用-D指定：mvn clean package -Denv=dev```
    ```自动应用，且覆盖pom已有属性```
    ```第二种：Profiles配置文件```
    ```使用-P指定：mvn clean package -Ptest```
    ```配置文件通过@profileActive@访问并应用配置```