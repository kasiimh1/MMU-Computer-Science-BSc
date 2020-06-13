class fish
{
  int x;
  int y;
  int dirX;
  int dirY;
  PImage img1, img2; 
  fish(int x, int y, int dirX, int dirY)
  {
    this.x = x;
    this.y = y;
    this.dirX = dirX;
    this.dirY = dirY;
    img1 = loadImage("Fish Left.png");
    img2 = loadImage("Fish Right.png");
  }
void load() {
   background(0,212,255);
   nemo1.move();
   nemo1.flip();   
   nemo1.collision(nemo2);
   nemo1.collision(nemo3);
   nemo2.collision(nemo3);  
   nemo2.move();
   nemo2.flip();  
   nemo3.move();
   nemo3.flip();   
 }
void collision (fish nemo){
if (abs(this.x - nemo.x ) < 199 && abs(this.y - nemo.y ) <75)
{  
  this.dirX =  this.dirX * -1;
  this.dirY =  this.dirY * -1;
  
  nemo.dirX =  nemo.dirX * -1;
  nemo.dirY =  nemo.dirY * -1;
}
}
void move()
{
  x=x+dirX;
if(x>= width-180)
  {
    dirX = -dirX;
  }
else if (x<= width/-50)
  {
    dirX = -dirX;
  }
 y=y+dirY;
 if (y>=height-150)
   {
     dirY = +dirY;
     dirY = -dirY;
   }
 else if (y<=height/-12)
   {
   dirY = +dirY;
   dirY = -dirY;
   }   
}
void flip(){
if(dirX == -5 && dirY == -5)
image(img2,x,y);  
else if(dirX == 5 && dirY == -5)
image(img1,x,y);
else if (dirX == -5 && dirY == 5)
image(img2,x,y);    
else if (dirX == 5 && dirY == 5)
image(img1,x,y);
}  
}