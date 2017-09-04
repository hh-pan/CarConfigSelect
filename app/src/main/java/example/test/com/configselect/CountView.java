package example.test.com.configselect;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Pan on 2017/8/30 0030 22:41
 * Desc:
 */

public class CountView extends LinearLayout implements View.OnClickListener {
    private Context mContext;
    private TextView mCount_result;

    private int count = 1;
    private OnClick mClick;

    public CountView(Context context) {
        this(context, null);
    }

    public CountView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.count_view_layout, this, true);
        ImageView add = (ImageView) view.findViewById(R.id.add); //增加
        ImageView reduce = (ImageView) view.findViewById(R.id.reduce); //减少
        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        //计数
        mCount_result = (TextView) view.findViewById(R.id.count_number);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                count++;
                mCount_result.setText(count + "");
                if (mClick!=null) {
                    mClick.onClick(Integer.valueOf(count));
                }
                break;
            case R.id.reduce:
                if (count <= 1) {
                    Toast.makeText(mContext, "最少为1件", Toast.LENGTH_SHORT).show();
                    return;
                }
                count--;
                mCount_result.setText(count + "");
                if (mClick!=null) {
                    mClick.onClick(Integer.valueOf(count));
                }
                break;
            default:
                break;
        }
    }

    /**
     * 设置商品数量
     * @param number
     */
    public void setCountNumber(String number){
        count = Integer.valueOf(number);
        mCount_result.setText(number);
    }

    public interface OnClick {
        void onClick(int count);
    }

    public void setOnClick(OnClick click) {

        mClick = click;
    }
}
