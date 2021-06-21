package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.PlayerDAO;
import hu.alkfejl.dao.PlayerDAOImpl;
import hu.alkfejl.model.Player;
import hu.alkfejl.model.Team;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewPlayerController {

    private Team team;
    private PlayerDAO playerDAO = new PlayerDAOImpl();

    public void setTeam(Team t) {
        this.team = t;
        teamNameLabel.textProperty().set(team.getTeamName());

        playersTableView.getItems().setAll(playerDAO.findPlayersOfTeam(team));
        playersIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        playersNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        playersPositionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        playersBirthYearColumn.setCellValueFactory(new PropertyValueFactory<>("birthYear"));

    }

    @FXML
    private Label teamNameLabel;
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
    public void onClose() {
        App.loadFXML("/fxml/main_window.fxml");
    }
}
