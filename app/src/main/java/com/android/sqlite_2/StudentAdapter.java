package com.android.sqlite_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {

    // field

    // 이 context를 가지고 어디에 띄울건지 설정할거야!
    private Context mContext = null;

    private int layout = 0;

    // bean을 arraylist로 묶어쓸거니까! general 사이에는 bean이름인 Weather 넣어줌.
    private ArrayList<StudentBean> data = null;

    // Adapter를 쓰기위해서는 layoutInflater가 필요하다.
    private LayoutInflater inflater = null;


    // Constructor
    // inflater는 생성자를 생성할 필요 없음!
    // 다른곳에서 부를 때는 아래 3개만 필요하니까. but inflater가 있어야 Adapter 사용 가능!

    public StudentAdapter(Context mContext, int layout, ArrayList<StudentBean> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        // MainActivity에 ListView를 쌓기 위해서는 List가 어떤 type인지 알아야 함.
        // 선언할 때는 inflater를 사용하지 않고, 아래 처럼 추가해주는게 보통.
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    // Override는 상속받아서 생긴 것.
    @Override
    public int getCount() {
        // Count를 해주는 것.
        // data는 arrayList니까 size사용.
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // 사용자가 클릭했을 때, 몇 번째냐 인지 확인
        // arrayList의 몇번째의 "Day"를 가져와라!
        return data.get(position).getStudentid();
    }

    @Override
    public long getItemId(int position) {
        //
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convertView가 null이면
        if (convertView == null) {

            // this layout에서, ViewGroup을, listView를 붙이지 않는다!
            convertView = inflater.inflate(this.layout, parent, false);

        } // convertView가 null이 아니라면 아래가 실행.
        TextView tv_studentid = convertView.findViewById(R.id.tv_studentid);
        TextView tv_studentname = convertView.findViewById(R.id.tv_studentname);
        TextView tv_studentmajor = convertView.findViewById(R.id.tv_studentmajor);
        TextView tv_studenttel = convertView.findViewById(R.id.tv_studenttel);

        // textView와 ImageView 설정.
        tv_studentid.setText(Integer.toString(data.get(position).getStudentid()) + " ");
        tv_studentname.setText(data.get(position).getStudentname() + " ");
        tv_studentmajor.setText(data.get(position).getStudentmajor() + " ");
        tv_studenttel.setText(data.get(position).getStudenttelno() + " ");


        // 만약 position이 홀수라면
        if(position % 2 == 1){

            convertView.setBackgroundColor(0x50CCFFFF);
        } else {

            convertView.setBackgroundColor(0x50CCCCFF);
        }
        return convertView;
    }
}
