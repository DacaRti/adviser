import React from "react";
import { Card } from "react-bootstrap";
import styles from "./Welcome.module.css";

const Welcome = () => {
  return (
    <Card bg="dark" text="light">
      <Card.Header className={styles.welcomePageName}>
        Advicer: Give advice to others and get yours
      </Card.Header>
      <Card.Body style={{ overflowY: "scroll", height: "570px" }}>
        <h1>How it's work?</h1>
        <div>
          You can get short advice from other people for the day, and in return
          give your advice based on the conclusions of the past day.
        </div>
        <h1>Rooles:</h1>
        <div>* Do not give empty advice;</div>
        <div>* Profanity is prohibited;</div>
      </Card.Body>
    </Card>
  );
};

export default Welcome;
