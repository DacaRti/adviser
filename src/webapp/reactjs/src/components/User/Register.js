import React, {useState} from "react";

import {Card, Form, Button, Col, InputGroup} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faSave,
    faPlusSquare,
    faReply,
} from "@fortawesome/free-solid-svg-icons";
import {registerUser} from "../../services";

function Register() {

    const [username, setUsername] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [firstName, setFirstName] = useState("")
    const [lastName, setLastName] = useState("")
    const [birthday, setBirthday] = useState("")

    const resetUser = () => {
        setUsername("");
        setEmail("")
        setPassword("")
        setFirstName("")
        setLastName("")
        setBirthday("")
    }

    return (
        <div>
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>
                    <FontAwesomeIcon icon={faPlusSquare}/>{" Register"}
                </Card.Header>
                <Form
                    onSubmit={() => registerUser(username, email, password, firstName, lastName, birthday, )}
                    onReset={() => resetUser()}
                    id="userFormId"
                >
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridUsername">
                                <Form.Label>Username</Form.Label>
                                <Form.Control
                                    required
                                    autoComplete="off"
                                    type="text"
                                    name="username"
                                    value={username}
                                    onChange={(event) => setUsername(event.target.value)}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter Username"
                                />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formGridEmail">
                                <Form.Label>Email</Form.Label>
                                <Form.Control
                                    required
                                    autoComplete="off"
                                    type="email"
                                    name="email"
                                    value={email}
                                    onChange={(event) => setEmail(event.target.value)}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter Email"
                                />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridPassword">
                                <Form.Label>Password</Form.Label>
                                <InputGroup>
                                    <Form.Control
                                        required
                                        autoComplete="off"
                                        type="password"
                                        name="password"
                                        value={password}
                                        onChange={(event) => setPassword(event.target.value)}
                                        className={"bg-dark text-white"}
                                        placeholder="Enter Password"
                                    />
                                </InputGroup>
                            </Form.Group>
                            <Form.Group as={Col} controlId="formGridFirstName">
                                <Form.Label>First Name</Form.Label>
                                <Form.Control
                                    required
                                    autoComplete="off"
                                    type="text"
                                    name="firstName"
                                    value={firstName}
                                    onChange={(event) => setFirstName(event.target.value)}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter First Name"
                                />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridLastName">
                                <Form.Label>Last Name</Form.Label>
                                <Form.Control
                                    required
                                    autoComplete="off"
                                    type="text"
                                    name="lastName"
                                    value={lastName}
                                    onChange={(event) => setLastName(event.target.value)}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter Last Name"
                                />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formGridBirthday">
                                <Form.Label>Birthday</Form.Label>
                                <Form.Control
                                    required
                                    autoComplete="off"
                                    type="date"
                                    name="birthday"
                                    value={birthday}
                                    onChange={(event) => setBirthday(event.target.value)}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter Birthday"
                                />
                            </Form.Group>
                        </Form.Row>
                    </Card.Body>
                    <Card.Footer style={{textAlign: "right"}}>
                        <Button size="sm" variant="secondary" type="reset">
                            <FontAwesomeIcon icon={faReply}/>{" "}
                            {"Reset"}
                        </Button>{" "}
                        <Button size="sm" variant="success" type="submit">
                            <FontAwesomeIcon icon={faSave}/>{" "}
                            {"Save"}
                        </Button>{" "}
                    </Card.Footer>
                </Form>
            </Card>
        </div>
    );
}

export default Register;