package finalproject.silviupal.ro.myfinale.subcategories;

import android.support.annotation.NonNull;
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
import finalproject.silviupal.ro.myfinale.model.Subcategory;

/**
 * Created by Silviu Pal on 6/6/2018.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private final ItemClickListener listener;
    private List<Subcategory> list;

    public RvAdapter(ItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_category_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItemAtPosition(position), listener);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public void setList(List<Subcategory> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private Subcategory getItemAtPosition(int position) {
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

        public void bind(final Subcategory item, final ItemClickListener listener) {
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
