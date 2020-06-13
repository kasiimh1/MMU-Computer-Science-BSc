package week13;

public class Room
{
	int Area;
	float unitPrice;
	int Time;

	Room(int Area, float unitPrice, int Time)
	{
		this.Area = Area;
		this.unitPrice = unitPrice;
		this.Time = Time;
	}

	float CalculateCost(float hourlyRate)
	{
		float cost = (Time * hourlyRate) + (unitPrice * Area);
		return cost;
	}

}

