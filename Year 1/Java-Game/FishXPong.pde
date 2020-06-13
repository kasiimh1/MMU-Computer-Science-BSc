/* 

- The game has 3 levels (Level 1 = 1 shark, Level 2 = 2 sharks, Level 3 = 3 sharks)





- Start the game by pressing 1, 2 or 3 then pressing Enter
  NO SHARKS WILL SHOW IF NO LEVEL IS SELECTED
  
  
  

- Game level can be changed while in game

- Press letter R OR r to restart the game

*/

Boat boat1;
Shark shark1, shark2, shark3;
Projectiles bubble0, bubble1;
Fish fishy0;
FishingRod rod1;
PlayerLives playerLives0, playerLives1, playerLives2;
Fireworks celebration1;

//Datatype for storing images, declaring the names here for later use
PImage bg, bg1, bg2, gameOver, backdrop;

//counter to tell the game what background image is / should be applied. Also when game related items should be spawned
int Counter = 0;

//fishleft
int FishCounter = 24;

//global variable for x and y
int x; 
int y;

//used to display how many fish are left to catch in the level
int fishLeft = 0;

//the amount of lives the player is given
int lives = 3;

//players score
int score = 0;

//health given to the boat
int boat = 100;

//current boat damage
int boatDamage = 0;

//current level selected
int level = 0;

//cols and rows for 2d array
int cols = 6;
int rows = 4;

//counter for player live images spawning
int remove = 1;

//fireworks counter
int celebration = 0;

void setup() {

  //set size of the screen
  size(1000, 800);  
  
  //loads images from file
  bg = loadImage("images/bg.png");  
  gameOver = loadImage("images/gameOver.png");
  backdrop = loadImage("images/darkbg.png");  
  
  //resizes images
  bg.resize(width, height); 
  gameOver.resize(width, height); 
  backdrop.resize(width, height); 
  
  //sets up boat and fishing rod
  boat1 = new Boat(width/2, 385); 
  rod1 = new FishingRod(boat1.x, boat1.y+50);
  
  //sets up 3 enemy sharks  
  shark1 = new Shark(width/2-80, 500, 1);    
  shark2 = new Shark(width-width, 500, 2); 
  shark3 = new Shark(boat1.x, 500, 2); 
  
  //sets up fish the fish array  
  fishy0 = new Fish(x, 600, 1, 600, 6, 4);  
  fishy0.setup();  
  
  //sets up projectiles (bubbles)
  bubble0 = new Projectiles(200, 500, 2);    
  bubble1 = new Projectiles(800, 500, 2);
  
  //sets up 3 hearts on screen for the players lives
  playerLives0 = new PlayerLives(80, 100);
  playerLives1 = new PlayerLives(130, 100);
  playerLives2 = new PlayerLives(180, 100);
  
  // sets up where to draw the fireworks
  celebration1 = new Fireworks(width/2-150, 50);
}

void draw() 
{
  //game is initalized with switch statements when Case matches the Counter 
  switch(Counter)
  {
    // if counter is 0 we draw the start screen
  case 0: 
    Counter = 0;
    { //draw start screen
      image(bg, x, y);
    }
    break;
    //if counter = 1 we draw the game background image and render game assets 
  case 1: 
    Counter = 1; 
    { 
      //game level background is drawn  
      image(backdrop, x, y);
      
      //render the following; boat and fishing rod, fishes and the sharks
      boat1.render();
      rod1.render();
      fishy0.draw(); 
      
      //find out what needs to be loaded, based on the level selected
      pickLevel();
      
      //spawn the fishing projectiles on scree
      bubble0.bubbleSpawn();
      bubble1.bubbleSpawn();  
      
      //check for collision with fishing rod and fish 
      collision();
      
      //sets game variables (lives, health, score and rod health)
      onScreenText();
      
      //player lives render
      playerLives0.display();
      
      //fires are drawn on screen
      celebration1.spawn();
    }
    break;
    // if counter = 2 we display game over screen
  case 2: 
    Counter = 2;  
    {
      //game over screen is drawn
      image(gameOver, x, y);
    }
  }
}

/* 
checks to find out if the fish is not null
and if it finds the collision is true 
it sets that fish in the array to null
*/

void collision()
{
  for (int i = 0; i < cols; i++)
  {
    for (int j = 0; j < rows; j++)
    { 
      if (spawnFish[i][j] != null)      
      {  
        if (fishy0.fishCollision(spawnFish[i][j]) == true)      
        {
          spawnFish[i][j] = null;
        }
      }
    }
  }
}

//tells the program what to spawn based on the users option of level to play
void pickLevel()
{
  switch(level)
  { 
  case 1: 
    level = 1;
    {
      //load level 1
      shark1.gameLevel1();
    }
    break;

  case 2: 
    level = 2;
    {
      //load level 2
      shark1.gameLevel1();
      shark2.gameLevel2();
    }  
    break;

  case 3: 
    level = 3;
    {
      //load level 3
      shark1.gameLevel1();
      shark2.gameLevel2();
      shark3.gameLevel3();
    }  
    break;
  }
}

//draws the text on screen for multiple different text based counters
void onScreenText() {
  //score
  textAlign(LEFT);
  fill(255);
  textSize(20);
  text("Score = " + score, width/12, 25);
  //fishleft
  textAlign(LEFT);
  fill(255);
  textSize(20);
  text("Fish left = " + FishCounter + "/24", width/12, 50);
  //boat health
  textAlign(LEFT);
  fill(255);
  textSize(20);
  text("Boat Health = " + boat + "/" + "100% ", width/12, 75);
}

//monitors the keys definded below and if they're pressed an action is executed
void keyPressed()
{
  //enter starts the game
  if (keyCode == ENTER)
  {
    Counter = 1;
  }
  
  // loads level 1
  if (key == '1')
  {
    level = 1;
    println("level one initalizing");
  }
  
  //loads level 2
  if (key == '2')
  {
    level = 2;
    println("level two initalizing");
  }
  
  //loads level 3
  if (key == '3')
  {
    level = 3;
    println("level three initalizing");
  }
  
  //boat movement left 
  if (keyCode == LEFT && boat1.x > 5) //out of bounds check
  {    
    boat1.x = boat1.x - 15;
  }
  
  //boat movement right
  if (keyCode == RIGHT && boat1.x < 920) //out of bounds check
  {
    boat1.x = boat1.x + 15;
  }
  
  //fishing rod movement down
  if (keyCode == DOWN && rod1.y < 730) //out of bounds check
  {
    rod1.y = rod1.y + 15;
  }
  //fishing rod movement up
  else if (keyCode == UP && rod1.y > 440 ) //out of bounds check
  {
    rod1.y = rod1.y - 15;
  }
  
  //restart the game
  //reset variables 
  //respawn the 2D array
  if (keyCode == 'r' | keyCode == 'R')
  {
    fishy0 = new Fish(x, 600, 1, 600, 6, 4);  //set where to spawn the fish on the scene
    fishy0.setup(); //spawn the 2d fish arrray on the scene using the x, y, speedX, yLoc and cols and rows above
    Counter = 0; //load splash screen, for a game restart
    FishCounter = 24; //reset fish counter
    lives = 3; // reset player lives
    score = 0; //reset layer score
    println("Restarting Game"); //debug info

  }
}