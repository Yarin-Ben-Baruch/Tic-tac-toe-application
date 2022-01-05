package Renderers;


public class RendererFactory {

    /**
     * 'buildRenderer' is method that accept the user type of UI.
     * @param typeOfGame
     * @return
     */
    public iRenderer buildRenderer(String typeOfGame) {

        switch(typeOfGame) {
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
