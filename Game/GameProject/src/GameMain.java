import game_interface.Breakout;
import game_observer.ObserverTrigger;
import java_socket.GameClient;

import java.awt.*;
import java.util.Scanner;

public class GameMain extends Thread{

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter 1 to execute the game ");
            int input = sc.nextInt();
            if(input == 1){
                var game = new Breakout();
                game.setVisible(true);
            }else{
                var game_observer = new ObserverTrigger();
                game_observer.setVisible(true);
            }
        });
    }
}
