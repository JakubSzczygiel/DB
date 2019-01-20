import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlLiteReader {
    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DATABASE_URL = "jdbc:sqlite:C:/Users/Jakub/IdeaProjects/Database/";
    static final String DATABASE_NAME = "chinook.db";

    public void readAlbumsWithSpecificGenreTracks(String genre) {
        try {
            Connection connection = createConnection(DATABASE_NAME);
            Statement statement = connection.createStatement();
            String sqlQuery = createQueryForReadingAlbumsWithSpecificGenreTracks(genre, connection);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            printResultSet(resultSet);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void printResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int albumID = resultSet.getInt("AlbumId");
            String albumTitle = resultSet.getString("Title");
            System.out.println(albumID + " " + albumTitle);
        }
    }

    private String createQueryForReadingAlbumsWithSpecificGenreTracks(String genre, Connection connection) throws SQLException {
        StringBuffer sqlQuery = new StringBuffer();
        sqlQuery.append("SELECT al.AlbumId, al.Title FROM albums as al");
        sqlQuery.append(" JOIN tracks as tr ON al.AlbumId=tr.AlbumId");
        sqlQuery.append(" JOIN genres as ge ON tr.GenreID=ge.GenreId");
        sqlQuery.append(" WHERE ge.Name='" + genre + "'");
        sqlQuery.append(" GROUP BY al.AlbumId");
        return sqlQuery.toString();
    }

    private Connection createConnection(String databaseName) throws SQLException {

        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL + databaseName);
            if (connection != null) {
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

}

