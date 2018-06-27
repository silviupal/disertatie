package finalproject.silviupal.ro.myfinale.myvotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import finalproject.silviupal.ro.myfinale.Keys;
import finalproject.silviupal.ro.myfinale.R;
import finalproject.silviupal.ro.myfinale.base.BaseActivity;
import finalproject.silviupal.ro.myfinale.data.UserProfile;
import finalproject.silviupal.ro.myfinale.model.MainCategory;
import finalproject.silviupal.ro.myfinale.model.MyVoteModel;
import finalproject.silviupal.ro.myfinale.model.Subcategory;
import finalproject.silviupal.ro.myfinale.model.Vote;

public class MyVotesActivity extends BaseActivity {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_no_votes_title)
    TextView tvNoVotesTitle;

    @BindView(R.id.rv_my_votes)
    RecyclerView recyclerView;

    private RvAdapter adapter;

    private List<MainCategory> mCategories = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getExtras();

        initToolbar(toolbar, tvToolbarTitle, getString(R.string.my_votes_title), true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (UserProfile.getInstance().getVoteList().size() == 0) {
            tvNoVotesTitle.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvNoVotesTitle.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            initRecyclerView();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_votes;
    }

    private void initToolbar() {
        initToolbar(toolbar, tvToolbarTitle, getString(R.string.title_main_activity), true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RvAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setList(getVoteList());
    }

    private List<MyVoteModel> getVoteList() {
        List<MyVoteModel> list = new ArrayList<>();

        List<Vote> voteList = UserProfile.getInstance().getVoteList();

        for (Vote vote : voteList) {
            for (MainCategory category : mCategories) {
                if (category.getId() == vote.getCategoryId()) {
                    for (Subcategory subcategory : category.getSubcategories()) {
                        if (subcategory.getId() == vote.getSubcategoryId()) {
                            list.add(new MyVoteModel(category.getName(), subcategory.getName()));
                        }
                    }
                }
            }
        }

        return list;
    }

    public void getExtras() {
        Intent intent = getIntent();
        if (intent != null) {
            mCategories = intent.getParcelableArrayListExtra(Keys.KEY_EXTRA_CATEGORIES);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
