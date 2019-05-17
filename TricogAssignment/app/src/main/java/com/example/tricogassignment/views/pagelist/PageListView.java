package com.example.tricogassignment.views.pagelist;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.tricogassignment.R;
import com.example.tricogassignment.api.model.PagesItem;

import java.util.ArrayList;
import java.util.List;

public class PageListView extends AppCompatActivity implements PageListContract.View {

    Context context;
    LinearLayout main;
    TextView txtNoPages;
    ShimmerRecyclerView listPages;

    private List<PagesItem> dataPages = new ArrayList<>();
    private PageAdapter adapter;

    PageListPresenter presenter;

    public PageListView() {
        presenter = new PageListPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_list);

        context = PageListView.this;

        main = (LinearLayout) findViewById(R.id.page_list_main);
        txtNoPages = (TextView) findViewById(R.id.txtNoPage);
        listPages = (ShimmerRecyclerView) findViewById(R.id.listPages);

        adapter = new PageAdapter(context, dataPages);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listPages.setLayoutManager(mLayoutManager);
        listPages.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        listPages.setItemAnimator(new DefaultItemAnimator());
        listPages.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search for Pages");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    public void search(final String strTerm) {
        txtNoPages.setVisibility(View.GONE);
        listPages.setVisibility(View.VISIBLE);

        dataPages.clear();
        adapter.notifyDataSetChanged();

        setLoadingIndicator(true);
        listPages.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getTracks("api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpssearch="+strTerm+"&gpslimit=10",strTerm);
            }
        }, 2000);
    }

    @Override
    public void displayTracks(List<PagesItem> dataTracks) {
        setLoadingIndicator(false);
        this.dataPages.clear();
        this.dataPages.addAll(dataTracks);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displayMessage(String message) {
        setLoadingIndicator(false);
        Snackbar.make(main, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setLoadingIndicator(boolean isLoading) {
        if (isLoading) {
            listPages.showShimmerAdapter();
        } else {
            listPages.hideShimmerAdapter();
        }
    }
}