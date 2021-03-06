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
 * File: Chapter.java
 *
 * Created by Vladimir Yakushev at 8/2017
 * E-mail: ru.phoenix@gmail.com
 * WWW: http://www.scripturesoftware.org
 */

package com.BibleQuote.domain.entity;

import com.BibleQuote.domain.textFormatters.ITextFormatter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Chapter {

    private Integer number;
    private String text;
    private TreeMap<Integer, Verse> verses = new TreeMap<>();

    public Chapter(Integer number, ArrayList<Verse> verseList) {
        this.number = number;
        Integer verseNumber = 1;
        for (Verse verse : verseList) {
            verses.put(verseNumber++, verse);
        }
    }

    public Integer getNumber() {
        return number;
    }

    public String getText() {
        if (text == null && !verses.isEmpty()) {
            StringBuilder buffer = new StringBuilder();
            for (Map.Entry<Integer, Verse> entry : verses.entrySet()) {
                buffer.append(entry.getValue().getText());
            }
            text = buffer.toString();
        }
        return text;
    }

    public ArrayList<Verse> getVerseList() {
        return new ArrayList<>(verses.values());
    }

    public String getText(int fromVerse, int toVerse, ITextFormatter formatter) {
        StringBuilder buffer = new StringBuilder();
        for (int verseNumber = fromVerse; verseNumber <= toVerse; verseNumber++) {
            Verse ver = verses.get(verseNumber);
            if (ver != null) {
                buffer.append(formatter.format(ver.getText()));
            }
        }
        return buffer.toString();
    }

    public LinkedHashMap<Integer, String> getVerses(TreeSet<Integer> verses) {
        LinkedHashMap<Integer, String> result = new LinkedHashMap<>();
        ArrayList<Verse> versesList = getVerseList();
        int verseListSize = versesList.size();
        for (Integer verse : verses) {
            int verseIndex = verse - 1;
            if (verseIndex > verseListSize) {
                break;
            }
            result.put(verse, versesList.get(verseIndex).getText());
        }

        return result;
    }
}
