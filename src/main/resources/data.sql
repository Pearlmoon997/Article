INSERT INTO article(id, title, content) VALUES(1, '가가가가', '1111');
INSERT INTO article(id, title, content) VALUES(2, '나나나나', '2222');
INSERT INTO article(id, title, content) VALUES(3, '다다다다', '3333');

--게시글 더미데이터
INSERT INTO article(id, title, content) VALUES(4, '좋아하는 영화', '댓글로 남겨주세요');
INSERT INTO article(id, title, content) VALUES(5, '좋아하는 음식', '댓글로 남겨주시라요');
INSERT INTO article(id, title, content) VALUES(6, '취미', '댓글로 남겨줘');

--comment 더미데이터
INSERT INTO comment(id, article_id, nickname, body) VALUES(1, 4, 'Moon', '어버웃 미닛');
INSERT INTO comment(id, article_id, nickname, body) VALUES(2, 4, 'Lee', '정무문');
INSERT INTO comment(id, article_id, nickname, body) VALUES(3, 4, 'Park', '굳 윌 헌팅');

INSERT INTO comment(id, article_id, nickname, body) VALUES(4, 5, 'Moon', '냉면');
INSERT INTO comment(id, article_id, nickname, body) VALUES(5, 5, 'Lee', '탕수육');
INSERT INTO comment(id, article_id, nickname, body) VALUES(6, 5, 'Park', '돈까스');

INSERT INTO comment(id, article_id, nickname, body) VALUES(7, 6, 'Moon', '여행');
INSERT INTO comment(id, article_id, nickname, body) VALUES(8, 6, 'Lee', '운동');
INSERT INTO comment(id, article_id, nickname, body) VALUES(9, 6, 'Park', '독서');
