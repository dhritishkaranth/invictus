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
            <img src="http://placekitten.com/g/400/200" />
          </div>
          <div>
            <img src="http://placekitten.com/g/400/200" />
          </div>
          <div>
            <img src="http://placekitten.com/g/400/200" />
          </div>
			</Slider>
		</div>
	);
};

export default CarouselComponent;