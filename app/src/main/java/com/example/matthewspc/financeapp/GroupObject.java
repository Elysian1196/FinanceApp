package com.example.matthewspc.financeapp;

/**
 * Created by Christine on 12/3/2017.
 */

public class GroupObject {
    private String groupId;
    private String groupName;

    public GroupObject() {

    }

    public GroupObject(String groupId, String groupName) {
        this.groupId=groupId;
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }
}
