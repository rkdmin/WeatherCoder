--
-- -- article 더미 데이터
-- INSERT INTO Article(id, title, content) VALUES (100, '당신의 인생 영화는 ?', '댓글 ㄱ');
-- INSERT INTO Article(id, title, content) VALUES (101, '당신의 소울 푸드는 ?', '댓글 ㄱ');
-- INSERT INTO Article(id, title, content) VALUES (102, '당신의 취미는 ?', '댓글 ㄱ');
--
-- -- comment 더미 데이터
-- ---- 100번 게시글의 댓글들
-- INSERT INTO comment(id, article_id, nickname, body) VALUES (100, 100, 'Park', '쇼생크탈출');
-- INSERT INTO comment(id, article_id, nickname, body) VALUES (101, 100, 'Lee', '시네마천국');
-- INSERT INTO comment(id, article_id, nickname, body) VALUES (102, 100, 'Kim', '명량');
--
-- ---- 101번 게시글의 댓글들
-- INSERT INTO comment(id, article_id, nickname, body) VALUES (103, 101, 'Park', '비빔밥');
--
-- ---- 102번 게시글의 댓글들
-- INSERT INTO comment(id, article_id, nickname, body) VALUES (104, 102, 'Park', '어쩔티비');
-- INSERT INTO comment(id, article_id, nickname, body) VALUES (105, 102, 'Lee', 'ㄴ 이사람 왜저래');
--
-- -- member 더미 데이터
-- INSERT INTO member(id, user_id, email, password, nickname, status, category,
--                    reg_date, drop_date)
-- VALUES (0, 'admin', 'admin@naver.com',
--         '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4',
--         '이강민', 'Y', '123', '1', '1');

-- clothes 더미 데이터
INSERT INTO clothes(id, temp, is_outer, gender)
VALUES (1000, 1, false, '남성');
INSERT INTO clothes(id, temp, is_outer, gender)
VALUES (1001, 2, false, '남성');
INSERT INTO clothes(id, temp, is_outer, gender)
VALUES (1002, 3, false, '남성');
INSERT INTO clothes(id, temp, is_outer, gender)
VALUES (1003, 4, false, '남성');
INSERT INTO clothes(id, temp, is_outer, gender)
VALUES (1004, 5, false, '남성');

INSERT INTO clothes(id, temp, is_outer, gender)
VALUES (1005, 1, true, '남성');
INSERT INTO clothes(id, temp, is_outer, gender)
VALUES (1006, 2, true, '남성');
INSERT INTO clothes(id, temp, is_outer, gender)
VALUES (1007, 3, true, '남성');
INSERT INTO clothes(id, temp, is_outer, gender)
VALUES (1008, 4, true, '남성');
INSERT INTO clothes(id, temp, is_outer, gender)
VALUES (1009, 5, true, '남성');







