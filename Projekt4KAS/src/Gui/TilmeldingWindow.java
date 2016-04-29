package Gui;

import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
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
    private Label lblError, lblMiljøkonferencer, lblPrisgrupper, lblIndkvarteringstype,
    			lblLedsager, lblLedsagerNavn, lblUdflugter, lblHotel, lblFaciliteter, 
    			lblTotalPris, lblPrisUdregning, lblStartDato, lblSlutDato, lblStartDatoHotel, lblSlutDatoHotel;
    private RadioButton rbHotel;
    private RadioButton rbAndet;
    private HBox boxIndkvarteringsTyper = new HBox();
    private ToggleGroup groupIndkvarteringsTyper = new ToggleGroup();
    private String[] indkvarteringsTyper = {"Hotel", "Andet"};
    private CheckBox cbxLedsager;
    private Miljøkonference konference;
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
        paneKonference.setGridLinesVisible(true);
        paneKonference.setPadding(new Insets(10));
        paneKonference.setHgap(10);
        paneKonference.setVgap(10);
        paneKonference.setStyle("-fx-border-color: black");
    
        GridPane paneLedsager = new GridPane();
        pane.add(paneLedsager, 0, 1);
        paneLedsager.setGridLinesVisible(true);
        paneLedsager.setPadding(new Insets(10));
        paneLedsager.setHgap(10);
        paneLedsager.setVgap(10);
        paneLedsager.setStyle("-fx-border-color: black");
        
        GridPane paneHotel = new GridPane();
        pane.add(paneHotel, 1, 0);
        paneHotel.setGridLinesVisible(true);
        paneHotel.setPadding(new Insets(10));
        paneHotel.setHgap(10);
        paneHotel.setVgap(10);
        paneHotel.setStyle("-fx-border-color: black");
        
        GridPane panePris = new GridPane();
        pane.add(panePris, 1, 1);
        panePris.setGridLinesVisible(true);
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
        
        lblStartDato = new Label("Start Dato:");
        paneKonference.add(lblStartDato, 0, 2);
        
        dpStartDato = new DatePicker();
        paneKonference.add(dpStartDato, 0, 3);
        dpStartDato.setMaxWidth(200);
        dpStartDato.setMinWidth(200);
        
        lblSlutDato = new Label("Slut Dato:");
        paneKonference.add(lblSlutDato, 1, 2);
        
        dpSlutDato = new DatePicker();
        paneKonference.add(dpSlutDato, 1, 3);
        dpSlutDato.setMaxWidth(200);
        dpSlutDato.setMinWidth(200);
        
        lblIndkvarteringstype = new Label("Vælg indkvarteringstype:");
        paneKonference.add(lblIndkvarteringstype, 0, 4);
        
        for(int i = 0; i < indkvarteringsTyper.length; i++) {
        	RadioButton rb = new RadioButton();
        	rb.setText(indkvarteringsTyper[i]);
        	rb.setToggleGroup(groupIndkvarteringsTyper);
        	boxIndkvarteringsTyper.getChildren().add(rb);
        }
        
        paneKonference.add(boxIndkvarteringsTyper, 0, 5);
        
        
        cbxLedsager = new CheckBox("Ledsager");
        paneLedsager.add(cbxLedsager, 0, 0);
        
        lblLedsagerNavn = new Label("Navn:");
        paneLedsager.add(lblLedsagerNavn, 0, 1);
        
        txfLedsagerNavn = new TextField();
        paneLedsager.add(txfLedsagerNavn, 0, 2);
        txfLedsagerNavn.setMaxWidth(200);
        txfLedsagerNavn.setMinWidth(200);
        paneLedsager.setValignment(txfLedsagerNavn, VPos.TOP);

        
        lblUdflugter = new Label("Udflugter:");
        paneLedsager.add(lblUdflugter, 1, 1);
        
        lvwUdflugter = new ListView();
        paneLedsager.add(lvwUdflugter, 1, 2, 1, 2);
        lvwUdflugter.setMaxSize(200, 130);
        lvwUdflugter.setMinSize(200, 130);
        lvwUdflugter.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        lblStartDatoHotel = new Label("Indtjekning:");
        paneHotel.add(lblStartDatoHotel, 0, 0);
        
        dpStartDatoHotel = new DatePicker();
        paneHotel.add(dpStartDatoHotel, 0, 1);
        dpStartDatoHotel.setMaxWidth(200);
        dpStartDatoHotel.setMinWidth(200);
        
        lblSlutDatoHotel = new Label("Udtjekning:");
        paneHotel.add(lblSlutDatoHotel, 1, 0);
        
        dpSlutDatoHotel  = new DatePicker();
        paneHotel.add(dpSlutDatoHotel, 1, 1);
        dpSlutDatoHotel.setMaxWidth(200);
        dpSlutDatoHotel.setMinWidth(200);
        
        lblHotel = new Label("Hoteller:");
        paneHotel.add(lblHotel, 0, 2);
        
        lvwHoteller = new ListView();
        paneHotel.add(lvwHoteller, 0, 3);
        lvwHoteller.setMaxSize(200, 130);
        lvwHoteller.setMinSize(200, 130);
        
        lblFaciliteter = new Label("Faciliteter:");
        paneHotel.add(lblFaciliteter, 1, 2);
        
        lvwFaciliteter = new ListView();
        paneHotel.add(lvwFaciliteter, 1, 3);
        lvwFaciliteter.setMaxSize(200, 130);
        lvwFaciliteter.setMinSize(200, 130);
        lvwFaciliteter.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        
        lblTotalPris = new Label("Total Pris:");
        panePris.add(lblTotalPris, 0, 0);
        
        lblPrisUdregning = new Label("0 kr.");
        panePris.add(lblPrisUdregning, 1, 0);
        
        Button btnTilmeld = new Button("Tilmeld");
        pane.add(btnTilmeld, 0, 4);
        GridPane.setHalignment(btnTilmeld, HPos.RIGHT);
        //btnTilmeld.setOnAction(event -> this.tilmeldAction());
        
        Button btnAnuller = new Button("Anuller");
        pane.add(btnAnuller, 0, 4);
        GridPane.setHalignment(btnAnuller, HPos.LEFT);
        
        lblError = new Label();
        pane.add(lblError, 0, 5);
        lblError.setStyle("-fx-text-fill: red");

        this.initControls();
    }

    private void initControls() {
//        if (company != null) {
//            txfName.setText(company.getName());
//            txfHours.setText("" + company.getHours());
//        } else {
//            txfName.clear();
//            txfHours.clear();
//        }
    }

    // -------------------------------------------------------------------------

  	private void selectedKonferenceChanged()
	{
		this.updateControls();
	}
  	
  	private void selectedPrisgruppeChanged()
  	{
  		
  	}

	public void updateControls()
	{
		Miljøkonference konference = lvwMiljøkonferencer.getSelectionModel().getSelectedItem();
		
		if (konference != null)
		{
			lvwPrisgrupper.getItems().setAll(konference.getPrisgrupper());
		}
	}
	
//    private void cancelAction() {
//        this.hide();
//    }
//
//    private void okAction() {
//        String name = txfNavn.getText().trim();
//        if (name.length() == 0) {
//            lblError.setText("Name is empty");
//            return;
//        }
//
//        int hours = -1;
//        try {
//            hours = Integer.parseInt(txfPris.getText().trim());
//        } catch (NumberFormatException ex) {
//            // do nothing
//        }
//        if (hours < 0) {
//            lblError.setText("Hours is not a positive number");
//            return;
//        }
//
//        // Call service methods
////        if (company != null) {
////            Service.updateCompany(company, name, hours);
////        } else {
////            Service.createCompany(name, hours);
////        }
//
//        this.hide();
//    }

}
