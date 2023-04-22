import "./Subscribe.css";
import { useEffect, useState } from "react";
import { getAthleteList, subscribe } from "../../client/http/httpClient";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

function Subscribe() {
  const [athleteNames, setAthleteNames] = useState([]);
  const [value, setValue] = useState("");
  const [email, setEmail] = useState("");

  function fetchAthletes() {
    const data = [];
    getAthleteList(data).then(() => {
      setAthleteNames(data);
    });
  }

  const handleChange = () => {
    console.log(value);
    console.log(email);
    subscribe({athletes: value, emailId: email}).then(res => {
        alert("Congratulations! Your subscription was successful!")
    })
  };

  useEffect(() => {
    fetchAthletes();
  }, []);

  return (
    <div className="centered-div">
      <h1 className="text-center">Subscribe</h1>
      <div className="outer-sub">
        <Form>
          <Form.Group className="mb-3" controlId="athlete.Name">
            <Form.Control
              type="text"
              placeholder="Email"
              onChange={(e) => {
                setEmail(e.target.value);
              }}
              autoFocus
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="athlete.Club">
            <Form.Select
              onChange={(e) => {
                setValue(e.target.value);
              }}
            >
              <option>Athlete</option>
              {athleteNames &&
                athleteNames.map((c) => {
                  return <option value={c}>{c}</option>;
                })}
            </Form.Select>
          </Form.Group>
          <Button className = "sub-button" variant="primary" onClick={handleChange}>
            Send
          </Button>
        </Form>
      </div>
    </div>
  );
}

export default Subscribe;
