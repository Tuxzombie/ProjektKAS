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

public class DeltagerPane extends GridPane
{
	private TextField txfNavn, txfTlfNr,
			txfLedsager,
			txfIndskvartering;
	private ListView<Deltager> lvwDeltagere;
	private TextArea txaAdresse,
			txaMiljøkonferencer;

	private final int FIELD_WIDTH = 227;

	public DeltagerPane()
	{
		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		Label lblComp = new Label("Deltagere");
		this.add(lblComp, 0, 0);

		lvwDeltagere = new ListView<>();
		this.add(lvwDeltagere, 0, 1, 1, 6);
		lvwDeltagere.setMinSize(200, 330);
		lvwDeltagere.setMaxSize(200, 330);
		lvwDeltagere.getItems().setAll(this.initAllDeltagerList());
		ChangeListener<Deltager> listener = (ov, oldDeltager, newDeltager) -> this.selectedDeltagerChanged();
		lvwDeltagere.getSelectionModel().selectedItemProperty().addListener(listener);

		Label lblTitel = new Label("Navn:");
		this.add(lblTitel, 1, 1);

		txfNavn = new TextField();
		this.add(txfNavn, 2, 1);
		txfNavn.setMinWidth(FIELD_WIDTH);
		txfNavn.setMaxWidth(FIELD_WIDTH);
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
		txfTlfNr.setMinWidth(FIELD_WIDTH);
		txfTlfNr.setMaxWidth(FIELD_WIDTH);
		this.add(txfTlfNr, 2, 3);
		txfTlfNr.setEditable(false);

		Label lblLedsager = new Label("Ledsager:");
		this.add(lblLedsager, 1, 4);

		txfLedsager = new TextField();
		txfLedsager.setMinWidth(FIELD_WIDTH);
		txfLedsager.setMaxWidth(FIELD_WIDTH);
		this.add(txfLedsager, 2, 4);
		txfLedsager.setEditable(false);

		Label lblIndkvartering = new Label("Indkvartering:");
		this.add(lblIndkvartering, 1, 5);

		txfIndskvartering = new TextField();
		txfIndskvartering.setMinWidth(FIELD_WIDTH);
		txfIndskvartering.setMaxWidth(FIELD_WIDTH);
		this.add(txfIndskvartering, 2, 5);
		txfIndskvartering.setEditable(false);

		Label lblMiljøkonferencer = new Label("Miljøkonferencer:");
		this.add(lblMiljøkonferencer, 1, 6);

		txaMiljøkonferencer = new TextArea();
		txaMiljøkonferencer.setMaxSize(FIELD_WIDTH, 80);
		txaMiljøkonferencer.setMinSize(FIELD_WIDTH, 80);
		this.add(txaMiljøkonferencer, 2, 6);

		HBox hbxButtons = new HBox(40);
		this.add(hbxButtons, 0, 7, 3, 1);
		// hbxButtons.setPadding(new Insets(10, 0, 0, 0));
		hbxButtons.setAlignment(Pos.BASELINE_LEFT);

		Button btnCreateDeltager = new Button("Opret \nDeltager");
		btnCreateDeltager.setMinWidth(80);
		btnCreateDeltager.setTextAlignment(TextAlignment.CENTER);
		hbxButtons.getChildren().add(btnCreateDeltager);
		btnCreateDeltager.setOnAction(event -> this.createActionDeltager());

		Button btnUpdateDeltager = new Button("Opdater \nDeltager");
		btnUpdateDeltager.setMinWidth(80);
		btnUpdateDeltager.setTextAlignment(TextAlignment.CENTER);
		hbxButtons.getChildren().add(btnUpdateDeltager);
		btnUpdateDeltager.setOnAction(event -> this.updateActionDeltager());

		Button btnDeleteDeltager = new Button("Slet \nDeltager");
		btnDeleteDeltager.setMinWidth(80);
		btnDeleteDeltager.setTextAlignment(TextAlignment.CENTER);
		hbxButtons.getChildren().add(btnDeleteDeltager);
		btnDeleteDeltager.setOnAction(event -> this.deleteActionDeltager());

		Button btnCreateLedsager = new Button("Opret \nLedsager");
		btnCreateLedsager.setMinWidth(80);
		btnCreateLedsager.setTextAlignment(TextAlignment.CENTER);
		hbxButtons.getChildren().add(btnCreateLedsager);
		btnCreateLedsager.setDisable(true);
		btnCreateLedsager.setOnAction(event -> this.createLedsagerAction());

		Button btnDeleteLedsager = new Button("Slet \nLedsager");
		btnDeleteLedsager.setMinWidth(80);
		btnDeleteLedsager.setTextAlignment(TextAlignment.CENTER);
		hbxButtons.getChildren().add(btnDeleteLedsager);
		btnDeleteLedsager.setDisable(true);
		btnDeleteLedsager.setOnAction(event -> this.deleteLedsagerAction());

		HBox hbxButtons2 = new HBox(40);
		this.add(hbxButtons2, 0, 8, 3, 1);
		hbxButtons2.setAlignment(Pos.BASELINE_LEFT);

		Button btnCreateFirma = new Button("Opret \nFirma");
		btnCreateFirma.setMinWidth(80);
		btnCreateFirma.setTextAlignment(TextAlignment.CENTER);
		hbxButtons2.getChildren().add(btnCreateFirma);
		btnCreateFirma.setDisable(true);
		btnCreateFirma.setOnAction(event -> this.createFirmaAction());

		Button btnDeleteFirma = new Button("Slet \nFirma");
		btnDeleteFirma.setMinWidth(80);
		btnDeleteFirma.setTextAlignment(TextAlignment.CENTER);
		hbxButtons2.getChildren().add(btnDeleteFirma);
		btnDeleteFirma.setDisable(true);
		btnDeleteFirma.setOnAction(event -> this.deleteFirmaAction());

		if (lvwDeltagere.getItems().size() > 0)
		{
			lvwDeltagere.getSelectionModel().select(0);
		}
	}

