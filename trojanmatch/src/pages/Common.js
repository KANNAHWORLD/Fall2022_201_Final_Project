import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8082/user",
  headers: {
    "Content-type": "application/json"
  }
});