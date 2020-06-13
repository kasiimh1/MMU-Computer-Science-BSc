package week16;
public class main {
	public static void main(String[] args) {
	Image2Ascii image2ascii = new Image2Ascii();
	image2ascii.LoadImage("cat.png");
	//image2ascii.LoadImage("mona.png");
	image2ascii.Print();
	image2ascii.PrintWarhol();
	}
}
