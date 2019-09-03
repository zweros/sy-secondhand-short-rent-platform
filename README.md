# 宿院二手短租平台
>主要目标是展示校园二手物品，供同学们在线浏览和查看二手物品信息。<br/>
>通过查看二手物品详情，获取到二手物品持有者的昵称和联系方式（前提是必须注册账号，登录成功后才可以查看），
>对有兴趣的二手物品添加关注。若没有找到自己满意的二手物品，可以通过搜索二手物品关键字，搜索对应的二手物品，或者
>发布求购信息。当然，除了浏览和查看二手物品信息，也可以自己发布二手物品信息，展示在网站上。
>若有其他同学看上你发布的二手物品，会主动根据你提供了联系方式联系你的。<br/>
>另外一个网站除了前台子系统，后台管理系统也是必不可少的。后台管理系统只提供给管理员登录，不允许其他用户登录。
>提供二手物品管理、用户管理等功能。

## 需求分析

### 用户模块
- 用户注册，普通手机号注册（模拟）
- 用户登录
- 查看及修改个人信息
- 发布求购二手物品信息
- 添加二手物品关注
- 发布二手物品
- 查看我发布的二手物品
- 分页查看用户信息
- 添加、删除、更新用户信息
 
### 评论模块
- 发布评论（必须是登录成功后才可以发布评论）

### 二手物品模块
- 首页显示最新发布二手物品
- 根据二手物品的目录查看满足条件的二手物品
- 查看二手物品详情
- 通过二手物品标题关键字搜索二手物品
- 分页查看物品信息  
- 添加、删除、更新二手物品

## 项目概况

### 项目名
sy-short-rent-platform

### 软件架构图
![](http://img.zwer.xyz/blog/20190902202519.png)

### 项目结构
sy-short-rent-platform<br/>
|---------->commons   存放 pojo 类和工具类<br/>
|---------->sy-spring-cloud-user 用户服务实现<br/>
|---------->sy-spring-cloud-user-service 用户服务接口<br/>
|---------->sy-spring-cloud-goods 物品服务实现<br/>
|---------->sy-spring-cloud-goods-service 物品服务接口<br/>
|---------->sy-spring-cloud-web-portal 前台子系统 web 端 <br/>
|---------->sy-spring-cloud-web-background-management 后台管理子系统 Web 端<br/>
|---------->sy-spring-cloud-zuul-gatewary 服务网关，管理服务路由，权限认证，异常处理<br/>
|---------->sy-spring-cloud-config 配置中心，使用 Gitee 作为配置文件的仓库<br/>



### 环境及技术选型

**基础环境：**  
- JDK 1.8<br/>
- 数据库：MySQL<br/>
- 操作系统os：Windows 10 家庭版<br/>
- IDE： IntelliJ IDEA <br/>

**所使用到的技术以下:** 
- Spring Boot 
- Spring Cloud 
- Spring Security 
- Spring Data Redis JPA 
- Mybatis 
- Thymeleaf 
- VSFTP
 
 
### 数据库设计
> 无外键设计，将外键关系通过 Java 实体类之间的关系体现
![](http://img.zwer.xyz/blog/20190902154013.png)	
	
### 网站部分截图
1. 网站首页展示
![](http://img.zwer.xyz/blog/20190902164256.png)

2. 二手物品详情展示
![](http://img.zwer.xyz/blog/20190902164402.png)
	
