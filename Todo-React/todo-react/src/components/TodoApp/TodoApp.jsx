import React from 'react'
import './TodoApp.css'
import {BrowserRouter,Routes,Route} from 'react-router-dom'
import LogoutComponent from './LogoutComponent'
import ListTodosComponent from './ListTodosComponent'
import ErrorComponent from './ErrorComponent'
import WelcomeComponent from './WelcomeComponent'
import LoginComponent from './LoginComponent'

import HeaderComponent from './HeaderComponent'
import AuthProvider from './security/AuthContext'


export default function TodoApp() {


  return (
    <div className='TodoApp'>
        <AuthProvider>
            <BrowserRouter>
            <HeaderComponent/>
                <Routes>
                    <Route path='/' element={<LoginComponent/>}></Route>
                    <Route path='/login' element={<LoginComponent/>}></Route>
                    <Route path='/welcome/:username' element={<WelcomeComponent/>}></Route>
                    <Route path='*' element={<ErrorComponent/>}></Route>
                    <Route path='/todos' element={<ListTodosComponent/>}></Route>
                    <Route path='/logout' element={<LogoutComponent/>}></Route>
                </Routes>
                
            </BrowserRouter> 
        </AuthProvider>
        
        
       {/*<WelcomeComponent/>*/}
    </div>
  )
}
 

