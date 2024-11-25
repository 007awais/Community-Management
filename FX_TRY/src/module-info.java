module FX_TRY {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	uses application.Main;
	
	opens application to javafx.graphics, javafx.fxml,javafx.base;

    
}
