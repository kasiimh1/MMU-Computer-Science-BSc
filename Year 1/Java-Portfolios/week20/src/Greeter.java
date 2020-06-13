import javax.swing.*;
import java.awt.event.*;

class GreeterPanel extends JPanel implements ActionListener
{
	
	
    JLabel outLabel;
    JTextField inField,inField1,inField2;
    JLabel inFieldLabel,inFieldLabel2,inFieldLabel3;
    JButton checkButton;

    GreeterPanel()
    {
        outLabel = new JLabel("");
        inFieldLabel = new JLabel("Day");
        inFieldLabel2 = new JLabel("Month");
        inFieldLabel3 = new JLabel("Year");

        inField = new JTextField(4);        
        inField1 = new JTextField(4);        
        inField2 = new JTextField(6);
        
        checkButton = new JButton("Check!");
        checkButton.addActionListener(this);

        add(inFieldLabel);
        add(inField);


        add(inFieldLabel2);
        add(inField1);

        add(inFieldLabel3);
        add(inField2);

        add(checkButton);
        add(outLabel);
    }


    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == checkButton)
        {        	
        	
            String Sday = inField.getText();
            int day = Integer.parseInt(Sday);
            
            String Smonth = inField1.getText();
            int month = Integer.parseInt(Smonth);
            
            String Syear = inField2.getText();
            int year = Integer.parseInt(Syear);
            
            int dayOfWeek = Doomsday.GetDayOfWeek(day,month,year);                                  
        
            System.out.println(dayOfWeek);// outputs '1' -29/2/2016 was a Monday.
                        
   		
            int weekday = dayOfWeek;
            
            String weekdayString;
            
            switch(weekday)
    		{		
    		case -1: weekday = -1;
    		{
    			weekdayString = "invalid!";  
                outLabel.setText("The following date " + day + "/" + month + "/" + year + " is " + weekdayString);
    		}
    		break;
    		case 0: weekday = 0;
    		{
    			weekdayString = "Sunday";    
                outLabel.setText(day + "/" + month + "/" + year + " is a " + weekdayString);
    		}
    		break;
    		case 1: weekday = 1;
    		{
    			weekdayString = "Monday";
                outLabel.setText(day + "/" + month + "/" + year + " is a " + weekdayString);
    		}
    		break;
    		case 2: weekday = 2;
    		{
    			weekdayString = "Tuesday";    	
                outLabel.setText(day + "/" + month + "/" + year + " is a " + weekdayString);
    		}
    		break;
    		case 3: weekday = 3;
    		{
    			weekdayString = "Wednesday";   
                outLabel.setText(day + "/" + month + "/" + year + " is a " + weekdayString);
    		}
    		break;
    		case 4: weekday = 4;
    		{
    			weekdayString = "Thursday";    		
                outLabel.setText(day + "/" + month + "/" + year + " is a " + weekdayString);
    		}
    		break;
    		case 5: weekday = 5;
    		{
    			weekdayString = "Friday";  
                outLabel.setText(day + "/" + month + "/" + year + " is a " + weekdayString);
    		}
    		break;
    		case 6: weekday = 6;
    		{
    			weekdayString = "Saturday";  
                outLabel.setText(day + "/" + month + "/" + year + " is a " + weekdayString);
    		}
    		break;   		    		            
    		}
        }
    }
}

public class Greeter
{
    public static void main( String args[] )
    {
        // create an instance of JFrame - a swing window
        JFrame f = new JFrame("Week 20 Portfolio by Kasim Hussain | 15078165");
        f.setSize( 450, 100);
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        // add an instance of MyPanel
        f.add( new GreeterPanel());
        f.setVisible( true );
    }
}
