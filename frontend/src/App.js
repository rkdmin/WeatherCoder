import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Home_page from "./Home_page";
import Information from "./Information";
import Sign_up from "./Sign_up";
import Userdata from "./Userdata";
function App (){
return(
<Userdata>
<Router>
<Switch>
<Route path="/" exact component={Home_page}/>
<Route path="/Sign_up" component={Sign_up}/>
<Route path="/UserInformation/:id" component={Information}/>
</Switch>
</Router>
</Userdata>
    );
}

export default App;