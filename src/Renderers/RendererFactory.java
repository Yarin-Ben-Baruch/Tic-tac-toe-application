package Renderers;


public class RendererFactory {

    public iRenderer buildRenderer(String i_Type) {

        switch(i_Type) {
            case "Console":
                return new ConsoleRenderer();
            case "None":
                return new VoidRenderer();
            case "Gui":
                return new GuiRenderer();
            default:
                return null;
        }
    }

}
