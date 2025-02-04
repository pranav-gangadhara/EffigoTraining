import React from "react";
import { CardBody,Card } from "reactstrap";

function Header({name,title}){
    return(
        <div>
            <Card className="my-2 bg-warning">
                <CardBody>
                    <h2 className="text-center my-2">Welcome to Courses Application</h2>
                </CardBody>
            </Card>
        </div>
    );
}

export default Header;