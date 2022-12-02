import axios from "axios"
import React,{ useEffect,useRef,useState } from "react"
import { useDispatch, useSelector} from "react-redux"
import { useLocation, useNavigate } from "react-router-dom"
import { userLoginInfo,userStyleRegistration, selectUserCheck} from "../data"
import { addStyle,removeStyle,deduplicationStyle,clearStyle} from "../store"
import MenuBar from "../Bar/MenuBar"
import "./StyleChange.css"
import pageback_btn from "../img/pageback_btn.png"
const userSeasonStyle = (...rest) => {
    return (
    <div className={"Myadress_main_component"}>
        <div className={"Information_main_component_title_container"}>
        <h3 className={"Myadress_main_component_title Information_main_component_title"}>{rest[0]}</h3>
        </div>
        <form onSubmit = {e=>
{(async()=>{
    e.preventDefault()
    try {
        const userClothing = new userStyleRegistration(userLoginInfo.email,rest[8][1].style)
        rest[9](await(await axios.post(`/my-style`,userClothing)).data)
      }
   catch (error) {
    console.log(error)}})()}}>
            {rest[1].map((clothing, number) => {
                return (
                    <div className={"Myadress_main_component_items"} key={number}>
                    <input className={"Myadress_main_component_item_check"} type="checkbox"  
                    ref={rest[7]}
                    value = {rest[1][number]}
                    onClick ={e=>
                        {const action = {list:rest[2], value:e.target.value,index:rest[4]}
                        if(e.target.checked){
                          rest[3](addStyle(action))
                          rest[3](deduplicationStyle(action))}
                          else{rest[3](removeStyle(action))}}}/>
                    <span className={"Myadress_main_component_item_text"}>{clothing}</span>
                </div>)})}
            <input className={"Information_component_submit"} type="submit" value="submit" />
        </form>
    </div>)
}
export default function StyleChange() {

    const selector = useSelector(item=>item)
    const {addStyleList} = selector
    const dispatch = useDispatch()
    const navigate = useNavigate()
    const location = useLocation()
    const selectData = Object.keys(addStyleList[1])
    const userIn = Object.keys(selectUserCheck)
    const [message,setMessage] = useState({})
    const checkRef = useRef()
    useEffect(()=>{dispatch(clearStyle(1))},[dispatch])
    useEffect(()=>{if(Object.keys(message).length===2){
        if(!alert(message.errorMessage)){navigate(0)}
      }
      else if(typeof(message) === "string"){
        if(!alert(message))navigate(-1)
      }},[message,navigate])
    return (
    <>
        <div className={"Myadress_container"} style={{height:"60%"}}>
            <div className={"Myadress_innercontainer"}>
                <img src={pageback_btn} width={16} height={16} onClick={()=>navigate(-1)} className={"Myadress_pageback"}/>
                <h3 className={"Myadress_title"} style={{marginRight:"220px"}}>내정보 수정하기</h3>
                <h5 className={"Myadress_description"} style={{marginRight:"220px"}}>마이페이지&gt;내정보 수정하기</h5>
                <div className={"Myadress_maincontainer_outer"}>
                    <div className={"Myadress_maincontainer"}>
                    {userIn.map((item,num)=>
     <React.Fragment key={num} >{
      userSeasonStyle(userIn[num],selectUserCheck[item],selectData[num],dispatch,1,location,addStyleList[1],checkRef,addStyleList,setMessage)}
      </React.Fragment>)}
                    </div>
                </div>
            </div>
        </div>
        <MenuBar/>
    </>
    );
}
