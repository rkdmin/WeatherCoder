import axios from "axios";
import React,{ useEffect, useState,useTransition, useDeferredValue } from "react";
import "./NowWeather.css"
import weather_cloudy from "../img/weather_cloudy.png";
import weather_rain_small from "../img/weather_rain_small.png";
import weather_humidity_small from "../img/weather_humidity_small.png";

// eslint-disable-next-line react/prop-types
const NowWeather = ({latitude,longitude,setDate}) => {
   const [weatherTemp,setWeatherTemp]=useState({})
   const [realWeather,setRealWeather]=useState([])
   const [isPending,startTransition] = useTransition()
   const nowState = useDeferredValue(realWeather)
   const nowTemp = useDeferredValue(weatherTemp)
        useEffect(()=>{ try{startTransition( async() =>{
    const nowWeatherData = (await axios(`https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=f980d31253eb2b185606cca64544373f&units=metric`)).data
     setWeatherTemp(nowWeatherData.main);setRealWeather(nowWeatherData.weather);setDate(nowWeatherData)
    })}catch(e){console.log(e)}},[]) 
return(
    <div className={"NowWeather_card"}>
        <h1 className={"NowWeather_card_top"}>현재</h1>
        <div className={"NowWeather_card_maincontainer"}>
            <div className={"NowWeather_card_mainleft"}>
                <h1 className={"NowWeather_card_title NowWeather_card_title_top"}>온도</h1>
                <div className={"NowWeather_card_degree"}>{!!Math.round(weatherTemp.temp) && Math.round(weatherTemp.temp)}</div>
            </div>
            <div className={"NowWeather_card_maincenter"}></div>
                {nowState.map(P=>{
                    return(
                    <div key={P.id} className={"NowWeather_card_mainright"}>
                        <h1 className={"NowWeather_card_title NowWeather_card_title_top"}>{P.main}</h1>
                        <img src={weather_cloudy} width={'51px'} height={'44px'} alt={P.main}/>
                    </div>)
                })}
        </div>
        <div className={"NowWeather_card_bottom"}>
            <img className={"NowWeather_card_thumnail"} src={weather_rain_small} width={'16px'} height={'15.02px'}/>
            <div className={"NowWeather_card_title"}>강수</div><div className={"NowWeather_card_title_value"}>{"10%"}</div>
        </div>
        <div className={"NowWeather_card_bottom"}>
            <img className={"NowWeather_card_thumnail"} src={weather_humidity_small} width={'12px'} height={'15.81px'}/>
            <div className={"NowWeather_card_title"}>습도</div><div className={"NowWeather_card_title_value"}>{nowTemp.humidity+"%"}</div>
        </div>
    </div>
)
}
export default React.memo(NowWeather);
