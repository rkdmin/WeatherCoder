import {ChangeToUserData} from "./Userdata";
import { Link } from "react-router-dom";
function Information (){
    return (
<ChangeToUserData.Consumer>
    {(H)=><div> 
    <h1>User Information</h1>
    <h3>Id : {H.Id}</h3>
    <h3>Email : {H.Email}</h3>
    <h3>NickName : {H.Nickname}</h3>
    <Link to = '/'> 홈페이지 </Link>
    </div>}
</ChangeToUserData.Consumer>
    );
}
export default Information;