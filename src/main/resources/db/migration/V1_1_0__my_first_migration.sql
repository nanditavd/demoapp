CREATE TABLE IF NOT EXISTS `trade` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `tradeId` varchar(20),
    `version` int NOT NULL ,
    `counterParty` varchar(20),
    `bookId` varchar(20),
    `maturityDate` timestamp,
     `createdDate` timestamp,
     `status` varchar(20)

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;