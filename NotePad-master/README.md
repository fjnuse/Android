# 笔记本
## 时间戳
 在noteslist_item布局里添加text2用来显示时间
 
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
      
     
  然后在在NodeEditor.java中的updateNode()这个函数修改时间，并将时间格式化存入数据库
  
  ```
     Date nowTime = new Date(System.currentTimeMillis());
     SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String retStrFormatNowDate = sdFormatter.format(nowTime);
     values.put(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE, retStrFormatNowDate);
     
  ```
  
  最后在NoteList.java的PROJECTION数组中增加对时间的描述，并在SimpleCursorAdapter中的参数
  viewsIDs和dataColumns增加对时间的描述，就可显示出来
  
  ```
  
  private static final String[] PROJECTION = new String[] {
            NotePad.Notes._ID, // 0
            NotePad.Notes.COLUMN_NAME_TITLE, // 1
            NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE,
    };
private String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE ,NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE} ;
private int[] viewIDs = { R.id.text1,R.id.text2 };

  ```
