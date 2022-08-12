package com.slatinin.formverification;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.slatinin.formverification.databinding.RvItemBinding;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvHolder> {
    private final String[] array;
    private final ValidationManager validationManager;

    public RvAdapter(String[] array, ValidationManager validationManager) {
        this.array = array;
        this.validationManager = validationManager;
    }

    @NonNull
    @Override
    public RvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemBinding binding = RvItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RvHolder(binding, validationManager);
    }

    @Override
    public void onBindViewHolder(@NonNull RvHolder holder, int position) {
        holder.bind(array[position], position);
    }

    @Override
    public int getItemCount() {
        return array.length;
    }

    static class RvHolder extends RecyclerView.ViewHolder {
        private final RvItemBinding binding;
        private final ValidationManager validationManager;

        public RvHolder(@NonNull RvItemBinding binding, ValidationManager validationManager) {
            super(binding.getRoot());
            this.binding = binding;
            this.validationManager = validationManager;
        }

        public void bind(String message, int position) {
            binding.rvLabel.setText(message);
            validationManager.addFieldToBeValidated(new EmptyVerification(binding.rvText), 100 + position);
        }
    }

}
