 insert into role(code,name,description)
 values ('0','admin','admin user role');
--
 insert into role(code,name,description)
 values ('1','default','default user role');
--

insert into users(id,name,ROLE_CODE)
 values (RANDOM_UUID(),'mohamed.abdo','0');
--
 insert into users(id,name,ROLE_CODE)
 values (RANDOM_UUID(),'ahmed abdo','1');
--
 insert into users(id,name,ROLE_CODE)
 values (RANDOM_UUID(),'omar abdo','1');
