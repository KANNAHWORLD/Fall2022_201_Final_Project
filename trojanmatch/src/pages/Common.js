import axios from "axios";

export default axios.create({
  baseURL: "http://34.130.1.66:8082/user",
  headers: {
    "Content-type": "application/json"
  }
});