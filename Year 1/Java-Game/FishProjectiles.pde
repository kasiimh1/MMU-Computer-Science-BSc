class Projectiles {   //<>// //<>// //<>// //<>//

  PImage bubble0, bubble1, bubble2, bubble3, bubble4;  
  int x;
  int y;
  int speedY;
  int count = 0;

  Projectiles(int x, int y, int speedY) {
    bubble0 = loadImage("images/1.png"); 
    bubble0.resize(40, 80); //resize the bubble image 
    bubble1 = loadImage("images/2.png"); 
    bubble1.resize(40, 80); //resize the bubble image       
    bubble2 = loadImage("images/3.png"); 
    bubble2.resize(40, 80); //resize the bubble image      
    bubble3 = loadImage("images/4.png"); 
    bubble3.resize(40, 80); //resize the bubble image     -
    // pop image when bubble > boat1.y (water level)
    bubble4 = loadImage("images/pop.png"); 
    bubble4.resize(80, 80); //resize the pop image 
    this.x = x;
    this.y = y;
    this.speedY = speedY;
  }  

  void render()
  {
    //animate the bubbles by showing a sequence of images 
    if (count<10)
      image(bubble0, x, y);
    else if (count<20)
      image(bubble1, x, y);
    else if (count<30)
      image(bubble2, x, y);
    else if (count<40)
      image(bubble3, x, y);    
    else count = -1;      
    count=count+1;
    if (y < 430)
    {
      // display pop image
      image(bubble4, x-30, y);
    }
  }  

  void move()
  {
    //code taken from my spaceship portfolio to give the bubbles some random movements 
    float stepY = random(-2, 2);
    y = y +(int)stepY;
    y = y - speedY;   
    
    //reset y if not within these limits
    if ( y < 420 || y > 800 )
      y = 800;
  }

  void bubbleSpawn()
  {
    render();
    move();
  }
}