/*eslint-disable*/
import { useEffect } from "react"
const WeatherAlgorithm = (props) =>{
useEffect(()=>{
    console.log("당일 최고 온도: "+Math.round(props.htemp)
    +" , "+"당일 최저 온도: "+Math.round(props.ltemp)
    +" , "+"강수여부: "+props.rain)},[])
    sessionStorage.setItem("wheatherInfo",JSON.stringify(props))
    return <></>
}
export default WeatherAlgorithm