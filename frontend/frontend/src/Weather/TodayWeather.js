import axios from "axios";
import NowWeather from "./NowWeather";
import WeatherDisplay from "./WeatherDisplay";
import WeatherAlgorithm from "./WeatherAlgorithm";
import React,{useEffect,useState,useTransition,useDeferredValue } from "react"
import "./TodayWeather.css"

const temp = (...rest) => {
    const dateValue = new Date()
    const  array = rest[0].map((R)=>{
const value = new Date(R.dt*1000);
    return dateValue.getDate()!==value.getDate()?
  null:R.temp })
  const data = array.filter(E=>E!==null)
  const hotdata = Math.max.apply(null,data)
  const colddata = Math.min.apply(null,data)
  rest[1](hotdata)
  rest[2](colddata)
  rest[3](false);
(colddata<=12||hotdata>=23)?rest[4](true):rest[4](false)}

const rain = (...rest) => {
    const dateValue = new Date()
    const rainning =
    rest[0].map((R)=>{
   const value = new Date(R.dt*1000);
      return dateValue.getDate()!==value.getDate()?
      null:R.rain??null })
      const rainData = rainning.filter(E=>E!==null);
      (rainData.length!==0)? rest[1](true): rest[1](false)
}

const TodayWeather = (props) =>{
    const  {latitude,longitude} = props
    const [weatherObject,setWeatherObject] = useState([])
    const [rainData,setRainData] = useState(false)
    const [lowTemp,setLowTemp] = useState(0)
    const [highTemp,setHighTemp] = useState(0)
    const [pageLoading,setPageLoading] = useState(true)
    const [outerClothing,setOuterClothing] = useState(false)
    const [isPending,startTransition] = useTransition()
    const weatherState = useDeferredValue(weatherObject)

    useEffect(()=>{try{
        startTransition(
         async() => {if(latitude!==0&&longitude!==0){
        setWeatherObject(
            await(
                await axios(`https://api.openweathermap.org/data/2.5/onecall?lat=${latitude}&lon=${longitude}&appid=f980d31253eb2b185606cca64544373f&units=metric`)
        ).data.hourly)
      }}
        )}catch(e){console.log(e)}
    },[props])

    const RainAndTemp = () => {
        if(weatherState.length!==0){
            temp(weatherState,setHighTemp,setLowTemp,setPageLoading,setOuterClothing)
            rain(weatherState,setRainData)
        }
    }
    useEffect(()=>RainAndTemp(),[weatherState])
    return (
        <>
            {
                isPending?
                <>
                    <h1>loading.....</h1>
                </>
                :pageLoading?
                    <>
                        <h1>오늘의 날씨</h1>
                    </>:
                    <div className={"TodayWeather_container"}>
                        <NowWeather latitude={latitude} longitude = {longitude} setDate={props.setDate}/>

                        {weatherState.map((R)=>{
                            return<WeatherDisplay
                            key={R.dt}
                            dt={R.dt}
                            humidity={R.humidity}
                            temp={R.temp}
                            weather={R.weather}
                            />})
                        }
                        <WeatherAlgorithm Htemp = {highTemp} Mtemp={lowTemp} outp = {outerClothing} rain={rainData}/>
                    </div>
            }
        </>
    )}
        export default React.memo(TodayWeather);
