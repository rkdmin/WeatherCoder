/*eslint-disable*/
import axios from "axios"
import { useEffect, useState } from "react"
import { useNavigate,useLocation,Link} from "react-router-dom"

const Border = () => {
    const [data,setData] = useState([])
    const navigate = useNavigate()
    const location = useLocation()
    const pageIndex = Math.floor(+location.pathname.substring(14)/10)
    useEffect(()=>{
    (async()=>{setData(await(await axios("/articles")).data)})()},[])
    return<>
    <h1>게시판</h1>
    {!data.length?<h4>게시글이 없습니다.</h4>:data.map((item,index)=>{
        if(pageIndex === Math.floor(index/10)){
            return <div key={index}>{index}. <Link to={`/detail/${index}/Comment/${data[index].id}`}>
                {item.title}</Link></div>}})}
    <form onSubmit={e=>e.preventDefault()}>
    <input type = "button" 
    value = "prev" 
    disabled={!(pageIndex)?true:false} 
    onClick={()=>{navigate(`/border/route/${(pageIndex-1)*10}`)}}/>
    {!data.length?null:data.map((_,index)=>{
        if(!(index%10)){
              return( 
                <span key={index}>
                <input type = "button" 
                value = {!(index)?index:index/10} 
                onClick = {()=>navigate(`/border/route/${index}`)}/>
                </span>)}})}
    <input 
    type = "button" 
    disabled={!data.length?true:Math.floor((data.length-1)/10)===pageIndex?true:false} 
    value = "next" onClick={()=>{
        navigate(`/border/route/${(pageIndex+1)*10}`)}}/>

    </form>

<Link to={'/detail/write'}>게시글 작성</Link>
    </>}
export default Border