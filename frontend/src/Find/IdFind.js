import axios from "axios";
import { useState, useRef } from "react";
import pageback_btn from "../img/pageback_btn.png";
import "./IdFind.css";

const id_find = async (setIdFind_State, setId_value, find_id_email) => {
  const data = find_id_email.current.value;
  try {
    setId_value(
      await (
        await axios.post(`/login/${data}`, { email: data })
      ).data.userId
    );
    setIdFind_State(false);
  } catch (e) {
    alert("이메일을 잘못입력하셨거나 가입된이메일이 아닙니다.");
    console.log(e);
  }
};

export default function IdFind() {
  const [IdFind_state, setIdFind_State] = useState(true);
  const [id_value, setId_value] = useState("");
  const find_id_email = useRef();
  //beforeunload 이벤트 추가 필요
  return (
    <>
      {IdFind_state ? (
        <>
          <div className={"IdFind_title_container"}>
            <img
              src={pageback_btn}
              alt="이미지를 불러오는데 실패 했습니다..."
              width={16}
              height={16}
              className={"IdFind_pageback"}
            />
            <h1 className={"IdFind_title"}>ID 찾기</h1>
          </div>{" "}
          <>
            <div className={"Information_password_container"}>
              <form
                onSubmit={(e) => {
                  e.preventDefault();
                  id_find(setIdFind_State, setId_value, find_id_email);
                }}
              >
                <input
                  className="passwordtext"
                  type="text"
                  placeholder="E-mail"
                  ref={find_id_email}
                />
                <br /> <br />
                <input className="submit" type="submit" value="다음" />
              </form>
            </div>
          </>
        </>
      ) : (
        <>
          <h1>가입하신 아이디는 "{id_value}" 입니다.</h1>
          <h1>*주의*</h1>
          <h3>뒤로가기나 새로고침시 초기화면으로 돌아 갑니다.</h3>
        </>
      )}
    </>
  );
}
