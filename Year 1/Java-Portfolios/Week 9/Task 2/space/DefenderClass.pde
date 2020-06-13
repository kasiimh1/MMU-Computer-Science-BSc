class Defender {
  
  int x = 400;
  int y = 200;
  int speed = 0;
  boolean crash = false;
  
  Defender(int x, int y , int speed)
  {
    this.x = x;
    this.y = y;
    this.speed = speed;
  }
  
  void render()
  {
fill(255,0,0);
rect(x,y,50,20); 
triangle(x+50,y,x+50,y+20,x+60,y+10); 
fill(0,0,100);
rect(x,y-10,20,10);
  }
    
boolean crash() {
    color peekColor;  
    for(int i=y; i < 200 ; i++) {
      peekColor = get(400, i);
      if (peekColor != ALIEN1) {
        return true;        
    }
  }
  return false;
}
}