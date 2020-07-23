package concurrent_collection.thread_safe_hashmap;

import java.util.Date;

public class Opreation  {
    private String user;
    private String opreation;
    private Date   date;

    public Opreation() {
    }

    public Opreation(String user, String opreation, Date date) {
        this.user = user;
        this.opreation = opreation;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOpreation() {
        return opreation;
    }

    public void setOpreation(String opreation) {
        this.opreation = opreation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
