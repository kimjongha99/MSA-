import './App.css';
import {Route, Routes} from "react-router-dom";
import {AUTH_PATH, MAIN_PATH, USER_PATH} from "./constant";
import Main from "./views/Main";
import Authentication from "./views/Authentication";
import Container from "./layouts/Container";

// component : Application 컴포넌트

function App() {
    // render 컴포넌트 렌더링

    // description  메인화면 '/' - Main
    // description   로그인 회원가입 + '/auth'  - Authentication



return(
    <Routes>
        <Route element={<Container/>}>
        <Route path={MAIN_PATH()} element={<Main />} />
        <Route path={AUTH_PATH()} element={<Authentication />} />
                <Route path={USER_PATH(':searchEmail')}  />

        <Route path='*' element={<h1>404 NOT FOUND</h1>}/>
        </Route>

    </Routes>
);
}

export default App;

