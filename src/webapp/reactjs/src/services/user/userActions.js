import axios from "axios";

const REST_URL = "http://localhost:8080/users/";
const REST_REGISTER_URL = "http://localhost:8080/users/register";

export const saveUser = async (
  username,
  email,
  password,
  firstName,
  lastName,
  birthday,
  role
) => {
  try {
    const response = await axios.post(
      REST_URL,
      {
        username,
        email,
        password,
        firstName,
        lastName,
        birthday,
        role,
      },
      {
        headers: {
          Authorization: `Bearer ${JSON.parse(localStorage.jwtToken)}`,
        },
      }
    );
    alert("User created successfully");
    console.log(response);
  } catch (error) {
    alert(error);
  }
};

export const updateUser = async (
  id,
  username,
  email,
  password,
  firstName,
  lastName,
  birthday,
  role
) => {
  try {
    const response = await axios.put(
      REST_URL,
      {
        id,
        username,
        email,
        password,
        firstName,
        lastName,
        birthday,
        role,
      },
      {
        headers: {
          Authorization: `Bearer ${JSON.parse(localStorage.jwtToken)}`,
        },
      }
    );
    alert("User updated successfully");
    console.log(response);
  } catch (error) {
    alert(error);
  }
};

export const registerUser = async (
  username,
  email,
  password,
  firstName,
  lastName,
  birthday
) => {
  try {
    const response = await axios.post(REST_REGISTER_URL, {
      username,
      email,
      password,
      firstName,
      lastName,
      birthday,
    });
    alert("User registrated successfully");
    console.log(response);
  } catch (error) {
    alert(error);
  }
};

export const deleteUser = async (id) => {
  await axios.delete(REST_URL + id, {
    headers: {
      Authorization: `Bearer ${JSON.parse(localStorage.jwtToken)}`,
    },
  });
  alert("User deleted successfully");
  window.location.reload();
};
