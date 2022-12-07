# WeatherCoder

## 회원
### 회원가입
POST http://localhost:8080/join   
Content-Type: application/json

{  
  "username": "?",  
  "password": "?",  
  "gender" : "?",  
  "age": ?,  
  "height": ?,  
  "weight" : ?,  
  "styleList" : ["?", "?"]    
}    
[참고] : gender는 "남성", "여성" age, height, weight는 1 ~ 5까지 styleList는 String형 List

받는데이터
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "스타일을 선택하세요."
  }
- {
  "errorCode": "STYLE_EMPTY",
  "errorMessage": "스타일이 없습니다."
  }
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "비밀번호는 영어, 숫자, 특수문자 포함해서 8~16자리 이내로 입력해주세요."
  }
- {
  "errorCode": "ALREADY_EXISTS_EMAIL",  
  "errorMessage": "이미 존재하는 이메일입니다."  
  }
- 회원가입이 완료되었습니다.  
[참고] : 비밀번호는 영문숫자특수문자포함 8자리 이상 20자리 이하


### 로그인
POST http://localhost:8080/login  
Content-Type: application/json

{  
"username": "?",  
"password": "?"  
}      

받는데이터
- {
  "errorCode": "MEMBER_EMPTY",
  "errorMessage": "이메일 또는 패스워드가 일치하지 않습니다."
  }
- {
  "username": "?",
  "gender": "?",
  "age": ?,
  "height": ?,
  "weight": ?,
  "styleList": [
  "?",
  "?",
  "?"
  ],
  "regDt": "?"
  },  
  "roles": "?",
  "token": "?"
  }

### 회원 카테고리 등록 / 수정    
POST http://localhost:8080/my-clothes     
Authorization: Bearer ey(..생략)  
Content-Type: application/json

{
  "seasonList" : [  
  {
  "seasonName" : "?",
  "nameList" : ["?", "?"]
  },
  {
  "seasonName" : "?",
  "nameList" : ["?", "?"]}  
  ]  
}

받는데이터
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "잘못된 요청입니다."
  }
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "계절 리스트가 없습니다."
  }
- {
    "errorCode": "CATEGORY_EMPTY",
    "errorMessage": "알맞은 카테고리가 없습니다."
  }
- 카테고리 저장이 완료되었습니다.

### 회원 스타일 수정
POST http://localhost:8080/my-style    
Authorization: Bearer ey(..생략)  
Content-Type: application/json

{
"styleList" : ["?", "?"]  
}  

받는데이터  
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "이메일이 없습니다."
  }
- {
  "errorCode": "MEMBER_EMPTY",
  "errorMessage": "회원이 없습니다."
  }
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "스타일이 없습니다."
  }
- {
  "errorCode": "STYLE_EMPTY",
  "errorMessage": "스타일이 없습니다."
  }
- 스타일 수정이 완료되었습니다.

### 회원 비밀번호 변경
POST http://localhost:8080/change-password  
Content-Type: application/json

{      
"email" : "?",  
"password" : "?",  
"newPassword" : "?"  
}

받는데이터
- {
  "errorCode": "MEMBER_EMPTY",
  "errorMessage": "패스워드가 일치하지 않습니다."
  }  
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "비밀번호는 영어, 숫자, 특수문자 포함해서 8~16자리 이내로 입력해주세요."
  } 
- 이메일 전송이 완료되었습니다.  



## 옷추천 기능 
### 날씨로 옷 추천(비회원)
POST http://localhost:8080/suggest1-non-member  
Content-Type: application/json

{
"lowTemp" : ?,
"highTemp" : ?,
"gender" : "?"
}

받는데이터  
- {"clothesList": [
  "https://res.cloudinary.com/dohqejduh/image/upload/v1666072183/1050.jpg",
  "https://res.cloudinary.com/dohqejduh/image/upload/v1666072183/1063.jpg"
  ]}
- {
   "clothesList": [
  null,
  "http://localhost:8080/img/1000"
  ]
  }
- {
  "errorCode": "CLOTHES_EMPTY",
  "errorMessage": "적합한 옷이 없습니다."
  }

### 회원 정보로 옷 추천
POST http://localhost:8080/suggest2  
Authorization: Bearer ey(..생략)  
Content-Type: application/json   

{
"lowTemp" : ?,
"highTemp" : ?,
}

