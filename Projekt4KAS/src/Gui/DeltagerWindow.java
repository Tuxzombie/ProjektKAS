package Gui;


import Model.Adresse;
import Model.Deltager;
import Model.Prisgruppe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DeltagerWindow extends Stage
{
	private Deltager deltager;

    public DeltagerWindow(String title, Deltager deltager) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setMinHeight(100);
        this.setMinWidth(200);
        this.setResizable(false);
        
        this.deltager = deltager;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }
    
    public DeltagerWindow(String title) {
        this(title, null);
    }

    // -------------------------------------------------------------------------
    private TextField[] txfInput;
    private Label[] lblInput;
    private String[] lblNames;

     // -------------------------------------------------------------------------
    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        
        lblNames = new String[]{"Deltagernavn:", "Vej:", "Nr:", "Etage:"
        				, "Postnr:", "Land:", "Ankomstdato:", "Afrejsedato:" , "Firmanavn:"
        				, "Foredragsholder:", "Tlf.nr.:", "Firma tlf.nr.:"};
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
			}
        	else
        	{
    			lblInput[i] = new Label(lblNames[i]);
    			pane.add(lblInput[i], 2, i-MAX_ROWS);
    			
    			txfInput[i] = new TextField();
    			pane.add(txfInput[i], 3, i-MAX_ROWS);
        		
        	}

		}

        Button btnOK = new Button("OK");
        pane.add(btnOK, 0, MAX_ROWS+1);
        btnOK.setOnAction(event -> this.okAction());
        
        Button btnCancel = new Button("Cancel");
        pane.add(btnCancel, 1, MAX_ROWS+1);
        btnCancel.setOnAction(event -> this.cancelAction());

    }

    // -------------------------------------------------------------------------

    private void cancelAction() {

        this.hide();
    }

    private void okAction() {
     	
//    	Adresse adresse = new Adresse(txfInput[2].getText(),Integer.parseInt(txfInput[3].getText())
//    								, txfInput[4].getText(),Integer.parseInt(txfInput[5].getText())
//    								, txfInput[6].getText());
    	//Deltager svend = new Deltager(txfInput[0].getText(), adresse, txfInput[9].getText(), ledsager, indkvartering, tilmelding);
    	
    	if(checkTxfValues())
    	{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Opret Deltager");
            alert.setHeaderText("Mangelde oplysninger");
            alert.setContentText("");
            alert.show();
    	}

    }
    
    private boolean checkTxfValues()
    {
        //0,2,3,5,6,7,10,11
    	if (txfInput[0].getText().equalsIgnoreCase("")
    		&& txfInput[2].getText().equalsIgnoreCase("")
    		&& txfInput[3].getText().equalsIgnoreCase("")
    		&& txfInput[5].getText().equalsIgnoreCase("")
    		&& txfInput[6].getText().equalsIgnoreCase("")
    		&& txfInput[7].getText().equalsIgnoreCase("")
    		&& txfInput[10].getText().equalsIgnoreCase("")
    		&& txfInput[11].getText().equalsIgnoreCase("")   		
    		)
		{
    		return true;
		}
    	else
    	{
    		return false;
    	}
    }

}
