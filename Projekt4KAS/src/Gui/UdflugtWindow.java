package Gui;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

import Model.*;

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
	private ListView<Udflugt> lvwUdflugter;
	private TextArea txaDeltagere;

	// --------------------------------------------------------------------------
//	private String lokalitet;
//	private String beskrivelse;
//	private double pris;
//	private LocalDate startDato;
//	private LocalDate slutDato;
//	private boolean hasFrokost;
//	
	
	private void initContent(GridPane pane)
	{
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		Label lblUdflugter = new Label("Udflugter:");
		pane.add(lblUdflugter, 0, 0);
		
		lvwUdflugter = new ListView<>();
		pane.add(lvwUdflugter, 0, 1);
		lvwUdflugter.setMinSize(300, 160);
		lvwUdflugter.setMaxSize(300, 160);
		lvwUdflugter.getItems().setAll(konference.getUdflugter());
		ChangeListener<Udflugt> listener = (ov, oldUdflugt, newUdflugt) -> this.selectedUdflugtChanged();
		lvwUdflugter.getSelectionModel().selectedItemProperty().addListener(listener);

		Label lblDeltagere = new Label("Deltagere:");
		pane.add(lblDeltagere, 1, 0);
		
		txaDeltagere = new TextArea();
		txaDeltagere.setMinSize(300, 160);
		txaDeltagere.setMaxSize(300, 160);
		txaDeltagere.setEditable(false);
		pane.add(txaDeltagere, 1, 1);

		this.updateControls();
	}

	private void updateControls()
	{
		Udflugt udflugt = lvwUdflugter.getSelectionModel().getSelectedItem();
		
		if (udflugt != null)
		{
			StringBuilder sbDeltagere = new StringBuilder();
			
			for (Ledsager led : udflugt.getLedsagere())
			{
				sbDeltagere.append(led.getNavn() + "(" + led.getDeltager().getNavn() + ", " + led.getDeltager().getTelefonNr() + ")\n");
			}
			
				txaDeltagere.setText(sbDeltagere.toString());
		}
		else
		{
			txaDeltagere.clear();
		}

	}

	private void selectedUdflugtChanged()
	{
		this.updateControls();
	}

}
