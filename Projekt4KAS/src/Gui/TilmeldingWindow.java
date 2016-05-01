package Gui;

import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.time.Period;


import Model.*;
import Service.Service;

public class TilmeldingWindow extends Stage {
    private Deltager deltager;

    public TilmeldingWindow(String title, Deltager deltager) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.deltager = deltager;
        
        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public TilmeldingWindow(String title) {
        this(title, null);
    }

    // -------------------------------------------------------------------------

    private TextField txfLedsagerNavn;
    private ListView<Miljøkonference> lvwMiljøkonferencer;
    private ListView<Prisgruppe> lvwPrisgrupper;
    private ListView<Udflugt> lvwUdflugter;
    private ListView<Hotel> lvwHoteller;
    private ListView<Facilitet> lvwFaciliteter;
    private ListView<Firma> lvwFirmaer;
    private Label lblError, lblMiljøkonferencer, lblPrisgrupper, lblIndkvarteringstype,
    			lblLedsager, lblLedsagerNavn, lblUdflugter, lblHotel, lblFaciliteter, 
    			lblTotalPris, lblPrisUdregning, lblStartDato, lblSlutDato, lblStartDatoHotel, 
    			lblSlutDatoHotel, lblFirmaer;
    private RadioButton rbHotel;
    private RadioButton rbAndet;
    private HBox boxButtons = new HBox();
    private HBox boxIndkvarteringsTyper = new HBox();
    private ToggleGroup groupIndkvarteringsTyper = new ToggleGroup();
    private CheckBox cbxLedsager;
    private Button btnOpretFirma;
    private Miljøkonference konference;
    private Prisgruppe prisgruppe;
    private Hotelbooking hotelbooking;
    private Tilmelding tilmelding;
    private Hotel hotel;
    private DatePicker dpStartDato;
    private DatePicker dpSlutDato;
    private DatePicker dpStartDatoHotel;
    private DatePicker dpSlutDatoHotel;
    
    
    
    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        
        GridPane paneKonference = new GridPane();
        pane.add(paneKonference, 0, 0);
        paneKonference.setGridLinesVisible(false);
        paneKonference.setPadding(new Insets(10));
        paneKonference.setHgap(10);
        paneKonference.setVgap(10);
        paneKonference.setStyle("-fx-border-color: black");
    
        GridPane paneLedsager = new GridPane();
        pane.add(paneLedsager, 0, 1);
        paneLedsager.setGridLinesVisible(false);
        paneLedsager.setPadding(new Insets(10));
        paneLedsager.setHgap(10);
        paneLedsager.setVgap(10);
        paneLedsager.setStyle("-fx-border-color: black");
        
        GridPane paneHotel = new GridPane();
        pane.add(paneHotel, 1, 0);
        paneHotel.setGridLinesVisible(false);
        paneHotel.setPadding(new Insets(10));
        paneHotel.setHgap(10);
        paneHotel.setVgap(10);
        paneHotel.setStyle("-fx-border-color: black");
 
  
        
        GridPane paneFirma = new GridPane();
        pane.add(paneFirma, 1, 1);
        paneFirma.setGridLinesVisible(false);
        paneFirma.setPadding(new Insets(10));
        paneFirma.setHgap(10);
        paneFirma.setVgap(10);
        paneFirma.setStyle("-fx-border-color: black");
    
        lblFirmaer = new Label("Firmaer:");
        paneFirma.add(lblFirmaer, 0, 0);
        
        
        btnOpretFirma = new Button("Opret");
        paneFirma.add(btnOpretFirma, 1, 0);
        GridPane.setHalignment(btnOpretFirma, HPos.RIGHT);
        btnOpretFirma.setOnAction(e -> opretFirmaAction());
        btnOpretFirma.setDisable(true);
                
        lvwFirmaer = new ListView();
        paneFirma.add(lvwFirmaer, 0, 1, 2, 1);
        lvwFirmaer.setMaxSize(410, 130);
        lvwFirmaer.setMinSize(410, 130);
        lvwFirmaer.setDisable(true);
        
