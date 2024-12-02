package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.pivot.TeamChatList;
import cs211.project.repository.*;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    @FXML private AnchorPane page;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox vbox;
    @FXML private TextField textField;

    private User user;
    private Event event;
    private Team team;
    private ArrayList<Integer> listId;
    private AccountRepository accountRepository;
    private Account account;
    private AccountList accountList;
    private ChatRepository chatRepository;
    private Chat chat;
    private ChatText chatText;
    private TeamChatRepository teamChatRepository;
    private TeamChatList teamChatList;
    private TeamRepository teamRepository;
    private TeamList teamList;

    public void initialize(URL url, ResourceBundle resourceBundle){
        scrollPane.setVvalue(1.0);

        user = (User) NPBPRouter.getDataAccount();
        int accId = user.getAccountId();
        accountRepository = new AccountRepository();
        accountList = accountRepository.getAccounts();
        account = accountList.findUserByAccountId(accId);

        event = (Event) NPBPRouter.getDataEvent();

        teamRepository = new TeamRepository();
        teamList = teamRepository.getTeamList();
        int teamId = (int) NPBPRouter.getDataTeam();
        team = teamList.findTeamById(teamId);

        chatRepository = new ChatRepository();
        chat = chatRepository.getChat();

        teamChatRepository = new TeamChatRepository();
        teamChatList = teamChatRepository.getTeamChatList();

        listId = new ArrayList<>();
        listId.addAll(teamChatList.findChatByTeamId(teamId));

        for(Integer id : listId){
            vbox.getChildren().add(createCard(id));
        }

        textField.addEventFilter(KeyEvent.KEY_PRESSED, event ->{
            if(event.getCode()== KeyCode.ENTER){
                send();
            }
        });
    }

    public HBox createCard(int id){
        File file = new File("src/main/resources/cs211/project/views/chat-text.fxml");
        URL url = null;
        try {
            url = file.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);

        HBox hbox = null;
        try {
            hbox = (HBox) fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ChatTextController chatTextController = (ChatTextController) fxmlLoader.getController();
        Label usernameLabel = chatTextController.getUsernameLabel();
        Label dateLabel = chatTextController.getDateLabel();
        Label timeLabel = chatTextController.getTimeLabel();
        Label textLabel = chatTextController.getTextLabel();

        chatText = chat.findTextByChatId(id);
        usernameLabel.setText(chatText.getUsername());
        dateLabel.setText(chatText.getDate().toString());
        timeLabel.setText(chatText.getTime().toString());
        textLabel.setText(chatText.getText());

        return hbox;
    }

    public void send(){
        if(textField.getText().equals("")){return;}
        String time;
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        if(currentTime.getHour() < 10){
            time = "0" +currentTime.getHour() + ":" + currentTime.getMinute();
        }
        else {
            time = currentTime.getHour() + ":" + currentTime.getMinute();
        }
        String text = textField.getText();
        chat.sendNewText(currentDate.toString(),time, account.getUsername(), text);


        chatRepository.save(chat);

        HBox hbox = createCard(chat.getLastId());
        vbox.getChildren().add(hbox);

        teamChatList.addNew(team.getTeamId(),chat.getLastId());
        teamChatRepository.save(teamChatList);

        textField.clear();

        try {
            NPBPRouter.loadPage("chat",page,user,event,team.getTeamId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void backToTeamDetail(){
        try {
            NPBPRouter.loadPage("team-detail",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
