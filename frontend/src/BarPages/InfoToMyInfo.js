import {no2ImData}from"../data"
import Recommend from "../RecycleComponents/Recommend"
const InfoToMyInfo = () => {
return(
<Recommend 
   content={"내 정보로 추천받기"}
   list={"No2Implements"}
   link={"/suggest2"}
   basicList={no2ImData}/>)}
export default InfoToMyInfo