        GridPane panePris = new GridPane();
        pane.add(panePris, 1, 2);
        panePris.setGridLinesVisible(false);
        panePris.setPadding(new Insets(10));
        panePris.setHgap(10);
        panePris.setVgap(10);
        panePris.setStyle("-fx-border-color: black");
        
        lblMiljøkonferencer = new Label("Miljøkonferencer:");
        paneKonference.add(lblMiljøkonferencer, 0, 0);
        
        lvwMiljøkonferencer = new ListView();
        lvwMiljøkonferencer.getItems().setAll(Service.getMiljøkonferencer());
		ChangeListener<Miljøkonference> listenerMiljøkonference = (ov, oldMiljøkonference, newMiljøkonference) -> this.selectedKonferenceChanged();
		lvwMiljøkonferencer.getSelectionModel().selectedItemProperty().addListener(listenerMiljøkonference);
        paneKonference.add(lvwMiljøkonferencer, 0, 1, 1, 1);
        lvwMiljøkonferencer.setMaxSize(200, 130);
        lvwMiljøkonferencer.setMinSize(200, 130);
                
        
        lblPrisgrupper = new Label("Prisgrupper:");
        paneKonference.add(lblPrisgrupper, 1, 0);
        
        lvwPrisgrupper = new ListView();
        ChangeListener<Prisgruppe> listenerPrisgruppe = (ov, oldPrisgruppe, newPrisgruppe) -> this.selectedPrisgruppeChanged();
        lvwPrisgrupper.getSelectionModel().selectedItemProperty().addListener(listenerPrisgruppe);
        paneKonference.add(lvwPrisgrupper, 1, 1, 1, 1);
        lvwPrisgrupper.setMaxSize(200, 130);
        lvwPrisgrupper.setMinSize(200, 130);
        lvwPrisgrupper.setDisable(true);
        

        
        lblStartDato = new Label("Start Dato:");
        paneKonference.add(lblStartDato, 0, 2);
        
        dpStartDato = new DatePicker();
        paneKonference.add(dpStartDato, 0, 3);
        dpStartDato.setMaxWidth(200);
        dpStartDato.setMinWidth(200);
        dpStartDato.setDisable(true);
        
        lblSlutDato = new Label("Slut Dato:");
        paneKonference.add(lblSlutDato, 1, 2);
        
        dpSlutDato = new DatePicker();
        paneKonference.add(dpSlutDato, 1, 3);
        dpSlutDato.setMaxWidth(200);
        dpSlutDato.setMinWidth(200);
        dpSlutDato.setDisable(true);

        
        lblIndkvarteringstype = new Label("Vælg indkvarteringstype:");
        paneKonference.add(lblIndkvarteringstype, 0, 4);
        
        
        rbHotel = new RadioButton("Hotel");
        rbHotel.setToggleGroup(groupIndkvarteringsTyper);
        boxIndkvarteringsTyper.getChildren().add(rbHotel);
        
        rbAndet = new RadioButton("Andet");
        rbAndet.setToggleGroup(groupIndkvarteringsTyper);
        boxIndkvarteringsTyper.getChildren().add(rbAndet);
        
        groupIndkvarteringsTyper.selectedToggleProperty().addListener(e -> this.toggleRadioButton());
        
        boxIndkvarteringsTyper.setDisable(true);
        paneKonference.add(boxIndkvarteringsTyper, 0, 5);
        
        
        cbxLedsager = new CheckBox("Ledsager");
        paneLedsager.add(cbxLedsager, 0, 0);
        cbxLedsager.setDisable(true);
        cbxLedsager.setOnAction(e -> this.checkboxLedsagerAction());
        
        lblLedsagerNavn = new Label("Navn:");
        paneLedsager.add(lblLedsagerNavn, 0, 1);
        
