package dlnelson.dlnelson_countbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static int EDIT_COUNTER = 0;
    public static int NEW_COUNTER = 1;
    public static Counter counterToEdit;
    private static int counterPositionInList;
    private boolean saveLoadFiles = true;

    private SaveableCounterList counterList;
    private CounterAdapter counterAdapter;
    private ListView counterListView;
    private TextView totalCounters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        totalCounters = (TextView) findViewById(R.id.counters_total);
        counterListView = (ListView) findViewById(R.id.counterListView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            newCounter();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        counterList = new SaveableCounterList(getApplicationContext());
        counterAdapter = new CounterAdapter(this, counterList);
        counterListView.setAdapter(counterAdapter);

        counterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editCounter(position);
            }
        });
    }

    public void newCounter() {
        Intent intent = new Intent(this, CounterActivity.class);
        counterToEdit = new Counter("A", 0, "");
        intent.putExtra("requestCode", NEW_COUNTER);
        startActivityForResult(intent, NEW_COUNTER);
    }

    public void editCounter(int position) {
        Intent intent = new Intent(this, CounterActivity.class);
        counterToEdit = counterList.get(position);
        counterPositionInList = position;
        intent.putExtra("requestCode", EDIT_COUNTER);
        this.saveLoadFiles = false;
        startActivityForResult(intent, EDIT_COUNTER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (EDIT_COUNTER == requestCode && CounterActivity.COUNTER_DELETE == resultCode) {
            counterList.remove(counterPositionInList);
        } else if (NEW_COUNTER == requestCode && CounterActivity.COUNTER_SAVED == resultCode) {
            counterList.add(counterToEdit);
        }
        counterAdapter.notifyDataSetChanged();
        setTotalCounters();
    }

    public void setTotalCounters() {
        totalCounters.setText(String.format("Counters: %d", counterList.size()));
    }
}
