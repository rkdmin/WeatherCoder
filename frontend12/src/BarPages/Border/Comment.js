import axios from "axios"
import { useMemo } from "react"
import { useEffect, useRef, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import { articles } from "../../data"
import "./Comment.moudule.css"
const Comment = () => {
    const [commentList,setCommentList] = useState([])
    const [message,setMessage] = useState({})
    const [creRef,creNickName] = [useRef(),useRef()]
    const parms = useParams()
    const navigate = useNavigate()
    useMemo(()=>{
        (async()=>{try{
            setCommentList(await(await axios(`/articles/${parms.id}/comments`)).data)  
            }catch(error){console.log(error)}})()},[parms])
            useEffect(()=>{
                if(Object.keys(message).length === 2){
                    alert(message.errorMessage)}
                else if(typeof(message) === "string"){
                    if(!alert(message))navigate(0)}},[message,navigate])
return(<>
    {commentList.map((item,index)=>{return(<div key={index} className="commentList">
        <h4 className="comment">{item.nickname} : {item.body}</h4></div>)})}
         <div className="borderHR171" />
<h4 className="commentWriteTitle">댓글 작성하기</h4>
<form onSubmit={e=>{
    (async()=>{try {
    e.preventDefault()
    const crefContent = new articles(creNickName.current.value,creRef.current.value)
    setMessage(await(await axios.post(`/articles/${parms.id}/comment/new`,crefContent)).data)
    }catch(error){console.log(error)}})()}}>
    <input className="nickname" type = "text"  placeholder="닉네임" ref = {creNickName}/><br/>
      <textarea className="commentField"placeholder="댓글을 입력하세요."rows="5"cols="80"ref={creRef}/>
        <div className="commentSubmitPR">
          <input className="commentSubmit" type="submit" value="제출" />
        </div>
    </form><div className="marginBox7"/></>)}
export default Comment