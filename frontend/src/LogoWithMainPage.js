/*eslint-disable*/
import { useState, useEffect } from "react";
import { useCookies } from "react-cookie";
import WeatherComponents from "./Weather/WeatherComponents";
import moment from "moment/moment";
import "./LogoWithMainPage.css";
import mypage_btn from "./img/mypage_btn.png";
const key = "Main Logo";
const LogoWithMainPage = ({ setWait }) => {
  const [cookie, setCookie] = useCookies([key]);
  const [logo, setLogo] = useState(true);
  const mon = moment();
  useEffect(() => {
    if (!cookie[key]) {
      setWait(true);
      setTimeout(() => {
        setLogo(false);
        setWait(false);
      }, 3000);
      if (logo) {
        mon.add(2, "h");
        setCookie(key, "true", { path: "/", expires: mon.toDate() });
      }
    } else {
      setLogo(false);
    }
  }, []);
  return (
    <>
      {logo ? (
        <>
          <div className={"Logo_container1"}>
            <div className={"Logo_logo"}></div>
          </div>
        </>
      ) : (<><br/><WeatherComponents/><br/></>)}</>)}
export default LogoWithMainPage;