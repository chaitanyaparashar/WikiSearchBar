package com.example.tricogassignment.views.pagelist;

import com.example.tricogassignment.api.APIService;
import com.example.tricogassignment.api.ServiceFactory;
import com.example.tricogassignment.api.model.JsonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mayur on 6/25/2017.
 */

class PageListPresenter implements PageListContract.Presenter {

    private PageListContract.View pageListView;

    PageListPresenter(PageListContract.View pageListView) {
        this.pageListView = pageListView;
    }

    @Override
    public void getTracks(String url, String term) {
        APIService service = ServiceFactory.getInstance();
        Call<JsonResponse> trackModelCall = service.getTracks(url);
        trackModelCall.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                JsonResponse result = response.body();
                if (result != null && result.getQuery() != null) {
                    pageListView.displayTracks(result.getQuery().getPages());
                } else {
                    pageListView.displayMessage("No data found, Try again.");
                }
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                pageListView.displayMessage("No data found, Try again.");
            }
        });
    }
}