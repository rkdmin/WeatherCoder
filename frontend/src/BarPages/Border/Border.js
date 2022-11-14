/*eslint-disable*/
import axios from "axios"
import { useRef } from "react"
import { useEffect, useState } from "react"
import { useNavigate,useLocation,Link, useParams} from "react-router-dom"
import "./Border.moudule.css"
const Border = () => {
    const [data,setData] = useState([])
    const navigate = useNavigate()
    const location = useLocation()
    const ref = useRef()
    const parm = useParams()
    const pageIndex = Math.floor(+location.pathname.substring(14)/10)
    useEffect(()=>{
    (async()=>{setData(await(await axios("/articles")).data)})()},[])
    return<>
    <div className={"top_title_area"}>
         <div className={"top_title"}>게시판</div>
          </div>
    
        {!data.length?<div className="noData"><h4>게시글이 없습니다.</h4></div>:data.map((item,index)=>{
    
            if(pageIndex === Math.floor(index/10)){
                return <div key={index}>
                <div className="borderHR1" />
                <div className="list_area1">
                <div className="list1">
                <div className="list1_title"> {index}.{" "}</div>
                <div className="list1_content"><Link className="list1_article" to={`/detail/${index}/Comment/${data[index].id}`}>
                    {item.title}</Link>
                    </div>
                     </div>
                    </div></div>
                  }})}                
        <form onSubmit={e=>e.preventDefault()}>
          <div className="buttonList">
        <input type = "button" 
          className="previousBtn"
        value = "&larr;" 
        style={!(pageIndex)?{border:"2px solid #4254ff",backgroundColor:"white",color:"#4254ff"}:{ backgroundColor: "#4254ff",color:"white"}}
        disabled={!(pageIndex)?true:false} 
        onClick={()=>{navigate(`/border/route/${(pageIndex-1)*10}`)}}/>
        {!data.length?null:data.map((_,index)=>{
            if(!(index%10)){
                  return( 
                    <span key={index}>
                    <input type = "button" className="numberBtn"
                    ref={ref}
                    style={+parm.index/10 === Math.floor(index/10)?{border:"2px solid #4254ff",color:"#4254ff"}:{  backgroundColor: "#4254ff",color:"white"}}
                    value = {!(index)?index:index/10} 
                    onClick = {()=>navigate(`/border/route/${index}`)}/>
                    </span>)}})}
        <input 
        type = "button" 
        className="nextBtn"
        style={!data.length?{border:"2px solid #4254ff",backgroundColor:"white",color:"#4254ff"}:Math.floor((data.length-1)/10)===pageIndex?{border:"2px solid #4254ff",backgroundColor:"white",color:"#4254ff"}:{ backgroundColor: "#4254ff",color:"white"}}
        disabled={!data.length?true:Math.floor((data.length-1)/10)===pageIndex?true:false} 
        value = "&rarr;" onClick={()=>{
            navigate(`/border/route/${(pageIndex+1)*10}`)}}/>
    </div>
        </form>

        <div className="writingBtnPR">
        <Link className="writingBtn" to={"/detail/write"}>
          글쓰기
        </Link>
      </div>
    </>}
export default Border