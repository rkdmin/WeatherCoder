import axios from "axios"
import { useRef } from "react"
import { useEffect } from "react"
import { useState } from "react"
import { useDispatch, useSelector} from "react-redux"
import { useLocation, useNavigate } from "react-router-dom"
import { userInfoRegistration, userLoginInfo,selectDataParse,userStyleRegistration} from "../data"
import { addStyle,removeStyle,deduplicationStyle,clearStyle} from "../store"
import "./Category.moudule.css"
const userSeasonStyle = (...rest) =>{
return(<div className="pareList" 
style={rest[5].pathname==="/Input_clothing"?{ width:"30vw"}:{width:"100vw"}}>
  <h3 className="content_title_write">{rest[0]}</h3>
{rest[1].map((clothing,index)=>{
  return (<div key = {index} className="listChild" 
  ><input id="userSelectCheckBox" type ="checkbox" ref={rest[7]}
  value = {rest[1][index]} onClick ={e=>
    {const action = {list:rest[2], value:e.target.value,index:rest[4]}
    if(e.target.checked){
    rest[3](addStyle(action))
    rest[3](deduplicationStyle(action))}
    else{rest[3](removeStyle(action)) }
}}/><span className="clothText" style={rest[5].pathname==="/My_page/StyleChange"?rest[6][rest[2]].includes(clothing)?{color:"#4254ff"}:
{color:"gray"}:
  rest[6][rest[0]]?.includes(clothing)?{color:"#4254ff"}:
{color:"gray"}}>{clothing}</span>
<br/></div>)})}</div>)}

const Category = ({text,type,link,index}) => {
  const selector = useSelector(item=>item)
  const {addStyleList} = selector
  const dispatch = useDispatch()
  const navigate = useNavigate()
  const location = useLocation()
  const selectData = Object.keys(addStyleList[index])
  const userIn = Object.keys(type)
  const [message,setMessage] = useState({})
  const [state,setState] = useState([])
  const checkRef = useRef()
  useEffect(()=>{dispatch(clearStyle(index))},[dispatch,index])
  useEffect(()=>{if(Object.keys(message).length===2){
      alert(message.errorMessage)
    }
    else if(typeof(message) === "string"){
      alert(message)
      navigate(-1)
    }},[message,navigate])
useEffect(()=>{
(async()=>{
  if(!!state.length){
    const data = new userInfoRegistration(userLoginInfo.email,state)
    setMessage(await(await axios.post(link,data)).data)}
 else if(state.length>=5){
  navigate(0)}})()},[state,navigate,link])
  return (<>
  <h3 className="defaultText" style={location.pathname==="/My_page/StyleChange"?{marginLeft:"51vw"}:null}>{text}</h3>
  <h6 className="defaultTex2" style={location.pathname==="/My_page/StyleChange"?{display:"none"}:{marginLeft:"37vw"}}>*회원의 옷을 기반으로 맟춤형을 제공합니다.</h6>
<form  className="listForm" onSubmit = {e=>
{(async()=>{
    e.preventDefault()
    try {
      if(location.pathname==="/My_page/StyleChange"){
        const userClothing = new userStyleRegistration(userLoginInfo.email,addStyleList[index].style)
        setMessage(await(await axios.post(link,userClothing)).data)
      }
      else{
      for(const key in addStyleList[index]){
        if(!!addStyleList[index][key].length){
          const data = new selectDataParse(key,addStyleList[index][key])
          setState(state=>[...state,data])
        }}}
  } catch (error) {
    console.log(error)}})()}}>
  <div className="list">
    <div className="pathName">
    {userIn.map((item,num)=>item==='봄'||item==='여름'||item==='스타일'?
     <div className = "child123" style={location.pathname==="/My_page/StyleChange"?{marginLeft:"34.5vw"}:{marginLeft:"20vw"}} key={num}>{
      userSeasonStyle(userIn[num],type[item],selectData[num],dispatch,index,location,addStyleList[index],checkRef)}
      </div>
    :null)}
</div>

<div className="pathPart">
    {userIn.map((item,num)=>item==='가을'||item==='겨울'?
     <div className = "child123" 
     style={location.pathname==="/My_page/StyleChange"?null:{marginLeft:"20vw"}}
     key={num}>{
      userSeasonStyle(userIn[num],type[item],selectData[num],dispatch,index,location,addStyleList[index],checkRef)}
      </div>
    :null)}
</div>


    <div className="btnBox">
<input type="submit" className="ctgSubmit" value = "click"/> 
</div>
  </div>
  </form>
  {location.pathname==="/My_page/StyleChange"?null:<div className="marginBox6"/>}
  </>)}
export default Category



