import axios from "axios"
import { useEffect } from "react"
import { useState } from "react"
import { useDispatch, useSelector} from "react-redux"
import { useNavigate } from "react-router-dom"
import { userInfoRegistration, userLoginInfo} from "../data"
import { addStyle,removeStyle,deduplicationStyle,clearStyle} from "../store"
const userSeasonStyle = (...rest) =>{
return(<><h3>{rest[0]}</h3>
{rest[1].map((clothing,index)=>{
  return (<div key = {index}>{clothing}<input type ="checkbox" 
  value = {clothing} onClick ={e=>
    {const action = {list:rest[2], value:e.target.value,index:rest[4]}
    if(e.target.checked){
    rest[3](addStyle(action))
    rest[3](deduplicationStyle(action))}
    else{rest[3](removeStyle(action)) }
}}/> <br/></div>)})}</>)}

const Category = ({text,type,link,index}) => {
  const selector = useSelector(item=>item)
  const {addStyleList} = selector
  const dispatch = useDispatch()
  const navigate = useNavigate()
  const selectData = Object.keys(addStyleList[index])
  const userIn = Object.keys(type)
  const [message,setMessage] = useState({})
  useEffect(()=>{dispatch(clearStyle(index))},[dispatch,index])
  return (<>
  {Object.keys(message).lengyh===2 ?alert(message.errorMessage):null}
  <h3>{text}</h3>
<form onSubmit = {e=>
{(async()=>{
    e.preventDefault()
    try {
      const userClothing = new userInfoRegistration(userLoginInfo.email,addStyleList[index])
    setMessage(await(await axios.post(link,userClothing)).data)
      navigate(-1)
  } catch (error) {
    console.log(error)}})()}}>
    {userIn.map((item,index)=>{
      return <div key={index}>{
    userSeasonStyle(userIn[index],type[item],selectData[index],dispatch,index)}
    </div>})}
<input type="submit" value = "click"/> 
  </form></>)}
export default Category



