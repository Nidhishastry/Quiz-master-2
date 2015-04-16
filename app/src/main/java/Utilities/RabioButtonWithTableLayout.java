package Utilities;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * Created by kumardivyarajat on 12/04/15.
 */
public class RabioButtonWithTableLayout extends TableLayout implements
        View.OnClickListener {

    private RadioButton mBtnCurrentRadio;

    public RabioButtonWithTableLayout(Context context) {
        super(context);
    }

    public RabioButtonWithTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onClick(View v) {
        final RadioButton mBtnRadio = (RadioButton) v;

        // select only one radio button at any given time
        if (mBtnCurrentRadio != null) {
            mBtnCurrentRadio.setChecked(false);
        }
        mBtnRadio.setChecked(true);
        mBtnCurrentRadio = mBtnRadio;
    }

    @Override
    public void addView(View child, int index,
                        android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        setChildrenOnClickListener((TableRow) child);
    }

    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        setChildrenOnClickListener((TableRow) child);
    }
    private void setChildrenOnClickListener(TableRow tr) {
        final int c = tr.getChildCount();
        for (int i = 0; i < c; i++) {
            final View v = tr.getChildAt(i);
            if (v instanceof RadioButton) {
                v.setOnClickListener(this);
            }
        }
    }
}
