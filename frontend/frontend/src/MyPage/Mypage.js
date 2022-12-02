import "./Mypage.css"
import { useNavigate } from "react-router-dom"
import mypage_goto_btn from "../img/mypage_goto_btn.png"
import mypage_mycloth from "../img/mypage_mycloth.png"
import mypage_myinfo from "../img/mypage_myinfo.png"
import mypage_password from "../img/mypage_password.png"
import mypage_privacy from "../img/mypage_privacy.png"
import mypage_version from "../img/mypage_version.png"
import mypage_service from "../img/mypage_service.png"
import mypage_avatar from "../img/mypage_avatar.png"
import mypage_login_btn from "../img/mypage_login_btn.png"
import pageback_btn from "../img/pageback_btn.png"
import { userLoginInfo } from "../data"
import MenuBar from "../Bar/MenuBar"
const Mypage = () => {
  const navi = useNavigate()
  return (
    <div className={"Mypage_container"}>
            <div className="img_contanier">
            <img src={pageback_btn} width="18" height="18" alt="뒤로 가기" onClick={()=>navi(-1)}/>
            </div>
        <div className={"Mypage_top_container"}>
            <h1 className={"Mypage_title"}>마이페이지</h1>
            {sessionStorage.getItem("login_information") ? ( 
                <div className={"Mypage_logout_btn"}>
                    <img src={mypage_login_btn} width={30} height={30} className={"Mypage_logout_icon"}/>
                  <span className={"Mypage_logout_text"}
                      onClick={() => {
                          alert("로그아웃 되었습니다.")
                          sessionStorage.removeItem("login_information")
                          navi(0)}}>로그아웃</span></div>
            ) : (
                <>
                    <div className={"Mypage_logout_btn Mypage_login_btn"}>
                        <img src={mypage_login_btn} width={30} height={30} className={"Mypage_logout_icon"}/>
                      <span className={"Mypage_logout_text"} onClick={()=>navi('/Login')}>로그인
                      </span>
                    </div>
                    <div className={"Mypage_logout_btn Mypage_login_btn"}>
                        <img src={mypage_login_btn} width={30} height={30} className={"Mypage_logout_icon"}/>
                        <span className={"Mypage_logout_text"} onClick={()=>navi('/Sign_up')}>회원가입
                        </span>
                    </div>
                </>
            )}
        </div>

      {!userLoginInfo?null:<div className="Mypage_avatar_container">
            <img src={mypage_avatar} className="Mypage_avatar"/>
            <div className="Mypage_myinfo_container">
                <div className="Mypage_myinfo_text">e-mail : </div>
                <div className="Mypage_myinfo_text">{userLoginInfo.email}</div>
            </div>
        </div>}
        
        <div className={"Mypage_body_container"}>
            <h1 className={"Mypage_subtitle"}>정보</h1>
            <div className={"Mypage_body_row"}>
                <img src={mypage_privacy} width={24} height={24}/> <h1 className={"Mypage_body_row_text"}>개인정보 수집 및 이용</h1>
            </div>
            <div className={"Mypage_body_row"}>
                <img src={mypage_service} width={18} height={20}/><h1 className={"Mypage_body_row_text"}>서비스 이용약관</h1>
            </div>
            <div className={"Mypage_body_row"}>
                <img src={mypage_version} width={24} height={24}/><h1 className={"Mypage_body_row_text"}>버전 정보</h1>
            </div>
        </div>
        <hr className={"Mypage_body_split"}/>
        <div className={"Mypage_body_container"}>
            <h1 className={"Mypage_subtitle"}>개인정보 등록</h1>
            <div className={"Mypage_body_row"}>
                <img src={mypage_mycloth} width={24} height={24}/>
                <h1 className={"Mypage_body_row_text"}
                    onClick={() =>
                        sessionStorage.getItem("login_information")
                            ? navi(`/Input_clothing`)
                            : alert("로그인후 이용이 가능한 서비스 입니다.")
                    }
                >
                    자신의 옷 등록하기
                </h1>
                <img src={mypage_goto_btn} width={8.33} height={10} className={"Mypage_body_row_gotobtn"}/>
            </div>
            <div className={"Mypage_body_row"}>
                <img src={mypage_myinfo} width={24} height={24}/>
                <h1 className={"Mypage_body_row_text"}
                    onClick={() =>
                        sessionStorage.getItem("login_information")
                            ? navi(`/My_page/StyleChange`)
                            : alert("로그인후 이용이 가능한 서비스 입니다.")
                    }
                >
                    내정보 수정하기
                </h1>
                <img src={mypage_goto_btn} width={8.33} height={10} className={"Mypage_body_row_gotobtn"}/>
            </div>
            <div className={"Mypage_body_row"}>
                <img src={mypage_password} width={24} height={24}/> <h1 className={"Mypage_body_row_text"}>비밀번호 변경하기</h1>
                <img src={mypage_goto_btn} width={8.33} height={10} className={"Mypage_body_row_gotobtn"}/>
            </div>
        </div><br/><br/><br/><br/><br/>
        <MenuBar/>
    </div>
  );
};
export default Mypage;
