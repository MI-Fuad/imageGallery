package com.sg.imageGallery.model;

public class Admin {
        // here we have the properties, the setters and getters
        // pk_imageID, artist, city, type, price, fileName, artistID
        private int imageId;
        private String artistInitial;
        private String cityValue;
        private String typeValue;
        private int price;
        private String fileNameValue;
        private int artistId;

    @Override
    public String toString() {
        return "Admin{" +
                "imageId=" + imageId +
                ", artistInitial='" + artistInitial + '\'' +
                ", cityValue='" + cityValue + '\'' +
                ", typeValue='" + typeValue + '\'' +
                ", price=" + price +
                ", fileNameValue='" + fileNameValue + '\'' +
                ", artistId=" + artistId +
                '}';
    }

    public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public String getArtistInitial() {
            return artistInitial;
        }

        public void setArtistInitial(String artistInitial) {
            this.artistInitial = artistInitial;
        }

        public String getCityValue() {
            return cityValue;
        }

        public void setCityValue(String cityValue) {
            this.cityValue = cityValue;
        }

        public String getTypeValue() {
            return typeValue;
        }

        public void setTypeValue(String typeValue) {
            this.typeValue = typeValue;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getFileNameValue() {
            return fileNameValue;
        }

        public void setFileNameValue(String fileNameValue) {
            this.fileNameValue = fileNameValue;
        }

        public int getArtistId() {
            return artistId;
        }

        public void setArtistId(int artistId) {
            this.artistId = artistId;
        }

}
