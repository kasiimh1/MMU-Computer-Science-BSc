fish nemo1,nemo2,nemo3;
void setup()
{
  size(1000,700);
  nemo1 = new fish(50,15,5,5);
  nemo2 = new fish(50,200,5,5);
  nemo3 = new fish(50,450,5,5);
}
void draw()
{
nemo1.load();
}