import "./History.css";
import { useEffect, useState } from "react";
import {
  getYoungPlayersMarketValue,
  getLowestHomeGoalLeagues,
} from "../../client/http/httpClient";

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

  function fetchYoungPlayersMarketValue() {
    const data = [];
    getYoungPlayersMarketValue(data).then(() => {
      setYoungPlayersMarketValues(data);
    });
  }

  function fetchLowestHomeGoalLeagues() {
    const data = [];
    getLowestHomeGoalLeagues(data).then(() => {
      setLowestHomeGoalLeagues(data);
    });
  }

  useEffect(() => {
    fetchYoungPlayersMarketValue();
    fetchLowestHomeGoalLeagues();
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

  const labelsYoungPlayers = youngPlayersMarketValues && youngPlayersMarketValues.map((x) => x.clubName);

  const dataYoungPlayers = {
    labels: labelsYoungPlayers,
    datasets: [
      {
        label: "Market Value (in mil)",
        data: youngPlayersMarketValues && youngPlayersMarketValues.map((x) => x.totalMarketValue),
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

  const labelsLowestHomeGoalLeagues = lowestHomeGoalLeagues && lowestHomeGoalLeagues.map(
    (x) => x.leagueName
  );

  const dataLowestHomeGoalLeagues = {
    labels: labelsLowestHomeGoalLeagues,
    datasets: [
      {
        label: "Number of Goals",
        data: lowestHomeGoalLeagues && lowestHomeGoalLeagues.map((x) => x.homeGoalsTotal),
        backgroundColor: "rgb(135, 206, 250)",
      },
    ],
  };

  return (
    <div> 
      <div className="border">
        <Bar options={optionsYoungPlayer} data={dataYoungPlayers} />
      </div>
        <hr/>
      <div className="border">
        <Bar
          options={optionsLowestHomeGoalLeagues}
          data={dataLowestHomeGoalLeagues}
        />
      </div>
    </div>
  );
}

export default History;
