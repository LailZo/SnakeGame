package view;

import java.io.IOException;
import java.net.URL;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

/**
 * Class U_ViewLogic ~ manages the windows in the system
 */
public class ViewLogic {
	// ------------------------------ Variables ------------------------------
	protected static final Rectangle2D FULL_SCREEN = Screen.getPrimary().getBounds();
	protected static final Rectangle2D VISIBLE_SCREEN = Screen.getPrimary().getVisualBounds();

	// protected static SysData sysData;
	protected static String currentUserID;
    public static Stage stage;
	protected static Login sampleController;
	protected static MainScreen sampleController2;
	/*
	 * protected static AdminPlayerController adminPlayerController; protected
	 * static AdminCoachController adminCoachController; protected static
	 * AdminCustomerController adminCustomerController; protected static
	 * AdminReceptionistController adminReceptionistController; protected static
	 * AdminStadiumController adminStadiumController; protected static
	 * AdminTrophyController adminTrophyController; protected static
	 * AdminTeamController adminTeamController; protected static
	 * AdminMatchController adminMatchController;
	 *
	 * protected static RecepMainController recepMainController; protected
	 * static CoachMainController coachMainController; protected static
	 * CustomerMainController customerMainController;
	 */

	// ------------------------------ Methods ------------------------------
	/**
	 * this method starts the windows in the system
	 */
	public static void initUI() {
		newUserWindow();
	}
	public static void initUIAdmin() {
		newAdminWindow();
	}

	/**
	 * this method manages the window properties
	 *
	 * @param fxmlLocation
	 * @param stage
	 * @param prefWidth
	 * @param prefHeight
	 * @param minWidth
	 * @param minHeight
	 * @param maxWidth
	 * @param maxHeight
	 * @param resizable
	 * @param title
	 * @param waitFor
	 */
	protected static void newWindow(URL fxmlLocation, Stage stage, Double prefWidth, Double prefHeight, Double minWidth,
			Double minHeight, Double maxWidth, Double maxHeight, boolean resizable, String title, boolean waitFor) {
		//
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(fxmlLocation);
					Parent root = loader.load();
					Scene scene;

					if (prefWidth == null || prefHeight == null)
						scene = new Scene(root);

					else
						scene = new Scene(root, prefWidth, prefHeight);
					stage.setScene(scene);

					if (minWidth != null)
						stage.setMinWidth(minWidth);
					if (minHeight != null)
						stage.setMinHeight(minHeight);
					if (maxWidth != null)
						stage.setMaxWidth(maxWidth);
					if (maxHeight != null)
						stage.setMaxHeight(maxHeight);

					stage.setResizable(resizable);

					if (waitFor)
						stage.initModality(Modality.APPLICATION_MODAL);

					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ------------------------------ Login ------------------------------
	/**
	 * Open Login Window
	 */
	protected static void newUserWindow() {
		stage = new Stage();
		stage.setMaximized(true);
		stage.initStyle(StageStyle.UNDECORATED);
		newWindow(ViewLogic.class.getResource("MainScreen.fxml"), stage, null, null, null, null, null, null, false,
				"UserWindow", false);
	}

	protected static void newAdminWindow() {
		stage = new Stage();
		stage.setMaximized(true);
		stage.initStyle(StageStyle.UNDECORATED);
		newWindow(ViewLogic.class.getResource("MainScreen.fxml"), stage, null, null, null, null, null, null, false,
				"UserWindow", false);

	}

	protected static void onSignin(String email) {
		if (email.equals("admin"))
			currentUserID = "admin";
	}

	protected static void onSignout() {
		currentUserID = null;
	}

	protected static void newSignUpWindow() {
		stage = new Stage();

		newWindow(ViewLogic.class.getResource("signUp.fxml"), stage, null, null, null, null, null, null, false,
				"sign Up", false);
	}

}
