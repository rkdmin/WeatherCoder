import { Link } from "react-router-dom"
import React, { useState, useEffect } from "react"
import MenuBar from "./Bar/MenuBar"
import WeatherComponents from "./Weather/WeatherComponents"
import "./LogoWithMainPage.css"
import mypage_btn from "./img/mypage_btn.png"

const LogoWithMainPage = () => {
  const [logo, setLogo] = useState(true)
  const input = () => {
    if (sessionStorage.getItem("logo") === null) {
      setTimeout(() => {
        setLogo(false)}, 3000)
      if (logo) {
        sessionStorage.setItem("logo", "checked")}
    } else {setLogo(false)}} 
  useEffect(() => {input()},[])
  return (
    <>
      {sessionStorage.getItem("logo") === null ? (
        <div className={"Logo_container1"}>
          <div className={"Logo_logo"}></div>
        </div> 
      ) : (
        <div className={"LogoWithMainPage_container"}>
          <div className={"top_title_area"}>
            <div className={"top_title"}>현재 날씨 조회</div>
            <div className={"top_mypage"}>
              <Link to={`/My_page`}><img
                  src={ mypage_btn }
                  width='25'
                  height='25'
                  alt='마이 페이지' /></Link>
            </div>
          </div>
          <WeatherComponents /> 
          <MenuBar/>
        </div>
      )}
    </>
  );
};
export default LogoWithMainPage;
