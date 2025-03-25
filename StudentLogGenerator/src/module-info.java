module IsaacKim_COMP1011Sec001 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens isaackim_sec001_ex01 to javafx.graphics, javafx.fxml;
	opens isaackim_sec001_ex02 to javafx.graphics, javafx.fxml;
}