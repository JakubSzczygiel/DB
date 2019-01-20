public class Main {
    public static void main(String[] args) {

        SqlLiteReader sqlLiteReader=new SqlLiteReader();
        sqlLiteReader.readAlbumsWithSpecificGenreTracks("Rock");
    }
}
