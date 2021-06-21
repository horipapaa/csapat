package hu.alkfejl.model;

import javafx.beans.property.*;

public class Message {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private IntegerProperty from = new SimpleIntegerProperty(this, "from");
    private IntegerProperty to = new SimpleIntegerProperty(this, "to");
    private IntegerProperty teamId = new SimpleIntegerProperty(this, "teamId");
    private StringProperty content = new SimpleStringProperty(this, "content");

    public int getTeamId() {
        return teamId.get();
    }

    public IntegerProperty teamIdProperty() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId.set(teamId);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getFrom() {
        return from.get();
    }

    public IntegerProperty fromProperty() {
        return from;
    }

    public void setFrom(int from) {
        this.from.set(from);
    }

    public int getTo() {
        return to.get();
    }

    public IntegerProperty toProperty() {
        return to;
    }

    public void setTo(int to) {
        this.to.set(to);
    }

    public String getContent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }
}
