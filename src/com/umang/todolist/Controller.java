package com.umang.todolist;

import com.umang.todolist.datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea itemDetailsTextArea;
    @FXML
    private Label deadlineLabel;

    public void initialize()
    {
          TodoItem item1 = new TodoItem("Mail Birthday Card",
                                        "Buy a 30th birthday card for John",
                                        LocalDate.of(2020, Month.SEPTEMBER,7));

          TodoItem item2 = new TodoItem("Doctor's Appointment",
                                         "See Dr.Smith at 123 Main Street. Bring Paperwork",
                                        LocalDate.of(2020, Month.OCTOBER,3));

           TodoItem item3 = new TodoItem("Finish Design Proposal for client",
                                         "I promised Mike I will send the website mockups by 10 September",
                                        LocalDate.of(2020, Month.SEPTEMBER,10));

           TodoItem item4 = new TodoItem("Pick up Doug at train station",
                                        "Pick up Doug at train station on 12 September at 5:00pm",
                                        LocalDate.of(2020, Month.SEPTEMBER,12));

           TodoItem item5 = new TodoItem("Pick up dry cleaning",
                                        "The clothes should be ready by wednesday",
                                        LocalDate.of(2020, Month.SEPTEMBER,15));

           todoItems = new ArrayList<TodoItem>();
           todoItems.add(item1);
           todoItems.add(item2);
           todoItems.add(item3);
           todoItems.add(item4);
           todoItems.add(item5);

           todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
               @Override
               public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem todoItem, TodoItem t1) {
                   if(t1!=null)
                   {
                         TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                         itemDetailsTextArea.setText(t1.getDetails());
                       DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                         deadlineLabel.setText(df.format(t1.getDeadline()));
                   }
               }
           });

           todoListView.getItems().setAll(todoItems);
           todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
           todoListView.getSelectionModel().selectFirst();

    }
    @FXML
    public void handelClickListView(){
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().toString());
        //System.out.println(item);
//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("\n\n\n\n");
//        sb.append("Due :");
//        sb.append(item.getDeadline().toString());
//        itemDetailsTextArea.setText(sb.toString());
    }


}
