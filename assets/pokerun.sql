create schema poke_run CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use poke_run;

create table `player` (
	`uuid` bigint primary key auto_increment,
    -- basic info
    `playerName` varchar(32) not null,
    `pwd` varchar(32) not null,
    `email` varchar(255) not null unique,
    `avatar` varchar(255),
    -- 对于其他信息，可以之后在建一张表
    -- index
    -- 使用index，可以考虑延迟关联优化查询
    index(`playerName`),
    index(`email`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

insert into player values(default, 'yxaa', '123456', 'yxa2111@qq.com', 'yxaa.png');
insert into player values(default, 'gyf', '123456', '1404220384@qq.com', 'gyf.png');
insert into player values(default, 'muyoo', '123456', 'muyoo.top@qq.com', 'muyoo.png');
insert into player values(default, 'wujichen158', '123456', '1169083089@qq.com', 'wujichen158.png');

select * from player;