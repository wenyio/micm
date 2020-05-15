CREATE TABLE `sys_tenant` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) NOT NULL COMMENT '租户名称（公司，学校名）',
`user_id` int(11) NOT NULL COMMENT '租户绑定的用户（租户的超管）',
`icon` varchar(255) NULL COMMENT '租户标志',
`tenant_area` varchar(255) NULL COMMENT '租户（学校）地区',
`tenant_address` varchar(255) NULL COMMENT '租住地址',
`business_license_id` varchar(255) NULL COMMENT '营业执照',
`legal_representative` varchar(255) NULL COMMENT '法定代表人',
`tenant_phone` varchar(255) NULL COMMENT '租户电话',
`mailbox` varchar(255) NULL COMMENT '邮箱',
`tenant_size` varchar(255) NULL COMMENT '小学？中学？大学？',
`balance` decimal(10,2) NULL COMMENT '余额',
`renewal_date` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '续期时间',
`expiration_date` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '到期时间',
`audit_state` varchar(255) NULL COMMENT '审核状态',
`status` bit(1) NULL DEFAULT 1 COMMENT '状态',
`created` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
`updated` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`) 
)
COMMENT = '系统租户表
';
CREATE TABLE `sys_service` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) NOT NULL COMMENT '服务名',
`icon` varchar(255) NULL COMMENT '服务logo',
`cover` varchar(255) NULL COMMENT '服务宣传图',
`price` decimal(10,2) NULL COMMENT '价格（按天计算）',
`version` varchar(255) NULL COMMENT '版本',
`description` varchar(255) NULL COMMENT '服务描述',
`status` bit(1) NULL COMMENT '状态 下架0，上架1',
`created` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
`updated` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`) 
)
COMMENT = '系统服务表
';
CREATE TABLE `sys_tenant_service` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`tenant_id` int(11) NOT NULL COMMENT '租户id',
`service_id` int NOT NULL COMMENT '服务id',
`renewal_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '续费时间',
`expiration_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '到期时间=到期时间 +（冻结时间=到期时间-冻结时间）',
`freeze_date` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '冻结时间',
`status` bit(1) NOT NULL DEFAULT 1 COMMENT '状态：冻结，正常',
PRIMARY KEY (`id`) 
)
COMMENT = '租户服务关系表';
CREATE TABLE `sys_log` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志类型',
`address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作地址',
`content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作内容',
`created` datetime NULL DEFAULT NULL,
`ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
`user_agent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器登录类型',
`user_id` int(11) NULL DEFAULT NULL COMMENT '操作人',
PRIMARY KEY (`id`) 
)
ENGINE = MyISAM
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci
COMMENT = '系统日志表'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;
