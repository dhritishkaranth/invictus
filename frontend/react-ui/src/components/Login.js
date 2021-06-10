import {React, useState} from "react";
import {Button, Form, Alert} from "react-bootstrap";
import axios from "axios";
import NavbarComponent from "./NavbarComponent";
import CarouselComponent from "./Carousel.js";
import 'bootstrap/dist/css/bootstrap.min.css';

const Login = (props) => {

	const [loginStatus, setloginStatus] = useState(null);

	const submitHandler = (event) => {
		event.preventDefault();


		let info = {username: event.target[0].value, password: event.target[1].value};
		console.log("Login event: ", info);

		let promise = axios.get(`${process.env.REACT_APP_PROTOCOL}://${process.env.REACT_APP_DOMAIN}/login`, {auth: info, validateStatus: false});
		promise.then(res => {
			if (res.status === 404) {
				console.log("Invalid user");
				setloginStatus({variant: "info", msg: "This username is not taken. We will create your profile after you answer some questions."});
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
							props.setCredentials({creds: info, location: {loc: uRes["data"]["location"], lat: uRes["data"]["lat"], lng: uRes["data"]["lng"]}});
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
			<NavbarComponent userCredentials={null}/>

			<h3 style={{paddingTop: "20px", paddingBottom: "50px"}}>Welcome! Please sign in below.</h3>

			<div style={{display: "flex", flexDirection: "row", justifyContent: "space-between", maxWidth: "1000px"}}>

			<img style={{width: "300px", height: "175px", marginRight: "200px"}} src="https://i.imgur.com/FycmgXY.png"></img>

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

				<hr/>
				{loginStatus &&
				<Alert className="text-center" style={{width: "200px"}} variant={loginStatus["variant"]}>
					{`${loginStatus["msg"]}`}
				</Alert>
			}
			</Form>
			</div>
		</>
	);
};

export default Login;
