import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.sound.sampled.*;
import java.util.Date;

public class Teren extends Window{
	Image MV;
	Image bkg,bkg1,bkg2,bkg3,bkg4,bkg5;
	Image bush1,bush2,bush3,bush4;
	Image tree1,tree2,tree3,tree4;
	Image cap;
	Image fire;
	Image clouds,clouds1,clouds2,clouds3,clouds4;
	Image rabbit;
	Image end, cartus;
	Image[] rabbits = new Image[14];
	int[] rabw = new int[14];
	int[] rabh = new int[14];

	int nr_cartuse = 12;
	int scor_iepuri, scor_pasari;
	int raza_tinta = 15;

    Joc joc;
	int w, h;

	public Image im;
    public Graphics imgr;

	public Font f = new Font("Helvetica", 1, 48);
	public FontMetrics fm = getFontMetrics(f);
	public Font f1 = new Font("Helvetica", 1, 24);

	Logo logo;
	Vanator vanator;
	Nori nori;
	Iepure iepure;
	Rata rata;

	int x,y,ww,hh;
	int iep_x, iep_y;
	int rat_x, rat_y, rat_size, rat_dir;
	boolean foc;
	boolean nimerit = false;
	boolean rat_nimerit = false;
	boolean stop_joc = false;

	long t0;
	int nr_incarcari;
	boolean play_inc, play_help;


