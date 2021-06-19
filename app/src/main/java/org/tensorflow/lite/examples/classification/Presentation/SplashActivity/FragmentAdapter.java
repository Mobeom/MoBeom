package org.tensorflow.lite.examples.classification.Presentation.SplashActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {              // @Copyright for 이원중

    public int mCount;

    public FragmentAdapter(FragmentActivity fa, int count) {              // @Copyright for 이원중
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {              // @Copyright for 이원중
        int index = getRealPosition(position);

        if (index == 0) return new FirstFragment();
        else if(index==1) return new SecondFragment();
        else return new ThirdFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }              // @Copyright for 이원중

    public int getRealPosition(int position) {
        return position % mCount;
    }              // @Copyright for 이원중
}
