import { Link,useNavigate } from "react-router-dom"
const myPageLoginState = sessionStorage.getItem("login_information")
const Mypage = () =>{
const navi = useNavigate()
return(
<>{myPageLoginState?
<><span onClick={()=>{
alert("로그아웃 되었습니다.")
sessionStorage.removeItem("login_information")
navi(0)}}>로그아웃</span> </>:<>
<span><Link to ={`/Login`}>로그인</Link></span>
<span><Link to = {`/Sign_up`}>회원가입</Link></span></>}
<h1>정보</h1><hr/>
<h1>개인정보 처리 방침</h1>
<h1>서비스 이용약관</h1>
<h1 onClick={()=>(myPageLoginState)?
        navi(`/Input_clothing`):alert("로그인후 이용이 가능한 서비스 입니다.")}>자신의 옷 등록하기</h1>
<h1 onClick={()=>(myPageLoginState)?
        navi(`/My_page/Correction`):alert("로그인후 이용이 가능한 서비스 입니다.")}>내정보 수정하기</h1>
<h1>Weather Coder</h1></>)}
export default Mypage