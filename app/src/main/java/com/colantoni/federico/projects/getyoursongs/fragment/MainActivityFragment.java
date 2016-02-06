package com.colantoni.federico.projects.getyoursongs.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.colantoni.federico.projects.getyoursongs.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivityFragment extends Fragment implements View.OnClickListener {

    //    @Bind(R.id.lyricList)
    //    RecyclerView lyricList;

    @Bind(R.id.loginSpotify)
    Button loginSpotify;

    //    LyricListAdapter lyricListAdapter;

    private MainFragmentListener listener;

    public MainActivityFragment() {

    }

    @Override
    public void onAttach(Context context) {

        try {

            listener = (MainFragmentListener) context;
        }
        catch (Exception e) {

            try {

                throw new Exception(context.toString() + " must implement " + MainFragmentListener.class.getSimpleName() + " interface");
            }
            catch (Exception e1) {

                e1.printStackTrace();
            }
        }

        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {

        super.onStart();

        //        lyricList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onResume() {

        super.onResume();

        loginSpotify.setOnClickListener(this);

        //        if (!BuildConfig.DEBUG) {
        //
        //            ChartLyricsService.changeLoggingInterceptorLevel(Level.NONE);
        //        }
        //
        //        ChartLyricsAPI chartLyricsAPI = ChartLyricsService.createRetrofitService(ChartLyricsAPI.class);
        //
        //        SearchLyricTextRequestEnvelope requestEnvelope = new SearchLyricTextRequestEnvelope(new RequestBody(new SearchLyricText("Once upon a time")));
        //
        //        chartLyricsAPI.searchLyricText(requestEnvelope).enqueue(new Callback<SearchLyricTextResponseEnvelope>() {
        //
        //            @Override
        //            public void onResponse(Response<SearchLyricTextResponseEnvelope> response) {
        //
        //                if (response.isSuccess()) {
        //
        //                    final List<SearchLyricResult> searchLyricResultItemList = response.body().getResponseBody().getSearchLyricTextResponse().getSearchLyricTextResult().getSearchLyricResult();
        //
        //                    Iterator<SearchLyricResult> iterator = searchLyricResultItemList.iterator();
        //
        //                    while (iterator.hasNext()) {
        //
        //                        final SearchLyricResult next = iterator.next();
        //
        //                        if (next.getLyricId() == 0 && next.getLyricChecksum() == null) {
        //
        //                            iterator.remove();
        //                        }
        //                    }
        //
        //                    lyricListAdapter = new LyricListAdapter(searchLyricResultItemList);
        //
        //                    lyricList.setAdapter(lyricListAdapter);
        //                }
        //            }
        //
        //            @Override
        //            public void onFailure(Throwable t) {
        //
        //            }
        //        });
    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        switch (viewId) {

            case R.id.loginSpotify: {

                listener.loginWithSpotifyButtonClick();
                return;
            }

            default:
        }
    }

    public void changeButtonLabel(@IdRes int resId, String newLabel) {

        switch (resId) {

            case R.id.loginSpotify: {

                loginSpotify.setText(newLabel);

                return;
            }

            default:
        }
    }

    public interface MainFragmentListener {

        void showSnackBar(boolean isRetrofit2Enabled);

        void loginWithSpotifyButtonClick();
    }
}
