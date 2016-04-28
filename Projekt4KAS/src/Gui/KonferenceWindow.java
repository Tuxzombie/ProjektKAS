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

public class KonferenceWindow extends Stage {
    private Miljøkonference konference;

    public KonferenceWindow(String title, Miljøkonference konference) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.konference = konference;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public KonferenceWindow(String title) {
        this(title, null);
    }

    // -------------------------------------------------------------------------
    private TextField[] txfInput;
    private Label[] lblInput;
    private String[] lblNames;

    //--------------------------------------------------------------------------
    private TextField txfName, txfHours;
    private Label lblError;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lblNames = new String[]{"Titel:", "Tema:", "Start Dato:", "Slut Dato:",  "Vej:", "Nr:", "Etage:"
				, "Postnr:", "Land:"};
		txfInput = new TextField[lblNames.length];
		lblInput = new Label[lblNames.length];
		
		final int MAX_ROWS = 6;
		
		for (int i = 0; i < lblNames.length; i++)
		{
			if (i < MAX_ROWS)
			{
				lblInput[i] = new Label(lblNames[i]);
				pane.add(lblInput[i], 0, i);
				
				txfInput[i] = new TextField();
				pane.add(txfInput[i], 1, i);
			}
			else
			{
				lblInput[i] = new Label(lblNames[i]);
				pane.add(lblInput[i], 2, i-MAX_ROWS);
				
				txfInput[i] = new TextField();
				pane.add(txfInput[i], 3, i-MAX_ROWS);
				
			}
		
		}
		
		Button btnOK = new Button("OK");
		pane.add(btnOK, 0, MAX_ROWS+1);
		btnOK.setOnAction(event -> this.okAction());
		
		Button btnCancel = new Button("Cancel");
		pane.add(btnCancel, 1, MAX_ROWS+1);
		btnCancel.setOnAction(event -> this.cancelAction());


        lblError = new Label();
        pane.add(lblError, 0, 5);
        lblError.setStyle("-fx-text-fill: red");

        this.initControls();
    }

    private void initControls() {
        if (konference != null) {
        	
        	txfInput[0].setText(konference.getTitel());
        	txfInput[1].setText(konference.getTema());
        	txfInput[2].setText(konference.getStartDato().toString());
        	txfInput[3].setText(konference.getSlutDato().toString());
        	txfInput[4].setText(konference.getAdresse().getVej());
        	txfInput[5].setText(""+konference.getAdresse().getNr());
        	txfInput[6].setText(konference.getAdresse().getEtage());
        	txfInput[7].setText(""+konference.getAdresse().getPostNr());
        	txfInput[8].setText(konference.getAdresse().getLand());      	
        	

        } else {
        	
        	txfInput[0].clear();
        	txfInput[1].clear();
        	txfInput[2].clear();
        	txfInput[3].clear();
        	txfInput[4].clear();
        	txfInput[5].clear();
        	txfInput[6].clear();
        	txfInput[7].clear();
        	txfInput[8].clear();
        }
    }

    // -------------------------------------------------------------------------

    private void cancelAction() {
        this.hide();
    }

    private void okAction() {
        String name = txfName.getText().trim();
        if (name.length() == 0) {
            lblError.setText("Name is empty");
            return;
        }

        int hours = -1;
        try {
            hours = Integer.parseInt(txfHours.getText().trim());
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
