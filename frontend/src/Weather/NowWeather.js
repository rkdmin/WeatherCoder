import axios from "axios";
import React, { useEffect, useState } from "react";
import "./NowWeather.css";
// import weather_cloudy from "../img/weather_cloudy.png";
// import weather_rain_small from "../img/weather_rain_small.png";
// import weather_humidity_small from "../img/weather_humidity_small.png";

const NowWeather = ({ latitude, longitude }) => {
  const [weatherTemp, setWeatherTemp] = useState({});
  const [realWeather, setRealWeather] = useState([]);
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
      } catch (e) {
        console.log(e);
      }
    })();
  }, [latitude, longitude]);
  return (
    <>
      <h1 className={"NowWeather_card_top"}>현재</h1>
      <h1 className={"NowWeather_card_title"}>습도 : {weatherTemp.humidity}</h1>
      <h1 className={"NowWeather_card_title NowWeather_card_title_top"}>
        온도 : {Math.round(weatherTemp.temp)}
      </h1>
      {realWeather.map(({ id, main }) => {
        return (
          <div key={id}>
            <h1>{main}</h1>
          </div>
        );
      })}
    </>
  );
};
export default React.memo(NowWeather);
