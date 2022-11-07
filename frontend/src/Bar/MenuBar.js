import React from "react";
import { useNavigate } from "react-router-dom";
import "./MenuBar.css";
import menu_weather from "../img/menu_weather.png";
import menu_me from "../img/menu_me.png";
import menu_home from "../img/menu_home.png";
import menu_cloth from "../img/menu_cloth.png";
import menu_board from "../img/menu_board.png";

const parm = (para, navigate) =>
  sessionStorage.getItem("login_information")
    ? navigate(`/Recommendation/${para}`)
    : (alert("로그인후 이용이 가능한 서비스 입니다."),
      navigate(`/Login`));

const MenuBar = () => {
  const navigate = useNavigate();
  // 메뉴바 컴포넌트 입니다. return 안의 영역만 css추가하시면 됩니다.
  return (
    <div className={"bottom-nav"}>
      <h3
        className={"bn-tab"}
        onClick={() => navigate(`/weather_code/Recommendation/weather`)}
      >
        <img
          src={menu_weather}
          width="24"
          height="18"
          alt="날씨 정보로 추천받기"
        />
      </h3>

      <h3 className={"bn-tab"} onClick={() => parm("Information", navigate)}>
        <img src={menu_me} width="20" height="20" alt="내 정보로 추천받기" />
      </h3>
      <h3 className={"bn-tab"} onClick={() => navigate(`/`)}>
        <img
          src={menu_home}
          width="21.25"
          height="20.07"
          alt="현재 날씨 조회"
        />
      </h3>
      <h3 className={"bn-tab"} onClick={() => parm("Clothing", navigate)}>
        <img
          src={menu_cloth}
          width="22"
          height="17.84"
          alt="내 옷들로 추천받기"
        />
      </h3>
      <h3 className={"bn-tab"} onClick={() => navigate(`/Board`)}>
        <img src={menu_board} width="22" height="22" alt="게시판" />
      </h3>
    </div>
  );
};

export default React.memo(MenuBar);
