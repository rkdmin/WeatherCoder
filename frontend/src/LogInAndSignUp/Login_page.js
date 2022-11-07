import React, { useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Login_page.css";

const test = async (...rest) => {
  const data = { userId: rest[0], password: rest[1] };
  try {
    const result = await (
      await fetch(`/login`, {
        method: "post",
        body: JSON.stringify(data),
        headers: { "Content-Type": "application/json" },
      })
    ).json();
    if (result.status === "Y") {
      sessionStorage.setItem("login_information", JSON.stringify(result));
      alert("sucess for login");
      rest[2](-1);
    }
  } catch (e) {
    console.log(e);
    alert("fail to login");
  }
};
const Login_page = () => {
  const trans = useNavigate();
  const input_id = useRef();
  const input_pw = useRef();
  return (
    <>
      <div className="container">
        <h1 className="loginpage_title"> Log-in </h1>
        <form
          className="form"
          onSubmit={(e) => {
            e.preventDefault();
            test(input_id.current.value, input_pw.current.value, trans);
          }}
        >
          <input
            className="ID"
            type="text"
            placeholder="아이디를 입력하세요."
            ref={input_id}
          />
          <br />
          <input
            className="PW"
            type="password"
            placeholder="비밀번호를 입력하세요."
            ref={input_pw}
          />
          <input type="submit" className="in" value="LOGIN" />
          <br />
          <br />
        </form>
        <Link to={"/Id_find"}>아이디 찾기</Link>
      </div>
    </>
  );
};
export default Login_page;
