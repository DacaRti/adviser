import React, { useEffect, useState } from "react";
import axios from "axios";

import "./../../assets/css/Style.css";
import { Card, Table, Button, ButtonGroup } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit, faTrash, faUsers } from "@fortawesome/free-solid-svg-icons";
import { deleteUser } from "../../services";
import { Link } from "react-router-dom";

function UserList() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    let isMounted = true;
    const controller = new AbortController();

    const getUsers = async () => {
      try {
        const response = await axios.get("http://localhost:8080/users/", {
          headers: {
            signal: controller.signal,
            Authorization: `Bearer ${JSON.parse(localStorage.jwtToken)}`,
          },
        });
        console.log(response.data);
        isMounted && setUsers(response.data);
      } catch (err) {
        console.error(err);
      }
    };
    getUsers();

    return () => {
      isMounted = false;
    };
  }, []);

  return (
    <div>
      <Card className={"border border-dark bg-dark text-white"}>
        <Card.Header>
          <FontAwesomeIcon icon={faUsers} /> User List
        </Card.Header>
        <Card.Body>
          <Table bordered hover striped variant="dark">
            <thead>
              <tr>
                <td>Id</td>
                <td>Username</td>
                <td>Email</td>
                <td>FirstName</td>
                <td>LastName</td>
                <td>Birthday</td>
                <td>Role</td>
                <td>Actions</td>
              </tr>
            </thead>
            <tbody>
              {users.map((user, index) => (
                <tr key={index}>
                  <td>{user.id}</td>
                  <td>{user.username}</td>
                  <td>{user.email}</td>
                  <td>{user.firstName}</td>
                  <td>{user.lastName}</td>
                  <td>{user.birthday}</td>
                  <td>{user.role.name}</td>
                  <td>
                    <ButtonGroup>
                      <Link
                        to={"edit/" + user.id}
                        className="btn btn-sm btn-outline-primary"
                      >
                        <FontAwesomeIcon icon={faEdit} />
                      </Link>{" "}
                      <Button
                        type="reset"
                        size="sm"
                        variant="outline-danger"
                        onClick={() => deleteUser(user.id)}
                      >
                        <FontAwesomeIcon icon={faTrash} />
                      </Button>
                    </ButtonGroup>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Card.Body>
      </Card>
    </div>
  );
}

export default UserList;
