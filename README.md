# WeatherCoder

## 회원가입 기능 
### 1. create  
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
gender는 "남성", "여성" age, height, weight는 1 ~ 5까지 styleList는 String형 List


받는데이터  
- {
"errorCode": "ALREADY_EXISTS_EMAIL",
"errorMessage": "이미 존재하는 이메일입니다."
}
- {
  "errorCode": "INVALID_REQUEST",
  "errorMessage": "비밀번호는 영어, 숫자, 특수문자 포함해서 8~16자리 이내로 입력해주세요."
  }
- 회원가입이 완료되었습니다.

## 옷추천 기능 
### 1. suggest1
POST http://localhost:8080/suggest1-non-member  
Content-Type: application/json

{
"lowTemp" : ?,
"highTemp" : ?,
"gender" : "?"
}

받는데이터  
- {"clothesList": [
  "http://localhost:8080/img/1005",
  "http://localhost:8080/img/1005"
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

### 2. suggest2
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
  "http://localhost:8080/img/1001",
  "http://localhost:8080/img/1000"
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




