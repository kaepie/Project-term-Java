package cs211.project.models;

import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    private int accountId;
    private String name;
    private String username;
    private String password;
    private String imagePath;
    private String roleAccount;
    private LocalDateTime timeLogin;
    private int accountTheme;

    /**
     * Use this constructor when sign up
     * and use [account object].hashPassword(plain)
     * @param username
     * @param name
     */
    public Account(String username, String name) {
        this.name = name;
        this.username = username;
        this.password = null;
        this.roleAccount = "User";
        this.imagePath = "images/User/default.png";
        this.timeLogin = LocalDateTime.now();
        this.accountTheme = 1;
    }
    public Account(String username, String name, String id, String role, String imagePath, String time, String accountTheme) {
        this.name = name;
        this.username = username;
        this.accountId = Integer.parseInt(id);
        this.password = null;
        this.roleAccount = role;
        this.imagePath = imagePath;
        this.timeLogin = LocalDateTime.parse(time);
        this.accountTheme = Integer.parseInt(accountTheme);
    }


    /**
     * Use this constructor when reading data from file
     * @param username
     * @param name
     * @param password
     */
    public Account(String username, String name, String  id, String password, String role, String imagePath, String time, String theme){
        this(username, name, id, role, imagePath, time, theme);
        this.password = password;
    }

    public boolean isUsername(String username){
        return this.username.equals(username);
    }
    public boolean isAccountId(int id){
        return this.accountId == id;
    }


    public void hashPassword(String password) {
        this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
    public  static boolean confirmPassword(String password,String confirmPassword){
        if(password.equals(confirmPassword)){
            return true;
        }
        return false;
    }
    public boolean validatePassword(String password) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), this.password);
        return result.verified;
    }
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }
    public void setAccountTheme(int theme){
        this.accountTheme = theme;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAccountId(int id){
        this.accountId = id;
    }

    public  void setPassword(String password){
        this.password = password;
        this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
    public void setTimeLogin(LocalDateTime timeLogin) {
        this.timeLogin = timeLogin;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getImagePath() {
        return imagePath;
    }
    public ImageView getImage(){
        return new ImageView(new Image("file:" + getImagePath()));
    }

    public String getRoleAccount() {
        return roleAccount;
    }

    public String getName() {
        return name;
    }
    public int getAccountTheme(){
        return accountTheme;
    }

    public LocalDateTime getTimeLogin() {
        return timeLogin;
    }
    public String getTime(){
        return timeLogin.format(DateTimeFormatter.ofPattern("dd.MM.YYYY : HH.mm a"));
    }

    public int getAccountId() {
        return accountId;
    }

}
