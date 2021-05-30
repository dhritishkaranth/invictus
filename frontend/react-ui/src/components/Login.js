import {React, useState} from "react";
import {Button, Form, Alert} from "react-bootstrap";
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';

const Login = (props) => {

	const [loginStatus, setloginStatus] = useState(null);

	const submitHandler = (event) => {
		event.preventDefault();
		if (loginStatus) {
			setloginStatus({variant: "primary", msg: "You are already logged in!"});
			return;
		}
		let info = {username: event.target[0].value, password: event.target[1].value};
		//let info = {username: "ysingh", password: "ysingh"};
		console.log("Login event: ", info);

		let promise = axios.get("http://localhost:9091/login", {auth: info, validateStatus: false});
		promise.then(res => {
			if (res.status === 404) {
				console.log("Invalid user");
				setloginStatus({variant: "info", msg: "User does not exist."});
			}
			else if (res.status === 401) {
				console.log("Wrong password");
				setloginStatus({variant: "warning", msg: "Incorrect password."});
			}
			else if (res.status === 200) {
				console.log("Auth successful");
				console.log("login response: ", res);
				setloginStatus({variant: "success", msg: "Authentication successful."});
				props.setCredentials(info);
			}
			else {
				console.log("Some other state");
				setloginStatus({variant: "danger", msg: `Some other error occurred -- server returned code ${res.status}.`});
			}
			//console.log("login response: ", res);
		})
	};

	return (
		<>
			<Form onSubmit={submitHandler} id="loginForm" name="loginForm">
				<Form.Group>
					<Form.Label>Username</Form.Label>
					<Form.Control  type="text" placeholder="Username" name="username"></Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<hr/>

				<Form.Group>
					<Form.Label>Password</Form.Label>
					<Form.Control  type="password" placeholder="Password" name="password"></Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<hr/>

				<Button variant="success" type="submit" className="btn-toolbar">Sign up or log in</Button>
			</Form>
			<hr/>
			{loginStatus &&
				<Alert variant={loginStatus["variant"]}>
					{`${loginStatus["msg"]}`}
				</Alert>
			}
		</>
	);
};

export default Login;
