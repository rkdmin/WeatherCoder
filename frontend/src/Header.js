import { useNavigate,Link,useLocation } from "react-router-dom"
const Header = () => {
    const location = useLocation()
const navigate = useNavigate()
return(<><h2 onClick = {()=>navigate('/')} style = {{display:"inline-block"}}>Weather Coder Logo</h2>
    {location.pathname==="/My_page"?null:<Link to = {`/My_page`}>마이 페이지</Link>}
    <br/></>)}
export default Header 