import {React, useState} from "react";
import {Button, Form, Alert} from "react-bootstrap";
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';

const Login = (props) => {

	const [loginStatus, setloginStatus] = useState(null);

	const submitHandler = (event) => {
		event.preventDefault();

		/*
		if (loginStatus) {
			setloginStatus({variant: "primary", msg: "You are already logged in!"});
			return;
		}
		*/
		
		let info = {username: event.target[0].value, password: event.target[1].value};
		//let info = {username: "ysingh", password: "ysingh"};
		console.log("Login event: ", info);

		let promise = axios.get(`${process.env.REACT_APP_PROTOCOL}://${process.env.REACT_APP_DOMAIN}/login`, {auth: info, validateStatus: false});
		promise.then(res => {
			if (res.status === 404) {
				console.log("Invalid user");
				setloginStatus({variant: "info", msg: "This username is not taken; we will create your profile after you answer some questions."});
				setTimeout(
					() => {
						props.setCredentials({creds: info});
					},
					4000);
			}
			else if (res.status === 401) {
				console.log("Wrong password");
				setloginStatus({variant: "warning", msg: "Incorrect password."});
			}
			else if (res.status === 200) {
				console.log("Auth successful");
				console.log("login response: ", res);
				setloginStatus({variant: "success", msg: "Authentication successful."});

				let userQuery = axios.get(`${process.env.REACT_APP_PROTOCOL}://${process.env.REACT_APP_DOMAIN}/invictus/v1/users/username/${info.username}`, {auth: info, validateStatus: false});
				userQuery.then(uRes => {
					console.log("Queried user location.");
					console.log(uRes);
					setTimeout(
						() => {
							props.setSignedUpState(true);
							props.setCredentials({creds: info, location: uRes["data"]["location"]});
						},
						2000
					);
				});
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
				<div className="text-center">
				<Button variant="success" type="submit" >Sign up or log in</Button>
				</div>
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
