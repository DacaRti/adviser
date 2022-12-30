import React, { useState } from "react";

import { Card, Form, Button, Col } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSave,
  faPlusSquare,
  faReply,
  faList,
  faEdit,
} from "@fortawesome/free-solid-svg-icons";
import { Link, useParams } from "react-router-dom";
import { saveTip, updateTip } from "../../services/tip/tipActions";

function AddTip() {
  const params = useParams();
  const tipId = params.id;
  const [advice, setAdvice] = useState("");
  const [description, setDescrtiption] = useState("");

  const resetTip = () => {
    setAdvice("");
    setDescrtiption("");
  };

  return (
    <div>
      <Card className={"border border-dark bg-dark text-white"}>
        <Card.Header>
          <FontAwesomeIcon icon={tipId == null ? faPlusSquare : faEdit} />
          {" Add tip"}
        </Card.Header>
        <Form
          onSubmit={
            tipId == null
              ? () => saveTip(advice, description)
              : () => updateTip(tipId, advice, description)
          }
          onReset={() => resetTip()}
          id="tipFormId"
        >
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
          <Card.Footer style={{ textAlign: "right" }}>
            <Button size="sm" variant="secondary" type="reset">
              <FontAwesomeIcon icon={faReply} /> {"Reset"}
            </Button>{" "}
            <Button size="sm" variant="success" type="submit">
              <FontAwesomeIcon icon={faSave} /> {"Save"}
            </Button>{" "}
            <Link to={"/tips"} className="nav-link">
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

export default AddTip;
