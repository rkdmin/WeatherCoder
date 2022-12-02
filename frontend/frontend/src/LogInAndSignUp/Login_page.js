import axios from "axios";
import React, { useRef } from "react";
import { useState,useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import MenuBar from "../Bar/MenuBar";
import "./Login_page.css";

class login {
  constructor(email, password) {
    this.email = email;
    this.password = password;
  }
}

const Login_page = () => {
  const [result, setResult] = useState({});
  const navigate = useNavigate();
  const input_id = useRef();
  const input_pw = useRef();
 
  useEffect(() => {
    if (Object.keys(result).length === 2) {
      alert(result.errorMessage);
    } else if (Object.keys(result).length === 7) {
      sessionStorage.setItem("login_information", JSON.stringify(result));
     window.location.replace('/')
    }
  }, [result, navigate]);
 
  return (
    <div className="container">
      <h1 className="loginpage_title"> Log-in </h1>
      <form
          className="form"
          onSubmit={(e) => {
            (async () => {
              e.preventDefault();
              try {
                const user = new login(
                  input_id.current.value,
                  input_pw.current.value
                );
                setResult(await (await axios.post(`/login`, user)).data);
              } catch (error) {
                console.log(error);
              }
            })();
          }}
        >
        <div className="emailtext">E-mail</div>
        <input
          className="ID"
          type="text"
          placeholder="아이디를 입력하세요."
          ref={input_id}
        />
        <div className="passwordtext">Password</div>
        <input
          className="PW"
          type="password"
          placeholder="비밀번호를 입력하세요."
          ref={input_pw}
        />
        <div className={"normaltext"}>
          계정이 없으신가요?{" "}
          <Link to={"/Sign_up"} className="boldtext">
            회원가입하기
          </Link>
        </div>
        <div className={"bcontainer"}>
          <Link
            to={"/Id_find"}
            className="normaltext boldtext mh-5"
          >
            아이디 찾기
          </Link>
          <div className="normaltext boldtext mh-10">|</div>
          <Link
            to={"/Id_find"}
            className="normaltext boldtext mh-10"
          >
            비밀번호 찾기
          </Link>
        </div>
        <input type = "submit" value="submit" className="submit2"/>
      </form>
      <Link to={"/Id_find"} className="normaltext">
        개인정보보호정책
      </Link>
      <Link to={"/Id_find"} className="normaltext">
        WeatherStyle 이용약관
      </Link>
      <MenuBar/>
    </div>
  );
};
export default Login_page;
