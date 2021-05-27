import StepWizard from "react-step-wizard";
import {ProgressBar, Button, Form} from "react-bootstrap";
import {useState} from "react";
import axios from "axios";
import BootstrapSwitchButton from 'bootstrap-switch-button-react';
import 'bootstrap/dist/css/bootstrap.min.css';

const Step1 = (props) => {

	const [state, setState] = useState({fname: "", lname: "", gender: ""});

	const submitHandler = (event) => {
		event.preventDefault();
		let newData = {
			fname: event.target[0].value,
			lname: event.target[1].value,
			gender: event.target[2].value
		};
		setState(newData);
		props.onCompleteHandler(newData);
		console.log("Step 1 completed", newData);
		props.nextStep();
	}

	return (
		<>
			<h2>You are in step 1</h2>
			<Form onSubmit={submitHandler} id="step1form" name="step1form">
			<ProgressBar now={0}/>
				<Form.Group>
					<Form.Label>First name</Form.Label>
					<Form.Control required type="text" placeholder="Enter first name" name="firstName"></Form.Control>
					<Form.Text muted>Please enter only Latin characters.</Form.Text>
				</Form.Group>
				<hr/>
				<Form.Group>
					<Form.Label>Last name</Form.Label>
					<Form.Control required type="text" placeholder="Enter last name" name="lastName"></Form.Control>
					<Form.Text muted>Please enter only Latin characters.</Form.Text>
				</Form.Group>
				<hr/>
				<Form.Group>
					<Form.Label className="col-md-4">Gender</Form.Label>
					<Form.Control required as="select" custom name="gender">
						<option value="" selected disabled>Please choose one</option>
						<option value="male">Male</option>
						<option value="female">Female</option>
						<option value="other">Other</option>
					</Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<hr/>
				<div className="button-align">
					<Button variant="primary" className="btn-toolbar" disabled>Back</Button>{'      '}
					<Button variant="primary" type="submit" className="btn-toolbar">Next</Button>
				</div>
			</Form>
		</>
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
		<>
			<h2>You are in step 2</h2>
			<Form onSubmit={submitHandler} id="step2form" name="step2form">
			<ProgressBar now={33}/>
				<Form.Group>
					<Form.Label>City</Form.Label>
					<Form.Control required type="text" placeholder="Enter city" name="Enter city"></Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<hr/>
				<Form.Group>
					<Form.Label className="col-md-4">State</Form.Label>
					<Form.Control required as="select" custom name="state">
						<option value="" selected disabled>Please choose one</option>
						<option value="CA">California</option>
						<option value="WA">Washington</option>
						<option value="NV">Nevada</option>
						<option value="AZ">Arizona</option>
						<option value="OR">Oregon</option>
					</Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<hr/>
				<Form.Group>
					<Form.Label className="col-md-5">Languages</Form.Label>
					<Form.Control multiple required as="select" custom name="lang">
						<option value="" selected disabled>Pick at least one</option>
						<option value="en">English</option>
						<option value="es">Spanish</option>
						<option value="fr">French</option>
						<option value="ka">Kannada</option>
					</Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<hr/>
				<div className="button-align">
					<Button variant="primary" className="btn-toolbar" onClick={() => props.previousStep()}>Back</Button>{' '}
					<Button variant="primary" type="submit" className="btn-toolbar">Next</Button>
				</div>
			</Form>
		</>
	);
}

const Step3 = (props) => {

	const [curState, setState] = useState({illnesses: [], isAnonymous: false});

	const submitHandler = (event) => {
		event.preventDefault();
		let newData = {
			illnesses: [],
			isAnonymous: curState.isAnonymous
		};
		for (let illness of event.target[0].selectedOptions) {
			newData.illnesses.push(illness.value);
		}
		setState(newData);
		props.onCompleteHandler(newData);
		console.log("Step 3 completed", newData);
		props.nextStep();
	}

	const onToggleChange = (checked) => {
		setState({...curState, isAnonymous: checked});
	}

	return (
		<>
			<h2>You are in step 3</h2>
			<Form onSubmit={submitHandler} id="step3form" name="step3form">
			<ProgressBar now={66}/>
			<hr/>
				<Form.Group>
					<Form.Label className="col-md-4">Illnesses</Form.Label>
					<Form.Control multiple required as="select" custom name="illness">
						<option value="" selected disabled>Please choose one</option>
						<option value="leukemia">Leukemia</option>
						<option value="cancer">Cancer</option>
						<option value="other">Other</option>
					</Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<hr/>
				<Form.Group>
					<Form.Label>Stay anonymous? </Form.Label>
					<BootstrapSwitchButton checked={false} onlabel={"Yes"} offlabel={"No"} onChange={onToggleChange}/>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<hr/>
				<div className="button-align">
					<Button variant="primary" className="btn-toolbar" onClick={() => props.previousStep()}>Back</Button>{' '}
					<Button variant="primary" type="submit" className="btn-toolbar">Next</Button>
				</div>
			</Form>
		</>
	);
}

const Step4 = (props) => {
	
	const submitHandler = (event) => {
		event.preventDefault();
		props.onCompleteHandler();
	}

	return (
		<>
			<h2>You are done!</h2>
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
		</>
	);
}

function DataInput() {

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
		console.log("Step 4 handler from parent");
		console.log(s1, s2, s3);
		axios.post("https://enbrivgmjobza.x.pipedream.net", {...s1, ...s2, ...s3}).then(res => console.log("POST req. done", res));
	}

	return (
		<StepWizard initialStep={1}>
			<Step1 onCompleteHandler={f1Update}/>
			<Step2 onCompleteHandler={f2Update}/>
			<Step3 onCompleteHandler={f3Update}/>
			<Step4 onCompleteHandler={submitHandler}/>
		</StepWizard>
	);
}

export default DataInput;