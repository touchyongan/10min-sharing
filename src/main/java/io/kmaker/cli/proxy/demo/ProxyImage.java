package io.kmaker.cli.proxy.demo;

public class ProxyImage implements Image {

    private Image image;

    public ProxyImage(Image image) {
        this.image = image;
    }

    @Override
    public void render() {
        System.out.println("Validate permission before get image");
        image.render();
    }
}
