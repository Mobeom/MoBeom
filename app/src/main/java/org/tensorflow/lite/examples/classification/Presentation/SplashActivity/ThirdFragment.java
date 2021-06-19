package org.tensorflow.lite.examples.classification.Presentation.SplashActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.tensorflow.lite.examples.classification.Presentation.MainActivity.MainActivity;
import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.databinding.FragmentThirdBinding;

public class ThirdFragment extends Fragment {              // @Copyright for 이원중

    private FragmentThirdBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {              // @Copyright for 이원중
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third, container, false);
        binding.ml.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {              // @Copyright for 이원중

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {              // @Copyright for 이원중

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {              // @Copyright for 이원중
                Intent intent = new Intent(getContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {              // @Copyright for 이원중

            }
        });
        return binding.getRoot();
    }
}
