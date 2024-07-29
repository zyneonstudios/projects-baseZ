package com.zyneonstudios.projects.base.modules.module;

public class Module {

    private final String name;
    private final String version;
    private final String[] authors;
    private PluginState state = PluginState.DISABLED;

    public Module(String name, String version, String... authors) {
        this.name = name;
        this.version = version;
        this.authors = authors;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void load() {
        state = PluginState.LOADED;
    }

    public void enable() {
        state = PluginState.ENABLED;
    }

    public void disable() {
        state = PluginState.DISABLED;
    }

    public PluginState getState() {
        return state;
    }

    public enum PluginState {
        DISABLED,
        LOADED,
        ENABLED
    }
}