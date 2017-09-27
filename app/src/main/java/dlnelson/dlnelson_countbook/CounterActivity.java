package dlnelson.dlnelson_countbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CounterActivity extends AppCompatActivity {
    public static int COUNTER_NO_CHANGE = 0;
    public static int COUNTER_DELETE = 1;
    public static int COUNTER_CHANGED = 2;

    private Counter counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);

        //get the fields that need to be populated from the counter.
        final EditText nameField = (EditText) findViewById(R.id.name_field);
        final EditText initialField = (EditText) findViewById(R.id.initial_field);
        final EditText currentField = (EditText) findViewById(R.id.current_field);
        final EditText commentField = (EditText) findViewById(R.id.comment_field);

        //get information about the counter to display from the intent.
        counter = MainActivity.counterToEdit;

        //populate the fields from the info in the counter.
        nameField.setText(counter.getName());
        initialField.setText(String.format("%d", counter.getInitialValue()));
        currentField.setText(String.format("%d", counter.getCurrentValue()));
        commentField.setText(counter.getComment());

        //Attach handlers to the save, delete, and cancel buttons.
        Button save = (Button) findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.setName(nameField.getText().toString());
                counter.setInitialValue(Integer.valueOf(initialField.getText().toString()));
                counter.setCurrentValue(Integer.valueOf(currentField.getText().toString()));
                counter.setComment(commentField.getText().toString());

                setResult(COUNTER_CHANGED);
                finish();
            }
        });

        Button delete = (Button) findViewById(R.id.delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(COUNTER_DELETE);
                finish();
            }
        });

        Button cancel = (Button) findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(COUNTER_NO_CHANGE);
                finish();
            }
        });
    }
}
