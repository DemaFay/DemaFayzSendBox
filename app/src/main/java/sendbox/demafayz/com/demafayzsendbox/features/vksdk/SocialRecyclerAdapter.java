package sendbox.demafayz.com.demafayzsendbox.features.vksdk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sendbox.demafayz.com.demafayzsendbox.R;

/**
 * Created by demafayz on 06.09.16.
 */
public class SocialRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> photoPaths;
    private Context context;
    private LayoutInflater inflater;

    private class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView picture;

        public ViewHolder(View itemView) {
            super(itemView);
            picture = (ImageView) itemView.findViewById(R.id.ivSocialImage);
        }
    }

    public SocialRecyclerAdapter(List<String> photoPaths) {
        this.photoPaths = photoPaths;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null || inflater == null) {
            context = parent.getContext();
            inflater = LayoutInflater.from(context);
        }
        View itemView = inflater.inflate(R.layout.fragment_social_item, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        Picasso.with(context).load(photoPaths.get(position)).fit().centerCrop().into(vh.picture);
    }

    @Override
    public int getItemCount() {
        return photoPaths.size();
    }
}
