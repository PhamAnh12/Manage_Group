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
						(N'Bảo vệ'		,2 			, 2			),
						(N'Nhân sự'		,2 			, 2			),
						(N'Kỹ thuật'	,2 			, 3			),
						(N'Tài chính1'	,2 			, 3			),
						(N'Tài chính2'	,2 			, 3			),
						(N'Tài chính3'	,2 			, 3			),
						(N'Tài chính4'	,2 			, 3			),
                        (N'Tài chính5'	,2 			, 3			),
                        (N'Tài chính6'	,2 			, 3			),
                        (N'Tài chính7'	,2 			, 3			),
                        (N'Tài chính8'	,2 			, 3			),
                        (N'Tài chính9'	,2 			, 3			),
                        (N'Kỹ thuật2'	,2 			, 3			),
                        (N'Kỹ thuật3'	,2 			, 3			),
                        (N'Kỹ thuật4'	,2 			, 3			),
                        (N'Kỹ thuật5'	,2 			, 3			),
					
						(N'Phó giám đốc',2 			, 4			);
                        
