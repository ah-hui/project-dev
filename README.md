# Spring Boot Example

**[github地址](https://github.com/JesseyGone/project-dev)**

Spring Boot的使用案例

# T00: 
仅保存项目spring-boot初始化状态

# T01: 
初始化仓库后添加基础设施和实体（前端thymeleaf实现/前后端不分离）
- 添加Spring-data-jpa/lombok/druid
- 添加最简单的UserRoleMenu权限模型但尚未实现业务逻辑，SimpleRole系列
- 添加thymeleaf，编写user/role/menu的CRUD和前端页面
- 添加RESTful风格编程示例（TestRestController和单元测试）
- 添加基于JPA的多数据源支持（主分支代码不使用多数据源，如果要测试请打开相应配置的注解）
- 添加Swagger2为RESTful API组织文档
- 添加统一异常处理，并分开处理REST异常
- 添加定时任务支持，测试请在主程序启用定时任务
- 添加异步调用支持，测试请在主程序启用
- 切换日志记录为log4j，并在多环境配置下做不同配置
- 添加AOP实现的web请求记录
- 多反思一下为什么抓着一个分支写 O_o
- 整理user和role的多对多关系，使用注解实现。主要内容是JPA查询user时关联查询出对应的role
- 添加JavaMail发送邮件
- 添加QueryDSL做复杂多表关联查询

# T02:
整合Apache Shiro加入登录认证和权限管理
- 添加Shiro实现一套RBAC（Role-Based Access Control）
- 添加前端展示的ztree、bootstrap table