import Search from "./search/Search";
import "./Statistics.css";
import { useState, useEffect } from "react";

import { getStats } from "../../client/http/httpClient";

import AthleteCardComponent from "./card/athletes/AthleteCardComponent";
import ClubsCardComponent from "./card/clubs/ClubsCardComponent";
import LeaguesCardComponent from "./card/leagues/LeaguesCardComponent";

function Statistics() {
  const [stats, setStats] = useState([]);
  const [statsFiltered, setStatsFiltered] = useState([]);

  function fetchStats() {
    const data = {};
    getStats(data).then(() => {
      setStats(data);
      setStatsFiltered(data);
    });
  }

  function handleSearchChange(data) {
    if (
      data &&
      Object.keys(data).length === 0 &&
      Object.getPrototypeOf(data) === Object.prototype
    ) {
      setStatsFiltered(stats);
    } else {
      setStatsFiltered(data);
    }
  }

  useEffect(() => {
    fetchStats();
  }, []);
  return (
    <div>
      <Search handleSearchChange={handleSearchChange} />
      <AthleteCardComponent statsFiltered={statsFiltered} />
      <hr />
      <ClubsCardComponent statsFiltered={statsFiltered} />
      <hr />
      <LeaguesCardComponent statsFiltered={statsFiltered} />
      <hr />
    </div>
  );
}

export default Statistics;
