import './style.css';
import {AUTH_PATH, MAIN_PATH, USER_PATH} from "../../constant";
import {useNavigate} from "react-router-dom";
import {useCookies} from "react-cookie";
import {useState} from "react";

// 헤더 컴포넌트
export default function Header() {

    //          function: 네비게이트 함수          //
    const navigator = useNavigate();
//          event handler: 로고 클릭 이벤트 처리          //
    const onLogoClickHanlder = () => {
        navigator(MAIN_PATH());
    }

    //staue  쿠키 상태
    const [cookies, setCookies] = useCookies();
    // state: 로그인상태
    const [isLogin ,setLogin] = useState<boolean>(false);
    const  LoginMyPageButton =() =>{

        //          event handler: 마이페이지 버튼 클릭 이벤트 처리         //
        const onMyPageButtonClickHandler = () => {
            navigator(USER_PATH(''));
        }
        //          event handler: 로그인 버튼 클릭 이벤트 처리         //

        const onSignInButtonClickHandler = () => {
            navigator(AUTH_PATH());
        }

        if(isLogin)
            return(
                <div className='login-button' onClick={onMyPageButtonClickHandler}>{'마이페이지'} </div>
            )
        return(
            <div className='login-button' onClick={onSignInButtonClickHandler}>{'로그인'} </div>
        )
    }
// 렌더링 헤더 레이아웃
    return (
        <div id='header'>
            <div className='header-container'>
                <div className='header-left-box'onClick={onLogoClickHanlder} >
                    <div className='header-logo-icon-box' >
                        <div className='logo-dark-icon'></div>
                    </div>
                    <div className='header-logo-text'>{'kim Board'}</div>
                </div>
                <div className='header-right-box'>
                    <LoginMyPageButton />
                </div>
            </div>
        </div>
    )

}