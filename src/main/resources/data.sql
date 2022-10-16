
-- article 더미 데이터
INSERT INTO Article(id, title, content) VALUES (100, '당신의 인생 영화는 ?', '댓글 ㄱ');
INSERT INTO Article(id, title, content) VALUES (101, '당신의 소울 푸드는 ?', '댓글 ㄱ');
INSERT INTO Article(id, title, content) VALUES (102, '당신의 취미는 ?', '댓글 ㄱ');

-- comment 더미 데이터
---- 100번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (100, 100, 'Park', '쇼생크탈출');
INSERT INTO comment(id, article_id, nickname, body) VALUES (101, 100, 'Lee', '시네마천국');
INSERT INTO comment(id, article_id, nickname, body) VALUES (102, 100, 'Kim', '명량');

---- 101번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (103, 101, 'Park', '비빔밥');

---- 102번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (104, 102, 'Park', '어쩔티비');
INSERT INTO comment(id, article_id, nickname, body) VALUES (105, 102, 'Lee', 'ㄴ 이사람 왜저래');



-- member 더미 데이터
INSERT INTO member(email, password, status, email_key, reg_date, drop_date, gender,
                   age, height, weight)
VALUES ('admin@naver.com', 1234, 'S', null, null, null, '남성',
        1, 1, 1);

-- -- member_style 더미 데이터
-- INSERT INTO member_style(id, member_email, style_name)
-- VALUES (1000, 'admin@naver.com', '댄디');
-- INSERT INTO member_style(id, member_email, style_name)
-- VALUES (1001, 'admin@naver.com', '스트릿');
-- INSERT INTO member_style(id, member_email, style_name)
-- VALUES (1002, 'admin@naver.com', '캐주얼');
-- INSERT INTO member_style(id, member_email, style_name)
-- VALUES (1003, 'admin@naver.com', '빈티지');




-- clothes 더미 데이터
INSERT INTO clothes(id, temp, is_outer, gender, age, height, weight, style_name)
VALUES (1000, 1, false, '남성', 1, 1, 1, '댄디');
INSERT INTO clothes(id, temp, is_outer, gender, age, height, weight, style_name)
VALUES (1001, 1, false, '남성', 1, 1, 1, '스트릿');

-- style 더미 데이터
INSERT INTO style(style_name)
VALUES ('댄디');
INSERT INTO style(style_name)
VALUES ('스트릿');
INSERT INTO style(style_name)
VALUES ('캐주얼');
INSERT INTO style(style_name)
VALUES ('빈티지');
INSERT INTO style(style_name)
VALUES ('놈코어');

-- category 더미 데이터
INSERT INTO category(id, season, name)
VALUES (1, '봄', '청바지');
INSERT INTO category(id, season, name)
VALUES (2, '봄', '맨투맨');
INSERT INTO category(id, season, name)
VALUES (3, '봄', '셔츠/블라우스');
INSERT INTO category(id, season, name)
VALUES (4, '봄', '후드티');
INSERT INTO category(id, season, name)
VALUES (5, '봄', '가디건');

INSERT INTO category(id, season, name)
VALUES (6, '여름', '반팔 티셔츠');
INSERT INTO category(id, season, name)
VALUES (7, '여름', '셔츠/블라우스');
INSERT INTO category(id, season, name)
VALUES (8, '여름', '민소매 티셔츠');
INSERT INTO category(id, season, name)
VALUES (9, '여름', '반바지');
INSERT INTO category(id, season, name)
VALUES (10, '여름', '청바지');

INSERT INTO category(id, season, name)
VALUES (11, '가을', '청바지');
INSERT INTO category(id, season, name)
VALUES (12, '가을', '맨투맨');
INSERT INTO category(id, season, name)
VALUES (13, '가을', '셔츠/블라우스');
INSERT INTO category(id, season, name)
VALUES (14, '가을', '후드티');
INSERT INTO category(id, season, name)
VALUES (15, '가을', '니트/스웨터');

INSERT INTO category(id, season, name)
VALUES (16, '겨울', '청바지');
INSERT INTO category(id, season, name)
VALUES (17, '겨울', '코트');
INSERT INTO category(id, season, name)
VALUES (18, '겨울', '패딩');
INSERT INTO category(id, season, name)
VALUES (19, '겨울', '후드티');
INSERT INTO category(id, season, name)
VALUES (20, '겨울', '니트/스웨터');









