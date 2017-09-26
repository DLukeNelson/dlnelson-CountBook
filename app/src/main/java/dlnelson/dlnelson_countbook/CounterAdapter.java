package dlnelson.dlnelson_countbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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
        TextView name = (TextView) rowView.findViewById(R.id.name_field);
        TextView date = (TextView) rowView.findViewById(R.id.date_field);
        TextView value = (TextView) rowView.findViewById(R.id.value_field);
        name.setText(values.get(position).getName());
        date.setText(values.get(position).getShortDate());
        value.setText(((Integer)values.get(position).getCurrentValue()).toString());
        return rowView;
    }
}