package Renderers;


public class RendererFactory {

    /**
     * 'buildRenderer' is method that accept the user type of UI.
     * @param i_Type
     * @return
     */
    public iRenderer buildRenderer(String i_Type) {

        switch(i_Type) {
            case "Console":
                return new ConsoleRenderer();
            case "None":
                return new VoidRenderer();
            case "Gui":
                return new ApplicationGuiRenderer();
            default:
                return null;
        }
    }

}
