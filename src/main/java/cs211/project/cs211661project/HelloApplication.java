package cs211.project.cs211661project;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Application;
import javafx.stage.Stage;
import cs211.project.services.NPBPRouter;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        int cssStyle = 1;
        NPBPRouter.bind(this, stage, "NPBP Application", 1024, 760);
        NPBPRouter.setCss("CSS/theme-"+cssStyle+".css");
        configRoute();
        NPBPRouter.goTo("app");
    }

    public void configRoute(){
        String viewPath = "cs211/project/views/";
        NPBPRouter.when("app",viewPath + "app.fxml");
        NPBPRouter.when("home",viewPath + "home.fxml");
        NPBPRouter.when("login",viewPath + "login.fxml");
        NPBPRouter.when("signup",viewPath + "signup.fxml");
        NPBPRouter.when("home-page",viewPath + "home-page.fxml");
        NPBPRouter.when("edit-event", viewPath + "edit-event.fxml");
        NPBPRouter.when("my-event",viewPath + "my-event.fxml");


        NPBPRouter.when("event-activity", viewPath + "event-activity.fxml");
        NPBPRouter.when("team-activity", viewPath + "team-activity.fxml");
        NPBPRouter.when("create-event-activity", viewPath + "create-event-activity.fxml");
        NPBPRouter.when("create-team-activity", viewPath + "create-team-activity.fxml");
        NPBPRouter.when("edit-event-activity", viewPath + "edit-event-activity.fxml");
        NPBPRouter.when("edit-team-activity", viewPath + "edit-team-activity.fxml");


        NPBPRouter.when("edit-user",viewPath + "edit-user.fxml");
        NPBPRouter.when("admin-sidebar",viewPath + "sidebar-admin.fxml");
        NPBPRouter.when("admin-main",viewPath + "admin-main.fxml");
        NPBPRouter.when("admin-pass",viewPath + "admin-pass.fxml");
        NPBPRouter.when("create-event",viewPath + "create-event.fxml");
        NPBPRouter.when("create-staff-team",viewPath + "create-staff-team.fxml");
        NPBPRouter.when("join-event",viewPath + "join-event.fxml");
        NPBPRouter.when("join-team",viewPath + "join-team.fxml");
        NPBPRouter.when("my-create-event",viewPath + "my-create-event.fxml");
        NPBPRouter.when("select-my-create-event",viewPath + "select-my-create-event.fxml");
        NPBPRouter.when("select-team",viewPath + "select-team.fxml");
        NPBPRouter.when("setting",viewPath + "setting.fxml");
        NPBPRouter.when("staff-card", viewPath + "staff-card.fxml");
        NPBPRouter.when("show-member", viewPath + "show-member.fxml");
        NPBPRouter.when("chat", viewPath + "chat.fxml");
        NPBPRouter.when("chat-text", viewPath + "chat-text.fxml");
        NPBPRouter.when("history", viewPath + "history.fxml");
        NPBPRouter.when("show-my-create-event", viewPath + "show-my-create-event.fxml");
        NPBPRouter.when("show-my-event", viewPath + "show-my-event.fxml");
        NPBPRouter.when("team-card", viewPath + "team-card.fxml");
        NPBPRouter.when("team-detail", viewPath + "team-detail.fxml");
        NPBPRouter.when("team-list", viewPath + "team-list.fxml");
        NPBPRouter.when("teams-chat", viewPath + "teams-chat.fxml");
        NPBPRouter.when("select-team-to-join",viewPath + "select-team-to-join.fxml");
        NPBPRouter.when("team-list-user", viewPath+"team-list-user.fxml");

        NPBPRouter.when("creator",viewPath + "creator.fxml");
        NPBPRouter.when("Introduction", viewPath + "introduction.fxml");
    }
    public static void main(String[] args) {
        launch();
    }
}