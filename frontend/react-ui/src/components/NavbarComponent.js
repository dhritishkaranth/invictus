import {Button, Container, Navbar} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';

const NavbarComponent = (props) => {
	return (
		<Container>
			<Navbar bg="light" variant="light" >
			<Navbar.Brand>
				Invictus
			</Navbar.Brand>
			{ props.userCredentials && 
			<div>
				<Navbar.Text>
					Signed in as: {props.userCredentials["creds"]["username"]}
				</Navbar.Text>
				<Button variant="link">Sign out</Button>
			</div>
			}
			</Navbar>
		</Container>
	);
}

export default NavbarComponent;