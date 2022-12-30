import React, { useEffect, useState } from "react";
import axios from "axios";

import "./../../assets/css/Style.css";
import { Card, Table, Button, ButtonGroup } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit, faTrash, faUsers } from "@fortawesome/free-solid-svg-icons";
import { deleteTip } from "../../services/tip/tipActions";
import { Link } from "react-router-dom";

function TipList() {
  const [tips, setTips] = useState([]);

  useEffect(() => {
    let isMounted = true;
    const controller = new AbortController();

    const getTips = async () => {
      try {
        const response = await axios.get("http://localhost:8080/tips/", {
          headers: {
            signal: controller.signal,
            Authorization: `Bearer ${JSON.parse(localStorage.jwtToken)}`,
          },
        });
        console.log(response.data);
        isMounted && setTips(response.data);
      } catch (err) {
        console.error(err);
      }
    };
    getTips();

    return () => {
      isMounted = false;
    };
  }, []);

  return (
    <div>
      <Card className={"border border-dark bg-dark text-white"}>
        <Card.Header>
          <FontAwesomeIcon icon={faUsers} /> Tip List
        </Card.Header>
        <Card.Body>
          <Table bordered hover striped variant="dark">
            <thead>
              <tr>
                <td>Id</td>
                <td>advice</td>
                <td>description</td>
                <td>Actions</td>
              </tr>
            </thead>
            <tbody>
              {tips.map((tip, index) => (
                <tr key={index}>
                  <td>{tip.id}</td>
                  <td>{tip.advice}</td>
                  <td>{tip.description}</td>
                  <td>
                    <ButtonGroup>
                      <Link
                        to={"editTip/" + tip.id}
                        className="btn btn-sm btn-outline-primary"
                      >
                        <FontAwesomeIcon icon={faEdit} />
                      </Link>{" "}
                      <Button
                        type="reset"
                        size="sm"
                        variant="outline-danger"
                        onClick={() => deleteTip(tip.id)}
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

export default TipList;
