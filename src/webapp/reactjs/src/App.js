import React from "react";
import "./App.css";

import { Container, Row, Col } from "react-bootstrap";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import NavigationBar from "./components/NavigationBar";
import Welcome from "./components/Welcome";
import AddUser from "./components/User/AddUser";
import UserList from "./components/User/UserList";
import TipList from "./components/Tip/TipList";
import RandomTip from "./components/Tip/RandomTip";

import Footer from "./components/Footer";
import Register from "./components/User/Register";
import PrivateRoute from "./components/PrivateRoute";
import Login from "./components/User/Login";
import AddTip from "./components/Tip/AddTip";
import UserPage from "./components/User/UserPage";

const App = () => {
  return (
    <Router>
      <NavigationBar />
      <Container>
        <Row>
          <Col lg={12} className={"margin-top"}>
            <Switch>
              <Route path="/" exact component={Welcome} />
              <Route path="/login" exact component={Login} />
              <Route path="/register" exact component={Register} />
              <PrivateRoute path="/add" component={AddUser}></PrivateRoute>
              <PrivateRoute path="/edit/:id" component={AddUser} />
              <PrivateRoute path="/users" component={UserList} />
              <PrivateRoute path="/tips" component={TipList} />
              <PrivateRoute path="/addTip" component={AddTip}></PrivateRoute>
              <PrivateRoute path="/editTip/:id" component={AddTip} />
              <PrivateRoute path="/userPage" component={UserPage} />
              <PrivateRoute path="/random" component={RandomTip} />
            </Switch>
          </Col>
        </Row>
      </Container>
      <Footer />
    </Router>
  );
};

export default App;
