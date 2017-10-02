package dlnelson.dlnelson_countbook;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luke on 2017-09-28.
 */

public class SaveableCounterList {
    private static String FILENAME = "dlnelson.counterList.sav";

    private Context context;
    private List<Counter> values;

    public SaveableCounterList(Context context) {
        super();
        this.context = context;
        load();
    }

    public void save() {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(this.values, osw);
            osw.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void load() {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();
            values = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            values = new ArrayList<Counter>();
        }
    }

    public List<Counter> getValues() {
        return values;
    }

    public Counter get(int index) {
        return this.values.get(index);
    }

    public void remove(int index) {
        this.values.remove(index);
    }

    public void add(Counter counter) {
        this.values.add(counter);
    }

    public int size() {
        return this.values.size();
    }

}
