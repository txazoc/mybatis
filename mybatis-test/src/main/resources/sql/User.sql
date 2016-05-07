use test;

create table User (
    id int(11) unsigned not null auto_increment comment 'id',
    name varchar(32) not null default '' comment 'name',
    age tinyint(4) unsigned not null default 0 comment 'age',
    primary key (id),
    unique (name)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='User';
