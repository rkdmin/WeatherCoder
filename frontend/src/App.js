/*eslint-disable*/
import Header from "./Header"
import { useState } from "react"
import IdFind from "./Find/IdFind"
import MenuBar from "./Bar/MenuBar"
import Mypage from "./MyPage/Mypage"
import Sign_up from "./LogInAndSignUp/Sign_up"
import { Routes, Route } from "react-router-dom"
import LogoWithMainPage from "./LogoWithMainPage"
import InfoToMyInfo from "./BarPages/InfoToMyInfo"
import Login_page from "./LogInAndSignUp/Login_page"
import InfoToWeather from "./BarPages/InfoToWeather"
import MyClothing from "./RegistrationClothing/MyClothing"
import InfoToMyClothing from "./BarPages/InfoToMyClothing"
import CorrectionPage from "./MyPage/CorrectionPage"
import StyleChange from "./ChangeInformatein/StyleChange"
import Border from "./BarPages/Border/Border"
import Comment from "./BarPages/Border/Comment"
import Write from "./BarPages/Border/Write"
import WritingLetter from "./BarPages/Border/WritingLetter"

const loginState = () => {
    const gett = sessionStorage.getItem("login_information")
    const user = JSON.parse(gett) 
    return user?.email}

export default function App (){
    const login = loginState()
const [wait,setWait] = useState(false)
return(
<>
{wait?null:<Header/>}<Routes>
<Route path="*" element = {<h1>404 ERROR :&#41;</h1>}/>
<Route path="/Border" element={<Border/>}>
<Route path="route/:index" element={null}/></Route>
<Route path="/detail/:index" element={<WritingLetter/>}>
<Route path="Comment/:id" element={<Comment/>}/></Route>
<Route path="/detail/write" element={<Write/>}/>
<Route path="/My_page" element={<Mypage/>}/>
<Route path="/Recommendation/weather" element={<InfoToWeather/>}/>
<Route path="/" element={<LogoWithMainPage setWait={setWait}/>}/>
<Route path="/Id_find" element={!!login?<LogoWithMainPage/>:<IdFind/>}/>
<Route path="/Sign_up" element={!!login?<LogoWithMainPage/>:<Sign_up/>}/>
<Route path="/Login" element={!!login?<Mypage/>:<Login_page/>}/>
<Route path="/My_page/Correction" element={!!login?<CorrectionPage/>:<Login_page/>}/>
<Route path="/Input_clothing" element={!!login?<MyClothing/>:<Login_page/>}/>
<Route path="/Recommendation/Information" element={!!login?<InfoToMyInfo/>:<Login_page/>}/>
<Route path="/Recommendation/Clothing" element={!!login?<InfoToMyClothing/>:<Login_page/>}/>
<Route path="/My_page/StyleChange" element={!!login?<StyleChange/>:<Login_page/>}/>
</Routes>{wait?null:<MenuBar/>}</>)}