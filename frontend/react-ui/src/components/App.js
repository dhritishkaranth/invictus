import '../styles/App.css';
import DataInput from "./DataInput.js";
import DisplayResults from "./DisplayResults.js";
import {React, useState, useEffect, useRef} from "react";
import Login from "./Login.js";
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
	let [userCredentials, setUserCredentials] = useState(null);
	let [isSignedUp, setSignedUpState] = useState(false);
	return (
		<>
			{!userCredentials && <Login setSignedUpState={setSignedUpState} setCredentials={setUserCredentials}/>}
			{userCredentials && !isSignedUp && <DataInput setSignedUpState={setSignedUpState} userCredentials={userCredentials}/>}
			{userCredentials && isSignedUp && <DisplayResults userCredentials={userCredentials}/>}
		</>
	);
}

export default App;
