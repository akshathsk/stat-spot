import Base from "../src/components/base/Base.js";
import Register from "./components/register/Register.js";
import { BrowserRouter } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <Register />
    </BrowserRouter>
  );
}

export default App;
