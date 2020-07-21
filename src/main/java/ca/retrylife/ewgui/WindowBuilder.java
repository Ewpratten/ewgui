package ca.retrylife.ewgui;

import ca.retrylife.ewgui.Window.ConfigurationFlags;

public class WindowBuilder {

    private Container content = new EmptyContainer();
    private Container navbar = new EmptyContainer();

    public WindowBuilder(String title){

    }

    public WindowBuilder withNavbar(){
        return this;
    }

    public WindowBuilder withContent(Container content){
        return this;
    }

    public WindowBuilder withConfiguration(ConfigurationFlags ... flags){
        return this;
    }

    public Window build(){
        return null;
    }
    
}