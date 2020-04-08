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
    private Context context;
    private LinkedList taskQueue;
    private ViewHolder viewHolder;

    public TaskQueueAdapter(Context context, Queue taskQueue) {
        this.context = context;
        this.taskQueue = (LinkedList)taskQueue;
    }

    public void setTaskQueue(Queue taskQueue) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.task_queue_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // View에 Data 세팅
        viewHolder.taskQueue_io.setText(((Task)taskQueue.get(position)).getIo()+"");
        viewHolder.taskQueue_qty.setText(((Task)taskQueue.get(position)).getQty()+"");
        viewHolder.taskQueue_locX.setText(((Task)taskQueue.get(position)).getLocX()+"");
        viewHolder.taskQueue_locY.setText(((Task)taskQueue.get(position)).getLocY()+"");
        return convertView;
    }



    public class ViewHolder {
        public TextView taskQueue_io;
        public TextView taskQueue_qty;
        public TextView taskQueue_locX;
        public TextView taskQueue_locY;

        public ViewHolder(View convertView) {
            System.out.println("New ViewHolder made");
            taskQueue_io = (TextView)convertView.findViewById(R.id.taskQueue_io);
            taskQueue_qty = (TextView)convertView.findViewById(R.id.taskQueue_qty);
            taskQueue_locX = (TextView)convertView.findViewById(R.id.taskQueue_locX);
            taskQueue_locY = (TextView)convertView.findViewById(R.id.taskQueue_locY);
        }
    }
}
