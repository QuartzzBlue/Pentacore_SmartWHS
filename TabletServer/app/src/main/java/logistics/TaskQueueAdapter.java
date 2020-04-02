package logistics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pentacore.tabletserver.R;

import java.util.LinkedList;
import java.util.Queue;

import msg.Task;

public class TaskQueueAdapter extends BaseAdapter {
    private Context mContext;
    private LinkedList taskQueue;
    private ViewHolder mViewHolder;

    public TaskQueueAdapter(Context mContext, Queue taskQueue) {
        this.mContext = mContext;
        this.taskQueue = (LinkedList)taskQueue;
    }



    @Override

    public int getCount() {
        return taskQueue.size();
    }

    @Override
    public Object getItem(int position) {
        return taskQueue.get(position);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // ViewHoldr 패턴
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.task_queue_item, parent, false);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        // View에 Data 세팅
        mViewHolder.taskQueue_io.setText(((Task)taskQueue.get(position)).getIo());
        mViewHolder.taskQueue_qty.setText(((Task)taskQueue.get(position)).getQty());
        mViewHolder.taskQueue_locX.setText(((Task)taskQueue.get(position)).getLocX());
        mViewHolder.taskQueue_locY.setText(((Task)taskQueue.get(position)).getLocY());
        return convertView;
    }



    public class ViewHolder {
        private TextView taskQueue_io;
        private TextView taskQueue_qty;
        private TextView taskQueue_locX;
        private TextView taskQueue_locY;

        public ViewHolder(View convertView) {
            taskQueue_io = (TextView)convertView.findViewById(R.id.taskQueue_io);
            taskQueue_qty = (TextView)convertView.findViewById(R.id.taskQueue_qty);
            taskQueue_locX = (TextView)convertView.findViewById(R.id.taskQueue_locX);
            taskQueue_locY = (TextView)convertView.findViewById(R.id.taskQueue_locY);
        }
    }
}
