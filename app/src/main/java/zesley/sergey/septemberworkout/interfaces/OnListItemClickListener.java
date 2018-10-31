package zesley.sergey.septemberworkout.interfaces;

public interface OnListItemClickListener {
    void onListItemClickListener(int index);
    void refreshAdapter();
    void refreshOneInList();
    void checkDeletedIndex(int index);
}
