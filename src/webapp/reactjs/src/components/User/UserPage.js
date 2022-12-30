import React, { useState } from "react";

import { Card, Form, Button, Col } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faQuestion } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import { saveTip } from "../../services/tip/tipActions";

function UserPage() {
  const [advice, setAdvice] = useState("");
  const [description, setDescrtiption] = useState("");

  return (
    <div>
      <Card className={"border border-dark bg-dark text-white"}>
        <Card.Header>
          {"A great merit is to help out a unknown friend in trouble"}
        </Card.Header>
        <Form id="tipFormId">
          <Card.Body>
            <Form.Row>
              <Form.Group as={Col} controlId="formGridAdvice">
                <Form.Label>Advice</Form.Label>
                <Form.Control
                  required
                  autoComplete="off"
                  type="text"
                  name="advice"
                  onChange={(event) => setAdvice(event.target.value)}
                  className={"bg-dark text-white"}
                  placeholder="Enter Advice"
                />
              </Form.Group>
              <Form.Group as={Col} controlId="formGridDescription">
                <Form.Label>Description</Form.Label>
                <Form.Control
                  required
                  autoComplete="off"
                  type="text"
                  name="description"
                  value={description}
                  onChange={(event) => setDescrtiption(event.target.value)}
                  className={"bg-dark text-white"}
                  placeholder="Enter Description"
                />
              </Form.Group>
            </Form.Row>
          </Card.Body>
          <Card.Footer style={{ textAlign: "center" }}>
            <Link to={"/random"} className="nav-link">
              <Button
                size="lg"
                variant="primary"
                type="submit"
                onClick={() => saveTip(advice, description)}
                disabled={advice.length === 0}
              >
                <FontAwesomeIcon icon={faQuestion} />{" "}
                {"GENERATE ADVICE ON THE DAY"}
              </Button>{" "}
            </Link>
          </Card.Footer>
        </Form>
      </Card>
    </div>
  );
}

export default UserPage;
