
package control;

import java.util.List;
import model.Image;
import view.ImageDisplay;

public class NextCommand implements Command{

    private final List<Image> images;
    private final ImageDisplay imageDisplay;

    public NextCommand(List<Image> images, ImageDisplay imageDisplay) {
        this.images = images;
        this.imageDisplay = imageDisplay;
    }
    
    @Override
    public void execute() {
        imageDisplay.display(next());
    }

    private Image next() {
        int index = images.indexOf(imageDisplay.currentImage());
        return images.get((index+1) % images.size());
    }

}
