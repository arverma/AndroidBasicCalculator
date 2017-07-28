package in.ac.iiitmanipur.calc.db;

import android.provider.BaseColumns;

/**
 * Created by amanranjan on 13-Jul-17.
 */

public class TaskContract {
    public static final String DB_NAME = "in.ac.iiitmanipur.calc.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "tasks";
        public static final String COL_TASK_TITLE = "title";
        public static final String COL_PASS = "password";
    }
}
