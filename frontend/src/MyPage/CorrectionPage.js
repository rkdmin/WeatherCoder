import { useNavigate } from "react-router-dom"
const myPageLoginState = sessionStorage.getItem("login_information")
const trans = (navigate,link) => {
    (myPageLoginState)?
        navigate(link):
        alert("로그인후 이용이 가능한 서비스 입니다.")
}
const CorrectionPage = () => {
    const navigate = useNavigate()
    return(<>
        <h1 onClick={()=>trans(navigate,`/My_page/StyleChange`)}>스타일 변경하기</h1>
        <h1>비밀번호 변경하기</h1>
    </>)
}
export default CorrectionPage
//한다은