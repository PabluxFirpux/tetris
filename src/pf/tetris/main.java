package pf.tetris;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        //TODO: Añadir pantalla de inicio, de derrota, highscores y cambiar el fondo a algo un pelin mas lindo
        MainScreen mainScreen = new MainScreen();
        //cliGame(controller);
    }

    public static void cliGame(Controller controller) {
        Scanner sc = new Scanner(System.in);
        controller.update();
        System.out.println("1 for right, 2 for left, 3 for rotate, 4 for down");
        int x = Integer.parseInt(sc.nextLine());
        sendCMD(x,controller);
        controller.update();
        while (true) {
            System.out.println("1 for right, 2 for left, 3 for rotate, 4 for down");
            x = Integer.parseInt(sc.nextLine());
            sendCMD(x,controller);
            controller.update();
        }
    }
    public static void sendCMD(int x,Controller controller) {
        switch (x) {
            case 1:
                controller.moveRight();
                break;
            case 2:
                controller.moveLeft();
                break;
            case 3:
                controller.rotate();
                break;
            case 4:
                controller.setDown();
                break;
            case 5:
                System.exit(0);
            default:
                System.out.println("Invalid command");
        }
    }
}
