import axios from "axios";
import { useState,useEffect,useRef} from "react";
import { Link,useNavigate } from "react-router-dom";
import "./Sign_up.css";
import { FaChevronDown } from "react-icons/fa";
import {signInfo,selectUserData,userSelect,userClientInput,inputTypeAndPalcehorder,selectUserCheck,} from "../data";

const basicSetting = Object.keys(selectUserData);
const userSetting = Object.keys(inputTypeAndPalcehorder);
const styleObject = Object.keys(selectUserCheck);


const UserInFormationRadio = (...rest) => {
  const [ageSelected, setAgeSelected] = useState(true);//default
  const [genderSelected, setGenderSelected] = useState(true);
  const [heightSelected, setHeightSelected] = useState(true);
  const [weightSelected, setWeightSelected] = useState(true);

  const getClassName = () => {
    if (rest[1] === "성별" && genderSelected)
      return "Sign_up_selectbox__active";
    else if (rest[1] === "연령" && ageSelected)
      return "Sign_up_selectbox__active";
    else if (rest[1] === "신장" && heightSelected)
      return "Sign_up_selectbox__active";
    else if (rest[1] === "체중" && weightSelected)
      return "Sign_up_selectbox__active";
    else return "";
  };

  const onSelectChoice = () => {
    switch (rest[1]) {
      case "성별":
        setGenderSelected(!genderSelected);
        break;
      case "연령":
        setAgeSelected(!ageSelected);
        break;
      case "신장":
        setHeightSelected(!heightSelected);
        break;
      case "체중":
        setWeightSelected(!weightSelected);
        break;
      default:
        return;
    }
  };

  const getClassName2 = () => {
    if (rest[1] === "성별" && genderSelected)
      return "Sign_up_selectcontainer__active";
    else if (rest[1] === "연령" && ageSelected)
      return "Sign_up_selectcontainer__active";
    else if (rest[1] === "신장" && heightSelected)
      return "Sign_up_selectcontainer__active";
    else if (rest[1] === "체중" && weightSelected)
      return "Sign_up_selectcontainer__active";
    else return "";
  };
  return (
    <div key={rest[3]}>
      <h3
        className={`Sign_up_selectbox ${getClassName()}`}
        onClick={() => onSelectChoice()}
      >
        <div className={"Sign_up_selectlabel"}>{rest[1]}</div>
        <FaChevronDown className={"Sign_up_selectboxright"}></FaChevronDown>
      </h3>
      {/**/}
      <div className={`Sign_up_selectcontainer ${getClassName2()}`}>
        {rest[0].map((childInformation, index) => {
          return (
            <div
              className={"Sign_up_selecteachrow"}
              key={rest[1] + ":" + `${childInformation}`}
            >
              <input
                className={"Sign_up_radiobtn"}
                key={Date.now()}
                type="radio"
                name={rest[1]}
                ref={rest[4]}
                value={childInformation === "남성" || childInformation === "여성"
                ? childInformation
                : index + 1}
                onClick={(e) => {
                  if (!rest[2].length) {
                    rest[2].push(e.target.value);
                  } else {
                    rest[2].splice(rest[2].indexOf(e.target.value), 1);
                    rest[2].push(e.target.value);
                  }
                }}
              />{" "}
              <div className={"Sign_up_radiolabel"}>{childInformation}</div>
            </div>
          );
        })}
      </div>
    </div>
  );
};
const userInFormationInput = (...rest) => {
  return (
    
      <input
      key={rest[3]}
      className={"Sign_up_ID"}
        type={rest[0]}
        placeholder={rest[1]}
        onBlur={(e) => {
          rest[2] && rest[2].push(e.target.value);
          rest[2] && rest[2].splice(0, rest[2].length - 1);
        }}
      />
  
  );
};