    public Teren(Joc joc, int w, int h) {
    	super(joc);
    	this.joc = joc;
		this.w = w;
		this.h = h;

    	try {
        	MediaTracker mt = new MediaTracker(this);
			double k = 0;

			BufferedImage BI = ImageIO.read(new File("res/imagini/MV.png"));
			MV = BI.getScaledInstance(1024, 229, Image.SCALE_DEFAULT);
			mt.addImage(MV, 0);

			BI = ImageIO.read(new File("res/imagini/bkg1.jpg"));
			bkg1 = BI.getScaledInstance(w, h, Image.SCALE_DEFAULT);
			mt.addImage(bkg1, 0);

			k = 0.5;
			BI = ImageIO.read(new File("res/imagini/cap.png"));
			cap = BI.getScaledInstance((int)(k*660), (int)(k*488), Image.SCALE_DEFAULT);
			mt.addImage(cap, 1);
			ww = cap.getWidth(this);
			hh = cap.getHeight(this);

			BI = ImageIO.read(new File("res/imagini/fire1.png"));
			fire = BI.getScaledInstance(220, 350, Image.SCALE_DEFAULT);
			mt.addImage(fire, 2);

			BI = ImageIO.read(new File("res/imagini/clouds2.png"));
			clouds1 = BI.getScaledInstance(400, 218, Image.SCALE_DEFAULT);
			mt.addImage(clouds1, 3);

			BI = ImageIO.read(new File("res/imagini/clouds1.png"));
			clouds2 = BI.getScaledInstance(290, 221, Image.SCALE_DEFAULT);
			mt.addImage(clouds2, 4);

			BI = ImageIO.read(new File("res/imagini/clouds3.png"));
			clouds3 = BI.getScaledInstance(320, 206, Image.SCALE_DEFAULT);
			mt.addImage(clouds3, 5);

			BI = ImageIO.read(new File("res/imagini/clouds4.png"));
			clouds4 = BI.getScaledInstance(277, 200, Image.SCALE_DEFAULT);
			mt.addImage(clouds4, 6);

			BI = ImageIO.read(new File("res/imagini/rabbit_1.png"));
			rabw[1] = 64; rabh[1] = 64;
			rabbits[1] = BI.getScaledInstance(rabw[1], rabh[1], Image.SCALE_DEFAULT);
			mt.addImage(rabbits[1], 7);

			BI = ImageIO.read(new File("res/imagini/rabbit_2.png"));
			rabw[2] = 64; rabh[2] = 55;
			rabbits[2] = BI.getScaledInstance(rabw[2], rabh[2], Image.SCALE_DEFAULT);
			mt.addImage(rabbits[2], 8);

			BI = ImageIO.read(new File("res/imagini/rabbit_3.png"));
			rabw[3] = 70; rabh[3] = 56;
			rabbits[3] = BI.getScaledInstance(rabw[3], rabh[3], Image.SCALE_DEFAULT);
			mt.addImage(rabbits[3], 9);

			BI = ImageIO.read(new File("res/imagini/rabbit_4.png"));
			rabw[4] = 48; rabh[4] = 48;
			rabbits[4] = BI.getScaledInstance(rabw[4], rabh[4], Image.SCALE_DEFAULT);
			mt.addImage(rabbits[4], 10);

			BI = ImageIO.read(new File("res/imagini/rabbit_5.png"));
			rabw[5] = 56; rabh[5] = 50;
			rabbits[5] = BI.getScaledInstance(rabw[5], rabh[5], Image.SCALE_DEFAULT);
			mt.addImage(rabbits[5], 11);

			BI = ImageIO.read(new File("res/imagini/rabbit_6.png"));
			rabw[6] = 50; rabh[6] = 47;
			rabbits[6] = BI.getScaledInstance(rabw[6], rabh[6], Image.SCALE_DEFAULT);
			mt.addImage(rabbits[6], 12);

			BI = ImageIO.read(new File("res/imagini/rabbit_7.png"));
			rabw[7] = 70; rabh[7] = 56;
			rabbits[7] = BI.getScaledInstance(rabw[7], rabh[7], Image.SCALE_DEFAULT);
			mt.addImage(rabbits[7], 13);

			BI = ImageIO.read(new File("res/imagini/rabbit_8.png"));
			rabw[8] = 64; rabh[8] = 60;
			rabbits[8] = BI.getScaledInstance(rabw[8], rabh[8], Image.SCALE_DEFAULT);
			mt.addImage(rabbits[8], 14);

			BI = ImageIO.read(new File("res/imagini/rabbit_9.png"));
			rabw[9] = 80; rabh[9] = 53;
			rabbits[9] = BI.getScaledInstance(rabw[9], rabh[9], Image.SCALE_DEFAULT);
			mt.addImage(rabbits[9], 15);

			BI = ImageIO.read(new File("res/imagini/rabbit_10.png"));
			rabw[10] = 48; rabh[10] = 80;
			rabbits[10] = BI.getScaledInstance(rabw[10], rabh[10], Image.SCALE_DEFAULT);
			mt.addImage(rabbits[10], 16);

			BI = ImageIO.read(new File("res/imagini/rabbit_11.png"));
			rabw[11] = 59; rabh[11] = 80;
			rabbits[11] = BI.getScaledInstance(rabw[11], rabh[11], Image.SCALE_DEFAULT);
			mt.addImage(rabbits[11], 17);

			BI = ImageIO.read(new File("res/imagini/rabbit_12.png"));
			rabw[12] = 66; rabh[12] = 70;
			rabbits[12] = BI.getScaledInstance(rabw[12], rabh[12], Image.SCALE_DEFAULT);
			mt.addImage(rabbits[12], 18);

			BI = ImageIO.read(new File("res/imagini/rabbit_13.png"));
			rabw[13] = 44; rabh[13] = 48;
			rabbits[13] = BI.getScaledInstance(rabw[13], rabh[13], Image.SCALE_DEFAULT);
			mt.addImage(rabbits[13], 18);

			BI = ImageIO.read(new File("res/imagini/bush_1.png"));
			bush1 = BI.getScaledInstance(100, 84, Image.SCALE_DEFAULT);
			mt.addImage(bush1, 19);

			BI = ImageIO.read(new File("res/imagini/bush_2.png"));
			bush2 = BI.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			mt.addImage(bush2, 20);

			BI = ImageIO.read(new File("res/imagini/bush_3.png"));
			bush3 = BI.getScaledInstance(256, 256, Image.SCALE_DEFAULT);
			mt.addImage(bush3, 21);

			BI = ImageIO.read(new File("res/imagini/bush_4.png"));
			bush4 = BI.getScaledInstance(250, 200, Image.SCALE_DEFAULT);
			mt.addImage(bush4, 22);

			BI = ImageIO.read(new File("res/imagini/tree_1.png"));
			tree1 = BI.getScaledInstance(1100, 823, Image.SCALE_DEFAULT);
			mt.addImage(tree1, 23);

			BI = ImageIO.read(new File("res/imagini/end.png"));
			end = BI.getScaledInstance(480, 480, Image.SCALE_DEFAULT);
			mt.addImage(end, 24);

			BI = ImageIO.read(new File("res/imagini/cartus.png"));
			cartus = BI.getScaledInstance(48, 48, Image.SCALE_DEFAULT);
			mt.addImage(cartus, 25);

    		mt.waitForAll();
		}
		catch(Throwable e) {
			System.out.println("Eroare la incarcarea imaginilor!");
			e.printStackTrace();
		}
		bkg = bkg1;
		setBounds(0, 0, w, h);
		setBackground(Color.black);
		setVisible(true);
		im = createImage(w, h);
		imgr = im.getGraphics();
		logo = new Logo(this, w, h);
		logo.start();
		vanator = new Vanator(this, w, h);
		vanator.start();
		nori = new Nori(this, w, h);
		nori.start();
		iepure = new Iepure(this, w, h);
		iepure.start();
		rata = new Rata(this, w, h);
		rata.start();
		playSound("audiobackg.au", true);

    }

