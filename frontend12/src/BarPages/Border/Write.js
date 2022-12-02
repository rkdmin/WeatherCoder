import axios from "axios"
import { useEffect } from "react"
import { useRef,useState } from "react"
import { useNavigate } from "react-router-dom"
import { listText } from "../../data"
import "./Write.moudule.css"
import pageback_btn from "../../img/pageback_btn.png"
const Write = () => {
    const [titArticle,conArticle] = [useRef(),useRef()]
    const [artMsg,setArtMsg] = useState(undefined)
    const navigate = useNavigate()
    useEffect(()=>{
        if(typeof(artMsg)==="object"){
            alert(artMsg.errorMessage)
        }
        else if(typeof(artMsg)==="string"){
            alert(artMsg)
            navigate(-1)
        }
        else return
    })
    return(<>
          <div className={"top_arrow_div"}>
          <img
            src={pageback_btn}
            width={16}
            height={16}
            className={"Myadress_pageback"}
            onClick={()=>navigate(-1)}
          />
        </div>
    <h2 className="writingName">게시글 작성하기</h2>
    <form onSubmit={e=>{(async()=>{
        try {
        const article = new listText(titArticle.current.value,conArticle.current.value)
        e.preventDefault()
        setArtMsg(await(await axios.post(`/articles/new`,article)).data)   
        } catch(error){console.log(error)}})()}}>
    <input
          className="writingTitle"
          type="text"
          placeholder="제목을 입력해주세요."
          ref={titArticle}
          minLength="1"
        />
        <br />
        <br />
        <textarea
          className="writingFillOut"
          placeholder="내용을 입력해주세요."
          ref={conArticle}
          rows="15"
          cols="60"
        />
        <br />
        <br />
        <div className="writingSubmitPR">
          <input className="writingSubmitBtn" type="submit" value="등록" />
        </div>
    </form>
    <div className="marginBox1414"/>
    </>)}
    
export default Write