import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

const CarouselComponent = (props) => {

	let settings = {
		dots: true,
	};

	return (
		<div className="Carousel-Container">
			<Slider {...settings}>
			<div>
            <h1>Text</h1>
          </div>
          <div>
            <img src="https://i.imgur.com/FycmgXY.png" />
          </div>
          <div>
            <img src="https://i.imgur.com/z17WbHI.jpg" />
          </div>
          <div>
            <img src="https://i.imgur.com/8oywn1C.png" />
          </div>
			</Slider>
		</div>
	);
};

export default CarouselComponent;