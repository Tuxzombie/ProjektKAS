package Gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

import Model.*;
import Service.Service;

public class HotelWindow extends Stage {
    private Hotel hotel;

    public HotelWindow(String title, Hotel hotel) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.hotel = hotel;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public HotelWindow(String title) {
        this(title, null);
    }

    // -------------------------------------------------------------------------

	private TextField[] txfInput;
	private Label[] lblInput;
	private String[] lblNames;
	private ListView<Facilitet> lvwFaciliteter;
	private Button btnOK;
	private Button btnAnuller;
	private Button btnTilføjFacilitet;
	private Button btnFjernFacilitet;
	private HBox boxBotBtns = new HBox();
	private HBox boxFacilitetBtns = new HBox();
	private Label lblFaciliteter;
	private ArrayList<Facilitet> faciliteter = new ArrayList<>();
    
	/**
	 * initialisere vinduet
	 * @param pane
	 */
    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        
        lblNames = new String[] {"Navn", "Vej", "Nr.", "Etage", "Post nr.", "By", "Land", "Pris enkeltværelse", "Pris dobbelværelse"};
        txfInput = new TextField[lblNames.length];
		lblInput = new Label[lblNames.length];
      
		for (int i = 0; i < lblNames.length; i++)
		{
				lblInput[i] = new Label(lblNames[i]);
				pane.add(lblInput[i], 0, i);

				txfInput[i] = new TextField();
				pane.add(txfInput[i], 1, i);
		}
		
		lblFaciliteter = new Label("Faciliteter:");
		pane.add(lblFaciliteter, 2, 0);
		
		lvwFaciliteter = new ListView();
		pane.add(lvwFaciliteter, 2, 0, 1, lblNames.length);
		lvwFaciliteter.setMaxSize(200, 235);
		lvwFaciliteter.setMinSize(200, 235);
		
        btnAnuller = new Button("Anuller");
        btnAnuller.setOnAction(e -> this.anullerAction());
        
        btnOK = new Button("OK");
        btnOK.setOnAction(e -> this.okAction());

        boxBotBtns.getChildren().add(btnOK);
        boxBotBtns.getChildren().add(btnAnuller);
        pane.add(boxBotBtns, 2, lblNames.length + 3);
        boxBotBtns.setSpacing(10);
        boxBotBtns.setAlignment(Pos.CENTER_RIGHT);
        
        btnTilføjFacilitet = new Button("Tilføj");
        boxFacilitetBtns.getChildren().add(btnTilføjFacilitet);
        btnTilføjFacilitet.setOnAction(e -> btnTilføjAction());
        
        btnFjernFacilitet = new Button("Fjern");
        btnFjernFacilitet.setOnAction(e -> btnFjernAction());
        boxFacilitetBtns.getChildren().add(btnFjernFacilitet);
  
        boxFacilitetBtns.setAlignment(Pos.CENTER_RIGHT);
        boxFacilitetBtns.setSpacing(10);
        
        pane.add(boxFacilitetBtns, 2, lblNames.length - 1);
       
        
        this.initControls();
    }

    /**
     * initialisere data i felterne
     */
    private void initControls() {
    	if(hotel != null) {
    		txfInput[0].setText(hotel.getNavn());
    		txfInput[1].setText(hotel.getAdresse().getVej());
    		txfInput[2].setText(hotel.getAdresse().getNr() + "");
    		txfInput[3].setText(hotel.getAdresse().getEtage());
    		txfInput[4].setText(hotel.getAdresse().getPostNr() + "");
    		txfInput[5].setText(hotel.getAdresse().getBy());
    		txfInput[6].setText(hotel.getAdresse().getLand());
    		txfInput[7].setText(hotel.getPrisEnkeltVærelse() + "");
    		txfInput[8].setText(hotel.getPrisDobbeltVærelse() + "");
    		lvwFaciliteter.getItems().setAll(hotel.getFaciliteter());
    	
    	}
    }

    // -------------------------------------------------------------------------

    /**
     * Metode til at tilføje facilitet
     */
    private void btnTilføjAction() {
    	
    	FacilitetWindow dia;
    	if(hotel == null) {
    		dia = new FacilitetWindow("Tilføj Facilitet", this.faciliteter);
    	}
    	else dia = new FacilitetWindow("Tilføj Facilitet", this.hotel);

    	dia.showAndWait();
    	if(hotel == null) {
    		lvwFaciliteter.getItems().setAll(faciliteter);
    	}
    	else{
    		lvwFaciliteter.getItems().setAll(hotel.getFaciliteter());
    	}
    
    }
    
    /**
     * Metode til at fjerne facilitet
     */
    private void btnFjernAction() {
    	Service.deleteFacilitet(this.hotel, lvwFaciliteter.getSelectionModel().getSelectedItem());
		lvwFaciliteter.getItems().setAll(hotel.getFaciliteter());
    }
    
    /**
     * metode der lukker vinduet
     */
    private void anullerAction() {
    	close();
    }

    /**
     * Metode der opretter hotellet
     */
    private void okAction() {
    	for (int i = 0; i < lblNames.length; i++) {
			if(txfInput[i].getLength() == 0) {
				if(i == 3) {
					break;
				}
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Indtast " + lblNames[i].toLowerCase());
				alert.showAndWait();
				return;
			}
		}
    	
    	
    	int nr = -1;
    	int postNr = -1;
    	double prisEnkeltVærelse = -1;
    	double prisDobbeltVærelse = -1;
    	
    	try{
    		nr = Integer.parseInt(txfInput[2].getText());
    		postNr = Integer.parseInt(txfInput[4].getText());
    		prisEnkeltVærelse = Double.parseDouble(txfInput[7].getText());
	    	prisDobbeltVærelse = Double.parseDouble(txfInput[8].getText());
    	}
    	catch (NullPointerException ex){
    		//Do nothing
    	}

			if(nr > 0 && postNr > 0 && prisEnkeltVærelse > 0 && prisDobbeltVærelse > 0) {
			
				if(hotel == null) 	{
				Hotel hotel = Service.createHotel(txfInput[0].getText(), prisEnkeltVærelse, prisDobbeltVærelse, txfInput[1].getText(), 
						nr, txfInput[3].getText(), postNr, txfInput[5].getText(), txfInput[6].getText());
					if(faciliteter.size() > 0) {
						for (Facilitet facilitet : faciliteter) {
							Service.createFacilitet(hotel, facilitet.getFacilitetNavn(), facilitet.getPris());
							}
						}
					}
				
				else {
				Service.updateHotel(hotel, txfInput[0].getText(), prisEnkeltVærelse, prisDobbeltVærelse, txfInput[1].getText(), 
							nr, txfInput[3].getText(), postNr, txfInput[5].getText(), txfInput[6].getText());
				}	
			}
				else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Nr., postnummer og priser skal være tal!");
				alert.showAndWait();
				}
			


			
		
			    
			
    	this.hide();
    }

}
