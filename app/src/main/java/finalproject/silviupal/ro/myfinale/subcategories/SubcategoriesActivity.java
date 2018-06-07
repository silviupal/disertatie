package finalproject.silviupal.ro.myfinale.subcategories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import butterknife.BindView;
import finalproject.silviupal.ro.myfinale.Keys;
import finalproject.silviupal.ro.myfinale.R;
import finalproject.silviupal.ro.myfinale.base.BaseActivity;
import finalproject.silviupal.ro.myfinale.subcategories.ItemClickListener;
import finalproject.silviupal.ro.myfinale.subcategories.RvAdapter;
import finalproject.silviupal.ro.myfinale.model.MainCategory;
import finalproject.silviupal.ro.myfinale.model.Subcategory;

/**
 * Created by Silviu Pal on 6/7/2018.
 */

public class SubcategoriesActivity extends BaseActivity implements ItemClickListener {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_main_categories)
    RecyclerView recyclerView;

    RvAdapter adapter;

    MainCategory mCategory = new MainCategory();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDataFromIntent();

        initToolbar(toolbar, tvToolbarTitle, getActivityTitle(), true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initRecyclerView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_subcategories;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onItemClickListener(Subcategory item) {

    }



    private void getDataFromIntent() {
        if (getIntent() != null) {
            mCategory = getIntent().getParcelableExtra(Keys.KEY_CATEGORY);
        }
    }

    private String getActivityTitle() {
        return !TextUtils.isEmpty(mCategory.getName()) ?
                mCategory.getName() : "";
    }

    private void initRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(manager);

        adapter = new RvAdapter(this);
        recyclerView.setAdapter(adapter);

        adapter.setList(mCategory.getSubcategories());
    }


}
