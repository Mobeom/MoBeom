package org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.tensorflow.lite.examples.classification.R;

import java.util.List;

public class RecyclerViewAdapther extends RecyclerView.Adapter<RecyclerViewAdapther.CustomViewHolder> {     //@copyright for 이동우

    private List<SelectiveClinicJson> list;

    public RecyclerViewAdapther(List<SelectiveClinicJson> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerViewAdapther.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {      //@copyright for 이동우

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent, false);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);

        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapther.CustomViewHolder holder, int position) {     //@copyright for 이동우
        holder.clinicName.setText(list.get(position).getName());
        holder.clinicAddress.setText(list.get(position).getAddress());
        holder.clinicPhone.setText(list.get(position).getPhone());

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);
    }       //@copyright for 이동우

    public class CustomViewHolder extends RecyclerView.ViewHolder {     //@copyright for 이동우

        protected TextView clinicName;
        protected TextView clinicAddress;
        protected TextView clinicPhone;

        public CustomViewHolder(@NonNull View itemView) {       //@copyright for 이동우
            super(itemView);
            this.clinicName = (TextView) itemView.findViewById(R.id.clinicName);
            this.clinicAddress = (TextView) itemView.findViewById(R.id.clinicAddress);
            this.clinicPhone = (TextView) itemView.findViewById(R.id.clinicPhone);
        }
    }
}
