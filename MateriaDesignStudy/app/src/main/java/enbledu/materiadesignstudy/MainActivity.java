package enbledu.materiadesignstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
        toolbar.setTitle("Materia design study");//设置主标题
        //toolbar.setSubtitle("Subtitle");//设置子标题

        toolbar.inflateMenu(R.menu.menu_main);//设置右上角的填充菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.menu1) {
                    Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.menu2) {
                    Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.menu3) {
                    Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();

                }
                    return true;
            }
        });






        }
    }



