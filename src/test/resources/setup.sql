 insert into role(code,name,description)
 values ('DEFAULT','default','default user role');
--
 insert into role(code,name,description)
 values ('ADMIN','admin','admin user role');
--
insert into users(id,name,ROLE_CODE)
 values (RANDOM_UUID(),'mohamed.abdo','ADMIN');
--
 insert into users(id,name,ROLE_CODE)
 values (RANDOM_UUID(),'ahmed abdo','DEFAULT');
--
 insert into users(id,name,ROLE_CODE)
 values (RANDOM_UUID(),'omar abdo','DEFAULT');
