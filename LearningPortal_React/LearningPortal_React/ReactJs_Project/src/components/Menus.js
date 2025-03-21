import React from "react";
import { Link } from "react-router-dom";
import { ListGroup} from "reactstrap";

const Menus=()=>{
    return(
        <ListGroup>
            <Link className="list-group-item list-group-item-action" tag="a" to="/">Home</Link>
            <Link className="list-group-item list-group-item-action" tag="a" to="/add-course">Add course</Link>
            <Link className="list-group-item list-group-item-action" tag="a" to="/view-courses">View course</Link>
            <Link className="list-group-item list-group-item-action" tag="a" to="#!">About</Link>
            <Link className="list-group-item list-group-item-action" tag="a" to="#!">Contact Us</Link>
        </ListGroup>

    )
}

export default Menus;