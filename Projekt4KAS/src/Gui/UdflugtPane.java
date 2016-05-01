package Gui;

import Model.Ledsager;
import Model.Miljøkonference;
import Model.Tilmelding;
import Model.Udflugt;
import Service.Service;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class UdflugtPane extends GridPane
{
	
	private ListView<Miljøkonference> lvwKonference;
	private ListView<Udflugt> lvwUdflugt;
	private TextArea txaLedsagere;
	private final int SIZE_H = 200;
	private final int SIZE_W = 280;
	
	public UdflugtPane()
	{
//		this.setPadding(new Insets(20));
//		this.setHgap(20);
//		this.setVgap(10);
		this.setGridLinesVisible(false);
		
		Label lblKonf = new Label("Miljøkonferencer");
		this.add(lblKonf, 0, 0);
		Label lblUdflugter = new Label("Udflugter");
		this.add(lblUdflugter, 1, 0);
		Label lblLedsagere = new Label("Ledsager (telefon nr., Deltager)");
		this.add(lblLedsagere, 0, 2);
		
		lvwKonference = new ListView<>();
		this.add(lvwKonference, 0, 1);
		lvwKonference.setMinSize(SIZE_W, SIZE_H);
		lvwKonference.setMaxSize(SIZE_W, SIZE_H);
		lvwKonference.getItems().setAll(Service.getMiljøkonferencer());
		ChangeListener<Miljøkonference> listenerKonference = (ov, oldMiljøkonference, newMiljøkonference) -> this.selectedKonferenceChanged();
		lvwKonference.getSelectionModel().selectedItemProperty().addListener(listenerKonference);
		
		lvwUdflugt = new ListView<>();
		this.add(lvwUdflugt, 1, 1);
		lvwUdflugt.setMinSize(SIZE_W, SIZE_H);
		lvwUdflugt.setMaxSize(SIZE_W, SIZE_H);
		ChangeListener<Udflugt> listenerUdflugt = (ov, oldUdflugt, newUdflugt) -> this.selectedUdflugtChanged();
		lvwUdflugt.getSelectionModel().selectedItemProperty().addListener(listenerUdflugt);
		
		txaLedsagere = new TextArea();
		txaLedsagere.setEditable(false);
		txaLedsagere.setMinSize(SIZE_W, SIZE_H);
		txaLedsagere.setMaxSize(SIZE_W, SIZE_H);
		this.add(txaLedsagere, 0, 3, 2, 1);
	}
	
	private void selectedUdflugtChanged()
	{
		Udflugt udflugt = lvwUdflugt.getSelectionModel().getSelectedItem();
		
		if (udflugt != null)
		{
			StringBuilder sbLedsagere = new StringBuilder();
			
			for (Ledsager led : udflugt.getLedsagere())
			{
				sbLedsagere.append(led.getNavn() + " (" + led.getDeltager().getTelefonNr() 
									+ ", " + led.getDeltager().getNavn() + ")" );
			}
			txaLedsagere.setText(sbLedsagere.toString());
		}
		else
		{
			txaLedsagere.clear();
		}

	}
	
	private void selectedKonferenceChanged()
	{
		Miljøkonference konference = lvwKonference.getSelectionModel().getSelectedItem();
		if (konference != null)
		{
			lvwUdflugt.getItems().setAll(konference.getUdflugter());

		} else
		{
			lvwUdflugt.getItems().clear();
			txaLedsagere.clear();
		}
	}
}
