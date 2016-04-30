package Gui;

import javafx.geometry.HPos;
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
import Service.Service;

public class FirmaWindow extends Stage {
    private Firma firma;

    public FirmaWindow(String title, Firma firma) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.firma = firma;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public FirmaWindow(String title) {
        this(title, null);
    }

    // -------------------------------------------------------------------------

    //Service.createFirma(navn, cvrNr, vej, nr, etage, postNr, land, by)
	private TextField[] txfInput;
	private Label[] lblInput;
	private String[] lblNames;
    private Label lblError;
    private Button btnOpret, btnAnuller;
    private HBox hbox;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        lblNames = new String[]
        		{"Firmanavn:", "CVR. nr.:", "Vej:", "Nr:", "Etage:", "Postnr:", "By", "Land:"};

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

        lblError = new Label();
        pane.add(lblError, 0, 5);
        lblError.setStyle("-fx-text-fill: red");

        
        hbox = new HBox();
        btnOpret = new Button("Opret");
        btnAnuller = new Button("Anuller");
        btnAnuller.setOnAction(e -> this.btnAnullerAction());
        hbox.getChildren().add(btnOpret);
        hbox.getChildren().add(btnAnuller);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        pane.add(hbox, 1, MAX_ROWS + 1);
        		}
    }

    private void initControls() {

    }

    // -------------------------------------------------------------------------

    private void btnAnullerAction() {
        this.hide();
    }

    private void btnOpretAction() {
    }
    

}
