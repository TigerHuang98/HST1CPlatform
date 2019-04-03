INSERT INTO userinfo (`username`,`gender`,`icon`) values('tiger','M','00');
INSERT INTO login(`username`,`password`,`uid`) values('tiger','$2a$10$srcCNi2tjA78DHvPJ.4mXOAjFSoQEwT6QX/IlBFJe3DOOU8VmI0vC','stuff');
INSERT INTO userinfo (`username`,`gender`,`icon`) values('user','M','00');
INSERT INTO login(`username`,`password`,`uid`) values('user','$2a$10$MUOACD/.SQMUPFEFFbSR..Bz6TykjLNrk40J74025l5AcYho9elMW','customer');
INSERT INTO item(`picture`,`price`,`itemname`,`username`,`description`) values('00','0.03','name','user','des');
INSERT INTO `order`(`username`,`itemid`,`status`,`lostdate`) values('user','1','processing','2018-04-01');
-- (tiger,123456)
