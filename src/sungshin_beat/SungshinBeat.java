// cont + shift + f : 자동 줄 정리
package sungshin_beat;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
//import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
//import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// cont + shift + o : import
public class SungshinBeat extends JFrame {

	// 그냥 이미지를 띄우면 버퍼링 심함 -> 더블 버퍼링 이용(매순간 이미지 갱신)
	private Image screenImage;
	private Graphics screenGraphic;

	// 이미지 가져오기 (초기화)
	// exit에 마우스 올리면 색 바뀌게
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/startButtonEnteredImage1.png"));
	private ImageIcon endButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/endButtonEnteredImage1.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/startButtonBasicImage1.png"));
	private ImageIcon endButtonBasicImage = new ImageIcon(Main.class.getResource("../images/endButtonBasicImage1.png"));

	// 속도조절
	private ImageIcon plusButtonImage = new ImageIcon(Main.class.getResource("../images/plusButton.png"));
	private ImageIcon minusButtonImage = new ImageIcon(Main.class.getResource("../images/minusButton.png"));

	// mode 버튼
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/Easy.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/Hard.png"));

	// 오른쪽 왼쪽 이동 버튼
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButton.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButton.png"));

	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground(title).jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	// back버튼
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButton.png"));

	// 버튼 추가는 여기도 추가
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton endButton = new JButton(endButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);

	// 속도조절
	private JButton plusButton = new JButton(plusButtonImage);
	private JButton minusButton = new JButton(minusButtonImage);

	private int mouseX, mouseY;

	private boolean isMainScreen = false;
	private boolean isGameScreen = false;

	///// 음악 객체로 넣기 //////
	ArrayList<Track> trackList = new ArrayList<Track>();

	private Image titleImage;
	private Music introMusic = new Music("intro.mp3", true);
	// 선택된 곡의 이미지
	private Image selectedImage;
	private Music selectedMusic;
	// 현재 선택된 곡의 인덱스
	private int nowSelected = 0;

	// Game class - 프로젝트 전체에서 하나
	public static Game game;

	public SungshinBeat() {
		// 로딩이 길어지면 오류가 발생할 수 있어서 맨 윗쪽으로
		// 음악 list
		trackList.add(new Track("Happy Life title image.png", "Happy Life start image.jpg", "Happy Life game image.jpg",
				"Fredji - Happy Life selected.mp3", "Fredji - Happy Life (Game).mp3", "Fredji - Happy Life"));
		trackList.add(new Track("Chilling title image.png", "Chilling start image.jpg", "Chilling game Image.png",
				"Music_cut.mp3", "Music_cut.mp3", "osh-va - Chilling"));
		trackList.add(new Track("Harmony title image.png", "Harmony start image.jpg", "Harmony game image.jpg",
				"Harmony2.mp3", "Harmony3.mp3", "Harmony - Ikson"));
		trackList.add(
				new Track("Good For You title image.png", "Good For You start image.jpg", "Good For You Game Image.png",
						"Lost Stars by Jung Kook.mp3", "Lost Stars by Jung Kook.mp3", "Good For You -THBD"));

		// 실행했을 때 기본적으로 존재하는 메뉴바가 보이지 않음
		setUndecorated(true);
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		backButton.setVisible(false);
		minusButton.setVisible(false);
		plusButton.setVisible(false);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// 정 중앙에 뜨게
		setResizable(false);
		setLocationRelativeTo(null);
		// 게임창을 종료했을 때 프로그램 전체가 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 눈에 게임창이 보이게
		setVisible(true);
		// paintComponent를 했을때 배경이 전부 흰색으로 바뀜
		setBackground(new Color(0, 0, 0, 0));
		// 버튼이나 JLabel같은거 넣었을 때 정말 그 위치 그대로
		setLayout(null);

		addKeyListener(new KeyListener());

		// intro 음악
		introMusic.start();

////////////////// exit버튼 //////////////////
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				// 마우스가 올라갔을 때 손가락 모양으로 바뀌게
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 버튼에 마우스 올리면 소리나게
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});

		add(exitButton);

//////////////////start버튼 //////////////////
		startButton.setBounds(320, 300, 128, 128);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				// 마우스가 올라갔을 때 손가락 모양으로 바뀌게
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 버튼에 마우스 올리면 소리나게
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();

				introMusic.close();

				// 음악삽입 -> 객체로 만들어서 구현
//				Music selectedMusic = new Music("Fools (COVER) By BTS.mp3",true);
//				selectedMusic.start();
				enterMain();

			}

		});

		add(startButton);

//////////////////end버튼 //////////////////
		endButton.setBounds(780, 300, 128, 128);
		endButton.setBorderPainted(false);
		endButton.setContentAreaFilled(false);
		endButton.setFocusPainted(false);
		endButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				endButton.setIcon(endButtonEnteredImage);
				// 마우스가 올라갔을 때 손가락 모양으로 바뀌게
				endButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 버튼에 마우스 올리면 소리나게
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				endButton.setIcon(endButtonBasicImage);
				endButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});

		add(endButton);

//////////////////left버튼 //////////////////
		leftButton.setBounds(240, 310, 64, 64);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				leftButton.setIcon(leftButtonEnteredImage);
