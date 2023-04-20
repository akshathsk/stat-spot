import "./History.css";
import { useEffect, useState } from "react";
import {
  getYoungPlayersMarketValue,
  getLowestHomeGoalLeagues,
} from "../../client/http/httpClient";
import Button from "react-bootstrap/Button";

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import { Bar } from "react-chartjs-2";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

function History() {
  const [youngPlayersMarketValues, setYoungPlayersMarketValues] = useState([]);
  const [lowestHomeGoalLeagues, setLowestHomeGoalLeagues] = useState();
  const [searchInputYoungPlayers, setSearchInputYoungPlayers] =
    useState("2000-01-01");
  const [searchInputLowestHomeGoals, setSearchInputLowestHomeGoals] =
    useState("2021");

  function fetchYoungPlayersMarketValue(inputYoungPlayers) {
    const data = [];
    getYoungPlayersMarketValue(data, inputYoungPlayers).then(() => {
      setYoungPlayersMarketValues(data);
    });
  }

  function fetchLowestHomeGoalLeagues(inputLowestHomeGoals) {
    const data = [];
    getLowestHomeGoalLeagues(data, inputLowestHomeGoals).then(() => {
      setLowestHomeGoalLeagues(data);
    });
  }

  useEffect(() => {
    fetchYoungPlayersMarketValue(searchInputYoungPlayers);
    fetchLowestHomeGoalLeagues(searchInputLowestHomeGoals);
  }, []);

  const optionsYoungPlayer = {
    responsive: true,
    plugins: {
      legend: {
        position: "top",
      },
      title: {
        display: true,
        text: "Total Market Value of all Young Players By Clubs",
      },
    },
  };

  const labelsYoungPlayers =
    youngPlayersMarketValues && youngPlayersMarketValues.map((x) => x.clubName);

  const dataYoungPlayers = {
    labels: labelsYoungPlayers,
    datasets: [
      {
        label: "Market Value (in mil)",
        data:
          youngPlayersMarketValues &&
          youngPlayersMarketValues.map((x) => x.totalMarketValue),
        backgroundColor: "rgb(135, 206, 250)",
      },
    ],
  };

  const optionsLowestHomeGoalLeagues = {
    responsive: true,
    plugins: {
      legend: {
        position: "top",
      },
      title: {
        display: true,
        text: "Competitions with the lowest home goals in the 2021 season",
      },
    },
  };

  const labelsLowestHomeGoalLeagues =
    lowestHomeGoalLeagues && lowestHomeGoalLeagues.map((x) => x.leagueName);

  const dataLowestHomeGoalLeagues = {
    labels: labelsLowestHomeGoalLeagues,
    datasets: [
      {
        label: "Number of Goals",
        data:
          lowestHomeGoalLeagues &&
          lowestHomeGoalLeagues.map((x) => x.homeGoalsTotal),
        backgroundColor: "rgb(135, 206, 250)",
      },
    ],
  };

  const handleChangeYoungPlayers = (e) => {
    e.preventDefault();
    setSearchInputYoungPlayers(e.target.value);
  };

  const handleButtonClickYoungPlayers = async (e) => {
    if (searchInputYoungPlayers != "") {
      fetchYoungPlayersMarketValue(searchInputYoungPlayers);
    } else {
      setSearchInputYoungPlayers("2000-01-01");
      fetchYoungPlayersMarketValue("2000-01-01");
    }
  };

  const handleChangeLowestHomeGoals = (e) => {
    e.preventDefault();
    setSearchInputLowestHomeGoals(e.target.value);
  };

  const handleButtonClickLowestHomeGoals = async (e) => {
    if (searchInputLowestHomeGoals != "") {
      fetchLowestHomeGoalLeagues(searchInputLowestHomeGoals);
    } else {
      setSearchInputLowestHomeGoals("2021");
      fetchLowestHomeGoalLeagues("2021");
    }
  };

  return (
    <div>
      <div className="border">
      <hr />
        <div className="search">
          <div className="outer">
            <div className="inner1">
              {" "}
              <input
                className="full-width"
                type="search"
                placeholder="Displaying results for players born after 2000-01-01. Update the date here."
                onChange={handleChangeYoungPlayers}
              />{" "}
            </div>
            <div className="inner2">
              {" "}
              <Button className="shrc-btn" type="submit" onClick={handleButtonClickYoungPlayers}>
                Search
              </Button>{" "}
            </div>
          </div>
        </div>
        <hr />
        <Bar options={optionsYoungPlayer} data={dataYoungPlayers} />
      </div>
      <hr />
      <div className="border">
      <hr />
        <div className="search">
          <div className="outer search">
            <div className="inner1">
              {" "}
              <input
                className="full-width"
                type="search"
                placeholder="Displaying results for 2021 season. Update the season here."
                onChange={handleChangeLowestHomeGoals}
              />{" "}
            </div>
            <div className="inner2">
              {" "}
              <Button className="shrc-btn" type="submit" onClick={handleButtonClickLowestHomeGoals}>
                Search
              </Button>{" "}
            </div>
          </div>
        </div>
        <hr />
        <Bar
          options={optionsLowestHomeGoalLeagues}
          data={dataLowestHomeGoalLeagues}
        />
      </div>
    </div>
  );
}

export default History;
