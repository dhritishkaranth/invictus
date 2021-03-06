import {React, useState, useEffect, useRef} from "react";
import axios from "axios";
import {Button, Form, Navbar, Container} from "react-bootstrap";
import getLatLongFromAddress from "./getLatLongFromAddress.js";
import mapStyles from "../resources/CustomMapStyles";
import NavbarComponent from "./NavbarComponent";
import BootstrapSwitchButton from 'bootstrap-switch-button-react';
import { GoogleMap, InfoWindow, LoadScript, Marker } from '@react-google-maps/api';
import 'bootstrap/dist/css/bootstrap.min.css';

const MapComponent = (props) => {
	const mapRef = useRef(null);
	//let [markerCount, setMarkerCount] = useState(0);
	//let [markerData, setMarkerData] = useState({});
	let [selectedMarker, setSelectedMarker] = useState(null);
	let [mapCenter, setCenter] = useState(props.center);

	const containerStyle = {
		width: "100%",
		height: "800px"
	  };

	  /*

	useEffect(() => {
		console.log("useEffect called with props = ", props.locs);
		setMarkerData({});
		for (let item of props.locs) {
			let {location, type, content} = {...item};

			if (location in markerData)
				continue;
			
			let promise = getLatLongFromAddress(location);
			promise.then(res => {
				let latLonData = res.data.results[0].geometry.location;
				setMarkerData(prevState => {
					let newState = {...prevState};
					newState[location] = {type: type, latLon: latLonData, InfoContent: content};
					return newState;
				});
				//setMarkerCount(prevCount => prevCount + 1);
			});
		}	
	}, [props.locs]);

	*/
	

	const onMarkerLoad = (event) => {
		//console.log("Marker placed: ", event);
		//console.log("state in marker callback: ", data.markers);
	};

	const onInfoWindowLoad = (event) => {
		//console.log("IW loaded ", event);
	};

	const markerIcons = {
		"group": "https://i.imgur.com/8ECigCq.png",
		"home": "https://i.imgur.com/pRFkmkl.png",
		"person": "https://i.imgur.com/wZQqNcS.png"
	};

	const mapOnLoadHandler = (map) => {
		console.log("map on load handler", map);
		//console.log(data.markers);
		//setMarkers(markers);
		mapRef.current = map;
	};

	const centerChangeHandler = () => {
		if (!mapRef.current)
			return;
		setCenter(mapRef.current.getCenter().toJSON());
	};

	return (
		<LoadScript googleMapsApiKey={`${process.env.REACT_APP_GOOGLE_MAPS_API_KEY}`}>
		<GoogleMap
		  mapContainerStyle={containerStyle}
		  center={mapCenter}
		  zoom={4}
		  onLoad={mapOnLoadHandler}
		  onIdle={centerChangeHandler}
		  clickableIcons={false}
		  options={{styles: mapStyles}}
		>
			{Object.entries(props.locs).map(
				([key, value]) => {
						//console.log(key, value);
						return <Marker 
							key={key} 
							onLoad={onMarkerLoad} 
							position={value["latLon"]} 
							onClick={() => {setSelectedMarker(key)}}
							title={key} 
							icon={markerIcons[value["type"]]}
						/>
					}
				)
			}
			{
				selectedMarker &&
				<InfoWindow 
					onLoad={onInfoWindowLoad}
					onCloseClick={() => {setSelectedMarker(null)}}
					position={props.locs[selectedMarker]["latLon"]}
				>
					<InfoWindowContent content={props.locs[selectedMarker]["InfoContent"]}/>
				</InfoWindow>
			}
		</GoogleMap>
	  </LoadScript>
  	);
}

const InfoWindowContent = (props) => {

	return (
		<>
			<h3>{`${props.content.title}`}</h3>
			<div>
				{`${props.content.body}`}
			</div>
			<div>
				<a href={props.content.link} target="_blank">Social media profile</a>
			</div>
		</>
	);
}

const Legend = () => {
	return (
		<div style={{width: "300px", height: "200px", paddingTop: "50px"}}>
		<div className="card">
			<div className="card-body">
			<h5 className="card-title">Legend</h5>
			<div style={{display: "flex", flexDirection: "row", justifyContent: "space-between"}}>
			<span style={{width: "100px"}}>
				<img src="https://i.imgur.com/8ECigCq.png"/>
				<span style={{textAlign: "center"}}>Groups</span>
			</span>
			<span style={{width: "100px"}}>
				<img src="https://i.imgur.com/pRFkmkl.png"/>
				<span style={{textAlign: "center"}}>You</span>
			</span>
			<span style={{width: "100px"}}>
				<img src="https://i.imgur.com/wZQqNcS.png"/>
				<span style={{textAlign: "center"}}>Person</span>
			</span>
			</div>
			</div>
		</div>
		</div>
	);
}

