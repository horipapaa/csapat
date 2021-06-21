package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.PlayerDAO;
import hu.alkfejl.dao.PlayerDAOImpl;
import hu.alkfejl.dao.TeamDAO;
import hu.alkfejl.dao.TeamDAOImpl;
import hu.alkfejl.model.Player;
import hu.alkfejl.model.Team;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class AddModifyPlayerController {

    private PlayerDAO playerDAO = new PlayerDAOImpl();
    private TeamDAO teamDAO = new TeamDAOImpl();

    List<Team> teamList = teamDAO.findAll();
    List<String> teamNamesList = new ArrayList<>();

    private Player player;

    public void setPlayer(Player p) {
        this.player = p;

        String teamName = "";
        for (Team team: teamList) {
            teamNamesList.add(team.getTeamName());
            if (player.getCurrentTeamId() == team.getId()) {
                teamName = team.getTeamName();
            }
        }

        playerNameTextField.textProperty().bindBidirectional(player.nameProperty());
        playerPositionTextField.textProperty().bindBidirectional(player.positionProperty());
        playerBirthYearTextField.textProperty().bindBidirectional(new SimpleStringProperty(player.birthYearProperty().getValue().toString()));
        playerTeamChoiceBox.setValue(teamName);
        playerTeamChoiceBox.setItems(FXCollections.observableList(teamNamesList));
    }

    @FXML
    TextField playerNameTextField;
    @FXML
    TextField playerPositionTextField;
    @FXML
    TextField playerBirthYearTextField;
    @FXML
    ChoiceBox<String> playerTeamChoiceBox;

    @FXML
    public void onCancel() {
        App.loadFXML("/fxml/main_window.fxml");
    }

    @FXML
    public void onSave() {
        String selectedTeam = playerTeamChoiceBox.getValue();
        int teamId = 0;
        for (Team team: teamList) {
            if(team.getTeamName().equals(selectedTeam)) {
                teamId = team.getId();
            }
        }
        player.setCurrentTeamId(teamId);
        player.setBirthYear(Integer.parseInt(playerBirthYearTextField.getText()));
        playerDAO.save(player);
        App.loadFXML("/fxml/main_window.fxml");
    }

}
