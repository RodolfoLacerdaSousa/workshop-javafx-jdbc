package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {
	
	private DepartmentService service;

	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	
	private ObservableList<Department> obsList;
	
	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/DepartmentForm.fxml", parentStage);
	}
	
	
	public void setDepartmentService(DepartmentService service) { //injencao de dependencia SOlida (atraves de 1 set)
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id")); // padrao, para iniciar o comportamento das colunas
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		// para a tabela (tableView) seguir o tamanho da janela 
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Sevice was null");
		}
		List<Department> list = service.findaAll();
		obsList = FXCollections.observableArrayList(list); //instancia do obsList com os dados da list
		tableViewDepartment.setItems(obsList);
	}
	
	private void createDialogForm(String absoluteName, Stage parentStage) { //janela de dialogo para preencher o novo departamento
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Department Data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false); //janela nao podera ser redmensionada
			dialogStage.initOwner(parentStage); // o pai do dialogStage
			dialogStage.initModality(Modality.WINDOW_MODAL); // diz se a janela vai ser modal (Fica travada ate fechar, ou seja nao da pra acessar a anterior ate fechar ele) ou vai ter outro comportamento 
			dialogStage.showAndWait();
		}
		catch (IOException e) {
			Alerts.showAlert("IOException", "Error load view", e.getMessage(), AlertType.ERROR);
		}
	}

}
