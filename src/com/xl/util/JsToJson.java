package com.xl.util;

import com.xl.model.StringEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsToJson {
    private String text;
    private ArrayList<StringEntry> entries;

    public JsToJson(String text) {
        this.text = text;
    }

    public ArrayList<StringEntry> getEntries() {
        return this.entries;
    }

    public String listNames() {
        String[] splits = this.text.split("\\r?\\n");
        entries = new ArrayList<>();
        boolean continueFlag = false;
        String key = null;
        String value = null;
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 1; i < splits.length; i++) { // i = 0 是var str
            String split = splits[i];
            int index = split.indexOf("//");
            if (index != -1) {
                split = split.substring(0, index);
            }
            index = split.indexOf("=");
            if (index == -1 && !continueFlag) {
                continue;
            }
            if (continueFlag) {
                split = split.trim();
                if (split.endsWith(";")) {
                    split = split.substring(0, split.length() - 1);
                }
                if (split.endsWith("]")) {
                    continueFlag = false;
                    value += split;
                    if (hashMap.containsKey(key)) {
                        continue;
                    }
                    entries.add(new StringEntry(key, value));
                    hashMap.put(key, value);
                } else if (split.endsWith(",")) {
                    value += split;
                }
                continue;
            }
            key = split.substring(0, index).trim();
            value = split.substring(index + 1).trim();
            if (value.endsWith(";")) {
                value = value.substring(0, value.length() - 1);
            }
            if (value.endsWith(",")) {
                continueFlag = true;
            } else {
                if (hashMap.containsKey(key)) {
                    continue;
                }
                entries.add(new StringEntry(key, value));
                hashMap.put(key, value);
            }
        }

        StringBuilder buffer = new StringBuilder();
        buffer.append("{\n");
        for (int i = 0 ; i < entries.size(); i++) {
            StringEntry stringEntry = entries.get(i);
            if (i == entries.size() - 1) {
                buffer.append("     \"").append(stringEntry.getName()).append("\" : ").append(exStringToJSON(stringEntry.getValue())).append("\n");
                break;
            }
            buffer.append("     \"").append(stringEntry.getName()).append("\" : ").append(exStringToJSON(stringEntry.getValue())).append(",\n");
//            System.out.println("key = " + stringEntry.getName() + "; value = " + stringEntry.getValue());
        }

        buffer.append("}\n");
        return buffer.toString();
    }

    public static String exStringToJSON(String text) {
        StringBuffer buffer = new StringBuffer();
        int type = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            switch (type) {
                case 0:
                    if (c == '\\') {
                        type = 1;
                    } else if (c == '\n') {
                        buffer.append("\\n");
                    } else if (c == '\r') {
                        buffer.append("\\r");
                    } else if (c == '\t') {
                        buffer.append("\\t");
                    } else {
                        buffer.append(c);
                    }
                    break;
                case 1:
                    if (c == '\'') {
                        buffer.append(c);
                    } else {
                        buffer.append('\\');
                        buffer.append(c);
                    }
                    type = 0;
                    break;
                case 2:
                    if (c != '\"') {
                        buffer.append(c);
                    } else {
                        type = 0;
                    }

                    break;
                case 3:

                    break;
                default:
                    break;
            }

        }

        return buffer.toString();
    }

    public static String exStringToJS(String text) {
        StringBuffer buffer = new StringBuffer();
        int type = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            switch (type) {
                case 0:
                    if (c == '\\') {
                        type = 1;
                    } else if (c == '\"') {
                        buffer.append("\\").append("\"");
                    } else if (c == '\n') {
                        buffer.append("\\n");
                    } else if (c == '\r') {
                        buffer.append("\\r");
                    } else if (c == '\t') {
                        buffer.append("\\t");
                    } else {
                        buffer.append(c);
                    }
                    break;
                case 1:
                    if (c == '\'') {
                        buffer.append(c);
                    } else {
                        buffer.append('\\');
                        buffer.append(c);
                    }
                    type = 0;
                    break;
                case 2:
                    if (c != '\"') {
                        buffer.append(c);
                    } else {
                        type = 0;
                    }

                    break;
                case 3:

                    break;
                default:
                    break;
            }

        }

        return buffer.toString();
    }
}
