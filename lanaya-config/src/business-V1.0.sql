SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `bs_brand`;
CREATE TABLE `bs_brand` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` varchar(64) NOT NULL COMMENT '品牌名称',
    `first_char` varchar(8) NOT NULL COMMENT '名称首字母',
    `listed_time` date DEFAULT NULL COMMENT '上市时间',
    `delisted_time` date DEFAULT NULL COMMENT '退市时间',
    `industry` varchar(64) DEFAULT NULL COMMENT '所属行业',
    `popularity` decimal(4,2) DEFAULT NULL COMMENT '知名度',
    `reputation` decimal(4,2) DEFAULT NULL COMMENT '美誉度',
    `penetration` decimal(4,2) DEFAULT NULL COMMENT '普及度',
    `logo_id` varchar(32) DEFAULT NULL COMMENT 'logo主键',
    `company_id` varchar(32) DEFAULT NULL COMMENT '所属公司id',
    `company_name` varchar(128) DEFAULT NULL COMMENT '所属公司名称',
    `rowstate` int(11) DEFAULT '1' COMMENT '状态',
    `version` int(11) DEFAULT '1' COMMENT '版本号',
    `createuser` varchar(64) DEFAULT NULL,
    `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updateuser` varchar(64) DEFAULT NULL,
    `updatetime` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bs_commodity`;
CREATE TABLE `bs_commodity` (
   `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '商品id，同时也是商品编号',
   `title` varchar(100) NOT NULL COMMENT '商品标题',
   `sell_point` varchar(150) DEFAULT NULL COMMENT '商品卖点',
   `price` decimal(10,2) NOT NULL COMMENT '商品价格，单位为：元',
   `num` int(10) NOT NULL COMMENT '库存数量',
   `barcode` varchar(30) DEFAULT NULL COMMENT '商品条形码',
   `cid` bigint(10) NOT NULL COMMENT '所属类目，叶子类目',
   `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品状态，1-正常，2-下架，-1-删除',
   `rowstate` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据状态，1-正常，0-无效，-1-禁用',
   `version` bigint(10) NOT NULL DEFAULT '1' COMMENT '版本号',
   `createuser` varchar(32) DEFAULT NULL COMMENT '创建者',
   `updateuser` varchar(32) DEFAULT NULL COMMENT '更新者',
   `createtime` datetime NOT NULL COMMENT '创建时间',
   `updatetime` datetime NOT NULL COMMENT '更新时间',
   PRIMARY KEY (`id`),
   KEY `cid` (`cid`),
   KEY `status` (`status`),
   KEY `updated` (`updatetime`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='商品表';

DROP TABLE IF EXISTS `bs_commodity_cat`;
CREATE TABLE `bs_commodity_cat` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
   `parent_id` bigint(20) DEFAULT NULL COMMENT '父类目ID=0时，代表的是一级的类目',
   `name` varchar(50) DEFAULT NULL COMMENT '类目名称',
   `status` int(1) DEFAULT '1' COMMENT '状态。可选值:1(正常),2(删除)',
   `ordered` int(4) DEFAULT NULL COMMENT '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
   `is_parent` tinyint(1) DEFAULT '1' COMMENT '该类目是否为父类目，1为true，0为false',
   `rowstate` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据状态，1-正常，0-无效，-1-禁用',
   `version` bigint(10) NOT NULL DEFAULT '1' COMMENT '版本号',
   `createuser` varchar(32) DEFAULT NULL COMMENT '创建者',
   `updateuser` varchar(32) DEFAULT NULL COMMENT '更新者',
   `createtime` datetime NOT NULL COMMENT '创建时间',
   `updatetime` datetime NOT NULL COMMENT '更新时间',
   PRIMARY KEY (`id`),
   KEY `parent_id` (`parent_id`,`status`) USING BTREE,
   KEY `ordered` (`ordered`)
) ENGINE=InnoDB AUTO_INCREMENT=1183 DEFAULT CHARSET=utf8 COMMENT='商品类目';

DROP TABLE IF EXISTS `bs_commodity_desc`;
CREATE TABLE `bs_commodity_desc` (
    `commodity_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
    `instruction` text COMMENT '商品描述',
    `rowstate` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据状态，1-正常，0-无效，-1-禁用',
    `version` bigint(10) NOT NULL DEFAULT '1' COMMENT '版本号',
    `createuser` varchar(32) DEFAULT NULL COMMENT '创建者',
    `updateuser` varchar(32) DEFAULT NULL COMMENT '更新者',
    `createtime` datetime NOT NULL COMMENT '创建时间',
    `updatetime` datetime NOT NULL COMMENT '更新时间',
    KEY `item_id` (`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品描述表';

DROP TABLE IF EXISTS `bs_commodity_spec`;
CREATE TABLE `bs_commodity_spec` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `commodity_cat_id` bigint(20) DEFAULT NULL COMMENT '商品类目ID',
    `spec_data` text COMMENT '参数数据，格式为json格式',
    `rowstate` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据状态，1-正常，0-无效，-1-禁用',
    `version` bigint(10) NOT NULL DEFAULT '1' COMMENT '版本号',
    `createuser` varchar(32) DEFAULT NULL COMMENT '创建者',
    `updateuser` varchar(32) DEFAULT NULL COMMENT '更新者',
    `createtime` datetime NOT NULL COMMENT '创建时间',
    `updatetime` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `commodity_category_id` (`commodity_cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品规格参数';

DROP TABLE IF EXISTS `bs_commodity_spec_relate`;
CREATE TABLE `bs_commodity_spec_relate` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `commodity_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
    `data` text COMMENT '参数数据，格式为json格式',
    `rowstate` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据状态，1-正常，0-无效，-1-禁用',
    `version` bigint(10) NOT NULL DEFAULT '1' COMMENT '版本号',
    `createuser` varchar(32) DEFAULT NULL COMMENT '创建者',
    `updateuser` varchar(32) DEFAULT NULL COMMENT '更新者',
    `createtime` datetime NOT NULL COMMENT '创建时间',
    `updatetime` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `commodity_id` (`commodity_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品规格和商品的关系表';

DROP TABLE IF EXISTS `bs_merchant`;
CREATE TABLE `bs_merchant` (
    `id` varchar(32) NOT NULL,
    `username` varchar(50) NOT NULL COMMENT '用户名',
    `password` varchar(32) NOT NULL COMMENT '密码，加密存储',
    `phone` varchar(20) DEFAULT NULL COMMENT '注册手机号',
    `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
    `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商户状态，0-初始化，1-审核通过，2-审核中，3-审核拒绝，-1-注销，-2-冻结',
    `rowstate` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据状态，1-正常，0-无效，-1-禁用',
    `version` bigint(10) NOT NULL DEFAULT '1' COMMENT '版本号',
    `createuser` varchar(32) DEFAULT NULL COMMENT '创建者',
    `updateuser` varchar(32) DEFAULT NULL COMMENT '更新者',
    `createtime` datetime NOT NULL COMMENT '创建时间',
    `updatetime` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`) USING BTREE,
    UNIQUE KEY `phone` (`phone`) USING BTREE,
    UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户表';

