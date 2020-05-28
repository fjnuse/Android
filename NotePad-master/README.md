# 笔记本
## 时间戳
 在noteslist_item布局里添加text2用来显示时间，布局文件是使用线性布局
 
 ```
 <TextView
        android:id="@+id/text2"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:textSize="12dp"
        android:gravity="center_vertical"
        android:paddingLeft="10dip"
        android:singleLine="true"
        android:layout_weight="1"
        android:layout_margin="0dp"
        />
   ```
      
     
  然后在在NodeEditor.java中的updateNode()这个函数添加时间，首先用Date获取当前系统时间，然后用SimpleDateFormat将时间格式化成日月年小时分钟秒，不然显示出来的是一串数字，再将时间转换成String类型赋值给NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE并存入数据库
  
  ```
     Date nowTime = new Date(System.currentTimeMillis());
     SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String retStrFormatNowDate = sdFormatter.format(nowTime);
     values.put(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE, retStrFormatNowDate);
     
  ```
  
  最后在NoteList.java的PROJECTION数组中增加对时间的描述，增加 NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE，并对viewsIDs增加对时间的描述NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE，然后将text2加进viewIDs，控件就可显示出来
  
  ```
  
  private static final String[] PROJECTION = new String[] {
            NotePad.Notes._ID, // 0
            NotePad.Notes.COLUMN_NAME_TITLE, // 1
            NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE,
    };
 String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE ,NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE} ;
 int[] viewIDs = { R.id.text1,R.id.text2 };

  ```

### 效果图
<image src="https://github.com/xiezhenqun/Android/blob/master/NotePad-master/screen/time.png">
 
 ## 搜索功能
 搜索的话是以搜索笔记的标题进行匹配，采用了模糊搜索，使用SearchView控件
 增加一个布局文件listview
 
 ```
 
 <?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    xmlns:android="http://schemas.android.com/apk/res/android">

    <android.support.v7.widget.SearchView
        android:id="@+id/sv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

    </android.support.v7.widget.SearchView>

    <ListView
        android:id="@+id/tv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
</LinearLayout>

```

在NodeList.java中创建一个SeachView函数，基本思想是新创建一个Cursor，在通过SeacrhView搜索的字段，
然后在数据库中进行模糊搜索进行匹配，如果输入的标题有部分被包含记事的标题里就会显示，最后在onCreate()中调用

```
private void SearchView(){
        searchView=findViewById(R.id.sv);
        
        searchView.onActionViewExpanded();
        
        searchView.setQueryHint("搜索笔记");
        
        searchView.setSubmitButtonEnabled(true);
        
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            
            @Override
            public boolean onQueryTextChange(String s) {
                if(!s.equals("")){
                    String selection=NotePad.Notes.COLUMN_NAME_TITLE+" GLOB '*"+s+"*'";
                    updatecursor = getContentResolver().query(
                            getIntent().getData(),            
                            PROJECTION,                      
                            selection,                             
                            null,                             
                            NotePad.Notes.DEFAULT_SORT_ORDER  
                    );
                }
               else {
                    updatecursor = getContentResolver().query(
                            getIntent().getData(),            
                            PROJECTION,                       
                            null,                             
                            null,                             
                            NotePad.Notes.DEFAULT_SORT_ORDER  
                    );
                }
                
                adapter.swapCursor(updatecursor);
               
                return false;
            }
        });
    }

```

### 效果图
<image src="https://github.com/xiezhenqun/Android/blob/master/NotePad-master/screen/SB~V%25XM%7DMHSRZPQIYIZU%5D~9.png">
