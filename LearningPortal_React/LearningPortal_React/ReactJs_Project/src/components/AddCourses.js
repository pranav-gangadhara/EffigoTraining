import axios from "axios";
import React, { Fragment,useEffect, useState } from "react";
import { Button, Form, FormGroup,Input,Container,htmlFor } from "reactstrap";
import base_url from "./../api/bootapi"; 
import { toast } from "react-toastify";
// import { toast } from "react-toastify";

const AddCourses=()=>{
    useEffect(()=>{
            document.title="Add Courses || Learning Portal";
        },[]);

    const [course,setCourse]=useState({});

    const handleForm=(e)=>{
        console.log(course);
        postDataToServer(course);
        e.preventDefault();
    };

    const postDataToServer=(data)=>{
        axios.post(`${base_url}/courses`,data).then(
            (response)=>{
                console.log(response);
                console.log("success");
                toast.success("Courses are added successfully");
            },
            (error)=>{
                console.log(error);
                console.log("error");
                toast.error("Error! Something went wrong");
            }
        )
    };
    return(
        <Fragment>
            <h4 className="text-center">Fill your course details:</h4>
            <Form onSubmit={handleForm}>
                <FormGroup>
                    <label htmlFor="id">Course Id: </label><br/>
                    <Input type="text" name="courseId" placeholder="Enter course Id" id="courseId" onChange={(e)=>{
                        setCourse({...course,courseId:e.target.value});
                    }}/>
                </FormGroup>

                <FormGroup>
                    <label htmlFor="title">Course Title: </label><br/>
                    <Input type="text" name="title" placeholder="Enter Course Title" id="title" onChange={(e)=>{
                        setCourse({...course,title:e.target.value});
                    }}/>
                </FormGroup>

                <FormGroup>
                    <label htmlFor="description">Course Description: </label><br/>
                    <Input type="textarea" name="description" placeholder="Enter Course Description" id="description" style={{height:100}} onChange={(e)=>{
                        setCourse({...course,description:e.target.value});
                    }}/>
                </FormGroup>
                <Container className="text-center">
                    <Button type="submit" color="success me-3">Add Course</Button>
                    <Button type="reset" color="warning" onClick={()=>{setCourse({id:"",title:"",description:""});}}>Clear</Button>
                </Container>
            </Form>
        </Fragment>
    )
}

export default AddCourses;