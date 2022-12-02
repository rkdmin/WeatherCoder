import { useState } from "react";
import TodayWeather from "./TodayWeather";
import "./WeatherComponents.css";

export default function WeatherComponents() {
  const [latitude, setLatitude] = useState(0);
  const [longitude, setLongitude] = useState(0);
  const getLocation = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        ({ coords: { latitude, longitude } }) => {
          setLatitude(latitude.toFixed(4));
          setLongitude(longitude.toFixed(4));
        },
        (error) => {
          console.error(error);
        },
        { enableHighAccuracy: false, maximumAge: 0, timeout: Infinity }
      );
    } else {
      alert("GPS를 지원하지 않습니다");
    }
  };
  getLocation();

  return (
    <>
      <TodayWeather latitude={latitude} longitude={longitude} />
    </>
  );
}