const SearchComponent = (props) => {
	let [userToggleState, setUserToggleState] = useState(true);
	let [groupToggleState, setGroupToggleState] = useState(true);

	let userInfo = props.userCredentials;

	const submitHandler = (event) => {
		event.preventDefault();
		console.log("Search executed", event);

		let selectedIllness = event.target[0].value;
		let selectedLanguage = event.target[1].value;

		let data = [];

		console.log(selectedIllness, selectedLanguage, userToggleState, groupToggleState);

		props.onSearchHandler({});

		setTimeout(() => {
			props.onSearchHandler((prevData) => {
				let newData = {...prevData};
				newData[userInfo.location.loc] = {type: "home", latLon: {lat: userInfo.location.lat, lng: userInfo.location.lng}, InfoContent: {title: "Your location", body: ""}};
				//console.log("Udata: ", newData);
				//console.log("creds", props.userCredentials);
				//console.log("item: ", {type: "home", latLon: {lat: userInfo.location.lat, lng: userInfo.location.lng}, InfoContent: {title: "Your location", body: ""}});
				return newData;
		})}, 750);


		/*

		let promise = getLatLongFromAddress(userInfo.location);
		promise.then(res => {
			//console.log("Geocode res: ", res);
			setTimeout(() => {
			props.onSearchHandler((prevData) => {
				let newData = {...prevData};
				newData["location"] = {type: "home", latLon: res.data.results[0].geometry.location, InfoContent: {title: "Your location", body: ""}};
				return newData;
			});
			}, 2000);
		});
		*/

		

		if (groupToggleState) {
			let url = "";
			if (selectedIllness === "" && selectedLanguage === "")
				url = `${process.env.REACT_APP_PROTOCOL}://${process.env.REACT_APP_DOMAIN}/invictus/v1/groups/illness/language/`;
			else if (selectedIllness === "")
				url = `${process.env.REACT_APP_PROTOCOL}://${process.env.REACT_APP_DOMAIN}/invictus/v1/groups/illness/language/${selectedLanguage}`;
			else if (selectedLanguage === "")
				url = `${process.env.REACT_APP_PROTOCOL}://${process.env.REACT_APP_DOMAIN}/invictus/v1/groups/illness/${selectedIllness}/language/`;
			else
				url = `${process.env.REACT_APP_PROTOCOL}://${process.env.REACT_APP_DOMAIN}/invictus/v1/groups/illness/${selectedIllness}/language/${selectedLanguage}`;

			console.log(url);

			let promise = axios.get(url, {auth: userInfo.creds, validateStatus: false});
			promise.then(res => {
					console.log("Search handler for groups received: ", res.data);
					let promArr = [];
					let groupData = {};

					for (let item of res.data) {
						let infoContent =  {
							title: item.groupName,
							link: item.resources[0],
							body: ""
						};
						groupData[item.location] = {type: "group", locStr: item.location, InfoContent: infoContent, latLon: {lat: item.lat, lng: item.lng}};
					}

					setTimeout(() => {
						props.onSearchHandler((prevData) => {
							return {...prevData, ...groupData};
						});
					}, 250);

					

					/*
					for (let item of res.data) {
						let locPromise = getLatLongFromAddress(item.location);
						promArr.push(locPromise);
						recData.push({type: "group", latLon: "", locStr: item.location, InfoContent: {title: item.groupName, body: item.resources[0]}});
					}
					Promise.all(promArr).then(values => {
						let newData = {};
						for (let i = 0; i < values.length; ++i) {
							//console.log(values[i]);
							newData[recData[i].locStr] = {...recData[i], latLon: values[i].data.results[0].geometry.location};
						}
						console.log(newData);
						props.onSearchHandler((prevData) => {
							return {...prevData, ...newData};
						});
					});
					*/
				}
			);
		}

		if (userToggleState) {

			let url = "";
			if (selectedIllness === "" && selectedLanguage === "")
				url = `${process.env.REACT_APP_PROTOCOL}://${process.env.REACT_APP_DOMAIN}/invictus/v1/users/illness/seeker/language/`;
			else if (selectedIllness === "")
				url = `${process.env.REACT_APP_PROTOCOL}://${process.env.REACT_APP_DOMAIN}/invictus/v1/users/illness/seeker/language/${selectedLanguage}`;
			else if (selectedLanguage === "")
				url = `${process.env.REACT_APP_PROTOCOL}://${process.env.REACT_APP_DOMAIN}/invictus/v1/users/illness/${selectedIllness}/seeker/language/`;
			else
				url = `${process.env.REACT_APP_PROTOCOL}://${process.env.REACT_APP_DOMAIN}/invictus/v1/users/illness/${selectedIllness}/seeker/language/${selectedLanguage}`;

			let promise = axios.get(url, {auth: userInfo.creds, validateStatus: false});
			promise.then(res => {
					console.log("Search handler for users received: ", res.data);
					
					let promArr = [];
					let userData = {};

					for (let item of res.data) {
						let infoContent =  {
							title: item.username,
							link: item.resources[0],
							body: `Gender: ${item.gender}, Type of user: ${item.typeOfSeeker}`
						};
						userData[item.location] = {type: "person", locStr: item.location, InfoContent: infoContent, latLon: {lat: item.lat, lng: item.lng}};
					}

					setTimeout(() => {
						props.onSearchHandler((prevData) => {
							return {...prevData, ...userData};
						});
					}, 500);

					/*
					for (let item of res.data) {
						let locPromise = getLatLongFromAddress(item.location);
						promArr.push(locPromise);
						recData.push({type: "person", latLon: "", locStr: item.location, InfoContent: {title: item.username, body: item.resources[0]}});
					}
					Promise.all(promArr).then(values => {
						let newData = {};
						for (let i = 0; i < values.length; ++i) {
							if (values[i].data.results[0].geometry.location !== null)
								newData[recData[i].locStr] = {...recData[i], latLon: values[i].data.results[0].geometry.location};
							else {
								console.log("Null value detected. Continuing...");
							}
						}
						console.log(newData);
						props.onSearchHandler((prevData) => {
							return {...prevData, ...newData};
						});
					});
					*/
				}
			);
		}
	};

	const userToggleChangeHandler = (toggleState) => {
		setUserToggleState(toggleState);
	};

	const groupToggleChangeHandler = (toggleState) => {
		setGroupToggleState(toggleState);
	};

	return (
		<div className="Seach-Row-Horiz search-fix-width">
			<Form onSubmit={submitHandler} id="searchParamsForm" name="searchParamsForm" className="Seach-Row-Horiz search-fix-width">
				<Form.Group className="Seach-Row-Horiz">
					<Form.Label style={{paddingRight: "10px"}}>Illness</Form.Label>
					<Form.Control as="select" custom name="illness">
						<option value="" selected>Any</option>
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

				<Form.Group className="Seach-Row-Horiz">
					<Form.Label style={{paddingRight: "10px"}}>Language</Form.Label>
					<Form.Control as="select" custom name="language">
						<option value="" selected>Any</option>
						<option value="English">English</option>
						<option value="Spanish">Spanish / Espa??ol</option>
						<option value="Kannada">Kannada / ???????????????</option>
						<option value="Hindi">Hindi / ??????????????????</option>
						<option value="Chinese">Chinese / ??????</option>
						<option value="French">French / Fran??ais</option>
					</Form.Control>
					<Form.Text muted></Form.Text>
				</Form.Group>
				
				<Form.Group>
					<Form.Label style={{paddingRight: "10px"}}>Include groups</Form.Label>
					<BootstrapSwitchButton checked={groupToggleState} onlabel={"Yes"} offlabel={"No"} onChange={groupToggleChangeHandler}/>
					<Form.Text muted></Form.Text>
				</Form.Group>

				<Form.Group>
					<Form.Label style={{paddingRight: "10px"}}>Include users</Form.Label>
					<BootstrapSwitchButton checked={userToggleState} onlabel={"Yes"} offlabel={"No"} onChange={userToggleChangeHandler}/>
					<Form.Text muted></Form.Text>
				</Form.Group>

				<Button variant="primary" type="submit" className="btn-toolbar">Search</Button>
			</Form>
		</div>
	);
}

function DisplayResults(props) {

	let groups = [{location: props.userCredentials["location"], type: "home", content: {title: "Your location", body: ""}}];
	
	let [markerData, setMarkerData] = useState({});


	return (
		<div className="verticalColumn">
			<NavbarComponent userCredentials={props.userCredentials}/>
			<h3 style={{paddingTop: "20px"}}>Search for groups and persons</h3>
			<hr/>
			<SearchComponent onSearchHandler={setMarkerData} userCredentials={props.userCredentials}/>
			<hr/>
			<div style={{width: "100%", height: "100%"}}>
				<MapComponent locs={markerData} center={{lat: 36.45, lng: -95.0}}/>
			</div>
			<Legend/>
		</div>
	);
}

export default DisplayResults;
