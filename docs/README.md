<h1 align="center"><a href="https://github.com/iscolt" target="_blank">校织网</a></h1>

> 基于微服务的 SaaS 多租户校园管理平台

<p align="center">
<a href="#"><img alt="JDK" src="https://img.shields.io/badge/JDK-1.8-yellow.svg?style=flat-square"/></a>
<a href="#"><img alt="" src="https://img.shields.io/github/stars/Miners-ICU/micm?style=flat-square"/></a>
<a href="https://github.com/Miners-ICU/micm/commits"><img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/Miners-ICU/micm.svg?style=flat-square"></a>
<a href="/"><img alt="" src="https://img.shields.io/github/license/Miners-ICU/micm.svg?style=flat-square)(https://github.com/halo-dev/halo/blob/master/LICENSE"></a></p>


---

## 简介

**MICM** [Micro-Intelligent Campus Management], 意为微智能校园管理, 又叫校织网. 

致力于打造统一的, 智能化的校园管理平台.

>  [背景](/docs/background.md) | [官网]() | [社区](/) 

## 技术选型

### 开发环境

- 操作系统：Windows 10 
- 开发工具：Intellij IDEA
- 数据库：MySQL 8.0.+
- Java SDK：Oracle JDK 1.8

### 部署环境

- 操作系统：Linux Ubuntu Server 18.04 X64
- 虚拟化技术：VMware + Docker

### 项目管理工具

- 项目构建：Maven + Nexus
- 代码管理：Git + Github
- 镜像管理：Docker Registry

### 后端主要技术栈

|         技术         | 文档                                                         | 备注                                                         |
| :------------------: | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Spring Cloud Alibaba | [文档](https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md) | 致力于提供分布式应用服务开发的一站式解决方案, 已经从Apache孵化器毕业 |
|                      |                                                              |                                                              |
|                      |                                                              |                                                              |



### 前端主要技术栈

### 自动化运维

## 系统架构



## 服务规划

### PaaS

|   名称   |               地址                | 端口 |     备注     |
| :------: | :-------------------------------: | :--: | :----------: |
|  AliOSS  | micm.oss-cn-shanghai.aliyuncs.com |      |   文件存储   |
|  Github  |       www.github.com/iscolt       |      | 代码仓库地址 |
| AliMaven |                                   |      |  Maven仓库   |

### Services

|   名称   |      地址       |   端口    |             备注              |
| :------: | :-------------: | :-------: | :---------------------------: |
|  nacos   | 192.168.153.130 |   8848    | 服务注册与发现/分布式配置管理 |
|  redis   | 192.168.153.130 |   6379    |           令牌缓存            |
|  mysql   |    localhost    |   3306    |           数据存储            |
| sentinel | 192.168.153.130 |   8080    |        服务熔断, 限流         |
| RocketMQ | 192.168.153.131 | 8080/9876 |           消息队列            |
|  mysql   | 192.168.153.132 | 8080/3306 |          数据存储(x)          |
|  redis   | 192.168.153.132 |   6379    |          令牌缓存(x)          |



### Middleware



### Data



### Gateway



### Provider



### Business



### Message



### Cloud



### 其他

> 纯 jar, 无须运行的

- commons
- configuration
- dependencies



## 开发人员

| 成员                                                         |                  主页                   |         工作内容         | 备注 |
| :----------------------------------------------------------- | :-------------------------------------: | :----------------------: | :--: |
| ![isColt](https://avatars1.githubusercontent.com/u/33213215?s=32&v=4)Colt | [github](https://www.github.com/iscolt) | 校织网整体架构、后端开发 |      |
| ![Gemyi](https://avatars1.githubusercontent.com/u/50950109?s=32&v=4)Gemyi | [github](https://www.github.com/Gemyi)  |  校织网移动端、前端开发  |      |

## 许可证

[![license](https://img.shields.io/github/license/Miners-ICU/micm.svg?style=flat-square)](https://github.com/halo-dev/halo/blob/master/LICENSE)

> Micm 使用 Apache-2.0 协议开源，请尽量遵守开源协议。

## 贡献
参考 [CONTRIBUTION](/CONTRIBUTION.md)。
