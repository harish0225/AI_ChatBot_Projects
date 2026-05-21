//package com.QuantamParadigm.ImageGenerator.DTO;
//
//
//
//public class ImageResponse {
//
//    private String status;
//    private String image;
//    private String message;
//
//    // Constructor for success
//    public ImageResponse(String status, String image, String message) {
//        this.status = status;
//        this.image = image;
//        this.message = message;
//    }
//
//    public String getStatus() { return status; }
//    public void setStatus(String status) { this.status = status; }
//
//    public String getImage() { return image; }
//    public void setImage(String image) { this.image = image; }
//
//    public String getMessage() { return message; }
//    public void setMessage(String message) { this.message = message; }
//}


package com.Quantam.Text_Generation_By_AI.DTO;

public class ImageGenerationResponse {

    private String status;
    private String base64Image;
    private String message;

    public ImageGenerationResponse() {
    }

    public ImageGenerationResponse(String status, String base64Image, String message) {
        this.status = status;
        this.base64Image = base64Image;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}