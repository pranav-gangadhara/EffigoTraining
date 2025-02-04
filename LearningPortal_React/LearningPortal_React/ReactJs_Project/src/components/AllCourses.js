import React,{useState,useEffect} from "react";
import Course from "./Course";
import base_url from "./../api/bootapi"; 
import axios from "axios";
import { toast } from "react-toastify";

const AllCourse=()=>{

    useEffect(()=>{
        document.title="All Courses || Learning Portal";
    },[]);

    const getAllCoursesFromServer=()=>{
        axios.get(`${base_url}/courses`).then(
            (response)=>{
                console.log(response.data);
                toast.success("Courses have been loaded",{
                    position:"bottom-center",
                });
                setCourses(response.data);
            },
            (error)=>{
                console.log(error);
                toast.error("Something went wrong",{
                    position:"bottom-center",
                });
            }
        );
    };

    useEffect(()=>{
        getAllCoursesFromServer();
    },[]);

    const [courses,setCourses]=useState([]);


    const updateCourses=(id)=>{
        setCourses(courses.filter((c)=>c.id!=id));
    };

    return(
        <div className="text-center">
            <h3>All Courses</h3>
            <p>List of all courses</p>
            {
                courses.length>0?courses.map((item)=>
                <Course key={item.id} course={item} update={updateCourses}/>):("No Courses")
            }    
        </div>
    );
};

export default AllCourse;