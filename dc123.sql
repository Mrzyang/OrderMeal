/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50639
Source Host           : 118.89.22.167:3306
Source Database       : dc123

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2019-03-05 14:31:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for about
-- ----------------------------
DROP TABLE IF EXISTS `about`;
CREATE TABLE `about` (
  `id` int(2) NOT NULL,
  `content` text COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of about
-- ----------------------------
INSERT INTO `about` VALUES ('1', '   “饿了么”是2008年创立的本地生活平台，主营在线外卖、新零售、即时配送和餐饮供应链等业务。隶属于上海拉扎斯信息科技有限公司，由张旭豪、康嘉等人于2009年4月在上海创立。 起源于上海交通大学闵行校区。截至2014年10月，公司业务覆盖全国近200个城市，加盟餐厅数共计18万家，日均订单超过100万单，团队规模超过2000人。 2018年5月29日，饿了么宣布获准开辟首批无人机即时配送航线，送餐无人机正式投入商业运营。 \n\n  “饿了么”是中国专业的餐饮O2O平台，由拉扎斯网络科技（上海）有限公司开发运营。 作为中国餐饮业数字化领跑者，“饿了么”秉承激情、极致、创新之信仰，以建立全面完善的数字化餐饮生态系统为使命，为用户提供便捷服务极致体验，为餐厅提供一体化运营解决方案，推进整个餐饮行业的数字化发展进程。   2017年4月20日，美团点评、百度外卖、到家美食会等主要网络订餐平台在北京市食药监局的支持、指导下，自发组建网络订餐平台行业自律联盟。 [18] 四大网络订餐平台CEO发言并共同签订《网络订餐平台自律共建联盟公约》，从入网经营者准入前承诺、平台准入、健全入网经营者档案管理及信息公示等十四个方面向社会郑重承诺，包括设立食品安全管理机构，主动在平台首页向社会公开；严格平台准入机制；主动定期向食药监管部门报送平台管理数据和信息；对实施联动惩戒的违法商户，在联盟各平台同步采取下线处理，同清同查；共同加强送餐员队伍食品安全培训；针对入网商户进行在线宣传、培训，共同宣传和推动“阳光餐饮”工程等。');

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_num` varchar(100) DEFAULT NULL COMMENT '员工号',
  `admin_password` varchar(255) DEFAULT NULL,
  `admin_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', '123456', 'fcea920f7412b5da7be0cf42b8c93759', '3');
INSERT INTO `admins` VALUES ('2', '1234', 'e10adc3949ba59abbe56e057f20f883e', '2');
INSERT INTO `admins` VALUES ('3', 'a12312312', 'e10adc3949ba59abbe56e057f20f883e', '2');
INSERT INTO `admins` VALUES ('4', '1234567', '25d55ad283aa400af464c76d713c07ad', '1');
INSERT INTO `admins` VALUES ('5', 'a2131q312', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `admins` VALUES ('6', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '3');

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_num` varchar(100) NOT NULL,
  `school_id` varchar(100) DEFAULT NULL,
  `school_name` varchar(100) DEFAULT NULL,
  `customer_grade` varchar(100) DEFAULT NULL COMMENT '年级',
  `customer_class` varchar(100) DEFAULT NULL,
  `customer_name` varchar(100) DEFAULT NULL,
  `customer_password` varchar(100) NOT NULL,
  `customer_account` double(10,0) NOT NULL DEFAULT '0' COMMENT '账户余额',
  `customer_type` int(1) NOT NULL DEFAULT '0' COMMENT '身份，0-学生   1-教职工',
  `customer_phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES ('1', '201513137020', null, null, null, null, '张明超', '123', '0', '1', '31326841313');
INSERT INTO `customers` VALUES ('2', 'zmcaa', null, null, null, null, null, '123', '0', '0', null);
INSERT INTO `customers` VALUES ('3', '123456', '1', '武汉科技大学', '3', '2', '张洋', '123456', '81', '0', '17671751371');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT COMMENT ' ''套餐id'',',
  `goods_name` varchar(100) DEFAULT NULL COMMENT '''套餐名'',',
  `goods_price` double(11,2) DEFAULT NULL COMMENT ' ''套餐价格'',',
  `product_name` varchar(255) DEFAULT NULL COMMENT ' ''套餐菜品(拼接'',',
  `product_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('7', '套餐5', '12.11', '菜品4,菜品5', null);
INSERT INTO `goods` VALUES ('8', '套餐6', '18.00', '菜品4,菜品5,菜品8', null);
INSERT INTO `goods` VALUES ('9', '套餐7', '15.00', '菜品4,菜品13,菜品5,菜品8', null);
INSERT INTO `goods` VALUES ('10', '套餐8', '15.00', '菜品4,菜品5,菜品8', null);
INSERT INTO `goods` VALUES ('12', '套餐10', '15.00', '菜品4,菜品5', null);
INSERT INTO `goods` VALUES ('14', '套餐12', '12.00', '菜品3,菜品9,菜品5,菜品7', null);
INSERT INTO `goods` VALUES ('18', '套餐13', '12.00', '菜品3,菜品9,菜品5,菜品7', null);
INSERT INTO `goods` VALUES ('19', '套餐14', '13.00', '菜品3,菜品13,菜品5,菜品7,菜品9', 'D:\\IDEA\\DC\\src\\main\\webapp\\WEB-INF\\view\\BackEnd\\goods\\0\\5\\2a5e2552-a10f-4da9-a6eb-c5a3ca1d700a_demo1.jpg');
INSERT INTO `goods` VALUES ('21', '套餐15', '12.22', '菜品4,菜品9,菜品12,菜品11,菜品9', 'D:\\IDEA\\DC\\src\\main\\webapp\\WEB-INF\\view\\BackEnd\\goods\\4\\8\\c760301b-8d81-498b-81a8-6ef1dedc68d4_demo1.jpg');
INSERT INTO `goods` VALUES ('22', '套餐16', '13.00', '菜品3,菜品9,菜品12,菜品11,-1', 'D:\\IDEA\\DC\\src\\main\\webapp\\WEB-INF\\view\\BackEnd\\goods\\14\\9\\07aff715-4d9b-4571-82fe-5824eafd993f_demo1.jpg');
INSERT INTO `goods` VALUES ('23', '好吃的东西', '18.00', '菜品4,菜品13,菜品12,菜品7,菜品9', 'D:\\java\\project\\OrderMeal\\src\\main\\webapp\\WEB-INF\\view\\BackEnd\\goods\\3\\11\\3252da34-6f3c-42de-9210-5364bcbe837f_poly.jpg');
INSERT INTO `goods` VALUES ('24', '美味佳肴', '15.00', '菜品4,菜品13,菜品12,菜品7,菜品13', 'D:\\java\\project\\OrderMeal\\src\\main\\webapp\\WEB-INF\\view\\BackEnd\\goods\\6\\1\\15db24ed-e815-41e5-9d7d-cae5bce3616e_poly.jpg');
INSERT INTO `goods` VALUES ('25', 'f1', '15.00', '菜品3,菜品13,菜品12,菜品11,菜品13', 'goods/10/8/d4a049c3-4a5b-40de-9423-491fee6a16ec_food1.jpg');
INSERT INTO `goods` VALUES ('26', 'f2', '15.00', '菜品10,菜品13,菜品12,菜品11,菜品13', 'goods/4/10/d099b228-6b8f-4474-ab7e-f004f1039d87_food2.jpg');
INSERT INTO `goods` VALUES ('27', 'f3', '15.00', '菜品10,菜品9,菜品12,菜品11,菜品9', 'goods/15/14/c2df715e-8f00-43dc-a321-8ae5519e5d84_food3.jpg');
INSERT INTO `goods` VALUES ('28', 'f3', '15.00', '菜品4,菜品13,菜品12,菜品7,菜品13', 'goods/2/10/161f2fb1-bdbe-4dbd-9f93-58a0bd5f7e36_food3.jpg');
INSERT INTO `goods` VALUES ('29', 'f4', '15.00', '菜品3,菜品9,菜品5,菜品11,菜品13', 'goods/3/11/bbef78f0-c86d-43da-84a5-4269b640e63e_food4.jpg');
INSERT INTO `goods` VALUES ('30', 'f5', '15.00', '菜品4,菜品9,菜品14,菜品11,菜品13', 'goods/4/7/74fe6b62-111b-4a70-b08e-8ea0aea01184_food5.jpg');
INSERT INTO `goods` VALUES ('31', 'f6', '15.00', '菜品6,菜品13,菜品14,菜品11,菜品9', 'goods/10/5/0874ea05-3ac7-4b4c-9fb8-aa4183ef69c7_food6.jpg');
INSERT INTO `goods` VALUES ('32', 'fd7', '18.00', '菜品6,菜品9,菜品12,菜品7,菜品13', 'goods/2/12/f0f29293-fab5-4171-9382-63d1280b1eef_food7.jpg');
INSERT INTO `goods` VALUES ('33', 'fd8', '18.00', '菜品10,菜品13,菜品12,菜品11,菜品13', 'goods/1/5/44099ff8-bf2e-4e86-a15b-8efa6b7fe21a_food8.jpg');
INSERT INTO `goods` VALUES ('34', 'fd9', '18.00', '菜品3,菜品13,菜品5,菜品11,菜品9', 'goods/11/7/e8b4a89a-247a-422c-9b6e-318f37e79f3a_food9.jpg');
INSERT INTO `goods` VALUES ('35', 'fd10', '18.00', '菜品6,菜品9,菜品5,菜品11,菜品9', 'goods/13/12/6f9cb8ab-ff11-4672-a404-348d016489ca_food10.jpg');
INSERT INTO `goods` VALUES ('36', 'fd11', '18.00', '菜品4,菜品9,菜品14,菜品7,菜品13', 'goods/2/6/a13bc2f2-b1f1-46ba-983f-d7d8d0b321fb_food11.jpg');
INSERT INTO `goods` VALUES ('37', 'fd12', '18.00', '菜品10,菜品13,菜品12,菜品7,菜品13', 'goods/14/2/f07e4787-ea5f-4a7b-9378-b8b9829fac47_food12.jpg');
INSERT INTO `goods` VALUES ('38', '的说法都是', '22.00', '菜品4,菜品13,菜品14,菜品11', 'goods/13/15/eaf09e70-70e8-4288-b176-4812c4908b3a_food3.jpg');
INSERT INTO `goods` VALUES ('39', '很好吃的菜', '18.00', '菜品4,菜品9,菜品5,菜品11', 'goods/14/11/a34435c7-0de9-44d6-8b24-6a308ae944f2_food1.jpg');

-- ----------------------------
-- Table structure for menu2goods
-- ----------------------------
DROP TABLE IF EXISTS `menu2goods`;
CREATE TABLE `menu2goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关系id',
  `menu_id` varchar(100) NOT NULL COMMENT '‘菜单id’',
  `goods_id` int(11) NOT NULL COMMENT '‘套餐id’',
  `goods_name` varchar(100) DEFAULT NULL,
  `goods_price` double DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `delivery_date` int(11) DEFAULT NULL COMMENT '‘订单配送时间’ 状态：1.第一餐 2.第二餐......',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=544 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu2goods
-- ----------------------------
INSERT INTO `menu2goods` VALUES ('3', 'd753094f-7e17-4ea1-a4cf-b3e1fd5bf2cb', '5', null, null, null, '1');
INSERT INTO `menu2goods` VALUES ('4', 'd753094f-7e17-4ea1-a4cf-b3e1fd5bf2cb', '5', null, null, null, '2');
INSERT INTO `menu2goods` VALUES ('5', 'd753094f-7e17-4ea1-a4cf-b3e1fd5bf2cb', '5', null, null, null, '3');
INSERT INTO `menu2goods` VALUES ('6', 'd753094f-7e17-4ea1-a4cf-b3e1fd5bf2cb', '5', null, null, null, '4');
INSERT INTO `menu2goods` VALUES ('7', '42b0339c-e05f-47e8-ae2a-5e1e3af51d02', '4', null, null, null, '1');
INSERT INTO `menu2goods` VALUES ('8', '42b0339c-e05f-47e8-ae2a-5e1e3af51d02', '4', null, null, null, '2');
INSERT INTO `menu2goods` VALUES ('9', '42b0339c-e05f-47e8-ae2a-5e1e3af51d02', '4', null, null, null, '3');
INSERT INTO `menu2goods` VALUES ('10', '42b0339c-e05f-47e8-ae2a-5e1e3af51d02', '4', null, null, null, '4');
INSERT INTO `menu2goods` VALUES ('11', '42b0339c-e05f-47e8-ae2a-5e1e3af51d02', '4', null, null, null, '5');
INSERT INTO `menu2goods` VALUES ('12', '2fc1531e-a2a2-4a2b-9c75-a7ea5755475f', '4', null, null, null, '1');
INSERT INTO `menu2goods` VALUES ('13', '2fc1531e-a2a2-4a2b-9c75-a7ea5755475f', '6', null, null, null, '2');
INSERT INTO `menu2goods` VALUES ('14', '2fc1531e-a2a2-4a2b-9c75-a7ea5755475f', '5', null, null, null, '3');
INSERT INTO `menu2goods` VALUES ('15', '2fc1531e-a2a2-4a2b-9c75-a7ea5755475f', '7', null, null, null, '4');
INSERT INTO `menu2goods` VALUES ('16', 'de0b2080-c7ea-4a50-a737-16bfddc7ae43', '5', null, null, null, '1');
INSERT INTO `menu2goods` VALUES ('17', 'de0b2080-c7ea-4a50-a737-16bfddc7ae43', '4', null, null, null, '2');
INSERT INTO `menu2goods` VALUES ('18', 'de0b2080-c7ea-4a50-a737-16bfddc7ae43', '6', null, null, null, '3');
INSERT INTO `menu2goods` VALUES ('19', 'de0b2080-c7ea-4a50-a737-16bfddc7ae43', '5', null, null, null, '4');
INSERT INTO `menu2goods` VALUES ('20', 'de0b2080-c7ea-4a50-a737-16bfddc7ae43', '6', null, null, null, '7');
INSERT INTO `menu2goods` VALUES ('21', 'de0b2080-c7ea-4a50-a737-16bfddc7ae43', '6', null, null, null, '8');
INSERT INTO `menu2goods` VALUES ('22', 'a26f98dd-bdac-4e55-a1a3-4bb5ae5cbe70', '7', '套餐5', '15', '菜品4,菜品5', '1');
INSERT INTO `menu2goods` VALUES ('23', 'a26f98dd-bdac-4e55-a1a3-4bb5ae5cbe70', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '2');
INSERT INTO `menu2goods` VALUES ('24', 'a26f98dd-bdac-4e55-a1a3-4bb5ae5cbe70', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '3');
INSERT INTO `menu2goods` VALUES ('25', 'a26f98dd-bdac-4e55-a1a3-4bb5ae5cbe70', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '4');
INSERT INTO `menu2goods` VALUES ('26', 'a26f98dd-bdac-4e55-a1a3-4bb5ae5cbe70', '7', '套餐5', '15', '菜品4,菜品5', '5');
INSERT INTO `menu2goods` VALUES ('27', 'a26f98dd-bdac-4e55-a1a3-4bb5ae5cbe70', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '7');
INSERT INTO `menu2goods` VALUES ('28', 'bc583a92-cd8c-42b2-b6f7-479255f2b905', '7', '套餐5', '15', '菜品4,菜品5', '1');
INSERT INTO `menu2goods` VALUES ('29', 'bc583a92-cd8c-42b2-b6f7-479255f2b905', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '2');
INSERT INTO `menu2goods` VALUES ('30', 'bc583a92-cd8c-42b2-b6f7-479255f2b905', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '3');
INSERT INTO `menu2goods` VALUES ('31', 'bc583a92-cd8c-42b2-b6f7-479255f2b905', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '4');
INSERT INTO `menu2goods` VALUES ('32', 'bc583a92-cd8c-42b2-b6f7-479255f2b905', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '5');
INSERT INTO `menu2goods` VALUES ('33', 'bc583a92-cd8c-42b2-b6f7-479255f2b905', '10', '套餐8', '15', '菜品4,菜品5,菜品8', '6');
INSERT INTO `menu2goods` VALUES ('34', 'bc583a92-cd8c-42b2-b6f7-479255f2b905', '11', '套餐9', '15', '菜品4', '7');
INSERT INTO `menu2goods` VALUES ('35', 'bc583a92-cd8c-42b2-b6f7-479255f2b905', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '8');
INSERT INTO `menu2goods` VALUES ('36', 'bc583a92-cd8c-42b2-b6f7-479255f2b905', '7', '套餐5', '15', '菜品4,菜品5', '9');
INSERT INTO `menu2goods` VALUES ('37', 'bc583a92-cd8c-42b2-b6f7-479255f2b905', '7', '套餐5', '15', '菜品4,菜品5', '10');
INSERT INTO `menu2goods` VALUES ('38', 'bc583a92-cd8c-42b2-b6f7-479255f2b905', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '11');
INSERT INTO `menu2goods` VALUES ('39', 'bc583a92-cd8c-42b2-b6f7-479255f2b905', '7', '套餐5', '15', '菜品4,菜品5', '12');
INSERT INTO `menu2goods` VALUES ('40', 'bc583a92-cd8c-42b2-b6f7-479255f2b905', '11', '套餐9', '15', '菜品4', '13');
INSERT INTO `menu2goods` VALUES ('41', 'bc583a92-cd8c-42b2-b6f7-479255f2b905', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '14');
INSERT INTO `menu2goods` VALUES ('42', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '1');
INSERT INTO `menu2goods` VALUES ('43', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '11', '套餐9', '15', '菜品4', '2');
INSERT INTO `menu2goods` VALUES ('44', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '10', '套餐8', '15', '菜品4,菜品5,菜品8', '3');
INSERT INTO `menu2goods` VALUES ('45', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '7', '套餐5', '15', '菜品4,菜品5', '4');
INSERT INTO `menu2goods` VALUES ('46', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '11', '套餐9', '15', '菜品4', '5');
INSERT INTO `menu2goods` VALUES ('47', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '10', '套餐8', '15', '菜品4,菜品5,菜品8', '6');
INSERT INTO `menu2goods` VALUES ('48', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '7');
INSERT INTO `menu2goods` VALUES ('49', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '8');
INSERT INTO `menu2goods` VALUES ('50', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '10', '套餐8', '15', '菜品4,菜品5,菜品8', '9');
INSERT INTO `menu2goods` VALUES ('51', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '10');
INSERT INTO `menu2goods` VALUES ('52', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '11');
INSERT INTO `menu2goods` VALUES ('53', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '12');
INSERT INTO `menu2goods` VALUES ('54', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '13');
INSERT INTO `menu2goods` VALUES ('55', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '16', '是的撒啊撒', '12', '菜品3', '14');
INSERT INTO `menu2goods` VALUES ('56', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '15');
INSERT INTO `menu2goods` VALUES ('57', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '10', '套餐8', '15', '菜品4,菜品5,菜品8', '16');
INSERT INTO `menu2goods` VALUES ('58', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '11', '套餐9', '15', '菜品4', '17');
INSERT INTO `menu2goods` VALUES ('59', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '18');
INSERT INTO `menu2goods` VALUES ('60', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '19');
INSERT INTO `menu2goods` VALUES ('61', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '7', '套餐5', '15', '菜品4,菜品5', '20');
INSERT INTO `menu2goods` VALUES ('62', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '21');
INSERT INTO `menu2goods` VALUES ('63', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '22');
INSERT INTO `menu2goods` VALUES ('64', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '23');
INSERT INTO `menu2goods` VALUES ('65', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '10', '套餐8', '15', '菜品4,菜品5,菜品8', '24');
INSERT INTO `menu2goods` VALUES ('66', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '25');
INSERT INTO `menu2goods` VALUES ('67', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '26');
INSERT INTO `menu2goods` VALUES ('68', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '27');
INSERT INTO `menu2goods` VALUES ('69', '7e9976ff-e6e1-4275-b183-669a83f5e11f', '7', '套餐5', '15', '菜品4,菜品5', '28');
INSERT INTO `menu2goods` VALUES ('70', '11cc91a0-d7b9-408c-a647-6c9fdadb8b9c', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '1');
INSERT INTO `menu2goods` VALUES ('71', '11cc91a0-d7b9-408c-a647-6c9fdadb8b9c', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '22');
INSERT INTO `menu2goods` VALUES ('72', '11cc91a0-d7b9-408c-a647-6c9fdadb8b9c', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '8');
INSERT INTO `menu2goods` VALUES ('73', '11cc91a0-d7b9-408c-a647-6c9fdadb8b9c', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '15');
INSERT INTO `menu2goods` VALUES ('74', '95df32cb-652b-4855-96fb-42aba0aa6352', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '1');
INSERT INTO `menu2goods` VALUES ('75', '95df32cb-652b-4855-96fb-42aba0aa6352', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '22');
INSERT INTO `menu2goods` VALUES ('76', '95df32cb-652b-4855-96fb-42aba0aa6352', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '8');
INSERT INTO `menu2goods` VALUES ('77', '95df32cb-652b-4855-96fb-42aba0aa6352', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '15');
INSERT INTO `menu2goods` VALUES ('78', 'de4c2560-886c-4c35-aa23-b3a876c57265', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '1');
INSERT INTO `menu2goods` VALUES ('79', 'de4c2560-886c-4c35-aa23-b3a876c57265', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '2');
INSERT INTO `menu2goods` VALUES ('80', 'de4c2560-886c-4c35-aa23-b3a876c57265', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '22');
INSERT INTO `menu2goods` VALUES ('81', 'de4c2560-886c-4c35-aa23-b3a876c57265', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '8');
INSERT INTO `menu2goods` VALUES ('82', 'de4c2560-886c-4c35-aa23-b3a876c57265', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '15');
INSERT INTO `menu2goods` VALUES ('87', '87a112c3-6ca2-4011-a8a7-03054c706959', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '16');
INSERT INTO `menu2goods` VALUES ('88', '87a112c3-6ca2-4011-a8a7-03054c706959', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '23');
INSERT INTO `menu2goods` VALUES ('89', '87a112c3-6ca2-4011-a8a7-03054c706959', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '9');
INSERT INTO `menu2goods` VALUES ('90', '52ee57c3-d1e4-489e-9094-90fbf13d4b6c', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '17');
INSERT INTO `menu2goods` VALUES ('91', '52ee57c3-d1e4-489e-9094-90fbf13d4b6c', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '3');
INSERT INTO `menu2goods` VALUES ('92', '52ee57c3-d1e4-489e-9094-90fbf13d4b6c', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '24');
INSERT INTO `menu2goods` VALUES ('93', '52ee57c3-d1e4-489e-9094-90fbf13d4b6c', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '10');
INSERT INTO `menu2goods` VALUES ('94', '81827582-020d-47e0-84da-e0fa3abc7e92', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '16');
INSERT INTO `menu2goods` VALUES ('95', '81827582-020d-47e0-84da-e0fa3abc7e92', '7', '套餐5', '15', '菜品4,菜品5', '1');
INSERT INTO `menu2goods` VALUES ('96', '81827582-020d-47e0-84da-e0fa3abc7e92', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '17');
INSERT INTO `menu2goods` VALUES ('97', '81827582-020d-47e0-84da-e0fa3abc7e92', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '2');
INSERT INTO `menu2goods` VALUES ('98', '81827582-020d-47e0-84da-e0fa3abc7e92', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '3');
INSERT INTO `menu2goods` VALUES ('99', '81827582-020d-47e0-84da-e0fa3abc7e92', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '22');
INSERT INTO `menu2goods` VALUES ('100', '81827582-020d-47e0-84da-e0fa3abc7e92', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '23');
INSERT INTO `menu2goods` VALUES ('101', '81827582-020d-47e0-84da-e0fa3abc7e92', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '8');
INSERT INTO `menu2goods` VALUES ('102', '81827582-020d-47e0-84da-e0fa3abc7e92', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '24');
INSERT INTO `menu2goods` VALUES ('103', '81827582-020d-47e0-84da-e0fa3abc7e92', '7', '套餐5', '15', '菜品4,菜品5', '9');
INSERT INTO `menu2goods` VALUES ('104', '81827582-020d-47e0-84da-e0fa3abc7e92', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '10');
INSERT INTO `menu2goods` VALUES ('105', '81827582-020d-47e0-84da-e0fa3abc7e92', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '15');
INSERT INTO `menu2goods` VALUES ('106', '92e0ab36-bab2-4d2e-807f-4ca0f551f90c', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '1');
INSERT INTO `menu2goods` VALUES ('107', '92e0ab36-bab2-4d2e-807f-4ca0f551f90c', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '22');
INSERT INTO `menu2goods` VALUES ('108', '92e0ab36-bab2-4d2e-807f-4ca0f551f90c', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '8');
INSERT INTO `menu2goods` VALUES ('109', '92e0ab36-bab2-4d2e-807f-4ca0f551f90c', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '15');
INSERT INTO `menu2goods` VALUES ('110', 'd84aacff-452c-411c-bad8-43e64a3eaea0', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '1');
INSERT INTO `menu2goods` VALUES ('111', 'd84aacff-452c-411c-bad8-43e64a3eaea0', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '22');
INSERT INTO `menu2goods` VALUES ('112', 'd84aacff-452c-411c-bad8-43e64a3eaea0', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '15');
INSERT INTO `menu2goods` VALUES ('116', 'fbce7831-f607-4438-81d1-99eca70e5f0c', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '1');
INSERT INTO `menu2goods` VALUES ('117', 'fbce7831-f607-4438-81d1-99eca70e5f0c', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '8');
INSERT INTO `menu2goods` VALUES ('118', 'fbce7831-f607-4438-81d1-99eca70e5f0c', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '15');
INSERT INTO `menu2goods` VALUES ('122', '435e0c3e-9bc6-4f29-becc-dc2bbee3904b', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '16');
INSERT INTO `menu2goods` VALUES ('123', '435e0c3e-9bc6-4f29-becc-dc2bbee3904b', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '1');
INSERT INTO `menu2goods` VALUES ('124', '435e0c3e-9bc6-4f29-becc-dc2bbee3904b', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '2');
INSERT INTO `menu2goods` VALUES ('125', '435e0c3e-9bc6-4f29-becc-dc2bbee3904b', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '8');
INSERT INTO `menu2goods` VALUES ('126', '435e0c3e-9bc6-4f29-becc-dc2bbee3904b', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '15');
INSERT INTO `menu2goods` VALUES ('127', 'badcea74-6609-43f9-b047-02fb4bfc955b', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '1');
INSERT INTO `menu2goods` VALUES ('128', 'badcea74-6609-43f9-b047-02fb4bfc955b', '7', '套餐5', '15', '菜品4,菜品5', '2');
INSERT INTO `menu2goods` VALUES ('129', 'badcea74-6609-43f9-b047-02fb4bfc955b', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '3');
INSERT INTO `menu2goods` VALUES ('130', 'badcea74-6609-43f9-b047-02fb4bfc955b', '7', '套餐5', '15', '菜品4,菜品5', '4');
INSERT INTO `menu2goods` VALUES ('131', 'badcea74-6609-43f9-b047-02fb4bfc955b', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '5');
INSERT INTO `menu2goods` VALUES ('132', 'badcea74-6609-43f9-b047-02fb4bfc955b', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '6');
INSERT INTO `menu2goods` VALUES ('133', 'badcea74-6609-43f9-b047-02fb4bfc955b', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '7');
INSERT INTO `menu2goods` VALUES ('134', 'badcea74-6609-43f9-b047-02fb4bfc955b', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '8');
INSERT INTO `menu2goods` VALUES ('135', 'badcea74-6609-43f9-b047-02fb4bfc955b', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '9');
INSERT INTO `menu2goods` VALUES ('136', 'badcea74-6609-43f9-b047-02fb4bfc955b', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '10');
INSERT INTO `menu2goods` VALUES ('137', 'badcea74-6609-43f9-b047-02fb4bfc955b', '16', '是的撒啊撒', '12', '菜品3', '11');
INSERT INTO `menu2goods` VALUES ('138', 'badcea74-6609-43f9-b047-02fb4bfc955b', '7', '套餐5', '15', '菜品4,菜品5', '12');
INSERT INTO `menu2goods` VALUES ('139', 'badcea74-6609-43f9-b047-02fb4bfc955b', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '13');
INSERT INTO `menu2goods` VALUES ('140', 'badcea74-6609-43f9-b047-02fb4bfc955b', '10', '套餐8', '15', '菜品4,菜品5,菜品8', '14');
INSERT INTO `menu2goods` VALUES ('141', 'badcea74-6609-43f9-b047-02fb4bfc955b', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '15');
INSERT INTO `menu2goods` VALUES ('142', 'badcea74-6609-43f9-b047-02fb4bfc955b', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '16');
INSERT INTO `menu2goods` VALUES ('143', 'badcea74-6609-43f9-b047-02fb4bfc955b', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '17');
INSERT INTO `menu2goods` VALUES ('144', 'badcea74-6609-43f9-b047-02fb4bfc955b', '10', '套餐8', '15', '菜品4,菜品5,菜品8', '18');
INSERT INTO `menu2goods` VALUES ('145', 'badcea74-6609-43f9-b047-02fb4bfc955b', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '19');
INSERT INTO `menu2goods` VALUES ('146', 'badcea74-6609-43f9-b047-02fb4bfc955b', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '20');
INSERT INTO `menu2goods` VALUES ('147', 'badcea74-6609-43f9-b047-02fb4bfc955b', '7', '套餐5', '15', '菜品4,菜品5', '21');
INSERT INTO `menu2goods` VALUES ('148', 'badcea74-6609-43f9-b047-02fb4bfc955b', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '22');
INSERT INTO `menu2goods` VALUES ('149', 'badcea74-6609-43f9-b047-02fb4bfc955b', '4', '套餐2', '15', '菜品14,菜品9,菜品5,菜品11', '23');
INSERT INTO `menu2goods` VALUES ('150', 'badcea74-6609-43f9-b047-02fb4bfc955b', '5', '套餐3', '18', '菜品4,菜品13,菜品5,菜品8', '24');
INSERT INTO `menu2goods` VALUES ('151', 'badcea74-6609-43f9-b047-02fb4bfc955b', '7', '套餐5', '15', '菜品4,菜品5', '25');
INSERT INTO `menu2goods` VALUES ('152', 'badcea74-6609-43f9-b047-02fb4bfc955b', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '26');
INSERT INTO `menu2goods` VALUES ('153', 'badcea74-6609-43f9-b047-02fb4bfc955b', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '27');
INSERT INTO `menu2goods` VALUES ('154', 'badcea74-6609-43f9-b047-02fb4bfc955b', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '28');
INSERT INTO `menu2goods` VALUES ('155', 'da927150-a13a-4479-a557-9514ade76916', '16', '是的撒啊撒', '12', '菜品3', '1');
INSERT INTO `menu2goods` VALUES ('156', 'da927150-a13a-4479-a557-9514ade76916', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '22');
INSERT INTO `menu2goods` VALUES ('157', 'da927150-a13a-4479-a557-9514ade76916', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '8');
INSERT INTO `menu2goods` VALUES ('158', 'da927150-a13a-4479-a557-9514ade76916', '7', '套餐5', '15', '菜品4,菜品5', '15');
INSERT INTO `menu2goods` VALUES ('159', '348b0828-cbd1-485c-932e-79ec1da4d275', '12', '套餐10', '15', '菜品4,菜品5', '16');
INSERT INTO `menu2goods` VALUES ('160', '348b0828-cbd1-485c-932e-79ec1da4d275', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '1');
INSERT INTO `menu2goods` VALUES ('161', '348b0828-cbd1-485c-932e-79ec1da4d275', '18', '套餐13', '12', '菜品3,菜品9,菜品5,菜品7', '17');
INSERT INTO `menu2goods` VALUES ('162', '348b0828-cbd1-485c-932e-79ec1da4d275', '10', '套餐8', '15', '菜品4,菜品5,菜品8', '2');
INSERT INTO `menu2goods` VALUES ('163', '348b0828-cbd1-485c-932e-79ec1da4d275', '16', '是的撒啊撒', '12', '菜品3', '3');
INSERT INTO `menu2goods` VALUES ('164', '348b0828-cbd1-485c-932e-79ec1da4d275', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '22');
INSERT INTO `menu2goods` VALUES ('165', '348b0828-cbd1-485c-932e-79ec1da4d275', '14', '套餐12', '12', '菜品3,菜品9,菜品5,菜品7', '23');
INSERT INTO `menu2goods` VALUES ('166', '348b0828-cbd1-485c-932e-79ec1da4d275', '7', '套餐5', '15', '菜品4,菜品5', '8');
INSERT INTO `menu2goods` VALUES ('167', '348b0828-cbd1-485c-932e-79ec1da4d275', '11', '套餐9', '15', '菜品4', '9');
INSERT INTO `menu2goods` VALUES ('168', '348b0828-cbd1-485c-932e-79ec1da4d275', '17', '啊大苏打实打实大苏打似的', '13', '菜品3,菜品5', '10');
INSERT INTO `menu2goods` VALUES ('169', '348b0828-cbd1-485c-932e-79ec1da4d275', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '15');
INSERT INTO `menu2goods` VALUES ('170', '80405758-971b-432b-8ceb-a45d10319b80', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '1');
INSERT INTO `menu2goods` VALUES ('171', '80405758-971b-432b-8ceb-a45d10319b80', '11', '套餐9', '15', '菜品4', '2');
INSERT INTO `menu2goods` VALUES ('172', '80405758-971b-432b-8ceb-a45d10319b80', '14', '套餐12', '12', '菜品3,菜品9,菜品5,菜品7', '3');
INSERT INTO `menu2goods` VALUES ('173', '80405758-971b-432b-8ceb-a45d10319b80', '11', '套餐9', '15', '菜品4', '4');
INSERT INTO `menu2goods` VALUES ('174', '80405758-971b-432b-8ceb-a45d10319b80', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '5');
INSERT INTO `menu2goods` VALUES ('175', '80405758-971b-432b-8ceb-a45d10319b80', '7', '套餐5', '15', '菜品4,菜品5', '6');
INSERT INTO `menu2goods` VALUES ('176', '80405758-971b-432b-8ceb-a45d10319b80', '7', '套餐5', '15', '菜品4,菜品5', '7');
INSERT INTO `menu2goods` VALUES ('177', '80405758-971b-432b-8ceb-a45d10319b80', '7', '套餐5', '15', '菜品4,菜品5', '8');
INSERT INTO `menu2goods` VALUES ('178', '80405758-971b-432b-8ceb-a45d10319b80', '10', '套餐8', '15', '菜品4,菜品5,菜品8', '9');
INSERT INTO `menu2goods` VALUES ('179', '80405758-971b-432b-8ceb-a45d10319b80', '12', '套餐10', '15', '菜品4,菜品5', '10');
INSERT INTO `menu2goods` VALUES ('180', '80405758-971b-432b-8ceb-a45d10319b80', '12', '套餐10', '15', '菜品4,菜品5', '11');
INSERT INTO `menu2goods` VALUES ('181', '80405758-971b-432b-8ceb-a45d10319b80', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '12');
INSERT INTO `menu2goods` VALUES ('182', '80405758-971b-432b-8ceb-a45d10319b80', '10', '套餐8', '15', '菜品4,菜品5,菜品8', '13');
INSERT INTO `menu2goods` VALUES ('183', '80405758-971b-432b-8ceb-a45d10319b80', '7', '套餐5', '15', '菜品4,菜品5', '14');
INSERT INTO `menu2goods` VALUES ('184', '80405758-971b-432b-8ceb-a45d10319b80', '7', '套餐5', '15', '菜品4,菜品5', '15');
INSERT INTO `menu2goods` VALUES ('185', '80405758-971b-432b-8ceb-a45d10319b80', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '16');
INSERT INTO `menu2goods` VALUES ('186', '80405758-971b-432b-8ceb-a45d10319b80', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '17');
INSERT INTO `menu2goods` VALUES ('187', '80405758-971b-432b-8ceb-a45d10319b80', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '18');
INSERT INTO `menu2goods` VALUES ('188', '80405758-971b-432b-8ceb-a45d10319b80', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '19');
INSERT INTO `menu2goods` VALUES ('189', '80405758-971b-432b-8ceb-a45d10319b80', '10', '套餐8', '15', '菜品4,菜品5,菜品8', '20');
INSERT INTO `menu2goods` VALUES ('190', '80405758-971b-432b-8ceb-a45d10319b80', '10', '套餐8', '15', '菜品4,菜品5,菜品8', '21');
INSERT INTO `menu2goods` VALUES ('191', '80405758-971b-432b-8ceb-a45d10319b80', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '22');
INSERT INTO `menu2goods` VALUES ('192', '80405758-971b-432b-8ceb-a45d10319b80', '7', '套餐5', '15', '菜品4,菜品5', '23');
INSERT INTO `menu2goods` VALUES ('193', '80405758-971b-432b-8ceb-a45d10319b80', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '24');
INSERT INTO `menu2goods` VALUES ('194', '80405758-971b-432b-8ceb-a45d10319b80', '14', '套餐12', '12', '菜品3,菜品9,菜品5,菜品7', '25');
INSERT INTO `menu2goods` VALUES ('195', '80405758-971b-432b-8ceb-a45d10319b80', '11', '套餐9', '15', '菜品4', '26');
INSERT INTO `menu2goods` VALUES ('196', '80405758-971b-432b-8ceb-a45d10319b80', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '27');
INSERT INTO `menu2goods` VALUES ('197', '80405758-971b-432b-8ceb-a45d10319b80', '12', '套餐10', '15', '菜品4,菜品5', '28');
INSERT INTO `menu2goods` VALUES ('198', 'badcea74-6609-43f9-b047-02fb4bfc955b', '7', '套餐5', '15', '菜品4,菜品5', '1');
INSERT INTO `menu2goods` VALUES ('199', 'badcea74-6609-43f9-b047-02fb4bfc955b', '7', '套餐5', '15', '菜品4,菜品5', '2');
INSERT INTO `menu2goods` VALUES ('200', 'badcea74-6609-43f9-b047-02fb4bfc955b', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '3');
INSERT INTO `menu2goods` VALUES ('201', 'badcea74-6609-43f9-b047-02fb4bfc955b', '7', '套餐5', '15', '菜品4,菜品5', '4');
INSERT INTO `menu2goods` VALUES ('202', 'badcea74-6609-43f9-b047-02fb4bfc955b', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '5');
INSERT INTO `menu2goods` VALUES ('203', 'badcea74-6609-43f9-b047-02fb4bfc955b', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '6');
INSERT INTO `menu2goods` VALUES ('204', 'badcea74-6609-43f9-b047-02fb4bfc955b', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '7');
INSERT INTO `menu2goods` VALUES ('205', 'badcea74-6609-43f9-b047-02fb4bfc955b', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '8');
INSERT INTO `menu2goods` VALUES ('206', 'badcea74-6609-43f9-b047-02fb4bfc955b', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '9');
INSERT INTO `menu2goods` VALUES ('207', 'badcea74-6609-43f9-b047-02fb4bfc955b', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '10');
INSERT INTO `menu2goods` VALUES ('208', 'badcea74-6609-43f9-b047-02fb4bfc955b', '16', '是的撒啊撒', '12', '菜品3', '11');
INSERT INTO `menu2goods` VALUES ('209', 'badcea74-6609-43f9-b047-02fb4bfc955b', '7', '套餐5', '15', '菜品4,菜品5', '12');
INSERT INTO `menu2goods` VALUES ('210', 'badcea74-6609-43f9-b047-02fb4bfc955b', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '13');
INSERT INTO `menu2goods` VALUES ('211', 'badcea74-6609-43f9-b047-02fb4bfc955b', '10', '套餐8', '15', '菜品4,菜品5,菜品8', '14');
INSERT INTO `menu2goods` VALUES ('212', 'badcea74-6609-43f9-b047-02fb4bfc955b', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '15');
INSERT INTO `menu2goods` VALUES ('213', 'badcea74-6609-43f9-b047-02fb4bfc955b', '7', '套餐5', '15', '菜品4,菜品5', '16');
INSERT INTO `menu2goods` VALUES ('214', 'badcea74-6609-43f9-b047-02fb4bfc955b', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '17');
INSERT INTO `menu2goods` VALUES ('215', 'badcea74-6609-43f9-b047-02fb4bfc955b', '10', '套餐8', '15', '菜品4,菜品5,菜品8', '18');
INSERT INTO `menu2goods` VALUES ('216', 'badcea74-6609-43f9-b047-02fb4bfc955b', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '19');
INSERT INTO `menu2goods` VALUES ('217', 'badcea74-6609-43f9-b047-02fb4bfc955b', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '20');
INSERT INTO `menu2goods` VALUES ('218', 'badcea74-6609-43f9-b047-02fb4bfc955b', '7', '套餐5', '15', '菜品4,菜品5', '21');
INSERT INTO `menu2goods` VALUES ('219', 'badcea74-6609-43f9-b047-02fb4bfc955b', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '22');
INSERT INTO `menu2goods` VALUES ('220', 'badcea74-6609-43f9-b047-02fb4bfc955b', '7', '套餐5', '15', '菜品4,菜品5', '23');
INSERT INTO `menu2goods` VALUES ('221', 'badcea74-6609-43f9-b047-02fb4bfc955b', '7', '套餐5', '15', '菜品4,菜品5', '24');
INSERT INTO `menu2goods` VALUES ('222', 'badcea74-6609-43f9-b047-02fb4bfc955b', '7', '套餐5', '15', '菜品4,菜品5', '25');
INSERT INTO `menu2goods` VALUES ('223', 'badcea74-6609-43f9-b047-02fb4bfc955b', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '26');
INSERT INTO `menu2goods` VALUES ('224', 'badcea74-6609-43f9-b047-02fb4bfc955b', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '27');
INSERT INTO `menu2goods` VALUES ('225', 'badcea74-6609-43f9-b047-02fb4bfc955b', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '28');
INSERT INTO `menu2goods` VALUES ('238', '5ddf5f38-6745-4abf-91fb-3007c64ab5be', '6', '套餐4', '14', '菜品4,菜品5,菜品8', '1');
INSERT INTO `menu2goods` VALUES ('239', '5ddf5f38-6745-4abf-91fb-3007c64ab5be', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '22');
INSERT INTO `menu2goods` VALUES ('240', '5ddf5f38-6745-4abf-91fb-3007c64ab5be', '8', '套餐6', '18', '菜品4,菜品5,菜品8', '8');
INSERT INTO `menu2goods` VALUES ('241', '5ddf5f38-6745-4abf-91fb-3007c64ab5be', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '15');
INSERT INTO `menu2goods` VALUES ('290', '18c16deb-9b89-4a8d-a622-17307485ef45', '9', '套餐7', '15', '菜品4,菜品13,菜品5,菜品8', '16');
INSERT INTO `menu2goods` VALUES ('291', '18c16deb-9b89-4a8d-a622-17307485ef45', '7', '套餐5', '12.11', '菜品4,菜品5', '2');
INSERT INTO `menu2goods` VALUES ('292', '18c16deb-9b89-4a8d-a622-17307485ef45', '7', '套餐5', '12.11', '菜品4,菜品5', '9');
INSERT INTO `menu2goods` VALUES ('516', '084531cd-f654-4538-b1f3-02247d7be9c0', '25', 'f1', '15', '菜品3,菜品13,菜品12,菜品11,菜品13', '1');
INSERT INTO `menu2goods` VALUES ('517', '084531cd-f654-4538-b1f3-02247d7be9c0', '27', 'f3', '15', '菜品10,菜品9,菜品12,菜品11,菜品9', '2');
INSERT INTO `menu2goods` VALUES ('518', '084531cd-f654-4538-b1f3-02247d7be9c0', '30', 'f5', '15', '菜品4,菜品9,菜品14,菜品11,菜品13', '3');
INSERT INTO `menu2goods` VALUES ('519', '084531cd-f654-4538-b1f3-02247d7be9c0', '28', 'f3', '15', '菜品4,菜品13,菜品12,菜品7,菜品13', '4');
INSERT INTO `menu2goods` VALUES ('520', '084531cd-f654-4538-b1f3-02247d7be9c0', '26', 'f2', '15', '菜品10,菜品13,菜品12,菜品11,菜品13', '5');
INSERT INTO `menu2goods` VALUES ('521', '084531cd-f654-4538-b1f3-02247d7be9c0', '26', 'f2', '15', '菜品10,菜品13,菜品12,菜品11,菜品13', '6');
INSERT INTO `menu2goods` VALUES ('522', '084531cd-f654-4538-b1f3-02247d7be9c0', '28', 'f3', '15', '菜品4,菜品13,菜品12,菜品7,菜品13', '7');
INSERT INTO `menu2goods` VALUES ('523', '084531cd-f654-4538-b1f3-02247d7be9c0', '37', 'fd12', '18', '菜品10,菜品13,菜品12,菜品7,菜品13', '8');
INSERT INTO `menu2goods` VALUES ('524', '084531cd-f654-4538-b1f3-02247d7be9c0', '35', 'fd10', '18', '菜品6,菜品9,菜品5,菜品11,菜品9', '9');
INSERT INTO `menu2goods` VALUES ('525', '084531cd-f654-4538-b1f3-02247d7be9c0', '32', 'fd7', '18', '菜品6,菜品9,菜品12,菜品7,菜品13', '10');
INSERT INTO `menu2goods` VALUES ('526', '084531cd-f654-4538-b1f3-02247d7be9c0', '35', 'fd10', '18', '菜品6,菜品9,菜品5,菜品11,菜品9', '11');
INSERT INTO `menu2goods` VALUES ('527', '084531cd-f654-4538-b1f3-02247d7be9c0', '35', 'fd10', '18', '菜品6,菜品9,菜品5,菜品11,菜品9', '12');
INSERT INTO `menu2goods` VALUES ('528', '084531cd-f654-4538-b1f3-02247d7be9c0', '35', 'fd10', '18', '菜品6,菜品9,菜品5,菜品11,菜品9', '13');
INSERT INTO `menu2goods` VALUES ('529', '084531cd-f654-4538-b1f3-02247d7be9c0', '35', 'fd10', '18', '菜品6,菜品9,菜品5,菜品11,菜品9', '14');
INSERT INTO `menu2goods` VALUES ('530', '084531cd-f654-4538-b1f3-02247d7be9c0', '28', 'f3', '15', '菜品4,菜品13,菜品12,菜品7,菜品13', '15');
INSERT INTO `menu2goods` VALUES ('531', '084531cd-f654-4538-b1f3-02247d7be9c0', '27', 'f3', '15', '菜品10,菜品9,菜品12,菜品11,菜品9', '16');
INSERT INTO `menu2goods` VALUES ('532', '084531cd-f654-4538-b1f3-02247d7be9c0', '28', 'f3', '15', '菜品4,菜品13,菜品12,菜品7,菜品13', '17');
INSERT INTO `menu2goods` VALUES ('533', '084531cd-f654-4538-b1f3-02247d7be9c0', '31', 'f6', '15', '菜品6,菜品13,菜品14,菜品11,菜品9', '18');
INSERT INTO `menu2goods` VALUES ('534', '084531cd-f654-4538-b1f3-02247d7be9c0', '28', 'f3', '15', '菜品4,菜品13,菜品12,菜品7,菜品13', '19');
INSERT INTO `menu2goods` VALUES ('535', '084531cd-f654-4538-b1f3-02247d7be9c0', '25', 'f1', '15', '菜品3,菜品13,菜品12,菜品11,菜品13', '20');
INSERT INTO `menu2goods` VALUES ('536', '084531cd-f654-4538-b1f3-02247d7be9c0', '27', 'f3', '15', '菜品10,菜品9,菜品12,菜品11,菜品9', '21');
INSERT INTO `menu2goods` VALUES ('537', '084531cd-f654-4538-b1f3-02247d7be9c0', '36', 'fd11', '18', '菜品4,菜品9,菜品14,菜品7,菜品13', '22');
INSERT INTO `menu2goods` VALUES ('538', '084531cd-f654-4538-b1f3-02247d7be9c0', '36', 'fd11', '18', '菜品4,菜品9,菜品14,菜品7,菜品13', '23');
INSERT INTO `menu2goods` VALUES ('539', '084531cd-f654-4538-b1f3-02247d7be9c0', '33', 'fd8', '18', '菜品10,菜品13,菜品12,菜品11,菜品13', '24');
INSERT INTO `menu2goods` VALUES ('540', '084531cd-f654-4538-b1f3-02247d7be9c0', '35', 'fd10', '18', '菜品6,菜品9,菜品5,菜品11,菜品9', '25');
INSERT INTO `menu2goods` VALUES ('541', '084531cd-f654-4538-b1f3-02247d7be9c0', '36', 'fd11', '18', '菜品4,菜品9,菜品14,菜品7,菜品13', '26');
INSERT INTO `menu2goods` VALUES ('542', '084531cd-f654-4538-b1f3-02247d7be9c0', '36', 'fd11', '18', '菜品4,菜品9,菜品14,菜品7,菜品13', '27');
INSERT INTO `menu2goods` VALUES ('543', '084531cd-f654-4538-b1f3-02247d7be9c0', '37', 'fd12', '18', '菜品10,菜品13,菜品12,菜品7,菜品13', '28');

-- ----------------------------
-- Table structure for menus
-- ----------------------------
DROP TABLE IF EXISTS `menus`;
CREATE TABLE `menus` (
  `menu_id` varchar(100) NOT NULL COMMENT '‘菜单id’',
  `menu_date` date DEFAULT NULL COMMENT '‘菜单周次’ (该周的星期一)    xxxx-yy-zz(年月日)',
  `school_id` int(11) DEFAULT NULL,
  `school_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menus
-- ----------------------------
INSERT INTO `menus` VALUES ('084531cd-f654-4538-b1f3-02247d7be9c0', '2019-03-04', '1', '武汉科技大学');
INSERT INTO `menus` VALUES ('11cc91a0-d7b9-408c-a647-6c9fdadb8b9c', '2018-06-04', '2', '学校2');
INSERT INTO `menus` VALUES ('18c16deb-9b89-4a8d-a622-17307485ef45', '2018-06-11', '3', '学校3');
INSERT INTO `menus` VALUES ('2', '2018-06-04', '2', '学校2');
INSERT INTO `menus` VALUES ('2fc1531e-a2a2-4a2b-9c75-a7ea5755475f', '2018-06-04', '3', '学校3');
INSERT INTO `menus` VALUES ('348b0828-cbd1-485c-932e-79ec1da4d275', '2018-06-04', '2', '学校2');
INSERT INTO `menus` VALUES ('42b0339c-e05f-47e8-ae2a-5e1e3af51d02', '2018-06-04', '1', '学校1');
INSERT INTO `menus` VALUES ('435e0c3e-9bc6-4f29-becc-dc2bbee3904b', '2018-06-11', '4', '学校4');
INSERT INTO `menus` VALUES ('52ee57c3-d1e4-489e-9094-90fbf13d4b6c', '2018-06-04', '6', '学校6');
INSERT INTO `menus` VALUES ('5ddf5f38-6745-4abf-91fb-3007c64ab5be', '2018-06-04', '8', '学校8');
INSERT INTO `menus` VALUES ('7e9976ff-e6e1-4275-b183-669a83f5e11f', '2018-06-11', '10', '学校10');
INSERT INTO `menus` VALUES ('80405758-971b-432b-8ceb-a45d10319b80', '2018-06-11', '14', '学校654123231');
INSERT INTO `menus` VALUES ('81827582-020d-47e0-84da-e0fa3abc7e92', '2018-06-04', '6', '学校6');
INSERT INTO `menus` VALUES ('87a112c3-6ca2-4011-a8a7-03054c706959', '2018-06-11', '8', '学校8');
INSERT INTO `menus` VALUES ('92e0ab36-bab2-4d2e-807f-4ca0f551f90c', '2018-06-11', '8', '学校8');
INSERT INTO `menus` VALUES ('95df32cb-652b-4855-96fb-42aba0aa6352', '2018-06-04', '4', '学校4');
INSERT INTO `menus` VALUES ('a26f98dd-bdac-4e55-a1a3-4bb5ae5cbe70', '2018-06-04', '5', '学校5');
INSERT INTO `menus` VALUES ('bc583a92-cd8c-42b2-b6f7-479255f2b905', '2018-06-11', '11', '学校11');
INSERT INTO `menus` VALUES ('d753094f-7e17-4ea1-a4cf-b3e1fd5bf2cb', '2018-06-04', '1', '学校1');
INSERT INTO `menus` VALUES ('d84aacff-452c-411c-bad8-43e64a3eaea0', '2018-06-04', '6', '学校6');
INSERT INTO `menus` VALUES ('da927150-a13a-4479-a557-9514ade76916', '2018-06-04', '2', '学校2');
INSERT INTO `menus` VALUES ('de0b2080-c7ea-4a50-a737-16bfddc7ae43', '2018-06-04', '5', '学校5');
INSERT INTO `menus` VALUES ('de4c2560-886c-4c35-aa23-b3a876c57265', '2018-06-04', '7', '学校7');
INSERT INTO `menus` VALUES ('fbce7831-f607-4438-81d1-99eca70e5f0c', '2018-06-11', '4', '学校4');

-- ----------------------------
-- Table structure for messages
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(100) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL COMMENT '留言 （不超过60字',
  `message_date` datetime DEFAULT NULL COMMENT '留言时间',
  `status` int(1) DEFAULT '0' COMMENT '留言的处理情况  0-未处理 1-已处理',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of messages
-- ----------------------------
INSERT INTO `messages` VALUES ('13', '1', '饭是在是太难吃了', '2018-05-01 11:58:21', '1');
INSERT INTO `messages` VALUES ('14', '1', '饭是在是太难吃了', '2018-05-01 11:58:21', '1');
INSERT INTO `messages` VALUES ('15', '1', '饭是在是太难吃了', '2018-05-01 11:58:21', '1');
INSERT INTO `messages` VALUES ('16', '1', '饭是在是太难吃了', '2018-05-01 11:58:21', '1');
INSERT INTO `messages` VALUES ('17', '1', '饭是在是太难吃了', '2018-05-01 11:58:21', '1');
INSERT INTO `messages` VALUES ('18', '1', '饭是在是太难吃了', '2018-05-01 11:58:21', '0');
INSERT INTO `messages` VALUES ('19', '1', '饭是在是太难吃了', '2018-05-01 11:58:21', '0');
INSERT INTO `messages` VALUES ('20', '1', '饭是在是太难吃了', '2018-05-01 11:58:21', '0');
INSERT INTO `messages` VALUES ('21', '1', '饭是在是太难吃了', '2018-05-01 11:58:21', '0');
INSERT INTO `messages` VALUES ('22', '1', '饭是在是太难吃了', '2018-05-01 11:58:21', '0');
INSERT INTO `messages` VALUES ('23', '1', '饭是在是太难吃了', '2018-05-01 11:58:21', '0');
INSERT INTO `messages` VALUES ('24', '1', '饭是在是太难吃了', '2018-05-01 11:58:21', '0');
INSERT INTO `messages` VALUES ('25', '1', '饭是在是太难吃了', '2018-05-01 11:58:21', '0');
INSERT INTO `messages` VALUES ('26', '1', '饭是在是太难吃了', '2018-05-01 11:58:21', '1');
INSERT INTO `messages` VALUES ('27', '3', '你家做的菜是相当好吃啊！', '2018-06-10 14:36:18', '0');
INSERT INTO `messages` VALUES ('28', '3', '你们家的菜做的还可以吧', '2018-06-10 14:47:30', '1');
INSERT INTO `messages` VALUES ('29', '3', '后端做得怎样了?', '2018-06-14 14:19:54', '1');
INSERT INTO `messages` VALUES ('30', '3', '444', '2018-07-20 23:03:08', '1');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `news_title` varchar(255) NOT NULL,
  `news_content` text NOT NULL,
  `release_time` varchar(100) NOT NULL,
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('4', '为了回馈小主们，我们坚持追求用心', '为了回馈小主们，我们坚持追求用心', '2018-06-04 14:42:30');
INSERT INTO `news` VALUES ('5', '打心底觉得吃的安全，放心', '让小主们看到我们的努力，打心底觉得吃的安全，放心', '2018-06-04 14:43:33');
INSERT INTO `news` VALUES ('6', '高峰期提前下单，保障用餐时间', '<p>高峰期提前下单，保障用餐时间22</p>', '2018-06-09 12:28:27');
INSERT INTO `news` VALUES ('7', '下雨天稍有延误请谅解', '下雨天稍有延误请谅解。', '2018-06-04 14:52:41');
INSERT INTO `news` VALUES ('10', 'dsaf', 'dsafdsaf', '2018-06-08 21:37:44');
INSERT INTO `news` VALUES ('11', '纷纷赶到', '<p>史蒂夫噶111</p>', '2018-06-09 12:27:22');

-- ----------------------------
-- Table structure for order4week
-- ----------------------------
DROP TABLE IF EXISTS `order4week`;
CREATE TABLE `order4week` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `customer_name` varchar(100) DEFAULT NULL,
  `school_id` int(11) DEFAULT NULL,
  `customer_grade` int(11) DEFAULT NULL COMMENT '限定1，2，3',
  `customer_class` int(11) DEFAULT NULL,
  `order_delivery` date DEFAULT NULL,
  `order4week_price` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order4week
-- ----------------------------
INSERT INTO `order4week` VALUES ('1', '1', '用户1', '1', '3', '2', '2018-06-11', '15,18,15,18,15,15,15,18,15,15,0,0,15,18');
INSERT INTO `order4week` VALUES ('3', '3', '用户3', '1', '3', '2', '2018-06-11', '18,0,15,18,15,15,15,18,15,15,0,0,15,18');
INSERT INTO `order4week` VALUES ('4', '4', '用户4', '1', '3', '2', '2018-06-11', '18,0,15,18,15,15,15,18,15,15,0,0,15,18');
INSERT INTO `order4week` VALUES ('5', '3', '用户3', '1', '3', '2', '2018-06-11', '18,0,15,18,15,15,15,18,15,15,0,0,15,18');
INSERT INTO `order4week` VALUES ('6', '3', '用户3', '1', '3', '2', '2018-06-11', '18,0,15,18,15,15,15,18,15,15,0,0,15,18');
INSERT INTO `order4week` VALUES ('7', '3', '用户3', '1', '3', '2', '2018-06-11', '18,0,15,18,15,15,15,18,15,15,0,0,15,18');
INSERT INTO `order4week` VALUES ('8', '3', '用户3', '1', '3', '2', '2018-06-11', '18,0,15,18,15,15,15,18,15,15,0,0,15,18');
INSERT INTO `order4week` VALUES ('9', '3', '用户3', '1', '3', '2', '2018-06-11', '18,0,15,18,15,15,15,18,15,15,0,0,15,18');
INSERT INTO `order4week` VALUES ('10', '3', '用户3', '1', '3', '2', '2018-06-11', '18,0,15,18,15,15,15,18,15,15,0,0,15,18');
INSERT INTO `order4week` VALUES ('11', '3', '用户3', '1', '3', '2', '2018-06-11', '18,0,15,18,15,15,15,18,15,15,0,0,15,18');
INSERT INTO `order4week` VALUES ('12', '3', '用户3', '1', '3', '2', '2018-06-11', '18,0,15,18,15,15,15,18,15,15,0,0,15,18');
INSERT INTO `order4week` VALUES ('13', '3', '用户3', '1', '3', '2', '2018-06-11', '18,0,15,18,15,15,15,18,15,15,0,0,15,18');
INSERT INTO `order4week` VALUES ('14', '3', '张洋', '1', '3', '2', '2018-06-11', '15.0,15.0,');
INSERT INTO `order4week` VALUES ('15', '3', '张洋', '1', '3', '2', '2018-06-11', '15.0,18.0');
INSERT INTO `order4week` VALUES ('16', '3', '张洋', '1', '3', '2', '2018-06-11', '15.0');
INSERT INTO `order4week` VALUES ('17', '3', '张洋', '1', '3', '2', '2018-06-11', '15.0,15.0');
INSERT INTO `order4week` VALUES ('18', '3', '张洋', '1', '3', '2', '2018-06-11', '15.0');
INSERT INTO `order4week` VALUES ('19', '15', '3', '1', '3', '2', '2018-06-11', '15.0');
INSERT INTO `order4week` VALUES ('20', '3', '张洋', '1', '3', '2', '2018-06-11', '15.0,15.0');
INSERT INTO `order4week` VALUES ('21', '30', '3', '1', '3', '2', '2018-06-11', '15.0,15.0');
INSERT INTO `order4week` VALUES ('22', '3', '张洋', '1', '3', '2', '2018-06-11', '18.0,15.0');
INSERT INTO `order4week` VALUES ('23', '33', '3', '1', '3', '2', '2018-06-11', '18.0,15.0');
INSERT INTO `order4week` VALUES ('24', '3', '张洋', '1', '3', '2', '2018-06-11', '18.0,18.0');
INSERT INTO `order4week` VALUES ('25', '36', '3', '1', '3', '2', '2018-06-11', '18.0,18.0');
INSERT INTO `order4week` VALUES ('26', '3', '张洋', '1', '3', '2', '2018-06-11', '15.0,18.0');
INSERT INTO `order4week` VALUES ('27', '3', '张洋', '1', '3', '2', '2018-06-11', '15.0');
INSERT INTO `order4week` VALUES ('28', '3', '张洋', '1', '3', '2', '2018-06-11', '18.0');
INSERT INTO `order4week` VALUES ('29', '3', '张洋', '1', '3', '2', '2018-06-11', '18.0,15.0,15.0');
INSERT INTO `order4week` VALUES ('30', '3', '张洋', '1', '3', '2', '2018-06-11', '15.0,18.0');
INSERT INTO `order4week` VALUES ('31', '3', '张洋', '1', '3', '2', '2018-06-11', '15.0');
INSERT INTO `order4week` VALUES ('32', '3', '张洋', '1', '3', '2', '2018-06-11', '18.0');
INSERT INTO `order4week` VALUES ('33', '3', '张洋', '1', '3', '2', '2018-06-25', '15.0,18.0,18.0');
INSERT INTO `order4week` VALUES ('34', '3', '张洋', '1', '3', '2', '2018-07-16', '18.0,18.0,15.0');
INSERT INTO `order4week` VALUES ('35', '3', '张洋', '1', '3', '2', '2019-03-04', '18.0,18.0,15.0,15.0,18.0');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `school_id` int(11) DEFAULT NULL,
  `customer_grade` int(11) DEFAULT NULL COMMENT '限定1，2，3',
  `customer_class` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `customer_num` varchar(100) DEFAULT NULL,
  `goods_price` double DEFAULT NULL,
  `product_name` varchar(266) DEFAULT NULL,
  `order_delivery` date DEFAULT NULL,
  `delivery_date` int(255) DEFAULT NULL COMMENT '限定1，2',
  `order_date` datetime DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL COMMENT '限定1，2，3，4',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('6', '36', '1', '3', '2', '3', '201513137016', '18', '菜品4,菜品9,菜品14,菜品7,菜品13', '2018-06-11', '2', '2018-06-12 00:00:00', '0');
INSERT INTO `orders` VALUES ('7', '35', '1', '3', '2', '3', '201513137016', '18', '菜品6,菜品9,菜品5,菜品11,菜品9', '2018-06-12', '1', '2018-06-12 00:00:00', '2');
INSERT INTO `orders` VALUES ('8', '36', '1', '3', '2', '3', '201513137016', '18', '菜品4,菜品9,菜品14,菜品7,菜品13', '2018-06-12', '2', '2018-06-12 00:00:00', '0');
INSERT INTO `orders` VALUES ('9', '30', '1', '3', '2', '3', '201513137016', '15', '菜品4,菜品9,菜品14,菜品11,菜品13', '2018-06-13', '1', '2018-06-12 00:00:00', '0');
INSERT INTO `orders` VALUES ('10', '28', '1', '3', '2', '3', '201513137016', '15', '菜品4,菜品13,菜品12,菜品7,菜品13', '2018-06-13', '2', '2018-06-12 00:00:00', '2');
INSERT INTO `orders` VALUES ('13', '28', '1', '3', '2', '3', '201513137016', '15', '菜品4,菜品13,菜品12,菜品7,菜品13', '2018-06-11', '2', '2018-06-14 15:27:36', '2');
INSERT INTO `orders` VALUES ('14', '35', '1', '3', '2', '3', '201513137016', '18', '菜品6,菜品9,菜品5,菜品11,菜品9', '2018-06-14', '1', '2018-06-14 15:32:47', '2');
INSERT INTO `orders` VALUES ('15', '31', '1', '3', '2', '3', '201513137016', '15', '菜品6,菜品13,菜品14,菜品11,菜品9', '2018-06-14', '2', '2018-06-14 15:32:47', '2');
INSERT INTO `orders` VALUES ('16', '35', '1', '3', '2', '3', '201513137016', '18', '菜品6,菜品9,菜品5,菜品11,菜品9', '2018-06-17', '1', '2018-06-14 15:35:52', '2');
INSERT INTO `orders` VALUES ('17', '37', '1', '3', '2', '3', '201513137016', '18', '菜品10,菜品13,菜品12,菜品7,菜品13', '2018-06-17', '2', '2018-06-14 15:35:52', '2');
INSERT INTO `orders` VALUES ('18', '26', '1', '3', '2', '3', '201513137016', '15', '菜品10,菜品13,菜品12,菜品11,菜品13', '2018-06-15', '1', '2018-06-14 15:39:38', '0');
INSERT INTO `orders` VALUES ('19', '36', '1', '3', '2', '3', '201513137016', '18', '菜品4,菜品9,菜品14,菜品7,菜品13', '2018-06-15', '2', '2018-06-14 15:39:38', '2');
INSERT INTO `orders` VALUES ('20', '30', '1', '3', '2', '3', '201513137016', '15', '菜品4,菜品9,菜品14,菜品11,菜品13', '2018-06-13', '1', '2018-06-14 15:58:20', '2');
INSERT INTO `orders` VALUES ('21', '37', '1', '3', '2', '3', '123456', '18', '菜品10,菜品13,菜品12,菜品7,菜品13', '2018-06-17', '2', '2018-06-14 15:59:52', '0');
INSERT INTO `orders` VALUES ('22', '35', '1', '3', '2', '3', '123456', '18', '菜品6,菜品9,菜品5,菜品11,菜品9', '2018-06-12', '1', '2018-06-14 16:11:29', '0');
INSERT INTO `orders` VALUES ('23', '27', '1', '3', '2', '3', '123456', '15', '菜品10,菜品9,菜品12,菜品11,菜品9', '2018-06-12', '2', '2018-06-14 16:11:29', '2');
INSERT INTO `orders` VALUES ('24', '28', '1', '3', '2', '3', '123456', '15', '菜品4,菜品13,菜品12,菜品7,菜品13', '2018-06-15', '2', '2018-06-14 16:11:29', '0');
INSERT INTO `orders` VALUES ('25', '28', '1', '3', '2', '3', '123456', '15', '菜品4,菜品13,菜品12,菜品7,菜品13', '2018-06-11', '2', '2018-06-14 16:28:56', '0');
INSERT INTO `orders` VALUES ('26', '35', '1', '3', '2', '3', '123456', '18', '菜品6,菜品9,菜品5,菜品11,菜品9', '2018-06-15', '1', '2018-06-14 16:28:56', '2');
INSERT INTO `orders` VALUES ('27', '31', '1', '3', '2', '3', '123456', '15', '菜品6,菜品13,菜品14,菜品11,菜品9', '2018-06-14', '2', '2018-06-14 16:39:50', '0');
INSERT INTO `orders` VALUES ('28', '35', '1', '3', '2', '3', '123456', '18', '菜品6,菜品9,菜品5,菜品11,菜品9', '2018-06-15', '1', '2018-06-14 17:47:33', '2');
INSERT INTO `orders` VALUES ('29', '28', '1', '3', '2', '3', '123456', '15', '菜品4,菜品13,菜品12,菜品7,菜品13', '2018-06-25', '2', '2018-06-26 23:37:51', '0');
INSERT INTO `orders` VALUES ('30', '32', '1', '3', '2', '3', '123456', '18', '菜品6,菜品9,菜品12,菜品7,菜品13', '2018-06-27', '1', '2018-06-26 23:37:51', '0');
INSERT INTO `orders` VALUES ('31', '35', '1', '3', '2', '3', '123456', '18', '菜品6,菜品9,菜品5,菜品11,菜品9', '2018-06-29', '1', '2018-06-26 23:37:51', '2');
INSERT INTO `orders` VALUES ('32', '32', '1', '3', '2', '3', '123456', '18', '菜品6,菜品9,菜品12,菜品7,菜品13', '2018-07-18', '1', '2018-07-20 23:04:10', '2');
INSERT INTO `orders` VALUES ('33', '35', '1', '3', '2', '3', '123456', '18', '菜品6,菜品9,菜品5,菜品11,菜品9', '2018-07-20', '1', '2018-07-20 23:04:10', '0');
INSERT INTO `orders` VALUES ('34', '28', '1', '3', '2', '3', '123456', '15', '菜品4,菜品13,菜品12,菜品7,菜品13', '2018-07-20', '2', '2018-07-20 23:04:10', '2');
INSERT INTO `orders` VALUES ('35', '37', '1', '3', '2', '3', '123456', '18', '菜品10,菜品13,菜品12,菜品7,菜品13', '2019-03-04', '1', '2019-03-04 15:40:40', '2');
INSERT INTO `orders` VALUES ('36', '35', '1', '3', '2', '3', '123456', '18', '菜品6,菜品9,菜品5,菜品11,菜品9', '2019-03-07', '2', '2019-03-04 15:40:40', '2');
INSERT INTO `orders` VALUES ('37', '26', '1', '3', '2', '3', '123456', '15', '菜品10,菜品13,菜品12,菜品11,菜品13', '2019-03-09', '1', '2019-03-04 15:40:40', '2');
INSERT INTO `orders` VALUES ('38', '25', '1', '3', '2', '3', '123456', '15', '菜品3,菜品13,菜品12,菜品11,菜品13', '2019-03-09', '2', '2019-03-04 15:40:40', '2');
INSERT INTO `orders` VALUES ('39', '37', '1', '3', '2', '3', '123456', '18', '菜品10,菜品13,菜品12,菜品7,菜品13', '2019-03-10', '2', '2019-03-04 15:40:40', '2');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `product_id` int(100) NOT NULL AUTO_INCREMENT COMMENT '菜品id',
  `product_name` varchar(100) DEFAULT NULL COMMENT '菜品名',
  `product_type` varchar(10) DEFAULT NULL COMMENT '''1.主荤 ， 2.配荤 ， 3.素菜 ， 4.饮品水果',
  `product_sign` varchar(10) DEFAULT NULL COMMENT '‘菜品有效期’  (自定义菜单字段',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('4', '菜品3', '1', '1');
INSERT INTO `products` VALUES ('50', '菜品4', '1', '1');
INSERT INTO `products` VALUES ('51', '菜品5', '3', '1');
INSERT INTO `products` VALUES ('53', '菜品6', '1', '1');
INSERT INTO `products` VALUES ('54', '菜品7', '4', '1');
INSERT INTO `products` VALUES ('56', '菜品9', '2', '1');
INSERT INTO `products` VALUES ('57', '菜品10', '1', '1');
INSERT INTO `products` VALUES ('58', '菜品11', '4', '1');
INSERT INTO `products` VALUES ('59', '菜品12', '3', '1');
INSERT INTO `products` VALUES ('60', '菜品13', '2', '1');
INSERT INTO `products` VALUES ('61', '菜品14', '3', '1');

-- ----------------------------
-- Table structure for rules
-- ----------------------------
DROP TABLE IF EXISTS `rules`;
CREATE TABLE `rules` (
  `rules_id` int(11) NOT NULL,
  `order_time` int(11) NOT NULL,
  `order_times` int(11) NOT NULL,
  `change_time` int(11) NOT NULL,
  `change_times` int(11) NOT NULL,
  `return_time` int(11) NOT NULL,
  `return_times` int(11) NOT NULL,
  PRIMARY KEY (`rules_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rules
-- ----------------------------
INSERT INTO `rules` VALUES ('1', '5', '5', '5', '5', '5', '5');
INSERT INTO `rules` VALUES ('2', '10', '10', '10', '10', '10', '10');

-- ----------------------------
-- Table structure for schools
-- ----------------------------
DROP TABLE IF EXISTS `schools`;
CREATE TABLE `schools` (
  `school_id` int(11) NOT NULL AUTO_INCREMENT,
  `school_name` varchar(100) DEFAULT NULL,
  `school_phone` varchar(20) DEFAULT NULL,
  `school_address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schools
-- ----------------------------
INSERT INTO `schools` VALUES ('1', '武汉科技大学', '111', '白沙洲大道');
INSERT INTO `schools` VALUES ('2', '学校2', '12313', '武汉');
INSERT INTO `schools` VALUES ('3', '学校3', '1231', '学校');
INSERT INTO `schools` VALUES ('4', '学校4', '12312', '学校');
INSERT INTO `schools` VALUES ('5', '学校5', '1231231', '学校');
INSERT INTO `schools` VALUES ('6', '学校6', '12124', '学校');
INSERT INTO `schools` VALUES ('7', '学校7', '12123124', '学校');
INSERT INTO `schools` VALUES ('8', '学校8', '123124', '学校');
INSERT INTO `schools` VALUES ('9', '学校9', '12312312', '学校');
