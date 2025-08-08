import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private TextArea textArea;

    @Override
    public void start(Stage primaryStage) {
        textArea = new TextArea();
        textArea.setEditable(false);

        VBox root = new VBox(textArea);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Contador e Letras com Threads");
        primaryStage.setScene(scene);
        primaryStage.show();

       
        startNumberThread();
        startLetterThread();
    }

    private void startNumberThread() {
        Thread numberThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                int finalI = i;
                Platform.runLater(() -> textArea.appendText("Número: " + finalI + "\n"));
                try {
                    Thread.sleep(999); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        numberThread.setDaemon(true); 
        numberThread.start();
    }

    private void startLetterThread() {
        Thread letterThread = new Thread(() -> {
            for (char letra = 'a' ; letra <= 'j'; letra++) {
                char finalC = letra;
                Platform.runLater(() -> textArea.appendText("Letra: " + finalC + "\n"));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        letterThread.setDaemon(true);
        letterThread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
