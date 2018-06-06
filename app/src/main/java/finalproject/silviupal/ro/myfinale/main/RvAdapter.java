package finalproject.silviupal.ro.myfinale.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import finalproject.silviupal.ro.myfinale.R;
import finalproject.silviupal.ro.myfinale.model.MainCategory;

/**
 * Created by Silviu Pal on 6/6/2018.
 */

public class RvAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<MainCategory> list;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_category_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainCategory item = getItemAtPosition(position);

        holder.tvTitle.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public void setList(List<MainCategory> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private MainCategory getItemAtPosition(int position){
        return list.get(position);
    }
}
