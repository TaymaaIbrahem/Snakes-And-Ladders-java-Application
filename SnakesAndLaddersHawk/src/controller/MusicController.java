package controller;
import javax.sound.sampled.*;
import java.net.URL;

public class MusicController {
    private Clip clip;
    private boolean isPlaying=false;

    public MusicController(String audioFilePath) {
        try {
            URL audioFileURL = getClass().getResource(audioFilePath);
            if (audioFileURL == null) {
                System.out.println("Audio file not found!");
                return;
            }

            AudioInputStream ais = AudioSystem.getAudioInputStream(audioFileURL);
            clip = AudioSystem.getClip();

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.setFramePosition(0);
                    if (isPlaying) {
                        clip.start();
                    }
                }
            });

            clip.open(ais);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void play() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
            isPlaying = true;
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.setFramePosition(0);
            isPlaying = false;
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
