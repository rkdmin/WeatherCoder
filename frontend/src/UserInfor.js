import PropTypes from "prop-types";
import { Link } from "react-router-dom";
function UserInfor(props){
    return(<div>
    <h1><Link to={`/User_Information/${props.id}`}>{props.text}</Link></h1>
    </div>);
}
export default UserInfor;

UserInfor.prototype ={
    id:PropTypes.string.isRequired,
    text:PropTypes.string.isRequired
  };