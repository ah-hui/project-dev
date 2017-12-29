
# 概要

- 扩展了datax-core，添加datax的job执行结果采集
- my文件夹下全部为自定义内容
- server下是我们部署datax为服务所需的辅助（定时任务定时读取队列执行）
- client下是客户端使用datax服务的示例（发送请求修改队列添加新元素，等待定时任务读取到并执行）
- 客户端主要通过实体类模拟reader和writer插件规定的json模板
- 实际使用时应该server和client分开部署，由client发送post请求修改服务端队列，从而服务端定时读取到执行
