package org.bhaduri.getnifty;
// Java program to read and download
// webpage in html file

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetNifty {

    public static void DownloadWebPage(String webpage) {
        try {
            // Create URL object
            URLConnection connection = new URL(webpage).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.connect();
            BufferedWriter writer;
            try (BufferedReader readr = new BufferedReader(new InputStreamReader(connection.getInputStream(),Charset.forName("UTF-8")))) {
                String fileTS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
                // Enter filename in which you want to download
                writer = new BufferedWriter(new FileWriter("Download" + fileTS + ".txt"));
                // read each line from stream till end
                String line;
                while ((line = readr.readLine()) != null) {
                    writer.write(line);
                } 
            } 
            writer.close();
            System.out.println("Successfully Downloaded.");
        } // Exceptions // Exceptions
        catch (MalformedURLException mue) {
            System.out.println("Malformed URL Exception raised");
        } catch (IOException ie) {
            System.out.println(ie);
        }
    }

    public static void main(String args[])
            throws IOException {
        String url = "https://in.investing.com/indices/s-p-cnx-nifty-components";
        DownloadWebPage(url);
    }
}
