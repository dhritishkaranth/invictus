import {React, useState, useEffect} from "react";
import axios from "axios";
import { GoogleMap, LoadScript, Marker } from '@react-google-maps/api';
import 'bootstrap/dist/css/bootstrap.min.css';

const MapComponent = (props) => {
	
	let [data, setMarkers] = useState({count: 0, markers: []});

	const containerStyle = {
		width: "80%",
		height: "600px"
	  };
	  
	  const center = {
		lat: 13.5,
		lng: 75.0
	  };

	useEffect(() => {

		let promises = [];
		let newState = [];
		
		for (let item of props.locs) {
			//console.log(locationString);
			let arrayItem = {...item};
			arrayItem["promise"] = props.getGeocode(arrayItem["location"]);

			promises.push(arrayItem["promise"]);
			newState.push(arrayItem);
		}
		console.log("state after reading data: ", newState);
		console.log("promise list: ", promises);
		//setMarkers(newState);


		Promise.all(promises).then(results => {
			//let newState = {...markers};
			console.log("state in callback ", newState);
			for (let item of newState) {
				let promise = item["promise"];
				console.log("promise is:", promise);
				promise.then(res => {
					item["latLon"] = res.data.results[0].geometry.location;

				});
			}
			setMarkers({count: newState.length, markers: [...newState]});
			console.log("state after writing:", newState);
		});
		
		setTimeout(() => {
			setMarkers({count: newState.length + 1, markers: [...newState, {location: "London, UK", type: "group"}]})
		}, 1000);
		
	}, [props.locs]);
	

	const onMarkerLoad = (event) => {
		console.log("Marker placed: ", event);
		console.log("state in marker callback: ", data.markers);
	}

	const markerIcons = {
		"group": "https://i.imgur.com/8ECigCq.png",
		"home": "https://i.imgur.com/pRFkmkl.png",
		"person": "https://i.imgur.com/wZQqNcS.png"
	};

	const mapOnLoadHandler = (map) => {
		console.log("map on load handler", map);
		console.log(data.markers);
		//setMarkers(markers);	
	};

	return (
		<LoadScript
		googleMapsApiKey="AIzaSyD6dGy10jxAX-o3ru_hPaUNcn5_gFRvHH0"
	  >
		<GoogleMap
		  mapContainerStyle={containerStyle}
		  center={center}
		  zoom={10}
		  onLoad={mapOnLoadHandler}
		>
			{/*Object.entries(markers).map(([key, value]) => value["latLon"] !== null ? <Marker key={key} onLoad={onMarkerLoad} position={value["latLon"]} title={key}/> : <></>)*/}
			{data["markers"].map(item => item["latLon"] !== null ? <Marker key={item["location"]} onLoad={onMarkerLoad} icon={markerIcons[item["type"]]} position={item["latLon"]}/> : <></>)}
		
		</GoogleMap>
	  </LoadScript>
  	);
}

function DisplayResults() {

	const getLatLongFromAddress = async (address) => {
		//console.log(address);
		return await (axios.get("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=AIzaSyD6dGy10jxAX-o3ru_hPaUNcn5_gFRvHH0"));
	}

	//const locs = ["Irvine, CA", "Brahmavar, Udupi", "Kundapura", "Manipal", "Udupi"];
	const data = [
		{location: "Irvine, CA", type: "group"},
		{location: "Brahmavar, Udupi, Karnataka", type: "group"},
		{location: "Kundapura, Karnataka", type: "group"},
		{location: "Manipal, Karnataka", type: "person"},
		{location: "Udupi, Karnataka", type: "person"},
		{location: "Baikady, Karnataka", type: "home"}
	];
	//let promise = (getLatLongFromAddress(locs[0]));
	//promise.then(res => console.log(promise));

	return (
		<>
		<h1>Display Results</h1>
		<hr/>
		<div style={{width: "80%", height: "100%", position: "relative"}}>
			<MapComponent locs={data} getGeocode={getLatLongFromAddress}/>
		</div>
		</>
	);
}

export default DisplayResults;
