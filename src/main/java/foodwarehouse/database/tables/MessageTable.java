package foodwarehouse.database.tables;

final public class MessageTable {
    static final public String NAME = "MESSAGE";

    static final public class Columns {
        static final public String MESSAGE_ID = "MESSAGE_ID";
        static final public String SENDER_ID = "SENDER_ID";
        static final public String RECEIVER_ID = "RECEIVER_ID";
        static final public String CONTENT = "CONTENT";
        static final public String STATE = "MESSAGE_STATE";
        static final public String SEND_DATE = "SEND_DATE";
        static final public String READ_DATE = "READ_DATE";
    }

    static final public class Procedures {
        static final public String INSERT = "CALL `INSERT_MESSAGE`(?,?,?,?)";

        static final public String UPDATE_CONTENT = "CALL `UPDATE_MESSAGE_CONTENT`(?,?,?)";

        static final public String UPDATE_READ = "CALL `UPDATE_MESSAGE_READ`(?)";

        static final public String DELETE = "CALL `DELETE_MESSAGE`(?)";



        static final public String READ_ALL = "CALL `GET_MESSAGES`()";
        static final public String READ_BY_ID = "CALL `GET_MESSAGE_BY_ID`(?)";
        static final public String READ_EMPLOYEE_ALL = "CALL `GET_EMPLOYEE_MESSAGES`(?)";
        static final public String COUNT_UNREAD_RECEIVED = "CALL `COUNT_UNREAD_RECEIVED_MESSAGES`(?)";
    }
}
