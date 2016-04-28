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

import java.time.LocalDate;

import Model.*;
import Service.*;

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
				, "Postnr:", "By", "Land:"};
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
        	txfInput[8].setText(konference.getAdresse().getBy());
        	txfInput[9].setText(konference.getAdresse().getLand());      	
        	

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
        	txfInput[9].clear();
        }
    }

    // -------------------------------------------------------------------------

    private void cancelAction() {
        this.hide();
    }

    private void okAction() {
    	
    	String titel = txfInput[0].getText().trim();
    	String tema = txfInput[1].getText().trim();
    	
    	LocalDate startDato = null;
    	try
		{
        	startDato = LocalDate.parse(txfInput[2].getText().trim());
		} catch (NumberFormatException ex)
		{
			// do nothing
		}
    	
    	LocalDate slutDato = null;
    	try
		{
        	slutDato = LocalDate.parse(txfInput[3].getText().trim());
		} catch (NumberFormatException ex)
		{
			// do nothing
		}


    	String vej = txfInput[4].getText().trim();
    	
    	int nr = -1;
    	try
		{
        	nr = Integer.parseInt(txfInput[5].getText().trim());
		} catch (NumberFormatException ex)
		{
			// do nothing
		}

    	String etage = txfInput[6].getText().trim();
    	
    	int postNr = -1;
    	try
		{
        	postNr = Integer.parseInt(txfInput[7].getText().trim());
		} catch (NumberFormatException ex)
		{
			// do nothing
		}

    	String by = txfInput[8].getText().trim(); 
    	String land = txfInput[9].getText().trim();

        if (titel.length() == 0) {
            lblError.setText("Titel er tom");
            return;
        }
        else if (tema.length() == 0)
		{
			lblError.setText("Tema er tom");
			return;
		}
        else if (startDato == null)
 		{
 			lblError.setText("Start Dato er tom");
 			return;
 		}
        else if (slutDato == null)
		{
			lblError.setText("Slut Dato er tom");
			return;
		}
        else if (vej.length() == 0)
		{
			lblError.setText("Vej er tom");
			return;
		}
        else if (postNr <=0)
		{
			lblError.setText("Post Nr er ugyldigt");
			return;
		}
        else if (by.length() == 0)
		{
			lblError.setText("By er ugyldigt");
			return;
		}
        else if (land.length() == 0)
		{
			lblError.setText("Land er tom");
			return;
		}
        

        // Call service methods
        if (konference != null) {
            Service.updateMiljøkonference(konference, titel, tema, startDato, slutDato, vej, nr, etage, postNr, by, land);
        } else {
            Service.createMiljøkonference(titel, tema, startDato, slutDato, vej, nr, etage, postNr, by, land);
        }

        this.hide();
    }

}
