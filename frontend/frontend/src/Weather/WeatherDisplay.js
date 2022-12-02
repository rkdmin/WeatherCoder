import React from "react";
import "./WeatherDisplay.css"
import weather_rain_small from "../img/weather_rain_small.png";
import weather_humidity_small from "../img/weather_humidity_small.png";
import weather_cloudy from "../img/weather_cloudy.png";

export default function Display({humidity, temp, weather, dt}) {
    const dateValue = new Date()
    const value = new Date(dt * 1000)
    return (
        <div className={"WeatherDisplay_container"}>
            {dateValue.getDate() !== value.getDate() ? null :
                value.getHours() === 10
                || value.getHours() === 16
                || value.getHours() === 20
                || value.getHours() === 23 ?
                    <div className={"NowWeather_card"}>
                        <h1 className={"NowWeather_card_top"}>{value.getHours() === 10 ?
                            "아침" : value.getHours() === 16 ?
                                "점심" : value.getHours() === 20 ?
                                    "저녁" : "밤"} </h1>
                        <div className={"NowWeather_card_maincontainer"}>

                            <div className={"NowWeather_card_mainleft"}>
                                <h1 className={"NowWeather_card_title NowWeather_card_title_top"}>온도</h1>
                                <div className={"NowWeather_card_degree"}>{Math.round(temp)}</div>
                            </div>
                            <div className={"NowWeather_card_maincenter"}></div>
                        {weather.map(R => {
                            return (
                                <div key={R.id} className={"NowWeather_card_mainright"}>
                                    <h1 className={"NowWeather_card_title NowWeather_card_title_top"}>{R.main}</h1>
                                    <img src={weather_cloudy} width={'51px'} height={'44px'} alt={R.description}/>
                                </div>
                            )
                        })}
                        </div>
                        <div className={"NowWeather_card_bottom"}>
                            <img className={"NowWeather_card_thumnail"} src={weather_rain_small} width={'16px'} height={'15.02px'}/>
                            <div className={"NowWeather_card_title"}>강수</div><div className={"NowWeather_card_title_value"}>{"10%"}</div>
                        </div>
                        <div className={"NowWeather_card_bottom"}>
                            <img className={"NowWeather_card_thumnail"} src={weather_humidity_small} width={'12px'} height={'15.81px'}/>
                            <div className={"NowWeather_card_title"}>습도</div><div className={"NowWeather_card_title_value"}>{humidity+"%"}</div>
                        </div></div>:null}</div>)}