        txfLedsagerNavn = new TextField();
        paneLedsager.add(txfLedsagerNavn, 0, 2);
        txfLedsagerNavn.setMaxWidth(200);
        txfLedsagerNavn.setMinWidth(200);
        GridPane.setValignment(txfLedsagerNavn, VPos.TOP);
        txfLedsagerNavn.setDisable(true);

        
        lblUdflugter = new Label("Udflugter:");
        paneLedsager.add(lblUdflugter, 1, 1);
        
        lvwUdflugter = new ListView();
        paneLedsager.add(lvwUdflugter, 1, 2, 1, 2);
        lvwUdflugter.setMaxSize(200, 130);
        lvwUdflugter.setMinSize(200, 130);
        lvwUdflugter.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvwUdflugter.setDisable(true);
        

        
        lblStartDatoHotel = new Label("Indtjekning:");
        paneHotel.add(lblStartDatoHotel, 0, 0);
        
        dpStartDatoHotel = new DatePicker();
        paneHotel.add(dpStartDatoHotel, 0, 1);
        dpStartDatoHotel.setMaxWidth(200);
        dpStartDatoHotel.setMinWidth(200);
        dpStartDatoHotel.setDisable(true);
        
        lblSlutDatoHotel = new Label("Udtjekning:");
        paneHotel.add(lblSlutDatoHotel, 1, 0);
        
        dpSlutDatoHotel  = new DatePicker();
        paneHotel.add(dpSlutDatoHotel, 1, 1);
        dpSlutDatoHotel.setMaxWidth(200);
        dpSlutDatoHotel.setMinWidth(200);
        dpSlutDatoHotel.setDisable(true);
        
        lblHotel = new Label("Hoteller:");
        paneHotel.add(lblHotel, 0, 2);
        
        lvwHoteller = new ListView();
        paneHotel.add(lvwHoteller, 0, 3);
        lvwHoteller.setMaxSize(200, 130);
        lvwHoteller.setMinSize(200, 130);
        lvwHoteller.setDisable(true);
        ChangeListener<Hotel> listenerHoteller = (ov, oldHotel, newHotel) -> this.selectedHotelChanged();
        lvwHoteller.getSelectionModel().selectedItemProperty().addListener(listenerHoteller);


        
        lblFaciliteter = new Label("Faciliteter:");
        paneHotel.add(lblFaciliteter, 1, 2);
        
        lvwFaciliteter = new ListView();
        paneHotel.add(lvwFaciliteter, 1, 3);
        lvwFaciliteter.setMaxSize(200, 130);
        lvwFaciliteter.setMinSize(200, 130);
        lvwFaciliteter.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvwFaciliteter.setDisable(true);
        
        
        lblTotalPris = new Label("Total Pris:");
        panePris.add(lblTotalPris, 0, 0);
        lblTotalPris.setStyle("-fx-text-fill: green; -fx-font-size: 20pt;");

        
        lblPrisUdregning = new Label("0 kr.");
        panePris.add(lblPrisUdregning, 1, 0);
        lblPrisUdregning.setStyle("-fx-text-fill: green; -fx-font-size: 20pt;");

        
        Button btnTilmeld = new Button("Tilmeld");
        btnTilmeld.setOnAction(event -> this.btnTilmeldAction());
        
        Button btnAnuller = new Button("Anuller");
        btnAnuller.setOnAction(event -> this.btnAnullerAction());
        boxButtons.getChildren().add(btnTilmeld);
        boxButtons.getChildren().add(btnAnuller);
        pane.add(boxButtons, 1, 4);
        boxButtons.setSpacing(10);
        boxButtons.setAlignment(Pos.CENTER_RIGHT);
        
        lblError = new Label();
        pane.add(lblError, 0, 4);
        lblError.setStyle("-fx-text-fill: red");
      
        ChangeListener<Facilitet> listenerFaciliteter = (ov, old, newFacilitet) -> this.updatePris();
        lvwFaciliteter.getSelectionModel().selectedItemProperty().addListener(listenerFaciliteter);
        
        ChangeListener<Udflugt> listenerUdflugter = (ov, old, newUdflugt) -> this.updatePris();
        lvwUdflugter.getSelectionModel().selectedItemProperty().addListener(listenerUdflugter);
        
