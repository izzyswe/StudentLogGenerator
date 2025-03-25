package isaackim_sec001_ex02;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.util.regex.Pattern;

import java.util.List;

import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;


public class StudentController {

	//creating a list to better handle the amount of list of checkbox
	private List<CheckBox> actCheckBox;
	
	@FXML
    private Button DisplayOutput;

    @FXML
    private TextArea StudentOutput;

    @FXML
    private ChoiceBox<String> SubjectSelection;

    @FXML
    private CheckBox actBasketballCheckBox;
    @FXML
    private CheckBox actFootballCheck;
    @FXML
    private CheckBox actStudentCouncilCheck;
    @FXML
    private CheckBox actSwimmingCheck;
    @FXML
    private CheckBox actVolleyballCheck;
    @FXML
    private CheckBox actVolunteerCheck;

    @FXML
    private RadioButton majorBusinessRadio;
    @FXML
    private RadioButton majorCompSciRadio;

    @FXML
    private TextField studentAddress;
    @FXML
    private TextField studentCity;
    @FXML
    private TextField studentEmailAddress;
    @FXML
    private TextField studentFirstName;
    @FXML
    private TextField studentPhoneNumber;
    @FXML
    private TextField studentPostalCode;
    @FXML
    private TextField studentProvince;
    
    @FXML
    public void initialize() {
        actCheckBox = List.of(actBasketballCheckBox, actFootballCheck, actStudentCouncilCheck, actSwimmingCheck, actVolleyballCheck, actVolunteerCheck); 

    }
    
    @FXML
    void OnBusinessCheckClicked(MouseEvent event) {
    	if (majorBusinessRadio.isSelected()) {
            // Unselect the other radio button
            majorCompSciRadio.setSelected(false);
            // Set business options
            SubjectSelection.setItems(FXCollections.observableArrayList("Process Management", "Supply Chain", "Resource Planning"));
        } else {
            // Clear the choice box when unchecked
            SubjectSelection.getItems().clear();
        }
    }


    @FXML
    void onCompSciCheckClicked(MouseEvent event) {
    	if (majorCompSciRadio.isSelected()) {
            // Unselect the other radio button
            majorBusinessRadio.setSelected(false);
            // Set computer science options
            SubjectSelection.setItems(FXCollections.observableArrayList("Python", "Swift", "PHP", "Database"));
        } else {
            // Clear the choice box when unchecked
            SubjectSelection.getItems().clear();
        }
    }

    @FXML
    void OnDisplayOutputClicked(MouseEvent event) {
    	//get all text field
    	String firstName = studentFirstName.getText().trim();
        String address = studentAddress.getText().trim();
        String city = studentCity.getText().trim();
        String province = studentProvince.getText().trim();
        String postalCode = studentPostalCode.getText().trim();
        String phoneNumber = studentPhoneNumber.getText().trim();
        String email = studentEmailAddress.getText().trim();
        
        //define the regex for each text field
        Pattern namePattern = Pattern.compile("^[A-Za-z]+(?: [A-Za-z]+)*$");
        Pattern phonePattern = Pattern.compile("^\\d{10}$");
        Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
        Pattern postalPattern = Pattern.compile("^[A-Za-z]\\d[A-Za-z] ?\\d[A-Za-z]\\d$");

        if (!namePattern.matcher(firstName).matches()) {
        	StudentOutput.setText("Invalid name. Only letters and spaces allowed.");
            return;
        }
        if (!phonePattern.matcher(phoneNumber).matches()) {
        	StudentOutput.setText("Invalid phone number. Must be 10 digits.");
            return;
        }
        if (!emailPattern.matcher(email).matches()) {
        	StudentOutput.setText("Invalid email format.");
            return;
        }
        if (!postalPattern.matcher(postalCode).matches()) {
        	StudentOutput.setText("Invalid postal code. Must be in the format A1B 2C3.");
            return;
        }
        
        String major = majorBusinessRadio.isSelected() ? "Business" :
            majorCompSciRadio.isSelected() ? "Computer Science" : "None";

        // Get selected subject from ChoiceBox
        String subject = SubjectSelection.getValue() != null ? SubjectSelection.getValue() : "None";

        // Get selected checkboxes
        StringBuilder selectedChecks = new StringBuilder();
        for (CheckBox cb : actCheckBox) {
        	if (cb.isSelected()) {
        		selectedChecks.append(cb.getText()).append(", ");
        	}
        }
        String selectedCheckboxes = selectedChecks.length() > 0 ? 
                         selectedChecks.substring(0, selectedChecks.length() - 2) : 
                         "None";

        // Format output message
        String output = "Student Information:\n" +
             "---------------------------\n" +
             "Name: " + firstName + "\n" +
             "Address: " + address + ", " + city + ", " + province + " " + postalCode + "\n" +
             "Phone: " + phoneNumber + "\n" +
             "Email: " + email + "\n" +
             "Major: " + major + "\n" +
             "Selected Subject: " + subject + "\n" +
             "Selected Activities: " + selectedCheckboxes;

        StudentOutput.setText(output);
    }
}

