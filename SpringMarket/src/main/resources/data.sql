

insert into LIBRO (TITULO,AUTOR,PRECIO) values ('Las Crónicas de Narnia', 'C. S. Lewis', '10.15');
insert into LIBRO (TITULO,AUTOR,PRECIO) values ('El Señor de los Anillos','J. R. R. Tolkien', '15.50');
insert into LIBRO (TITULO,AUTOR,PRECIO) values ('Memorias de Idhún', 'Laura Gallego', '25.30');
insert into LIBRO (TITULO,AUTOR,PRECIO) values ('El Imperio Final', 'Brandon Sanderson','18.75');
insert into LIBRO (TITULO,AUTOR,PRECIO) values ('El último deseo', 'Andrzej Sapkowski','30.5');

INSERT INTO CLIENTE (NOMBRE, APELLIDOS, CONTRASEÑA, EMAIL, FECHA_NACIMIENTO) 
VALUES ("Manuel", "Casero", "$2a$10$b7SDpw.fajKdKk1AbuL4T.E7QaG2INTPQMPAvbziZ1pcKR9YpaBLm", "mcaserobodelon@gmail.com", '2008-7-04');
INSERT INTO CLIENTE (NOMBRE, APELLIDOS, CONTRASEÑA, EMAIL, FECHA_NACIMIENTO) 
VALUES ("Pepe", "Cabrero", "$2a$10$b7SDpw.fajKdKk1AbuL4T.E7QaG2INTPQMPAvbziZ1pcKR9YpaBLm", "pepe@gmail.com", '2008-7-04');



insert into ROL (ID_ROL, NOMBRE_ROL) values (1,'admin');
insert into ROL (ID_ROL, NOMBRE_ROL) values (2, 'registrado');

insert into CLIENTE_ROL (ID_CLIENTE, ID_ROL) values (1, 1);
insert into CLIENTE_ROL (ID_CLIENTE, ID_ROL) values (2, 2);