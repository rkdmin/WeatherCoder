import { useNavigate } from "react-router-dom";
const myPageLoginState = sessionStorage.getItem("login_information");
const trans = (navigate, link) => {
  myPageLoginState
    ? navigate(link)
    : alert("로그인후 이용이 가능한 서비스 입니다.");
};
const CorrectionPage = () => {
  const navigate = useNavigate();
  return (
    <>
      <h1 onClick={() => trans(navigate, `/My_page/StyleChange`)}>
        스타일 변경하기
      </h1>

      <div className={"Mypage_body_row"}>
        <img 
        // src={mypage_password} 
        alt="이미지를 불러오는데 실패 했습니다." width={24} height={24} />
        <h1 className={"Mypage_body_row_text"}>비밀번호 변경하기</h1>
        <img
          // src={mypage_goto_btn}
          alt="이미지를 불러오는데 실패 했습니다."
          width={8.33}
          height={10}
          className={"Mypage_body_row_gotobtn"}
        />
      </div>
    </>
  );
};
export default CorrectionPage;
