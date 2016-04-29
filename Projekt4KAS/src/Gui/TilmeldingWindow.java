package Gui;

import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
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
    private ListView<Facilitet> lvwFaciliter;
    private Label lblError, lblMiljøkonferencer, lblPrisgrupper, lblIndkvarteringstype,
    			lblLedsager, lblLedsagerNavn, lblUdflugter, lblHotel, lblFaciliteter, 
    			lblTotalPris, lblPrisUdregning;
    private HBox boxIndkvarteringsTyper = new HBox();
    private ToggleGroup groupIndkvarteringsTyper = new ToggleGroup();
    private String[] indkvarteringsTyper = {"Hotel", "Andet"};
    private CheckBox cbxLedsager;
    private Miljøkonference konference;
    
    
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
        pane.add(paneHotel, 0, 2);
        paneHotel.setGridLinesVisible(true);
        paneHotel.setPadding(new Insets(10));
        paneHotel.setHgap(10);
        paneHotel.setVgap(10);
        paneHotel.setStyle("-fx-border-color: black");
        
        GridPane panePris = new GridPane();
        pane.add(panePris, 0, 3);
        panePris.setGridLinesVisible(true);
        panePris.setPadding(new Insets(10));
        panePris.setHgap(10);
        panePris.setVgap(10);
        panePris.setStyle("-fx-border-color: black");

        lblMiljøkonferencer = new Label("Miljøkonferencer:");
        paneKonference.add(lblMiljøkonferencer, 0, 0);
        
        lvwMiljøkonferencer = new ListView();
        lvwMiljøkonferencer.getItems().setAll(Service.getMiljøkonferencer());
		ChangeListener<Miljøkonference> listener = (ov, oldMiljøkonference, newMiljøkonference) -> this.selectedKonferenceChanged();
		lvwMiljøkonferencer.getSelectionModel().selectedItemProperty().addListener(listener);
        paneKonference.add(lvwMiljøkonferencer, 0, 1, 1, 1);
        lvwMiljøkonferencer.setMaxSize(200, 130);
        lvwMiljøkonferencer.setMinSize(200, 130);
        
        
        lblPrisgrupper = new Label("Prisgrupper:");
        paneKonference.add(lblPrisgrupper, 1, 0);
        
        lvwPrisgrupper = new ListView();
        paneKonference.add(lvwPrisgrupper, 1, 1, 1, 1);
        lvwPrisgrupper.setMaxSize(200, 130);
        lvwPrisgrupper.setMinSize(200, 130);
        
        for(int i = 0; i < indkvarteringsTyper.length; i++) {
        	RadioButton rb = new RadioButton();
        	rb.setText(indkvarteringsTyper[i]);
        	rb.setToggleGroup(groupIndkvarteringsTyper);
        	boxIndkvarteringsTyper.getChildren().add(rb);
        }
        
        paneKonference.add(boxIndkvarteringsTyper, 0, 2);
        
        
        cbxLedsager = new CheckBox("Ledsager");
        paneLedsager.add(cbxLedsager, 0, 0);
        
        lblLedsagerNavn = new Label("Navn:");
        paneLedsager.add(lblLedsagerNavn, 0, 1);
        
        txfLedsagerNavn = new TextField();
        paneLedsager.add(txfLedsagerNavn, 0, 2);
        
        lblUdflugter = new Label("Udflugter:");
        paneLedsager.add(lblUdflugter, 1, 0);
        
        lvwUdflugter = new ListView();
        paneLedsager.add(lvwUdflugter, 1, 1, 1, 2);
        lvwUdflugter.setMaxSize(200, 130);
        lvwUdflugter.setMinSize(200, 130);
        
        Button btnOK = new Button("Tilmeld");
        pane.add(btnOK, 0, 4);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
        //btnOK.setOnAction(event -> this.okAction());
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