	public void paint(Graphics g){
		update(g);
	}

    public void update(Graphics g){
		if(im != null && imgr != null){
			imgr.drawImage(bkg, 0, 0, this);

			if(logo.activ){
				imgr.setFont(f);
				imgr.setColor(Color.black);
				if(logo.k<logo.text.length()){
					imgr.setColor(Color.black);
					imgr.drawString(logo.text.substring(0,logo.k), logo.xx[0]+6, logo.y+6);
					imgr.drawString(""+logo.text.charAt(logo.k), logo.x+4, logo.y+4);
					imgr.setColor(Color.white);
					imgr.drawString(logo.text.substring(0,logo.k), logo.xx[0], logo.y);
					imgr.drawString(""+logo.text.charAt(logo.k), logo.x, logo.y);
				}else{
					imgr.setColor(Color.black);
					imgr.drawString(logo.text, logo.xx[0]+6, logo.y+6);
					imgr.setColor(Color.white);
					imgr.drawString(logo.text, logo.xx[0], logo.y);
					imgr.drawImage(MV, (w-1024)/2, 300, this);
				}

			}

			if(nori.activ){
				switch(nori.nr_img){
					case 1:
						clouds = clouds1;
						break;
					case 2:
						clouds = clouds2;
						break;
					case 3:
						clouds = clouds3;
						break;
					case 4:
						clouds = clouds4;
						break;
				}
				imgr.drawImage(clouds, nori.x, nori.y, this);
			}

			if(!logo.activ){
				if(!stop_joc){
					// draw duck (rata)
					rat_x = rata.x; rat_y = rata.y;
					rat_size = rata.size; rat_dir = rata.dir;
					if(!rat_nimerit) drawDuck(imgr, rat_x, rat_y, rat_size, rat_dir);

					rabbit = rabbits[iepure.nr_img];
					iep_x = iepure.x;
					iep_y = iepure.yy[iepure.nr_img];
					if(!nimerit) imgr.drawImage(rabbit, iep_x, iep_y, this);

					Point p0 = new Point(vanator.x+ww/2, vanator.y+hh/2);
					Point p1 = new Point(this.x, this.y);
					if(foc)imgr.drawLine(p0.x, p0.y, p1.x, p1.y);

					int[] xx = new int[4];
					int[] yy = new int[4];
					int r = 10;
					double d = Math.sqrt((p1.x-p0.x)*(p1.x-p0.x)+(p1.y-p0.y)*(p1.y-p0.y));
					double t = d>700?0.15:d>600?0.2:d>500?0.25:d>400?0.35:d>300?0.45:d>200?0.55:0.65;
					if(Math.abs(p1.y-p0.y)>Math.abs(p1.x-p0.x)){
						xx[0] = p0.x-r; xx[1] = p0.x-r + (int)(t*(p1.x-p0.x+r)+0.5);
						xx[3] = p0.x+r; xx[2] = p0.x+r + (int)(t*(p1.x-p0.x-r)+0.5);
						yy[0] = p0.y; yy[1] = p0.y + (int)(t*(p1.y-p0.y)+0.5);
						yy[3] = p0.y; yy[2] = p0.y + (int)(t*(p1.y-p0.y)+0.5);
					}else{
						yy[0] = p0.y-r; yy[1] = p0.y-r + (int)(t*(p1.y-p0.y+r)+0.5);
						yy[3] = p0.y+r; yy[2] = p0.y+r + (int)(t*(p1.y-p0.y-r)+0.5);
						xx[0] = p0.x; xx[1] = p0.x + (int)(t*(p1.x-p0.x)+0.5);
						xx[3] = p0.x; xx[2] = p0.x + (int)(t*(p1.x-p0.x)+0.5);
					}
					imgr.setColor(Color.black);
					int r1 = (int)(Math.sqrt((xx[1]-xx[2])*(xx[1]-xx[2])+(yy[1]-yy[2])*(yy[1]-yy[2]))/2);
					imgr.fillOval((xx[1]+xx[2])/2-r1+1,(yy[1]+yy[2])/2-r1+1, 2*(r1-1), 2*(r1-1));
					imgr.fillPolygon(xx,yy,4);

					if(foc){
						imgr.drawImage(fire, (xx[1]+xx[2])/2-110, (yy[1]+yy[2])/2-200, this);
						foc = false;
					}

					imgr.drawImage(cap, vanator.x, vanator.y, this);

				}else{
					long t = (new Date()).getTime()-t0;
					long prag = 100;
					if(t<2000+prag){
						play_inc = true;
					}else if(t<4000){
						if(play_inc) {
							playSound("inc.au", false);
							nr_cartuse++;
							play_inc=false;
						}
					}else if(t<4000+prag){
						play_inc = true;
						play_help = true;
					}else if(t<6000){
						if(play_inc) {
							playSound("inc.au", false);
							nr_cartuse++;
							play_inc=false;
						}
						if(play_help){
							playSound("help.au", false);
							play_help=false;
						}
						imgr.drawImage(end, (w - 338)/2-100, 480, this);
					}else if(t<25500){
						if(t<6000+2000*nr_incarcari+prag){
							play_inc = true;
						}else if(t<8000+2000*nr_incarcari){
							if(play_inc) {
								playSound("inc.au", false);
								nr_cartuse++;
								play_inc=false;
								nr_incarcari++;
							}
						}
						imgr.drawImage(end, (w - 338)/2-100, 480, this);

					}else{
						stop_joc = false;
						vanator = new Vanator(this, w, h);
						vanator.start();
						iepure = new Iepure(this, w, h);
						iepure.start();
						rata = new Rata(this, w, h);
						rata.start();
					}
				}

				// score panel: rabbit
				imgr.drawImage(rabbits[10], w-100, 10, this);
				imgr.setColor(Color.red);
				imgr.setFont(f1);
				imgr.drawString(""+scor_iepuri, w-40, 60);
				// score panel: bullets
				imgr.drawImage(cartus, w-100, 100, this);
				imgr.setColor(Color.black);
				imgr.drawString(""+nr_cartuse, w-40, 140);
				// score panel: duck (rata)
				drawDuck(imgr, w-90, 165, 1, -1);
				imgr.setColor(new Color(0, 80, 180));
				imgr.drawString(""+scor_pasari, w-40, 200);
			}

			imgr.drawImage(bush4, 100, 620, this);
			imgr.drawImage(bush3, w-200, 700, this);
			imgr.drawImage(bush2, 1000, 680, this);
			imgr.drawImage(bush1, 480, 710, this);
			imgr.drawImage(tree1, -560, 30, this);

			g.drawImage(im, 0, 0, this);
		}

    }

