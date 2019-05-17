package com.example.tricogassignment.views.pagelist;


import com.example.tricogassignment.api.model.PagesItem;

import java.util.List;

/**
 * Created by Mayur on 6/25/2017.
 */

class PageListContract {

    interface View {
        void displayMessage(String message);

        void setLoadingIndicator(boolean isLoading);

        void displayTracks(List<PagesItem> dataTracks);
    }

    interface Presenter {
        void getTracks(String url, String term);
    }
}