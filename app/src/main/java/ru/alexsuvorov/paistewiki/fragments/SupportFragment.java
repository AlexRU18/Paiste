package ru.alexsuvorov.paistewiki.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.alexsuvorov.paistewiki.R;
import ru.alexsuvorov.paistewiki.adapter.SupportAdapter;
import ru.alexsuvorov.paistewiki.db.AppDatabase;
import ru.alexsuvorov.paistewiki.db.dao.SupportDao;
import ru.alexsuvorov.paistewiki.model.SupportModel;
import ru.alexsuvorov.paistewiki.tools.AppPreferences;

public class SupportFragment extends Fragment {

    RecyclerView supportView;
    AppPreferences appPreferences;
    List<SupportModel> supportList = new ArrayList<>();
    SupportAdapter supportAdapter;
    SupportDao supportDao;
    AppDatabase db;
    Context context;

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        appPreferences = new AppPreferences(this.getContext());
        Locale locale = new Locale(appPreferences.getText("choosed_lang"));
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
    }*/

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_support, container, false);
        context = getContext();

        supportView = view.findViewById(R.id.supportList);
        supportView.setNestedScrollingEnabled(false);
        supportView.setHasFixedSize(false);
        supportView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        db = AppDatabase.getDatabase(context);
        supportDao = db.supportDao();
        supportList = supportDao.getSupportList();
        supportAdapter = new SupportAdapter(supportList,getContext());

        supportView.setAdapter(supportAdapter);
        supportAdapter.notifyDataSetChanged();
        return view;
    }
}