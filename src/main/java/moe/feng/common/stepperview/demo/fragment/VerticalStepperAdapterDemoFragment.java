package moe.feng.common.stepperview.demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import moe.feng.common.stepperview.IStepperAdapter;
import moe.feng.common.stepperview.VerticalStepperItemView;
import moe.feng.common.stepperview.VerticalStepperView;
import moe.feng.common.stepperview.demo.R;

public class VerticalStepperAdapterDemoFragment extends Fragment implements IStepperAdapter {

    private VerticalStepperView mVerticalStepperView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vertical_stepper_adapter, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mVerticalStepperView = view.findViewById(R.id.vertical_stepper_view);
        mVerticalStepperView.setStepperAdapter(this);
    }

    @Override
    public @NonNull
    CharSequence getTitle(int index) {
        return "Step " + index;
    }

    @Override
    public @Nullable
    CharSequence getSummary(int index) {
//		switch (index) {
//			case 0:
//				return Html.fromHtml("Summarized if needed"
//						+ (mVerticalStepperView.getCurrentStep() > index ? "; <b>isDone!</b>" : ""));
//			case 2:
//				return Html.fromHtml("Last step"
//                        + (mVerticalStepperView.getCurrentStep() > index ? "; <b>isDone!</b>" : ""));
//			default:
//				return null;
//		}
        return null;
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public View onCreateCustomView(final int index, Context context, VerticalStepperItemView parent) {
        View inflateView = LayoutInflater.from(context).inflate(R.layout.vertical_stepper_sample_item, parent, false);
        TextView contentView = inflateView.findViewById(R.id.item_content);
        Button nextButton = inflateView.findViewById(R.id.button_next);

        getPosition(contentView, index);
        getBottom(nextButton, index);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mVerticalStepperView.nextStep()) {
                    Snackbar.make(mVerticalStepperView, "Set!", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        Button prevButton = inflateView.findViewById(R.id.button_prev);
        prevButton.setText(index == 0 ? R.string.toggle_animation_button : android.R.string.cancel);
        inflateView.findViewById(R.id.button_prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index != 0) {
                    mVerticalStepperView.prevStep();
                } else {
                    mVerticalStepperView.setAnimationEnabled(!mVerticalStepperView.isAnimationEnabled());
                }
            }
        });
        return inflateView;
    }

    @Override
    public void onShow(int index) {

    }

    @Override
    public void onHide(int index) {

    }

    private void getPosition(TextView textView, int indexs) {

        if (indexs == 0) {
            textView.setText(R.string.content_step_0);
        } else if (indexs == 1) {
            textView.setText(R.string.content_step_1);
        } else if (indexs == 2) {
            textView.setText(R.string.content_step_2);
        }
    }

    private void getBottom(Button button, int indexs) {
        if (indexs == 0) {
            button.setText("Step 0");
        } else if (indexs == 1) {
            button.setText("Step 1");
        } else if (indexs == 2) {
            button.setText("Finish");
        }
    }

}
