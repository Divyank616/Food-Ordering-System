package com.example.assignment_4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import com.example.assignment_4.model.*;
import com.example.assignment_4.User.*;
import com.example.assignment_4.Utils.*;
import com.example.assignment_4.main.*;
import java.util.List;

public class JavaFx_gui extends Application {

        Stage Window;
        Scene scene1, scene2, scene3;
        ObservableList<MenuItems> observableMenuList;
        ObservableList<Order> observablePendingList;
        Menu menu;
        List<Order> Pendinggg;

    public List<Order> getPendings(List<Order> Pendings){
        return Pendings;
    }

        public void start(Stage primarystage){
            Window=primarystage;
            menu = DataInitializer.initializeMenu();
            Pendinggg = FileHandler.readPendings();
            observableMenuList = FXCollections.observableArrayList(menu.getItems());
            observablePendingList=FXCollections.observableArrayList(getPendings(Pendinggg));


            Label label1=new Label("Welcome to the canteen system");
            label1.setLayoutX(175);
            label1.setLayoutY(125);

            ListView<MenuItems> menuListView = new ListView<>(observableMenuList);
            menuListView.setPrefSize(400, 300);
            menuListView.setLayoutX(50);
            menuListView.setLayoutY(50);

            ListView<Order> PendingListView = new ListView<>(observablePendingList);
            PendingListView.setPrefSize(400, 300);
            PendingListView.setLayoutX(50);
            PendingListView.setLayoutY(50);


            Button b1=new Button("Menu");
            b1.setLayoutX(235);
            b1.setLayoutY(220);
            b1.setOnAction(e->Window.setScene(scene2));

            Button b2=new Button("Pending Order");
            b2.setLayoutX(210);
            b2.setLayoutY(300);
            b2.setOnAction(e->Window.setScene(scene3));

            Button b3 = new Button("Back");
            b3.setLayoutX(235);
            b3.setLayoutY(400);
            b3.setOnAction(e -> Window.setScene(scene1));

            Button b4=new Button("Back");
            b4.setLayoutX(235);
            b4.setLayoutY(400);
            b4.setOnAction(e->Window.setScene(scene1));

            Pane layout1=new Pane();
            layout1.getChildren().addAll(b1,b2,label1);

            Pane layout2 = new Pane();
            layout2.getChildren().addAll(menuListView, b3);

            Pane layout3=new Pane();
            layout3.getChildren().addAll(b4,PendingListView);

            scene1=new Scene(layout1,500,500);
            scene2=new Scene(layout2,500,500);
            scene3=new Scene(layout3,500,500);

            Window.setScene(scene1);
            Window.setTitle("Canteen System!");
            observablePendingList.clear();
            Window.show();
        }

    public static void main(String[] args) {
        launch();
    }
}