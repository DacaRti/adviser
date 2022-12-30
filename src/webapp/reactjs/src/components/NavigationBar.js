import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { Navbar, Nav } from "react-bootstrap";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faUserPlus,
  faSignInAlt,
  faSignOutAlt,
} from "@fortawesome/free-solid-svg-icons";
import { logoutUser } from "../services/index";

const NavigationBar = () => {
  useSelector((state) => state.auth);
  const dispatch = useDispatch();

  const logout = () => {
    dispatch(logoutUser());
  };

  const guestLinks = (
    <>
      <div className="mr-auto"></div>
      <Nav className="navbar-right">
        <Link to={"register"} className="nav-link">
          <FontAwesomeIcon icon={faUserPlus} /> Register
        </Link>
        <Link to={"login"} className="nav-link">
          <FontAwesomeIcon icon={faSignInAlt} /> Login
        </Link>
      </Nav>
    </>
  );
  const adminLinks = (
    <>
      <Nav className="mr-auto">
        <Link to={"add"} className="nav-link">
          Add User
        </Link>
        <Link to={"users"} className="nav-link">
          User List
        </Link>
        <Link to={"tips"} className="nav-link">
          Tips List
        </Link>
        <Link to={"addTip"} className="nav-link">
          Add Tip
        </Link>
      </Nav>
      <Nav className="navbar-right">
        <Link to={""} className="nav-link" onClick={logout}>
          <FontAwesomeIcon icon={faSignOutAlt} /> Logout
        </Link>
      </Nav>
    </>
  );

  const userLinks = (
    <>
      <Nav className="mr-auto">
        <Link to={"userPage"} className="nav-link">
          Get Advice
        </Link>
      </Nav>
      <Nav className="navbar-right">
        <Link to={""} className="nav-link" onClick={logout}>
          <FontAwesomeIcon icon={faSignOutAlt} /> Logout
        </Link>
      </Nav>
    </>
  );

  return (
    <Navbar bg="dark" variant="dark">
      <Link to={"/"} className="nav-link">
        <img src="/apple-touch-icon.png" width="50" height="50" alt="brand" />
        {""}
      </Link>
      {localStorage.jwtToken != null
        ? localStorage.role === "admin"
          ? adminLinks
          : userLinks
        : guestLinks}
    </Navbar>
  );
};

export default NavigationBar;
