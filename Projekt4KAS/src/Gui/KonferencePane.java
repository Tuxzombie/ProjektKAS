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

public class KonferencePane extends GridPane
{
	private TextField txfTitel, txfTema,
			txfStartDato, txfSlutDato;
	private ListView<Miljøkonference> lvwKonference;
	private TextArea txaAdresse,
			txaDeltagere;

	private final int FIELD_WIDTH = 263;

	public KonferencePane()
	{
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
		lvwKonference.getItems().setAll(Service.getMiljøkonferencer());
		ChangeListener<Miljøkonference> listener = (ov, oldMiljøkonference, newMiljøkonference) -> this.selectedKonferenceChanged();
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
		txaAdresse.setEditable(false);
		this.add(txaAdresse, 2, 5);

		Label lblDeltagere = new Label("Deltagere:");
		this.add(lblDeltagere, 1, 6);

		txaDeltagere = new TextArea();
		txaDeltagere.setMaxSize(FIELD_WIDTH, 80);
		txaDeltagere.setMinSize(FIELD_WIDTH, 80);
		txaDeltagere.setEditable(false);
		this.add(txaDeltagere, 2, 6);

		HBox hbxButtons = new HBox(40);
		this.add(hbxButtons, 0, 7, 3, 1);
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
		
		Button btnVisUdflugter = new Button("Vis \nUdflugter");
		btnVisUdflugter.setMinWidth(80);
		btnVisUdflugter.setTextAlignment(TextAlignment.CENTER);
		hbxButtons.getChildren().add(btnVisUdflugter);
		btnVisUdflugter.setOnAction(event -> this.visUdflugterAction());

		if (lvwKonference.getItems().size() > 0)
		{
			lvwKonference.getSelectionModel().select(0);
		}
	}

	/**
	 * initialisere dataen til konference list
	 * @return
	 */
	private ArrayList<Miljøkonference> initAllKonfList()
	{
		ArrayList<Miljøkonference> list = new ArrayList<>();
		for (Miljøkonference konf : Service.getMiljøkonferencer())
		{
			list.add(konf);
		}
		return list;
	}

	// -------------------------------------------------------------------------

	/**
	 * Åbnet vindue der viser udflugter tilknyttet til den valgte konfernece
	 */
	private void visUdflugterAction()
	{
		Miljøkonference konference = lvwKonference.getSelectionModel().getSelectedItem();
		if (konference == null)
			return;
	
		if (konference.getUdflugter().size() > 0)
		{
			VisUdflugtWindow dia = new VisUdflugtWindow("Vis Udflugter", konference);
			dia.showAndWait();
		}
		else
		{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Advarsel");
		alert.setHeaderText(null);
		alert.setContentText("Der er ikke oprettet nogle udflugter");

		alert.showAndWait();
		}

	}

	/**
	 * Åbnet vinduet der oprette konference
	 */
	private void createKonferenceAction()
	{
		KonferenceWindow dia = new KonferenceWindow("Opret Miljøkonference");
		dia.showAndWait();

		// Wait for the modal dialog to close

		lvwKonference.getItems().setAll(this.initAllKonfList());
		this.updateControls();
	}

	/**
	 * Åbner vinduet opdater konference
	 */
	private void updateKonferenceAction()
	{
		Miljøkonference konference = lvwKonference.getSelectionModel().getSelectedItem();
		if (konference == null)
			return;

		KonferenceWindow dia = new KonferenceWindow("Opdater Miljøkonference", konference);
		dia.showAndWait();

		// Wait for the modal dialog to close

		int selectIndex = lvwKonference.getSelectionModel().getSelectedIndex();
		lvwKonference.getItems().setAll(this.initAllKonfList());
		lvwKonference.getSelectionModel().select(selectIndex);
	}

	/**
	 * Metode til at slette, konference 
	 */
	private void deleteKonferenceAction()
	{
		Miljøkonference konference = lvwKonference.getSelectionModel().getSelectedItem();
		if (konference == null)
			return;

		Stage owner = (Stage) this.getScene().getWindow();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Slet Miljøkonference");
		alert.initOwner(owner);
		alert.setHeaderText("Er du sikker?");

		// Wait for the modal dialog to close
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK)
		{
			Service.deleteMiljøkonference(konference);
			lvwKonference.getItems().setAll(this.initAllKonfList());
			this.updateControls();
		}
	}

	// -------------------------------------------------------------------------

	/**
	 * Metode der holder kaldes når valgt konference er skiftet.
	 */
	private void selectedKonferenceChanged()
	{
		this.updateControls();
	}

	/**
	 * Metode der skriver data i felter
	 */
	public void updateControls()
	{
		Miljøkonference konference = lvwKonference.getSelectionModel().getSelectedItem();
		if (konference != null)
		{
			txfTitel.setText(konference.getTitel());
			txfTema.setText(konference.getTema());
			txfStartDato.setText(konference.getStartDato().toString());
			txfSlutDato.setText(konference.getSlutDato().toString());
			txaAdresse.setText(konference.getAdresse().toString());

			StringBuilder sb = new StringBuilder();
			for (Tilmelding delt : konference.getTilmeldingliste())
			{
				if (delt.getDeltager() != null)
				{
					sb.append(delt.getDeltager().getNavn() + "\n");
				}

			}
			txaDeltagere.setText(sb.toString());

		} else
		{
			txfTitel.clear();
			txfTema.clear();
			txfStartDato.clear();
			txfSlutDato.clear();
			txaAdresse.clear();
			txaDeltagere.clear();
		}
	}

}
