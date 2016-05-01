package Gui;

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

public class DeltagerPane extends GridPane
{
	private TextField txfNavn, txfTlfNr;
	private ListView<Deltager> lvwDeltagere;
	private TextArea txaAdresse,
			txaMiljøkonferencer;
	private Button btnTilmeldDeltager;

	private final int FIELD_WIDTH = 227;

	public DeltagerPane()
	{
		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		Label lblComp = new Label("Deltagere");
		this.add(lblComp, 0, 0);

		lvwDeltagere = new ListView<>();
		this.add(lvwDeltagere, 0, 1, 1, 6);
		lvwDeltagere.setMinSize(200, 330);
		lvwDeltagere.setMaxSize(200, 330);
		lvwDeltagere.getItems().setAll(Service.getDeltagere());
		ChangeListener<Deltager> listener = (ov, oldDeltager, newDeltager) -> this.selectedDeltagerChanged();
		lvwDeltagere.getSelectionModel().selectedItemProperty().addListener(listener);

		Label lblTitel = new Label("Navn:");
		this.add(lblTitel, 1, 1);

		txfNavn = new TextField();
		this.add(txfNavn, 2, 1);
		txfNavn.setMinWidth(FIELD_WIDTH);
		txfNavn.setMaxWidth(FIELD_WIDTH);
		txfNavn.setPrefWidth(FIELD_WIDTH);
		txfNavn.setEditable(false);

		Label lblAdresse = new Label("Adresse:");
		this.add(lblAdresse, 1, 2);

		txaAdresse = new TextArea();
		txaAdresse.setMaxSize(FIELD_WIDTH, 80);
		txaAdresse.setMinSize(FIELD_WIDTH, 80);
		this.add(txaAdresse, 2, 2);
		
		Label lblTlfNr = new Label("Telefon nr.:");
		this.add(lblTlfNr, 1, 3);

		txfTlfNr = new TextField();
		txfTlfNr.setMinWidth(FIELD_WIDTH);
		txfTlfNr.setMaxWidth(FIELD_WIDTH);
		this.add(txfTlfNr, 2, 3);
		txfTlfNr.setEditable(false);


		Label lblMiljøkonferencer = new Label("Miljøkonferencer:");
		this.add(lblMiljøkonferencer, 1, 4);

		txaMiljøkonferencer = new TextArea();
		txaMiljøkonferencer.setMaxSize(FIELD_WIDTH, 160);
		txaMiljøkonferencer.setMinSize(FIELD_WIDTH, 160);
		this.add(txaMiljøkonferencer, 2, 4);

		HBox hbxButtons = new HBox(40);
		this.add(hbxButtons, 0, 7, 3, 1);
		hbxButtons.setAlignment(Pos.BASELINE_LEFT);

		Button btnCreateDeltager = new Button("Opret \nDeltager");
		btnCreateDeltager.setMinWidth(80);
		btnCreateDeltager.setTextAlignment(TextAlignment.CENTER);
		hbxButtons.getChildren().add(btnCreateDeltager);
		btnCreateDeltager.setOnAction(event -> this.createDeltagerAction());

		Button btnUpdateDeltager = new Button("Opdater \nDeltager");
		btnUpdateDeltager.setMinWidth(80);
		btnUpdateDeltager.setTextAlignment(TextAlignment.CENTER);
		hbxButtons.getChildren().add(btnUpdateDeltager);
		btnUpdateDeltager.setOnAction(event -> this.updateDeltagerAction());

		Button btnDeleteDeltager = new Button("Slet \nDeltager");
		btnDeleteDeltager.setMinWidth(80);
		btnDeleteDeltager.setTextAlignment(TextAlignment.CENTER);
		hbxButtons.getChildren().add(btnDeleteDeltager);
		btnDeleteDeltager.setOnAction(event -> this.deleteDeltagerAction());

		btnTilmeldDeltager = new Button("Tilmeld \nDeltager");
		btnTilmeldDeltager.setMinWidth(80);
		btnTilmeldDeltager.setTextAlignment(TextAlignment.CENTER);
		hbxButtons.getChildren().add(btnTilmeldDeltager);
		btnTilmeldDeltager.setDisable(true);
		btnTilmeldDeltager.setOnAction(event -> this.tilmeldDeltagerAction());

		if (lvwDeltagere.getItems().size() > 0)
		{
			lvwDeltagere.getSelectionModel().select(0);
		}
	}

