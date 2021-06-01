import StepWizard from "react-step-wizard";
import {ProgressBar, Button, Form, Container, Navbar, Alert} from "react-bootstrap";
import {useState} from "react";
import axios from "axios";
import NavbarComponent from "./NavbarComponent";
import BootstrapSwitchButton from 'bootstrap-switch-button-react';
import 'bootstrap/dist/css/bootstrap.min.css';

const Step1 = (props) => {

	const [state, setState] = useState({firstName: "", secondName: "", gender: "", resources: []});

	const submitHandler = (event) => {
		event.preventDefault();
		let newData = {
			firstName: event.target[0].value,
			secondName: event.target[1].value,
			gender: event.target[2].value,
			resources: [event.target[3].value]
		};
		setState(newData);
		props.onCompleteHandler(newData);
		console.log("Step 1 completed", newData);
		props.nextStep();
	}

	return (
		<div className="Wizard-Step">
			<h4>You are in step 1</h4>
			<Form onSubmit={submitHandler} id="step1form" name="step1form">
			<ProgressBar now={0}/>
				<hr/>
				<Form.Group>
					<Form.Label>First name</Form.Label>
					<Form.Control required type="text" placeholder="Enter first name" name="firstName"></Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<hr/>
				<Form.Group>
					<Form.Label>Last name</Form.Label>
					<Form.Control required type="text" placeholder="Enter last name" name="lastName"></Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<hr/>
				<Form.Group className="Row-Horiz-Spread">
					<Form.Label>Gender</Form.Label>
					<Form.Control required as="select" custom name="gender">
						<option value="" selected disabled>Please choose one</option>
						<option value="Male">Male</option>
						<option value="Female">Female</option>
						<option value="other">Other</option>
					</Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<hr/>
				<Form.Group>
					<Form.Label>Social media profile link</Form.Label>
					<Form.Control required type="text" placeholder="facebook.com/yourprofile" name="smprofile"></Form.Control>
					<Form.Text muted>This might be visible to other users.</Form.Text>
				</Form.Group>
				<hr/>
				<div className="button-align">
					<Button variant="primary" className="btn-toolbar" disabled>Back</Button>{'      '}
					<Button variant="primary" type="submit" className="btn-toolbar">Next</Button>
				</div>
			</Form>
		</div>
	);
}

const Step2 = (props) => {

	const [curState, setState] = useState({city: "", state: "", languages: []});

	const submitHandler = (event) => {
		event.preventDefault();
		let newData = {
			city: event.target[0].value,
			state: event.target[1].value,
			languages: []
		};
		for (let lang of event.target[2].selectedOptions) {
			newData.languages.push(lang.value);
		}
		setState(newData);
		props.onCompleteHandler(newData);
		console.log("Step 2 completed", newData);
		props.nextStep();
	}

	return (
		<div className="Wizard-Step">
			<h4>You are in step 2</h4>
			<Form onSubmit={submitHandler} id="step2form" name="step2form">
			<ProgressBar now={33}/>
			<hr/>
				<Form.Group>
					<Form.Label>Neighborhood, city</Form.Label>
					<Form.Control required type="text" placeholder="Palo Verde Rd, Irvine" name="city"></Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<hr/>
				<Form.Group>
					<Form.Label>Two-letter state code</Form.Label>
					<Form.Control required type="text" placeholder="E.g. CA" name="state"></Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<hr/>
				<Form.Group className="Row-Horiz-Spread">
					<Form.Label>Languages</Form.Label>
					<Form.Control multiple required as="select" custom name="lang">
						<option value="" selected disabled>Pick at least one</option>
						<option value="English">English</option>
						<option value="Spanish">Spanish / Español</option>
						<option value="Kannada">Kannada / ಕನ್ನಡ</option>
						<option value="Hindi">Hindi / हिन्दी</option>
						<option value="Chinese">Chinese / 汉语</option>
						<option value="French">French / Français</option>
					</Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<hr/>
				<div className="button-align">
					<Button variant="primary" className="btn-toolbar" onClick={() => props.previousStep()}>Back</Button>{' '}
					<Button variant="primary" type="submit" className="btn-toolbar">Next</Button>
				</div>
			</Form>
		</div>
	);
}

