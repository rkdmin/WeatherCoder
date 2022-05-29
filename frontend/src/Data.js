import Information from "./Information";
function Data(props){
    return(<div>
<Information 
    userId={props.userId} 
    email={props.email} 
    nickname={props.nickname} 
    regDate={props.regDate}/>
</div>);
}
export default Data