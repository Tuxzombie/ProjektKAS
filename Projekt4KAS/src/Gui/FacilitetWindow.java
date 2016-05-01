package Gui;

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
import java.util.ArrayList;
import Model.*;
import Service.*;

public class FacilitetWindow extends Stage
{
	private Hotel hotel;
	private ArrayList<Facilitet> faciliteter;

	public FacilitetWindow(String title, Hotel hotel)
	{
		this.initStyle(StageStyle.UTILITY);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);

		this.hotel = hotel;
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
	private HBox boxTilføjAnuller = new HBox();

	// --------------------------------------------------------------------------

	private void initContent(GridPane pane)
	{
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

        Label lblNavn = new Label("Navn:");
        pane.add(lblNavn, 0, 0);

        txfNavn = new TextField();
        pane.add(txfNavn, 1, 0);
        txfNavn.setPrefWidth(200);

        Label lblPris = new Label("Pris:");
        pane.add(lblPris, 0, 1);

        txfPris = new TextField();
        pane.add(txfPris, 1, 1);
        
		Button btnTilføj = new Button("Tilføj");
		btnTilføj.setOnAction(event -> this.tilføjAction());
		boxTilføjAnuller.getChildren().add(btnTilføj);
				
		Button btnAnuller = new Button("Anuller");
		btnAnuller.setOnAction(event -> this.anullerAction());
		boxTilføjAnuller.getChildren().add(btnAnuller);

		pane.add(boxTilføjAnuller, 1, 2);
		boxTilføjAnuller.setSpacing(10);
		boxTilføjAnuller.setAlignment(Pos.CENTER_RIGHT);
		
		lblError = new Label();
		pane.add(lblError, 0, 5, 2, 1);
		lblError.setStyle("-fx-text-fill: red");
	}

	// -------------------------------------------------------------------------

	/**
	 * Metode til at lukke vinduet ned
	 */
	private void anullerAction()
	{
		this.hide();
	}

	/**
	 * Metode til godkende indtastede oplysninger og oprette en ny facilitet tilknyttet det 
	 * relevante hotel.
	 * @pre pris kan oversættes til double, og navns længde > 0. 
	 */
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
			lblError.setText("Navn er tom!");
			return;
		} else if (pris <= 0)
		{
			lblError.setText("Pris er ugyldig!");
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
