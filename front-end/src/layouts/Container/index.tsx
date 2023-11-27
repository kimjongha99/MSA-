import {Outlet, useLocation} from "react-router-dom";
import Header from 'layouts/Header'
import Footer from 'layouts/Footer'
import React from 'react'
import {AUTH_PATH} from 'constant';

//          component: 메인 레이아웃          //
export default function Container() {
    //statue 현재 페이지 path name 상태

    const  {pathname} = useLocation();




  //          render: 메인 레이아웃 렌더링          //
  return (
    <>
        <Header/>
        <Outlet/>
        {pathname !== AUTH_PATH() && <Footer/>}
    </>
  )
}
