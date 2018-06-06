package finalproject.silviupal.ro.myfinale.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import finalproject.silviupal.ro.myfinale.R;

/**
 * Created by Silviu Pal on 6/6/2018.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
