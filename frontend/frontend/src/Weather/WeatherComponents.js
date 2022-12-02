import React, { useState } from "react";
import TodayWeather from "./TodayWeather";
import "./WeatherComponents.css"

export default function WeatherComponents(){
    const [latitude,setLatitude] = useState(0)
    const [longitude,setLongitude]=useState(0)
    const [date,setDate] = useState({})
    const getLocation = () => {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
          setLatitude(position.coords.latitude.toFixed(4))
          setLongitude(position.coords.longitude.toFixed(4))
        },(error) => {
          console.error(error);
        }, {
          enableHighAccuracy: false,
          maximumAge: 0,
          timeout: Infinity
        });
      } else {
        alert('GPS를 지원하지 않습니다');
      }
    }

    getLocation()
    const dateAraea = new Date();
    return(
    <div className={"WeatherComponents_container"}>
        <div className={"WeatherComponents_date"}>{dateAraea.getFullYear()+"-"+(dateAraea.getMonth()+1)+"-"+dateAraea.getDate()}</div>
        <div className={"WeatherComponents_location"}>{!!date && date.name+" , "+date.sys?.country}</div>
       <TodayWeather latitude={latitude} longitude = {longitude} setDate={setDate}/>
    </div>
    )
}
