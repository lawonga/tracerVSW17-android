package tracer.vsw17.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import mehdi.sakout.fancybuttons.FancyButton;
import tracer.vsw17.R;
import tracer.vsw17.logic.SimpleClickListener;
import tracer.vsw17.model.FundRaiseModel;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class FundRaiseAdapter extends RecyclerView.Adapter<FundRaiseAdapter.ViewHolder> {
    private List<FundRaiseModel> models;
    private Context context;
    private SimpleClickListener viewHolderClickListener;
    private SimpleClickListener viewHolderAltClickListener;

    public FundRaiseAdapter(List<FundRaiseModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public FundRaiseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fundraising_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FundRaiseModel model = models.get(position);
        holder.setTitle(model.getTitle());
        holder.setSubTitle(model.getSubTitle());
        holder.setImage(context, model.getImageUrl());
        holder.setModel(model);
        holder.setOnItemClickListener(viewHolderClickListener);
        holder.setOnAltClickListener(viewHolderAltClickListener);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setViewHolderClickListener(SimpleClickListener viewHolderClickListener) {
        this.viewHolderClickListener = viewHolderClickListener;
    }

    public void setViewHolderAltClickListener(SimpleClickListener viewHolderAltClickListener) {
        this.viewHolderAltClickListener = viewHolderAltClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, subTitle;
        private FancyButton give, learnMore;
        private ImageView imageView;
        private SimpleClickListener onItemClickListener;
        private FundRaiseModel model;
        private SimpleClickListener viewHolderAltClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.fund_raise_title);
            subTitle = itemView.findViewById(R.id.fund_raise_subtitle);
            imageView = itemView.findViewById(R.id.fund_raise_image);
            give = itemView.findViewById(R.id.button_give);
            learnMore = itemView.findViewById(R.id.button_learn_more);
            give.setOnClickListener(view -> onItemClickListener.onItemClicked(model, getAdapterPosition()));
            learnMore.setOnClickListener(view -> viewHolderAltClickListener.onItemClicked(model, getAdapterPosition()));
        }

        void setTitle(String text) {
            title.setText(text);
        }

        void setSubTitle(String text) {
            subTitle.setText(text);
        }

        void setImage(Context context, String imageUrl) {
            Glide.with(context)
                .load(imageUrl)
                .apply(bitmapTransform(new MultiTransformation<>(
                    new BlurTransformation(20),
                    new ColorFilterTransformation(R.color.colorPrimaryDark)
                )))
                .into(imageView);
        }

        void setOnItemClickListener(SimpleClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        void setModel(FundRaiseModel model) {
            this.model = model;
        }

        public void setOnAltClickListener(SimpleClickListener onAltClickListener) {
            this.viewHolderAltClickListener = onAltClickListener;
        }
    }
}
