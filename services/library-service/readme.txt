Insert statements to create the books and users:

INSERT INTO `notes_app`.`books`
(`author`,
`callnumber`,
`created_at`,
`isbn`,
`publisher`,
`title`,
`updated_at`,
`year_of_publication`)
VALUES
('author1', 'call1', '2017-12-01 01:02:26', 'isbn1', 'publ1', 'title1', '2017-12-01 01:02:26', '2017'),
('author2', 'call2', '2017-12-01 01:02:58', 'isbn2', 'publ2', 'title2', '2017-12-01 01:02:58', '2017'),
('author3', 'call3', '2017-12-01 01:03:11', 'isbn3', 'publ3', 'title3', '2017-12-01 01:03:11', '2017'),
('author4', 'call4', '2017-12-01 01:03:23', 'isbn4', 'publ4', 'title4', '2017-12-01 01:03:23', '2017'),
('author5', 'call5', '2017-12-01 01:03:36', 'isbn5', 'publ5', 'title5', '2017-12-01 01:03:36', '2017'),
('author6', 'call6', '2017-12-01 01:03:36', 'isbn6', 'publ6', 'title6', '2017-12-01 01:03:36', '2017'),
('author7', 'call7', '2017-12-01 01:04:19', 'isbn7', 'publ7', 'title7', '2017-12-01 01:04:19', '2017');

INSERT INTO `notes_app`.`users`
(`created_at`,
`password`,
`role`,
`updated_at`,
`useremail`,
`username`)
VALUES
('2017-12-01 01:04:41',	'usr1usr1'	,'LIBRARIAN'	,'2017-12-01 01:04:41'	,'usr1@ness.com'	,'usr1'),
('2017-12-01 01:05:36'	,'usr2usr2'	,'PATRON'	,'2017-12-01 01:05:36'	,'usr2@ness.com'	,'usr2'),
('2017-12-01 01:05:56',	'usr3usr3'	,'PATRON',	'2017-12-01 01:05:56'	,'usr3@ness.com'	,'usr3')
;


Sample:
 POST : http://localhost:8080/api/books/
{"isbn":"isbn7",
"author":"author7",
"title":"title7",
"callnumber":"call7",
"publisher":"publ7",
"year_of_publication":"2017"}

 POST : http://localhost:8080/api/users/
{"username":"usr3",
"useremail":"usr3@ness.com",
"password":"usr3usr3",
"role":"PATRON"}

POST:http://localhost:8080/api/library/issueBook
{
    "userId": 2,
    "bookId": 3
}

POST:http://localhost:8080/api/library/releaseBook
{
    "userId": 2,
    "bookId": 3
}
