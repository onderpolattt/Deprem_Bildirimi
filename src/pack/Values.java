package pack;

import java.text.DateFormat;

public class Values {

	public String country;
	public String mag;
	public String Date;
	public String magType;
	public String coordinates;

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getMagType() {
		return magType;
	}

	public void setMagType(String magType) {
		this.magType = magType;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMag() {
		return mag;
	}

	public void setMag(String mag) {
		this.mag = mag;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	@Override
	public String toString() {

		return "Place = " + country + ",  Coordinates = " + coordinates + " ,  Mag = " + mag + " " + magType
				+ ", Date = " + Date;
	}

}
