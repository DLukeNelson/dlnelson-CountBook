package dlnelson.dlnelson_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);

        //get the fields that need to be populated from the counter.
        EditText nameField = (EditText) findViewById(R.id.name_field);
        EditText initialField = (EditText) findViewById(R.id.initial_field);
        EditText currentField = (EditText) findViewById(R.id.current_field);
        EditText commentField = (EditText) findViewById(R.id.comment_field);

        //get information about the counter to display from the intent.
        Intent intent = getIntent();
        String json = intent.getStringExtra(MainActivity.COUNTER);
        Counter counter = (new Gson()).fromJson(json, new TypeToken<Counter>() {}.getType());

        //populate the fields from the info in the counter.
        nameField.setText(counter.getName());
        initialField.setText(String.format("%d", counter.getInitialValue()));
        currentField.setText(String.format("%d", counter.getCurrentValue()));
        commentField.setText(counter.getComment());
    }
}