받는데이터
- {
  "clothesList": [
  "https://res.cloudinary.com/dohqejduh/image/upload/v1666072183/1050.jpg",
  "https://res.cloudinary.com/dohqejduh/image/upload/v1666072183/1063.jpg"
  ]
  }
- {
  "errorCode": "MEMBER_EMPTY",
  "errorMessage": "회원이 없습니다."
  }
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "이메일이 없습니다."
  }
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "최저기온이 없습니다."
  }
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "최고기온이 없습니다."
  }


### 회원이 등록한 옷 카테고리로 옷 추천
POST http://localhost:8080/suggest3  
Authorization: Bearer ey(..생략)  
Content-Type: application/json

{
"lowTemp" : ?,
"highTemp" : ?,
}

받는데이터
- {
  "clothesList": [
  "https://res.cloudinary.com/dohqejduh/image/upload/v1666072183/1050.jpg",
  "https://res.cloudinary.com/dohqejduh/image/upload/v1666072183/1063.jpg"
  ]
  }
- {
  "errorCode": "MEMBER_EMPTY",
  "errorMessage": "회원이 없습니다."
  }
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "잘못된 요청입니다."
  }
- {
  "errorCode": "CATEGORY_EMPTY",
  "errorMessage": "알맞은 카테고리가 없습니다."
  }
- {
  "errorCode": "CLOTHES_EMPTY",
  "errorMessage": "적합한 옷이 없습니다."
  }

## 게시판 및 댓글
### 게시글 쓰기
POST http://localhost:8080/articles/new  
Content-Type: application/json

{  
"title" : "?",  
"content" : "?"  
}  

받는데이터
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "내용이 없습니다."
  }
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "제목이 없습니다."
  }
- {
  "errorCode": "EXIST_ID",
  "errorMessage": "고유번호가 존재하면 안됩니다."
  }
- 게시글 작성이 완료되었습니다.

### 게시글 전체 읽기
GET http://localhost:8080/articles  
Accept: application/json

받는데이터
- [  
  {
  "id": 1,
  "title": "첫번째 게시글",
  "content": "첫번째 게시글 입니다."  
  },
  {
  "id": 2,
  "title": "게시글",
  "content": "게시글 입니다."
  }  
  ]

### 게시글 id로 읽기
GET http://localhost:8080/articles/{id}  
Accept: application/json

받는데이터
- {
  "errorCode": "ARTICLE_EMPTY",
  "errorMessage": "게시글이 없습니다."
  }
- {
    "id": 2,
    "title": "게시글",
    "content": "게시글 입니다."
    }

### 게시글 수정
PATCH http://localhost:8080/articles/{id}/edit   
Content-Type: application/json

{  
"title" : "?",  
"content" : "?"  
}

받는데이터
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "잘못된 요청입니다."
  }
- 게시글 수정이 완료되었습니다.

### 게시글 삭제
DELETE http://localhost:8080/articles/{id}/delete

받는데이터
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "잘못된 요청입니다."
  }
- 게시글 삭제가 완료되었습니다.

### 댓글 작성
POST http://localhost:8080/articles/{article_id}/comments/new  
Content-Type: application/json

{  
"nickname" : "?",  
"body" : "?"  
}

받는데이터
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "잘못된 요청입니다."
  }
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "내용이 없습니다."
  }
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "닉네임이 없습니다."
  }
- {
  "errorCode": "EXIST_ID",
  "errorMessage": "고유번호가 존재하면 안됩니다."
  }
- 댓글 작성이 완료되었습니다.

### 댓글 목록 조회
GET http://localhost:8080/articles/{aritcle_id}/comments  
Accept: application/json

받는데이터
- [
  {
  "id": 4,
  "articleId": 5,
  "nickname": "홍길동",
  "body": "댓글 입니다."
  },
  {
  "id": 5,
  "articleId": 5,
  "nickname": "홍길동",
  "body": "댓글 입니다."
  }
  ]

### 댓글 수정
PATCH http://localhost:8080/articles/comment/{comment_id}/edit  
Content-Type: application/json

{  
"nickname" : "?",  
"body" : "?"  
}

받는데이터
- {
"errorCode": "INVALID_COMMENT_ID",
"errorMessage": "잘못된 댓글 번호입니다."
}
- 댓글 수정이 완료되었습니다.

### 댓글 삭제
DELETE http://localhost:8080/articles/comment/{comment_id}/delete

받는데이터
- {
  "errorCode": "COMMENT_EMPTY",
  "errorMessage": "댓글이 없습니다."
  }
- 댓글 삭제가 완료되었습니다.


