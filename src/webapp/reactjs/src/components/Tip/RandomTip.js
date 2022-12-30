import React, { useEffect, useState } from "react";
import axios from "axios";

import "./../../assets/css/Style.css";
import styles from "./RandomTip.module.css";

function RandomTip() {
  const [tip, setTip] = useState([]);

  useEffect(() => {
    let isMounted = true;
    const controller = new AbortController();

    const getTips = async () => {
      try {
        const response = await axios.get("http://localhost:8080/tips/random/", {
          headers: {
            signal: controller.signal,
            Authorization: `Bearer ${JSON.parse(localStorage.jwtToken)}`,
          },
        });
        console.log(response.data);
        isMounted && setTip(response.data);
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
      <h1 className="text-center" style={styles.h1}>
        {tip.advice}
      </h1>
      <p className="text-center" style={styles.p}>
        {tip.description}
      </p>
    </div>
  );
}

export default RandomTip;
