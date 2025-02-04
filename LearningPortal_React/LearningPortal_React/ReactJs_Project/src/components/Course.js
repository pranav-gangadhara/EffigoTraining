import React from 'react';
import axios from "axios";
import { Card,CardBody,CardTitle,CardSubtitle,
    CardText,CardFooter,Button,Container } from 'reactstrap';
import base_url from '../api/bootapi';
import { toast } from 'react-toastify';

const Course=({course, update})=>{
    const deleteCourse=(id)=>{
        axios.delete(`${base_url}/courses/${id}`).then(
            (response)=>{
                toast.success("Course deleted");
                update(id);
            },
            (error)=>{
                toast.error("Course not deleted ! Server problem");
            }
        );
    };
    
    return(
        <Card className="text-center">
            <CardBody>
                <CardSubtitle className="fw-bold">{course.title}</CardSubtitle>
                <CardTitle>{course.description}</CardTitle>
                <Container className="text-center">
                    <Button color="danger me-3" onClick={()=>deleteCourse(course.id)}>Delete</Button>
                    <Button color="warning" onClick={() => update(course.id)}>Update</Button>
                </Container>
            </CardBody>
        </Card>
    )
}

export default Course;