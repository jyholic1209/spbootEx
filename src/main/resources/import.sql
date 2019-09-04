insert into user (id, u_id, u_pw, u_name, u_email) values (1, 'aa', 'aa', 'test', 'test@test.com');
insert into user (id, u_id, u_pw, u_name, u_email) values (2, 'sky', '1111', '변종성', 'sky@test.com');

insert into question (id, title, contents, create_time, writer_id) values (1, 'test_title_aa', 'test_contents aa', CURRENT_TIMESTAMP(), 1);
insert into question (id, title, contents, create_time, writer_id) values (2, 'test_title_sky', 'test_contents sky', CURRENT_TIMESTAMP(), 2);