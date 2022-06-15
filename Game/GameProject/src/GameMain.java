import game_interface.Breakout;
import game_observer.ObserverTrigger;
import java_socket.GameClient;

import java.awt.*;

public class GameMain extends Thread{

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var game = new Breakout();
            game.setVisible(true);
            //var game_observer = new ObserverTrigger();
            //game_observer.setVisible(true);
        });
    }
}
