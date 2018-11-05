package sungshin_beat;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

	// 어떤 키를 눌렀는지 감지해줌
	@Override
	public void keyPressed(KeyEvent e) {
		if (SungshinBeat.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			SungshinBeat.game.pressS();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			SungshinBeat.game.pressD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			SungshinBeat.game.pressF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			SungshinBeat.game.pressSpace();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			SungshinBeat.game.pressJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			SungshinBeat.game.pressK();
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			SungshinBeat.game.pressL();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (SungshinBeat.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			SungshinBeat.game.releaseS();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			SungshinBeat.game.releaseD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			SungshinBeat.game.releaseF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			SungshinBeat.game.releaseSpace();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			SungshinBeat.game.releaseJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			SungshinBeat.game.releaseK();
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			SungshinBeat.game.releaseL();
		}
	}
}
