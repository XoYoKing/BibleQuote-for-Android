/*
 * Copyright (C) 2011 Scripture Software
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Project: BibleQuote-for-Android
 * File: TagWithCount.java
 *
 * Created by Vladimir Yakushev at 11/2017
 * E-mail: ru.phoenix@gmail.com
 * WWW: http://www.scripturesoftware.org
 */

package com.BibleQuote.domain.entity;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AutoValue
public abstract class TagWithCount {

    public abstract Tag tag();

    public abstract String count();

    public static TagWithCount create(Tag tag, String count) {
        return new AutoValue_TagWithCount(tag, count);
    }

    public static List<TagWithCount> create(Map<Tag, String> tags) {
        List<TagWithCount> result = new ArrayList<>();
        for (Map.Entry<Tag, String> entry : tags.entrySet()) {
            result.add(create(entry.getKey(), entry.getValue()));
        }
        return result;
    }
}
