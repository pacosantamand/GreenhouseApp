package edu.itsco.proyectotesis;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLecturas extends Fragment {





    private AppBarLayout appBar;
    private TabLayout pestanas;

    public FragmentLecturas() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_paginado, container, false);
        if(savedInstanceState==null){
            insertarTabs(container);
            ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
            poblarViewPager(pager);
            pestanas.setupWithViewPager(pager);
        }
        return view;
    }

    public void insertarTabs(ViewGroup container){
        View root = (View) container.getParent();
        appBar = (AppBarLayout) root.findViewById(R.id.appbar);
        pestanas = new TabLayout(getActivity());
        pestanas.setTabTextColors(Color.parseColor("#FFFFFF"),Color.parseColor("#FFFFFF"));
        appBar.addView(pestanas);
    }

    private void poblarViewPager(ViewPager viewPager) {
        AdaptadorSecciones adapter = new AdaptadorSecciones(getFragmentManager());
        String[] titles = getTitleVariables();
        for(int i=0;i<titles.length;i++){
           // adapter.addFragment(LecturaFragment.newInstance(i,titles[i]),titles[i]);
            adapter.addFragment(VariableFragment.newInstance(i,titles[i]),titles[i]);
        }
        viewPager.setAdapter(adapter);
    }

    public String[] getTitleVariables(){
        return new String[]{"Temperatura","Humedad"};
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        if(pestanas!=null)
            appBar.removeView(pestanas);
    }

    public class AdaptadorSecciones extends FragmentStatePagerAdapter {
        private final List<Fragment> fragmentos = new ArrayList<>();
        private final List<String> titulosFragmentos = new ArrayList<>();

        public AdaptadorSecciones(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            fragmentos.add(fragment);
            titulosFragmentos.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulosFragmentos.get(position);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_lecturas, menu);
    }
}
