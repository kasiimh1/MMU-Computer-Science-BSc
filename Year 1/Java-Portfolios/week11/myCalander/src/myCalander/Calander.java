package myCalander;

/* Created by Kasim Hussain - 15078165 */

//import the scanner 
import java.util.Scanner;

public class Calander {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	Scanner in = new Scanner(System.in);
	String monthString;

	System.out.println("Enter a month in format e.g. 1 / 12 ");
	int month = in.nextInt();
	
	if (month < 1 | month > 12) //validation if the number for the month is real
	{
		monthString = "Not a valid month, please run the program and try again!";
		System.out.println(monthString);
		System.exit(0); //exit the program
	}
	
	System.out.println("Enter a day to see if it's valid in the format: 9 ");
	int day = in.nextInt();
	
		switch(month)
		{		
		case 1: month = 1;
		{
			monthString = "January";
			System.out.println(monthString);
			if (day > 0 && day < 32)  {
			System.out.println("The day is valid!");
			}
			else 
			{
				System.out.println("The day isn't valid!");
			}
			}
		break;
		
		case 2: month = 2;
		{
			monthString = "Feburary";
			System.out.println(monthString);
			if (day > 0 && day < 29)  {
			System.out.println("The day is valid!");
			}
			else 
			{
				System.out.println("The day isn't valid!");
			}
			}
		break;
		case 3: month = 3;
		{
			monthString = "Feburary";
			System.out.println(monthString);
			if (day > 0 && day < 32)  {
			System.out.println("The day is valid!");
			}
			else 
			{
				System.out.println("The day isn't valid!");
			}
			}
		break;
		case 4: month = 4;
		{
			monthString = "April";
			System.out.println(monthString);
			if (day > 0 && day < 31)  {
			System.out.println("The day is valid!");
			}
			else 
			{
				System.out.println("The day isn't valid!");
			}
			}
		break;
		case 5: month = 5;
		{
			monthString = "May";
			System.out.println(monthString);
			if (day > 0 && day < 32)  {
			System.out.println("The day is valid!");
			}
			else 
			{
				System.out.println("The day isn't valid!");
			}
			}
		break;
		case 6: month = 6;
		{
			monthString = "June";
			System.out.println(monthString);
			if (day > 0 && day < 31)  {
			System.out.println("The day is valid!");
			}
			else 
			{
				System.out.println("The day isn't valid!");
			}
			}
		break;
		case 7: month = 7;
		{
			monthString = "July";
			System.out.println(monthString);
			if (day > 0 && day < 32)  {
			System.out.println("The day is valid!");
			}
			else 
			{
				System.out.println("The day isn't valid!");
			}
			}
		break;
		case 8: month = 8;
		{
			monthString = "August";
			System.out.println(monthString);
			if (day > 0 && day < 32)  {
			System.out.println("The day is valid!");
			}
			else 
			{
				System.out.println("The day isn't valid!");
			}
			}
		break;
		case 9: month = 9;
		{
			monthString = "September";
			System.out.println(monthString);
			if (day > 0 && day < 31)  {
			System.out.println("The day is valid!");
			}
			else 
			{
				System.out.println("The day isn't valid!");
			}
			}
		break;
		case 10: month = 10;
		{
			monthString = "October";
			System.out.println(monthString);
			if (day > 0 && day < 31)  {
			System.out.println("The day is valid!");
			}
			else 
			{
				System.out.println("The day isn't valid!");
			}
			}
		break;
		case 11: month = 11 ;
		{
			monthString = "Novemeber";
			System.out.println(monthString);
			if (day > 0 && day < 31)  {
			System.out.println("The day is valid!");
			}
			else 
			{
				System.out.println("The day isn't valid!");
			}
			}
		break;
		case 12: month = 12;
		{
			monthString = "December";
			System.out.println(monthString);
			if (day > 0 && day < 32)  {
			System.out.println("The day is valid!");
			}
			else 
			{
				System.out.println("The day isn't valid!");
			}
			}
		break;		
		}
	}
}
