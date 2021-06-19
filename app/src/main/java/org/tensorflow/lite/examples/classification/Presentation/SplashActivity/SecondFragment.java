package org.tensorflow.lite.examples.classification.Presentation.SplashActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.tensorflow.lite.examples.classification.R;

public class SecondFragment extends Fragment {              // @Copyright for 이원중
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {              // @Copyright for 이원중
        return inflater.inflate(R.layout.fragment_second, container, false);
    }
}
