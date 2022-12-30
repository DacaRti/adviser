import React, { useEffect, useState } from "react";
import axios from "axios";

import { Card, Form, Button, Col, InputGroup } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSave,
  faPlusSquare,
  faReply,
  faList,
  faEdit,
} from "@fortawesome/free-solid-svg-icons";
import { Link, useParams } from "react-router-dom";
import { saveUser, updateUser } from "../../services";

function AddUser() {
  const params = useParams();
  const userId = params.id;
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [birthday, setBirthday] = useState("");
  const [role, setRole] = useState({
    id: "",
    name: "",
  });
  const [roles, setRoles] = useState([]);

  useEffect(() => {
    let isMounted = true;
    const controller = new AbortController();

    const getRoles = async () => {
      try {
        const response = await axios.get("http://localhost:8080/users/roles/", {
          headers: {
            signal: controller.signal,
            Authorization: `Bearer ${JSON.parse(localStorage.jwtToken)}`,
          },
        });
        console.log(response.data);
        isMounted && setRoles(response.data);
      } catch (err) {
        console.error(err);
      }
    };
    getRoles();

    return () => {
      isMounted = false;
    };
  }, []);

  const resetUser = () => {
    setUsername("");
    setEmail("");
    setPassword("");
    setFirstName("");
    setLastName("");
    setBirthday("");
    setRole({
      id: "",
      name: "",
    });
  };

  return (
    <div>
      <Card className={"border border-dark bg-dark text-white"}>
        <Card.Header>
          <FontAwesomeIcon icon={userId == null ? faPlusSquare : faEdit} />
          {" Add user"}
        </Card.Header>
        <Form
          onSubmit={
            userId == null
              ? () =>
                  saveUser(
                    username,
                    email,
                    password,
                    firstName,
                    lastName,
                    birthday,
                    role
                  )
              : () =>
                  updateUser(
                    userId,
                    username,
                    email,
                    password,
                    firstName,
                    lastName,
                    birthday,
                    role
                  )
          }
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
              <Form.Group as={Col} controlId="formGridRole">
                <Form.Label>Role</Form.Label>
                <Form.Control
                  required
                  as="select"
                  custom
                  name="role"
                  value={role.name}
                  onChange={(event) =>
                    setRole({
                      id: event.target.options.selectedIndex,
                      name: event.target.value,
                    })
                  }
                  className={"bg-dark text-white"}
                >
                  <option>{""}</option>
                  {roles.map((role) => (
                    <option key={role.name} value={role.name}>
                      {role.name}
                    </option>
                  ))}
                </Form.Control>
              </Form.Group>
            </Form.Row>
          </Card.Body>
          <Card.Footer style={{ textAlign: "right" }}>
            <Button size="sm" variant="secondary" type="reset">
              <FontAwesomeIcon icon={faReply} /> {"Reset"}
            </Button>{" "}
            <Button size="sm" variant="success" type="submit">
              <FontAwesomeIcon icon={faSave} /> {"Save"}
            </Button>{" "}
            <Link to={"/users"} className="nav-link">
              <Button size="sm" variant="primary">
                <FontAwesomeIcon icon={faList} /> {"Go to List"}
              </Button>{" "}
            </Link>
          </Card.Footer>
        </Form>
      </Card>
    </div>
  );
}

export default AddUser;
