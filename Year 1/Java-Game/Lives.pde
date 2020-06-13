class PlayerLives
{
  int x;
  int y;
  PImage heart;

  PlayerLives(int x, int y)
  {
    heart = loadImage("images/heart.png");
    heart.resize(40, 40); //resize the heart image
    this.x = x;
    this.y = y;
  }

  void display()
  {    
    if (remove == 1)
    {
      //display 3 lives
      playerLives0.draw();
      playerLives1.draw();
      playerLives2.draw();
    }

    if (remove == 2)
    {    
      //display 2 lives
      playerLives0.draw();
      playerLives1.draw();
    }

    if (remove == 3)
    {   
      //display 1 heart
      playerLives0.draw();
    }

    if (remove == 4)
    {    
      // if no lives left then end the game
      Counter = 2;
    }
  }

  void draw()
  {
    // draw lives heart image on screen
    image(heart, x, y);
  }
}