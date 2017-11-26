package tracer.vsw17;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import tracer.vsw17.base.BaseModelActivity;


public class FundedActivity extends BaseModelActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.funded_activity);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String[] milestoneArray = getResources().getStringArray(R.array.milestones);

        TextView title = findViewById(R.id.fund_raise_title);
        TextView milestone_1 = findViewById(R.id.fund_description_funded_description_1);
        TextView milestone_2 = findViewById(R.id.fund_description_funded_description_2);
        TextView milestone_3 = findViewById(R.id.fund_description_funded_description_3);
        TextView milestone_4 = findViewById(R.id.fund_description_funded_description_4);
        TextView milestone_5 = findViewById(R.id.fund_description_funded_description_5);

        ImageView imageView = findViewById(R.id.fund_description_image);
        title.setText(super.getFundRaiseModel().getTitle());
        milestone_1.setText(milestoneArray[0]);
        milestone_2.setText(milestoneArray[1]);
        milestone_3.setText(milestoneArray[2]);
        milestone_4.setText(milestoneArray[3]);
        milestone_5.setText(milestoneArray[4]);

        Glide.with(this)
            .load(super.getFundRaiseModel().getImageUrl())
            .into(imageView);
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
