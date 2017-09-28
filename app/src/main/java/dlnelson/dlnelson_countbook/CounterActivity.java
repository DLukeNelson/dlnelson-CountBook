package dlnelson.dlnelson_countbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CounterActivity extends AppCompatActivity {
    public static int COUNTER_NO_CHANGE = 0;
    public static int COUNTER_DELETE = 1;
    public static int COUNTER_SAVED = 2;

    private Counter counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);

        //get the fields that need to be populated from the counter.
        final EditText nameField = (EditText) findViewById(R.id.name_field);
        final TextView dateField = (TextView) findViewById(R.id.date_field);
        final EditText initialField = (EditText) findViewById(R.id.initial_field);
        final EditText currentField = (EditText) findViewById(R.id.current_field);
        final EditText commentField = (EditText) findViewById(R.id.comment_field);
        int requestCode = MainActivity.EDIT_COUNTER;        /* Choose edit by default */

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null && bundle.get("requestCode") != null) {
            requestCode = (int) bundle.get("requestCode");
        }

        //get the counter to display from the MainActivity.
        counter = MainActivity.counterToEdit;

        if (requestCode == MainActivity.EDIT_COUNTER) {
            //populate the fields from the info in the counter.
            nameField.setText(counter.getName());
            dateField.setText(counter.getShortDate());
            initialField.setText(String.format("%d", counter.getInitialValue()));
            currentField.setText(String.format("%d", counter.getCurrentValue()));
            commentField.setText(counter.getComment());

            //Only attach a handler to the delete button if in edit mode.
            Button delete = (Button) findViewById(R.id.delete_button);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setResult(COUNTER_DELETE);
                    finish();
                }
            });
        }
        else {
            Button delete = (Button) findViewById(R.id.delete_button);
            delete.setEnabled(false);
            currentField.setText("Set to initial value.");
            currentField.setFocusable(false);
        }

        //Attach handlers to the save, delete, and cancel buttons.
        Button save = (Button) findViewById(R.id.save_button);
        final int finalRequestCode = requestCode;
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameField.getText().toString();
                String initialValue = initialField.getText().toString();
                String currentValue = currentField.getText().toString();

                //Check if the user left any empty fields.
                if (name.equals("") || initialValue.equals("") || currentValue.equals("") ) {
                    Toast.makeText(getApplicationContext(), getString(R.string.unfilled_fields),
                            Toast.LENGTH_LONG).show();
                } else {
                    counter.setName(name);
                    counter.setInitialValue(Integer.valueOf(initialValue));
                    if(finalRequestCode == MainActivity.NEW_COUNTER) {
                        counter.setCurrentValue(Integer.valueOf(initialValue));
                    } else {
                        counter.setCurrentValue(Integer.valueOf(currentValue));
                    }
                    counter.setComment(commentField.getText().toString());

                    setResult(COUNTER_SAVED);
                    finish();
                }
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
