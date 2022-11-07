import { selectUserCheck } from "../data"
import Category from "../RecycleComponents/Category"
    const StyleChange = () => {
        return (<Category 
            text={"스타일 변경하기"} 
            link={`/my-style`}
            type={selectUserCheck} 
            index={1}/>)}
      export default StyleChange