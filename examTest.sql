-- create database
DROP DATABASE IF EXISTS TestingSystem;
CREATE DATABASE TestingSystem;
USE TestingSystem;

-- create table: Account
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User`(
	id						TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    email					VARCHAR(50) NOT NULL UNIQUE KEY, -- Cannot update this field
    username				VARCHAR(50) NOT NULL UNIQUE KEY, -- Cannot update this field
	`password`				VARCHAR(800) NOT NULL, -- Cannot update this field
    firstName				NVARCHAR(50) NOT NULL,
    lastName				NVARCHAR(50) NOT NULL,	-- create field fullName in POJO
    phone					VARCHAR(15)  
);
-- create table: Group
DROP TABLE IF EXISTS `Group`;
CREATE TABLE `Group`(
	GroupID 				TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    GroupName 				NVARCHAR(30) NOT NULL UNIQUE KEY,
    `Member`				INT DEFAULT 0,
    CreateDate				DATETIME DEFAULT NOW(),
    CreatorID				TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY(CreatorID) 	REFERENCES `User`(id)
);

/*============================== INSERT DATABASE =======================================*/
/*======================================================================================*/
-- Add data User
INSERT INTO `User` (email								, username			, firstName,	lastName,		 phone				, `password`	)
VALUES 				('haidang29productions@gmail.com'	, 'dangblack'		,'Dang'	,		'Nguyen Hai'	,   '123456789'		,   '123456'	),
					('account1@gmail.com'				, 'quanganh'		,'Anh'	,		'Tong Quang'	,   '123456789'		,   '123456'	),
                    ('account2@gmail.com'				, 'vanchien'		,'Chien',		'Nguyen Van'	,   '123456789'		,   '123456'	),
                    ('account3@gmail.com'				, 'cocoduongqua'	,'Do'	,		'Duong'			,   '123456789'		,   '123456'	),
                    ('account4@gmail.com'				, 'doccocaubai'		,'Thang',		'Nguyen Chien'  ,   '123456789'		,   '123456'	),
                    ('dapphatchetngay@gmail.com'		, 'khabanh'			,'Kha'	,		'Ngo Ba'		,   '123456789'		,   '123456'	),
                    ('songcodaoly@gmail.com'			, 'huanhoahong'		,'Huan'	,		'Bui Xuan'		,   '123456789'		,   '123456'	),
                    ('sontungmtp@gmail.com'				, 'tungnui'			,'Tung'	,		'Nguyen Thanh'	,   '123456789'		,   '123456'	),
                    ('duongghuu@gmail.com'				, 'duongghuu'		,'Huu'	,		'Duong Van'		,   '123456789'		,   '123456'	),
                    ('vtiaccademy@gmail.com'			, 'vtiaccademy'		,'Ai'	,		'Vi Ti'			,   '123456789'		,   '123456'	);
INSERT INTO `Group`		(GroupName 		,`Member` 	, CreatorID) 
VALUES
						(N'Marketing'	,2 			, 1			),
						(N'Sale'		,2 			, 1			),
						(N'B???o v???'		,2 			, 2			),
						(N'Nh??n s???'		,2 			, 2			),
						(N'K??? thu???t'	,2 			, 3			),
						(N'T??i ch??nh1'	,2 			, 3			),
						(N'T??i ch??nh2'	,2 			, 3			),
						(N'T??i ch??nh3'	,2 			, 3			),
						(N'T??i ch??nh4'	,2 			, 3			),
                        (N'T??i ch??nh5'	,2 			, 3			),
                        (N'T??i ch??nh6'	,2 			, 3			),
                        (N'T??i ch??nh7'	,2 			, 3			),
                        (N'T??i ch??nh8'	,2 			, 3			),
                        (N'T??i ch??nh9'	,2 			, 3			),
                        (N'K??? thu???t2'	,2 			, 3			),
                        (N'K??? thu???t3'	,2 			, 3			),
                        (N'K??? thu???t4'	,2 			, 3			),
                        (N'K??? thu???t5'	,2 			, 3			),
					
						(N'Ph?? gi??m ?????c',2 			, 4			);
                        
