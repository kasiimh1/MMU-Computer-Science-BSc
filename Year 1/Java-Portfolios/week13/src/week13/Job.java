package week13;

public class Job
{
  float hourlyRate;
  Room room;

  Job(float hourlyRate, Room room)
  {
	  this.hourlyRate = hourlyRate;
	  this.room = room;
  }

  float CalculateCost()
  {
	  return room.CalculateCost(hourlyRate); //CALLS THE DATA FORM THE OTHER FILE
  }

}
