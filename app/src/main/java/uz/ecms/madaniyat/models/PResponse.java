package uz.ecms.madaniyat.models;

public class PResponse {
    public int id;
    public int type_id;
    public String question;
    public String answer_a;
    public int ball_a;
    public String answer_b;
    public int ball_b;
    public String answer_c;
    public int ball_c;
    public String answer_d;
    public int ball_d;

    public PResponse(int id, int type_id, String question, String answer_a, int ball_a, String answer_b, int ball_b, String answer_c, int ball_c, String answer_d, int ball_d) {
        this.id = id;
        this.type_id = type_id;
        this.question = question;
        this.answer_a = answer_a;
        this.ball_a = ball_a;
        this.answer_b = answer_b;
        this.ball_b = ball_b;
        this.answer_c = answer_c;
        this.ball_c = ball_c;
        this.answer_d = answer_d;
        this.ball_d = ball_d;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer_a() {
        return answer_a;
    }

    public void setAnswer_a(String answer_a) {
        this.answer_a = answer_a;
    }

    public int getBall_a() {
        return ball_a;
    }

    public void setBall_a(int ball_a) {
        this.ball_a = ball_a;
    }

    public String getAnswer_b() {
        return answer_b;
    }

    public void setAnswer_b(String answer_b) {
        this.answer_b = answer_b;
    }

    public int getBall_b() {
        return ball_b;
    }

    public void setBall_b(int ball_b) {
        this.ball_b = ball_b;
    }

    public String getAnswer_c() {
        return answer_c;
    }

    public void setAnswer_c(String answer_c) {
        this.answer_c = answer_c;
    }

    public int getBall_c() {
        return ball_c;
    }

    public void setBall_c(int ball_c) {
        this.ball_c = ball_c;
    }

    public String getAnswer_d() {
        return answer_d;
    }

    public void setAnswer_d(String answer_d) {
        this.answer_d = answer_d;
    }

    public int getBall_d() {
        return ball_d;
    }

    public void setBall_d(int ball_d) {
        this.ball_d = ball_d;
    }
}
