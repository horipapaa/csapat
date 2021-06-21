package hu.alkfejl.model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.util.Date;

public class Game {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty date = new SimpleStringProperty(this, "date");
    private StringProperty referee = new SimpleStringProperty(this, "referee");
    private StringProperty location = new SimpleStringProperty(this, "location");
    private IntegerProperty homeTeamId = new SimpleIntegerProperty(this, "homeTeamId");
    private IntegerProperty awayTeamId = new SimpleIntegerProperty(this, "awayTeamId");
    private StringProperty result = new SimpleStringProperty(this, "result");
    private StringProperty travelInfo = new SimpleStringProperty(this, "travelInfo");
    private BooleanProperty completed = new SimpleBooleanProperty(this, "completed");

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getReferee() {
        return referee.get();
    }

    public StringProperty refereeProperty() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee.set(referee);
    }

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getResult() {
        return result.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void setResult(String result) {
        this.result.set(result);
    }

    public int getHomeTeamId() {
        return homeTeamId.get();
    }

    public IntegerProperty homeTeamIdProperty() {
        return homeTeamId;
    }

    public void setHomeTeamId(int homeTeamId) {
        this.homeTeamId.set(homeTeamId);
    }

    public int getAwayTeamId() {
        return awayTeamId.get();
    }

    public IntegerProperty awayTeamIdProperty() {
        return awayTeamId;
    }

    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId.set(awayTeamId);
    }

    public boolean isCompleted() {
        return completed.get();
    }

    public BooleanProperty completedProperty() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed.set(completed);
    }

    public String getTravelInfo() {
        return travelInfo.get();
    }

    public StringProperty travelInfoProperty() {
        return travelInfo;
    }

    public void setTravelInfo(String travelInfo) {
        this.travelInfo.set(travelInfo);
    }
}
