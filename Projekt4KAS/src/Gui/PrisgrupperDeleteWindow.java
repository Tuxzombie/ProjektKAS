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
	
	private void initContent(GridPane pane)
	{
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);
		
        lvwPrisgrupper = new ListView<>();
        pane.add(lvwPrisgrupper, 0, 0, 1, 1);
        lvwPrisgrupper.setMinSize(200, 330);
        lvwPrisgrupper.setMaxSize(200, 330);
        lvwPrisgrupper.getItems().setAll(konference.getPrisgrupper());
        
		Button btnOK = new Button("OK");
		pane.add(btnOK, 0, 1);
		btnOK.setOnAction(event -> this.okAction());

		Button btnCancel = new Button("Cancel");
		pane.add(btnCancel, 1, 1);
		btnCancel.setOnAction(event -> this.cancelAction());
		
	}
	
	private void okAction()
	{
		konference.removePrisgruppe(lvwPrisgrupper.getSelectionModel().getSelectedItem());
	}
	
	private void cancelAction()
	{
		this.hide();
	}
	
}
