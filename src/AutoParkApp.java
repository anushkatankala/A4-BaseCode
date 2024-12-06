import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.*;
import java.util.*;

public class AutoParkApp extends Application{
    private AutoPark model;
    private Map<Item, Integer> cart;
    private double cartTotal;
    private int numSalesTotal;
    private double revTotal;
    private double avgSaleTotal;

    public AutoParkApp(){
        model = AutoPark.createPark();
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

        primaryStage.setTitle("VroomVille Vehicle Haven - Sales and Inventory");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();

        view.update(model, cart, cartTotal);

        view.getInvList().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectedItem = view.getInvList().getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    view.getAddButton().setDisable(false); // Enable add button when an item is selected
                } else {
                    view.getAddButton().setDisable(true); // Disable add button if nothing is selected
                }
            }
        });

        // Add button handler
        view.getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String selectedItem = view.getInvList().getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    for (Item i : model.getItemList()) {
                        if (selectedItem.equals(i.toString()) && i.getInvQuantity() > cart.getOrDefault(i, 0)) {
                            cart.put(i, cart.getOrDefault(i, 0) + 1);
                            cartTotal += i.getPrice();
                            view.update(model, cart, cartTotal); // Update the view
                            break; // Exit after adding the item
                        }
                    }
                }
            }
        });


        // Enable/Disable Remove Button Based on Cart Selection
        view.getCartList().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectedItem = view.getCartList().getSelectionModel().getSelectedItem();
                view.getRemoveButton().setDisable(selectedItem == null || getCart().isEmpty());
                //view.getCompleteButton().setDisable(getCart().isEmpty());
                //view.update(model, cart, cartTotal);
            }
        });

        view.getRemoveButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String selectedItem = view.getCartList().getSelectionModel().getSelectedItem();

                //if (selectedItem != null) { // Null check for selectedItem
                    for (Item i : model.getItemList()) {
                        if (selectedItem.split(" ", 3)[2].equals(i.toString())) {
                            cart.put(i, cart.get(i) - 1);

                            // Remove the item if quantity reaches 0
                            if (cart.get(i) == 0) {
                                cart.remove(i);
                            }

                            view.getRemoveButton().setDisable(true);
                            cartTotal -= i.getPrice();
                            view.update(model, cart, cartTotal);
                            break;
                        }
                    }
               // }
            }
        });

        view.getCompleteButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (Map.Entry<Item, Integer> c : cart.entrySet()){
                    for (Item i: model.getItemList()){
                        if (c.getKey().toString().equals(i.toString())){
                            i.sellUnits(c.getValue());
                            break;
                        }
                    }
                }
                revTotal += cartTotal;
                cartTotal = 0.0;
                cart.clear();
                numSalesTotal += 1;
                avgSaleTotal = revTotal / numSalesTotal;
                view.getAvgSaleText().setText(""+avgSaleTotal);
                view.getRevText().setText(""+revTotal);
                view.getSalesText().setText(""+numSalesTotal);
                view.update(model, cart, cartTotal);
            }
        });

        view.getResetButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model = AutoPark.createPark();
                revTotal = 0.0;
                avgSaleTotal = 0.0;
                numSalesTotal = 0;
                cartTotal = 0.0;
                view.getAvgSaleText().setText("N/A");
                view.getRevText().setText(""+revTotal);
                view.getSalesText().setText(""+numSalesTotal);
                view.update(model, cart, cartTotal);
            }
        });

    }
    public static void main(String[] args){
        launch(args);
    }
}
