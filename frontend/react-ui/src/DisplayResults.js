import {React, useState, useEffect, useRef} from "react";
import axios from "axios";
import getLatLongFromAddress from "./getLatLongFromAddress.js";
import mapStyles from "./CustomMapStyles";
import { GoogleMap, InfoWindow, LoadScript, Marker } from '@react-google-maps/api';
import 'bootstrap/dist/css/bootstrap.min.css';

const MapComponent = (props) => {
	const mapRef = useRef(null);
	//let [markerCount, setMarkerCount] = useState(0);
	let [markerData, setMarkerData] = useState({});
	let [selectedMarker, setSelectedMarker] = useState(null);
	let [mapCenter, setCenter] = useState(props.center);

	const containerStyle = {
		width: "80%",
		height: "600px"
	  };

	useEffect(() => {
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
	

	const onMarkerLoad = (event) => {
		console.log("Marker placed: ", event);
		//console.log("state in marker callback: ", data.markers);
	};

	const onInfoWindowLoad = (event) => {
		console.log("IW loaded ", event);
	};

	const markerIcons = {
		"group": "https://i.imgur.com/8ECigCq.png",
		"home": "https://i.imgur.com/pRFkmkl.png",
		"person": "https://i.imgur.com/wZQqNcS.png"
	};

	const mapOnLoadHandler = (map) => {
		//console.log("map on load handler", map);
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
		  zoom={10}
		  onLoad={mapOnLoadHandler}
		  onIdle={centerChangeHandler}
		  clickableIcons={false}
		  options={{styles: mapStyles}}
		>
			{Object.entries(markerData).map(
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
					position={markerData[selectedMarker]["latLon"]}
				>
					<InfoWindowContent content={markerData[selectedMarker]["InfoContent"]}/>
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
		</>
	);
}

const Legend = () => {
	return (
		<>
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
		</>
	);
}

function DisplayResults() {

	const data = [
		{location: "Irvine, CA", type: "group", content: {title: "Kumar's home", body: "aaa"}},
		{location: "Brahmavar, Udupi, Karnataka", type: "group", content: {title: "My hometown", body: "aaa"}},
		{location: "Kundapura, Karnataka", type: "group", content: {title: "Shetty Lunch home", body: "aaa"}},
		{location: "Manipal, Karnataka", type: "person", content: {title: "Someone's home", body: "aaa"}},
		{location: "Udupi, Karnataka", type: "person", content: {title: "Someone's home", body: "aaa"}},
		{location: "Baikady, Karnataka", type: "home", content: {title: "My home", body: "aaa"}}
	];

	return (
		<>
		<h1>Display Results</h1>
		<hr/>
		<div style={{width: "80%", height: "100%", position: "relative"}}>
			<MapComponent locs={data} center={{lat: 13.5, lng: 75.0}}/>
		</div>
		<Legend/>
		</>
	);
}

export default DisplayResults;
