package ru.alexsuvorov.paistewiki.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.alexsuvorov.paistewiki.Adapter.ImageSliderAdapter;
import ru.alexsuvorov.paistewiki.Adapter.NewsAdapter;
import ru.alexsuvorov.paistewiki.R;
import ru.alexsuvorov.paistewiki.Utils.NewsLoader;
import ru.alexsuvorov.paistewiki.model.NewsMonth;
import ru.alexsuvorov.paistewiki.model.NewsPost;

public class NewsFragment extends Fragment {

    private List<NewsMonth> monthArray;
    private List<NewsPost> postArray;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.news_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        ImageSliderAdapter sliderAdapter = new ImageSliderAdapter(getContext());
        viewPager.setAdapter(sliderAdapter);

        initializeData();
        RecyclerView recyclerView = view.findViewById(R.id.newsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        NewsAdapter adapter = new NewsAdapter(monthArray); /*this.getContext(), R.layout.news_item, monthArray*/
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initializeData() {
        monthArray = NewsLoader.getMonthList();
        postArray = NewsLoader.getPostsList();
        /*monthArray.add(new NewsMonth("Июль", "WHO IS...JODY GIACHELLO", null));
        //monthArray.get(1).setMonthPosts(monthArray).add(new NewsMonth("Июль", "опачки!", null));
        monthArray.add(new NewsMonth("Июнь", "THE NEW PST X - Craig Blundell", null));
        monthArray.add(new NewsMonth("Май", "Барабанщик забил гол", null));
        monthArray.add(new NewsMonth("Апрель", "йцуйцуйц забил гол", null));
        monthArray.add(new NewsMonth("Март", "Барабанщик забил гол", null));
        monthArray.add(new NewsMonth("Февраль", "йцуйуц забил гол", null));*/
    }
}