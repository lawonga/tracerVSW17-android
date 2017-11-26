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

import static tracer.vsw17.CONSTANTS.DESCRIPTION_ACTIVITY;


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
            }
        );
        recyclerView.setAdapter(fundRaiseAdapter);
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
        String[] descriptions = getResources().getStringArray(R.array.descriptions);
        String[] imageUrls = getResources().getStringArray(R.array.image_urls);

        Random random = new Random(100);
        for (int i = 0; i < titles.length; i++) {
            FundRaiseModel model = new FundRaiseModel();
            model.setTitle(titles[i]);
            model.setDescription(descriptions[i]);
            model.setPercentage(random.nextInt(100));
            model.setEthCost((random.nextDouble() * 100) / 100);
            model.setImageUrl(imageUrls[i]);
            list.add(model);
        }

        FundRaiseModel modelOne = new FundRaiseModel("A good start");
        modelOne.setDescription("Help cure cancer!");
        modelOne.setPercentage(13);
        modelOne.setEthCost(0.5);

        FundRaiseModel modelTwo = new FundRaiseModel("Disease");
        modelTwo.setDescription("Help cure disease");
        modelTwo.setPercentage(98);
        modelTwo.setEthCost(0.1);

        FundRaiseModel modelThree = new FundRaiseModel("Virus");
        modelThree.setDescription("Help cure virus");
        modelThree.setPercentage(34);
        modelThree.setEthCost(1);

        FundRaiseModel modelFour = new FundRaiseModel("Social Issue");
        modelFour.setDescription("Help cure social issues");
        modelFour.setPercentage(65);
        modelFour.setEthCost(0.8);

        list.add(modelOne);
        list.add(modelTwo);
        list.add(modelThree);
        list.add(modelFour);
        return list;
    }
}
