package pe.edu.upeu.calcfx;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CalcFxApplication extends Application {

	private static ConfigurableApplicationContext configurableApplicationContext;
	private Parent parent;

	public static void main(String[] args) {
		//SpringApplication.run(CalcFxApplication.class, args);
		launch(args);
	}


	@Override
	public void init() throws Exception {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(CalcFxApplication.class);
		builder.application().setWebApplicationType(WebApplicationType.NONE);
		configurableApplicationContext = builder.run(getParameters().getRaw().toArray(new String[0]));

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/calcfx.fxml"));

		fxmlLoader.setControllerFactory(configurableApplicationContext::getBean);
		parent= fxmlLoader.load();
	}


	@Override
	public void start(Stage stage) throws Exception {

		Scene scene = new Scene(parent);

		double preferredWidth = parent.prefWidth(-1);
		double preferredHeight = parent.prefHeight(-1);
		stage.setMinWidth(preferredWidth);
		stage.setMinHeight(preferredHeight);

		Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
		scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

		stage.setScene(scene);
		stage.setTitle("Spring Java-FX");
		stage.show();

	}
}