	private ArrayList<Deltager> initAllDeltagerList()
	{
		ArrayList<Deltager> list = new ArrayList<>();
		for (Deltager delt : Service.getDeltagere())
		{
			list.add(delt);
		}
		return list;
	}

	// -------------------------------------------------------------------------
	private void createFirmaAction()
	{

	}

	private void deleteFirmaAction()
	{

	}

	private void createLedsagerAction()
	{

	}

	private void deleteLedsagerAction()
	{

	}

	private void createActionDeltager()
	{
		DeltagerWindow dia = new DeltagerWindow("Opret Deltager");
		dia.showAndWait();

		// Wait for the modal dialog to close

		lvwDeltagere.getItems().setAll(this.initAllDeltagerList());
		this.updateControls();
	}

	private void updateActionDeltager()
	{
		Deltager deltager = lvwDeltagere.getSelectionModel().getSelectedItem();
		if (deltager == null)
			return;

		DeltagerWindow dia = new DeltagerWindow("Opret Deltager", deltager);
		dia.showAndWait();

		// Wait for the modal dialog to close

		int selectIndex = lvwDeltagere.getSelectionModel().getSelectedIndex();
		lvwDeltagere.getItems().setAll(this.initAllDeltagerList());
		lvwDeltagere.getSelectionModel().select(selectIndex);
	}

	private void deleteActionDeltager()
	{
		// Employee employee =
		// lvwKonference.getSelectionModel().getSelectedItem();
		// if (employee == null)
		// return;
		//
		// Stage owner = (Stage) this.getScene().getWindow();
		// Alert alert = new Alert(AlertType.CONFIRMATION);
		// alert.setTitle("Delete Employee");
		// alert.initOwner(owner);
		// alert.setHeaderText("Are you sure?");
		//
		// // Wait for the modal dialog to close
		// Optional<ButtonType> result = alert.showAndWait();
		// if (result.isPresent() && result.get() == ButtonType.OK) {
		// Service.deleteEmployee(employee);
		// lvwKonference.getItems().setAll(this.initAllKonfList());
		// this.updateControls();
		// }
	}

	// -------------------------------------------------------------------------

	private void selectedDeltagerChanged()
	{
		this.updateControls();
	}

	public void updateControls()
	{
		// Employee employee =
		// lvwKonference.getSelectionModel().getSelectedItem();
		// if (employee != null) {
		// txfName.setText(employee.getName());
		// txfWage.setText("kr " + employee.getWage());
		// //txfEmploymentYear.setText(""+employee.getEmploymentYear());
		// if (employee.getCompany() != null) {
		// txfCompany.setText("" + employee.getCompany());
		// txfSalary.setText("kr " + employee.weeklySalary());
		// txfEmploymentYear.setText(""+employee.getEmploymentYear());
		// } else {
		// txfCompany.clear();
		// txfSalary.clear();
		// txfEmploymentYear.clear();
		// }
		// } else {
		// txfName.clear();
		// txfWage.clear();
		// txfCompany.clear();
		// txfSalary.clear();
		// txfEmploymentYear.clear();
		// }
	}

}
