package finalproject.silviupal.ro.myfinale.maincategories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import finalproject.silviupal.ro.myfinale.R;
import finalproject.silviupal.ro.myfinale.model.MainCategory;

/**
 * Created by Silviu Pal on 6/6/2018.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private final ItemClickListener listener;
    private List<MainCategory> list;

    public RvAdapter(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_category_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItemAtPosition(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public void setList(List<MainCategory> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private MainCategory getItemAtPosition(int position) {
        return list.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_logo)
        ImageView ivLogo;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final MainCategory item) {
            tvTitle.setText(item.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(item);
                }
            });
        }

    }
}
