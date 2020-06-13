class Shark {

  PImage sharkRight, sharkLeft, sharkBite;
  int x;
  int y;
  int speedY;
  int count = 0;

  Shark(int x, int y, int speedY)
  {
    sharkRight = loadImage("images/shark/1.png"); 
    sharkRight.resize(80, 80); //resize the shark image  
    sharkLeft = loadImage("images/shark/2.png"); 
    sharkLeft.resize(80, 80); //resize the shark image 
    sharkBite = loadImage("images/shark/3.png"); 
    sharkBite.resize(80, 80); //resize the shark image 
    this.x = x;
    this.y = y;
    this.speedY = speedY;
  }

  void move()
  {
    y = y - speedY;
    if ( y < 460 || y > 800 )
      y = 800;
  }

  void render()
  {
    //animate the bubbles by showing a sequence of images 
    if (count<10)
      image(sharkLeft, x, y);
    else if (count<20)
      image(sharkRight, x, y);
    else if (count<30)
      image(sharkBite, x, y);
    else if (count<40)
      image(sharkLeft, x, y);    
    else count = -1;
    count=count+1;
  }

  void gameLevel1()
  {
    shark1.render();
    shark1.move();
    shark1.sharkCollision(shark1);
  }

  void gameLevel2() {

    shark2.render();
    shark2.move();    
    shark2.sharkCollision(shark2);
  }

  void gameLevel3()
  {
    shark3.render();
    shark3.move();
    shark3.sharkCollision(shark3);
    if ( shark3.y > 750 && shark3.y < 800)
    {
      x = boat1.x;
    }
  }

  boolean sharkCollision(Shark shark)
  { 
    for (int x = boat1.x-40; x < boat1.x+40; x++)
    {
      float distance = dist(x, boat1.y, shark.x, shark.y);
      if ( distance < 80)
      {
        //removes health ends up being - 5 * 5
        boat -= 5;
        //removes player score if shark hits boat 
        score -= score / 8;
        //fire damage
        boatDamage += 1;
        //make fireworks go away
        celebration = 0;
        //removes a life if the boat health goes below 0
        if (boat < 1 && lives > 0)
        {
          //resets boat health 
          boat = 100;
          //reset boat damage
          boatDamage = 0;
          //removes a life icon
          remove += 1;
          //remove a player life
          lives -= 1;
        } else if (lives < 1 && boat < 0)
        {
          //load gameover screen
          Counter = 2;
        }    
        return true;
      }
    }
    return false;
  }
}