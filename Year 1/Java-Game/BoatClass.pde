class Boat {

  PImage boat, fire0, fire1, fire2, fire3, fire4, fire5;
  int x;
  int y;
  int count;

  Boat(int x, int y)
  {
    boat = loadImage("images/boat.png"); 
    boat.resize(80, 80); //resize the boat image
    fire0 = loadImage("images/fire/0.png");
    fire0.resize(60, 60); //resize the fire image    
    fire1 = loadImage("images/fire/1.png");
    fire1.resize(60, 60); //resize the fire image 
    fire2 = loadImage("images/fire/2.png");
    fire2.resize(60, 60); //resize the fire image 
    fire3 = loadImage("images/fire/3.png");
    fire3.resize(60, 60); //resize the fire image    
    this.x = x;
    this.y = y;
  }

  void render()
  {
    //render the boat on screen
    image(boat, x, y);

    // if the boat damage counter > 0 then redner the fire on the boat
    if (boatDamage > 0)
    {
      //animate the boat images to make it look like that a gif is playing 
      display();
    }
  }

  void display()
  {

    //which fire image is set to be display at x, y at which count, giving the fish fire animation
    if (count<10)
      image(fire0, x, y);
    else if (count<20)
      image(fire1, x, y);
    else if (count<30)
      image(fire2, x, y);
    else if (count<40)
      image(fire3, x, y);
    else count = -1;
    count=count+1;
  }
}