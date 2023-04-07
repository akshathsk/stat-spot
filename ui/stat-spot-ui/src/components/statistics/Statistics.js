import Search from "./search/Search";
import "./Statistics.css";
import { useState, useEffect } from "react";

import { getStats } from "../../client/http/httpClient";

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
      <div>{statsFiltered && JSON.stringify(statsFiltered)}</div>
    </div>
  );
}

export default Statistics;
