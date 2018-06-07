package finalproject.silviupal.ro.myfinale.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import finalproject.silviupal.ro.myfinale.FirebaseController;
import finalproject.silviupal.ro.myfinale.Keys;
import finalproject.silviupal.ro.myfinale.R;
import finalproject.silviupal.ro.myfinale.base.BaseActivity;
import finalproject.silviupal.ro.myfinale.helper.MainActivityHelper;
import finalproject.silviupal.ro.myfinale.model.MainCategory;
import finalproject.silviupal.ro.myfinale.subcategories.SubcategoriesActivity;

public class MainActivity extends BaseActivity implements ItemClickListener {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_main_categories)
    RecyclerView recyclerView;

    RvAdapter adapter;

    private ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            List<MainCategory> mainCategories = new ArrayList<>();

            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                mainCategories.add(postSnapshot.getValue(MainCategory.class));
            }

            adapter.setList(mainCategories);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolbar(toolbar, tvToolbarTitle, getString(R.string.title_main_activity), false);

        initRecyclerView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(manager);

        adapter = new RvAdapter(this);
        recyclerView.setAdapter(adapter);

        FirebaseController.getInstance().getMainCategories(valueEventListener);
    }

    @Override
    public void onItemClickListener(MainCategory item) {
        Intent intent = new Intent(this, SubcategoriesActivity.class);
        intent.putExtra(Keys.KEY_CATEGORY, item);
        startActivity(intent);
    }
}
