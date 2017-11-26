package tracer.vsw17;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class FundRaiseAdapter extends RecyclerView.Adapter<FundRaiseAdapter.ViewHolder> {
    private List<FundRaiseModel> models;
    private Context context;
    private SimpleClickListener viewHolderClickListener;

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
        holder.setText(model.getTitle());
        holder.setImage(context, model.getImageUrl());
        holder.setModel(model);
        holder.setOnItemClickListener(viewHolderClickListener);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setViewHolderClickListener(SimpleClickListener viewHolderClickListener) {
        this.viewHolderClickListener = viewHolderClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView imageView;
        private SimpleClickListener onItemClickListener;
        private FundRaiseModel model;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.fund_raise_text);
            imageView = itemView.findViewById(R.id.fund_raise_image);
            itemView.setOnClickListener(view -> onItemClickListener.onItemClicked(model, getAdapterPosition()));
        }

        void setText(String text) {
            title.setText(text);
        }

        void setImage(Context context, String imageUrl) {
            Glide.with(context)
                .load(imageUrl)
                .apply(bitmapTransform(new BlurTransformation(25)))
                .into(imageView);
        }

        void setOnItemClickListener(SimpleClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        void setModel(FundRaiseModel model) {
            this.model = model;
        }
    }
}
