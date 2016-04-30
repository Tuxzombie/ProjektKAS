package Gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Service.*;

import Model.*;

public class MainApp extends Application
{

	// -------------------------------------------------------------------------
	public static void main(String[] args)
	{
		Application.launch(args);
	}
	
    @Override
    public void init() {
		Service.initStorage();
    }

	@Override
	public void start(Stage stage)
	{
		stage.setTitle("KAS system");
		GridPane pane = new GridPane();
		paneClone = pane;
		this.initContent(pane);

		Scene scene = new Scene(pane);
		sceneClone = scene;
		stage.setScene(scene);
		stage.show();
		
		stageClone = stage;
		
	}

	// -------------------------------------------------------------------------

	private final Controller controller = new Controller();
	private Button[] btnMainMenu;
	private String[] btnNames;
	private GridPane paneClone;
	private Scene sceneClone;
	private Stage stageClone;
	private ListView<Miljøkonference> lvwMiljø;
	private ListView<Deltager> lvwDeltagere;
	private ListView<Ledsager> lvwLedsagere;
	private ListView<Hotel> lvwHoteller;

	// -------------------------------------------------------------------------
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
	    
	    Tab tabUdflugter = new Tab();
	    tabUdflugter.setText("Udflugter");
	    tabUdflugter.setClosable(false);
	    
	    UdflugtPane udflugtpane = new UdflugtPane();
	    tabUdflugter.setContent(udflugtpane);
	    tabPane.getTabs().add(tabUdflugter);
	 
	    
	    pane.add(tabPane, 0, 0, 1, 3);
		
		
		lvwMiljø = new ListView<Miljøkonference>();
		lvwDeltagere = new ListView<Deltager>();
		lvwLedsagere = new ListView<Ledsager>();
		lvwHoteller = new ListView<Hotel>();
		
		btnNames = new String[]{"Opret \nDeltager", "Opret \nKonference", "Opret \nHotel", "Opret \nArrengement"
					, "Opret \nUdflugter", "Opret \nFirma", "Opret \nLedsager", "Opret \nUdflugter"
					, "Opret ...", "Tilknyt \ndeltager \ntil firma", "Vis \nOversigt"
					, "Vis \npris", "Tilmeld \ntil \nkonference", "Vis \nBlanket"};
		
		btnMainMenu = new Button[btnNames.length];
		

		
//		for (int i = 8; i < btnNames.length; i++)
//		{
//			btnMainMenu[i] = new Button(btnNames[i]);
//			pane.add(btnMainMenu[i], 1, i-8);
//			btnMainMenu[i].setMinSize(100, 40);
//			btnMainMenu[i].setMaxSize(100, 40);
//		}
//		
//		btnMainMenu[8].setOnAction(event -> this.controller.opretAction());
//		btnMainMenu[btnNames.length-1].setOnAction(event -> this.controller.visBlanketAction());
		
		
	}
	

	// -------------------------------------------------------------------------
	private class Controller
	{
	/*
	 * Viser opret knapperne	
	 */
		public void opretAction()
		{
//			for (int i = 0; i < 8; i++)
//			{
//				btnMainMenu[i] = new Button(btnNames[i]);
//				paneClone.add(btnMainMenu[i], 2, i);
//				btnMainMenu[i].setMinSize(100, 40);
//				btnMainMenu[i].setMaxSize(100, 40);
//			}
//			
//			stageClone.sizeToScene();
//			btnMainMenu[0].setOnAction(event -> this.opretDeltagerAction());
		}
		
		/*
		 * åbper opret deltager viduet
		 */
		public void opretDeltagerAction()
		{
			
		}
		
		/*
		 * Åbner den samlede tilmeldingsblanket
		 */
		public void visBlanketAction()
		{

		}
		
	}

}
