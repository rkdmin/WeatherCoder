import axios from "axios"
import { useEffect } from "react"
import { useState } from "react"
import { useDispatch, useSelector} from "react-redux"
import { useLocation, useNavigate } from "react-router-dom"
import { userInfoRegistration, userLoginInfo,selectDataParse,userStyleRegistration} from "../data"
import { addStyle,removeStyle,deduplicationStyle,clearStyle} from "../store"
import "./Category.moudule.css"

const userSeasonStyle = (...rest) =>{
return(<div className="pareList" 
style={rest[5].pathname==="/Input_clothing"?{ width:"45vw"}:{width:"100vw"}}>
  <h3 className="content_title_write">{rest[0]}</h3>
{rest[1].map((clothing,index)=>{
  return (<div key = {index} className="listChild" 
  style={rest[5].pathname ==="/Input_clothing"?{}:{}}
  ><input id="userSelectCheckBox" type ="checkbox" 
  value = {clothing} onClick ={e=>
    {const action = {list:rest[2], value:e.target.value,index:rest[4]}
    if(e.target.checked){
    rest[3](addStyle(action))
    rest[3](deduplicationStyle(action))}
    else{rest[3](removeStyle(action)) }
}}/><span className="clothText">{clothing}</span>
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
  <h3 className="defaultText">{text}</h3>
  <div className="borderHR12"/>
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
    {userIn.map((item,num)=>{
      return <div className = "child" key={num}>{
    userSeasonStyle(userIn[num],type[item],selectData[num],dispatch,index,location)}
    </div>})}
    <div className="btnBox">
<input type="submit" className="ctgSubmit" value = "click"/> 
</div>
  </div>
  </form>
  <div className="marginBox6"/>
  </>)}
export default Category



