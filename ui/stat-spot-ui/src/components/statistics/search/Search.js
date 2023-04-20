import "./Search.css";
import { useState } from "react";
import Button from "react-bootstrap/Button";
import { getStatsWithSearchString } from "../../../client/http/httpClient";

function Search(props) {
  const [searchInput, setSearchInput] = useState("");
  const [resData, setResData] = useState({});

  const handleChange = (e) => {
    e.preventDefault();
    setSearchInput(e.target.value);
  };

  const handleButtonClick = async (e) => {
    if (searchInput != "") {
      const data = {};
      await getStatsWithSearchString(data, searchInput).then(() => {
        setResData(data);
        props.handleSearchChange(data);
      });
    } else {
      setResData({});
      props.handleSearchChange({});
    }
  };

  return (
    <div>
      <div className="outer">
        <hr />
        <div className="inner1">
          {" "}
          <input
            className="full-width"
            type="search"
            placeholder="Search here"
            onChange={handleChange}
            value={searchInput}
          />{" "}
        </div>
        <div className="inner2">
          {" "}
          <Button type="submit" onClick={handleButtonClick}>
            Search
          </Button>{" "}
        </div>
        <hr />
      </div>
    </div>
  );
}

export default Search;
