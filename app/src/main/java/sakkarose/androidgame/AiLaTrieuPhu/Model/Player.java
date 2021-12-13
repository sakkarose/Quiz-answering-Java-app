package sakkarose.androidgame.AiLaTrieuPhu.Model;

public class Player {

    public String Name;
    public String Score;
    public String ID;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        this.Score = score;
    }

    public String getID() {
        return ID;
    }

    public void setID(String id) {
        this.ID = id;
    }

    public Player() {}

    public Player(String name, String score) {
        this.Name = name;
        this.Score = score;
    }
}
