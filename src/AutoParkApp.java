import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.*;
import java.util.*;

public class AutoParkApp extends Application{
    private AutoPark model;
    private final AutoPark model2;
    private Map<Item, Integer> cart;
    private double cartTotal;
    private int numSalesTotal;
    private double revTotal;
    private double avgSaleTotal;

    public AutoParkApp(){
        model = AutoPark.createPark();
        model2 = AutoPark.createPark();
        cartTotal = 0.0;
        numSalesTotal = 0;
        revTotal = 0.0;
        avgSaleTotal = 0.0;
    }

    public double getCartTotal(){
        return cartTotal;
    }

    public Map<Item,Integer> getCart(){
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

        view.update(model, cart);

        view.getInvList().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getAddButton().setDisable(false);

                view.getAddButton().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        for (Item i: model.getItemList()){
                            if (view.getInvList().getSelectionModel().getSelectedItem().equals(i.toString()) & i.getInvQuantity() > cart.getOrDefault(i,0)){
                                cart.put(i, cart.getOrDefault(i,0)+1);
                                if (i.getInvQuantity() == cart.get(i)){
                                    for (int j = 0; j < model.getTotalItem(); j++){
                                        if (model.getItems()[j] == i){
                                            model.getItems()[j] = null;
                                            break;
                                        }
                                    }
                                }
                                view.update(model, cart);
                            }
                        }
                    }
                });
            }
        });
        view.getCartList().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!getCart().isEmpty()){
                    view.getRemoveButton().setDisable(false);
                }
                else {
                    view.getRemoveButton().setDisable(true);
                }
                view.getRemoveButton().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        for (Item i: model.getItemList()){
                            if (i.toString().equals(view.getCartList().getSelectionModel().getSelectedItem())){
                                cart.put(i, cart.getOrDefault(i,0)-1);
                                //cart.get(i) = cart.get(i) - 1;
                                view.update(model, cart);
                            }
                        }
                    }
                });
            }
        });
    }
    public static void main(String[] args){
        launch(args);
    }
}
