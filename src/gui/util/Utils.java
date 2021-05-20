package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	
	public static Stage currentStage(ActionEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}
	
	public static Integer tryParseToInt(String str) { //transformar string (so com numeros) para int.
		try{
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) { //se nao tiver so letras na string.
			return null;
		}
	}

}
