import axios from "axios";

const api = axios.create({
  baseURL: "http://34.121.253.23:8008/",
  // baseURL: "http://localhost:8008/",
  
});

export async function getYoungPlayersMarketValue(
  data,
  searchInputYoungPlayers
) {
  await api
    .get("history/market-value/young/" + searchInputYoungPlayers)
    .then((res) => {
      data.push(...res.data);
    });
}

export async function getLowestHomeGoalLeagues(
  data,
  searchInputLowestHomeGoals
) {
  await api
    .get("history/leagues/goals/home/lowest/" + searchInputLowestHomeGoals)
    .then((res) => {
      data.push(...res.data);
    });
}

export async function getStats(data) {
  await api.get("stats").then((res) => {
    for (var key in res.data) {
      data[key] = res.data[key];
    }
  });
}

export async function getStatsWithSearchString(data, str) {
  await api.get("stats/" + str).then(async (res) => {
    for (var key in res.data) {
      data[key] = res.data[key];
    }
  });
}

export async function getMetadata(data) {
  await api.get("athlete/metadata").then(async (res) => {
    for (var key in res.data) {
      data[key] = res.data[key];
    }
  });
}

export async function postAthlete(data) {
  await api.post("athlete", data).then(async (res) => {});
}

export async function putAthlete(data) {
  await api.put("athlete/" + data.athleteId, data).then(async (res) => {});
}

export async function deleteAthlete(athleteId) {
  await api.delete("athlete/" + athleteId).then(async (res) => {});
}

export async function getAthleteList(data) {
  await api.get("athlete/names").then(async (res) => {
    data.push(...res.data);
  });
}

export async function subscribe(data) {
  await api.post("subscribe", data).then(async (res) => {
    return "success";
  });
}

export function register(data) {
  api.post("register", data).then((resp) => {
    console.log(resp);
    if (resp["status"] == 200) {
      alert("Successfully registered user");
    } else {
      alert("Failed to register user");
    }
  });
}

export async function loginUser(data, fun) {
  await api.post("login", data).then(async (res) => {
    console.log(res);
    if (res["status"] == 200 && res["data"] == "Success") {
      alert("Login success");
      fun(true)
    } else {
      alert("Please check the username and password");
    }
  });
}