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
check_item VARCHAR(3000) COMMENT '检查项',
create_time DATETIME COMMENT '创建时间',
creator INT COMMENT '创建者',
update_time DATETIME COMMENT '最后修改人',
updator INT COMMENT '最后修改人',
PRIMARY KEY(id),
KEY idx_specific_name(specific_name)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='检查规范表';


DROP TABLE IF EXISTS t_expert;

/*==============================================================*/
/* Table: t_expert                                              */
/*==============================================================*/
CREATE TABLE t_expert
(
   id                   INT NOT NULL AUTO_INCREMENT COMMENT '主键',
   expert_name          VARCHAR(5) COMMENT '专家名称',
   job_title            VARCHAR(20) COMMENT '职称',
   mobile               VARCHAR(13) COMMENT '手机号',
   skill                VARCHAR(50) COMMENT '擅长领域',
   avatar               VARCHAR(200) COMMENT '头像',
   create_time          DATETIME COMMENT '创建时间',
   creator              INT COMMENT '创建人',
   update_time          DATETIME COMMENT '修改时间',
   updator              INT COMMENT '修改人',
   PRIMARY KEY (id),
   KEY idx_expert_name(expert_name)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='专家库表';


DROP TABLE IF EXISTS t_regulation;

/*==============================================================*/
/* Table: t_regulation                                          */
/*==============================================================*/
CREATE TABLE t_regulation
(
   id                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键Id',
   knowledge_name       VARCHAR(30) COMMENT '知识名称',
   description          VARCHAR(50) COMMENT '描述',
   url                  VARCHAR(200) COMMENT '预览图片url',
   TYPE                 TINYINT COMMENT '知识内容方式(0：自主编辑；1：外部链接)',
   content              TEXT COMMENT '自主编辑知识内容',
   link                 VARCHAR(200) COMMENT '知识外部链接地址',
   relation             VARCHAR(200) COMMENT '关联考试',
   create_time          DATETIME COMMENT '创建时间',
   creator              INT COMMENT '创建人',
   update_time          DATETIME COMMENT '更改时间',
   updator              INT COMMENT '修改人',
   PRIMARY KEY (id),
   KEY idx_knowledge_name(knowledge_name)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='法规管理表';


DROP TABLE IF EXISTS t_exam;

/*==============================================================*/
/* Table: t_exam                                                */
/*==============================================================*/
CREATE TABLE t_exam
(
   id                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键Id',
   exam_name            VARCHAR(300) NOT NULL COMMENT '试题名称',
   introduce            VARCHAR(300) COMMENT '试题介绍',
   begin_time           DATETIME COMMENT '开始时间',
   end_time             DATETIME COMMENT '结束时间',
   member               INT COMMENT '参考人员',
   create_time          DATETIME COMMENT '创建时间',
   creator              INT COMMENT '创建人',
   update_time          DATETIME COMMENT '修改时间',
   updator              INT COMMENT '修改人',
   enabled              TINYINT COMMENT '是否启用（0：启用；1：禁用）',
   PRIMARY KEY (id),
   KEY idx_exam_name(exam_name)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='试题管理表';


DROP TABLE IF EXISTS t_exam_member;

/*==============================================================*/
/* Table: t_exam_member                                         */
/*==============================================================*/
CREATE TABLE t_exam_member
(
   id                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
   exam_id              INT COMMENT '试题Id',
   member_id            INT COMMENT '参考人员Id',
   PRIMARY KEY (id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='考试参考人员';


DROP TABLE IF EXISTS t_exam_question;

/*==============================================================*/
/* Table: t_exam_question                                       */
/*==============================================================*/
CREATE TABLE t_exam_question
(
   id                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
   exam_id              INT COMMENT '试题Id',
   score                DOUBLE COMMENT '题目分值',
   question             TEXT COMMENT '题目',
   answer               VARCHAR(20) COMMENT '答案',
   item                 INT COMMENT '题目选项',
   create_time          DATETIME COMMENT '创建时间',
   creator              INT COMMENT '创建人',
   PRIMARY KEY (id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='试题题目表';



DROP TABLE IF EXISTS t_question_item;

/*==============================================================*/
/* Table: t_question_item                                       */
/*==============================================================*/
CREATE TABLE t_question_item
(
   id                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键Id',
   question_id          BIGINT COMMENT '题目Id',
   item                 VARCHAR(50) COMMENT '题目选项',
   PRIMARY KEY (id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='试题选项表';



DROP TABLE IF EXISTS t_enterprice;

/*==============================================================*/
/* Table: t_enterprice                                          */
/*==============================================================*/
CREATE TABLE t_enterprice
(
   id                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键Id',
   enterprice_name      VARCHAR(50) NOT NULL COMMENT '企业名称',
   mobile               VARCHAR(13) NOT NULL COMMENT '联系电话',
   OWNER                INT COMMENT '负责人',
   address              VARCHAR(50) COMMENT '地址',
   account              VARCHAR(20) COMMENT '账号',
   PASSWORD             VARCHAR(20) COMMENT '密码',
   create_time          DATETIME COMMENT '创建时间',
   creator              INT COMMENT '创建人',
   update_time          DATETIME COMMENT '更新时间',
   updator              INT COMMENT '更新人',
   enabled               TINYINT COMMENT '是否启用（0：启用；1：禁用）',
   PRIMARY KEY(id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='企业信息表';


DROP TABLE IF EXISTS t_report_log;

/*==============================================================*/
/* Table: t_report_log                                          */
/*==============================================================*/
CREATE TABLE t_report_log
(
   id                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键Id',
   event_no             VARCHAR(20) COMMENT '事件编号',
   result               TINYINT COMMENT '推送结果(0:成功;1:失败;3:异常)',
   alarm_location       VARCHAR(30) COMMENT '告警位置',
   localtion_tag        VARCHAR(10) COMMENT '位置标签',
   device_type          INT COMMENT '设备类型',
   customer_id          INT COMMENT '客户id',
   send_time            DATETIME COMMENT '发送时间/告警时间',
   alarm_floor          VARCHAR(10) COMMENT '所在楼层',
   install_local        VARCHAR(1000) COMMENT '安装位置',
   send_text            TEXT COMMENT '发起内容',
   classify             INT COMMENT '上报类型',
   create_time          DATETIME COMMENT '创建时间',
   PRIMARY KEY (id),
   KEY idx_device_type(device_type)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='上报记录表';


DROP TABLE IF EXISTS t_check_details;

/*==============================================================*/
/* Table: t_check_details                                       */
/*==============================================================*/
CREATE TABLE t_check_details
(
   id                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键Id',
   matetial_id          BIGINT COMMENT '物品Id',
   check_time           DATETIME COMMENT '检查时间',
   checkor              INT COMMENT '检查人',
   result               TINYINT COMMENT '检查结果(0:正常;1:异常;2:报废)',
   description          VARCHAR(100) COMMENT '检查描述',
   sit_photos_url       VARCHAR(200) COMMENT '现场照片url',
   create_time          DATETIME COMMENT '创建时间',
   PRIMARY KEY (id),
   idx_result(result)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='检查情况信息表';