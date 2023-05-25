package com.example.triple_app;

public class QuestionOrTask {
    private String type;
    private String difficulty;
    private String questionOrTask;

    public QuestionOrTask(String type, String difficulty, String questionOrTask) {
        this.type = type;
        this.difficulty = difficulty;
        this.questionOrTask = questionOrTask;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestionOrTask() {
        return questionOrTask;
    }

    public void setQuestionOrTask(String questionOrTask) {
        this.questionOrTask = questionOrTask;
    }

    public String toString(){
        return "type: "+ type +"\n" +
                "difficulty: "+ difficulty +"\n" +
                "question: "+ questionOrTask;
    }
}
