DROP DATABASE IF EXISTS db_javaee_library;
CREATE DATABASE db_javaee_library;

DROP TABLE IF EXISTS db_javaee_library.user;
CREATE TABLE db_javaee_library.user (
  id       INT AUTO_INCREMENT PRIMARY KEY
  COMMENT 'ID PK',
  username VARCHAR(255) UNIQUE NOT NULL COMMENT '用户名',
  password VARCHAR(255) NOT NULL COMMENT '密码',
  role VARCHAR(255) NOT NULL DEFAULT '用户' COMMENT '角色：用户；管理员'
)COMMENT '用户表';

INSERT INTO db_javaee_library.user VALUE (null,'admin','123','管理员');

SELECT *
FROM db_javaee_library.user;