	private ArrayList<Deltager> initAllDeltagerList()
	{
		ArrayList<Deltager> list = new ArrayList<>();
		for (Deltager delt : Service.getDeltagere())
		{
			list.add(delt);
		}
		return list;
	}

	// -------------------------------------------------------------------------
	
	/**
	 * Metode til at åbne DeltagerWindow, hvor der kan oprettes en deltager. 
	 * @post: deltager listview opdateres og hvis succesfuld, vil der være et nyt deltager objekt
	 */
	private void createDeltagerAction()
	{
		DeltagerWindow dia = new DeltagerWindow("Opret Deltager");
		dia.showAndWait();

		// Venter på at dia bliver lukket

		lvwDeltagere.getItems().setAll(this.initAllDeltagerList());
		this.updateControls();
	}

	/**
	 * Metode til at åbne et DeltagerWindow, hvor et eksisterende deltager objekt kan opdateres.
	 * @pre et deltager objekt er markeret i deltager listview.
	 * @post et deltager objekts instansvariabler er opdateret.
	 */
	private void updateDeltagerAction()
	{
		Deltager deltager = lvwDeltagere.getSelectionModel().getSelectedItem();
		if (deltager == null)
			return;

		DeltagerWindow dia = new DeltagerWindow("Opret Deltager", deltager);
		dia.showAndWait();

		// Venter på at dia bliver lukket

		int selectIndex = lvwDeltagere.getSelectionModel().getSelectedIndex();
		lvwDeltagere.getItems().setAll(this.initAllDeltagerList());
		lvwDeltagere.getSelectionModel().select(selectIndex);
	}

	/**
	 * Metode til at slette et markeret deltager objekt.
	 * @pre et deltager objekt er markeret i deltager listview.
	 * @post det markerede deltager objekt bliver slettet.
	 */
	private void deleteDeltagerAction()
	{
		Deltager deltager = lvwDeltagere.getSelectionModel().getSelectedItem();
		if (deltager == null)
			return;

		Stage owner = (Stage) this.getScene().getWindow();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Slet Deltager");
		alert.initOwner(owner);
		alert.setHeaderText("Er du sikker?");

		// Venter på at dia bliver lukket
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK)
		{
			Service.deleteDeltager(deltager);
			lvwDeltagere.getItems().setAll(this.initAllDeltagerList());
			this.updateControls();
		}
	}
	
	/**
	 * Metode til at åbne TilmeldingWindow, hvor en deltager kan tilmeldes en miljøkonference.
	 * @pre et deltager objekt er markeret i deltager listview.
	 * @post deltager er tilmeldt miljøkonference, og har fået oprettet indkvartering (med/eller
	 * uden hotelbooking)
	 */
	private void tilmeldDeltagerAction () {
		Deltager deltager = lvwDeltagere.getSelectionModel().getSelectedItem();
		TilmeldingWindow dia = new TilmeldingWindow("Tilmeld", deltager);
		
		// Venter på at dia bliver lukket
		
		dia.showAndWait();
	}

	// -------------------------------------------------------------------------

	/**
	 * Metode til at opdatere oplysninger når der vælges nye objekter i deltager listview.
	 */
	private void selectedDeltagerChanged()
	{
		this.updateControls();
	}

	/**
	 * Metode til at opdatere oplysninger i DeltagerPane.
	 */
	public void updateControls()
	{
		Deltager deltager = lvwDeltagere.getSelectionModel().getSelectedItem();
		
		if (deltager != null)
		{
			btnTilmeldDeltager.setDisable(false);
			txfNavn.setText(deltager.getNavn());
			txfTlfNr.setText("" + deltager.getTelefonNr());

			txaAdresse.setText(deltager.getAdresse().toString());

			StringBuilder sb = new StringBuilder();
			for (Miljøkonference konf : Service.getMiljøkonferencer())
			{
				for (Tilmelding til : konf.getTilmeldingliste())
				{
					if (til.getDeltager() == deltager)
					{
						sb.append(konf.getTitel() + "\n");
					}					
				}
			}
			txaMiljøkonferencer.setText(sb.toString());
		}
		else
		{
			txfNavn.clear();
			txfTlfNr.clear();
			txaAdresse.clear();
			txaMiljøkonferencer.clear();
			btnTilmeldDeltager.setDisable(true);
		}
	}
}
