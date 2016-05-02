package Gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Service.*;

public class MainApp extends Application
{

	public static void main(String[] args)
	{
		Application.launch(args);
	}
	
	/**
	 * initialisere storage med nogle værdier
	 */
    @Override
    public void init() {
		Service.initStorage();
    }

    /**
     * opretter pane
     */
	@Override
	public void start(Stage stage)
	{
		stage.setTitle("KAS system");
		GridPane pane = new GridPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.show();
		
	}

	/**
	 * initialisere data i tabs
	 * @param pane
	 */
	private void initContent(GridPane pane)
	{
		pane.setGridLinesVisible(false);
		pane.setPadding(new Insets(20));
		pane.setHgap(10);
		pane.setVgap(10);
		
		TabPane tabPane = new TabPane();
		

	    
		Tab tabKonference = new Tab();
	    tabKonference.setText("Miljøkonferencer");
	    tabKonference.setClosable(false);
	   	    
	    KonferencePane konferencepane = new KonferencePane();
	    tabKonference.setContent(konferencepane);
	    tabPane.getTabs().add(tabKonference);
	    
		Tab tabDeltager = new Tab();
	    tabDeltager.setText("Deltagere");
	    tabDeltager.setClosable(false);
	   	    
	    DeltagerPane deltagerpane = new DeltagerPane();
	    tabDeltager.setContent(deltagerpane);
	    tabPane.getTabs().add(tabDeltager);
	    
		Tab tabHotel = new Tab();
	    tabHotel.setText("Hoteller");
	    tabHotel.setClosable(false);
	    
	    HotelPane hotelpane = new HotelPane();
	    tabHotel.setContent(hotelpane);
	    tabPane.getTabs().add(tabHotel);
	    
	    pane.add(tabPane, 0, 0, 1, 3);
		
	}

}
