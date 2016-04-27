package Gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BlanketWindow extends Stage
{
    //-------------------------------------------------------------------------------
    //									fields
    //-------------------------------------------------------------------------------
    private TextField[] txfInputDeltager;
    private Label[] lblInputDeltager, lblInputProgram;
    private String[] lblNamesDeltager, lblNamesProgram, lblNamesOvernatning;
    private CheckBox[] chbDeltager, chbProgram, chbOvernatning;
	
    //-------------------------------------------------------------------------------
    //									Constructor
    //-------------------------------------------------------------------------------
	public BlanketWindow(String title, Stage owner)
	{
		   this.initOwner(owner);
	        this.initStyle(StageStyle.UTILITY);
	        this.initModality(Modality.APPLICATION_MODAL);
	        this.setMinHeight(100);
	        this.setMinWidth(200);
	        this.setResizable(false);

	        this.setTitle(title);
	        GridPane pane = new GridPane();
	        this.initContent(pane);

	        Scene scene = new Scene(pane);
	        this.setScene(scene);
	}
	
    private void initContent(GridPane pane) 
    {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        
        //-------------------------------------------------------------------------------
        //									labels
        //-------------------------------------------------------------------------------
        Label lblOverskrift = new Label("Blanket for tilmelding til miljøkonferencen "
        								+ "\"Hav og Himmel\".\n" + "18/4" + "-" 
        								+ "20/4" + "2016" + " på" + " Odense Universitet");
        lblOverskrift.setStyle("-fx-font-size: 30px; -fx-border-color: black;");
        pane.add(lblOverskrift, 0, 0);
        
        Label lblDeltagerInformation = new Label("Deltagerinformation");
        lblDeltagerInformation.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        pane.add(lblDeltagerInformation, 0, 1);
        
        Label lblProgramForLedsagere = new Label("Program for ledsagere");
        lblProgramForLedsagere.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        pane.add(lblProgramForLedsagere, 0, 3);
        
        Label lblOvernatningsønsker = new Label("Overnatningsønsker");
        lblOvernatningsønsker.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        pane.add(lblOvernatningsønsker, 0, 5);
        
        
        //-------------------------------------------------------------------------------
        //									panes
        //-------------------------------------------------------------------------------
        GridPane deltagerInformationPane = new GridPane();
        deltagerInformationPane.setStyle("-fx-border-color: black");
        pane.add(deltagerInformationPane, 0, 2);
        
        deltagerInformationPane.setGridLinesVisible(false);
        deltagerInformationPane.setPadding(new Insets(20));
        deltagerInformationPane.setHgap(10);
        deltagerInformationPane.setVgap(10);
        

        lblNamesDeltager = new String[]{"Deltagernavn:", "Adresse:", "By/Land:", "Ankomstdato:" 
        				, "Firmanavn:", "Foredragsholder:", "" , "Tlf.nr.:", "Afrejsedato:"
        				, "Firma tlf.nr.:"};
        txfInputDeltager = new TextField[lblNamesDeltager.length];
        lblInputDeltager = new Label[lblNamesDeltager.length];
        
        final int MAX_ROWS_DELTAGER = 5;
        
        for (int i = 0; i < lblNamesDeltager.length; i++)
		{
        	if (i < MAX_ROWS_DELTAGER)
			{
    			lblInputDeltager[i] = new Label(lblNamesDeltager[i]);
    			deltagerInformationPane.add(lblInputDeltager[i], 0, i);
    			
    			txfInputDeltager[i] = new TextField();
    			deltagerInformationPane.add(txfInputDeltager[i], 1, i);
			}
        	else
        	{
    			lblInputDeltager[i] = new Label(lblNamesDeltager[i]);
    			deltagerInformationPane.add(lblInputDeltager[i], 2, i-MAX_ROWS_DELTAGER);
    			
    			txfInputDeltager[i] = new TextField();
    			deltagerInformationPane.add(txfInputDeltager[i], 3, i-MAX_ROWS_DELTAGER);
        		
        	}

		}
        
        GridPane programForLedsagerePane = new GridPane();
        programForLedsagerePane.setStyle("-fx-border-color: black");
        pane.add(programForLedsagerePane, 0, 4);
        
        lblNamesProgram = new String[]{"Ledsagernavn", "Byrundtur, Odense","Egeskov"
        								,"Trapholdt Museum, Kolding", "", "18/4-16", "19/4-16"
        								,"20/4-16" , "",  "Kr. 125 incl. Frokost", "Kr. 75"
        								,"Kr. 200 incl. frokost", "Sæt kryds","","",""};
        lblInputProgram= new Label[lblNamesProgram.length];
        chbProgram = new CheckBox[3];
        
        final int MAX_ROWS_PROGRAM = 4;
        final int WIDTH_PROGRAM_1 = 180;
        final int WIDTH_PROGRAM_2 = WIDTH_PROGRAM_1/2;
        
        for (int i = 0; i < lblNamesProgram.length; i++)
		{
			if (i < MAX_ROWS_PROGRAM)
			{
				lblInputProgram[i] = new Label(lblNamesProgram[i]);
				lblInputProgram[i].setMinWidth(WIDTH_PROGRAM_1);
				lblInputProgram[i].setMaxWidth(WIDTH_PROGRAM_1);
				lblInputProgram[i].setStyle("-fx-border-color: black");
				programForLedsagerePane.add(lblInputProgram[i], 0, i);
			}
			else if (i-MAX_ROWS_PROGRAM < MAX_ROWS_PROGRAM)
			{
				lblInputProgram[i] = new Label(lblNamesProgram[i]);
				lblInputProgram[i].setMinWidth(WIDTH_PROGRAM_2);
				lblInputProgram[i].setMaxWidth(WIDTH_PROGRAM_2);
				lblInputProgram[i].setStyle("-fx-border-color: black");
				programForLedsagerePane.add(lblInputProgram[i], 1, i-MAX_ROWS_PROGRAM);
			}
			else if (i-2*MAX_ROWS_PROGRAM < MAX_ROWS_PROGRAM)
			{
				lblInputProgram[i] = new Label(lblNamesProgram[i]);
				lblInputProgram[i].setMinWidth(WIDTH_PROGRAM_1);
				lblInputProgram[i].setMaxWidth(WIDTH_PROGRAM_1);
				lblInputProgram[i].setStyle("-fx-border-color: black");
				programForLedsagerePane.add(lblInputProgram[i], 2, i-2*MAX_ROWS_PROGRAM );
			}	
			else 
			{
				lblInputProgram[i] = new Label(lblNamesProgram[i]);
				lblInputProgram[i].setMinWidth(WIDTH_PROGRAM_2);
				lblInputProgram[i].setMaxWidth(WIDTH_PROGRAM_2);
				lblInputProgram[i].setStyle("-fx-border-color: black");
				programForLedsagerePane.add(lblInputProgram[i], 3, i-3*MAX_ROWS_PROGRAM);
			}

		}
        lblInputProgram[0].setStyle(null);
		lblInputProgram[4].setStyle(null);
		lblInputProgram[8].setStyle(null);
		
		for (int i = 0; i < chbProgram.length; i++)
		{
			chbProgram[i] = new CheckBox();
			chbProgram[i].setAlignment(Pos.TOP_CENTER);
			programForLedsagerePane.add(chbProgram[i], 3, i+1);
		}
        
        
        GridPane overnatningsønskerPane = new GridPane();
        overnatningsønskerPane.setStyle("-fx-border-color: black");
        pane.add(overnatningsønskerPane, 0, 6);
    }

}
