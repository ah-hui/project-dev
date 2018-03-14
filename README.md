# Spring Boot Example

---

**[github地址](https://github.com/JesseyGone/project-dev)**

Spring Boot的使用案例

## T00: 
仅保存项目spring-boot初始化状态

## T01: 
初始化仓库后添加基本基础设施和实体并完成user的CRUD（thymeleaf实现/前后端不分离）
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

## T03: 
整合datax源码做ETL
- 整合`datax`源码，添加常用reader和writer插件
- 扩展功能，实现服务端和客户端（同在本机）
- 整理mysql一套reader和writer和job模板对应的实体对象
```
{
  "job": {
    "content": [
      {
        "reader": {
          "name": "",
          "parameter": {}
        },
        "writer": {
          "name": "",
          "parameter": {}
        }
      }
    ],
    "setting": {
      "speed": {},
      "errorLimit": {}
    }
  }
}
```
- 实现服务端和客户端一组服务，调试成功，使用datax从db1到db2抽取转换保存
- 添加postgresql的reader和hdfs的writer