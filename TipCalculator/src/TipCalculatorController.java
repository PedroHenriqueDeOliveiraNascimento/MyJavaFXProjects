import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController
{
	// formatadores para moeda e porcentagens
	private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
	private static final NumberFormat percent = NumberFormat.getPercentInstance();
	private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% padrão
	
	// Controles da Gui definidos na FXML e usados pelo código do controlador
	@FXML
	private TextField amountTextField;
	@FXML
	private Label tipPercentageLabel;
	@FXML
	private Slider tipPercentageSlider;
	@FXML
	private TextField tipTextField;
	@FXML
	private TextField totalTextField;
	
	// chamado pelo FXMLLoader para inicializar o controlador
	public void initialize()
	{
		tipPercentageSlider.valueProperty().addListener((ov, oldValue, newValue) -> 
		{
			tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
			tipPercentageLabel.setText(percent.format(tipPercentage));
		});
	}
	
	// calcula e exibe o valor da gorjeta e o valor total
	@FXML
	private void calculateButtonPressed(ActionEvent event)
	{
		try
		{
			BigDecimal amount = new BigDecimal(amountTextField.getText());
			BigDecimal tip = amount.multiply(tipPercentage);
			BigDecimal total = amount.add(tip);
			
			tipTextField.setText(currency.format(tip));
			totalTextField.setText(currency.format(total));
		}
		catch (NumberFormatException e)
		{
			amountTextField.setText("Enter amount");
			amountTextField.selectAll();
			amountTextField.requestFocus();
		}
	}
}
