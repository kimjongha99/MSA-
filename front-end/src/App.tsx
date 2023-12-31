import { Route, Routes, useLocation } from 'react-router-dom';
import './App.css';
import { AUTH_PATH , MAIN_PATH } from 'constant';
import Main from 'views/Main';
import Authentication from 'views/Authentication';

import Container from 'layouts/Container';
import { useEffect } from 'react';
import { useCookies } from 'react-cookie';
import { useUserStore } from 'stores';
import { getSignInUserRequest } from 'apis';
import { GetSignInUserResponseDto } from 'apis/dto/response/user';
import ResponseDto from 'apis/dto/response';

function App() {

        const { pathname } = useLocation();   //          state: 현재 페이지 url 상태          //
        const { user, setUser } = useUserStore();   //          state: 로그인 유저 상태          //
        const [cookies, setCookie] = useCookies();  //          state: cookie 상태          //


        const getSignInUserResponse = (responseBody: GetSignInUserResponseDto | ResponseDto) => {
                const { code } = responseBody;
                if (code !== 'SU') {
                        setCookie('accessToken', '', { expires: new Date(), path: MAIN_PATH });
                        setUser(null);
                        return;
                }

                setUser({ ...responseBody as GetSignInUserResponseDto });
        }

        useEffect(() => {    //          effect: 현재 path가 변경될 때마다 실행될 함수          //


                const accessToken = cookies.accessToken;
                if (!accessToken) {
                        setUser(null);
                        return;
                }

                getSignInUserRequest(accessToken).then(getSignInUserResponse);

        }, [pathname]);

        return (
        <Routes>
                <Route element={<Container /> }>
                        <Route path={MAIN_PATH} element={<Main />} />
                            <Route path={AUTH_PATH} element={<Authentication/>}/>
                            <Route path='*' element={<h1>404 Not Found</h1>} />
                    </Route>
            </Routes>
        );
}

export default App;


