package isaackim_sec001_ex01;

//FXML IMPORTS
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class CalculatorController {
	
	private int factorialResult;
	private double carLoanResult;
	//Referencing the Left side (Factorial Calculation)
	@FXML
	private TextField Factorial_Text; // <- Text Input for Getting the factorial of x
	@FXML
	private Button FactorialCalculateBtn; // <- Button for Factorial Calculation
	@FXML
	private TextArea Factorial_Output; // <- Uneditable Text Area for The factorial Output
	
	// Referencing the Right side (Car Loan Calculations)
	@FXML
	private TextField carLoanText; // <- Text Field for Car Loan 
	@FXML
	private TextField carInterestText; // <- Text Field for Car Interest Rate
	@FXML
	private TextField carDurationText;// <- Text Field for Car Duration
	@FXML
	private Button carCalculateBtn; // <- Button for Car Loan Calculation
	@FXML
	private TextArea carOutput; // <- Uneditable Text Area for the Car Loan Output
	
	//creating a factorial method
    public static int factorial(int n) 
    { 
    	// if n is equal to 0
        if (n == 0) 
        	// return 1
            return 1; 
        //until then do recursive loop and multiply each parameter as the argument decrements
        return n * factorial(n - 1); 
    }
    
    public static double loan(double loanNum, double loanInterest, int loanDuration) {
    	//find annual interest
    	double monthlyRate = (loanInterest /100) / 12;
    	int TotalPayment = loanDuration * 12;
    	return (loanNum * monthlyRate * Math.pow(1+monthlyRate, TotalPayment)) / (Math.pow(1+monthlyRate, TotalPayment) -1);
    }
	
	@FXML
    private void OnFactorialMouseClicked(MouseEvent event) {
		// got the text field object and parsed the text value
		int castedNumber = Integer.parseInt(Factorial_Text.getText());

		new Thread(() -> {
			// assign result to the function using the text field as the argument
            int result = factorial(castedNumber);
            //when the user clicks the button, it will get the value and output the answer
            Platform.runLater(() -> Factorial_Output.setText("Output Answer: \n\n" + "\t".repeat(5) + result));
        }).start();
    }
	
	@FXML
    private void OnCarLoanMouseClicked(MouseEvent event) {
		double castedLoan = Double.parseDouble(carLoanText.getText());
        double castedInterest = Double.parseDouble(carInterestText.getText());
        int castedDuration = Integer.parseInt(carDurationText.getText());

        new Thread(() -> {
            double result = loan(castedLoan, castedInterest, castedDuration);
            Platform.runLater(() -> carOutput.setText("Output Answer: \n\n" + "\t".repeat(5) + result));
        }).start();
    }	
	
}
