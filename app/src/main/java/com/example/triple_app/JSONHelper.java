package com.example.triple_app;

import com.google.gson.Gson;
import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

class JSONHelper {
    //https://metanit.com/java/android/13.3.php
    private static final String FILE_NAME = "data.json";

    static boolean exportToJSON(Context context, List<QuestionOrTask> dataList) {

        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setQuestions(dataList);
        String jsonString = gson.toJson(dataItems);

        try(FileOutputStream fileOutputStream =
                    context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    static List<QuestionOrTask> importFromJSON(Context context) {

        try(FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            InputStreamReader streamReader = new InputStreamReader(fileInputStream)){

            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
            return  dataItems.getQuestions();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return null;
    }

    private static class DataItems {
        private List<QuestionOrTask> questions;

        List<QuestionOrTask> getQuestions() {
            return questions;
        }
        void setQuestions(List<QuestionOrTask> questions) {
            this.questions = questions;
        }
    }
}