package example.test.com.configselect;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

public class MainActivity extends AppCompatActivity {

    private LayoutInflater mInflater;
    private TagFlowLayout mFlow_layout;

    //测试
    private String[] tag = {"参数1", "参数2","参数1", "参数2","参数1",
            "参数2","参数1", "参数2","参数1", "参数2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInflater = LayoutInflater.from(this);

    }

    public void add(View view) {
        final Dialog select_dialog = new Dialog(this, R.style.select_dialog);
        View root = mInflater.inflate(R.layout.select_detail_layout, null);
        CountView countView = (CountView) root.findViewById(R.id.count_view);
        LinearLayout pay = (LinearLayout) root.findViewById(R.id.pay);
        LinearLayout add_car = (LinearLayout) root.findViewById(R.id.add_car);

        mFlow_layout = (TagFlowLayout) root.findViewById(R.id.flow_layout);
        mFlow_layout.setMaxSelectCount(1);
        mFlow_layout.setAdapter(new TagAdapter<String>(tag) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv, mFlow_layout, false);
                tv.setText(s);
                return tv;
            }
        });

        mFlow_layout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(MainActivity.this, tag[position], Toast.LENGTH_SHORT).show();
                //view.setVisibility(View.GONE);
                return true;
            }
        });

        select_dialog.setContentView(root);
        Window dialogWindow = select_dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = select_dialog.getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(params);
        select_dialog.setCanceledOnTouchOutside(true);
        select_dialog.setCancelable(true);
        select_dialog.show();
    }
}
