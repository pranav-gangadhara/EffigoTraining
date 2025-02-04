import React, { useEffect } from "react";
import { Container,Button } from "reactstrap";

const Home=()=>{
    useEffect(()=>{
        document.title="Home || Learning Portal";
    },[]);
    return(
        <div>
            <Container className="text-center">
                <h1>Learning Portal</h1>
                <p>
                    This is a place where you can learn new things 
                                     and 
                           improve your skills.
                </p>
                <Button color="primary" outline>Start Using</Button>
            </Container>

        </div>
    )
}

export default Home;