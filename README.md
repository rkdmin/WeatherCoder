# WeatherCoder

## 옷추천 기능 
### 1. suggest1
GET http://localhost:8080/suggest1
Content-Type: application/json

{
"lowTemp" : 1,
"highTemp" : 11111,
"gender" : "남성"
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



