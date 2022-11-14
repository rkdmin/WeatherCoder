export const userClothingModify = {springObj:{spring:[]},summerObj:{summer:[]},fallObj:{fall:[]},winterObj:{winter:[]},styleObj:{style:[]}}
export const userSelect = {성별:[],연령:[],신장:[],체중:[],스타일:[]}
export const userClientInput = {id:[],email:[],password:[],password2:[],nickname:[]}
export const wheather = JSON.parse(sessionStorage.getItem("wheatherInfo"))
export const userLoginInfo = JSON.parse(sessionStorage.getItem("login_information"))
export const imgListToSever = JSON.parse(sessionStorage.getItem("imgList"))
export const no2ImData = JSON.parse(sessionStorage.getItem("No2Implements"))
export const no3ImData = JSON.parse(sessionStorage.getItem("No3Implements"))
export const infoUser = {
    봄:["니트/스웨터","맨투맨","셔츠/블라우스","후드티","가디건"],
    여름:["반팔 티셔츠","셔츠/블라우스","민소매 티셔츠","반바지","청바지"],
    가을:["청바지","맨투맨","셔츠/블라우스","후드티","니트/스웨터"],
    겨울:["청바지","코트","패딩","후드티","니트/스웨터"],
}
export const selectUserData = {
    연령: ["10 ~ 19세","20 ~ 29세","30 ~ 39세","40 ~ 49세","50세 이상"],
    성별:["남성","여성"],
    신장: ["156 ~ 160cm","160 ~ 165cm","165 ~ 170cm","170 ~ 175cm","175이상"],
    체중: ["45 ~ 55kg","56 ~ 65kg","66 ~ 75kg","76 ~ 85kg","85kg 이상"],
}
export const selectUserCheck= {
  스타일:["스트릿","댄디","캐주얼","빈티지","놈코어"]
}
export const inputTypeAndPalcehorder = {
    email:["text","E-mail"],
    password:["password","비밀번호"]
}
export const no1Data = {
  login_information:userLoginInfo?.gender,
  imgList:imgListToSever,
  img_list:false,
  gender:sessionStorage.getItem("gender")
}


export class selectDataParse{
  constructor(seasonName,nameList){
this.seasonName=seasonName
this.nameList=nameList
  }}

export class infotomyinfo{
  constructor(...rest){
    this.lowTemp=rest[0]
    this.highTemp=rest[1]
    this.gender=rest[2]
  }}

export class infotomyinfo2{
  constructor(...rest){
    this.lowTemp=rest[0]
    this.highTemp=rest[1]
    this.email=rest[2]
  }}

export class signInfo {
  constructor(...rest){
this.email = rest[0]
this.password = rest[1]
this.gender = rest[2]
this.age = rest[3]
this.height = rest[4]
this.weight = rest[5]
this.styleList = rest[6]
  }}

export class userInfoRegistration {
  constructor(email,seasonList){
      this.email = email
      this.seasonList = seasonList
  }}

export class userStyleRegistration {
  constructor(email,styleList){
    this.email = email
    this.styleList = styleList
}}  

export class listText {
  constructor(title,content){
    this.title = title
    this.content = content
}}

export class articles {
  constructor(nickname,body){
    this.nickname=nickname
    this.body=body
  }}  