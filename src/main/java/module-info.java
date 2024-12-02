module cs211.project.cs211661project {
    requires javafx.controls;
    requires javafx.fxml;
    requires bcrypt;
    requires MaterialFX;


    opens cs211.project.cs211661project to javafx.fxml;
    exports cs211.project.cs211661project;

    opens cs211.project.controllers to javafx.fxml;
    exports cs211.project.controllers;

    opens cs211.project.services to javafx.fxml;
    exports cs211.project.services;

    opens cs211.project.models to javafx.fxml;
    exports cs211.project.models;
}