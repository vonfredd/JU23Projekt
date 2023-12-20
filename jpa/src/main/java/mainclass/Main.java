package mainclass;

import jakarta.persistence.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.JPAUtil;

import java.util.function.Consumer;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
        boolean isRunning = true;
        while (isRunning) {
            Menu.showMain();
            int menuChoice = UserInputHandler.menuInput(7);
            switch (menuChoice) {
                case 0 -> isRunning = false;
                case 1 -> Menu.create();
                case 2 -> Menu.read();
                case 3 -> Menu.count();
                case 4 -> Menu.update();
                case 5 -> Menu.delete();
                case 6 -> Menu.testMenu();
            }
        }
    }

    public static void inTransaction(Consumer<EntityManager> work) {
        try (EntityManager entityManager = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                work.accept(entityManager);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml")); // Change to your FXML file
        primaryStage.setTitle("JavaFX App");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}