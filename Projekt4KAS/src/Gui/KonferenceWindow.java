package Gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;

import Model.*;
import Service.*;

public class KonferenceWindow extends Stage
{
	private Miljøkonference konference;

	public KonferenceWindow(String title, Miljøkonference konference)
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

	public KonferenceWindow(String title)
	{
		this(title, null);
	}

	// -------------------------------------------------------------------------
	private TextField[] txfInput;
	private Label[] lblInput;
	private String[] lblNames;
	private Label lblError;
	private ListView<Prisgruppe> lvwPrisgrupper;
	private ListView<Udflugt> lvwUdflugter;
	private ArrayList<Prisgruppe> prisgrupper = new ArrayList<>();
	private ArrayList<Udflugt> udflugter = new ArrayList<>();

	// --------------------------------------------------------------------------

	/**
	 * initialisere vinduet
	 * @param pane
	 */
	private void initContent(GridPane pane)
	{
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		lblNames = new String[]
		{"Titel:", "Tema:", "Start Dato:", "Slut Dato:", "Vej:", "Nr:", "Etage:", "Postnr:", "By", "Land:"};
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
			} else
			{
				lblInput[i] = new Label(lblNames[i]);
				pane.add(lblInput[i], 2, i - MAX_ROWS);

				txfInput[i] = new TextField();
				pane.add(txfInput[i], 3, i - MAX_ROWS);

			}

		}

		Button btnOK = new Button("OK");
		pane.add(btnOK, 0, MAX_ROWS + 1);
		btnOK.setOnAction(event -> this.okAction());

		Button btnCancel = new Button("Cancel");
		pane.add(btnCancel, 1, MAX_ROWS + 1);
		btnCancel.setOnAction(event -> this.cancelAction());

		GridPane prisgruppePane = new GridPane();
		pane.add(prisgruppePane, 4, 0, 2, 6);

		lvwPrisgrupper = new ListView<>();
		lvwPrisgrupper.setMinSize(200, 160);
		lvwPrisgrupper.setMaxSize(200, 160);
		try
		{
			lvwPrisgrupper.getItems().setAll(konference.getPrisgrupper());

		} catch (NullPointerException ex)
		{
			// do nothing
		}

		prisgruppePane.add(lvwPrisgrupper, 0, 1, 2, 5);

		Label lblPrisgrupper = new Label("Prisgrupper");
		prisgruppePane.add(lblPrisgrupper, 0, 0);

		Button btnTilføj = new Button("Tilføj");
		prisgruppePane.add(btnTilføj, 0, 7);
		btnTilføj.setOnAction(event -> this.createPrisgruppeAction());

		Button btnFjern = new Button("Fjern");
		prisgruppePane.add(btnFjern, 1, 7);
		btnFjern.setOnAction(event -> this.deletePrisgruppeAction());

		GridPane udflugtPane = new GridPane();
		pane.add(udflugtPane, 6, 0, 2, 6);

		lvwUdflugter = new ListView<>();
		lvwUdflugter.setMinSize(200, 160);
		lvwUdflugter.setMaxSize(200, 160);
		try
		{
			lvwUdflugter.getItems().setAll(konference.getUdflugter());

		} catch (NullPointerException ex)
		{
			// do nothing
		}

		udflugtPane.add(lvwUdflugter, 0, 1, 2, 5);

		Label lblUdflugter = new Label("Udflugter");
		udflugtPane.add(lblUdflugter, 0, 0);

		Button btnTilføjUdflugt = new Button("Tilføj");
		udflugtPane.add(btnTilføjUdflugt, 0, 7);
		btnTilføjUdflugt.setOnAction(event -> this.createUdflugtAction());

		Button btnFjernUdflugt = new Button("Fjern");
		udflugtPane.add(btnFjernUdflugt, 1, 7);
		btnFjernUdflugt.setOnAction(event -> this.deleteUdflugtAction());

		lblError = new Label();
		pane.add(lblError, 0, MAX_ROWS + 2, 2, 1);
		lblError.setStyle("-fx-text-fill: red");

		this.initControls();
	}

	/**
	 * initialisere data og skriver det i felterne
	 */
	private void initControls()
	{
		if (konference != null)
		{

			txfInput[0].setText(konference.getTitel());
			txfInput[1].setText(konference.getTema());
			txfInput[2].setText(konference.getStartDato().toString());
			txfInput[3].setText(konference.getSlutDato().toString());
			txfInput[4].setText(konference.getAdresse().getVej());
			txfInput[5].setText("" + konference.getAdresse().getNr());
			txfInput[6].setText(konference.getAdresse().getEtage());
			txfInput[7].setText("" + konference.getAdresse().getPostNr());
			txfInput[8].setText(konference.getAdresse().getBy());
			txfInput[9].setText(konference.getAdresse().getLand());

			try
			{
				lvwPrisgrupper.getItems().setAll(konference.getPrisgrupper());

			} catch (NullPointerException ex)
			{
				// do nothing
			}
			try
			{
				lvwUdflugter.getItems().setAll(konference.getUdflugter());

			} catch (NullPointerException ex)
			{
				// do nothing
			}

		} else
		{

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

			lvwUdflugter.getItems().clear();
			lvwPrisgrupper.getItems().clear();
		}
	}

	// -------------------------------------------------------------------------

	/**
	 * metode der lukker viduet
	 */
	private void cancelAction()
	{
		this.hide();
	}

	/**
	 * Metode der oprtter og gemmer konference
	 */
	private void okAction()
	{

		String titel = txfInput[0].getText().trim();
		String tema = txfInput[1].getText().trim();

		LocalDate startDato = LocalDate.MIN;
		try
		{
			startDato = LocalDate.parse(txfInput[2].getText().trim());
		} catch (DateTimeParseException ex)
		{
			// do nothing
		}

		LocalDate slutDato = LocalDate.MIN;
		try
		{
			slutDato = LocalDate.parse(txfInput[3].getText().trim());
		} catch (DateTimeParseException ex)
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

		if (titel.length() == 0)
		{
			lblError.setText("Titel er tom");
			return;
		} else if (tema.length() == 0)
		{
			lblError.setText("Tema er tom");
			return;
		} else if (startDato == LocalDate.MIN)
		{
			lblError.setText("Start Dato er tom");
			return;
		} else if (slutDato == LocalDate.MIN)
		{
			lblError.setText("Slut Dato er tom");
			return;
		} else if (vej.length() == 0)
		{
			lblError.setText("Vej er tom");
			return;
		} else if (nr <= 0)
		{
			lblError.setText("Nr er ugyldigt");
			return;
		} else if (postNr <= 0)
		{
			lblError.setText("Post Nr er ugyldigt");
			return;
		} else if (by.length() == 0)
		{
			lblError.setText("By er ugyldigt");
			return;
		} else if (land.length() == 0)
		{
			lblError.setText("Land er tom");
			return;
		}

		// Call service methods
		if (konference != null)
		{
			for (Prisgruppe prisgruppe : prisgrupper)
			{
				konference.addPrisgrupper(prisgruppe);
			}
			for (Udflugt udflugt : udflugter)
			{
				String tmplokalitet = udflugt.getLokalitet();
				String tmpBeskrivelse = udflugt.getBeskrivelse();
				double tmpPris = udflugt.getPris();
				LocalDate tmpStartDato = udflugt.getStartDato();
				LocalDate tmpSlutDato = udflugt.getSlutDato();
				boolean tmpHasFrokost = udflugt.isHasFrokost();
				Service.createUdflugt(konference, tmplokalitet, tmpBeskrivelse, tmpPris, tmpStartDato, tmpSlutDato, tmpHasFrokost);
			}
			Service.updateMiljøkonference(konference, titel, tema, startDato, slutDato, vej, nr, etage, postNr, by, land);
		} else
		{
			Miljøkonference tmpKonference = Service.createMiljøkonference(titel, tema, startDato, slutDato, vej, nr, etage, postNr, by, land);
			for (Prisgruppe prisgruppe : prisgrupper)
			{
				tmpKonference.addPrisgrupper(prisgruppe);
			}
			for (Udflugt udflugt : udflugter)
			{
				String tmplokalitet = udflugt.getLokalitet();
				String tmpBeskrivelse = udflugt.getBeskrivelse();
				double tmpPris = udflugt.getPris();
				LocalDate tmpStartDato = udflugt.getStartDato();
				LocalDate tmpSlutDato = udflugt.getSlutDato();
				boolean tmpHasFrokost = udflugt.isHasFrokost();
				Service.createUdflugt(tmpKonference, tmplokalitet, tmpBeskrivelse, tmpPris, tmpStartDato, tmpSlutDato, tmpHasFrokost);
			}
		}

		this.hide();
	}
	
	/**
	 * Metode der åbner vinduet til at oprette prisgruppe
	 */
	private void createPrisgruppeAction()
	{
		if (konference != null)
		{
			PrisgruppeWindow dia = new PrisgruppeWindow("Opret Prisgruppe", konference);
			dia.showAndWait();
		} else
		{
			PrisgruppeWindow dia = new PrisgruppeWindow("Opret Prisgruppe", prisgrupper);
			dia.showAndWait();
		}

		if (konference != null)
		{
			lvwPrisgrupper.getItems().setAll(konference.getPrisgrupper());
		} else
		{
			lvwPrisgrupper.getItems().setAll(prisgrupper);
		}

	}

	/**
	 * Metode der sletter prisgruppe
	 */
	private void deletePrisgruppeAction()
	{
		Prisgruppe prisgruppe = lvwPrisgrupper.getSelectionModel().getSelectedItem();

		Stage owner = (Stage) this.getScene().getWindow();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Fjern Prisgruppe");
		alert.initOwner(owner);
		alert.setHeaderText("Er du sikker?");

		// Wait for the modal dialog to close
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK)
		{
			if (konference != null)
			{
				Service.deletePrisgruppe(prisgruppe, konference);
				lvwPrisgrupper.getItems().setAll(konference.getPrisgrupper());
			} else
			{
				prisgrupper.remove(prisgruppe);
				lvwPrisgrupper.getItems().setAll(prisgrupper);
			}
		}

	}

	/**
	 * Metode der åbner vinduet til at oprette en udflugt
	 */
	private void createUdflugtAction()
	{
		if (konference != null)
		{
			UdflugtWindow dia = new UdflugtWindow("Opret Udflugt", konference);
			dia.showAndWait();
		} else
		{
			UdflugtWindow dia = new UdflugtWindow("Opret Udflugt", udflugter);
			dia.showAndWait();
		}

		if (konference != null)
		{
			lvwUdflugter.getItems().setAll(konference.getUdflugter());
		} else
		{
			lvwUdflugter.getItems().setAll(udflugter);
		}
	}

	/**
	 * Metode der sletter en udflugt
	 */
	private void deleteUdflugtAction()
	{
		Udflugt udflugt = lvwUdflugter.getSelectionModel().getSelectedItem();
		if (udflugt == null)
			return;

		Stage owner = (Stage) this.getScene().getWindow();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Fjern Udflugt");
		alert.initOwner(owner);
		alert.setHeaderText("Er du sikker?");

		// Wait for the modal dialog to close
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK)
		{						
			if (konference != null)
			{
				Service.deleteUdflugt(konference, udflugt);
				lvwUdflugter.getItems().setAll(konference.getUdflugter());
			} else
			{
				udflugter.remove(udflugt);
				lvwUdflugter.getItems().setAll(udflugter);
			}
		}

	}

}
