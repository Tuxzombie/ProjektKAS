package Gui;

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

import Service.Service;

public class FirmaWindow extends Stage {
    public FirmaWindow(String title) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);



        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }


    // -------------------------------------------------------------------------

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


        		for (int i = 0; i < lblNames.length; i++)
        		{
        		
        			{
        				lblInput[i] = new Label(lblNames[i]);
        				pane.add(lblInput[i], 0, i);

        				txfInput[i] = new TextField();
        				pane.add(txfInput[i], 1, i);
        			}

        lblError = new Label();
        pane.add(lblError, 0, lblNames.length + 1);
        lblError.setStyle("-fx-text-fill: red");

        
        hbox = new HBox();
        btnOpret = new Button("Opret");
        btnAnuller = new Button("Anuller");
        btnAnuller.setOnAction(e -> this.btnAnullerAction());
        hbox.getChildren().add(btnOpret);
        btnOpret.setOnAction(e -> btnOpretAction());
        hbox.getChildren().add(btnAnuller);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        pane.add(hbox, 1, lblNames.length + 1);
        		}
    }

    // -------------------------------------------------------------------------

    private void btnAnullerAction() {
        this.hide();
    }

 
    private void btnOpretAction() {
    	
    	String navn = txfInput[0].getText().trim();
		
    	int cvrNr = -1;
		try
		{
			cvrNr = Integer.parseInt(txfInput[1].getText().trim());
		} catch (NumberFormatException ex)
		{
			// do nothing
		}

    	String vej = txfInput[2].getText().trim();

		int nr = -1;
		try
		{
			nr = Integer.parseInt(txfInput[3].getText().trim());
		} catch (NumberFormatException ex)
		{
			// do nothing
		}

		String etage = txfInput[4].getText().trim();

		int postNr = -1;
		try
		{
			postNr = Integer.parseInt(txfInput[5].getText().trim());
		} catch (NumberFormatException ex)
		{
			// do nothing
		}

		String by = txfInput[6].getText().trim();
		String land = txfInput[7].getText().trim();

		if (navn.length() == 0)
		{
			lblError.setText("Navn er tom");
			return;
		} else if (cvrNr <= 0)
		{
			lblError.setText("Cvr.nr. er ugyldigt");
			return;
		}

		else if (vej.length() == 0)
		{
			lblError.setText("Vej er ugyldig!");
			return;
		} else if (nr <= 0)
		{
			lblError.setText("Husnummer er ugyldigt!");
			return;
		} else if (postNr <= 0)
		{
			lblError.setText("Post. nr. er ugyldigt!");
			return;
		} else if (by.length() == 0)
		{
			lblError.setText("By er ugyldig!");
			return;
		} else if (land.length() == 0)
		{
			lblError.setText("Land er ugyldigt!");
			return;
		}
		else {
//    	Service.createFirma(txfInput[0].getText(), cvrNr, txfInput[2].getText()
//    		, nr, txfInput[3].getText(), postNr, txfInput[5].getText(), txfInput[7].getText());
			
			//her er en af de store pointer ved at oprette en million små variabler ;
	    	Service.createFirma(navn, cvrNr, vej, nr, etage, postNr, by, land);			
			
    	close();
		}
    }
    
    

}
