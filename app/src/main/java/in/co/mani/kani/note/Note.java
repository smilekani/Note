package in.co.mani.kani.note;

/**
 * Created by zmxxkan on 8/4/2017.
 */

public class Note {

    private String _id;
    private String title;
    private String type;
    private String description;
    private String time;
    private String date;

   Note() {
   }

   Note(String _id, String title, String type, String description, String time, String date){
       this._id = _id;
       this.title = title;
       this.type = type;
       this.description = description;
       this.time = time;
       this.date = date;
   }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return title + ((description != null) ? "\n+description" : "") ;
    }
}
