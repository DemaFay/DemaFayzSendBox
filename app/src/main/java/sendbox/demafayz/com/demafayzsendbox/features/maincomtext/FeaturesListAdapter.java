package sendbox.demafayz.com.demafayzsendbox.features.maincomtext;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sendbox.demafayz.com.demafayzsendbox.R;

/**
 * Created by demafayz on 01.09.16.
 */
public class FeaturesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    private List<String> items;
    private Context context;
    private LayoutInflater inflater;

    public FeaturesListAdapter(List<String> items) {
        this.items = items == null ? new ArrayList<String>() : items;
    }

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tvFeatureTitle);
             itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onRecyclerItemClickListener != null) {
                onRecyclerItemClickListener.onRecyclerItemClickListener(view);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
            inflater = LayoutInflater.from(context);
        }
        View itemView = inflater.inflate(R.layout.fragment_features_list_item, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        vh.title.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public interface OnRecyclerItemClickListener {
        public void onRecyclerItemClickListener(View view);
    }
}
