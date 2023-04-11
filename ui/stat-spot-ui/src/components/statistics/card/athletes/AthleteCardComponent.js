import "./AthleteCardComponent.css";
import Card from "react-bootstrap/Card";
import React, { useState, useEffect } from "react";

import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";

import {
  getMetadata,
  postAthlete,
  putAthlete,
  deleteAthlete,
} from "../../../../client/http/httpClient";

function AthleteCardComponent(props) {
  const [show, setShow] = useState(false);
  const [meta, setMeta] = useState({});
  const [athlete, setAthlete] = useState({});

  const [showClicked, setShowClicked] = useState(false);
  const [clickedAthlete, setClickedAthlete] = useState({});
  const [athleteEdit, setAthleteEdit] = useState({});

  function fetchMeta() {
    const data = {};
    getMetadata(data).then(() => {
      setMeta(data);
    });
  }

  useEffect(() => {
    fetchMeta();
  }, []);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const handleSubmit = () => {
    postAthlete(athlete);
    setShow(false);
  };

  const handleCardClick = (athlete) => {
    setShowClicked(true);
    setClickedAthlete(athlete);
    setAthleteEdit(athlete);
  };

  const handleCardClickClose = () => setShowClicked(false);

  const handleCardClickEdit = () => {
    putAthlete(athleteEdit);
    setShowClicked(false);
  };

  const handleCardClickDelete = () => {
    deleteAthlete(clickedAthlete.athleteId);
    setShowClicked(false);
  };

  return (
    <>
      <div className="flex-layout">
        <h2 className="font-color-blue-sub-heading">Athletes</h2>
        <div className="font-awesome-plus">
          <i class="fa-solid fa-circle-plus fa-2x" onClick={handleShow}></i>
        </div>
      </div>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add Athlete</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3" controlId="athlete.Name">
              <Form.Label>Name</Form.Label>
              <Form.Control
                type="text"
                onChange={(e) => {
                  var jsonToPost = athlete;
                  jsonToPost.name = e.target.value;
                  setAthlete(jsonToPost);
                }}
                autoFocus
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="athlete.Position">
              <Form.Label>Position</Form.Label>
              <Form.Control
                type="text"
                onChange={(e) => {
                  var jsonToPost = athlete;
                  jsonToPost.position = e.target.value;
                  setAthlete(jsonToPost);
                }}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="athlete.DOB">
              <Form.Label>Date of Birth</Form.Label>
              <Form.Control
                type="text"
                placeholder="yyyy-mm-dd"
                onChange={(e) => {
                  var jsonToPost = athlete;
                  jsonToPost.dateOfBirth = e.target.value;
                  setAthlete(jsonToPost);
                }}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="athlete.MarketValue">
              <Form.Label>Market Value</Form.Label>
              <Form.Control
                type="text"
                placeholder="in mil"
                onChange={(e) => {
                  var jsonToPost = athlete;
                  jsonToPost.marketValue = e.target.value;
                  setAthlete(jsonToPost);
                }}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="athlete.Sex">
              <Form.Label>Sex</Form.Label>
              <Form.Control
                type="text"
                placeholder="Male/Female"
                onChange={(e) => {
                  var jsonToPost = athlete;
                  jsonToPost.sex = e.target.value;
                  setAthlete(jsonToPost);
                }}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="athlete.Club">
              <Form.Select
                onChange={(e) => {
                  var jsonToPost = athlete;
                  jsonToPost.clubId = e.target.value;
                  setAthlete(jsonToPost);
                }}
              >
                <option>Select Club</option>
                {meta &&
                  meta.clubs &&
                  meta.clubs.map((c) => {
                    return <option value={c.clubId}>{c.name}</option>;
                  })}
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3" controlId="athlete.Country">
              <Form.Select
                onChange={(e) => {
                  var jsonToPost = athlete;
                  jsonToPost.countryId = e.target.value;
                  setAthlete(jsonToPost);
                }}
              >
                <option>Select Country</option>
                {meta &&
                  meta.countries &&
                  meta.countries.map((c) => {
                    return <option value={c.countryId}>{c.name}</option>;
                  })}
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3" controlId="athlete.Sport">
              <Form.Select
                onChange={(e) => {
                  var jsonToPost = athlete;
                  jsonToPost.sportId = e.target.value;
                  setAthlete(jsonToPost);
                }}
              >
                <option>Select Sport</option>
                {meta &&
                  meta.sports &&
                  meta.sports.map((c) => {
                    return <option value={c.sportId}>{c.name}</option>;
                  })}
              </Form.Select>
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleSubmit}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>

      {/* Clicked Athlete */}
      <Modal show={showClicked} onHide={handleCardClickClose}>
        <Modal.Header closeButton>
          <Modal.Title>Athlete Details</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3" controlId="clickedAthlete.Name">
              <Form.Label>Name</Form.Label>
              <Form.Control
                type="text"
                placeholder={clickedAthlete.name}
                onChange={(e) => {
                  var jsonToPut = clickedAthlete;
                  jsonToPut.name = e.target.value;
                  setAthleteEdit(jsonToPut);
                }}
                autoFocus
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="clickedAthlete.Position">
              <Form.Label>Position</Form.Label>
              <Form.Control
                type="text"
                placeholder={clickedAthlete.position}
                onChange={(e) => {
                  var jsonToPut = clickedAthlete;
                  jsonToPut.position = e.target.value;
                  setAthleteEdit(jsonToPut);
                }}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="clickedAthlete.DOB">
              <Form.Label>Date of Birth</Form.Label>
              <Form.Control
                type="text"
                placeholder={clickedAthlete.dateOfBirth}
                onChange={(e) => {
                  var jsonToPut = clickedAthlete;
                  jsonToPut.dateOfBirth = e.target.value;
                  setAthleteEdit(jsonToPut);
                }}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="clickedAthlete.MarketValue">
              <Form.Label>Market Value (in mil)</Form.Label>
              <Form.Control
                type="text"
                placeholder={clickedAthlete.marketValue}
                onChange={(e) => {
                  var jsonToPut = clickedAthlete;
                  jsonToPut.marketValue = e.target.value;
                  setAthleteEdit(jsonToPut);
                }}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="clickedAthlete.Sex">
              <Form.Label>Sex</Form.Label>
              <Form.Control
                type="text"
                placeholder={clickedAthlete.sex}
                onChange={(e) => {
                  var jsonToPut = clickedAthlete;
                  jsonToPut.sex = e.target.value;
                  setAthleteEdit(jsonToPut);
                }}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="clickedAthlete.Club">
              <Form.Label>Club</Form.Label>
              <Form.Select
                onChange={(e) => {
                  var jsonToPut = clickedAthlete;
                  jsonToPut.clubId = e.target.value;
                  setAthleteEdit(jsonToPut);
                }}
              >
                <option value={clickedAthlete.clubId}>
                  {clickedAthlete.clubName}
                </option>
                {meta &&
                  meta.clubs &&
                  meta.clubs.map((c) => {
                    return <option value={c.clubId}>{c.name}</option>;
                  })}
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3" controlId="clickedAthlete.Country">
              <Form.Label>Country</Form.Label>
              <Form.Select
                onChange={(e) => {
                  var jsonToPut = clickedAthlete;
                  jsonToPut.countryId = e.target.value;
                  setAthleteEdit(jsonToPut);
                }}
              >
                <option value={clickedAthlete.countryId}>
                  {clickedAthlete.countryName}
                </option>
                {meta &&
                  meta.countries &&
                  meta.countries.map((c) => {
                    return <option value={c.countryId}>{c.name}</option>;
                  })}
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3" controlId="clickedAthlete.Sport">
              <Form.Label>Sport</Form.Label>
              <Form.Select
                onChange={(e) => {
                  var jsonToPut = clickedAthlete;
                  jsonToPut.sportId = e.target.value;
                  setAthleteEdit(jsonToPut);
                }}
              >
                <option value={clickedAthlete.sportId}>
                  {clickedAthlete.sportName}
                </option>
                {meta &&
                  meta.sports &&
                  meta.sports.map((c) => {
                    return <option value={c.sportId}>{c.name}</option>;
                  })}
              </Form.Select>
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCardClickClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleCardClickEdit}>
            Edit
          </Button>
          <Button variant="danger" onClick={handleCardClickDelete}>
            Delete
          </Button>
        </Modal.Footer>
      </Modal>

      <div className="container">
        {props.statsFiltered &&
          props.statsFiltered.athletes &&
          props.statsFiltered.athletes.length > 0 &&
          props.statsFiltered.athletes.map((athlete) => {
            return (
              <div className="box">
                <Card onClick={() => handleCardClick(athlete)}>
                  <Card.Body className="card-bg">
                    <Card.Title>{athlete.name}</Card.Title>
                    <Card.Subtitle className="mb-2 text-muted">
                      {athlete.position}
                    </Card.Subtitle>
                    <Card.Text>Club : {athlete.clubName}</Card.Text>
                    <Card.Text>Country : {athlete.countryName}</Card.Text>
                    <Card.Text>
                      Market Value : {athlete.marketValue} mil
                    </Card.Text>
                  </Card.Body>
                </Card>
              </div>
            );
          })}
      </div>
    </>
  );
}

export default AthleteCardComponent;
