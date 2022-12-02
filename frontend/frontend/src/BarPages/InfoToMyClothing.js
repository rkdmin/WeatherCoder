import axios from "axios"
import MenuBar from "../Bar/MenuBar"
import { Link } from "react-router-dom"
import mypage_btn from "../img/mypage_btn.png"
import {ruby,wheather,userLoginInfo} from "../data"
import { useEffect, useState , useRef } from "react"
import "./InfoToWeather.css"

const minus = (num) => {
  return num-100
}
const plus = (num) => {
  return num+100
}

const carousel = (result,change,curent) => {
      if(!curent.current)return;
      curent.current.style.transform = `translate(${result}vw)`
      change(result)
}

const InfoToWeather = () => {

  const [regis,setRegis] = useState({})
  const [result,setResult] = useState(true)
  const [constant,setConstant] = useState(0)
  const moveRef = useRef()
  useEffect(()=>{
    (async()=>{
        const rubyLan = new ruby(wheather.Mtemp,wheather.Htemp,userLoginInfo.email)
        setRegis(await(await axios.post("/suggest3",rubyLan)).data)
        setResult(false)
      })()
    },[])

  return (
<>{result?<div>loading ...</div>:
<div className={"LogoWithMainPage_container Recommendation_container"}>
      <div className={"top_title_area"}>
        <div className={"top_title"}>내 옷들로 추천받기</div>
        <div className={"top_mypage"}>
          <Link to={`/My_page`}>
            <img src={mypage_btn} width="25" height="25" alt="마이 페이지" />
          </Link>
        </div>
      </div>
  
      <div
        className={
          "Recommendation_card_container " +
          (sessionStorage.getItem("login_information")
            ? ""
            : "InfoToWeather_card_container")
        }
      >
        <div className="Recommendation_card_container_inner_Box" ref={moveRef}  >
        <div className={"Recommendation_card_container_inner"}
        style={{marginLeft:"1.2vw",marginRight:"3vw"}}
        >
         {Object.keys(regis).length === 1?<img src={regis.clothesList[0]} className="imgTag" alt="이미지를 불러오는데 실패하였습니다."/>:Object.keys(regis).length===2?<div className="errorText">{regis.errorMessage}</div>:<></>}
          <div className="Recommendation_notice">
            <div className="Recommendation_notice1"
            style={wheather.rain?{backgroundColor:"rgb(27, 92, 212)",color:"white"}:
            {color:"rgb(27, 92, 212)",border:"2px solid rgb(27, 92, 212)",backgroundColor:"white"}}
            >우산필요</div>
          </div>
        </div>
        <div className={"Recommendation_card_container_inner"}
        style={{marginLeft:"2vw"}}>
         {Object.keys(regis).length === 1?<img src={regis.clothesList[1]} className="imgTag" alt="이미지를 불러오는데 실패하였습니다."/>:Object.keys(regis).length===2?<div className="errorText">{regis.errorMessage}</div>:<></>}
          <div className="Recommendation_notice">
            <div className="Recommendation_notice1"
            style={wheather.rain?{backgroundColor:"rgb(27, 92, 212)",color:"white"}:
            {color:"rgb(27, 92, 212)",border:"2px solid rgb(27, 92, 212)",backgroundColor:"white"}}
            >우산필요</div>
          </div>
        </div>
        </div>
        <button className="leftButton" disabled={!constant?true:false}
        style={!constant?{border:"3px solid rgb(55, 110, 211)",backgroundColor:"white",color:"rgb(55, 110, 211)"}
:
{backgroundColor:"rgb(55, 110, 211)",color:"white",border:"3px solid rgb(55, 110, 211)"}}
        onClick={()=>{
          const res = plus(constant)
          carousel(res,setConstant,moveRef)
        }}>&larr;</button>
        <button className="rightButton" disabled={!constant?false:true} 
        
        style={constant?{border:"3px solid rgb(55, 110, 211)",backgroundColor:"white",color:"rgb(55, 110, 211)"}
:
{backgroundColor:"rgb(55, 110, 211)",color:"white",border:"3px solid rgb(55, 110, 211)"}}
        onClick={()=>{
          const res = minus(constant)
          carousel(res,setConstant,moveRef)
        }}>&rarr;</button>

      </div>
      <MenuBar />
    </div>}</>
  );
};
export default InfoToWeather;
