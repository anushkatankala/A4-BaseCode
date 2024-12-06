import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.util.*;

import java.util.Map;

public class AutoParkView extends Pane {

    private ListView<String> invList;
    private ListView<String> cartList;
    private ListView<String> popularList;
    private Button addButton;
    private Button removeButton;
    private Button completeButton;
    private Button resetButton;
    private Label currentCartLabel;
    private TextField salesText;
    private TextField revText;
    private TextField avgSaleText;

    public ListView<String> getInvList() {
        return invList;
    }
    public ListView<String> getCartList() {
        return cartList;
    }
    public ListView<String> getPopularList() {
        return popularList;
    }
    public Button getAddButton(){
        return addButton;
    }
    public Button getRemoveButton(){
        return removeButton;
    }
    public Button getCompleteButton(){
        return completeButton;
    }
    public Button getResetButton(){
        return resetButton;
    }
    public TextField getSalesText(){
        return salesText;
    }
    public TextField getRevText(){
        return revText;
    }
    public TextField getAvgSaleText(){
        return avgSaleText;
    }

    public AutoParkView(){
        Label parkInvLabel = new Label("Park Inventory:");
        parkInvLabel.relocate(20,10);
        currentCartLabel = new Label("Current Cart: ($0.0)");
        currentCartLabel.relocate(300,10);
        Label parkSumLabel = new Label("Park Summary:");
        parkSumLabel.relocate(590,10);
        Label numSalesLabel = new Label("# Sales:");
        numSalesLabel.relocate(595, 40);
        Label revLabel = new Label("Revenue:");
        revLabel.relocate(595,70);
        Label avgSaleLabel = new Label("$ / Sale: ");
        avgSaleLabel.relocate(595, 100);
        Label mostPopularLabel = new Label("Most Popular Items:");
        mostPopularLabel.relocate(590,125);

        invList = new ListView<String>();
        invList.relocate(20,40);
        invList.setPrefSize(275,300);
        cartList = new ListView<String>();
        cartList.relocate(300,40);
        cartList.setPrefSize(275,300);
        popularList = new ListView<String>();
        popularList.relocate(590,150);
        popularList.setPrefSize(190,190);

        addButton = new Button("Add to Cart");
        addButton.relocate(100,360);
        addButton.setPrefSize(120,20);
        addButton.setDisable(true);
        removeButton = new Button("Remove Item");
        removeButton.relocate(315,360);
        removeButton.setPrefSize(120,20);
        removeButton.setDisable(true);
        completeButton = new Button("Complete Sale");
        completeButton.relocate(440,360);
        completeButton.setPrefSize(120,20);
        completeButton.setDisable(true);
        resetButton = new Button("Reset Stock");
        resetButton.relocate(630,360);
        resetButton.setPrefSize(120,20);

        salesText = new TextField();
        salesText.setEditable(false);
        salesText.setText("0");
        salesText.relocate(670,40);
        salesText.setPrefSize(110,20);
        revText = new TextField();
        revText.setEditable(false);
        revText.setText("0.0");
        revText.relocate(670,70);
        revText.setPrefSize(110,20);
        avgSaleText = new TextField();
        avgSaleText.setEditable(false);
        avgSaleText.setText("N/A");
        avgSaleText.relocate(670,100);
        avgSaleText.setPrefSize(110,20);


        getChildren().addAll(parkInvLabel,currentCartLabel,parkSumLabel,numSalesLabel,revLabel,avgSaleLabel,mostPopularLabel,invList,cartList,popularList,salesText,revText,avgSaleText,addButton,removeButton,completeButton,resetButton);

        setPrefSize(800,400);
    }

    public void update(AutoPark model, Map<Item, Integer> cart, double cartTotal){
        ObservableList<String> listOfInv = FXCollections.observableArrayList();
        ObservableList<String> listOfCart = FXCollections.observableArrayList();
        ObservableList<String> listOfPopular = FXCollections.observableArrayList();

        for (Item item : model.getItemList()){
            if (item.getInvQuantity() == cart.getOrDefault(item,0)) {
                continue;
            }
            listOfInv.add(item.toString());
        }
        //invList.getItems().clear();
        invList.setItems(listOfInv);

        for (Map.Entry<Item, Integer> c : cart.entrySet()){
            listOfCart.add(c.getValue() + " x " + c.getKey().toString());
        }
        //cartList.getItems().clear();
        cartList.setItems(listOfCart);

        List<Item> sorting = new ArrayList<>(model.getItemList());
        sorting.sort((i1, i2) -> Integer.compare(i2.getSoldQuantity(), i1.getSoldQuantity()));
        for (int i = 0; i < 3; i++){
            listOfPopular.add(sorting.get(i).toString());
        }
        popularList.setItems(listOfPopular);

        currentCartLabel.setText("Current Cart: ($" + cartTotal + ")");
        completeButton.setDisable(cart.isEmpty());

    }
}

