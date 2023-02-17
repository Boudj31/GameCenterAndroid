package fr.dawan.gamecenter.models;

public class Game {

    private long id;
    private String title;
    private String description;
    private String yearReleased;
    private String console;
    private String imgPath;

    public Game(String title, String description, String yearReleased, String console, String imgPath) {
        this.title = title;
        this.description = description;
        this.yearReleased = yearReleased;
        this.console = console;
        this.imgPath = imgPath;
    }

    public Game() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(String yearReleased) {
        this.yearReleased = yearReleased;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
