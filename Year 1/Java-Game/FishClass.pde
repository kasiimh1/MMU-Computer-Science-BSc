Fish [][] spawnFish;
class Fish {

  PImage fish, fish1;
  int cols;
  int rows;   
  int x;  
  int y;
  int speedX = 0;
  int yLoc;
  int count = 0;
  int fishCounter = 0;
  boolean fishCollision = false; // sets collision to false for first launch


  Fish(int x, int y, int speedX, int yLoc, int cols, int rows)
  {
    fish = loadImage("images/fish0.png");
    fish.resize(60, 60); //resize the fish image    
    fish1 = loadImage("images/fish1.png");
    fish1.resize(60, 60); //resize the fish image    
    this.x = x;
    this.y = y;
    this.yLoc = y;
    this.speedX = speedX;
    this.cols = cols;
    this.rows = rows;
  }

  void setup() {
    spawnFish = new Fish[cols][rows];
    for (int i = 0; i < cols; i++)
    {
      for (int j = 0; j < rows; j++)
      { 
        spawnFish[i][j] = new Fish(x, y, speedX, yLoc, cols, rows);
        y += 70;
      }
      x += 120;
      y = yLoc;
    }
  }

  void display()
  {
    //animate the fish by showing a sequence of images 
    //which image is set to be display at x, y
    if (count<10)
      image(fish, x, y);
    else if (count<20)
      image(fish1, x, y);
    else if (count<30)
      image(fish, x, y);
    else if (count<40)
      image(fish1, x, y);    
    else count = -1;
    count=count+1;
  }

  void move()
  {
    //moves the fish across the screen
    x = x + speedX;    
    if ( x > width)
    {
      x = x - x ; // this allows them to reset at x swimming from outside the bounds
      y = y - 20; // y - minused by 20 so it can travel up the screen
    }
    // reset if fish make it to the top
    if ( y < 430)
    {
      //game over screen
      Counter = 2;
    }
    // if all the fish are caught
    if(FishCounter == 0)
    {
      //game over screen
      Counter = 2;
    }
  }


/* 
only draw this fish in the array if they're set to null
and above the boat height
*/
  void draw()
  {
    for (int i = 0; i < cols; i++)
    {
      for (int j = 0; j < rows; j++)
      { 
        if (spawnFish[i][j] != null)
        {
          spawnFish[i][j].move();

          if (spawnFish[i][j].y > 435)
          { 
            spawnFish[i][j].display();
          }
        }
      }
    }
  } 

  boolean fishCollision(Fish fish)
  {     
    if (fish.y > 435)//check is the fish are above the boat
    {
      for (int y = rod1.y; y < rod1.y+20; y++) //check the distance between y to y+20
      {
        float distance = dist(boat1.x, y, fish.x, fish.y);
        if (distance < 40)
        {
          fish.y = rod1.y;        
          fish.x = boat1.x;            
          if (fish.y == 435)
          {
            score += 10; //grant player 10 points
            FishCounter -= 1; //remove a fish from the on screen counter
            celebration = 1;  //set the game to render the fireworks   
            
            if ( FishCounter < 1)
            {
              Counter = 2; // end game
            }
            return true; //fish has collided with the fishing rod
          }
        }
      }
    }
    return false;
  }
}