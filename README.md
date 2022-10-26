# WeatherCoder

## 회원
### 회원가입
POST http://localhost:8080/join   
Content-Type: application/json

{  
  "email": "?",  
  "password": "?",  
  "gender" : "?",  
  "age": ?,  
  "height": ?,  
  "weight" : ?,  
  "styleList" : ["?", "?"]    
}    
[참고] : gender는 "남성", "여성" age, height, weight는 1 ~ 5까지 styleList는 String형 List

### 회원 카테고리 등록
POST http://localhost:8080/my-clothes
Content-Type: application/json

{  
  "email" : "?",      
  "seasonList" : [  
  {
  "seasonName" : "봄",
  "nameList" : ["가디건", "후드티"]
  },
  {
  "seasonName" : "겨울",
  "nameList" : ["패딩", "니트/스웨터"]}  
  ]  
}

받는데이터  
- {
"errorCode": "INVALID_REQUEST",
"errorMessage": "잘못된 요청입니다."
}
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "이메일이 없습니다."
  }
- 카테고리 저장이 완료되었습니다.


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
Content-Type: application/json   

{
"lowTemp" : ?,
"highTemp" : ?,
"email" : "?"
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
Content-Type: application/json

{
"lowTemp" : ?,
"highTemp" : ?,
"email" : "?"
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




