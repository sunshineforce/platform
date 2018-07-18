create database inspect;

DROP TABLE IF EXISTS t_license;
CREATE TABLE t_license(
id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键Id',
license_type_id INT COMMENT '证照类型Id',
licence_name VARCHAR(30) COMMENT '证件名称',
number VARCHAR(30) COMMENT '证件编号',
expire_date DATE COMMENT '证件到期时间',
url VARCHAR(300) COMMENT '证件url地址',
create_time DATETIME DEFAULT NOW() COMMENT '创建时间',
creator INT COMMENT '创建人',
PRIMARY KEY(id),
KEY idx_licence_name(licence_name)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='证照表';


DROP TABLE IF EXISTS t_license_type;
CREATE TABLE t_license_type(
id INT NOT NULL AUTO_INCREMENT COMMENT '主键Id',
license_type VARCHAR(20) NOT NULL COMMENT '证照类型名称',
remark VARCHAR(100) COMMENT '备注',
create_time DATETIME DEFAULT NOW() COMMENT '创建时间',
creator INT COMMENT '创建人Id',
PRIMARY KEY (id),
KEY idx_license_type(license_type)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='证照类型表';


DROP TABLE IF EXISTS t_material;
CREATE TABLE t_material(
id INT NOT NULL AUTO_INCREMENT COMMENT '主键Id',
QR_code VARCHAR(200) COMMENT '二维码',
material_name VARCHAR(50) COMMENT '物品名称',
location VARCHAR(50) COMMENT '所在位置',
material_type_id INT COMMENT '物品类型Id',
produced_date DATE COMMENT '生产日期',
expire_date DATE COMMENT '到期时间',
check_date DATE COMMENT '最近检查时间',
material_status TINYINT COMMENT '物品状态（0：正常；1：报废；2：异常）',
material_owner VARCHAR(50) COMMENT '所属企业',
create_time DATETIME DEFAULT NOW() COMMENT '创建时间',
creator INT COMMENT '创建人Id',
PRIMARY KEY(id),
KEY idx_material_name(material_name)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='物品表';


DROP TABLE IF EXISTS t_material_type;
CREATE TABLE t_material_type(
id INT NOT NULL AUTO_INCREMENT COMMENT '主键Id',
material_type_name VARCHAR(50) COMMENT '物品名称',
parent_id INT COMMENT '上级类目Id',
remark VARCHAR(200) COMMENT '备注',
create_time DATETIME DEFAULT NOW() COMMENT '创建时间',
creator INT COMMENT '创建人',
update_time DATETIME DEFAULT NOW() COMMENT '最后修改时间',
updator INT COMMENT '最后修改人',
PRIMARY KEY(id),
KEY idx_material_type_name(material_type_name)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='物品类型表';


DROP TABLE IF EXISTS t_qr_code_apply;
CREATE TABLE t_qr_code_apply(
id INT NOT NULL AUTO_INCREMENT COMMENT '主键Id',
qr_code_type TINYINT COMMENT '二维码类型（0：物品二维码；1：企业二维码）',
batch_no VARCHAR(30) COMMENT '申请批次号',
quantity INT NOT NULL COMMENT '二维码生成数量',
prefix VARCHAR(20) COMMENT '二维前缀',
applicant INT(22) COMMENT '申请人',
apply_date DATETIME DEFAULT NOW() COMMENT '申请时间',
qr_code_status TINYINT COMMENT '状态（0：审核中；1：已发放）',
reject_reason VARCHAR(100) COMMENT '驳回原因',
generate_man INT COMMENT '生成人员',
generate_date DATETIME COMMENT '生成时间',
bind_man INT COMMENT '绑定人员',
bind_date DATETIME COMMENT '绑定时间',
matetial_id INT COMMENT '物料Id',
material_type_id INT COMMENT '物品类型',
create_date DATETIME COMMENT '创建时间',
PRIMARY KEY(id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='二维码申请表';


DROP TABLE IF EXISTS t_check_specific;
CREATE TABLE t_check_specific(
id INT NOT NULL AUTO_INCREMENT COMMENT '主键Id',
specific_name VARCHAR(30) NOT NULL COMMENT '规范名称',
remark VARCHAR(100) COMMENT '备注',
check_item VARCHAR(2000) COMMENT '检查项',
create_time DATETIME COMMENT '创建时间',
creator INT COMMENT '创建者',
update_time DATETIME COMMENT '最后修改人',
updator INT COMMENT '最后修改人',
PRIMARY KEY(id),
KEY idx_specific_name(specific_name)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='检查规范表';