DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
                            `username` varchar(255) NOT NULL,
                            `gender` enum('M','F') DEFAULT NULL,
                            `phonenumber` varchar(11) DEFAULT NULL,
                            `emailaddress` varchar(255) DEFAULT NULL,
                            `birthdate` date DEFAULT NULL,
                            `icon` blob NOT NULL,
                            PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
                         `userid` int(11) NOT NULL AUTO_INCREMENT,
                         `username` varchar(255) NOT NULL,
                         `password` varchar(80)
--                          CHARACTER SET utf8 COLLATE utf8_bin
                         NOT NULL,
                         `uid` enum('customer','stuff') NOT NULL,
                         PRIMARY KEY (`userid`),
                         CONSTRAINT `l_username` FOREIGN KEY (`username`) REFERENCES `userinfo` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
                        `itemid` int(11) NOT NULL AUTO_INCREMENT,
                        `picture` blob NOT NULL,
                        `price` decimal(10,2) NOT NULL,
                        `itemname` varchar(255) NOT NULL,
                        `username` varchar(255) NOT NULL,
                        `description` varchar(255) NOT NULL,
                        PRIMARY KEY (`itemid`),
                        CONSTRAINT `i_username` FOREIGN KEY (`username`) REFERENCES `userinfo` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ordertable`;
CREATE TABLE `ordertable` (
                         `ordernumber` int(11) NOT NULL AUTO_INCREMENT,
                         `orderdate` datetime DEFAULT CURRENT_TIMESTAMP,
                         `username` varchar(255) NOT NULL,
                         `itemid` int(11) NOT NULL,
                         `status` enum('processing','denying','approving') DEFAULT NULL,
                         `lostdate` date DEFAULT NULL,
                         PRIMARY KEY (`ordernumber`),
                         CONSTRAINT `i_itemid` FOREIGN KEY (`itemid`) REFERENCES `item` (`itemid`) ON DELETE CASCADE ON UPDATE CASCADE,
                         CONSTRAINT `o_username` FOREIGN KEY (`username`) REFERENCES `userinfo` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
                           `messageid` int(11) NOT NULL AUTO_INCREMENT,
                           `text` varchar(255) NOT NULL,
                           `messagedate` datetime NOT NULL,
                           `ordernumber` int(11) NOT NULL,
                           `issend` enum('N','Y') NOT NULL,
                           PRIMARY KEY (`messageid`),
                           CONSTRAINT `m_ordernumber` FOREIGN KEY (`ordernumber`) REFERENCES `ordertable` (`ordernumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;