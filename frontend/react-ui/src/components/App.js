import '../styles/App.css';
import DataInput from "./DataInput.js";
import DisplayResults from "./DisplayResults.js";
import {React, useState, useEffect, useRef} from "react";
import Login from "./Login.js";
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
	let [userCredentials, setUserCredentials] = useState(null);

	return (
		<>
			<DisplayResults/>
			<Login setCredentials={setUserCredentials}/>
			{userCredentials && 
				<span>{userCredentials["username"]}</span>
			}
		</>
	);
}

export default App;
