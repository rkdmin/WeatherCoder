import axios from "axios";
import React, {useState, useRef} from "react";
import pageback_btn from "../img/pageback_btn.png";
import "./IdFind.css"

export default function IdFind() {
    let [IdFind_state, setIdFind_State] = useState(true);
    const [id_value, setId_value] = useState("");
    const find_id_email = useRef();
    const id_find = async () => {
        const data = find_id_email.current.value
        try {
            setId_value((await axios.post(`/login/${data}`, {email: data})
            ).data.userId)
            setIdFind_State(false)
        } catch (e) {
            alert("이메일을 잘못입력하셨거나 가입된이메일이 아닙니다.")
            console.log(e)
        }
    }
    return (
        <>
            {IdFind_state ?
                <>
                    <div className={"IdFind_title_container"}>
                        <img src={pageback_btn} width={16} height={16} className={"IdFind_pageback"}/>
                        <h1 className={"IdFind_title"}>아이디 찾기</h1>
                    </div>

                    <div className={"Information_password_container"}>
                        <div className="passwordtext">이메일 찾기</div>
                        <input className="PW" placeholder="이름"/>
                        <input className="PW" placeholder="이메일"
                        />
                        <button
                            className="in" onClick={id_find}
                        >
                            <span className="submit">Submit</span>
                        </button>
                    </div>
                </>
                :
                <>
                    <h1>가입하신 아이디는 {id_value} 입니다.</h1>
                    <h1>*주의*</h1> <h3>뒤로가기나 새로고침시 초기화면으로 돌아 갑니다.</h3>
                </>}
        </>
    );
}
