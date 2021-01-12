
package control;

import java.util.List;
import model.Image;
import view.ImageDisplay;
import view.ImageLoader;

public class InitCommand implements Command{
    private final ImageLoader imageLoader;
    private final List<Image> images;
    private final ImageDisplay imageDisplay;

    public InitCommand(ImageLoader imageLoader, List<Image> images, ImageDisplay imageDisplay) {
        this.imageLoader = imageLoader;
        this.images = images;
        this.imageDisplay = imageDisplay;
    }
    
    @Override
    public void execute() {
        images.clear();
        images.addAll(imageLoader.load());
        imageDisplay.display(images.get(0));
    }
        

}
