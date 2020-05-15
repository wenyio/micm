CREATE TABLE `sys_user` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`nickname` varchar(50) NULL COMMENT '昵称, 显示用',
`username` varchar(50) NOT NULL COMMENT '用户名, 用于登录',
`password` varchar(255) NOT NULL COMMENT '密码',
`phone` varchar(20) NULL COMMENT '手机, 可用于登录',
`email` varchar(100) NOT NULL COMMENT '邮箱, 可用于登录',
`account` varchar(64) NULL COMMENT '学号, 工号等等',
`level` varchar(255) NULL COMMENT '主要身份, 学生, 管理员',
`avatar` varchar(255) NOT NULL COMMENT '头像url',
`face` varchar(255) NULL COMMENT '人脸url',
`face_id` varchar(255) NULL COMMENT '人脸id(百度云人脸TOKEN)',
`tenant_id` int(11) NULL COMMENT '租户ID',
`tenant_name` varchar(255) NULL COMMENT '租户名(组织名)',
`status` bit(1) NOT NULL COMMENT '0禁用, 1可用',
`updated` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
`created` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
`deleted` bit(1) NOT NULL COMMENT '1 正常, 0 删除',
PRIMARY KEY (`id`) 
)
COMMENT = '用户表';
CREATE TABLE `sys_role` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`parent_id` int(11) NOT NULL COMMENT '父角色',
`name` varchar(64) NOT NULL COMMENT '角色名称',
`enname` varchar(64) NOT NULL COMMENT '角色英文名',
`description` varchar(255) NULL COMMENT '备注',
`updated` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
`created` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
`deleted` bit(1) NOT NULL COMMENT '1 正常, 0 删除',
PRIMARY KEY (`id`) 
)
COMMENT = '角色表';
CREATE TABLE `sys_permission` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`parent_id` int(11) NOT NULL COMMENT '父权限id',
`name` varchar(64) NOT NULL COMMENT '权限名称',
`enname` varchar(64) NOT NULL COMMENT '权限英文名',
`url` varchar(255) NOT NULL COMMENT '授权路径',
`description` varchar(255) NULL COMMENT '备注',
`updated` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
`created` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
`deleted` bit(1) NOT NULL COMMENT '1 正常, 0 删除',
PRIMARY KEY (`id`) 
)
COMMENT = '权限表';
CREATE TABLE `sys_user_role` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`user_id` int(11) NOT NULL COMMENT '用户id',
`role_id` int(11) NOT NULL COMMENT '角色id',
PRIMARY KEY (`id`) 
)
COMMENT = '用户角色表';
CREATE TABLE `sys_role_permission` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`role_id` int(11) NOT NULL COMMENT '角色id',
`permission_id` int(11) NOT NULL COMMENT '权限id',
PRIMARY KEY (`id`) 
)
COMMENT = '角色权限表';
