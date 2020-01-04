package com.example.obstatistics.Saving;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.obstatistics.R;

import java.util.List;

public class RecordAdapter extends ArrayAdapter<Record> {

    public RecordAdapter(Context context, List<Record> records) {
        super(context, 0, records);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Record record = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_record, parent, false);
        }
        TextView tvDate = (TextView) convertView.findViewById(R.id.db_dateText);
        TextView tvName = (TextView) convertView.findViewById(R.id.db_nameText);
        TextView tvChip = (TextView) convertView.findViewById(R.id.db_chipText);
        TextView tvMoneyPaid = (TextView) convertView.findViewById(R.id.db_moneyPaidText);
        TextView tvCountCompetition = (TextView) convertView.findViewById(R.id.db_countCompetitionText);
        TextView tvTotalTime = (TextView) convertView.findViewById(R.id.db_totalTimeText);
        TextView tvTotalLoss = (TextView) convertView.findViewById(R.id.db_totalLossText);
        TextView tvMedalPlaces = (TextView) convertView.findViewById(R.id.db_medalPlacesText);
        TextView tvDiskEvents = (TextView) convertView.findViewById(R.id.db_diskPlacesText);
        TextView tvCathegories = (TextView) convertView.findViewById(R.id.db_cathegoriesText);
        TextView tvTotalDistance = (TextView) convertView.findViewById(R.id.db_totalDistanceText);
        TextView tvTotalElevation = (TextView) convertView.findViewById(R.id.db_totalElevationText);
        TextView tvTotalControlNumber = (TextView) convertView.findViewById(R.id.db_totalControlNumberText);

        if(record.getRegistration() == null) {
            tvDate.setText(record.getDateOfRecord());
        } else {
            tvDate.setText(record.getRegistration().toUpperCase() + ", uloženo " + record.getDateOfRecord());
        }
        tvName.setText(record.getIdentity());
        tvChip.setText(record.getChip());
        tvMoneyPaid.setText(record.getMoneyPaid());
        tvCountCompetition.setText(record.getCountCompetition());
        tvTotalTime.setText(record.getTotalTime());
        tvTotalLoss.setText(record.getTotalLoss());
        tvMedalPlaces.setText(record.getMedalPlaces());
        tvDiskEvents.setText(record.getDiskEvents());
        tvCathegories.setText(record.getCathegories());
        tvTotalDistance.setText(record.getTotalDistance());
        tvTotalElevation.setText(record.getTotalClimbing());
        tvTotalControlNumber.setText(record.getTotalControls());

        return convertView;
    }
}
