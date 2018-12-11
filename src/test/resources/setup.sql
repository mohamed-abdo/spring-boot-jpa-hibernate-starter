 insert into role(code,name,description)
 values ('DEFAULT','default','default user role');
--
 insert into role(code,name,description)
 values ('ADMIN','admin','admin user role');
--
insert into users(id,name,ROLE_CODE)
 values (1,'mohamed abdo','ADMIN');
--
 insert into users(id,name,ROLE_CODE)
 values (2,'ahmed abdo','DEFAULT');
--
 insert into users(id,name,ROLE_CODE)
 values (3,'omar abdo','DEFAULT');
