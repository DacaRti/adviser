import axios from "axios";

const REST_URL = "http://localhost:8080/tips/";

export const saveTip = async (advice, description) => {
  try {
    const response = await axios.post(
      REST_URL,
      {
        advice,
        description,
      },
      {
        headers: {
          Authorization: `Bearer ${JSON.parse(localStorage.jwtToken)}`,
        },
      }
    );
    console.log(response);
  } catch (error) {
    alert(error);
  }
};

export const updateTip = async (id, advice, description) => {
  try {
    const response = await axios.put(
      REST_URL,
      {
        id,
        advice,
        description,
      },
      {
        headers: {
          Authorization: `Bearer ${JSON.parse(localStorage.jwtToken)}`,
        },
      }
    );
    alert("Tip updated successfully");
    console.log(response);
  } catch (error) {
    alert(error);
  }
};

export const deleteTip = async (id) => {
  await axios.delete(REST_URL + id, {
    headers: {
      Authorization: `Bearer ${JSON.parse(localStorage.jwtToken)}`,
    },
  });
  alert("Tip deleted successfully");
  window.location.reload();
};
