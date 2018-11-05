package sungshin_beat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

// Thread : 하나의 작은 프로그램
public class Music extends Thread{
	
	// 다운받은 자바줌의 라이브러리
	private Player player;
	private Boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	// 생성자
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI());
			
			// 해당 파일 읽어올 수 있도록
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			
			// 해당 파일 담을 수 있도록
			player = new Player(bis);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 현재 실행중인 음악의 현재 실행위치
	public int getTime() {
		if(player == null)
			return 0;
		return player.getPosition();
	}
	
	// 항상 종료할 수 있도록
	public void close() {
		isLoop = false;
		player.close();
		// 곡을 실행해주는 프로그램은 따로임 그걸 종료해주는게 interrupt
		this.interrupt();
	}
	
	// thread 상속받으면 무조건 사용
	@Override
	public void run() {
		try {
			// 곡실행
			do {
				player.play();
				// 해당 파일 읽어올 수 있도록
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				
				// 해당 파일 담을 수 있도록
				player = new Player(bis);
			// isLoop가 true 면 무한반복
			} while(isLoop);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
