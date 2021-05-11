import './App.css';
import StepWizard from "react-step-wizard";
import {ProgressBar, Button, Form} from "react-bootstrap";
import {useState} from "react";
import 'bootstrap/dist/css/bootstrap.min.css';

const Step1 = (props) => {

	const [state, setState] = useState({fname: "", lname: "", gender: ""});

	const submitHandler = (event) => {
		event.preventDefault();
		setState({
			fname: event.target[0].value,
			lname: event.target[1].value,
			gender: event.target[2].value
		});
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

				<Form.Group>
					<Form.Label>Last name</Form.Label>
					<Form.Control required type="text" placeholder="Enter last name" name="lastName"></Form.Control>
					<Form.Text muted>Please enter only Latin characters.</Form.Text>
				</Form.Group>

				<Form.Group>
					<Form.Label>Gender</Form.Label>
					<Form.Control required as="select" custom name="gender">
						<option value="" selected disabled>Please choose one</option>
						<option value="male">Male</option>
						<option value="female">Female</option>
						<option value="other">Other</option>
					</Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<div class="button-align">
					<Button variant="primary" class="btn-toolbar" disabled>Back</Button>{'      '}
					<Button variant="primary" type="submit" class="btn-toolbar">Next</Button>
				</div>
			</Form>
		</>
	);
}

const Step2 = (props) => {

	const [curState, setState] = useState({city: "", state: "", language: ""});

	const submitHandler = (event) => {
		event.preventDefault();
		setState({
			city: event.target[0].value,
			state: event.target[1].value,
			language: event.target[2].value
		});
		props.nextStep();
	}

	return (
		<>
			<h2>You are in step 2</h2>
			<Form onSubmit={submitHandler} id="step2form" name="step2form">
			<ProgressBar now={33}/>
				<Form.Group>
					<Form.Label>City</Form.Label>
					<Form.Control required type="text" placeholder="Enter city" name="city"></Form.Control>
					<Form.Text muted>Enter the city or town you are living in.</Form.Text>
				</Form.Group>

				<Form.Group>
					<Form.Label>State</Form.Label>
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

				<Form.Group>
					<Form.Label>Languages</Form.Label>
					<Form.Control multiple required as="select" custom name="lang">
						<option value="" selected disabled>Please choose at least one</option>
						<option value="en">English</option>
						<option value="es">Spanish</option>
						<option value="fr">French</option>
						<option value="ka">Kannada</option>
					</Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<div class="button-align">
					<Button variant="primary" class="btn-toolbar" onClick={() => props.previousStep()}>Back</Button>{' '}
					<Button variant="primary" type="submit" class="btn-toolbar">Next</Button>
				</div>
			</Form>
		</>
	);
}

const Step3 = (props) => {

	const [curState, setState] = useState({illnesses: "", isAnonymous: ""});

	const submitHandler = (event) => {
		event.preventDefault();
		setState({
			illnesses: event.target[0].value,
			isAnonymous: event.target[1].value,
		});
	}

	return (
		<>
			<h2>You are in step 3</h2>
			<Form onSubmit={submitHandler} id="step3form" name="step3form">
			<ProgressBar now={66}/>
				<Form.Group>
					<Form.Label>Illnesses</Form.Label>
					<Form.Control multiple required as="select" custom name="illness">
						<option value="" selected disabled>Please choose one</option>
						<option value="leukemia">Leukemia</option>
						<option value="cancer">Cancer</option>
						<option value="other">Nevada</option>
					</Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>

				<Form.Group>
					<Form.Label>Stay anonymous</Form.Label>
					<Form.Check type="checkbox" name="anonymous" label="Stay anonymous?"/>
					<Form.Text muted></Form.Text>
				</Form.Group>
				<div class="button-align">
					<Button variant="primary" class="btn-toolbar" onClick={() => props.previousStep()}>Back</Button>{' '}
					<Button variant="primary" type="submit" class="btn-toolbar">Next</Button>
				</div>
			</Form>
		</>
	);
}

function App() {
  return (
	<StepWizard initialStep={1}>
		<Step1/>
		<Step2/>
		<Step3/>
	</StepWizard>
  );
}

export default App;
