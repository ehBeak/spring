create table MEMBER_INFO (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 email VARCHAR(255),
 password VARCHAR(64),
 name VARCHAR(30),
 create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
 role TINYINT DEFAULT 3,
 INDEX (email)
)engine=InnoDB default character set = utf8;