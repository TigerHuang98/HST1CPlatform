DROP TABLE IF EXISTS "userinfo";

CREATE TABLE `userinfo` (
                          `username` varchar(255) NOT NULL,
                          `gender` enum('M','F') DEFAULT NULL,
                          `phonenumber` varchar(20) DEFAULT NULL,
                          `emailaddress` varchar(255) DEFAULT NULL,
                          `birthdate` date DEFAULT NULL,
                          `icon` blob NOT NULL,
                          PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS login;

CREATE TABLE `login` (
                       `username` varchar(255) NOT NULL,
                       `password` varchar(80) NOT NULL,
                       `uid` enum('customer','stuff') NOT NULL,
                       `userid` int(11) NOT NULL AUTO_INCREMENT,
                       PRIMARY KEY (`userid`),
--                        KEY `l_username` (`username`),
                       CONSTRAINT `l_username` FOREIGN KEY (`username`) REFERENCES `userinfo` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS item;

CREATE TABLE `item` (
                      `itemid` int(11) NOT NULL,
                      `picture` blob NOT NULL,
                      `price` decimal(10,2) NOT NULL,
                      `itemname` varchar(255) NOT NULL,
                      `username` varchar(255) NOT NULL,
                      PRIMARY KEY (`itemid`),
--                       KEY `i_username` (`username`),
                      CONSTRAINT `i_username` FOREIGN KEY (`username`) REFERENCES `userinfo` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS message;

CREATE TABLE `message` (
                         `text` varchar(255) DEFAULT NULL,
                         `messagedate` datetime NOT NULL,
                         `ordernumber` int(11) NOT NULL,
                         `messageid` int(11) NOT NULL AUTO_INCREMENT,
                         `issend` enum('N','Y') NOT NULL,
                         PRIMARY KEY (`messageid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
                       `ordernumber` int(11) NOT NULL,
                       `orderdate` datetime NOT NULL,
                       `username` varchar(255) NOT NULL,
                       `itemid` int(11) NOT NULL,
                       `status` enum('processing','denying','approving') DEFAULT NULL,
                       PRIMARY KEY (`ordernumber`),
--                        KEY `o_username` (`username`),
--                        KEY `i_itemid` (`itemid`),
                       CONSTRAINT `i_itemid` FOREIGN KEY (`itemid`) REFERENCES `item` (`itemid`) ON DELETE CASCADE ON UPDATE CASCADE,
                       CONSTRAINT `o_username` FOREIGN KEY (`username`) REFERENCES `userinfo` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


