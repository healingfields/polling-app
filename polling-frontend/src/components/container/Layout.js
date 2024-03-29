import React from 'react'
import { Header } from '../common/header/Header'
import {Layout as AntdLayout} from 'antd'
import { Outlet } from 'react-router-dom'
import './Layout.css'

const {Content, Footer} = AntdLayout
export const Layout = () => {

  return (
    <AntdLayout className='layout-container'>
        <Header className='header'/>
        <Content className='page-content'>
            <Outlet/>
        </Content>
        <Footer className='footer'>Footer</Footer>
    </AntdLayout>
  )
}
