import java.awt.*;

class Joc extends Frame{
	int w, h;
    Teren teren;
	
	public static void main(String[] args){
		new Joc();
	}
   
    Joc(){
		Toolkit tool = getToolkit();
		Dimension res=tool.getScreenSize();
        w = res.width; h = res.height;	
		teren = new Teren(this, w, h);
    }
	

}
