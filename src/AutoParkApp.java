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
                            cart.put(i, cart.getOrDefault(i, 0) + 1); // Add to cart
                            if (i.getInvQuantity() == cart.get(i)) {
                                i.setInvQuantity(0); // Set quantity to 0 when added completely
                            }
                            view.update(model, cart); // Update the view
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
                            view.update(model, cart);
                            break;
                        }
                    }
               // }
            }
        });

    }
    public static void main(String[] args){
        launch(args);
    }
}
