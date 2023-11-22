SELECT B.name,BC.name
FROM books B
         INNER JOIN book_categories BC
                    ON B.book_category_id=BC.id;

-- US01 -1
    select  count(id) from users; -- 4891

    select count(distinct id) from users; -- 4891

-- us1 query
SELECT COLUMN_NAME
FROM information_schema.COLUMNS
WHERE TABLE_NAME = 'users';

-- us2 query
select * from book_borrow
where is_returned = 0;



-- us3 query
select name from book_categories;

-- us4 query
select * from books
where  description like 'Prac%';

-- us5 query
select bc.name,count(*) from book_borrow bb
                                 inner join books b on bb.book_id = b.id
                                 inner join book_categories bc on b.book_category_id=bc.id
group by name
order by 2 desc;

-- us6 query
select id,name,author from books
where name = 'Cypress First Book' and author='Barack Obama'
order by id desc;

-- us7 query
select full_name,b.name,bb.borrowed_date from users u
                                                  inner join book_borrow bb on u.id = bb.user_id
                                                  inner join books b on bb.book_id = b.id
where full_name='Test Student 49' and name='Richard Burke' and is_returned =0
order by 3 desc;

select  * from books;