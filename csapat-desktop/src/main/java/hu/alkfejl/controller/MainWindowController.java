package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.PlayerDAO;
import hu.alkfejl.dao.PlayerDAOImpl;
import hu.alkfejl.dao.TeamDAO;
import hu.alkfejl.dao.TeamDAOImpl;
import hu.alkfejl.model.Player;
import hu.alkfejl.model.Team;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;


import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    TeamDAO teamDAO = new TeamDAOImpl();
    PlayerDAO playerDAO = new PlayerDAOImpl();

    @FXML
    private TableView<Team> teamsTableView;
    @FXML
    private TableColumn<Team, Integer> teamsIdColumn;
    @FXML
    private TableColumn<Team, String> teamsNameColumn;
    @FXML
    private TableColumn<Team, String> teamsSuccessesColumn;
    @FXML
    private TableColumn<Team, String> teamsNationalityColumn;
    @FXML
    private TableColumn<Team, Void> teamsActionsColumn;

    @FXML
    private TableView<Player> playersTableView;
    @FXML
    private TableColumn<Player, Integer> playersIdColumn;
    @FXML
    private TableColumn<Player, String> playersNameColumn;
    @FXML
    private TableColumn<Player, String> playersPositionColumn;
    @FXML
    private TableColumn<Player, Integer> playersBirthYearColumn;
    @FXML
    private TableColumn<Player, String> playersTeamColumn;
    @FXML
    private TableColumn<Player, Void> playersActionsColumn;


    @FXML
    public void onExit(){
        Platform.exit();
    }

    @FXML
    public void onAddNewTeam() {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/add_modify_team.fxml");
        AddModifyTeamController controller = fxmlLoader.getController();
        controller.setTeam(new Team());
    }

    @FXML
    public void onAddNewPlayer() {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/add_modify_player.fxml");
        AddModifyPlayerController controller = fxmlLoader.getController();
        controller.setPlayer(new Player());
    }

    private void refreshTeamsTable() {
        teamsTableView.getItems().setAll(teamDAO.findAll());
    }

    private void refreshPlayersTable() {
        playersTableView.getItems().setAll(playerDAO.findAll());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTeamsTable();
        refreshPlayersTable();

        teamsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        teamsNameColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        teamsSuccessesColumn.setCellValueFactory(new PropertyValueFactory<>("successes"));
        teamsNationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));

        playersIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        playersNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        playersPositionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        playersBirthYearColumn.setCellValueFactory(new PropertyValueFactory<>("birthYear"));
        playersTeamColumn.setCellValueFactory(new PropertyValueFactory<>("currentTeamId"));

        teamsActionsColumn.setCellFactory(param -> new TableCell<>(){
            private final Button playersButton = new Button("Játékosok");
            private final Button modifyButton = new Button("Módosítás");
            private final Button deleteButton = new Button("Törlés");

            {
                playersButton.setOnAction(event -> {
                    Team team = getTableRow().getItem();
                    showPlayers(team);
                    refreshTeamsTable();
                });

                modifyButton.setOnAction(event -> {
                    Team team = getTableRow().getItem();
                    modifyTeam(team);
                    refreshTeamsTable();
                });

                deleteButton.setOnAction(event -> {
                    Team team = getTableRow().getItem();
                    deleteTeam(team);
                    refreshTeamsTable();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                }
                else{
                    HBox container = new HBox();
                    container.getChildren().addAll(playersButton, modifyButton, deleteButton);
                    container.setSpacing(10.0);
                    setGraphic(container);
                }
            }
        });

        playersActionsColumn.setCellFactory(param -> new TableCell<>(){
            private final Button modifyPlayerButton = new Button("Módosítás");
            private final Button deletePlayerButton = new Button("Törlés");

            {
                modifyPlayerButton.setOnAction(event -> {
                    Player player = getTableRow().getItem();
                    modifyPlayer(player);
                    refreshPlayersTable();
                });

                deletePlayerButton.setOnAction(event -> {
                    Player player = getTableRow().getItem();
                    deletePlayer(player);
                    refreshPlayersTable();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                }
                else{
                    HBox container = new HBox();
                    container.getChildren().addAll(modifyPlayerButton, deletePlayerButton);
                    container.setSpacing(10.0);
                    setGraphic(container);
                }
            }
        });
    }

    private void showPlayers(Team team) {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/view_players.fxml");
        ViewPlayerController controller = fxmlLoader.getController();
        controller.setTeam(team);
    }

    private void modifyTeam(Team team) {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/add_modify_team.fxml");
        AddModifyTeamController controller = fxmlLoader.getController();
        controller.setTeam(team);
    }

    private void deleteTeam(Team team) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan törlöd a csapatot: " + team.getTeamName() + "?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)) {
                teamDAO.delete(team);
            }
        });
    }
    private void modifyPlayer(Player player) {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/add_modify_player.fxml");
        AddModifyPlayerController controller = fxmlLoader.getController();
        controller.setPlayer(player);
    }

    private void deletePlayer(Player player) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan törlöd a játékost: " + player.getName() + "?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)) {
                playerDAO.delete(player);
            }
        });
    }
}
