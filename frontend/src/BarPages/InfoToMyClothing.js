import { no3ImData } from "../data"
import Recommend from "../RecycleComponents/Recommend"
const InfoToMyClothing = () =>{
    return(
   <Recommend 
   content={"내 옷들로 추천받기"}
   list={"No3Implements"}
   link={"/suggest3"}
   basicList={no3ImData}/>)}
export default InfoToMyClothing