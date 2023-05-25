package com.example.triple_app;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InputFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InputFragment() {
        // Required empty public constructor
    }
    /**
     *
     *
     *
     * */

    private Button dataActivityButton, okButton;
    private EditText inputField;
    private RadioGroup taskDifficulty, taskType;


    interface OnFragmentSendDataListener {
        void onSendData(String data);
    }

    private OnFragmentSendDataListener fragmentSendDataListener;
    String[] countries = { "Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InputFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InputFragment newInstance(String param1, String param2) {
        InputFragment fragment = new InputFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_input, container, false);
        okButton = view.findViewById(R.id.okButton);
        dataActivityButton = view.findViewById(R.id.dataActivityButton);
        inputField = view.findViewById(R.id.inputField);
        taskDifficulty = view.findViewById(R.id.taskDifficulty);
        taskType = view.findViewById(R.id.taskType);

        okButton.setOnClickListener(v -> {
            if(validateInput())
            {
                fragmentSendDataListener.onSendData(infoAnswer(view));
                saveAnswer(view);
                clearVisualInput();
            }});
        return view;
    }

    private boolean validateInput () {
        if (taskDifficulty.getCheckedRadioButtonId() == -1
        || taskDifficulty.getCheckedRadioButtonId() == -1
        || inputField.getText().toString().matches(""))
        {
            Toast.makeText(getActivity(), "Не всі дані заповнено.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private String infoAnswer (View v) {
        RadioButton difficultyButton = v.findViewById(
                taskDifficulty.getCheckedRadioButtonId());
        RadioButton typeButton = v.findViewById(
                taskType.getCheckedRadioButtonId());
        return "тип: " + typeButton.getText() + "\n" +
            "важкість: " + difficultyButton.getText() + "\n" +
                "текст: " + inputField.getText();
    }

    private void saveAnswer (View v) {
        RadioButton difficultyButton = v.findViewById(
                taskDifficulty.getCheckedRadioButtonId());
        RadioButton typeButton = v.findViewById(
                taskType.getCheckedRadioButtonId());
        QuestionOrTask s = new QuestionOrTask(
                typeButton.getText().toString(),
                difficultyButton.getText().toString(),
                inputField.getText().toString());
        try {
            JSONHelper.exportToJSON(getActivity(), Collections.singletonList(s));
            Toast.makeText(getActivity(), "Дані успішно збережено.", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(getActivity(), "Помилка збереження файлу: \n"+
                    e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void clearVisualInput() {
        inputField.setText(null);
        taskDifficulty.clearCheck();
        taskType.clearCheck();
    }

    private void sendToActivity() {

    }
}