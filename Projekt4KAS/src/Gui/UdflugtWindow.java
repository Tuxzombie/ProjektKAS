package Gui;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.Checkbox;
import java.time.LocalDate;

import Model.*;
import Service.Service;

public class UdflugtWindow extends Stage
{
	private Miljøkonference konference;

	public UdflugtWindow(String title, Miljøkonference konference)
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

	// -------------------------------------------------------------------------
	private TextField txfLokalitet,
			txfBeskrivelse, txfPris;
	private DatePicker dpStartDato;
	private DatePicker dpSlutDato;
	private CheckBox chbFrokost;
	private Label lblError;

	// --------------------------------------------------------------------------

	private void initContent(GridPane pane)
	{
		pane.setPadding(new Insets(20));
		pane.setHgap(50);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		Label lblLokalitet = new Label("Lokalitet: ");
		pane.add(lblLokalitet, 0, 0);
		txfLokalitet = new TextField();
		pane.add(txfLokalitet, 1, 0);

		Label lblBeskrivelse = new Label("Beskrivelse: ");
		pane.add(lblBeskrivelse, 0, 1);
		txfBeskrivelse = new TextField();
		pane.add(txfBeskrivelse, 1, 1);

		Label lblPris = new Label("Pris: ");
		pane.add(lblPris, 0, 2);
		txfPris = new TextField();
		pane.add(txfPris, 1, 2);

		Label lblStartDato = new Label("Start Dato: ");
		pane.add(lblStartDato, 0, 3);
		dpStartDato = new DatePicker();
		pane.add(dpStartDato, 1, 3);

		Label lblSlutDato = new Label("Slut Dato: ");
		pane.add(lblSlutDato, 0, 4);
		dpSlutDato = new DatePicker();
		pane.add(dpSlutDato, 1, 4);

		chbFrokost = new CheckBox("Frokost: ");
		pane.add(chbFrokost, 0, 5, 2, 1);

		Button btnOK = new Button("OK");
		pane.add(btnOK, 0, 6);
		btnOK.setOnAction(event -> this.okAction());

		Button btnCancel = new Button("Cancel");
		pane.add(btnCancel, 1, 6);
		btnCancel.setOnAction(event -> this.cancelAction());

		lblError = new Label();
		pane.add(lblError, 0, 7, 2, 1);
		lblError.setStyle("-fx-text-fill: red");
	}

	private void okAction()
	{
		String lokalitet = null;
		String beskrivelse = null;
		double pris = -1;
		LocalDate startDato = null;
		LocalDate slutDato = null;
		boolean hasFrokost = false;

		try
		{
			lokalitet = txfLokalitet.getText().trim();
		} catch (NullPointerException ex)
		{
			// do nothing
		}

		try
		{
			beskrivelse = txfBeskrivelse.getText().trim();
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
		try
		{
			startDato = dpStartDato.getValue();
		} catch (NullPointerException ex)
		{
			// do nothing
		}
		try
		{
			slutDato = dpSlutDato.getValue();
		} catch (NullPointerException ex)
		{
			// do nothing
		}
		hasFrokost = chbFrokost.isSelected();

		if (lokalitet == null)
		{
			lblError.setText("Lokalitet er tom");
			return;
		} else if (beskrivelse == null)
		{
			lblError.setText("Beskrivelse er tom");
			return;
		} else if (pris <= -1)
		{
			lblError.setText("Pris er ugyldig");
			return;
		} else if (startDato == null)
		{
			lblError.setText("Vælg en startdato");
			return;
		} else if (slutDato == null)
		{
			lblError.setText("Vælg en slutdato");
			return;
		} else if (slutDato.isBefore(startDato))
		{
			lblError.setText("Slutdato kan ikke være før startdato");
			return;
		}

		Service.createUdflugt(konference, lokalitet, beskrivelse, pris, startDato, slutDato, hasFrokost);

		this.hide();

	}

	private void cancelAction()
	{
		this.hide();
	}

}
