package cs211.project.services;

import cs211.project.models.Team;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;


public final class NPBPRouter {
    private static final String WINDOW_TITLE = "";
    private static final Double WINDOW_WIDTH = 800D;
    private static final Double WINDOW_HEIGHT = 600D;
    private static final Double FADE_ANIMATION_DURATION = 500D;
    private static NPBPRouter router;
    private static Object main;
    private static Stage window;
    private static Double windowWidth;
    private static String windowTitle;
    private static Double windowHeight;
    private static AbstractMap<String,RouteScene> routes = new HashMap();
    private static RouteScene current;
    private static String animationType;
    private static Double animationDuration;
    private static String CSS;

    private NPBPRouter(){}

    public static void bind(Object ref,Stage win){
        checkIn(ref,win);
    }
    public static void bind(Object ref,Stage win,String windowTitle){
        checkIn(ref,win);
        NPBPRouter.windowTitle = windowTitle;
    }
    public static void bind(Object ref,Stage win,double windowWidth,double windowHeight){
        checkIn(ref,win);
        NPBPRouter.windowHeight = windowHeight;
        NPBPRouter.windowWidth = windowWidth;
    }
    public static void bind(Object ref,Stage win,String windowTitle,double windowWidth,double windowHeight){
        checkIn(ref,win);
        NPBPRouter.windowHeight = windowHeight;
        NPBPRouter.windowWidth = windowWidth;
        NPBPRouter.windowTitle = windowTitle;
    }

