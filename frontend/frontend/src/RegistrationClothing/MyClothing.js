import axios from "axios"
import React,{ useState,useEffect,useRef } from "react"
import { useDispatch, useSelector} from "react-redux"
import { useLocation, useNavigate } from "react-router-dom"
import { userInfoRegistration, userLoginInfo,selectDataParse,infoUser} from "../data"
import { addStyle,removeStyle,deduplicationStyle,clearStyle} from "../store"
import MenuBar from "../Bar/MenuBar"
import pageback_btn from "../img/pageback_btn.png"
import "./MyClothing.css"
const userSeasonStyle = (...rest) => {
    return (
      <div className={"Myadress_main_component"}>
        <h3 style={{"margin": "0px"}} className={"Myadress_main_component_title"}>{rest[0]}</h3>
        {rest[1].map((clothing,number) => {
            return (
                <div className={"Myadress_main_component_items"} key={number}>
                    <input className={"Myadress_main_component_item_check"}
                type="checkbox"  value = {rest[1][number]} ref={rest[7]}
                onClick={e=>
                    {const action = {list:rest[2], value:e.target.value,index:rest[4]}
                    if(e.target.checked){
                      rest[3](addStyle(action))
                      rest[3](deduplicationStyle(action))}
                      else{rest[3](removeStyle(action))}}}/>
                    <span className={"Myadress_main_component_item_text"}
                     style={rest[6][rest[0]]?.includes(clothing)?
                     {color:"#4254ff"}:{color:"#54595E"}}>
                        {clothing}</span></div>)})}</div>)}

const MyClothing = () => {
    const navigate = useNavigate()
    const selector = useSelector(item=>item)
    const {addStyleList} = selector
    const dispatch = useDispatch()
    const location = useLocation()
    const selectData = Object.keys(addStyleList[0])
    const userIn = Object.keys(infoUser)
    const [message,setMessage] = useState({})
    const [state,setState] = useState([])
    const checkRef = useRef()
    useEffect(()=>{dispatch(clearStyle(0))},[dispatch])
    useEffect(()=>{if(Object.keys(message).length===2){
        alert(message.errorMessage)}
      else if(typeof(message) === "string"){
        alert(message)
        navigate(-1)}},[message,navigate])
  useEffect(()=>{
  (async()=>{
    if(!!state.length){
      const data = new userInfoRegistration(userLoginInfo.email,state)
      setMessage(await(await axios.post("/my-clothes",data)).data)}
   else if(state.length>=5){navigate(0)}})()},[state,navigate])
    return (
        <><div className={"Myadress_container"}>
        <div className={"Myadress_innercontainer"}>
        <img src={pageback_btn} width={16} height={16} onClick={()=>navigate(-1)} 
        className={"Myadress_pageback"}/>
        <h3 className={"Myadress_title"}>자신의옷 등록하기</h3>
        <h5 className={"Myadress_description"}>* 회원의 옷을 기반으로 맞춤형 추천을 제공합니다.</h5>
        <form onSubmit={e => {
          (async()=>{e.preventDefault()
            try {
              for(const key in addStyleList[0]){
                if(!!addStyleList[0][key].length){
                  const data = new selectDataParse(key,addStyleList[0][key])
                  setState(state=>[...state,data])
                }}
          } catch (error) {
            console.log(error)}})()
            }} className={"Myadress_maincontainer_outer"}>
        <div className={"Myadress_maincontainer"}>
        {userIn.map((item,num)=><React.Fragment key={num}>{
        userSeasonStyle(userIn[num],infoUser[item],selectData[num],
        dispatch,0,location,addStyleList[0],checkRef)}</React.Fragment>)}
        </div><input className={"Myadress_submit"} type="submit" value="submit"/>
        </form></div></div>
        <MenuBar/></>)}

export default MyClothing
