package tracer.vsw17.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.parceler.Parcels;

import tracer.vsw17.model.FundRaiseModel;

import static tracer.vsw17.constants.CONSTANTS.DESCRIPTION_ACTIVITY;

public abstract class BaseModelActivity extends BaseActivity {

    private FundRaiseModel fundRaiseModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey(DESCRIPTION_ACTIVITY)) {
            fundRaiseModel = Parcels.unwrap(getIntent().getParcelableExtra(DESCRIPTION_ACTIVITY));
        } else if (savedInstanceState != null && savedInstanceState.containsKey(DESCRIPTION_ACTIVITY)) {
            fundRaiseModel = Parcels.unwrap(savedInstanceState.getParcelable(DESCRIPTION_ACTIVITY));
        }
    }

    public FundRaiseModel getFundRaiseModel() {
        return fundRaiseModel;
    }
}
