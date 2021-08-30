package com.example.android.roomwordssample;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;


public class AlimentListAdapter extends ListAdapter<Aliment, AlimentViewHolder> {

    public AlimentListAdapter(@NonNull DiffUtil.ItemCallback<Aliment> diffCallback) {
        super(diffCallback);
    }

    @Override
    public AlimentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AlimentViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(AlimentViewHolder holder, int position) {
        Aliment current = getItem(position);
        holder.bind(current.getAlimentName());
    }

    static class AlimentDiff extends DiffUtil.ItemCallback<Aliment> {

        @Override
        public boolean areItemsTheSame(@NonNull Aliment oldItem, @NonNull Aliment newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Aliment oldItem, @NonNull Aliment newItem) {
            return oldItem.getAlimentName().equals(newItem.getAlimentName());
        }
    }
}
