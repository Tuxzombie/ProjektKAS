package Gui;

import Model.Deltager;
import Model.Firma;
import Model.Ledsager;
import Service.Service;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DeltagerWindow extends Stage
{
	private Deltager deltager;

	public DeltagerWindow(String title, Deltager deltager)
	{
		this.initStyle(StageStyle.UTILITY);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setMinHeight(100);
		this.setMinWidth(200);
		this.setResizable(false);

		this.deltager = deltager;

		this.setTitle(title);
		GridPane pane = new GridPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		this.setScene(scene);
	}

	public DeltagerWindow(String title)
	{
		this(title, null);
	}

	// -------------------------------------------------------------------------
	private TextField[] txfInput;
	private Label[] lblInput;
	private Label lblError;
	private String[] lblNames;
	private ListView<Firma> lvwFirmaer;
	private ListView<Ledsager> lvwLedsagere;

	// -------------------------------------------------------------------------
	private void initContent(GridPane pane)
	{
		pane.setGridLinesVisible(false);
		pane.setPadding(new Insets(20));
		pane.setHgap(10);
		pane.setVgap(10);

		lblNames = new String[]
		{"Deltagernavn:", "Tlf.nr.:", "Vej:", "Nr:", "Etage:", "Postnr:", "By", "Land:"};

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

		lblError = new Label();
		pane.add(lblError, 0, MAX_ROWS + 2, 2, 1);
		lblError.setStyle("-fx-text-fill: red");

		this.initControls();
	}

	private void initControls()
	{
		if (deltager != null)
		{

			txfInput[0].setText(deltager.getNavn());
			txfInput[1].setText("" + deltager.getTelefonNr());
			txfInput[2].setText(deltager.getAdresse().getVej());
			txfInput[3].setText("" + deltager.getAdresse().getNr());
			txfInput[4].setText(deltager.getAdresse().getEtage());
			txfInput[5].setText("" + deltager.getAdresse().getPostNr());
			txfInput[6].setText(deltager.getAdresse().getBy());
			txfInput[7].setText(deltager.getAdresse().getLand());

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
		}
	}

	// -------------------------------------------------------------------------

	private void cancelAction()
	{

		this.hide();
	}

	private void okAction()
	{
		// firma
		// ledsager,
		String navn = txfInput[0].getText().trim();

		int telefonNr = -1;
		try
		{
			telefonNr = Integer.parseInt(txfInput[1].getText().trim());
		} catch (NumberFormatException ex)
		{
			// do nothing
		}
		// prisgruppe

		String vej = txfInput[2].getText().trim();

		int nr = -1;
		try
		{
			nr = Integer.parseInt(txfInput[3].getText().trim());
		} catch (NumberFormatException ex)
		{
			// do nothing
		}

		String etage = txfInput[4].getText().trim();

		int postNr = -1;
		try
		{
			postNr = Integer.parseInt(txfInput[5].getText().trim());
		} catch (NumberFormatException ex)
		{
			// do nothing
		}

		String by = txfInput[6].getText().trim();
		String land = txfInput[7].getText().trim();

		if (navn.length() == 0)
		{
			lblError.setText("Navn er tom");
			return;
		} else if (telefonNr <= 0)
		{
			lblError.setText("Telefon nr er ugyldigt");
			return;
		}

		else if (vej.length() == 0)
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
		
		Firma firma = null;
		try
		{
			firma = lvwFirmaer.getSelectionModel().getSelectedItem();
		} catch (NullPointerException ex)
		{
			// do nothing
		}


		Ledsager ledsager = null;
		try
		{
			ledsager = lvwLedsagere.getSelectionModel().getSelectedItem();
		} catch (NullPointerException ex)
		{
			// do nothing
		}
		

		if (deltager != null)
		{
			Service.updateDeltager(deltager, firma, ledsager, navn, telefonNr, null, vej, nr, etage, postNr, by, land);
		} else
		{
			Service.createDeltager(navn, telefonNr, null, vej, nr, etage, postNr, by, land);
		}

		this.hide();

	}

}
