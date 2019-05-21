SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_item
-- ----------------------------
DROP TABLE IF EXISTS `business_commodity`;
CREATE TABLE `business_commodity` (
   `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '商品id，同时也是商品编号',
   `title` varchar(100) NOT NULL COMMENT '商品标题',
   `sell_point` varchar(150) DEFAULT NULL COMMENT '商品卖点',
   `price` decimal(10,2) NOT NULL COMMENT '商品价格，单位为：元',
   `num` int(10) NOT NULL COMMENT '库存数量',
   `barcode` varchar(30) DEFAULT NULL COMMENT '商品条形码',
   `cid` bigint(10) NOT NULL COMMENT '所属类目，叶子类目',
   `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品状态，1-正常，2-下架，3-删除',
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

DROP TABLE IF EXISTS `business_commodity_category`;
CREATE TABLE `business_commodity_category` (
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

DROP TABLE IF EXISTS `business_commodity_desc`;
CREATE TABLE `business_commodity_desc` (
    `commodity_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
    `instruction` text COMMENT '商品描述',
    `createuser` varchar(32) DEFAULT NULL COMMENT '创建者',
    `updateuser` varchar(32) DEFAULT NULL COMMENT '更新者',
    `createtime` datetime NOT NULL COMMENT '创建时间',
    `updatetime` datetime NOT NULL COMMENT '更新时间',
    KEY `item_id` (`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品描述表';