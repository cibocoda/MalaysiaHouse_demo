package com.fiveteam.malaysiahouse2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private View rootView;
    private String areaListitems[], trafficListitems[], optionsListitems[];
    private ListView areaListView, trafficListView, optionsListView;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search, container, false);

        ImageView larrow = (ImageView) rootView.findViewById(R.id.IV_larrow);
        larrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).initFirstPage();
            }
        });

        initList();
        return rootView;
    }

    public void initList(){
        areaListitems=getResources().getStringArray(R.array.search_area);
        trafficListitems=getResources().getStringArray(R.array.search_area);
        optionsListitems=getResources().getStringArray(R.array.search_options);

        areaListView = (ListView) rootView.findViewById(R.id.LV_search_area);
        trafficListView = (ListView) rootView.findViewById(R.id.LV_search_traffic);
        optionsListView = (ListView) rootView.findViewById(R.id.LV_search_options);

        areaListView.setAdapter(new searchListAdapter(areaListitems));
        trafficListView.setAdapter(new searchListAdapter(trafficListitems));
        optionsListView.setAdapter(new searchListAdapter(optionsListitems));
        setListViewHeightBasedOnChildren(areaListView);
        setListViewHeightBasedOnChildren(trafficListView);
        setListViewHeightBasedOnChildren(optionsListView);
    }

    private class searchListAdapter extends BaseAdapter {
        String[] mlistItems;

        public searchListAdapter(String[] listItems){
            mlistItems = listItems;
        }

        @Override
        public int getCount() {
            return mlistItems.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            Holder holder;
            if(v == null){
                v = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_search, null);
                holder = new Holder();
                holder.text = (TextView) v.findViewById(R.id.TV_listItem);

                v.setTag(holder);
            } else{
                holder = (Holder) v.getTag();
            }

            holder.text.setText(mlistItems[position]);
            return v;
        }
        class Holder{
            TextView text;
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
