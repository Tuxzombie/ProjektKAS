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
import java.time.format.DateTimeParseException;
import java.util.IllegalFormatException;

import Model.*;
import Service.*;

public class PrisgruppeWindow extends Stage
{
	private Miljøkonference konference;

	public PrisgruppeWindow(String title, Miljøkonference konference)
	{
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

	public PrisgruppeWindow(String title)
	{
		this(title, null);
	}

	// -------------------------------------------------------------------------
	private TextField txfNavn, txfPris;
	private Label lblError;

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
        
		Button btnOK = new Button("OK");
		pane.add(btnOK, 0, 4);
		btnOK.setOnAction(event -> this.okAction());

		Button btnCancel = new Button("Cancel");
		pane.add(btnCancel, 1, 4);
		btnCancel.setOnAction(event -> this.cancelAction());

		lblError = new Label();
		pane.add(lblError, 0, 5, 2, 1);
		lblError.setStyle("-fx-text-fill: red");

		this.initControls();
	}

	private void initControls()
	{
		if (konference != null)
		{

		} else
		{

		}
	}

	// -------------------------------------------------------------------------

	private void cancelAction()
	{
		this.hide();
	}

	private void okAction()
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

	
//		if (konference != null)
//		{
//			Service.updatePrisgruppe(Service.createPrisgruppe(konference, navn, pris), navn, pris);
//		} else
//		{
			Service.createPrisgruppe(konference, navn, pris);
//		}

		this.hide();
	}

}
