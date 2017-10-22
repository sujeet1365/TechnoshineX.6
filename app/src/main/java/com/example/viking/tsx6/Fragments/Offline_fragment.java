package com.example.viking.tsx6.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.viking.tsx6.Offline_Adapter;
import com.example.viking.tsx6.R;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

/**
 * Created by viking on 21/7/16.
 */
public class Offline_fragment extends android.support.v4.app.Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    int[] myImageList = new int[]{R.drawable.tattoo_icon, R.drawable.brain_icon, R.drawable.cs_icon,
            R.drawable.treasure_icon, R.drawable.gadget_icon, R.drawable.poy_icon, R.drawable.tric_icon, R.drawable.total_icon};
    int[] eventname = new int[]{R.string.tattoo,R.string.bm,R.string.cs,R.string.th,R.string.gg,R.string.p,R.string.tri,R.string.tc};

    int[] myTitleList = new int[]{R.string.Tatto,R.string.brain,R.string.counter,R.string.treasure,R.string.gadget,R.string.poy,R.string.tricology,R.string.total};

    int[] tagline = new int[]{R.string.tattoo_tagline,R.string.brain_tagline,R.string.counter_tagline,R.string.treasure_tagline,R.string.gadget_tagline,R.string.poy_tagline,R.string.trico_tagline,R.string.total_tagline};
    int[] poster = new int[]{R.drawable.tattoo, R.drawable.brain, R.drawable.counter,
            R.drawable.treasure, R.drawable.gg, R.drawable.poy, R.drawable.t, R.drawable.tc};
    public static Offline_fragment newInstance() {
        return new Offline_fragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RecyclerViewMaterialAdapter(new Offline_Adapter(myImageList,eventname,tagline,myTitleList,poster));
        //mAdapter = new RecyclerViewMaterialAdapter(new Offline_Adapter(myImageList),2);
        mRecyclerView.setAdapter(mAdapter);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView);

  /*      mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Dialog dialog = new Dialog(getActivity(), R.style.DialogAnimation);
                dialog.setContentView(R.layout.for_dialog);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.background_image);
                imageView.setImageResource(myImageList[position - 1]);
                TextView textView = (TextView) dialog.findViewById(R.id.detailTitle);

                TextView title = (TextView) dialog.findViewById(R.id.title);

                title.setText(eventname[position - 1]);
                textView.setText(myTitleList[position - 1]);
                imageView.setImageResource(myImageList[position - 1]);

                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Bitmap blurred = MainActivity.blurRenderScript(bitmap, 25);//second parameter is radius
                imageView.setImageBitmap(blurred);

                //dialog.getWindow().setBackgroundDrawableResource(myImageList[position - 1]);
                dialog.getWindow().setFormat(PixelFormat.TRANSLUCENT);
                dialog.show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        }));
        */
    }
}
