/*eslint-disable*/
import axios from "axios"
import { useEffect, useState } from "react"
import { useNavigate,useLocation,Link, useParams} from "react-router-dom"
import "./Border.moudule.css"
import mypage_btn from "../../img/mypage_btn.png"
import pen_square from "../../img/pen_square.png"
import MenuBar from "../../Bar/MenuBar"
const Border = () => {
    const [data,setData] = useState([])
    const navigate = useNavigate()
    const location = useLocation()
    const parm = useParams()
    const pageIndex = Math.floor(+location.pathname.substring(14)/10)
    useEffect(()=>{
    (async()=>{setData(await(await axios("/articles")).data)})()},[])
    
    return(<>
    <div className="topContanier">
    <div className={"top_title_area"}>
        <div className={"top_title"}>게시판</div>
        <div className={"top_mypage"}>
          <Link to={`/My_page`}>
            <img src={mypage_btn} width="25" height="25" alt="마이 페이지" />
          </Link>
        </div>
      </div>
    
      <div className="search_box">
        <input className="search" placeholder="search"></input>
        <input className="search_btn" type="submit" value="검색"/>
      </div>

        {!data.length?<div className="noData"><h4>게시글이 없습니다.</h4></div>
        :data.map((item,index)=>{
            if(pageIndex === Math.floor(index/10))
            {
              return (
                <div className="list_area" key={index}>
                  <h3 className="border_nickname">{item.title}</h3>
                  <div className="list">
                    <Link
                      className="list_link"
                      to={`/detail/${index}/comment/${item.id}`}
                    >
                      {item.content}
                    </Link>
                  </div>
                  <div className="borderHR" />
                </div>
              )}})}       
<form className="numFormBtn" onSubmit={(e) => e.preventDefault()}>
        <input
          className="previousBtn"
          type="button"
          value="<"
          style={!(pageIndex)?{border:"2px solid #4254ff",backgroundColor:"white",color:"#4254ff"}:{ backgroundColor: "#4254ff",color:"white"}}
          disabled={!pageIndex ? true : false}
          onClick={() => {navigate(`/border/route/${(pageIndex-1)*10}`)}}/>

        {!data.length?null:
        data.map((_,index)=>{
            if(!(index%10)){
              return (
                <span key={index}>
                  <input
                    className="numberBtn"
                    type="button"
                    style={+parm.index/10 === Math.floor(index/10)?{border:"2px solid #4254ff",color:"#4254ff",backgroundColor:"white"}:{  backgroundColor: "#4254ff",color:"white"}}
                    value={!index ? index : index / 10}
                    onClick={() => navigate(`/border/route/${index}`)}
                  />
                </span>
              );}})}
<input
          className="nextBtn"
          type="button"
          disabled={
            Math.floor((data.length - 1) / 10) === pageIndex ? true : false
          }
          value=">"
          style={!data.length?{border:"2px solid #4254ff",backgroundColor:"white",color:"#4254ff"}:Math.floor((data.length-1)/10)===pageIndex?{border:"2px solid #4254ff",backgroundColor:"white",color:"#4254ff"}:{ backgroundColor: "#4254ff",color:"white"}}
          onClick={() => {
            navigate(`/border/route/${(pageIndex+1)*10}`);
          }}
        />
    </form>
      <i className="fa-solid fa-pen-to-square"></i>
      <div className="writingBtnPR">
        <Link className="writingBtn" to={"/detail/write"}>
          <div className="pen_square">
            <img src={pen_square} width="20" height="20" />
          </div>
          글쓰기
        </Link>
      </div>
      </div>
      <MenuBar/>
    </>)}
export default Border