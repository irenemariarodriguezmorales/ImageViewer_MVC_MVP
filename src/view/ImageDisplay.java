package view;

import model.Image;

public interface ImageDisplay {
    public void display (Image image);
    public Image currentImage();
    void on(Shift shift);

    interface Shift{
        Image left();
        Image right();
    }
}