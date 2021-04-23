insert into users(id,name,lastname,userName,mobile) values(1,'petter','parker','spiderman',0800300),
(2,'bruce','wayne','imNotBatman',0800301),
(3,'bruce','banner','bigGreenGuy', 0700300),
(4,'martin','parker','Alan', 0750300),
(5,'tom','holands','tom', 073200),
(6,'bruce','daniels','almostAhero', 0706000),
(7,'martin','holands','martin', 0705000);
insert into logins(email,password,validationNumber,usersId) values ('test1234@gmail.com','12345test',527,1),
('gothamlove@gmail.com','12345',648,2),
('hulksmash@yahoo.com','smash123',13264,3),
('generic12@gmail.com','genericpass',9515,4),
('niceguy@gmail.com','easypass',654,5),
('ambicious@yahoo.com','notsecure',852,6),
('noidea@yahoo.com','1234567',6795,7);
insert into userfriends(id,sourceId,targetId,friendType,status) values(1,2,1,1,5);
insert into multimedia(id,type,name) values(1,'V','Im Batman');
insert into multimedia(id,type,name) values(2,'P', 'Working');
insert into multimedia(id,type,name) values(3,'V', 'general');
insert into multimedia(id,type,name) values(4,'A', 'Working');
insert into multimedia(id,type,name) values(5,'A', 'general');
insert into multimedia(id,type,name) values(6,'P', 'Working');
insert into videos(id,link,duration,multimediaId) values(1,'https://www.youtube.com/watch?v=sPCIB7OGLjg',0.32,1);
insert into videos(id,link,duration,multimediaId) values(2,'https://www.youtube.com/thisitsnotaurl',2.8,3);
insert into messages(id,message,sourceId,targetId,multimediaId) values(1,'remember not to reveal my true identity', 2,1,1);
insert into photos(id,link,multimediaId) values(1,'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/spiderman-lejos-de-casa-marvel-1547720163.jpg',2);
insert into photos(id,link,multimediaId) values(2,'https://anyurl.jpg',6);
insert into audios(id,duration,link,multimediaId) values(1,0.35,'someplaceintotheserver',4);
insert into audios(id,duration,link,multimediaId) values(2,2.35,'someplaceintotheserver5',5);
insert into posts(id,message,usersId,multimediaId) values(1,'The crimes never stop',1,2), (2, 'I never relax',3,null);
insert into usergroups(id,title,themes,createdBy) values(1,'Superheroes','Save the world',2);




SET SQL_SAFE_UPDATES = 0;
update users set userName = 'imNotSpiderman' where id =1;
update users set mobile = '257946' where name='bruce';
update logins set password = 'newpass123' where usersId = (select id from users where lastname ='parker');
update logins set email = 'spiders@gmail.com' where usersId =(select id from users where userName ='imNotSpiderman');
update userfriends set status= 4 where id = 1;
update multimedia set name= 'Dark knight' where type = 'V';
update posts set message='I like to relax a bit' where id = (select multimediaId from photos where id =1);
update usergroups set title = 'Super League' where themes like '%crimes%';
update multimedia set type= 'A' where name like 'Working';
update users set name='Santiago' where id= 4;


select name, lastname
from users
group by(name);

select name
from multimedia
group by(name)
having count(*)>2;

select p.message, u.name, u.lastname
from posts p join users u on(p.usersId=u.id)
where message like '%relax%';

select * 
from users
group by(name)
having count(name)>2;

select count(*)
from multimedia
group by(name);

select mobile
from users
group by (name)
having max(mobile);

select u.*
from users u join usergroups g on (u.id=g.createdBy)
where g.themes like '%hero%';

select title
from usergroups
group by(themes)
having count(title) > 3;

select m.*
from multimedia m join audios a on(m.id=a.multimediaId) join photos p on (m.id=p.multimediaId) join videos v on (m.id=v.multimediaId)
group by (m.type)
having count(*)>1;


delete from multimedia where name like('%Working%');
delete from videos where duration > 20.30;
delete from audios where link like('%whatsapp%');
delete from users where year(registerAt) < 2010;
delete from users where userName = 'imNotAHero';
delete from logins where email like '%yahoo%';
delete from userfriends where status = 0;
delete from messages where targetId = 2;



select * 
		from logins l 
        left join users u on (l.usersId =u.id)
        left join userFriends ufS on (u.id=ufS.sourceId)
        #left join userFriends ufT on (u.id=ufT.targetId)
        left join messages mS on (u.id=mS.sourceId)
        #left join messages mT on (u.id=TS.targetId)
        left join multimedia mult on (mult.id=mS.multimediaId)
        left join videos v on (v.multimediaId=mult.id)
        left join photos p on (mult.id=p.multimediaId)
        left join audios a on (mult.id=a.multimediaId)
        left join posts pos on (u.id=pos.usersId)
        left join usergroups gr on (u.id=gr.usersId)
        left join groupMembers gm on(gm.groupsId=gr.id)
        left join groupMessages gmes on (gmes.groupsId=gr.id)
	;