    public static void checkIn(Object ref,Stage win){
        if(router == null){
            router = new NPBPRouter();
        }
        if(main == null){
            main = ref; // main ref to object
        }
        if(window == null){
            window = win;
        }
    }
    public static void when(String routeLabel,String scenePath){
        RouteScene routeScene = new RouteScene(scenePath);
        routes.put(routeLabel,routeScene);
    }
    public static void when(String routeLabel,String scenePath,String windowTitle){
        RouteScene routeScene = new RouteScene(scenePath,windowTitle);
        routes.put(routeLabel,routeScene);
    }
    public static void when(String routeLabel,String scenePath,Double windowHeight,Double windowWidth){
        RouteScene routeScene = new RouteScene(scenePath,windowWidth,windowHeight);
        routes.put(routeLabel,routeScene);
    }
    public static void when(String routeLabel,String scenePath,Double windowHeight,Double windowWidth,String windowTitle){
        RouteScene routeScene = new RouteScene(scenePath,windowTitle,windowWidth,windowHeight);
        routes.put(routeLabel,routeScene);
    }
    private static void routeAnimation(Parent node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(FADE_ANIMATION_DURATION),node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
    private static void loadNewRoute(RouteScene route) throws IOException {
        current = route;
        String scenePath = "/" + route.scenePath;
        Parent resource = (Parent)FXMLLoader.load((new Object() {
        }).getClass().getResource(scenePath));
        window.setTitle(route.windowTitle);
        Scene scene = new Scene(resource,route.sceneWidth, route.sceneHeight);
        scene.getStylesheets().add((new Object(){
       }).getClass().getResource(CSS).toExternalForm());
        window.setScene(scene);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
        window.setResizable(false);
        window.show();
        routeAnimation(resource);
    }
    private static void loadNewPage(RouteScene route, Parent parent) throws IOException{
        Pane page = (Pane)parent;
        current = route;
        String scenePath = "/" + route.scenePath;
        Parent resource = (Parent)FXMLLoader.load((new Object() {
        }).getClass().getResource(scenePath));
        window.setTitle(route.windowTitle);
        page.getChildren().removeAll();
        page.getChildren().setAll(resource);
        routeAnimation(resource);
        MFXThemeManager.addOn(page, Themes.DEFAULT, Themes.LEGACY);
        page.getStylesheets().add((new Object(){}).getClass().getResource(CSS).toExternalForm());
    }
    public static void loadPage(String routeLabel, Parent parent) throws IOException{
        RouteScene route = (RouteScene)routes.get(routeLabel);
        loadNewPage(route, parent);
    }
    public static void loadPage(String routeLabel, Parent parent, Object data1) throws IOException{
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.dataAccount = data1;
        loadNewPage(route, parent);
    }
    public static void loadPage(String routeLabel, Parent parent, Object data1,Object data2) throws IOException{
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.dataAccount = data1;
        route.dataEvent = data2;
        loadNewPage(route, parent);
    }
    public static void loadPage(String routeLabel, Parent parent, Object data1,Object data2,Object data3) throws IOException{
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.dataAccount = data1;
        route.dataEvent = data2;
        route.dataTeam = data3;
        route.dataActivity = data3;
        loadNewPage(route, parent);
    }
    public static void loadPage(String routeLabel, Parent parent, Object data1,Object data2,Object data3, Object data4) throws IOException{
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.dataAccount = data1;
        route.dataEvent = data2;
        route.dataTeam = data3;
        route.dataActivity = data4;
        loadNewPage(route, parent);
    }

    public static void loadPageSet(String routeLabel, Parent parent, Object data1,Object data2,Object data3, int data) throws IOException{
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.dataAccount = data1;
        route.dataEvent = data2;
        route.dataTeam = data3;
        route.data = data;
        loadNewPage(route, parent);
    }



    public static void loadPage(String routeLabel, Parent parent, Object data1, Object data2, Team data3) throws IOException{
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.dataAccount = data1;
        route.dataEvent = data2;
        route.dataTeam = data3;
        loadNewPage(route, parent);
    }

    public static void loadPageEditTeam(String routeLabel, Parent parent, Object data1, Object data2, Object data3, Object data4)throws IOException{
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.dataAccount = data1;
        route.dataTeam = data2;
        route.dataActivity = data3;
        route.dataEvent = data4;
        loadNewPage(route, parent);
    }

    public static void loadPageEditEvent(String routeLabel, Parent parent, Object data1, Object data2, Object data3)throws IOException{
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.dataAccount = data1;
        route.dataEvent = data2;
        route.dataActivity = data3;
        loadNewPage(route, parent);
    }
    public static void goTo(String routeLabel) throws IOException {
        RouteScene route = (RouteScene)routes.get(routeLabel);
        loadNewRoute(route);
    }

    public static void goTo(String routeLabel, Object data) throws IOException {
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.dataAccount = data;
        loadNewRoute(route);
    }
    public static void setCss(String path){
        String scenePath = "/cs211/project/";
        CSS = scenePath + path;
    }

    public static void startFrom(String routeLabel) throws Exception {
        goTo(routeLabel);
    }

    public static void startFrom(String routeLabel, Object data) throws Exception {
        goTo(routeLabel, data);
    }

    public static void setAnimationType(String anType) {
        animationType = anType;
    }

    public static void setAnimationType(String anType, double anDuration) {
        animationType = anType;
        animationDuration = anDuration;
    }
    public static Object getDataAccount() {
        return current.dataAccount;
    }
    public static Object getDataEvent(){
        return current.dataEvent;
    }
    public static Object getDataTeam(){
        return current.dataTeam;
    }

    public  static Object getDataActivity(){
        return current.dataActivity;
    }
    public  static int getData(){
        return current.data;
    }



    public static class RouteScene{
        private String scenePath;
        private String windowTitle;
        private double sceneWidth;
        private double sceneHeight;
        private Object dataAccount;
        private Object dataEvent;
        private Object dataTeam;
        private Object dataActivity;
        private int data;
        private int css_select;
        private RouteScene(String scenePath) {
            this(scenePath, getWindowTitle(), getWindowWidth(), getWindowHeight());
        }

        private RouteScene(String scenePath, String windowTitle) {
            this(scenePath, windowTitle, getWindowWidth(), getWindowHeight());
        }

        private RouteScene(String scenePath, double sceneWidth, double sceneHeight) {
            this(scenePath, getWindowTitle(), sceneWidth, sceneHeight);
        }

        private RouteScene(String scenePath, String windowTitle, double sceneWidth, double sceneHeight) {
            this.scenePath = scenePath;
            this.windowTitle = windowTitle;
            this.sceneWidth = sceneWidth;
            this.sceneHeight = sceneHeight;
        }

        private static String getWindowTitle() {
            return NPBPRouter.windowTitle != null ? NPBPRouter.windowTitle : "";
        }

        private static double getWindowWidth() {
            return NPBPRouter.windowWidth != null ? NPBPRouter.windowWidth : NPBPRouter.WINDOW_WIDTH;
        }

        private static double getWindowHeight() {
            return NPBPRouter.windowHeight != null ? NPBPRouter.windowHeight : NPBPRouter.WINDOW_HEIGHT;
        }
    }
}
