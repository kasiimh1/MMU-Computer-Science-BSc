class Fireworks {   

  PImage firework0, firework1, firework2, firework3, firework4;   
  int x;
  int y;
  int count = 0;

  Fireworks(int x, int y) {
    firework0 = loadImage("images/fireworks/0.jpg"); 
    firework0.resize(300, 300); //resize the firework image 
    firework1 = loadImage("images/fireworks/1.jpg"); 
    firework1.resize(300, 300); //resize the firework image 
    firework2 = loadImage("images/fireworks/2.jpg"); 
    firework2.resize(300, 300); //resize the firework image       
    firework3 = loadImage("images/fireworks/3.jpg"); 
    firework3.resize(300, 300); //resize the firework image      
    firework4 = loadImage("images/fireworks/4.jpg"); 
    firework4.resize(300, 300); //resize the firework image       
    this.x = x;
    this.y = y;
  }  

  void render()
  {
    //animate the fireworks by showing a sequence of images 
    if (count<10)
      image(firework0, x, y);
    else if (count<20)
      image(firework1, x, y);
    else if (count<30)
      image(firework2, x, y);
    else if (count<40)
      image(firework3, x, y); 
    else if (count<50)
      image(firework4, x, y);       
    else count = -1;      
    count=count+1;
  }  

  void spawn()
  {
    // render the fireworks upon a fish being caught, they will stop if the shark hits the boat
    if (celebration == 1)
    {
      //animate the firework images to make it look like that a gif is playing 
      render();
    }
  }
}