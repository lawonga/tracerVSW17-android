package tracer.vsw17;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tracer.vsw17.adapter.FundRaiseAdapter;
import tracer.vsw17.base.BaseActivity;
import tracer.vsw17.model.FundRaiseModel;

import static tracer.vsw17.constants.CONSTANTS.DESCRIPTION_ACTIVITY;


public class MainActivity extends BaseActivity {
    ArrayList<FundRaiseModel> fundRaiseModels;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey(DESCRIPTION_ACTIVITY)) {
            fundRaiseModels = Parcels.unwrap(getIntent().getParcelableExtra(DESCRIPTION_ACTIVITY));
        } else if (savedInstanceState != null && savedInstanceState.containsKey(DESCRIPTION_ACTIVITY)) {
            fundRaiseModels = Parcels.unwrap(savedInstanceState.getParcelable(DESCRIPTION_ACTIVITY));
        }

        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.fund_raise_list);

        if (fundRaiseModels == null) {
            fundRaiseModels = (ArrayList<FundRaiseModel>) getModelList();
        }

        FundRaiseAdapter fundRaiseAdapter = new FundRaiseAdapter(fundRaiseModels, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fundRaiseAdapter.setViewHolderClickListener((fundRaiseModel, position) -> {
                Intent intent = new Intent(this, DescriptionActivity.class);
                intent.putExtra(DESCRIPTION_ACTIVITY, Parcels.wrap(fundRaiseModel));
                startActivity(intent);
            });
        fundRaiseAdapter.setViewHolderAltClickListener((fundRaiseModel, position) -> {
                Intent intent = new Intent(this, FundedActivity.class);
                intent.putExtra(DESCRIPTION_ACTIVITY, Parcels.wrap(fundRaiseModel));
                startActivity(intent);
        });
        recyclerView.setAdapter(fundRaiseAdapter);
//        getSupportActionBar().
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_icon);
//        getSupportActionBar().setIcon(); // Todo later
//        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(DESCRIPTION_ACTIVITY, Parcels.wrap(fundRaiseModels));
    }

    private List<FundRaiseModel> getModelList() {
        List<FundRaiseModel> list = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.titles);
        String[] subTitles = getResources().getStringArray(R.array.subtitles);
        String[] partners = getResources().getStringArray(R.array.partners);
        String[] hosts = getResources().getStringArray(R.array.hosts);
        String[] descriptions = getResources().getStringArray(R.array.descriptions);
        String[] imageUrls = getResources().getStringArray(R.array.image_urls);

        Random random = new Random(100);
        for (int i = 0; i < titles.length; i++) {
            FundRaiseModel model = new FundRaiseModel();
            model.setTitle(titles[i]);
            model.setSubTitle(subTitles[i]);
            model.setDescription(descriptions[i]);
            model.setPartner(partners[i]);
            model.setHost(hosts[i]);
            int percentage = random.nextInt(100);
            if (percentage <= 35) percentage += 35;
            model.setPercentage(percentage);
            model.setEthCost((random.nextDouble() * 100) / 100);
            model.setImageUrl(imageUrls[i]);
            list.add(model);
        }
        return list;
    }
}
