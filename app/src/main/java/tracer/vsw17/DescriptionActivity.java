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

import java.util.ArrayList;
import java.util.List;

import tracer.vsw17.base.BaseModelActivity;

import static tracer.vsw17.constants.CONSTANTS.BAR_CHART;


public class DescriptionActivity extends BaseModelActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.causes));
        }

        setContentView(R.layout.description_activity);
        TextView titleView = findViewById(R.id.fund_raise_title);
        TextView percentageView = findViewById(R.id.fund_raise_cost);
        TextView descriptionView = findViewById(R.id.fund_raise_description);
        TextView host = findViewById(R.id.fund_raise_host_name);
        TextView partner = findViewById(R.id.fund_raise_partner_name);
        ImageView imageView = findViewById(R.id.fund_raise_image);
        HorizontalBarChart chart = findViewById(R.id.fund_raise_horizontal_bar_chart);
        setupBarData(chart);

        titleView.setText(super.getFundRaiseModel().getTitle());
        percentageView.setText(super.getFundRaiseModel().getEthCostDescription());
        descriptionView.setText(super.getFundRaiseModel().getDescription());
        host.setText(super.getFundRaiseModel().getHost());
        partner.setText(super.getFundRaiseModel().getPartner());

        Glide.with(this)
            .load(super.getFundRaiseModel().getImageUrl())
            .into(imageView);
    }

    void setupBarData(HorizontalBarChart chart) {
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, super.getFundRaiseModel().getPercentage()));
        BarDataSet barDataSet = new BarDataSet(entries, BAR_CHART);
        barDataSet.setColor(R.color.green);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(20);
        barData.setValueTextSize(20f);
        barData.setValueTextColor(R.color.white);
        barData.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> {
            String toReturn = "";
            if (value > 35) toReturn = String.valueOf((int) value) + "% of goal";
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
