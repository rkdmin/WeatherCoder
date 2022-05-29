import PropTypes from "prop-types";
import { Link } from "react-router-dom";

function UserInfor(props){
    return(<div>
    <h1><Link to={`/UserInformation/${props.id}`}>회원정보</Link></h1>    
    </div>);
}
export default UserInfor;

UserInfor.prototype ={
    id:PropTypes.string.isRequired
  };