package sungshin_beat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	// ÆÇÁ¤¶óÀÎ
	private String score = "00000";

	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();

	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image blueFlareImage;
	private Image judgeImage;
	private Image keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();

	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;

	private int count = 0;
	ArrayList<Note> noteList = new ArrayList<Note>();

	public static int all = 0;
	public double percent;
	public static boolean isGameEnd;
	public String grade;

	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
//		dropNotes(titleName);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);

		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);

		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);

		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (note.getY() > 620) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
				count = 0;
			}
			if (!note.isProceeded()) {
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}
		}

		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1190, 702);
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString(score, 565, 702);

		g.drawImage(blueFlareImage, 300, 290, null);
		g.drawImage(judgeImage, 460, 420, null);
		g.setColor(Color.BLACK);
		/* count¼¾°Å »Ñ·ÁÁÜ */
		g.drawString("" + count, 615, 400);

//		try {
//			Thread.sleep(5);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		g.drawImage(keyPadSImage, 228, 580, null);
		g.drawImage(keyPadDImage, 332, 580, null);
		g.drawImage(keyPadFImage, 436, 580, null);
		g.drawImage(keyPadSpace1Image, 540, 580, null);
		g.drawImage(keyPadSpace2Image, 640, 580, null);
		g.drawImage(keyPadJImage, 744, 580, null);
		g.drawImage(keyPadKImage, 848, 580, null);
		g.drawImage(keyPadLImage, 952, 580, null);
	}

	public void screenDraw2(Graphics2D g) {

		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 420, 120);
		g.drawString(difficulty, 730, 120);
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);

		g.setColor(Color.YELLOW);
		g.setFont(new Font("Arial", Font.BOLD, 72));
		g.drawString(score, 357, 580);

		g.setColor(Color.ORANGE);
		g.setFont(new Font("Arial", Font.BOLD, 450));
		g.drawString(grade, 290, 475);

	}

	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("sound.mp3", false).start();
	}

	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();

	}

	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("sound.mp3", false).start();
	}

	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("sound.mp3", false).start();
	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("sound.mp3", false).start();
	}

	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("sound.mp3", false).start();
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("sound.mp3", false).start();
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("sound.mp3", false).start();
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	@Override
	public void run() {
		dropNotes(this.titleName);
		calculate();
	}

	public void calculate() {
		int temp = Integer.parseInt(score);
		percent = ((double) temp / (double) all) * 100;
		if (percent >= 90) {
			grade = "S";
		} else if (percent >= 80) {
			grade = "A";
		} else if (percent >= 70) {
			grade = "B";
		} else if (percent >= 50) {
			grade = "C";
		} else if (percent >= 40) {
			grade = "D";
		} else
			grade = "F";

	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	public void dropNotes(String titleName) {
		Beat[] beats = null;
		if (titleName.equals("Fredji - Happy Life") && difficulty.equals("Easy")) {
			int startTime = 1350 - Main.REACH_TIME * 1000;
			int gap = 1200;
			beats = new Beat[] { new Beat(startTime + gap * 0, "Space"), new Beat(startTime + gap * 1, "Space"),
					new Beat(startTime + gap * 2, "D"), new Beat(startTime + gap * 3, "S"),
					new Beat(startTime + gap * 4, "F"), new Beat(startTime + gap * 5, "L"),
					new Beat(startTime + gap * 6, "J"), new Beat(startTime + gap * 7, "K"),
					new Beat(startTime + gap * 8, "Space"), new Beat(startTime + gap * 9, "D"),
					new Beat(startTime + gap * 10, "S"), new Beat(startTime + gap * 11, "F"),
					new Beat(startTime + gap * 12, "L"), new Beat(startTime + gap * 13, "J"),
					new Beat(startTime + gap * 14, "K"), new Beat(startTime + gap * 15, "Space"),
					new Beat(startTime + gap * 16, "D"), new Beat(startTime + gap * 17, "S"),
					new Beat(startTime + gap * 18, "F"), new Beat(startTime + gap * 19, "L"),
					new Beat(startTime + gap * 20, "J"), new Beat(startTime + gap * 21, "K"),
					new Beat(startTime + gap * 22, "Space"), new Beat(startTime + gap * 23, "D"),
					new Beat(startTime + gap * 24, "S"), new Beat(startTime + gap * 25, "F"),
					new Beat(startTime + gap * 26, "L"), new Beat(startTime + gap * 27, "J"),
					new Beat(startTime + gap * 28, "K"), new Beat(startTime + gap * 29, "Space"),
					new Beat(startTime + gap * 30, "D"), new Beat(startTime + gap * 31, "S"),
					new Beat(startTime + gap * 32, "F"), new Beat(startTime + gap * 33, "L"),
					new Beat(startTime + gap * 34, "J"), new Beat(startTime + gap * 35, "K"),
					new Beat(startTime + gap * 36, "Space"), new Beat(startTime + gap * 37, "D"),
					new Beat(startTime + gap * 38, "S"), new Beat(startTime + gap * 39, "F"),
					new Beat(startTime + gap * 40, "L"), new Beat(startTime + gap * 41, "J"),
					new Beat(startTime + gap * 42, "K"), new Beat(startTime + gap * 43, "Space"),
					new Beat(startTime + gap * 44, "D"), new Beat(startTime + gap * 45, "S"),
					new Beat(startTime + gap * 46, "F"), new Beat(startTime + gap * 47, "L"),
					new Beat(startTime + gap * 48, "J"), new Beat(startTime + gap * 49, "K"),
					new Beat(startTime + gap * 50, "Space"), new Beat(startTime + gap * 51, "D"),
					new Beat(startTime + gap * 52, "S"), new Beat(startTime + gap * 53, "F"),
					new Beat(startTime + gap * 54, "L"), new Beat(startTime + gap * 56, ""), };
			all = beats.length * 50;
		}

		else if (titleName.equals("Fredji - Happy Life") && difficulty.equals("Hard")) {
			int startTime = 1350 - Main.REACH_TIME * 1000;
			int gap = 1200;
			beats = new Beat[] { new Beat(startTime + gap * 0, "Space"), new Beat(startTime + gap * 1, "Space"),
					new Beat(startTime + gap * 2, "D"), new Beat(startTime + gap * 2, "K"),
					new Beat(startTime + gap * 3, "S"), new Beat(startTime + gap * 3, "F"),
					new Beat(startTime + gap * 4, "J"), new Beat(startTime + gap * 4, "F"),
					new Beat(startTime + gap * 5, "L"), new Beat(startTime + gap * 6, "J"),
					new Beat(startTime + gap * 7, "K"), new Beat(startTime + gap * 8, "Space"),
					new Beat(startTime + gap * 9, "D"), new Beat(startTime + gap * 10, "S"),
					new Beat(startTime + gap * 11, "F"), new Beat(startTime + gap * 12, "L"),
					new Beat(startTime + gap * 12, "Space"), new Beat(startTime + gap * 13, "J"),
					new Beat(startTime + gap * 14, "K"), new Beat(startTime + gap * 15, "Space"),
					new Beat(startTime + gap * 16, "D"), new Beat(startTime + gap * 17, "S"),
					new Beat(startTime + gap * 18, "F"), new Beat(startTime + gap * 18, "Space"),
					new Beat(startTime + gap * 18, "J"), new Beat(startTime + gap * 19, "L"),
					new Beat(startTime + gap * 20, "J"), new Beat(startTime + gap * 21, "K"),
					new Beat(startTime + gap * 22, "Space"), new Beat(startTime + gap * 23, "D"),
					new Beat(startTime + gap * 24, "S"), new Beat(startTime + gap * 25, "F"),
					new Beat(startTime + gap * 26, "L"), new Beat(startTime + gap * 27, "S"),
					new Beat(startTime + gap * 27, "D"), new Beat(startTime + gap * 27, "F"),
					new Beat(startTime + gap * 27, "Space"), new Beat(startTime + gap * 27, "J"),
					new Beat(startTime + gap * 27, "K"), new Beat(startTime + gap * 27, "L"),
					new Beat(startTime + gap * 28, "K"), new Beat(startTime + gap * 29, "Space"),
					new Beat(startTime + gap * 30, "D"), new Beat(startTime + gap * 31, "S"),
					new Beat(startTime + gap * 32, "F"), new Beat(startTime + gap * 33, "L"),
					new Beat(startTime + gap * 33, "S"), new Beat(startTime + gap * 34, "J"),
					new Beat(startTime + gap * 34, "D"), new Beat(startTime + gap * 34, "S"),
					new Beat(startTime + gap * 34, "F"), new Beat(startTime + gap * 35, "K"),
					new Beat(startTime + gap * 35, "F"), new Beat(startTime + gap * 35, "Space"),
					new Beat(startTime + gap * 36, "Space"), new Beat(startTime + gap * 37, "D"),
					new Beat(startTime + gap * 38, "S"), new Beat(startTime + gap * 39, "F"),
					new Beat(startTime + gap * 39, "Space"), new Beat(startTime + gap * 40, "L"),
					new Beat(startTime + gap * 41, "J"), new Beat(startTime + gap * 42, "K"),
					new Beat(startTime + gap * 43, "Space"), new Beat(startTime + gap * 44, "D"),
					new Beat(startTime + gap * 45, "S"), new Beat(startTime + gap * 46, "F"),
					new Beat(startTime + gap * 47, "L"), new Beat(startTime + gap * 47, "J"),
					new Beat(startTime + gap * 47, "Space"), new Beat(startTime + gap * 48, "J"),
					new Beat(startTime + gap * 49, "K"), new Beat(startTime + gap * 50, "Space"),
					new Beat(startTime + gap * 51, "D"), new Beat(startTime + gap * 52, "S"),
					new Beat(startTime + gap * 53, "F"), new Beat(startTime + gap * 54, "S"),
					new Beat(startTime + gap * 54, "D"), new Beat(startTime + gap * 54, "F"),
					new Beat(startTime + gap * 54, "Space"), new Beat(startTime + gap * 54, "J"),
					new Beat(startTime + gap * 54, "K"), new Beat(startTime + gap * 54, "L"),
					new Beat(startTime + gap * 56, ""), };
			all = beats.length * 50;
		}

		else if (titleName.equals("osh-va - Chilling") && difficulty.equals("Easy")) {
			int startTime = 0;
			int gap = 125;
			beats = new Beat[] { new Beat(startTime, "S"), new Beat(startTime + gap * 3, "F"),
					new Beat(startTime + gap * 6, "S"), new Beat(startTime + gap * 9, "F"),
					new Beat(startTime + gap * 12, "Space"), new Beat(startTime + gap * 15, "J"),
					new Beat(startTime + gap * 18, "L"), new Beat(startTime + gap * 21, "J"),
					new Beat(startTime + gap * 24, "L"), new Beat(startTime + gap * 27, "Space"),
					new Beat(startTime + gap * 30, "D"), new Beat(startTime + gap * 33, "K"),
					new Beat(startTime + gap * 36, "Space"), new Beat(startTime + gap * 39, "S"),
					new Beat(startTime + gap * 42, "F"), new Beat(startTime + gap * 45, "S"),
					new Beat(startTime + gap * 48, "F"), new Beat(startTime + gap * 51, "Space"),
					new Beat(startTime + gap * 54, "J"), new Beat(startTime + gap * 57, "L"),
					new Beat(startTime + gap * 60, "J"), new Beat(startTime + gap * 63, "L"),
					new Beat(startTime + gap * 66, "Space"), new Beat(startTime + gap * 69, "D"),
					new Beat(startTime + gap * 72, "K"), new Beat(startTime + gap * 75, "Space"),
					new Beat(startTime + gap * 78, "S"), new Beat(startTime + gap * 78, "F"),
					new Beat(startTime + gap * 81, "J"), new Beat(startTime + gap * 81, "L"),
					new Beat(startTime + gap * 84, "S"), new Beat(startTime + gap * 84, "F"),
					new Beat(startTime + gap * 87, "J"), new Beat(startTime + gap * 87, "L"),
					new Beat(startTime + gap * 90, "Space"), new Beat(startTime + gap * 93, "S"),
					new Beat(startTime + gap * 96, "F"), new Beat(startTime + gap * 99, "S"),
					new Beat(startTime + gap * 102, "F"), new Beat(startTime + gap * 105, "Space"),
					new Beat(startTime + gap * 108, "S"), new Beat(startTime + gap * 108, "F"),
					new Beat(startTime + gap * 111, "J"), new Beat(startTime + gap * 111, "L"),
					new Beat(startTime + gap * 114, "S"), new Beat(startTime + gap * 114, "F"),
					new Beat(startTime + gap * 117, "J"), new Beat(startTime + gap * 117, "L"),
					new Beat(startTime + gap * 120, "Space"), new Beat(startTime + gap * 123, "J"),
					new Beat(startTime + gap * 126, "L"), new Beat(startTime + gap * 129, "J"),
					new Beat(startTime + gap * 132, "L"), new Beat(startTime + gap * 135, "Space"),
					new Beat(startTime + gap * 137, "S"), new Beat(startTime + gap * 139, "F"),
					new Beat(startTime + gap * 141, "S"), new Beat(startTime + gap * 143, "F"),
					new Beat(startTime + gap * 145, "J"), new Beat(startTime + gap * 147, "L"),
					new Beat(startTime + gap * 149, "J"), new Beat(startTime + gap * 151, "L"),
					new Beat(startTime + gap * 153, "Space"), new Beat(startTime + gap * 155, "S"),
					new Beat(startTime + gap * 157, "F"), new Beat(startTime + gap * 159, "S"),
					new Beat(startTime + gap * 161, "F"), new Beat(startTime + gap * 163, "J"),
					new Beat(startTime + gap * 165, "L"), new Beat(startTime + gap * 167, "J"),
					new Beat(startTime + gap * 169, "L"), new Beat(startTime + gap * 171, "Space"),
					new Beat(startTime + gap * 173, "D"), new Beat(startTime + gap * 175, "K"),
					new Beat(startTime + gap * 177, "D"), new Beat(startTime + gap * 179, "K"),
					new Beat(startTime + gap * 181, "Space"), new Beat(startTime + gap * 183, "S"),
					new Beat(startTime + gap * 185, "F"), new Beat(startTime + gap * 187, "S"),
					new Beat(startTime + gap * 189, "F"), new Beat(startTime + gap * 191, "Space"),
					new Beat(startTime + gap * 193, "J"), new Beat(startTime + gap * 195, "L"),
					new Beat(startTime + gap * 197, "J"), new Beat(startTime + gap * 199, "L"),
					new Beat(startTime + gap * 201, "Space"), new Beat(startTime + gap * 203, "D"),
					new Beat(startTime + gap * 205, "K"), new Beat(startTime + gap * 207, "D"),
					new Beat(startTime + gap * 209, "K"), new Beat(startTime + gap * 211, "Space"),
					new Beat(startTime + gap * 213, "S"), new Beat(startTime + gap * 213, "F"),
					new Beat(startTime + gap * 222, "J"), new Beat(startTime + gap * 222, "L"),
					new Beat(startTime + gap * 231, "S"), new Beat(startTime + gap * 231, "F"),
					new Beat(startTime + gap * 240, "J"), new Beat(startTime + gap * 240, "L"),
					new Beat(startTime + gap * 249, "Space"), new Beat(startTime + gap * 257, "S"),
					new Beat(startTime + gap * 257, "F"), new Beat(startTime + gap * 266, "J"),
					new Beat(startTime + gap * 266, "L"), new Beat(startTime + gap * 275, "S"),
					new Beat(startTime + gap * 275, "F"), new Beat(startTime + gap * 284, "J"),
					new Beat(startTime + gap * 284, "L"), new Beat(startTime + gap * 293, "Space"),
					new Beat(startTime + gap * 302, "S"), new Beat(startTime + gap * 311, "F"),
					new Beat(startTime + gap * 320, "J"), new Beat(startTime + gap * 329, "L"),
					new Beat(startTime + gap * 338, "S"), new Beat(startTime + gap * 347, "F"),
					new Beat(startTime + gap * 356, "J"), new Beat(startTime + gap * 365, "L"),
					new Beat(startTime + gap * 374, "Space"), new Beat(startTime + gap * 383, "L"),
					new Beat(startTime + gap * 392, "J"), new Beat(startTime + gap * 401, "F"),
					new Beat(startTime + gap * 410, "S"), new Beat(startTime + gap * 419, "L"),
					new Beat(startTime + gap * 428, "J"), new Beat(startTime + gap * 437, "F"),
					new Beat(startTime + gap * 446, "S"), new Beat(startTime + gap * 455, "Space"),
					new Beat(startTime + gap * 464, "S"), new Beat(startTime + gap * 473, "L"),
					new Beat(startTime + gap * 482, "F"), new Beat(startTime + gap * 491, "J"),
					new Beat(startTime + gap * 500, "S"), new Beat(startTime + gap * 509, "L"),
					new Beat(startTime + gap * 518, "F"), new Beat(startTime + gap * 527, "J"),
					new Beat(startTime + gap * 536, "Space"), new Beat(startTime + gap * 545, "F"),
					new Beat(startTime + gap * 554, "J"), new Beat(startTime + gap * 563, "S"),
					new Beat(startTime + gap * 572, "L"), new Beat(startTime + gap * 581, "F"),
					new Beat(startTime + gap * 590, "J"), new Beat(startTime + gap * 599, "S"),
					new Beat(startTime + gap * 608, "L"), new Beat(startTime + gap * 628, ""),
//					new Beat(startTime + gap * 630, ""), 
					};
			all = beats.length * 50;
		} else if (titleName.equals("osh-va - Chilling") && difficulty.equals("Hard")) {
			int startTime = 0;
			int gap = 125;
			beats = new Beat[] { new Beat(startTime, "S"), new Beat(startTime + gap * 3, "K"),
					new Beat(startTime + gap * 6, "D"), new Beat(startTime + gap * 9, "J"),
					new Beat(startTime + gap * 12, "Space"), new Beat(startTime + gap * 15, "J"),
					new Beat(startTime + gap * 15, "K"), new Beat(startTime + gap * 18, "L"),
					new Beat(startTime + gap * 18, "D"), new Beat(startTime + gap * 21, "J"),
					new Beat(startTime + gap * 21, "F"), new Beat(startTime + gap * 24, "L"),
					new Beat(startTime + gap * 24, "S"), new Beat(startTime + gap * 27, "Space"),
					new Beat(startTime + gap * 30, "D"), new Beat(startTime + gap * 30, "L"),
					new Beat(startTime + gap * 33, "K"), new Beat(startTime + gap * 33, "S"),
					new Beat(startTime + gap * 36, "Space"), new Beat(startTime + gap * 39, "S"),
					new Beat(startTime + gap * 42, "F"), new Beat(startTime + gap * 45, "D"),
					new Beat(startTime + gap * 48, "J"), new Beat(startTime + gap * 51, "Space"),
					new Beat(startTime + gap * 54, "J"), new Beat(startTime + gap * 54, "K"),
					new Beat(startTime + gap * 57, "S"), new Beat(startTime + gap * 57, "D"),
					new Beat(startTime + gap * 57, "S"), new Beat(startTime + gap * 63, "L"),
					new Beat(startTime + gap * 66, "Space"), new Beat(startTime + gap * 69, "D"),
					new Beat(startTime + gap * 72, "K"), new Beat(startTime + gap * 75, "Space"),
					new Beat(startTime + gap * 78, "S"), new Beat(startTime + gap * 78, "F"),
					new Beat(startTime + gap * 81, "J"), new Beat(startTime + gap * 81, "L"),
					new Beat(startTime + gap * 84, "S"), new Beat(startTime + gap * 84, "F"),
					new Beat(startTime + gap * 87, "J"), new Beat(startTime + gap * 87, "L"),
					new Beat(startTime + gap * 90, "Space"), new Beat(startTime + gap * 93, "S"),
					new Beat(startTime + gap * 96, "F"), new Beat(startTime + gap * 99, "S"),
					new Beat(startTime + gap * 102, "F"), new Beat(startTime + gap * 105, "Space"),
					new Beat(startTime + gap * 108, "S"), new Beat(startTime + gap * 108, "F"),
					new Beat(startTime + gap * 111, "J"), new Beat(startTime + gap * 111, "L"),
					new Beat(startTime + gap * 114, "S"), new Beat(startTime + gap * 114, "F"),
					new Beat(startTime + gap * 117, "J"), new Beat(startTime + gap * 117, "L"),
					new Beat(startTime + gap * 120, "Space"), new Beat(startTime + gap * 123, "J"),
					new Beat(startTime + gap * 126, "L"), new Beat(startTime + gap * 129, "J"),
					new Beat(startTime + gap * 132, "L"), new Beat(startTime + gap * 135, "Space"),
					new Beat(startTime + gap * 137, "S"), new Beat(startTime + gap * 139, "K"),
					new Beat(startTime + gap * 141, "L"), new Beat(startTime + gap * 143, "D"),
					new Beat(startTime + gap * 145, "J"), new Beat(startTime + gap * 147, "F"),
					new Beat(startTime + gap * 149, "S"), new Beat(startTime + gap * 151, "L"),
					new Beat(startTime + gap * 153, "Space"), new Beat(startTime + gap * 155, "D"),
					new Beat(startTime + gap * 157, "J"), new Beat(startTime + gap * 159, "K"),
					new Beat(startTime + gap * 161, "S"), new Beat(startTime + gap * 163, "L"),
					new Beat(startTime + gap * 165, "J"), new Beat(startTime + gap * 167, "S"),
					new Beat(startTime + gap * 169, "F"), new Beat(startTime + gap * 171, "Space"),
					new Beat(startTime + gap * 173, "D"), new Beat(startTime + gap * 175, "K"),
					new Beat(startTime + gap * 177, "D"), new Beat(startTime + gap * 179, "K"),
					new Beat(startTime + gap * 181, "Space"), new Beat(startTime + gap * 183, "S"),
					new Beat(startTime + gap * 183, "K"), new Beat(startTime + gap * 185, "F"),
					new Beat(startTime + gap * 185, "S"), new Beat(startTime + gap * 187, "S"),
					new Beat(startTime + gap * 187, "L"), new Beat(startTime + gap * 189, "F"),
					new Beat(startTime + gap * 189, "L"), new Beat(startTime + gap * 191, "Space"),
					new Beat(startTime + gap * 193, "J"), new Beat(startTime + gap * 195, "L"),
					new Beat(startTime + gap * 197, "J"), new Beat(startTime + gap * 199, "L"),
					new Beat(startTime + gap * 201, "Space"), new Beat(startTime + gap * 203, "D"),
					new Beat(startTime + gap * 205, "K"), new Beat(startTime + gap * 207, "D"),
					new Beat(startTime + gap * 209, "K"), new Beat(startTime + gap * 211, "Space"),
					new Beat(startTime + gap * 213, "S"), new Beat(startTime + gap * 213, "F"),
					//
					new Beat(startTime + gap * 215, "D"), new Beat(startTime + gap * 217, "K"),
					new Beat(startTime + gap * 219, "L"), new Beat(startTime + gap * 222, "J"),
					new Beat(startTime + gap * 222, "L"),
					//
					new Beat(startTime + gap * 224, "F"), new Beat(startTime + gap * 226, "J"),
					new Beat(startTime + gap * 228, "K"), new Beat(startTime + gap * 228, "D"),
					new Beat(startTime + gap * 230, "Space"), new Beat(startTime + gap * 231, "S"),
					new Beat(startTime + gap * 231, "F"),
					//
					new Beat(startTime + gap * 233, "F"), new Beat(startTime + gap * 233, "K"),
					new Beat(startTime + gap * 235, "S"), new Beat(startTime + gap * 237, "L"),
					new Beat(startTime + gap * 239, "Space"), new Beat(startTime + gap * 240, "J"),
					new Beat(startTime + gap * 240, "L"),
					//
					new Beat(startTime + gap * 242, "F"), new Beat(startTime + gap * 244, "K"),
					new Beat(startTime + gap * 246, "S"), new Beat(startTime + gap * 248, "L"),
					new Beat(startTime + gap * 249, "Space"),

					new Beat(startTime + gap * 251, "K"), new Beat(startTime + gap * 253, "S"),
					new Beat(startTime + gap * 255, "F"), new Beat(startTime + gap * 255, "L"),

					new Beat(startTime + gap * 257, "S"), new Beat(startTime + gap * 257, "F"),

					new Beat(startTime + gap * 259, "J"), new Beat(startTime + gap * 261, "S"),
					new Beat(startTime + gap * 263, "D"), new Beat(startTime + gap * 265, "Space"),

					new Beat(startTime + gap * 266, "J"), new Beat(startTime + gap * 266, "L"),

					new Beat(startTime + gap * 268, "F"), new Beat(startTime + gap * 268, "S"),
					new Beat(startTime + gap * 270, "K"), new Beat(startTime + gap * 270, "D"),
					new Beat(startTime + gap * 272, "L"), new Beat(startTime + gap * 274, "Space"),

					new Beat(startTime + gap * 275, "S"), new Beat(startTime + gap * 275, "F"),

					new Beat(startTime + gap * 277, "S"), new Beat(startTime + gap * 279, "D"),
					new Beat(startTime + gap * 281, "F"),

					new Beat(startTime + gap * 284, "J"), new Beat(startTime + gap * 284, "L"),

					new Beat(startTime + gap * 286, "J"), new Beat(startTime + gap * 288, "K"),
					new Beat(startTime + gap * 290, "L"),

					new Beat(startTime + gap * 293, "Space"),

					new Beat(startTime + gap * 295, "S"), new Beat(startTime + gap * 295, "K"),
					new Beat(startTime + gap * 297, "D"), new Beat(startTime + gap * 299, "F"),
					new Beat(startTime + gap * 299, "J"), new Beat(startTime + gap * 301, "Space"),

					new Beat(startTime + gap * 302, "S"),

					new Beat(startTime + gap * 304, "K"), new Beat(startTime + gap * 306, "L"),
					new Beat(startTime + gap * 308, "D"),

					new Beat(startTime + gap * 311, "F"),

					new Beat(startTime + gap * 313, "K"), new Beat(startTime + gap * 315, "L"),
					new Beat(startTime + gap * 317, "D"), new Beat(startTime + gap * 319, "Space"),

					new Beat(startTime + gap * 320, "J"),

					new Beat(startTime + gap * 322, "F"), new Beat(startTime + gap * 324, "S"),
					new Beat(startTime + gap * 326, "J"), new Beat(startTime + gap * 328, "Space"),

					new Beat(startTime + gap * 329, "L"),

					new Beat(startTime + gap * 331, "F"), new Beat(startTime + gap * 333, "S"),
					new Beat(startTime + gap * 335, "J"), new Beat(startTime + gap * 337, "Space"),

					new Beat(startTime + gap * 338, "S"),

					new Beat(startTime + gap * 340, "F"), new Beat(startTime + gap * 340, "K"),
					new Beat(startTime + gap * 342, "S"), new Beat(startTime + gap * 342, "F"),
					new Beat(startTime + gap * 344, "J"), new Beat(startTime + gap * 346, "F"),

					new Beat(startTime + gap * 347, "F"),

					new Beat(startTime + gap * 349, "D"), new Beat(startTime + gap * 351, "F"),
					new Beat(startTime + gap * 353, "K"), new Beat(startTime + gap * 355, "Space"),

					new Beat(startTime + gap * 356, "J"),

					new Beat(startTime + gap * 358, "J"), new Beat(startTime + gap * 360, "L"),
					new Beat(startTime + gap * 362, "S"), new Beat(startTime + gap * 364, "Space"),

					new Beat(startTime + gap * 365, "L"),

					new Beat(startTime + gap * 367, "D"), new Beat(startTime + gap * 367, "K"),
					new Beat(startTime + gap * 369, "F"), new Beat(startTime + gap * 369, "L"),
					new Beat(startTime + gap * 371, "K"), new Beat(startTime + gap * 371, "J"),
					new Beat(startTime + gap * 373, "Space"),

					new Beat(startTime + gap * 374, "Space"),

					new Beat(startTime + gap * 376, "J"), new Beat(startTime + gap * 378, "L"),
					new Beat(startTime + gap * 380, "S"), new Beat(startTime + gap * 382, "Space"),

					new Beat(startTime + gap * 383, "L"),

					new Beat(startTime + gap * 385, "K"), new Beat(startTime + gap * 387, "S"),
					new Beat(startTime + gap * 389, "L"), new Beat(startTime + gap * 391, "Space"),

					new Beat(startTime + gap * 392, "J"),

					new Beat(startTime + gap * 394, "D"), new Beat(startTime + gap * 396, "F"),
					new Beat(startTime + gap * 398, "S"), new Beat(startTime + gap * 400, "Space"),

					new Beat(startTime + gap * 401, "F"),

					new Beat(startTime + gap * 403, "K"), new Beat(startTime + gap * 405, "L"),
					new Beat(startTime + gap * 407, "J"), new Beat(startTime + gap * 409, "Space"),

					new Beat(startTime + gap * 410, "S"),

					new Beat(startTime + gap * 412, "K"), new Beat(startTime + gap * 414, "S"),
					new Beat(startTime + gap * 416, "D"), new Beat(startTime + gap * 418, "Space"),

					new Beat(startTime + gap * 419, "L"),

					new Beat(startTime + gap * 421, "S"), new Beat(startTime + gap * 423, "K"),
					new Beat(startTime + gap * 425, "J"), new Beat(startTime + gap * 427, "Space"),

					new Beat(startTime + gap * 428, "J"),

					new Beat(startTime + gap * 430, "K"), new Beat(startTime + gap * 430, "D"),
					new Beat(startTime + gap * 432, "L"), new Beat(startTime + gap * 432, "F"),
					new Beat(startTime + gap * 434, "J"), new Beat(startTime + gap * 434, "S"),
					new Beat(startTime + gap * 436, "Space"),

					new Beat(startTime + gap * 437, "F"),

					new Beat(startTime + gap * 439, "D"), new Beat(startTime + gap * 441, "S"),
					new Beat(startTime + gap * 443, "F"), new Beat(startTime + gap * 445, "Space"),

					new Beat(startTime + gap * 446, "S"),

					new Beat(startTime + gap * 448, "K"), new Beat(startTime + gap * 450, "J"),
					new Beat(startTime + gap * 452, "L"), new Beat(startTime + gap * 454, "Space"),

					new Beat(startTime + gap * 455, "Space"),

					new Beat(startTime + gap * 457, "S"), new Beat(startTime + gap * 459, "K"),
					new Beat(startTime + gap * 461, "J"), new Beat(startTime + gap * 463, "Space"),

					new Beat(startTime + gap * 464, "S"),

					new Beat(startTime + gap * 466, "D"), new Beat(startTime + gap * 468, "K"),
					new Beat(startTime + gap * 470, "D"), new Beat(startTime + gap * 472, "Space"),

					new Beat(startTime + gap * 473, "L"),

					new Beat(startTime + gap * 475, "K"), new Beat(startTime + gap * 477, "D"),
					new Beat(startTime + gap * 479, "K"), new Beat(startTime + gap * 481, "Space"),

					new Beat(startTime + gap * 482, "F"),

					new Beat(startTime + gap * 484, "S"), new Beat(startTime + gap * 486, "F"),
					new Beat(startTime + gap * 488, "S"), new Beat(startTime + gap * 490, "Space"),

					new Beat(startTime + gap * 491, "J"),

					new Beat(startTime + gap * 493, "F"), new Beat(startTime + gap * 495, "S"),
					new Beat(startTime + gap * 497, "F"), new Beat(startTime + gap * 499, "Space"),

					new Beat(startTime + gap * 500, "S"),

					new Beat(startTime + gap * 502, "J"), new Beat(startTime + gap * 504, "L"),
					new Beat(startTime + gap * 506, "J"), new Beat(startTime + gap * 508, "Space"),

					new Beat(startTime + gap * 509, "L"),

					new Beat(startTime + gap * 511, "S"), new Beat(startTime + gap * 513, "F"),
					new Beat(startTime + gap * 515, "S"), new Beat(startTime + gap * 517, "Space"),

					new Beat(startTime + gap * 518, "F"),

					new Beat(startTime + gap * 520, "L"), new Beat(startTime + gap * 522, "J"),
					new Beat(startTime + gap * 524, "L"), new Beat(startTime + gap * 526, "Space"),

					new Beat(startTime + gap * 527, "J"),

					new Beat(startTime + gap * 529, "S"), new Beat(startTime + gap * 531, "J"),
					new Beat(startTime + gap * 533, "L"), new Beat(startTime + gap * 524, "Space"),

					new Beat(startTime + gap * 536, "Space"),

					new Beat(startTime + gap * 538, "S"), new Beat(startTime + gap * 538, "F"),
					new Beat(startTime + gap * 538, "K"), new Beat(startTime + gap * 540, "Space"),
					new Beat(startTime + gap * 542, "J"), new Beat(startTime + gap * 542, "L"),
					new Beat(startTime + gap * 542, "D"), new Beat(startTime + gap * 544, "Space"),

					new Beat(startTime + gap * 545, "F"),

					new Beat(startTime + gap * 547, "L"), new Beat(startTime + gap * 549, "L"),
					new Beat(startTime + gap * 551, "L"), new Beat(startTime + gap * 553, "Space"),

					new Beat(startTime + gap * 554, "J"),

					new Beat(startTime + gap * 557, "D"), new Beat(startTime + gap * 559, "D"),
					new Beat(startTime + gap * 561, "D"),

					new Beat(startTime + gap * 563, "S"),

					new Beat(startTime + gap * 565, "D"), new Beat(startTime + gap * 567, "J"),
					new Beat(startTime + gap * 569, "F"), new Beat(startTime + gap * 571, "Space"),

					new Beat(startTime + gap * 572, "L"),

					new Beat(startTime + gap * 574, "K"), new Beat(startTime + gap * 576, "S"),
					new Beat(startTime + gap * 578, "D"), new Beat(startTime + gap * 580, "Space"),

					new Beat(startTime + gap * 581, "F"),

					new Beat(startTime + gap * 583, "D"), new Beat(startTime + gap * 585, "F"),
					new Beat(startTime + gap * 587, "J"), new Beat(startTime + gap * 589, "Space"),

					new Beat(startTime + gap * 590, "J"),

					new Beat(startTime + gap * 592, "D"), new Beat(startTime + gap * 594, "J"),
					new Beat(startTime + gap * 596, "S"), new Beat(startTime + gap * 598, "Space"),

					new Beat(startTime + gap * 599, "S"),

					new Beat(startTime + gap * 601, "S"), new Beat(startTime + gap * 601, "F"),
					new Beat(startTime + gap * 603, "S"), new Beat(startTime + gap * 603, "K"),
					new Beat(startTime + gap * 605, "S"), new Beat(startTime + gap * 605, "L"),
					new Beat(startTime + gap * 607, "Space"),

					new Beat(startTime + gap * 608, "L"),

					new Beat(startTime + gap * 628, ""), };
			all = beats.length * 50;
		}

		else if (titleName.equals("Harmony - Ikson") && difficulty.equals("Hard")) {
			int startTime = 300 - Main.REACH_TIME * 1000;
			int gap = 350;
			int gap2 = 200;
			beats = new Beat[] { new Beat(startTime, "L"), new Beat(startTime + gap * 1, "L"),
					new Beat(startTime + gap * 1, "J"), new Beat(startTime + gap * 2, "Space"),
					new Beat(startTime + gap * 2, "F"), new Beat(startTime + gap * 2, "S"),
					new Beat(startTime + gap * 6, "L"), new Beat(startTime + gap * 6, "Space"),
					new Beat(startTime + gap * 7, "L"), new Beat(startTime + gap * 7, "Space"),
					new Beat(startTime + gap * 9, "L"), new Beat(startTime + gap * 9, "Space"),
					new Beat(startTime + gap * 10, "J"), new Beat(startTime + gap * 10, "F"),
					new Beat(startTime + gap * 12, "L"), new Beat(startTime + gap * 12, "Space"),
					new Beat(startTime + gap * 13, "L"), new Beat(startTime + gap * 13, "Space"),
					new Beat(startTime + gap * 14, "J"), new Beat(startTime + gap * 14, "F"),
					new Beat(startTime + gap * 16, "L"), new Beat(startTime + gap * 16, "K"),
					new Beat(startTime + gap * 17, "L"), new Beat(startTime + gap * 17, "J"),
					new Beat(startTime + gap * 18, "F"), new Beat(startTime + gap * 18, "Space"),
					new Beat(startTime + gap * 20, "L"), new Beat(startTime + gap * 20, "K"),
					new Beat(startTime + gap * 21, "L"), new Beat(startTime + gap * 21, "J"),
					new Beat(startTime + gap * 22, "F"), new Beat(startTime + gap * 22, "Space"),
					new Beat(startTime + gap * 24, "L"), new Beat(startTime + gap * 25, "L"),
					new Beat(startTime + gap * 26, "K"), new Beat(startTime + gap * 26, "Space"),
					new Beat(startTime + gap * 28, "L"), new Beat(startTime + gap * 29, "L"),
					new Beat(startTime + gap * 29, "K"), new Beat(startTime + gap * 30, "J"),
					new Beat(startTime + gap * 30, "Space"), new Beat(startTime + gap * 32, "L"),
					new Beat(startTime + gap * 33, "L"), new Beat(startTime + gap * 34, "K"),
					new Beat(startTime + gap * 34, "Space"), // hard
					new Beat(startTime + gap * 38, "L"), new Beat(startTime + gap * 39, "L"),
					new Beat(startTime + gap * 39, "K"), new Beat(startTime + gap * 40, "J"),
					new Beat(startTime + gap * 40, "Space"), new Beat(startTime + gap2 * 40, "K"),
					new Beat(startTime + gap2 * 41, "J"), new Beat(startTime + gap2 * 42, "F"),
					new Beat(startTime + gap * 42, "K"), new Beat(startTime + gap2 * 43, "D"),
					new Beat(startTime + gap * 43, "J"), new Beat(startTime + gap * 44, "F"),
					new Beat(startTime + gap * 45, "D"), new Beat(startTime + gap * 45, "S"),
					new Beat(startTime + gap * 47, "L"), new Beat(startTime + gap * 48, "L"),
					new Beat(startTime + gap * 48, "K"), new Beat(startTime + gap * 49, "J"),
					new Beat(startTime + gap * 49, "Space"), new Beat(startTime + gap * 55, "L"),
					new Beat(startTime + gap * 57, "L"), new Beat(startTime + gap * 57, "K"),
					new Beat(startTime + gap * 58, "J"), new Beat(startTime + gap * 58, "Space"),
					new Beat(startTime + gap * 65, "L"), new Beat(startTime + gap * 65, "K"),
					new Beat(startTime + gap * 66, "L"), new Beat(startTime + gap * 67, "K"),
					new Beat(startTime + gap * 67, "D"), new Beat(startTime + gap * 67, "Space"),
					new Beat(startTime + gap * 73, "D"), new Beat(startTime + gap * 74, "K"),
					new Beat(startTime + gap * 74, "Space"), new Beat(startTime + gap * 75, "K"),
					new Beat(startTime + gap * 75, "Space"), new Beat(startTime + gap * 76, "D"),
					new Beat(startTime + gap * 77, "K"), new Beat(startTime + gap * 77, "Space"),
					new Beat(startTime + gap * 78, "K"), new Beat(startTime + gap * 78, "Space"),
					new Beat(startTime + gap * 79, "K"), new Beat(startTime + gap * 80, "J"),
					new Beat(startTime + gap * 80, "F"), new Beat(startTime + gap * 81, "Space"),
					new Beat(startTime + gap * 82, "S"), new Beat(startTime + gap * 83, "K"),
					new Beat(startTime + gap * 83, "Space"), new Beat(startTime + gap * 84, "K"),
					new Beat(startTime + gap * 84, "Space"), new Beat(startTime + gap * 85, "S"),
					new Beat(startTime + gap * 86, "K"), new Beat(startTime + gap * 86, "Space"),
					new Beat(startTime + gap * 87, "K"), new Beat(startTime + gap * 87, "Space"),
					new Beat(startTime + gap * 88, "L"), new Beat(startTime + gap * 89, "J"),
					new Beat(startTime + gap * 89, "F"), new Beat(startTime + gap * 90, "J"),
					new Beat(startTime + gap * 90, "F"), new Beat(startTime + gap * 91, "K"),
					new Beat(startTime + gap * 92, "J"), new Beat(startTime + gap * 93, "D"),
					new Beat(startTime + gap * 94, "K"), new Beat(startTime + gap * 94, "Space"),
					new Beat(startTime + gap * 95, "K"), new Beat(startTime + gap * 95, "Space"),
					new Beat(startTime + gap * 96, "D"), new Beat(startTime + gap * 97, "K"),
					new Beat(startTime + gap * 97, "Space"), new Beat(startTime + gap * 98, "K"),
					new Beat(startTime + gap * 98, "Space"), // HARD
					new Beat(startTime + gap * 99, "K"), new Beat(startTime + gap * 100, "J"),
					new Beat(startTime + gap * 101, "Space"), new Beat(startTime + gap * 102, "K"),
					new Beat(startTime + gap * 103, "L"), new Beat(startTime + gap * 103, "Space"),
					new Beat(startTime + gap * 104, "L"), new Beat(startTime + gap * 104, "Space"),
					new Beat(startTime + gap * 106, "J"), new Beat(startTime + gap * 107, "K"),
					new Beat(startTime + gap * 108, "L"), new Beat(startTime + gap * 109, "L"),
					new Beat(startTime + gap * 110, "K"), new Beat(startTime + gap * 110, "J"),
					new Beat(startTime + gap * 111, "D"), new Beat(startTime + gap * 111, "S"),
					new Beat(startTime + gap * 112, "K"), new Beat(startTime + gap * 112, "Space"),
					new Beat(startTime + gap * 113, "K"), new Beat(startTime + gap * 113, "Space"),
					new Beat(startTime + gap * 114, "D"), new Beat(startTime + gap * 114, "S"),
					new Beat(startTime + gap * 115, "Space"), new Beat(startTime + gap * 115, "K"),
					new Beat(startTime + gap * 116, "K"), new Beat(startTime + gap * 116, "Space"),
					new Beat(startTime + gap * 117, "K"), new Beat(startTime + gap * 118, "J"),
					new Beat(startTime + gap * 119, "Space"), new Beat(startTime + gap * 121, "S"),
					new Beat(startTime + gap * 122, "K"), new Beat(startTime + gap * 122, "Space"),
					new Beat(startTime + gap * 123, "K"), new Beat(startTime + gap * 123, "Space"),
					new Beat(startTime + gap * 124, "S"), new Beat(startTime + gap * 125, "K"),
					new Beat(startTime + gap * 125, "Space"), new Beat(startTime + gap * 126, "K"),
					new Beat(startTime + gap * 126, "Space"), // Hard
					new Beat(startTime + gap * 127, "L"), new Beat(startTime + gap * 127, "S"),
					new Beat(startTime + gap * 128, "J"), new Beat(startTime + gap * 128, "K"),
					new Beat(startTime + gap * 129, "J"), new Beat(startTime + gap * 129, "K"),
					new Beat(startTime + gap * 130, "K"), new Beat(startTime + gap * 130, "Space"),
					new Beat(startTime + gap * 130, "L"), new Beat(startTime + gap * 131, "D"),
					new Beat(startTime + gap * 131, "S"), new Beat(startTime + gap * 132, "K"),
					new Beat(startTime + gap * 132, "Space"), new Beat(startTime + gap * 133, "K"),
					new Beat(startTime + gap * 133, "Space"), new Beat(startTime + gap * 134, "D"),
					new Beat(startTime + gap * 135, "K"), new Beat(startTime + gap * 135, "Space"),
					new Beat(startTime + gap * 136, "K"), new Beat(startTime + gap * 136, "Space"),
					new Beat(startTime + gap * 137, "K"), new Beat(startTime + gap * 137, "J"),
					new Beat(startTime + gap * 138, "D"), new Beat(startTime + gap * 138, "S"),
					new Beat(startTime + gap * 139, "Space"), new Beat(startTime + gap * 140, "J"), // 50
					new Beat(startTime + gap * 140, "K"), new Beat(startTime + gap * 141, "K"),
					new Beat(startTime + gap * 142, "L"), new Beat(startTime + gap * 142, "Space"),
					new Beat(startTime + gap * 143, "L"), new Beat(startTime + gap * 143, "Space"),
					new Beat(startTime + gap * 144, "J"), new Beat(startTime + gap * 144, "K"),
					new Beat(startTime + gap * 145, "K"), new Beat(startTime + gap * 146, "L"),
					new Beat(startTime + gap * 146, "Space"), new Beat(startTime + gap * 147, "J"),
					new Beat(startTime + gap * 148, "Space"), new Beat(startTime + gap * 149, "L"),
					new Beat(startTime + gap * 149, "Space"), new Beat(startTime + gap * 152, "L"), // hard UNDER
					new Beat(startTime + gap * 154, "L"), new Beat(startTime + gap * 156, "J"),
					new Beat(startTime + gap * 157, "J"), new Beat(startTime + gap * 158, "J"),
					new Beat(startTime + gap * 159, "J"), new Beat(startTime + gap * 160, "F"),
					new Beat(startTime + gap * 162, "F"), new Beat(startTime + gap * 163, "J"),
					new Beat(startTime + gap * 164, "J"), new Beat(startTime + gap * 165, "J"),
					new Beat(startTime + gap * 166, "J"), new Beat(startTime + gap * 169, "L"),
					new Beat(startTime + gap * 170, "L"), new Beat(startTime + gap * 171, "J"),
					new Beat(startTime + gap * 172, "J"), new Beat(startTime + gap * 173, "J"),
					new Beat(startTime + gap * 174, "J"), new Beat(startTime + gap * 180, ""), };
			all = beats.length * 50;
		} else if (titleName.equals("Harmony - Ikson") && difficulty.equals("Easy")) {
			int startTime = 300 - Main.REACH_TIME * 1000;
			int gap = 350;
			beats = new Beat[] { new Beat(startTime, "L"), new Beat(startTime + gap * 1, "L"),
					new Beat(startTime + gap * 2, "Space"), new Beat(startTime + gap * 6, "Space"),
					new Beat(startTime + gap * 7, "L"), new Beat(startTime + gap * 10, "K"),
					new Beat(startTime + gap * 12, "L"), new Beat(startTime + gap * 14, "J"),
					new Beat(startTime + gap * 16, "L"), new Beat(startTime + gap * 18, "F"),
					new Beat(startTime + gap * 20, "L"), new Beat(startTime + gap * 22, "F"),
					new Beat(startTime + gap * 24, "L"), new Beat(startTime + gap * 26, "K"),
					new Beat(startTime + gap * 28, "L"), new Beat(startTime + gap * 30, "J"),
					new Beat(startTime + gap * 32, "L"), new Beat(startTime + gap * 34, "K"),
					new Beat(startTime + gap * 38, "L"), new Beat(startTime + gap * 40, "J"),
					new Beat(startTime + gap * 42, "K"), new Beat(startTime + gap * 43, "J"),
					new Beat(startTime + gap * 44, "F"), new Beat(startTime + gap * 45, "D"),
					new Beat(startTime + gap * 45, "S"), new Beat(startTime + gap * 47, "L"),
					new Beat(startTime + gap * 49, "J"), new Beat(startTime + gap * 55, "L"),
					new Beat(startTime + gap * 58, "K"), new Beat(startTime + gap * 65, "L"),
					new Beat(startTime + gap * 67, "K"), new Beat(startTime + gap * 67, "D"),
					new Beat(startTime + gap * 73, "D"), new Beat(startTime + gap * 75, "K"),
					new Beat(startTime + gap * 76, "D"), new Beat(startTime + gap * 78, "K"),
					new Beat(startTime + gap * 79, "K"), new Beat(startTime + gap * 80, "J"),
					new Beat(startTime + gap * 81, "Space"), new Beat(startTime + gap * 82, "S"),
					new Beat(startTime + gap * 84, "K"), new Beat(startTime + gap * 87, "K"),
					new Beat(startTime + gap * 88, "L"), new Beat(startTime + gap * 89, "J"),
					new Beat(startTime + gap * 90, "J"), new Beat(startTime + gap * 91, "K"),
					new Beat(startTime + gap * 92, "J"), new Beat(startTime + gap * 93, "D"),
					new Beat(startTime + gap * 95, "K"), new Beat(startTime + gap * 96, "D"),
					new Beat(startTime + gap * 99, "K"), new Beat(startTime + gap * 100, "J"),
					new Beat(startTime + gap * 101, "Space"), new Beat(startTime + gap * 102, "K"),
					new Beat(startTime + gap * 103, "L"), new Beat(startTime + gap * 104, "L"),
					new Beat(startTime + gap * 106, "J"), new Beat(startTime + gap * 107, "K"),
					new Beat(startTime + gap * 108, "L"), new Beat(startTime + gap * 109, "L"),
					new Beat(startTime + gap * 110, "J"), new Beat(startTime + gap * 111, "D"),
					new Beat(startTime + gap * 113, "K"), new Beat(startTime + gap * 114, "D"),
					new Beat(startTime + gap * 116, "K"), new Beat(startTime + gap * 117, "K"),
					new Beat(startTime + gap * 118, "J"), new Beat(startTime + gap * 119, "Space"),
					new Beat(startTime + gap * 121, "S"), new Beat(startTime + gap * 123, "K"),
					new Beat(startTime + gap * 124, "S"), new Beat(startTime + gap * 126, "K"),
					new Beat(startTime + gap * 127, "L"), new Beat(startTime + gap * 128, "J"),
					new Beat(startTime + gap * 129, "J"), new Beat(startTime + gap * 130, "K"),
					new Beat(startTime + gap * 130, "L"), new Beat(startTime + gap * 131, "D"),
					new Beat(startTime + gap * 133, "K"), new Beat(startTime + gap * 134, "D"),
					new Beat(startTime + gap * 136, "K"), new Beat(startTime + gap * 137, "K"),
					new Beat(startTime + gap * 138, "J"), new Beat(startTime + gap * 139, "Space"),
					new Beat(startTime + gap * 140, "J"), // 50
					new Beat(startTime + gap * 141, "K"), new Beat(startTime + gap * 142, "L"),
					new Beat(startTime + gap * 143, "L"), new Beat(startTime + gap * 144, "J"),
					new Beat(startTime + gap * 145, "K"), new Beat(startTime + gap * 146, "L"),
					new Beat(startTime + gap * 147, "J"), new Beat(startTime + gap * 148, "Space"),
					new Beat(startTime + gap * 149, "L"), new Beat(startTime + gap * 152, "L"), // 60
					new Beat(startTime + gap * 154, "L"), new Beat(startTime + gap * 156, "J"),
					new Beat(startTime + gap * 158, "J"), new Beat(startTime + gap * 160, "F"),
					new Beat(startTime + gap * 162, "F"), new Beat(startTime + gap * 163, "J"),
					new Beat(startTime + gap * 165, "J"), new Beat(startTime + gap * 169, "L"),
					new Beat(startTime + gap * 170, "L"), new Beat(startTime + gap * 171, "J"),
					new Beat(startTime + gap * 173, "J"), new Beat(startTime + gap * 180, ""), };
			all = beats.length * 50;
		}

		else if (titleName.equals("Good For You -THBD") && difficulty.equals("Easy")) {
			int startTime = 1770;
			int gap = 125;

			beats = new Beat[] { new Beat(startTime - 350, "S"), new Beat(startTime + gap * 30, "D"),
					new Beat(startTime + gap * 60, "J"), new Beat(startTime + gap * 93, "S"),

					new Beat(startTime + gap * 125, "L"), new Beat(startTime + gap * 138, "K"),
					new Beat(startTime + gap * 155, "S"), new Beat(startTime + gap * 170, "D"),

					new Beat(startTime + gap * 187, "J"), new Beat(startTime + gap * 195, "L"),
					new Beat(startTime + gap * 200, "Space"), new Beat(startTime + gap * 218, "L"),
					new Beat(startTime + gap * 233, "S"),

					new Beat(startTime + gap * 250, "D"), new Beat(startTime + gap * 258, "Space"),
					new Beat(startTime + gap * 263, "K"), new Beat(startTime + gap * 281, "F"),
					new Beat(startTime + gap * 295, "D"),

					new Beat(startTime + gap * 313, "D"), new Beat(startTime + gap * 321, "Space"),
					new Beat(startTime + gap * 326, "K"), new Beat(startTime + gap * 345, "F"),
					new Beat(startTime + gap * 359, "D"), // 45

					new Beat(startTime + gap * 376, "D"), new Beat(startTime + gap * 388, "K"),
					new Beat(startTime + gap * 407, "F"), new Beat(startTime + gap * 421, "D"),

					new Beat(startTime + gap * 439, "D"), new Beat(startTime + gap * 452, "K"),
					new Beat(startTime + gap * 471, "F"), new Beat(startTime + gap * 484, "D"), // 1ºĞÂë

					new Beat(startTime + gap * 502, "D"), new Beat(startTime + gap * 509, "Space"),
					new Beat(startTime + gap * 524, "K"), new Beat(startTime + gap * 533, "F"),
					new Beat(startTime + gap * 546, "D"),

					new Beat(startTime + gap * 565, "D"), new Beat(startTime + gap * 572, "S"),
					new Beat(startTime + gap * 576, "F"),

					new Beat(startTime + gap * 595, "D"), new Beat(startTime + gap * 605, "S"),
					new Beat(startTime + gap * 608, "F"),

					new Beat(startTime + gap * 628, "D"), new Beat(startTime + gap * 635, "Space"),
					new Beat(startTime + gap * 650, "K"), new Beat(startTime + gap * 659, "F"),
					new Beat(startTime + gap * 672, "D"),

					new Beat(startTime + gap * 691, "D"), new Beat(startTime + gap * 698, "S"),
					new Beat(startTime + gap * 702, "F"),

					new Beat(startTime + gap * 723, "D"), new Beat(startTime + gap * 730, "S"),
					new Beat(startTime + gap * 736, "F"),

					// result
					new Beat(startTime + gap * 760, ""),

			};

			all = beats.length * 50;

		} else if (titleName.equals("Good For You -THBD") && difficulty.equals("Hard")) {
			int startTime = 1770;
			int gap = 125;
			beats = new Beat[] { new Beat(startTime - 350, "S"), new Beat(startTime + gap * 10, "K"),
					new Beat(startTime + gap * 30, "D"), new Beat(startTime + gap * 44, "Space"),
					new Beat(startTime + gap * 45, "K"), new Beat(startTime + gap * 46, "F"),

					new Beat(startTime + gap * 60, "J"), new Beat(startTime + gap * 75, "S"),
					new Beat(startTime + gap * 92, "Space"), new Beat(startTime + gap * 107, "L"),
					new Beat(startTime + gap * 108, "K"), new Beat(startTime + gap * 109, "J"),

					new Beat(startTime + gap * 125, "L"), new Beat(startTime + gap * 138, "K"),
					new Beat(startTime + gap * 155, "S"), new Beat(startTime + gap * 170, "F"),
					new Beat(startTime + gap * 171, "D"), new Beat(startTime + gap * 172, "S"),

					new Beat(startTime + gap * 187, "J"), new Beat(startTime + gap * 195, "L"),
					new Beat(startTime + gap * 200, "Space"), new Beat(startTime + gap * 218, "L"),
					new Beat(startTime + gap * 233, "S"), new Beat(startTime + gap * 234, "D"),
					new Beat(startTime + gap * 235, "F"),

					new Beat(startTime + gap * 250, "D"), new Beat(startTime + gap * 258, "Space"),
					new Beat(startTime + gap * 263, "K"), new Beat(startTime + gap * 281, "F"),
					new Beat(startTime + gap * 282, "F"), new Beat(startTime + gap * 285, "Space"),
					new Beat(startTime + gap * 289, "J"), new Beat(startTime + gap * 291, "K"),
					new Beat(startTime + gap * 295, "D"),

					new Beat(startTime + gap * 313, "D"), new Beat(startTime + gap * 321, "Space"),
					new Beat(startTime + gap * 326, "K"), new Beat(startTime + gap * 344, "F"),
					new Beat(startTime + gap * 345, "F"), new Beat(startTime + gap * 347, "K"),
					new Beat(startTime + gap * 352, "L"), new Beat(startTime + gap * 353, "J"),
					new Beat(startTime + gap * 359, "D"), // 45

					new Beat(startTime + gap * 376, "D"), new Beat(startTime + gap * 389, "K"), // ´Ê¾î¼­ ¼öÁ¤
					new Beat(startTime + gap * 407, "F"), new Beat(startTime + gap * 421, "K"),
					new Beat(startTime + gap * 422, "J"), new Beat(startTime + gap * 423, "Space"),

					new Beat(startTime + gap * 439, "D"), new Beat(startTime + gap * 452, "K"),
					new Beat(startTime + gap * 471, "F"), new Beat(startTime + gap * 484, "D"), // 1ºĞÂë
					new Beat(startTime + gap * 485, "F"), new Beat(startTime + gap * 486, "Space"),

					new Beat(startTime + gap * 502, "D"), new Beat(startTime + gap * 509, "Space"),
					new Beat(startTime + gap * 515, "S"), new Beat(startTime + gap * 524, "K"),
					new Beat(startTime + gap * 533, "F"), new Beat(startTime + gap * 534, "F"),
					new Beat(startTime + gap * 537, "L"), new Beat(startTime + gap * 541, "J"),
					new Beat(startTime + gap * 542, "K"), new Beat(startTime + gap * 546, "D"),

					new Beat(startTime + gap * 565, "D"), new Beat(startTime + gap * 567, "F"),
					new Beat(startTime + gap * 568, "K"), new Beat(startTime + gap * 572, "Space"),
					new Beat(startTime + gap * 575, "S"), new Beat(startTime + gap * 577, "F"),

					new Beat(startTime + gap * 596, "D"), new Beat(startTime + gap * 598, "L"),
					new Beat(startTime + gap * 599, "Space"), new Beat(startTime + gap * 604, "S"),
					new Beat(startTime + gap * 606, "F"),

					// ¸¶Áö¸·
					new Beat(startTime + gap * 628, "D"), new Beat(startTime + gap * 635, "Space"),
					new Beat(startTime + gap * 641, "S"), new Beat(startTime + gap * 650, "K"),
					new Beat(startTime + gap * 659, "F"), new Beat(startTime + gap * 660, "F"),
					new Beat(startTime + gap * 663, "L"), new Beat(startTime + gap * 667, "J"),
					new Beat(startTime + gap * 668, "K"), new Beat(startTime + gap * 672, "D"),

					new Beat(startTime + gap * 691, "D"), new Beat(startTime + gap * 693, "F"),
					new Beat(startTime + gap * 694, "K"), new Beat(startTime + gap * 698, "S"),
					new Beat(startTime + gap * 702, "F"),

					new Beat(startTime + gap * 723, "D"), new Beat(startTime + gap * 729, "S"),
					new Beat(startTime + gap * 731, "K"), new Beat(startTime + gap * 736, "F"),
					new Beat(startTime + gap * 755, ""), };

			all = beats.length * 50;

		}

		int i = 0;
		gameMusic.start();
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		// °ÔÀÓ ³¡³µ´ÂÁö È®ÀÎ result
		if (i >= beats.length) {
			isGameEnd = true;
		}
	}

	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}

	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {
			blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlareImage.png")).getImage();
		}
		if (judge.equals("Miss")) {
			count = 0;
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
			System.out.println(count);
		} else if (judge.equals("Late")) {
			count++;
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeLate.png")).getImage();
			System.out.println(count);
		} else if (judge.equals("Good")) {
			count++;
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
			int temp = Integer.parseInt(score);
			temp += 10;
			score = Integer.toString(temp);
			System.out.println(count);
		} else if (judge.equals("Great")) {
			count++;
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGreat.png")).getImage();
			int temp = Integer.parseInt(score);
			temp += 30;
			score = Integer.toString(temp);
			System.out.println(count);

		} else if (judge.equals("Perfect")) {
			count++;
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();
			int temp = Integer.parseInt(score);
			temp += 50;
			score = Integer.toString(temp);
			System.out.println(count);
		} else if (judge.equals("Early")) {
			count++;
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeEarly.png")).getImage();
			System.out.println(count);
		}

	}
}
