package Gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Model.*;

public class LedsagerWindow extends Stage {
    private Ledsager ledsager;

    public LedsagerWindow(String title, Ledsager ledsager) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.ledsager = ledsager;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public LedsagerWindow(String title) {
        this(title, null);
    }

    // -------------------------------------------------------------------------

    private TextField txfNavn, txfPris;
    private Label lblError;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblName = new Label("Name");
        pane.add(lblName, 0, 0);

        txfNavn = new TextField();
        pane.add(txfNavn, 0, 1);
        txfNavn.setPrefWidth(200);

        Label lblHours = new Label("Weekly Hours");
        pane.add(lblHours, 0, 2);

        txfPris = new TextField();
        pane.add(txfPris, 0, 3);

        Button btnCancel = new Button("Cancel");
        pane.add(btnCancel, 0, 4);
        GridPane.setHalignment(btnCancel, HPos.LEFT);
        btnCancel.setOnAction(event -> this.cancelAction());

        Button btnOK = new Button("OK");
        pane.add(btnOK, 0, 4);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
        btnOK.setOnAction(event -> this.okAction());

        lblError = new Label();
        pane.add(lblError, 0, 5);
        lblError.setStyle("-fx-text-fill: red");

        this.initControls();
    }

    private void initControls() {
//        if (company != null) {
//            txfName.setText(company.getName());
//            txfHours.setText("" + company.getHours());
//        } else {
//            txfName.clear();
//            txfHours.clear();
//        }
    }

    // -------------------------------------------------------------------------

    private void cancelAction() {
        this.hide();
    }

    private void okAction() {
        String name = txfNavn.getText().trim();
        if (name.length() == 0) {
            lblError.setText("Name is empty");
            return;
        }

        int hours = -1;
        try {
            hours = Integer.parseInt(txfPris.getText().trim());
        } catch (NumberFormatException ex) {
            // do nothing
        }
        if (hours < 0) {
            lblError.setText("Hours is not a positive number");
            return;
        }

        // Call service methods
//        if (company != null) {
//            Service.updateCompany(company, name, hours);
//        } else {
//            Service.createCompany(name, hours);
//        }

        this.hide();
    }

}
