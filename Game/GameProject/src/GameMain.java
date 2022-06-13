import game_interface.Breakout;
import java_socket.GameClient;

import java.awt.*;

public class GameMain{
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var game = new Breakout();
            game.setVisible(true);
        });
    }
}
