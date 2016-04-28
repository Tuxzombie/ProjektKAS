package Gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import Model.*;
import Service.Service;


public class HotelPane extends GridPane {
    private TextField txfNavn, txfTlfNr, txfLedsager, txfIndskvartering;
    private ListView<Hotel> lvwHoteller;
    private TextArea txaAdresse, txaGæster, txaFaciliteter;
    
//	private String navn;
//	private double prisEnkeltVærelse;
//	private double prisDobbeltVærelse;
//	private ArrayList<Facilitet> faciliteter;
//	private ArrayList<Hotelbooking> hotelbookinger;
    
    private final int FIELD_WIDTH = 261;

    public HotelPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        
        Label lblComp = new Label("Hoteller");
        this.add(lblComp, 0, 0);

        lvwHoteller = new ListView<>();
        this.add(lvwHoteller, 0, 1, 1, 5);
        lvwHoteller.setMinSize(200, 330);
        lvwHoteller.setMaxSize(200, 330);
        lvwHoteller.getItems().setAll(this.initAllHotelList());
        ChangeListener<Hotel> listener =
            (ov, oldHotel, newHotel) -> this.selectedHotelChanged();
        lvwHoteller.getSelectionModel().selectedItemProperty().addListener(listener);

        Label lblNavn = new Label("Navn:");
        this.add(lblNavn, 1, 1);

        txfNavn = new TextField();
        this.add(txfNavn, 2, 1);
        txfNavn.setPrefWidth(FIELD_WIDTH);
        txfNavn.setEditable(false);
        
        Label lblAdresse = new Label("Adresse:");
        this.add(lblAdresse, 1, 2);
        
        txaAdresse = new TextArea();
        txaAdresse.setMaxSize(FIELD_WIDTH, 80);
        txaAdresse.setMinSize(FIELD_WIDTH, 80);
        this.add(txaAdresse, 2, 2);

        Label lblTlfNr = new Label("Telefon nr.:");
        this.add(lblTlfNr, 1, 3);

        txfTlfNr = new TextField();
        this.add(txfTlfNr, 2, 3);
        txfTlfNr.setEditable(false);

        Label lblGæster = new Label("Gæster:");
        this.add(lblGæster, 1, 4);
        
        txaGæster = new TextArea();
        txaGæster.setMaxSize(FIELD_WIDTH, 80);
        txaGæster.setMinSize(FIELD_WIDTH, 80);
        this.add(txaGæster, 2, 4);
        
        Label lblFaciliteter = new Label("Faciliteter:");
        this.add(lblFaciliteter, 1, 5);
        
        txaFaciliteter = new TextArea();
        txaFaciliteter.setMaxSize(FIELD_WIDTH, 80);
        txaFaciliteter.setMinSize(FIELD_WIDTH, 80);
        this.add(txaFaciliteter, 2, 5);
        
      
        HBox hbxButtons = new HBox(40);
        this.add(hbxButtons, 0, 6, 3, 1);
//        hbxButtons.setPadding(new Insets(10, 0, 0, 0));
        hbxButtons.setAlignment(Pos.BASELINE_LEFT);

        Button btnCreateHotel = new Button("Opret \nHotel");
        btnCreateHotel.setMinWidth(80);
        btnCreateHotel.setTextAlignment(TextAlignment.CENTER);
        hbxButtons.getChildren().add(btnCreateHotel);
        btnCreateHotel.setOnAction(event -> this.createHotelAction());

        Button btnUpdateHotel = new Button("Opdater \nHotel");
        btnUpdateHotel.setMinWidth(80);
        btnUpdateHotel.setTextAlignment(TextAlignment.CENTER);
        hbxButtons.getChildren().add(btnUpdateHotel);
        btnUpdateHotel.setOnAction(event -> this.updateHotelAction());

