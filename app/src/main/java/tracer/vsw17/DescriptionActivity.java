package tracer.vsw17;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import static tracer.vsw17.CONSTANTS.BAR_CHART;
import static tracer.vsw17.CONSTANTS.DESCRIPTION_ACTIVITY;


public class DescriptionActivity extends BaseActivity {

    private FundRaiseModel fundRaiseModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey(DESCRIPTION_ACTIVITY)) {
            fundRaiseModel = Parcels.unwrap(getIntent().getParcelableExtra(DESCRIPTION_ACTIVITY));
        } else if (savedInstanceState != null && savedInstanceState.containsKey(DESCRIPTION_ACTIVITY)) {
            fundRaiseModel = Parcels.unwrap(savedInstanceState.getParcelable(DESCRIPTION_ACTIVITY));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.causes));
        }

        setContentView(R.layout.description_activity);
        TextView titleView = findViewById(R.id.fund_raise_title);
        TextView percentageView = findViewById(R.id.fund_raise_percentage);
        TextView descriptionView = findViewById(R.id.fund_raise_description);
        ImageView imageView = findViewById(R.id.fund_raise_image);
        HorizontalBarChart chart = findViewById(R.id.fund_raise_horizontal_bar_chart);

        titleView.setText(fundRaiseModel.getTitle());
        percentageView.setText(fundRaiseModel.getEthCostDescription());
        descriptionView.setText(fundRaiseModel.getDescription());

        Glide.with(this)
            .load(fundRaiseModel.getImageUrl())
            .into(imageView);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, fundRaiseModel.getPercentage()));
        BarDataSet barDataSet = new BarDataSet(entries, BAR_CHART);
        barDataSet.setColor(R.color.green);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(20);
        barData.setValueTextSize(32f);
        barData.setValueTextColor(R.color.white);
        barData.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> {
            String toReturn = "";
            if (value > 0) toReturn = String.valueOf((int) value) + "%";
            return toReturn;
        });
        chart.setData(barData);
        chart.setDrawValueAboveBar(false);
        chart.setFitBars(true);
        chart.getXAxis().setEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisLeft().setAxisMinimum(0);
        chart.getAxisLeft().setAxisMaximum(100);
        chart.getAxisRight().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.setClickable(false);
        chart.setTouchEnabled(false);
        chart.invalidate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
