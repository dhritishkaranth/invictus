import axios from "axios";

const getLatLongFromAddress = (address) => {
	//console.log(address);
	if (!process.env.REACT_APP_GOOGLE_MAPS_API_KEY) {
		console.log("Google Maps API key not defined!");
		return;
	}
	console.log("Geocoding request received.");
	return (axios.get("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + `&key=${process.env.REACT_APP_GOOGLE_MAPS_API_KEY}`));
}

export default getLatLongFromAddress;