import axios from "axios"
import { useMemo,useState } from "react"
import { Outlet,useNavigate,useParams } from "react-router-dom"
import anonymous_blue from "../../img/anonymous_blue.png"
import pageback_btn from "../../img/pageback_btn.png"
import "./WrittingLetter.moudule.css"
const WritingLetter = () => {
    const parms = useParams()
    const navigate = useNavigate()
    const [contentOb,setContetOb] = useState({})
    useMemo(()=>{
        (async()=>{
            try {
    setContetOb(await(await axios(`/articles/${parms.id}`)).data)  
            } catch(error){console.log(error)}})()},[parms])
const {title,content} = contentOb
return(<>
    <div className="write_contanier"><img src={pageback_btn} width="18" height="18" onClick={()=>navigate(-1)}/></div>
  <div className="writing_person">
    <img src={anonymous_blue} width="30" height="30" />
    <h1 className="writing_nickname">익명</h1>
  </div>
  <div className="borderHR"/>
  <h1 className="writingTitle2">{title}</h1>
  <h3 className="writingFillOut2">{content}</h3>
  <div className="delBtnPR">
  </div>
  <Outlet></Outlet>
</>)}
export default WritingLetter