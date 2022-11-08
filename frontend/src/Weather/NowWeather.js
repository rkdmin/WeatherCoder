import axios from "axios"
import React,{ useEffect, useState} from "react"
const NowWeather = ({latitude,longitude}) => {
    const [weatherTemp,setWeatherTemp]=useState({}) 
    const [realWeather,setRealWeather]=useState([])
        useEffect(()=>{(async() =>{try{
    const nowWeatherData = await (await axios(`https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=${process.env.REACT_APP_WHEATHER_API_KEY}&units=metric`)).data
     setWeatherTemp(nowWeatherData.main);setRealWeather(nowWeatherData.weather);
    }catch(e){console.log(e);}
})()},[latitude,longitude])
return(
    <><h1>현재</h1>
    <h1>습도 : {weatherTemp.humidity}</h1>
    <h1>온도 : {Math.round(weatherTemp.temp)}</h1>
    {realWeather.map(({id,main})=>{
    return(
    <div key={id}>
    <h1>{main}</h1>
    </div>)})}</>)}
export default React.memo(NowWeather)