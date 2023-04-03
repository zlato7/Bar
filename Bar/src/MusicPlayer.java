

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//using JLayer
public class MusicPlayer {

  public void turnTheMusic(String musicLocation) {

    try {

      File song = new File(musicLocation);

      AudioInputStream audioInput = AudioSystem.getAudioInputStream(song);
      Clip clip = AudioSystem.getClip();
      clip.open(audioInput);
      clip.start();
      clip.loop(Clip.LOOP_CONTINUOUSLY);

      // clip.stop();
    } catch (Exception ex) {
      System.err.println("Can't find the music file!");
    }
  }

  public String musicLocation(Band band) {

    switch (band.name()) {

      case "SLAYER":
        return "src/Music/Slayer - Dead Skin Mask.wav";

      case "SEPULTURA":
        return "src/Music/Sepultura - Slave New World.wav";

      case "BLOODBATH":
        return "src/Music/Bloodbath - Eaten.wav";

      case "BEHEMOTH":
        return "src/Music/Behemoth - Ov Fire and the Void.wav";

      case "SYSTEM_OF_A_DOWN":
        return "src/Music/System Of A Down - Toxicity.wav";

      case "SEPTIC_FLESH":
        return "src/Music/SepticFlesh - Prometheus.wav";

      case "SLIPKNOT":
        return "src/Music/Slipknot - The Devil In I.wav";

      case "MESHUGGAH":
        return "src/Music/MESHUGGAH - New Millennium Cyanide Christ.wav";

      case "JUDAS_PRIEST":
        return "src/Music/Judas Priest - Painkiller.wav";

      case "LAMB_OF_GOD":
        return "src/Music/Lamb of God - Ruin.wav";

      default:
        return "There's no such a band!";
    }
  }
}
