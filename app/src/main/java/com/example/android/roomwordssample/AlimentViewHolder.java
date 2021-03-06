/*
 * Copyright (C) 2020 Google Inc.
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

package com.example.android.roomwordssample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class AlimentViewHolder extends RecyclerView.ViewHolder {
    private final TextView alimentItemView;

    private AlimentViewHolder(View itemView) {
        super(itemView);
        alimentItemView = itemView.findViewById(R.id.AlimentTV);
            }

    public void bind(String aliment) {
        alimentItemView.setText(aliment);
    }

    static AlimentViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aliment_recyclerview_item, parent, false);
        return new AlimentViewHolder(view);
    }
}
