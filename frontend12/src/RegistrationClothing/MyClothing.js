import { infoUser } from "../data"
import Category from "../RecycleComponents/Category"
const MyClothing = () => {
  return (<Category 
    text = {"자신의 옷 등록하기"} 
    link={`/my-clothes`} 
    type={infoUser}
    index={0}
    />)}
export default MyClothing



