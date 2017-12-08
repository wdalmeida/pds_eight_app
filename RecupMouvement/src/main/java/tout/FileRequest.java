package tout;

import java.net.URI;



public class FileRequest {

    private URI path;

    public FileRequest() {
    }
    public FileRequest(URI path) {
        this.path = path;
    }
    public URI getPath() {
        return path;
    }
    public void setPath(URI path) {
        this.path = path;
    }
}
