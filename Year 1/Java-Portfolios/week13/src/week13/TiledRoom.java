package week13;

public class TiledRoom extends Room{
	TiledRoom(int Area, float unitPrice, int Time) {
		super(Area, unitPrice, Time);
	}

	float Room(int Area, float unitPrice, int Time)
	{
	this.Area = Area;
	this.unitPrice = unitPrice;
	this.Time = Time;

	float roomMeasurements = (Area) + (unitPrice) + (Time);
	return roomMeasurements;
	}
	float CalculateCost(float hourlyRate)
	{
		float cost = (Time * hourlyRate) + (hourlyRate / 2) + (unitPrice * Area) + (Area * 1);
		return cost;
	}
}
