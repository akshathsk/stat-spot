import "./LeaguesCardComponent.css";
import Card from "react-bootstrap/Card";

function LeaguesCardComponent(props) {
  return (
    <>
      <h2 className="font-color-blue-sub-heading">Leagues</h2>
      <div className="container">
        {props.statsFiltered &&
          props.statsFiltered.leagues &&
          props.statsFiltered.leagues.length > 0 &&
          props.statsFiltered.leagues.map((league) => {
            return (
              <div className="box">
                <Card>
                  <Card.Body className="card-bg">
                    <Card.Title>{league.type}</Card.Title>
                    <Card.Subtitle className="mb-2 text-muted">
                      {league.subType}
                    </Card.Subtitle>
                  </Card.Body>
                </Card>
              </div>
            );
          })}
      </div>
    </>
  );
}

export default LeaguesCardComponent;
