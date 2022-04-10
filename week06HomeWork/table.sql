-- 电商
/*
基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。
*/


create table t_user(
	id   				varchar(100) primary key comment 'id',
	name 				varchar(255) 			 comment '名字',
	gender 				char(1) 		 		 comment '性别',
	email  				varchar(255) 			 comment 'email地址',
	status 				int 					 comment '1有效，0失效'
)comment '用户表';

create table t_product(
	id   				varchar(100) primary key    comment 'id',
	name 				varchar(255) 			    comment '名字',
	price 				decimal(10,5) 				comment '价格',
	type 				int 					    comment '类型',
	status 				int 						comment '1有效，0失效'
)comment '详情表';

create table t_order(

	id 				varchar(100) primary key 				comment 'id',
	user_id   		varchar(100) 						 	comment '用户id',
	shop_id   		varchar(255) 						 	comment '店铺id',
	create_time     timestamp not null default now()        comment '订购时间',
	type  			int									    comment '类型',
	amount  		decimal(10,5)							comment '价格'
)comment '订单表';

create table t_order_detail(
	id   			varchar(100) primary key comment 'id',
	order_id     	varchar(100)			 comment '订单id',
	product_id   	varchar(100)			 comment '产品id',
	type 			int 					 comment '类型',
	num  			int unsigned not null 	 comment '购买数量'
)comment '订单详情表';


select * from t_user;
select * from t_product;
select * from t_order;
select * from t_order_detail;
