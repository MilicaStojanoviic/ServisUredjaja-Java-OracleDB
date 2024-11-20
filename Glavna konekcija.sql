create user c##projekat identified by projekat;

grant connect to c##projekat;

grant create table, select any table, insert any table,
delete any table, update any table to c##projekat;

grant unlimited tablespace to c##projekat;

grant CREATE ANY TYPE, ALTER ANY TYPE, EXECUTE ANY TYPE to c##projekat;

grant CREATE ANY TRIGGER, ALTER ANY TRIGGER to c##projekat;

grant CREATE procedure to c##projekat;

grant CREATE ANY INDEX to c##projekat;

grant CREATE ANY VIEW to c##projekat;