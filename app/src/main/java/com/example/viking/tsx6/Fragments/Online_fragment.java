package com.example.viking.tsx6.Fragments;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.viking.tsx6.MainActivity;
import com.example.viking.tsx6.Online_Adapter;
import com.example.viking.tsx6.R;
import com.example.viking.tsx6.RecyclerItemClickListener;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

/**
 * Created by viking on 21/7/16.
 */
public class Online_fragment extends android.support.v4.app.Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    int[] myImageList = new int[]{R.drawable.click_icon, R.drawable.binoculars, R.drawable.inspire_icon, R.drawable.code_icon};
    int[] eventname = new int[]{R.string.just,R.string.net,R.string.inspire,R.string.cw};
    int[] myTitleList = new int[]{R.string.Just_click,R.string.net_hunt,R.string.Inspire_India,R.string.code_war};
    int[] tagline = new int[]{R.string.just_tagline,R.string.net_tagline,R.string.inspire_tagline,R.string.code_tagline};
    int[] poster = new int[]{R.drawable.justclick,R.drawable.net,R.drawable.inspire,R.drawable.code_war};
    int[] back_color = new int[] {R.color.just,R.color.net,R.color.inspire,R.color.code};
    public static Online_fragment newInstance() {
        return new Online_fragment();
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
        mAdapter = new RecyclerViewMaterialAdapter(new Online_Adapter(myImageList,eventname,tagline,myTitleList,poster,back_color));
      //mAdapter = new RecyclerViewMaterialAdapter(new Online_Adapter(myImageList,eventname,tagline,myTitleList),2);
        mRecyclerView.setAdapter(mAdapter);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView);

        Button button = (Button) view.findViewById(R.id.card_button);

      /*  mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                Dialog dialog = new Dialog(getActivity(), R.style.DialogAnimation);
                dialog.setContentView(R.layout.for_dialog);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.background_image);
                imageView.setImageResource(myImageList[position - 1]);
                TextView textView = (TextView) dialog.findViewById(R.id.detailTitle);
                TextView title = (TextView) dialog.findViewById(R.id.title);

                title.setText(eventname[position - 1]);
                //title.setText(setEventname(getString(R.array.Offline_Title)));
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
