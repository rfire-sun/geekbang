DROP TABLE IF EXISTS `user01`;
CREATE TABLE `user01` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user01
-- ----------------------------
INSERT INTO `user01` VALUES ('1', 'wangxin', '99');