        dpStartDato.setOnHidden(e -> startDatoAction());
        dpSlutDato.setOnHidden(e -> slutDatoAction());
        
        dpStartDatoHotel.setOnHidden(e -> startDatoHotelAction());
        dpSlutDatoHotel.setOnHidden(e -> slutDatoHotelAction());
 

    }


    // -------------------------------------------------------------------------
    
  	private void selectedKonferenceChanged()
	{
		konference = lvwMiljøkonferencer.getSelectionModel().getSelectedItem();
		
		if (konference != null && konference.getPrisgrupper().size() != 0)
		{
			lvwPrisgrupper.getItems().setAll(konference.getPrisgrupper());
			lvwPrisgrupper.setDisable(false);
		}
		else
		{
			lvwPrisgrupper.setDisable(true);
			lvwPrisgrupper.getItems().clear();
		}
	}
  	
  	private void selectedPrisgruppeChanged()
  	{
  		prisgruppe = lvwPrisgrupper.getSelectionModel().getSelectedItem();
  		
  		if (prisgruppe != null)
		{
			boxIndkvarteringsTyper.setDisable(false);
			Service.updateDeltager(deltager, deltager.getFirma(), deltager.getLedsager(), deltager.getNavn(),
					deltager.getTelefonNr(), lvwPrisgrupper.getSelectionModel().getSelectedItem(), 
					deltager.getAdresse().getVej(), deltager.getAdresse().getNr(), 
					deltager.getAdresse().getEtage(), deltager.getAdresse().getPostNr(), 
					deltager.getAdresse().getBy(), deltager.getAdresse().getLand());
			dpStartDato.setDisable(false);
			dpSlutDato.setDisable(false);
			dpStartDato.setValue(lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getStartDato()); //Sætter dpStartDato og dpSlutDato til konferencens start og slut dato
			dpSlutDato.setValue(lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getSlutDato());
			updatePris();

 		}
  		else
  		{
  			boxIndkvarteringsTyper.setDisable(true);
  			dpStartDato.setValue(null);
  			dpSlutDato.setValue(null);
  			dpStartDato.setDisable(true);
			dpSlutDato.setDisable(true);
			updatePris();

  		}
  	}

  	public void selectedHotelChanged() {
  		
  		hotel = lvwHoteller.getSelectionModel().getSelectedItem();
  		
  		if(hotel != null) {
  			dpStartDatoHotel.setDisable(false);
  			dpSlutDatoHotel.setDisable(false);
  			
  			dpStartDatoHotel.setValue(lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getStartDato());
  			dpSlutDatoHotel.setValue(lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getSlutDato());
  			
  			lvwFaciliteter.setDisable(false);
  			lvwFaciliteter.getItems().setAll(hotel.getFaciliteter());
  			updatePris();
  			lvwFirmaer.setDisable(false);
  		}
  		else {
  			dpStartDatoHotel.setDisable(true);
  			dpSlutDatoHotel.setDisable(true);
  			dpStartDatoHotel.setValue(null);
  			dpSlutDatoHotel.setValue(null);
  			updatePris();
  		}
  	}
  	
  	public void toggleRadioButton() {
  		if(rbHotel.isSelected()) {
  			lvwHoteller.setDisable(false);
  			lvwHoteller.getItems().setAll(Service.getHoteller());
  			cbxLedsager.setDisable(false);
  			updatePris();
  			lvwFirmaer.setDisable(false);
  			btnOpretFirma.setDisable(false);
  			lvwFirmaer.getItems().setAll(Service.getFirmaer());
  			
  		}
  		else {
  			lvwHoteller.setDisable(true);
  			lvwHoteller.getSelectionModel().clearSelection();
  			lvwHoteller.getItems().setAll();
  			lvwFaciliteter.setDisable(true);
  			lvwFaciliteter.getSelectionModel().clearSelection();
  			lvwFaciliteter.getItems().setAll();
  			dpStartDatoHotel.setValue(null);
  			dpSlutDatoHotel.setValue(null);
  			dpStartDatoHotel.setDisable(true);
  			dpSlutDatoHotel.setDisable(true);
  			cbxLedsager.setDisable(false);
  			lvwFirmaer.setDisable(false);
  			btnOpretFirma.setDisable(false);
  			lvwFirmaer.getItems().setAll(Service.getFirmaer());

  			updatePris();

   		}
  	}
  	
  	public void checkboxLedsagerAction() {
  		if(cbxLedsager.isSelected()) {
  			txfLedsagerNavn.setDisable(false);
  			lvwUdflugter.setDisable(false);
  			lvwUdflugter.getItems().setAll(lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getUdflugter());
  			updatePris();
  		}
  		else {
  			txfLedsagerNavn.setDisable(true);
  			lvwUdflugter.setDisable(true);
  			txfLedsagerNavn.clear();
  			lvwUdflugter.getItems().setAll();
  			updatePris();
  		}
  	}

  	public void startDatoAction () {
  		if(dpStartDato.getValue().isEqual(lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getStartDato()) || 
  				dpStartDato.getValue().isAfter(lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getStartDato()) &&
  				dpStartDato.getValue().isEqual(lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getSlutDato()) ||
  				dpStartDato.getValue().isBefore(lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getSlutDato()) &&
  				dpStartDato.getValue().isEqual(dpSlutDato.getValue()) ||
  				dpStartDato.getValue().isAfter(dpSlutDato.getValue())){
  			updatePris();
  		}
  		else {
  			lblError.setText("Dato er ugyldig! Startdato skal være indenfor konferencens aktive periode og før slutdatoen! (" 
  					+ lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getStartDato() + " til " + 
  					lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getSlutDato());
  			dpStartDato.setValue(lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getStartDato());
  			updatePris();

  		}
  	} 
  	
  	public void slutDatoAction () {
  		if(dpSlutDato.getValue().isEqual(lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getSlutDato()) || 
  				dpSlutDato.getValue().isAfter(lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getStartDato()) &&
  				dpSlutDato.getValue().isEqual(lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getSlutDato()) ||
  				dpSlutDato.getValue().isBefore(lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getSlutDato())){
  			updatePris();
  		}
  		else {
  			lblError.setText("Dato er ugyldig! Slutdato skal være indenfor konferencens aktive periode og efter startdatoen! (" 
  					+ lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getStartDato() + " til " + 
  					lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getSlutDato());
  			dpSlutDato.setValue(lvwMiljøkonferencer.getSelectionModel().getSelectedItem().getSlutDato());
  			updatePris();

  		}
  	}
  	
  	public void startDatoHotelAction () {
  		if(dpStartDatoHotel.getValue().isBefore(dpSlutDatoHotel.getValue())) {
  			updatePris();
  		}
  		else {
  			lblError.setText("Dato er ugyldig! Indkvartering skal være før udtjekning!"); 
  			dpStartDatoHotel.setValue(dpSlutDatoHotel.getValue().minusDays(1));
  			updatePris();

  		}
  	}
  	
  	public void slutDatoHotelAction () {
  		if(dpSlutDatoHotel.getValue().isAfter(dpStartDatoHotel.getValue())) {
  			updatePris();
  		}
  		else {
  			lblError.setText("Dato er ugyldig! Udtjekning skal være efter indtjekning!"); 
  			dpSlutDatoHotel.setValue(dpStartDatoHotel.getValue().plusDays(1));
  			updatePris();

  		}
  	} 
  	
	public void btnTilmeldAction()
	{
		Hotelbooking hotelbooking = Service.createHotelbooking(lvwHoteller.getSelectionModel().getSelectedItem(), cbxLedsager.isSelected());
		Indkvartering indkvartering;
		if(rbHotel.isSelected()) {
			indkvartering = Service.createIndkvarteringMedHotelbooking(dpStartDatoHotel.getValue(), dpSlutDatoHotel.getValue());
		}
		else {
			indkvartering = Service.createIndkvarteringMedHotelbooking(dpStartDato.getValue(), dpSlutDato.getValue());
		}
		
		Ledsager ledsager;
		if(txfLedsagerNavn.getLength() > 0) {
				ledsager = Service.createLedsager(txfLedsagerNavn.getText(), deltager);
		}
		else {
			ledsager = Service.createLedsager(txfLedsagerNavn.getText(), deltager);
		}
		
		Service.createTilmelding(lvwMiljøkonferencer.getSelectionModel().getSelectedItem(), this.deltager, 
				dpStartDato.getValue(), dpSlutDato.getValue(), indkvartering);
		Service.connectIndkvarteringOgHotelbooking(indkvartering, hotelbooking);
		for (Facilitet valgtFacilitet : lvwFaciliteter.getSelectionModel().getSelectedItems()) {
			Service.addFacilitetTilHotelbooking(hotelbooking, valgtFacilitet);
		}
		for (Udflugt valgtUdflugt : lvwUdflugter.getSelectionModel().getSelectedItems()) {
			Service.addLedsagerTilUdflugt(valgtUdflugt, ledsager);
		}
		
		if(lvwFirmaer.getSelectionModel().getSelectedItem() != null) {
			Service.addDeltagerTilFirma(lvwFirmaer.getSelectionModel().getSelectedItem(), this.deltager);
		}
		
		close();
	}
	
	public void updatePris() {
		int nætterPåHotel = 0;
		if(dpStartDatoHotel.getValue() != null && dpSlutDatoHotel.getValue() != null) {
			Period periodeHotel = Period.between(dpStartDatoHotel.getValue(), dpSlutDatoHotel.getValue());		
			nætterPåHotel = periodeHotel.getDays();
		}
		
		double prisDobbeltværelse = 0.0; 
		double prisEnkeltværelse = 0.0;
		if(lvwHoteller.getSelectionModel().getSelectedItem() != null) {
			prisDobbeltværelse = lvwHoteller.getSelectionModel().getSelectedItem().getPrisDobbeltVærelse();
			prisEnkeltværelse = lvwHoteller.getSelectionModel().getSelectedItem().getPrisEnkeltVærelse();
		}
		
		double prisgruppeKvote = 0.0;
		if (lvwPrisgrupper.getSelectionModel().getSelectedItem() != null) {
			prisgruppeKvote = lvwPrisgrupper.getSelectionModel().getSelectedItem().getPris(); 
		}
		
		Period periodeTilmelding;
		int dageTilmeldtKonference = 0;
		if(dpStartDato != null && dpSlutDato != null){
			periodeTilmelding = Period.between(dpStartDato.getValue(), dpSlutDato.getValue());		
			dageTilmeldtKonference = periodeTilmelding.getDays() + 1; //+1 for at inkludere slutdatoen
		}
		
		double facilitetPris = 0.0;
			for (Facilitet valgtFacilitet : lvwFaciliteter.getSelectionModel().getSelectedItems()) {
				facilitetPris += valgtFacilitet.getPris();
			}
		
		double hotelPris = 0.0;
		if(cbxLedsager.isSelected()) {
			hotelPris = nætterPåHotel * prisDobbeltværelse + facilitetPris;	
		}
		else hotelPris = nætterPåHotel * prisEnkeltværelse + facilitetPris;	
		
			
		double udflugtPris = 0.0;
			for (Udflugt valgtUdflugt : lvwUdflugter.getSelectionModel().getSelectedItems()) {
				udflugtPris += valgtUdflugt.getPris();
			}
		
		double tilmeldingsPris = dageTilmeldtKonference * prisgruppeKvote + udflugtPris;
			
		double totalPris = hotelPris + tilmeldingsPris;
		lblPrisUdregning.setText(totalPris + " kr.");
		
	}
	
	public void opretFirmaAction() {
		FirmaWindow dia = new FirmaWindow("Opret firma");
		dia.showAndWait();
		lvwFirmaer.getItems().setAll(Service.getFirmaer());
	}

	public void btnAnullerAction() {
		close();
	}
}
