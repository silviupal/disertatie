package finalproject.silviupal.ro.myfinale.subcategories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import finalproject.silviupal.ro.myfinale.FirebaseController;
import finalproject.silviupal.ro.myfinale.Keys;
import finalproject.silviupal.ro.myfinale.R;
import finalproject.silviupal.ro.myfinale.base.BaseActivity;
import finalproject.silviupal.ro.myfinale.helper.DialogHelper;
import finalproject.silviupal.ro.myfinale.model.MainCategory;
import finalproject.silviupal.ro.myfinale.model.Subcategory;
import finalproject.silviupal.ro.myfinale.model.Vote;

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
    public void onItemClickListener(Subcategory item, List<Subcategory> list) {
        List<Subcategory> filteredList = new ArrayList<>();

        for (Subcategory subcategory : list) {
            subcategory.setSelected(subcategory.getId() == item.getId());
            filteredList.add(subcategory);
        }

        adapter.setList(filteredList);
        adapter.notifyDataSetChanged();
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

        // TODO: 6/18/2018 Check if this category has been voted by this user

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        adapter = new RvAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setList(mCategory.getSubcategories());
    }

    @OnClick(R.id.vote_btn)
    public void handleVote() {
        Subcategory selectedSubcategory = adapter.getSelectedSubcategory();
        if (selectedSubcategory == null) {
            DialogHelper.showDialogWithOneAction(this, getString(R.string.dialog_info_title), getString(R.string.dialog_select_one_subcategory), null);
            return;
        }

        Vote vote = new Vote();
        vote.setCategoryId(mCategory.getId());
        vote.setSubcategoryId(selectedSubcategory.getId());

        FirebaseController.getInstance().addVoteForUser(vote);

        finish();
    }
}
