package dad.javafx.cambiodivisa;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisasApp extends Application{
	

	private ComboBox<Divisa> divisaFromCombo, divisaToCombo;
	private TextField divisaFromText, divisaToText;
	private ArrayList<Divisa> divisasArray=new ArrayList<Divisa>();
	private Button cambiarButton; 
	private Alert errorAlert;

	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setTitle("CambioDivisasApp");
		errorAlert.setHeaderText("Error");
		errorAlert.setContentText("El valor introducido no es válido, compruebe que se trate e un número");
		
		divisasArray.add(new Divisa("Euro", 1.0));
		divisasArray.add( new Divisa("Libra", 0.8873));
		divisasArray.add(new Divisa("Dolar", 1.2007));
		divisasArray.add(new Divisa("Yen", 133.59));
		
		divisaFromText=new TextField("0");
		divisaFromText.setMaxWidth(50);
		
		divisaToText=new TextField("0");
		divisaToText.setMaxWidth(50);
		divisaToText.setEditable(false);
		
		divisaFromCombo=new ComboBox<Divisa>();
		divisaFromCombo.getItems().addAll(divisasArray);
		divisaFromCombo.getSelectionModel().selectFirst();
		
		divisaToCombo=new ComboBox<Divisa>();
		divisaToCombo.getItems().addAll(divisasArray);
		divisaToCombo.getSelectionModel().selectFirst();
		
		cambiarButton=new Button("Cambiar");
		cambiarButton.setDefaultButton(true);
		cambiarButton.setOnAction(e-> onCambiarButtonAction(e));
		
		
		
		HBox divisasfromBox=new HBox(5, divisaFromText,divisaFromCombo);
		divisasfromBox.setAlignment(Pos.CENTER);
		
		HBox divisasToBox=new HBox(5, divisaToText, divisaToCombo);
		divisasToBox.setAlignment(Pos.CENTER);
		
		VBox root = new VBox(5, divisasfromBox, divisasToBox, cambiarButton );
		root.setAlignment(Pos.CENTER);
		
		
		Scene scene = new Scene(root, 320, 200);
		primaryStage.setTitle("CambioDivisasApp");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
		
		

		
		
	}
	
	private void onCambiarButtonAction(ActionEvent e) {
		try {
		Divisa divisaFrom=divisaFromCombo.getSelectionModel().getSelectedItem() , divisaTo=divisaToCombo.getSelectionModel().getSelectedItem();
		Double cantidad=Double.parseDouble(divisaFromText.getText());
		
		divisaToText.setText(Divisa.fromTo(divisaFrom,divisaTo ,cantidad ).toString());
		} catch(NumberFormatException exception) {
			errorAlert.showAndWait();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
