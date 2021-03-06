package Gui;

import Model.Miljøkonference;
import Model.Prisgruppe;
import Service.Service;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrisgrupperDeleteWindow extends Stage
{
	private Miljøkonference konference;

	public PrisgrupperDeleteWindow(String title, Miljøkonference konference)
	{
		this.initStyle(StageStyle.UTILITY);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);

		this.konference = konference;

		this.setTitle(title);
		GridPane pane = new GridPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		this.setScene(scene);
	}
	
	private ListView<Prisgruppe> lvwPrisgrupper;
	
	/**
	 * initialisere vinduet
	 * @param pane
	 */
	private void initContent(GridPane pane)
	{
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);
		
        lvwPrisgrupper = new ListView<>();
        pane.add(lvwPrisgrupper, 0, 0, 2, 1);
        lvwPrisgrupper.setMinSize(200, 100);
        lvwPrisgrupper.setMaxSize(200, 100);
        lvwPrisgrupper.getItems().setAll(konference.getPrisgrupper());
        
		Button btnOK = new Button("Slet");
		pane.add(btnOK, 0, 1);
		btnOK.setOnAction(event -> this.okAction());

		Button btnCancel = new Button("Annuller");
		pane.add(btnCancel, 1, 1);
		btnCancel.setOnAction(event -> this.cancelAction());
		
	}
	
	/**
	 * Opretter / opdatere konference objekt
	 */
	private void okAction()
	{
		Service.deletePrisgruppe(lvwPrisgrupper.getSelectionModel().getSelectedItem(), konference);
		
		this.hide();
	}
	
	/**
	 * Lukker vinduet
	 */
	private void cancelAction()
	{
		this.hide();
	}
	
}
