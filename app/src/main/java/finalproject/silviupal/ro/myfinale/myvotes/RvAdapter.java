package finalproject.silviupal.ro.myfinale.myvotes;

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
import finalproject.silviupal.ro.myfinale.model.MyVoteModel;
import finalproject.silviupal.ro.myfinale.model.Vote;

/**
 * Created by Silviu Pal on 6/6/2018.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private List<MyVoteModel> list;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_vote_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItemAtPosition(position), position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public void setList(List<MyVoteModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private MyVoteModel getItemAtPosition(int position) {
        return list.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_position)
        public TextView tvItemPosition;

        @BindView(R.id.tv_category_name)
        public TextView tvCategoryName;

        @BindView(R.id.tv_subcategory_name)
        public TextView tvSubcategoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final MyVoteModel item, int position) {
            tvItemPosition.setText(String.valueOf(position + 1));
            tvCategoryName.setText(item.getCategoryName());
            tvSubcategoryName.setText(item.getSubcategoryName());
        }

    }
}
