import React from 'react';
import logo from './logo.svg';
import './App.css';
import { ToastContainer, toast } from 'react-toastify';
import Header from './components/Header';
import Home from './components/Home';
import Menus from './components/Menus';
import AllCourses from './components/AllCourses';
import AddCourses from './components/AddCourses';
import {Container,Row,Col} from "reactstrap";
import { BrowserRouter as Router,Route,Routes} from 'react-router-dom';

function App() {
  return(
    <div>
      <Router>
        <ToastContainer/>
        <Container>
          <Header/>
          <Row>
            <Col md={4}>
              <Menus/>
            </Col>
            <Col md={8}>
            <Routes>
              <Route path="/" element={<Home/>} exact/>
              <Route path="/add-course" element={<AddCourses/>} exact/>
              <Route path="/view-courses" element={<AllCourses/>} exact/>
            </Routes>
            </Col>
          </Row>
        </Container>
      </Router>
    </div>
  );
}

export default App;