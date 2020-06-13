final color ALIEN1 = color (0,255,0);
final color ALIEN2 = color (50,100,0);

class Alien {
  
  int x = 400;
  int y = 200;
  int speedX = 0;
  
  Alien(int x, int y , int speedX)
  {
    this.x = x;
    this.y = y;
    this.speedX = speedX;
  }
  
  void render()
  {
  fill(ALIEN1);
  ellipse(x,y,30,30); 
  fill(ALIEN2); 
  ellipse(x,y,50,15);
  }
  
 void move()
  {
    
    float stepY = random(-2,2);
    y =y +(int)stepY;
    x=x-speedX;
    if(x<0)
    x=width;  
  }
}