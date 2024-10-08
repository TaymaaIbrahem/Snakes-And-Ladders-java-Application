package view;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.MusicController;

public class MusicButton extends JToggleButton {
    private static MusicController musicController;
    private static MusicButton instance;
    private String iconPath;
    private String iconPath2;

    MusicButton(String iconPath,String iconPath2) {
		this.iconPath=iconPath;
		this.iconPath2=iconPath2;
		
        addActionListener(e -> toggleMusic());
    }



    private void toggleMusic() {
        if (musicController == null) {
            musicController = new MusicController("/music/compressedwallpaper.wav");
        }
        if (musicController.isPlaying()) {
            musicController.stop();
            setMusicButtonIcon(iconPath2);

        } else {
            musicController.play();
            setMusicButtonIcon(iconPath);

        }
    }
    private void setMusicButtonIcon(String iconPath) {
        this.setIcon(new ImageIcon(getClass().getResource(iconPath)));
       
    }
}