const Step3 = (props) => {

	const [curState, setState] = useState({illness: "", isAnonymous: false, typeOfSeeker: "Caregiver"});

	const submitHandler = (event) => {
		event.preventDefault();
		let newData = {
			illness: event.target[0].value,
			isAnonymous: curState.isAnonymous,
			typeOfSeeker: curState["typeOfSeeker"]
		};
		setState(newData);
		props.onCompleteHandler(newData);
		console.log("Step 3 completed", newData);
		props.nextStep();
	}

	const onAnonToggleChange = (checked) => {
		setState((prevState) => {
			return {...prevState,  isAnonymous: checked};
		});
	}

	const onSeekerToggleChange = (checked) => {
		if (checked) {
			setState((prevState) => {
				return {...prevState, typeOfSeeker: "Patient"};
			});
		}
		else {
			setState((prevState) => {
				return {...prevState, typeOfSeeker: "Caregiver"};
			});
		}
	}

	return (
		<div className="Wizard-Step">
			<h4>You are in step 3</h4>
			<Form onSubmit={submitHandler} id="step3form" name="step3form">
			<ProgressBar now={66}/>
				<hr/>
				<Form.Group className="Row-Horiz-Spread">
					<Form.Label>Illnesses</Form.Label>
					<Form.Control required as="select" custom name="illness">
						<option value="" selected disabled>Please choose one</option>

						<option value="Alzheimers">Alzheimers</option>
						<option value="Heart Cancer">Heart disease</option>
						<option value="Kidney Cancer">Kidney cancer</option>
						<option value="Leukemia">Leukemia</option>
						<option value="Liver Cancer">Liver cancer</option>
						<option value="Lung Cancer">Lung cancer</option>
						<option value="Pancreatic Cancer">Pancreatic cancer</option>
						<option value="Parkinsons">Parkinsons</option>
						<option value="Thyroid Cancer">Thyroid cancer</option>

					</Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<hr/>
				<Form.Group className="Row-Horiz-Spread">
					<Form.Label>Stay anonymous? </Form.Label>
					<BootstrapSwitchButton checked={curState["isAnonymous"]} onlabel={"Yes"} offlabel={"No"} onChange={onAnonToggleChange}/>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<Form.Text muted>If you choose not to, then you might be contacted by other users.</Form.Text>
				<hr/>
				<Form.Group className="Row-Horiz-Spread">
					<Form.Label>Are you the patient?</Form.Label>
					<BootstrapSwitchButton checked={curState["typeOfSeeker"] === "Patient"} onlabel={"Yes"} offlabel={"No"} onChange={onSeekerToggleChange}/>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<Form.Text muted>Say no if you are the caregiver to the patient.</Form.Text>
				<hr/>
				<div className="button-align">
					<Button variant="primary" className="btn-toolbar" onClick={() => props.previousStep()}>Back</Button>{' '}
					<Button variant="primary" type="submit" className="btn-toolbar">Next</Button>
				</div>
			</Form>
		</div>
	);
}

const Step4 = (props) => {
	
	const submitHandler = (event) => {
		event.preventDefault();
		props.onCompleteHandler();
	}

	return (
		<div className="Wizard-Step">
			<h4>You are done!</h4>
			<Form onSubmit={submitHandler} id="step4form" name="step4form">
				<ProgressBar now={100}/>
				<hr/>
				<div className="button-align">
					<Form.Text muted>Change info</Form.Text>
					<Form.Text muted>Proceed.</Form.Text>
				</div>
				<div className="button-align">
					<Button variant="primary" className="btn-toolbar" onClick={() => props.previousStep()}>Back</Button>{' '}
					<Button variant="primary" type="submit" className="btn-toolbar">Submit</Button>
				</div>
			</Form>
			<hr/>
			{ props.showAlert &&
			<Alert variant="success">
					{"Successfully created user account. You will be redirected soon."}
			</Alert>
			}
		</div>
	);
}

const DataInput = (props) => {

	let [showAlert, setShowAlert] = useState(false);

	let s1 = {}, s2 = {}, s3 = {};

	const f1Update = (data) => {
		s1 = {...data};
	}

	const f2Update = (data) => {
		s2 = {...data};
	}

	const f3Update = (data) => {
		s3 = {...data};
	}

	const submitHandler = () => {

		let profileData = {
			firstName: s1.firstName,
			secondName: s1.secondName,
			gender: s1.gender,
			resources: s1.resources,
			username: props.userCredentials["creds"]["username"],
			password: props.userCredentials["creds"]["password"],
			languages: s2.languages,
			location: s2.city + ", " + s2.state,
			typeOfSeeker: s3.typeOfSeeker,
			typeOfIllness: s3.illness,
			anonymous: s3.isAnonymous,
			age: 0,
		};

		console.log("Step 4 handler from parent");
		console.log(profileData);
		let url = `${process.env.REACT_APP_PROTOCOL}://${process.env.REACT_APP_DOMAIN}/invictus/v1/users`;
		//axios.post("https://en00k5ay06pzq1l.x.pipedream.net", profileData).then(res => console.log("POST req. done", res));
		axios.post(url, profileData, {validateStatus: false}).then((res) => {
				console.log("User created.", res);
				
				setShowAlert(true);
				setTimeout(() => {
					props.setSignedUpState(true);
				}, 3000);
				
		});
	}

	return (
		<>
			<NavbarComponent userCredentials={props.userCredentials}/>
			<h2>Please fill in the required info to complete your profile.</h2>
			<hr/>
			<StepWizard initialStep={1}>
				<Step1 onCompleteHandler={f1Update}/>
				<Step2 onCompleteHandler={f2Update}/>
				<Step3 onCompleteHandler={f3Update}/>
				<Step4 onCompleteHandler={submitHandler} isSignedUp={props.isSignedUp} showAlert={showAlert}/>
			</StepWizard>
		</>
	);
}

export default DataInput;