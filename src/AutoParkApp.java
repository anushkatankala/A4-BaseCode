import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.*;
import java.util.*;

public class AutoParkApp extends Application{
    private AutoParkApp model;
    private Map<Integer, Item> cart;

    public AutoParkApp(){
        AutoPark model = AutoPark.createPark();
    }

    public Map<Integer,Item> getCart(){
        return cart;
    }

    public void start(Stage primaryStage){
        Pane aPane = new Pane();

        AutoParkView view = new AutoParkView();
        aPane.getChildren().add(view);

        cart = new HashMap<>();

        primaryStage.setTitle("VroomVille Vehicle Haven - Sale and Inventory");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();

        //view.update(model);
    }
    public static void main(String[] args){
        launch(args);
    }
}
