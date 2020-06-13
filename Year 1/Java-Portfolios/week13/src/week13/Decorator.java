package week13;

import java.util.Scanner;

public class Decorator
{
	public static void main(String[] args)
	{
		Job job1,job2;
		Room room1;
		TiledRoom room2;

		int roomArea;
		float decorateRate;
		float unitCost;
		int decorateTime;

		//String readIfTiled;

		Scanner in = new Scanner(System.in);

		System.out.print("Please enter hourly rate ");
		decorateRate = in.nextFloat();

		System.out.print("Please enter room's area ");
		roomArea = in.nextInt();

		System.out.print("Please enter room's unit cost ");
		unitCost = in.nextFloat();

		System.out.print("Please enter room's decorating time ");
		decorateTime = in.nextInt();

		//Tiled Room
		System.out.print("is this a Tiled Room? Enter yes/y | no/n ");

		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.next();

		if(userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
		    // y or yes

			//fix this to inherit
			room2 = new TiledRoom(decorateTime, unitCost, decorateTime);
			//^^^
			job2 = new Job(decorateRate, room2);

			System.out.println("The job will cost £" + job2.CalculateCost());

		} else {
		    // other character

			//default calc
			room1 = new Room(roomArea, unitCost, decorateTime);
			job1 = new Job(decorateRate, room1);

			System.out.println("The job will cost £" + job1.CalculateCost());

		}

		/*
		room1 = new Room(roomArea, unitCost, decorateTime);
		job1 = new Job(decorateRate, room1);
		*/
//		System.out.println("The job will cost £" + job1.CalculateCost());


	}
}
