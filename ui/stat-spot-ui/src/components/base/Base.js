import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";

import "./Base.css";
import History from "../history/History";

function Base() {
  return (
    <div>
      <h1 className="header bg">StatSpot</h1>
      <div>
        <Tabs
          defaultActiveKey="history"
          id="uncontrolled-tab-example"
          className="mb-3"
        >
          <Tab eventKey="statistics" title="Statistics">
            <div className="link">
              The Statistics Page is under developement. We will be up and running
              soon!
            </div>
          </Tab>
          <Tab eventKey="history" title="History">
            <History />
          </Tab>
          <Tab eventKey="subscribe" title="Subscribe">
            <div className="link">
              The Subscribe Page is under developement. We will be up and running
              soon!
            </div>
          </Tab>
        </Tabs>
      </div>
    </div>
  );
}

export default Base;
