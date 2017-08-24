package nogu96.streetfighterthirdstrike.view.character_detail.youtube_links;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nogu96.streetfighterthirdstrike.R;
import nogu96.streetfighterthirdstrike.model.pojo.youtube.Youtube;

public class AdapterYoutube extends RecyclerView.Adapter implements View.OnClickListener{

    private List<Youtube> youtubeList;
    private Context context;
    private View.OnClickListener listener;

    public AdapterYoutube(Context context, List<Youtube> youtubeList){
        this.youtubeList = youtubeList;
        this.context = context;
    }

    public Youtube getYoutubeAtPosition(Integer position){
        return youtubeList.get(position);
    }

    public void setListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_youtube_item, parent, false);
        view.setOnClickListener(listener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.loadData(youtubeList.get(position));
    }

    @Override
    public int getItemCount() {
        return youtubeList.size();
    }

    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onClick(v);
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView txtTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image_view_youtube_item);
            txtTitle = (TextView)itemView.findViewById(R.id.text_view_youtube_item);
        }

        public void loadData(Youtube youtube){
            txtTitle.setText(youtube.getTitle());
            Picasso.with(context)
                    .load(youtube.getImageUrl())
                    .into(imageView);
        }
    }
}