        Button btnDeleteHotel = new Button("Slet \nHotel");
        btnDeleteHotel.setMinWidth(80);
        btnDeleteHotel.setTextAlignment(TextAlignment.CENTER);
        hbxButtons.getChildren().add(btnDeleteHotel);
        btnDeleteHotel.setOnAction(event -> this.deleteHotelAction());
        
        Button btnCreateFacilitet = new Button("Opret \nFacilitet");
        btnCreateFacilitet.setMinWidth(80);
        btnCreateFacilitet.setTextAlignment(TextAlignment.CENTER);
        hbxButtons.getChildren().add(btnCreateFacilitet);
        btnCreateFacilitet.setDisable(true);
        btnCreateFacilitet.setOnAction(event -> this.createFacilitetAction());
        
        Button btnDeleteFacilitet = new Button("Slet \nFacilitet");
        btnDeleteFacilitet.setMinWidth(80);
        btnDeleteFacilitet.setTextAlignment(TextAlignment.CENTER);
        hbxButtons.getChildren().add(btnDeleteFacilitet);
        btnDeleteFacilitet.setDisable(true);
        btnDeleteFacilitet.setOnAction(event -> this.deleteFacilitetAction());
        

        if (lvwHoteller.getItems().size() > 0) {
            lvwHoteller.getSelectionModel().select(0);
        }
    }

    private ArrayList<Hotel> initAllHotelList() {
        ArrayList<Hotel> list = new ArrayList<>();
//        for (Miljøkonference konf : Service.getMiljøkonference()) {
//            list.add(konf);
//        }
        return list;
    }

    // -------------------------------------------------------------------------

    private void createFacilitetAction()
    {
    	
    }
    
    private void deleteFacilitetAction()
    {
    	
    }
    
    private void createHotelAction() {
        HotelWindow dia = new HotelWindow("Opret Hotel");
        dia.showAndWait();

        // Wait for the modal dialog to close

        lvwHoteller.getItems().setAll(this.initAllHotelList());
        this.updateControls();
    }

    private void updateHotelAction() {
        Hotel hotel = lvwHoteller.getSelectionModel().getSelectedItem();
        if (hotel == null)
            return;

        HotelWindow dia = new HotelWindow("Update Employee", hotel);
        dia.showAndWait();

        // Wait for the modal dialog to close

        int selectIndex = lvwHoteller.getSelectionModel().getSelectedIndex();
        lvwHoteller.getItems().setAll(this.initAllHotelList());
        lvwHoteller.getSelectionModel().select(selectIndex);
    }

    private void deleteHotelAction() {
    	Hotel hotel = lvwHoteller.getSelectionModel().getSelectedItem();
        if (hotel == null)
            return;

        Stage owner = (Stage) this.getScene().getWindow();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Employee");
        alert.initOwner(owner);
        alert.setHeaderText("Are you sure?");

        // Wait for the modal dialog to close
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
//            Service.deleteHotel(hotel);
            lvwHoteller.getItems().setAll(this.initAllHotelList());
            this.updateControls();
        }
    }

    // -------------------------------------------------------------------------

    private void selectedHotelChanged() {
        this.updateControls();
    }

    public void updateControls() {
//        Employee employee = lvwKonference.getSelectionModel().getSelectedItem();
//        if (employee != null) {
//            txfName.setText(employee.getName());
//            txfWage.setText("kr " + employee.getWage());
//            //txfEmploymentYear.setText(""+employee.getEmploymentYear());
//            if (employee.getCompany() != null) {
//                txfCompany.setText("" + employee.getCompany());
//                txfSalary.setText("kr " + employee.weeklySalary());
//                txfEmploymentYear.setText(""+employee.getEmploymentYear());
//            } else {
//                txfCompany.clear();
//                txfSalary.clear();
//                txfEmploymentYear.clear();
//            }
//        } else {
//            txfName.clear();
//            txfWage.clear();
//            txfCompany.clear();
//            txfSalary.clear();
//            txfEmploymentYear.clear();
//        }
    }

}
