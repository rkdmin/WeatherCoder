import axios from "axios";
import React, { useEffect, useState } from "react";
import "./NowWeather.css";
import {Link} from "react-router-dom";
import weather_cloudy from "../img/weather_cloudy.png";
import mypage_btn from "../img/mypage_btn.png";

import weather_humidity_small from "../img/weather_humidity_small.png";
const date = new Date()
const NowWeather = ({ latitude, longitude }) => {
  const [weatherTemp, setWeatherTemp] = useState({});
  const [realWeather, setRealWeather] = useState([]);
  const [weather,setWeather] = useState({})
  useEffect(() => {
    (async () => {
      try {
        const nowWeatherData = await (
          await axios(
            `https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=${process.env.REACT_APP_WHEATHER_API_KEY}&units=metric`
          )
        ).data;
        setWeatherTemp(nowWeatherData.main);
        setRealWeather(nowWeatherData.weather);
        setWeather(nowWeatherData)
      } catch (e) {
        console.log(e);
      }
    })();
  }, [latitude, longitude]);
  return (
    <><div className="headerPart">
    <h3 className="header_title">현재 날씨 조회</h3>
    <Link className="header_content" to={`/My_page`}>
      <img src={mypage_btn}/>
      </Link>
    </div>
    <div className="borderHR1112" />
    <div className={"NowWeather_top"}>
    <span className="date">{date.getFullYear()}-
      {date.getMonth()+1}-{date.getDate()}</span><br/><br/>
      <span className="location">{weather.name},{weather.sys?.country}</span><br/><br/>
       </div>
      <div className={"NowWeather_card"}>
        <h1 className={"NowWeather_card_top"}>현재</h1>
        <div className={"NowWeather_card_maincontainer"}>
          <div className={"NowWeather_card_mainleft"}>
            <h1 className={"NowWeather_card_title NowWeather_card_title_top"}>
              온도
            </h1>
            <div className={"NowWeather_card_degree"}>
            {!!Math.round(weatherTemp.temp) && Math.round(weatherTemp.temp)}
            </div>
          </div>

          <div className={"NowWeather_card_maincenter"}></div>
          {realWeather.map(({ id, main }) => {
            return (
              <div key={id} className={"NowWeather_card_mainright"}>
                <h1
                  className={"NowWeather_card_title NowWeather_card_title_top"}
                >
                  {main}
                </h1>
                <img
                  src={weather_cloudy}
                  width={"51px"}
                  height={"44px"}
                  alt={main}
                />
              </div>
            );
          })}
        </div>
        <br />
        <div className={"NowWeather_card_bottom"}>
          <img
            className={"NowWeather_card_thumnail"}
            src={weather_humidity_small}
            width={"12px"}
            height={"15.81px"}
          />

          <div className={"NowWeather_card_title"}>습도</div>
          <div className={"NowWeather_card_title_value"}>
            {weatherTemp.humidity + "%"}
          </div>
        </div>
      </div>
    </>
  );
};
export default React.memo(NowWeather);
