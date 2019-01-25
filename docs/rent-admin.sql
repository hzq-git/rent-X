create database rentx_admin;
use rentx_admin;

create table user
(
  id             int unsigned auto_increment comment 'ID'
    primary key,
  username       varchar(20)                                   not null comment '用户名',
  password       char(32)                                      not null comment '密码',
  salt           char(32)                                      not null comment '盐',
  add_user_id    int unsigned        default 0                 not null comment '添加人ID',
  add_time       timestamp           default CURRENT_TIMESTAMP not null comment '添加时间',
  update_user_id int unsigned        default 0                 not null comment '更新人ID',
  update_time    timestamp           default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
  mark           tinyint(1) unsigned default 1                 not null comment '删除标识(是否有效 1有效,0无效)'
)
  comment '用户表' charset = utf8mb4;