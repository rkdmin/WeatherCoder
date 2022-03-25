import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Home_page from "./Home_page";
import Information from "./Information";
import Sign_up from "./Sign_up";
function App (){
return<Router>
<Switch>
<Route path="/" exact component={Home_page}/>
<Route path="/Sign_up" component={Sign_up}/>
<Route path="/User_Information/:id" component={Information}/>
</Switch>
    </Router>;
}

export default App;