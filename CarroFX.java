import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        VBox root = new VBox(textArea);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Corrida de Carros com Threads");
        primaryStage.setScene(scene);
        primaryStage.show();

        Carro carro1 = new Carro("Carro A", 800, msg -> textArea.appendText(msg + "\n"));
        Carro carro2 = new Carro("Carro B", 500, msg -> textArea.appendText(msg + "\n"));
        Carro carro3 = new Carro("Carro C", 650, msg -> textArea.appendText(msg + "\n"));

        carro1.setDaemon(true);
        carro2.setDaemon(true);
        carro3.setDaemon(true);

        carro1.start();
        carro2.start();
        carro3.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
 
    public static class Carro extends Thread {
        private String nome;
        private int velocidade;
        private java.util.function.Consumer<String> atualizador;

        public Carro(String nome, int velocidade, java.util.function.Consumer<String> atualizador) {
            this.nome = nome;
            this.velocidade = velocidade;
            this.atualizador = atualizador;
        }

        public void run() {
            for (int distancia = 10; distancia <= 100; distancia += 10) {
                try {
                    Thread.sleep(velocidade);
                    String mensagem = nome + " percorreu " + distancia + " metros";
                    Platform.runLater(() -> atualizador.accept(mensagem));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Platform.runLater(() ->
                atualizador.accept(nome + " terminou a corrida!")
            );
        }
    }
}
