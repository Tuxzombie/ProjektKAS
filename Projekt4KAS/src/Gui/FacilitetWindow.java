package Gui;

import javafx.beans.property.StringProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.IllegalFormatException;

import Model.*;
import Service.*;

public class FacilitetWindow extends Stage
{
	private Hotel hotel;
	private ArrayList<Facilitet> faciliteter;
	private String title;

	public FacilitetWindow(String title, Hotel hotel)
	{
		this.initStyle(StageStyle.UTILITY);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);

		this.hotel = hotel;
		this.faciliteter = faciliteter;

		this.setTitle(title);
		GridPane pane = new GridPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		this.setScene(scene);
	}

	public FacilitetWindow(String title, ArrayList<Facilitet> faciliteter)
	{
		this.hotel = null;
		this.faciliteter = faciliteter;
		
		this.initStyle(StageStyle.UTILITY);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);
		
		this.setTitle(title);
		GridPane pane = new GridPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		this.setScene(scene);
	}


	// -------------------------------------------------------------------------
	private TextField txfNavn, txfPris;
	private Label lblError;
	private HBox box = new HBox();

	// --------------------------------------------------------------------------

	private void initContent(GridPane pane)
	{
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

        Label lblNavn = new Label("Navn");
        pane.add(lblNavn, 0, 0);

        txfNavn = new TextField();
        pane.add(txfNavn, 0, 1);
        txfNavn.setPrefWidth(200);

        Label lblPris = new Label("Pris");
        pane.add(lblPris, 0, 2);

        txfPris = new TextField();
        pane.add(txfPris, 0, 3);
        
		Button btnTilføj = new Button("Tilføj");
		btnTilføj.setOnAction(event -> this.tilføjAction());
		box.getChildren().add(btnTilføj);
				
		Button btnAnuller = new Button("Anuller");
		btnAnuller.setOnAction(event -> this.anullerAction());
		box.getChildren().add(btnAnuller);

		pane.add(box, 0, 4);
		box.setSpacing(10);
		box.setAlignment(Pos.CENTER_RIGHT);
		
		lblError = new Label();
		pane.add(lblError, 0, 5, 2, 1);
		lblError.setStyle("-fx-text-fill: red");

	}

	// -------------------------------------------------------------------------

	private void anullerAction()
	{
		this.hide();
	}

	private void tilføjAction()
	{
		String navn = null;
		double pris = -1;
		
		try
		{
			navn = txfNavn.getText().trim();
		} catch (NullPointerException ex)
		{
			// do nothing
		}

		try
		{
			pris = Double.parseDouble(txfPris.getText().trim());
		} catch (NullPointerException ex)
		{
			// do nothing
		}

		if (navn.length() == 0)
		{
			lblError.setText("Navn er tom");
			return;
		} else if (pris <= 0)
		{
			lblError.setText("Pris er ugyldig");
			return;
		}

		if(hotel == null) {
		 Facilitet facilitet = new Facilitet(navn, pris);
		 faciliteter.add(facilitet);
		}
		else Service.createFacilitet(hotel, navn, pris);
		 
		this.hide();
	}

}
