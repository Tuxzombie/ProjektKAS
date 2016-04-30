package Gui;

import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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

public class UdflugtWindow extends Stage {
    private Miljøkonference konference;

    public UdflugtWindow(String title, Miljøkonference konference) {
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

    //--------------------------------------------------------------------------

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        
		lvwUdflugter = new ListView<>();
		pane.add(lvwUdflugter, 0, 1, 1, 6);
		lvwUdflugter.setMinSize(200, 330);
		lvwUdflugter.setMaxSize(200, 330);
		lvwUdflugter.getItems().setAll(konference.getUdflugter());
		ChangeListener<Udflugt> listener = (ov, oldUdflugt, newUdflugt) -> this.selectedUdflugtChanged();
		lvwUdflugter.getSelectionModel().selectedItemProperty().addListener(listener);
		
		

        this.initControls();
    }

    private void initControls() {
        if (konference != null) {
    	
        	

        } else {

        }
    }

    private void selectedUdflugtChanged()
    {
    	
    }
    


}
