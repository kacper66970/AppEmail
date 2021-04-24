package applicationEmail.Email.Model;

public class Email {

    private String to;

    private String subcejt;

    private String messeges;

    public Email() {
    }

    public Email(String to, String subcejt, String messeges) {
        this.to = to;
        this.subcejt = subcejt;
        this.messeges = messeges;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubcejt() {
        return subcejt;
    }

    public void setSubcejt(String subcejt) {
        this.subcejt = subcejt;
    }

    public String getMesseges() {
        return messeges;
    }

    public void setMesseges(String messeges) {
        this.messeges = messeges;
    }

}

