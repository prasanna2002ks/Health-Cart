package com.example.healthcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.*;

public class DoctorDetailActivity extends AppCompatActivity {
    private String[][] doctor_details1 =
            {
                    {"Doctor Name : Abc", "Hospital Address : Madurai", "Exp : 5yrs", "Mobile No : 7218456945", "600"},
                    {"Doctor Name : Cde", "Hospital Address : Madurai", "Exp : 10yrs", "Mobile No : 8523691475", "800"},
                    {"Doctor Name : Fgh", "Hospital Address : Madurai", "Exp : 15yrs", "Mobile No : 7536984125", "1000"},
                    {"Doctor Name : Ijk", "Hospital Address : Madurai", "Exp : 20yrs", "Mobile No : 9587412364", "1600"},
                    {"Doctor Name : Lmn", "Hospital Address : Madurai", "Exp : 25yrs", "Mobile No : 6385479125", "2600"}
            };
    private String[][] doctor_details2 =
            {
                    {"Doctor Name : Opq", "Hospital Address : Madurai", "Exp : 4yrs", "Mobile No : 7218456945", "500"},
                    {"Doctor Name : Rst", "Hospital Address : Madurai", "Exp : 8yrs", "Mobile No : 8523691475", "700"},
                    {"Doctor Name : Uvw", "Hospital Address : Madurai", "Exp : 12yrs", "Mobile No : 7536984125", "900"},
                    {"Doctor Name : Xyz", "Hospital Address : Madurai", "Exp : 16yrs", "Mobile No : 9587412364", "1500"},
                    {"Doctor Name : Abcd", "Hospital Address : Madurai", "Exp : 19yrs", "Mobile No : 6385479125", "2500"}
            };
    private String[][] doctor_details3 =
            {
                    {"Doctor Name : Efgh", "Hospital Address : Madurai", "Exp : 3yrs", "Mobile No : 7218456945", "400"},
                    {"Doctor Name : Ijkl", "Hospital Address : Madurai", "Exp : 2yrs", "Mobile No : 8523691475", "650"},
                    {"Doctor Name : Mnop", "Hospital Address : Madurai", "Exp : 1yrs", "Mobile No : 7536984125", "850"},
                    {"Doctor Name : Qrst", "Hospital Address : Madurai", "Exp : 17yrs", "Mobile No : 9587412364", "1400"},
                    {"Doctor Name : Uvwx", "Hospital Address : Madurai", "Exp : 21yrs", "Mobile No : 6385479125", "2400"}
            };
    private String[][] doctor_details4 =
            {
                    {"Doctor Name : Yzab", "Hospital Address : Madurai", "Exp : 5yrs", "Mobile No : 7218456945", "100"},
                    {"Doctor Name : Cdef", "Hospital Address : Madurai", "Exp : 10yrs", "Mobile No : 8523691475", "200"},
                    {"Doctor Name : Ghij", "Hospital Address : Madurai", "Exp : 15yrs", "Mobile No : 7536984125", "250"},
                    {"Doctor Name : Klmn", "Hospital Address : Madurai", "Exp : 20yrs", "Mobile No : 9587412364", "1600"},
                    {"Doctor Name : Opqr", "Hospital Address : Madurai", "Exp : 25yrs", "Mobile No : 6385479125", "2600"}
            };
    private String[][] doctor_details5 =
            {
                    {"Doctor Name : Stuv", "Hospital Address : Madurai", "Exp : 5yrs", "Mobile No : 7218456945", "600"},
                    {"Doctor Name : Wxyz", "Hospital Address : Madurai", "Exp : 10yrs", "Mobile No : 8523691475", "800"},
                    {"Doctor Name : Avgg", "Hospital Address : Madurai", "Exp : 15yrs", "Mobile No : 7536984125", "1000"},
                    {"Doctor Name : nbhb", "Hospital Address : Madurai", "Exp : 20yrs", "Mobile No : 9587412364", "1600"},
                    {"Doctor Name : vghv", "Hospital Address : Madurai", "Exp : 25yrs", "Mobile No : 6385479125", "2600"}
            };
    TextView tv;
    Button btn;
    String[][] doctor_details = new String[][]{};
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        tv = findViewById(R.id.textviewODTitle);
        btn = findViewById(R.id.buttonDDBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Physician")==0)
            doctor_details = doctor_details1;
        else
            if (title.compareTo("Dietitian")==0)
            doctor_details = doctor_details2;
        else
            if (title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;
        else
            if (title.compareTo("Surgeon")==0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;

            btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailActivity.this,FindDoctorActivity.class));
            }
        });
        list = new ArrayList();
        for(int i=0;i<doctor_details.length;i++) {
            item = new HashMap<String,String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons Fees:"+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}