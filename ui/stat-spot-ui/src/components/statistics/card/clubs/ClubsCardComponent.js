import "./ClubsCardComponent.css";
import Card from "react-bootstrap/Card";

function ClubsCardComponent(props) {
  return (
    <>
      <h2 className="font-color-blue-sub-heading">Clubs</h2>
      <div className="container">
        {props.statsFiltered &&
          props.statsFiltered.clubs &&
          props.statsFiltered.clubs.length > 0 &&
          props.statsFiltered.clubs.map((club) => {
            return (
              <div className="box">
                <Card>
                  <Card.Body className="card-bg">
                    <Card.Title>{club.name}</Card.Title>
                    <Card.Subtitle className="mb-2 text-muted">
                      {club.established}
                    </Card.Subtitle>
                    <Card.Text>Squad Size : {club.squadSize}</Card.Text>
                    <Card.Text>
                      Market Value : {club.totalMarketValue} mil
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

export default ClubsCardComponent;
