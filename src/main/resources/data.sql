INSERT INTO Article(id, title, content) VALUES (1, '가가가가', '1111');
INSERT INTO Article(id, title, content) VALUES (2, '나나나나', '2222');
INSERT INTO Article(id, title, content) VALUES (3, '다다다다', '3333');


-- article 더미 데이터
INSERT INTO Article(id, title, content) VALUES (4, '당신의 인생 영화는 ?', '댓글 ㄱ');
INSERT INTO Article(id, title, content) VALUES (5, '당신의 소울 푸드는 ?', '댓글 ㄱ');
INSERT INTO Article(id, title, content) VALUES (6, '당신의 취미는 ?', '댓글 ㄱ');

-- comment 더미 데이터
---- 4번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (1, 4, 'Park', '쇼생크탈출');
INSERT INTO comment(id, article_id, nickname, body) VALUES (2, 4, 'Lee', '시네마천국');
INSERT INTO comment(id, article_id, nickname, body) VALUES (3, 4, 'Kim', '명량');

---- 5번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (4, 5, 'Park', '비빔밥');

---- 6번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (5, 6, 'Park', '어쩔티비');
INSERT INTO comment(id, article_id, nickname, body) VALUES (6, 6, 'Lee', 'ㄴ 이사람 왜저래');
