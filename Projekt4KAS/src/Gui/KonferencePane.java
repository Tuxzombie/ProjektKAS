package Gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import Model.*;
import Service.Service;


public class KonferencePane extends GridPane {
    private TextField txfTitel, txfTema, txfStartDato, txfSlutDato;
    private ListView<Miljøkonference> lvwKonference;
    private TextArea txaAdresse, txaDeltagere;
    
    private final int FIELD_WIDTH = 263;

    public KonferencePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        
        
        Label lblComp = new Label("Miljøkonferencer");
        this.add(lblComp, 0, 0);

        lvwKonference = new ListView<>();
        this.add(lvwKonference, 0, 1, 1, 6);
        lvwKonference.setMinSize(200, 330);
        lvwKonference.setMaxSize(200, 330);
        lvwKonference.getItems().setAll(this.initAllKonfList());
        ChangeListener<Miljøkonference> listener =
            (ov, oldMiljøkonference, newMiljøkonference) -> this.selectedKonferenceChanged();
        lvwKonference.getSelectionModel().selectedItemProperty().addListener(listener);

        Label lblTitel = new Label("Titel:");
        this.add(lblTitel, 1, 1);

        txfTitel = new TextField();
        this.add(txfTitel, 2, 1);
        txfTitel.setPrefWidth(FIELD_WIDTH);
        txfTitel.setEditable(false);

        Label lblTema = new Label("Tema:");
        this.add(lblTema, 1, 2);

        txfTema = new TextField();
        this.add(txfTema, 2, 2);
        txfTema.setEditable(false);

        Label lblStartDato = new Label("Start Dato:");
        this.add(lblStartDato, 1, 3);

        txfStartDato = new TextField();
        this.add(txfStartDato, 2, 3);
        txfStartDato.setEditable(false);

        Label lblSlutDato = new Label("Slut Dato:");
        this.add(lblSlutDato, 1, 4);

        txfSlutDato = new TextField();
        this.add(txfSlutDato, 2, 4);
        txfSlutDato.setEditable(false);
        
        Label lblAdresse = new Label("Adresse:");
        this.add(lblAdresse, 1, 5);
        
        txaAdresse = new TextArea();
        txaAdresse.setMaxSize(FIELD_WIDTH, 80);
        txaAdresse.setMinSize(FIELD_WIDTH, 80);
        this.add(txaAdresse, 2, 5);
        
        Label lblDeltagere = new Label("Deltagere:");
        this.add(lblDeltagere, 1, 6);
        
        txaDeltagere = new TextArea();
        txaDeltagere.setMaxSize(FIELD_WIDTH, 80);
        txaDeltagere.setMinSize(FIELD_WIDTH, 80);
        this.add(txaDeltagere, 2, 6);
        
        HBox hbxButtons = new HBox(40);
        this.add(hbxButtons, 0, 7, 3, 1);
//        hbxButtons.setPadding(new Insets(10, 0, 0, 0));
        hbxButtons.setAlignment(Pos.BASELINE_LEFT);

        Button btnCreateKonference = new Button("Opret \nKonference");
        btnCreateKonference.setMinWidth(80);
        btnCreateKonference.setTextAlignment(TextAlignment.CENTER);
        hbxButtons.getChildren().add(btnCreateKonference);
        btnCreateKonference.setOnAction(event -> this.createKonferenceAction());

        Button btnUpdateKonference = new Button("Opdater \nKonference");
        btnUpdateKonference.setMinWidth(80);
        btnUpdateKonference.setTextAlignment(TextAlignment.CENTER);
        hbxButtons.getChildren().add(btnUpdateKonference);
        btnUpdateKonference.setOnAction(event -> this.updateKonferenceAction());

        Button btnDeleteKonference = new Button("Slet \nKonference");
        btnDeleteKonference.setMinWidth(80);
        btnDeleteKonference.setTextAlignment(TextAlignment.CENTER);
        hbxButtons.getChildren().add(btnDeleteKonference);
        btnDeleteKonference.setOnAction(event -> this.deleteKonferenceAction());


        if (lvwKonference.getItems().size() > 0) {
            lvwKonference.getSelectionModel().select(0);
        }
    }

    private ArrayList<Miljøkonference> initAllKonfList() {
        ArrayList<Miljøkonference> list = new ArrayList<>();
        for (Miljøkonference konf : Service.getMiljøkonference()) {
            list.add(konf);
        }
        return list;
    }

    // -------------------------------------------------------------------------


    private void createKonferenceAction() {
        KonferenceWindow dia = new KonferenceWindow("Opret Miljøkonference");
        dia.showAndWait();

        // Wait for the modal dialog to close

        lvwKonference.getItems().setAll(this.initAllKonfList());
        this.updateControls();
    }

    private void updateKonferenceAction() {
        Miljøkonference konference = lvwKonference.getSelectionModel().getSelectedItem();
        if (konference == null)
            return;

        KonferenceWindow dia = new KonferenceWindow("Update Employee", konference);
        dia.showAndWait();

        // Wait for the modal dialog to close

        int selectIndex = lvwKonference.getSelectionModel().getSelectedIndex();
        lvwKonference.getItems().setAll(this.initAllKonfList());
        lvwKonference.getSelectionModel().select(selectIndex);
    }

    private void deleteKonferenceAction() {
    	Miljøkonference konference = lvwKonference.getSelectionModel().getSelectedItem();
        if (konference == null)
            return;

        Stage owner = (Stage) this.getScene().getWindow();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Employee");
        alert.initOwner(owner);
        alert.setHeaderText("Are you sure?");

        // Wait for the modal dialog to close
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
//            Service.deleteMiljøkonference(konference);
            lvwKonference.getItems().setAll(this.initAllKonfList());
            this.updateControls();
        }
    }

    // -------------------------------------------------------------------------

    private void selectedKonferenceChanged() {
        this.updateControls();
    }

    public void updateControls() {
//        Employee employee = lvwKonference.getSelectionModel().getSelectedItem();
//        if (employee != null) {
//            txfName.setText(employee.getName());
//            txfWage.setText("kr " + employee.getWage());
//            //txfEmploymentYear.setText(""+employee.getEmploymentYear());
//            if (employee.getCompany() != null) {
//                txfCompany.setText("" + employee.getCompany());
//                txfSalary.setText("kr " + employee.weeklySalary());
//                txfEmploymentYear.setText(""+employee.getEmploymentYear());
//            } else {
//                txfCompany.clear();
//                txfSalary.clear();
//                txfEmploymentYear.clear();
//            }
//        } else {
//            txfName.clear();
//            txfWage.clear();
//            txfCompany.clear();
//            txfSalary.clear();
//            txfEmploymentYear.clear();
//        }
    }

}