	void drawDuck(Graphics g, int dx, int dy, int size, int dir) {
		int bw = 30 + size * 15;  // body width:  45, 60, 75
		int bh = 15 + size * 8;   // body height: 23, 31, 39
		int hr = 5  + size * 3;   // head radius:  8, 11, 14

		// tail
		g.setColor(new Color(60, 40, 20));
		int[] tailX = new int[3];
		int[] tailY = new int[3];
		if (dir == -1) {  // flying left -> tail at right
			tailX[0] = dx+bw;   tailX[1] = dx+bw+hr; tailX[2] = dx+bw;
			tailY[0] = dy;       tailY[1] = dy+bh/2;  tailY[2] = dy+bh;
		} else {          // flying right -> tail at left
			tailX[0] = dx;      tailX[1] = dx-hr;     tailX[2] = dx;
			tailY[0] = dy;      tailY[1] = dy+bh/2;   tailY[2] = dy+bh;
		}
		g.fillPolygon(tailX, tailY, 3);

		// wing highlight
		g.setColor(new Color(120, 80, 30));
		int[] wingX = {dx+bw/5, dx+4*bw/5, dx+bw/2};
		int[] wingY = {dy+2, dy+2, dy-bh/3};
		g.fillPolygon(wingX, wingY, 3);

		// body
		g.setColor(new Color(90, 55, 20));
		g.fillOval(dx, dy, bw, bh);

		// head position
		int hx = (dir == -1) ? dx + hr/2 : dx + bw - 3*hr/2 - hr/2;
		int hy = dy - hr;

		// head (mallard green)
		g.setColor(new Color(0, 110, 30));
		g.fillOval(hx, hy, hr*2, hr*2);

		// beak
		g.setColor(new Color(230, 180, 0));
		int midHY = hy + hr;
		int[] bkX = new int[3];
		int[] bkY = new int[3];
		if (dir == -1) {
			bkX[0] = hx;       bkX[1] = hx - hr;   bkX[2] = hx;
			bkY[0] = midHY-hr/3; bkY[1] = midHY;   bkY[2] = midHY+hr/3;
		} else {
			bkX[0] = hx+2*hr;     bkX[1] = hx+2*hr+hr; bkX[2] = hx+2*hr;
			bkY[0] = midHY-hr/3;  bkY[1] = midHY;       bkY[2] = midHY+hr/3;
		}
		g.fillPolygon(bkX, bkY, 3);

		// eye
		int eyeX = (dir == -1) ? hx + hr/2 : hx + hr;
		g.setColor(Color.white);
		g.fillOval(eyeX, hy + hr/3, hr/2+1, hr/2+1);
		g.setColor(Color.black);
		g.fillOval(eyeX + (dir == -1 ? 0 : hr/4), hy + hr/3, hr/3+1, hr/3+1);
	}