const UserInFormationCheckBox = (...rest) => {
  const [styleSelected, setStyleSelected] = useState(true);

  const getClassName = () => {
    if (rest[0] === "스타일" && styleSelected)
      return "Sign_up_selectbox__active";
  };

  const onSelectChoice = () => {
    switch (rest[0]) {
      case "스타일":
        setStyleSelected(!styleSelected);
        break;
    }
  };

  const getClassName2 = () => {
    if (rest[0] === "스타일" && styleSelected)
      return "Sign_up_selectcontainer__active";
  };

  return (
    <div key={rest[0]}>
    <h3
      className={`Sign_up_selectbox ${getClassName()}`}
      onClick={() => onSelectChoice()}
    >
      <div className={"Sign_up_selectlabel"}>{rest[0]}</div>
      <FaChevronDown className={"Sign_up_selectboxright"}></FaChevronDown>
    </h3>
    {/**/}
    <div className={`Sign_up_selectcontainer ${getClassName2()}`}>
      {rest[1].map((clothing, index) => {
        return (
          <div key={index} className="checkbox_selector">
            <input
             className={"Sign_up_radiobtn"}
              type="checkbox"
              value={clothing}
              onClick={(e) => {
                if (e.target.checked) {
                  rest[2].push(e.target.value);
                  rest[2].filter(
                    (element, index) => rest[2].indexOf(element) === index
                  );
                } else {
                  rest[2].splice(rest[2].indexOf(e.target.value), 1);
                }
              }}
            />{" "}
 <div className={"Sign_up_radiolabel"}>{clothing}</div>
          </div>
        );
      })}
    </div></div>
  );
};


export default function Sign_Up() {
  const trans = useNavigate();
  const [isFirst, setIsFirst] = useState(true);
  const [result, setResult] = useState({});
  const ref = useRef() 
  useEffect(() => {
    if (Object.keys(result).length === 2) {
      alert(result.errorMessage);
    } else if (typeof result === "string") {
      alert(result);
      window.history.back();
    } else return;
  }, [result]);
  
  return (
    <div className={"Sign_up_container"}>
      <div
        className={"Sign_up_container_1"}
        style={{ display: isFirst ? "flex" : "none" }}
      >
        <h1 className={"Sign_up_loginpage_title"}>Sign Up</h1>
        <div className={"Sign_up_normaltext"}>
          이미 계정이 있으신가요?{" "}
          <Link to={"/weather_coder/Login"} className="Sign_up_boldtext">
            로그인하기
          </Link>
        </div>
      </div>
      {/**/}
      <div
        className={"Sign_up_container_2"}
        style={{ display: !isFirst ? "flex" : "none" }}
      >
        <h1 className={"Sign_up_title1"}>Weather Style 회원정보 입력</h1>
        <h1 className={"Sign_up_title2"}>User Information</h1>
        <div className={"Sign_up_title3"}>
          * 회원 정보 기반 맞춤형 추천을 제공합니다.
        </div>
      </div>
      {/**/}
      <form onSubmit={(e) => {(async () => {
            e.preventDefault();
            const { password, email } = userClientInput;
            const { 성별, 연령, 신장, 체중, 스타일 } = userSelect;
            const signIn = new signInfo(email[0],password[0],성별[0],연령[0],신장[0],체중[0],스타일);
            try {
              setResult(await(await axios.post("/join", signIn)).data);
            } catch (e) {
              console.log(e);
            }
          })();}}>
        <div
          className={"Sign_up_form"}
          style={{ display: isFirst ? "flex" : "none" }}
        >

{userSetting.map((item, index) =>
          userInFormationInput(
            inputTypeAndPalcehorder[userSetting[index]][0],
            item,
            userClientInput[item],
            index
          )
        )}
          <button
            className="Sign_up_in"
            onClick={(e) => {
              e.preventDefault();
              setIsFirst(false);
            }}
          >
            <span className="Sign_up_submit">Next</span>
          </button>
        </div>
        {/**/}
        <div
          className={"Sign_up_form2"}
          style={{ display: !isFirst ? "flex" : "none" }}
        >

          {basicSetting.map((item, index) =>
          UserInFormationRadio(selectUserData[item],item,userSelect[item],index,ref))}
             {styleObject.map((item, index) =>
             UserInFormationCheckBox(item,selectUserCheck[item],userSelect[styleObject[0]],index))}
          <br/>
          <input type="submit" value="가입"className={"Sign_Up_button"}/>
          <input type="button" value="홈페이지" onClick={() => {trans("/")}} className={"Sign_Up_button"} />
        </div></form>
        <div className="marginBox3"/></div>)}
