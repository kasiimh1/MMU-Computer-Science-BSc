
Alien aliens1, aliens2;
Defender defender1;

PImage background;
PImage GAMEOVER;
int x=0; //global variable background location
void setup(){
size(800,400);
GAMEOVER = loadImage("overBackground.jpg"); GAMEOVER.resize(width,height);
background = loadImage("spaceBackground.jpg"); background.resize(width,height);

aliens1 = new Alien(400,100,15);
aliens2 = new Alien(400,200,15);
defender1 = new Defender(10,300,0);
}
void draw ()
{
drawBackground();
aliens1.render();
aliens1.move();

defender1.render();
aliens2.render();
aliens2.move();
}

void drawBackground()
{
image(background, x, 0); //draw background twice adjacent 
image(background, x+background.width, 0);
x -=4;
if(x == -background.width)
x=0; 

if (defender1.crash() == true){
println("GAME OVER!");
image(GAMEOVER, x, 0); //draw overbackground twice adjacent 
image(GAMEOVER, x+GAMEOVER.width, 0);
}
}

void keyPressed()
{
  if (key == CODED)
  {
    if (keyCode == UP)
    {
      defender1.y = defender1.y - 15;
    }
         else if (keyCode == DOWN){
           defender1.y = defender1.y + 15;
         }
    }
  }