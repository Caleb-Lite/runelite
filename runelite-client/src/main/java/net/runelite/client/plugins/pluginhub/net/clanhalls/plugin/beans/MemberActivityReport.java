package net.runelite.client.plugins.pluginhub.net.clanhalls.plugin.beans;

import lombok.Value;

import java.util.List;

@Value
public class MemberActivityReport {
    List<MemberActivity> members;
}
