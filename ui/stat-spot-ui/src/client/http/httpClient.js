import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8008/",
});

export async function getYoungPlayersMarketValue(data) {
  await api.get("history/market-value/young").then((res) => {
    data.push(...res.data)
  });
}

export async function getLowestHomeGoalLeagues(data) {
    await api.get("history/leagues/goals/home/lowest").then((res) => {
      data.push(...res.data)
    });
  }

