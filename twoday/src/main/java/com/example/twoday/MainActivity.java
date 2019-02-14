package com.example.twoday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.twoday.bean.Person;

import java.lang.reflect.Field;

/*
* JsonArray array = new JsonParser().parse(json).getAsJsonArray();array是数组集合
Gson gson = new Gson();Gson解析
Class<T> cls = null;判断Class为空
Class clz = this.getClass();
ParameterizedType type = (ParameterizedType) clz.getGenericSuperclass();参数化的类型
Type[] types = type.getActualTypeArguments();真实的参数类型
cls = (Class<T>) types[0];
for(final JsonElement elem : array){for循环
        mList.add((T) gson.fromJson(elem, cls));赋值
}
* */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Field_Btn;
    private Button Method_Btn;
    private Button Concur_Btn;
    private Person person;
    private Class<? extends Person> aClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        person = new Person();
        aClass = person.getClass();
    }

    private void initView() {
        Field_Btn = (Button) findViewById(R.id.Field_Btn);
        Method_Btn = (Button) findViewById(R.id.Method_Btn);
        Concur_Btn = (Button) findViewById(R.id.Concur_Btn);

        Field_Btn.setOnClickListener(this);
        Method_Btn.setOnClickListener(this);
        Concur_Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Field_Btn:
                getField();
                break;
            case R.id.Method_Btn:

                break;
            case R.id.Concur_Btn:

                break;
        }
    }

    private void getField() {
        //得到该类的所有成员变量
        Field[] fields = aClass.getDeclaredFields();
        //所有的public修饰的变量
        // aClass.getFields();
        for (Field f : fields) {
            Log.e("变量", f.getName());
        }

        try {
            Field field = aClass.getDeclaredField("lick");
            //java语法规定 私有化的东西只能在本类访问  反射提供一个方法加暴力访问对象
            field.setAccessible(true);
            Object o = field.get(person);
            if (o instanceof String) {
                Log.e("通过反射获取属性的值", o.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
