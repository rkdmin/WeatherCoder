import axios from "axios"
import { useRef,useState } from "react"
import { articles } from "../../data"

const Write = () => {
    const [titArticle,conArticle] = [useRef(),useRef()]
    const [artMsg,setArtMsg] = useState(undefined)
    return(<>
    {typeof(artMsg)==="object"?alert(artMsg.errorMessage):
    typeof(artMsg)==="string"?alert(artMsg):null}
    <h2>게시글 작성하기</h2>
    <form onSubmit={e=>{(async()=>{
        try {
        const article = new articles(titArticle.current.value,conArticle.current.value)
        e.preventDefault()
        setArtMsg(await(await axios.post(`/articles/new`,article)).data)   
        } catch(error){console.log(error)}})()}}>
    <input type = "text" placeholder="제목" ref={titArticle} minLength="1"/>
    <br/><br/>
    <textarea placeholder="내용" ref = {conArticle} rows="15" cols="60" />
    <br/><br/>
    <input type="submit" value="작성하기"/>
    </form>
    </>)}
    
export default Write