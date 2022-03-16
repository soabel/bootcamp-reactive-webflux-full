create table subscriber(Id int identity(1,1) primary key, BlogId varchar(50), BlogName varchar(250), AuthorName varchar(250), Date datetime )


insert into [dbo].[subscriber](BlogId, BlogName, AuthorName, Date)
values('1234','Rincon de Java', 'Bootcamp', '2022-01-01')