import "./Register.css";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import React, { useState } from "react";
import { register, loginUser } from "../../client/http/httpClient";
import Base from "../base/Base";

function Register() {
  const [userDetails, setUserDetails] = useState({});
  const [login, setLogin] = useState({});
  const [proceed, setProceed] = useState(false);

  const handleRegister = async (e) => {
    console.log(userDetails);
    await register(userDetails);
  };

  const setter = x => {
    console.log("here");
    setProceed(x)
  }

  const handleLogin = async (e) => {
    console.log(login);
    await loginUser(login, setter)
  };

  return (
    <div>
      {!proceed && (
        <div>
          <h1 className="header bg">StatSpot</h1>
          <div className="out">
            <div className="left">
              <h1 className="header bg color-login ">Register</h1>
              <Form>
                <Form.Group className="mb-3" controlId="firstName">
                  <Form.Label>First Name</Form.Label>
                  <Form.Control
                    type="text"
                    onChange={(e) => {
                      var jsonToPost = userDetails;
                      jsonToPost.firstName = e.target.value;
                      setUserDetails(jsonToPost);
                    }}
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="lastName">
                  <Form.Label>Last Name</Form.Label>
                  <Form.Control
                    type="text"
                    onChange={(e) => {
                      var jsonToPost = userDetails;
                      jsonToPost.lastName = e.target.value;
                      setUserDetails(jsonToPost);
                    }}
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="email">
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="text"
                    onChange={(e) => {
                      var jsonToPost = userDetails;
                      jsonToPost.email = e.target.value;
                      setUserDetails(jsonToPost);
                    }}
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="userName">
                  <Form.Label>User Name</Form.Label>
                  <Form.Control
                    type="text"
                    onChange={(e) => {
                      var jsonToPost = userDetails;
                      jsonToPost.userName = e.target.value;
                      setUserDetails(jsonToPost);
                    }}
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="password">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    onChange={(e) => {
                      var jsonToPost = userDetails;
                      jsonToPost.password = e.target.value;
                      setUserDetails(jsonToPost);
                    }}
                  />
                </Form.Group>
              </Form>
              <div className="wrapper-top">
                <Button className="ctr-button" variant="primary" onClick={handleRegister}>
                    Register
                </Button>
              </div>
            </div>
            <div className="right">
              <h1 className="header bg color-login ">Login</h1>
              <Form className="margin-top">
                <Form.Group className="mb-3" controlId="userName">
                  <Form.Label>User Name</Form.Label>
                  <Form.Control
                    type="text"
                    onChange={(e) => {
                      var jsonToPost = login;
                      jsonToPost.userName = e.target.value;
                      setLogin(jsonToPost);
                    }}
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="password">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    onChange={(e) => {
                      var jsonToPost = login;
                      jsonToPost.password = e.target.value;
                      setLogin(jsonToPost);
                    }}
                  />
                </Form.Group>
              </Form>
              <div className="wrapper-top">
                <Button className="ctr-button" variant="primary" onClick={handleLogin}>
                    Login
                </Button>
              </div>
            </div>
          </div>
        </div>
      )}
      {proceed && (
        <div>
          <Base />
        </div>
      )}
    </div>
  );
}

export default Register;
