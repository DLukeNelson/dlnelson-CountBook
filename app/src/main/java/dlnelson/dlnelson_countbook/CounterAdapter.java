package dlnelson.dlnelson_countbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import dlnelson.dlnelson_countbook.Counter;
import dlnelson.dlnelson_countbook.R;

/**
 * Created by Luke on 2017-09-25.
 */

public class CounterAdapter extends ArrayAdapter<Counter> {
    private final Context context;
    private final List<Counter> values;


    public CounterAdapter(Context context, List<Counter> values) {
        super(context, 0, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.counter_list_item, parent, false);

        //Get "-" button and attach an decrement method.
        Button decButton = (Button) rowView.findViewById(R.id.dec_button);
        decButton.setTag(position);
        decButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                Counter counter = getItem(position);
                counter.decrementValue();
                notifyDataSetChanged();
            }
        });

        //Get "+" button and attach an increment method.
        Button incButton = (Button) rowView.findViewById(R.id.inc_button);
        incButton.setTag(position);
        incButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                Counter counter = getItem(position);
                counter.incrementValue();
                notifyDataSetChanged();
            }
        });

        TextView name = (TextView) rowView.findViewById(R.id.name_field);
        TextView date = (TextView) rowView.findViewById(R.id.date_field);
        TextView value = (TextView) rowView.findViewById(R.id.value_field);
        Counter counter = values.get(position);
        name.setText(counter.getName());
        date.setText(counter.getShortDate());
        value.setText(String.format("%d", counter.getCurrentValue()));
        return rowView;
    }
}