	public boolean mouseMove(Event e, int x, int y){
		this.x = x;
		this.y = y;
		return true;
	}

	public boolean mouseDown(Event e, int x, int y){
		this.x = x;
		this.y = y;
		if(!logo.activ){
			if(!stop_joc){
				foc = true;
				trage();
				// check rabbit hit
				int k = iepure.nr_img;
				for(int i=-raza_tinta; i<=raza_tinta; i++)
					for(int j=-raza_tinta; j<=raza_tinta; j++)
						if(x == iep_x + rabw[k]/2 + i && y == iep_y + rabh[k]/2 + j){
							scor_iepuri++;
							nimerit = true;
						}
				// check duck (rata) hit
				int rdw = rata.dw;
				int rdh = rata.dh;
				for(int i=-raza_tinta; i<=raza_tinta; i++)
					for(int j=-raza_tinta; j<=raza_tinta; j++)
						if(x == rat_x + rdw/2 + i && y == rat_y + rdh/2 + j){
							scor_pasari++;
							rat_nimerit = true;
							rata.reset();
							rat_nimerit = false;
						}
				nr_cartuse--;
				if(nr_cartuse==0) {
					stop_joc = true;
					vanator.activ = false;
					iepure.activ = false;
					rata.activ = false;
					t0 = (new Date()).getTime();
					nr_incarcari = 0;
				}
			}
		}
		return true;
	}

	void playSound(String nume_fisier_sunet, boolean repeta) {
		try{
			String res = System.getProperty("user.dir")+"/res/sunete/";
			String soundFile = res + nume_fisier_sunet;
			File fin = new File(soundFile).getAbsoluteFile();
			AudioInputStream ais = AudioSystem.getAudioInputStream(fin);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.loop(0);
			clip.start();
			if(repeta) clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(IOException e){e.printStackTrace();}
		catch(UnsupportedAudioFileException e){e.printStackTrace();}
		catch(LineUnavailableException e){e.printStackTrace();}
	}

	void trage(){
		int nr = (int)(Math.random()*8) + 1;
		String sunet = "0"+nr;
		sunet += ".au";
		playSound(sunet, false);
		playSound("inc.au", false);
	}



}



