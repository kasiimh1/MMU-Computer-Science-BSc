class FishingRod {

  PImage rod;
  int x;
  int y;

  FishingRod(int x, int y)
  {
    rod = loadImage("images/hook.png"); 
    rod.resize(80, 80); //resize the fishing rod image
    this.x = x;
    this.y = y;
  }

  void render()
  {
    image(rod, boat1.x+5, y); //x value is the boats x to make sure the rod and fishing boat move along together
    line(boat1.x+38, 460, boat1.x+38, rod1.y+30); // it renders when the fishing rod1.y is changed via a keyCoded event
  }
}