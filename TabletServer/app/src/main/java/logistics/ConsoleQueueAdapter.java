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

public class ConsoleQueueAdapter extends BaseAdapter {
    private Context context;
    private LinkedList consoleQueue;
    private ViewHolder viewHolder;

    public ConsoleQueueAdapter(Context context, Queue consoleQueue) {
        this.context = context;
        this.consoleQueue = (LinkedList) consoleQueue;
    }



    @Override

    public int getCount() {
        return consoleQueue.size();
    }

    @Override
    public Object getItem(int position) {
        return consoleQueue.get(position);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // ViewHoldr 패턴
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.console_queue_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // View에 Data 세팅
        viewHolder.consoleQueue_logMessage.setText((consoleQueue.get(position)).toString());
        return convertView;
    }



    public class ViewHolder {
        private TextView consoleQueue_logMessage;

        public ViewHolder(View convertView) {
            consoleQueue_logMessage = (TextView)convertView.findViewById(R.id.consoleQueue_logMessage);
        }
    }
}