//				// 마우스가 올라갔을 때 손가락 모양으로 바뀌게
//				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//				// 버튼에 마우스 올리면 소리나게
//				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
//				buttonEnteredMusic.start();
//			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				selectLeft();
			}
		});

		add(leftButton);

//////////////////right버튼 //////////////////
		rightButton.setBounds(990, 310, 64, 64);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				selectRight();
			}
		});

		add(rightButton);

/////////////////속도조절 -버튼/////////////////////
		minusButton.setBounds(500, 50, 64, 64);
		minusButton.setBorderPainted(false);
		minusButton.setContentAreaFilled(false);
		minusButton.setFocusPainted(false);
		minusButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				minusButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (Main.NOTE_SPEED > 1)
					Main.NOTE_SPEED--;
			}
		});
		add(minusButton);

/////////////////속도조절 +버튼/////////////////////
		plusButton.setBounds(700, 50, 64, 64);
		plusButton.setBorderPainted(false);
		plusButton.setContentAreaFilled(false);
		plusButton.setFocusPainted(false);
		plusButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
//minusButton.setIcon(rightButtonBasicImage);
				plusButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (Main.NOTE_SPEED < 10)
					Main.NOTE_SPEED++;
			}
		});
		add(plusButton);

//////////////////easy버튼 //////////////////
		easyButton.setBounds(357, 580, 250, 70);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {

//			@Override
//			public void mouseExited(MouseEvent e) {
//			
//				
//			}

			@Override
			public void mousePressed(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
//				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
				// easy 구현
				gameStart(nowSelected, "Easy");
			}
		});

		add(easyButton);

//////////////////hard버튼 //////////////////
		hardButton.setBounds(695, 580, 250, 70);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {

//			@Override
//			public void mouseExited(MouseEvent e) {
//				
//
//			}

			@Override
			public void mousePressed(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();

				// hard 구현
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);

//////////////////back버튼 //////////////////
		backButton.setBounds(50, 70, 70, 70);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// 메인 화면으로 돌아가는 이벤트
				Game.isGameEnd = false;
				backMain();
			}
		});
		add(backButton);

///////////////////// 메뉴바 ///////////////////
		// 위치와 크기 정해줌
		menuBar.setBounds(0, 0, 1280, 30);
		// 마우스 위치에 따라 작동하게
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();

			}
		});
		// 메뉴바를 잡고 이동시킬 수 있게 해줌
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		// 글자 깨짐 현상 방지
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		// background를 0,0에 그려줌
		// 역동적인 이미지 그려줄 때
		g.drawImage(background, 0, 0, null);

		// 특정 이미지 그려줌 ( start 누르면 앨범아트 그려줌 )
		if (isMainScreen) {
			g.drawImage(selectedImage, 350, 130, null);
			g.drawImage(titleImage, 350, 450, null);
			// 속도조절
			g.setFont(new Font("Arial", Font.PLAIN, 40));
			g.setColor(Color.BLACK);
			g.drawString(Main.NOTE_SPEED + "", 620, 90);

		}

		if (isGameScreen) {
			game.screenDraw(g);
		}

		if (Game.isGameEnd) {

			result();
			game.screenDraw2(g);
//			g.drawRect(274, 134, 300, 350);
			g.drawImage(selectedImage, 630, 135, 450, 450, null);
		}
		// 항상 존재하는(고정된) 이미지는 paintComponents로 그려줌 ex) JLabel
		// 추가된거 그려줌 (add로 )
		paintComponents(g);
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 매순간 불러와줌
		this.repaint();
	}

	// javazoom.net -> project -> JLayer

	// 선택된 곡의 번호 넣어서 알려줌
	public void selectTrack(int nowSelected) {
		// 음악이 선택되어 있다면 멈춤
		if (selectedMusic != null)
			selectedMusic.close();

		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	// 왼쪽, 오른쪽 버튼 눌렀을 때 넘어가게

	public void selectLeft() {
		if (nowSelected == 0) {
			nowSelected = trackList.size() - 1;
		} else
			nowSelected--;
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1) {
			nowSelected = 0;
		} else
			nowSelected++;
		selectTrack(nowSelected);
	}

	// hard, easy버튼 구현 게임시작!
	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null)
			selectedMusic.close();
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		minusButton.setVisible(false);
		plusButton.setVisible(false);

		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		backButton.setVisible(true);
		isGameScreen = true;

		game = new Game(trackList.get(nowSelected).gettitleName(), difficulty,
				trackList.get(nowSelected).getGameMusic());
		game.start();
		// 메인 프래임에 키보드 포커스 맞춰줌 오류줄여줌
		setFocusable(true);
	}

	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		minusButton.setVisible(true);
		plusButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		backButton.setVisible(false);
		isGameScreen = false;
		backButton.setVisible(false);
		game.close();
		setFocusable(true);

	}

	public void enterMain() {
		startButton.setVisible(false);
		endButton.setVisible(false);

		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();

		isMainScreen = true;

		// 버튼 보이게
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		minusButton.setVisible(true);
		plusButton.setVisible(true);

		introMusic.close();

		// 처음에는 첫째 선택
		selectTrack(0);
	}

	public void result() {
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		background = new ImageIcon(Main.class.getResource("../images/resultImage.png")).getImage();
		backButton.setVisible(true);
		isGameScreen = false;
		Game.isGameEnd = true;
		game.close();

	}
	
	
}
